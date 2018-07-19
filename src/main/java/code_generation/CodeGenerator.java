package main.java.code_generation;

import main.java.semantic_analysis.*;
import main.java.syntax_analysis.statement.*;

import java.util.Map;
import java.util.Set;

public class CodeGenerator {

    private RegisterManager registerManager;

    private TagManager tagManager;

    // program root
    private ProgramScope root;
    // .data section in the final file
    private StringBuilder dataSection;
    // .text section in the final file
    private StringBuilder textSection;

    /**
     * @param root
     */
    public CodeGenerator(ProgramScope root) {
        this.root = root;
        registerManager = new RegisterManager();
        this.tagManager = new TagManager();
        dataSection = new StringBuilder();
        textSection = new StringBuilder();

        dataSection.append(".data\n");

        textSection.append(".text\n");
        textSection.append("jal main\n");
        textSection.append("li $v0, 10 \nsyscall\n");

        for (ClassScope classScope : root.classes.values()) {
            gen(classScope);
        }

        System.out.println(dataSection.toString() + textSection.toString());
    }

    /**
     * @param classScope
     */
    private void gen(ClassScope classScope) {
        for (MethodScope methodScope : classScope.methods.values()) {
            gen(methodScope);
        }
    }

    /**
     * @param methodScope
     */
    private void gen(MethodScope methodScope) {

        reserveVariables(methodScope.declarations.entrySet());

        textSection.append("#StartOfMethod ").append(methodScope.methodName).append("\n");

        textSection.append(methodScope.methodName).append(":\n");
        gen(methodScope.getBlock());
        textSection.append("jr $ra\n");
        textSection.append("#EndOfMethod ").append(methodScope.methodName).append("\n");
    }

    private void reserveVariables(Set<Map.Entry<String, DataType>> entries) {
        for (Map.Entry me : entries) {
            System.out.println(me.getKey());
            switch ((DataType) me.getValue()) {
                case NUMERO:
                    dataSection
                            .append(me.getKey())
                            .append(":\t.word\t0\t#declaration for string variable\n");
                    break;
                case LOGICO:
                    dataSection
                            .append(me.getKey())
                            .append(":\t.word\t0\t#declaration for string variable\n");
                    break;
                case PALABRA:
                    dataSection
                            .append(me.getKey())
                            .append(":\t.asciiz\t")
                            .append("\t#declaration for string variable\n");
                    break;
                case Vector:
                    dataSection.append("#VECTOR\n");
                    break;
                default:
                    System.err.println("Unrecognizable type");
                    break;
            }
        }
    }

    /**
     * @param asigmentStatement
     */
    private void gen(AsigmentStatement asigmentStatement) {
        textSection.append("#StartOfAsignment\n");
        String reg = registerManager.acquireRegister();
        gen(asigmentStatement.getExpression(), reg);
        textSection.append("sw ").append(reg).append( " , ").append(asigmentStatement.variableName).append("\n");
        textSection.append("#EndOfAsignment\n");
    }

    private void gen(ReturnStatement returnStatement) {
        textSection.append("#StartOfReturnStatement\n");
        gen(returnStatement.getExpression(), "a0");
        dataSection.append("jr $ra\n");
        textSection.append("#EndOfReturnStatement\n");
    }

    /**
     * Allows to generate code using ExpressionStatements
     *
     * @param expressionStatement the expression for generation
     */
    private void gen(ExpressionStatement expressionStatement, String register) {
        Evaluable eval1 = expressionStatement.getVar1();

        String reg1 = registerManager.acquireRegister();
        //check the Evaluable implementation

        if (eval1 instanceof Variable) {
            gen((Variable) eval1, reg1);
        } else if (eval1 instanceof Value) {
            gen((Value) eval1, reg1);
            // recursive call
        } else if (eval1 instanceof ExpressionStatement) {
            gen((ExpressionStatement) eval1, reg1);
        }
        //check if exists another var
        Evaluable eval2 = expressionStatement.getVar2();
        if (eval2 != null) {
            String reg2 = registerManager.acquireRegister();
            if (eval1 instanceof Variable) {
                gen((Variable) eval1, reg2);
            } else if (eval1 instanceof Value) {
                gen((Value) eval1, reg2);
                // recursive call
            } else if (eval1 instanceof ExpressionStatement) {
                gen((ExpressionStatement) eval1, reg2);
            }
            switch (expressionStatement.getOperator()) {
                case "+":
                    textSection.append("add\t").append(register).append(" , ").append(reg1).append(" , ").append(reg2).append("\n");
                    break;
                case "-":
                    textSection.append("sub\t").append(register).append(" , ").append(reg1).append(" , ").append(reg2).append("\n");
                    break;
                case "*":
                    textSection.append("mult\t").append(reg1).append(" , ").append(reg2).append("\n").append("mflo\t").append(register).append("\n");
                    break;
                case "/":
                    textSection.append("div\t").append(reg1).append(" , ").append(reg2).append("\n").append("mflo\t").append(register).append("\n");
                    break;
                case "<":
                    textSection.append("slt\t").append(register).append(" , ").append(reg1).append(" , ").append(reg2).append("\n");
                    break;
                case ">":
                    textSection.append("slt\t").append(register).append(" , ").append(reg2).append(" , ").append(reg1).append("\n");
                    break;
                case "<=":

                    break;
                case ">=":

                    break;
                case "==":

                    break;
                case "!=":

                    break;
                case "Y":
                    textSection.append("and\t").append(register).append(" , ").append(reg1).append(" , ").append(reg2);
                    break;
                case "O":
                    textSection.append("or\t").append(register).append(" , ").append(reg1).append(" , ").append(reg2);
                    break;
            }
        } else {
            textSection.append("move\t").append(register).append(" , ").append(reg1).append("\n");
        }
        registerManager.releaseRegister(reg1);
    }

    /**
     * Generate code for moving declared variables
     *
     * @param variable the variable object
     */
    private void gen(Variable variable, String register) {
        if (variable.parentBlock.parentMethod.parameters.containsKey(variable.getValue())) {
            int a = 0;
            for (String var : variable.parentBlock.parentMethod.parameters.keySet()) {
                if(!variable.getValue().equals(var)) a++;
                else break;
            }
            textSection
                    .append("ori $a").append(a).append(" , ").append(register).append("$0");
        } else {
            textSection
                    .append("lw\t")
                    .append(register)
                    .append(" , ")
                    .append(variable.getValue())
                    .append("\n");
        }

    }

    /**
     * @param functionStatement
     */
    private void gen(FunctionStatement functionStatement) {
        textSection.append("#StartFunctionCall\n");
        int a = 0;
        for (ExpressionStatement expression : functionStatement.parameters) {
            gen(expression, "$a" + a);
        }

        if (functionStatement.functionName.equals("imprimir")) {
            textSection.append("\naddi $v0, $0, 4 \nsyscall #Print a string in console\n");
        } else {
            textSection.append("jal ").append(functionStatement.functionName).append("\n");
        }
        textSection.append("#StartFunctionCall\n");
    }

    /**
     * Generate code for literal values
     *
     * @param value the value object
     */
    private void gen(Value value, String register) {
        switch (value.getType()) {
            case NUMERO:
                textSection
                        .append("li\t")
                        .append(register)
                        .append(" , ")
                        .append(value.getValue())
                        .append("\n");
                break;
            case LOGICO:
                // 1: true 0: false
                textSection
                        .append("li\t")
                        .append(register)
                        .append(" , ")
                        .append(value.getValue().equals("VERDADERO") ? 1 : 0)
                        .append("\n");
                break;
            case PALABRA:
                String tag = this.tagManager.getTag();
                dataSection
                        .append(tag)
                        .append(":\t.asciiz\t")
                        .append(value.getValue())
                        .append("\t#declaration for string variable\n");
                textSection
                        .append("la\t")
                        .append(register)
                        .append(",")
                        .append(tag)
                        .append("\t# load address of string to be printed\n");
                break;
            case Vector:
                //TODO implement vectors
                break;
            default:
                System.err.println("Unrecognized value type in gen(Value)");
                break;

        }
    }

    private void gen(Block block) {
        //reserve space for variables
        reserveVariables(block.getVariables().entrySet());

        for (Statement statement : block.getStatements()) {
            if (statement instanceof AsigmentStatement) {
                gen((AsigmentStatement) statement);

            } else if (statement instanceof FunctionStatement) {
                gen((FunctionStatement) statement);

            } else if (statement instanceof IfStatement) {
                gen((IfStatement) statement);

            } else if (statement instanceof ReturnStatement) {
                gen((ReturnStatement) statement);

            } else if (statement instanceof WhileStatement) {
                gen((WhileStatement) statement);
            }
        }
    }

    /**
     * @param ifStatement
     */
    private void gen(IfStatement ifStatement) {
        textSection.append("#StartOfIfStatement\n");
        String reg = registerManager.acquireRegister();
        String elseTag = tagManager.getTag();
        String endTag = tagManager.getTag();

        gen(ifStatement.getCondition(), reg);
        textSection.append("bez ").append(reg).append(" , ").append(elseTag).append("\n");
        registerManager.releaseRegister(reg);

        gen(ifStatement.getThenBlock());
        textSection.append("j ").append(endTag).append("\n");

        textSection.append(elseTag).append(":\n");
        gen(ifStatement.getElseBlock());
        textSection.append(endTag).append(":\n");

        textSection.append("#EndOfIfStatement\n");
    }

    /**
     * @param whileStatement
     */
    private void gen(WhileStatement whileStatement) {
        textSection.append("#StartOfWhileStatement\n");
        //gen(ifStatement.getCondition());
        String tag = tagManager.getTag();
        textSection.append(tag).append(":\n");
        gen(whileStatement.getBlock());
        String reg = registerManager.acquireRegister();
        gen(whileStatement.getCondition(), reg);

        //TODO add jump on condition
        textSection.append("BEQ ").append(reg).append(" , ").append("$0 , ").append(tag).append("\n");
        registerManager.releaseRegister(reg);

        textSection.append("#EndOfWhileStatement\n");
    }
}
