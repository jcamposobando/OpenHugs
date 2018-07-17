package main.java.code_generation;

import main.java.semantic_analysis.*;
import main.java.syntax_analysis.statement.*;

import java.util.Map;

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

        dataSection.append("data.\n");

        textSection.append("text.\n");
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
        textSection.append(methodScope.methodName).append(":\n");
        gen(methodScope.getBlock());
        textSection.append("jr\n");
    }

    /**
     * @param asigmentStatement
     */
    private void gen(AsigmentStatement asigmentStatement) {

    }

    private void gen(ReturnStatement returnStatement) {
        String reg = registerManager.acquireRegister();
        gen(returnStatement.getExpression(),reg);
        registerManager.releaseRegister(reg);
        dataSection.append("jr\n");
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
    }

    /**
     * Generate code for moving declared variables
     *
     * @param variable the variable object
     */
    private void gen(Variable variable, String register) {
        textSection
                .append("la\t")
                .append(register)
                .append(" , ")
                .append(variable.getValue())
                .append("\n");
    }

    /**
     * @param functionStatement
     */
    private void gen(FunctionStatement functionStatement) {

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
                        .append("\t.asciiz\t")
                        .append("\"")
                        .append(value.getValue())
                        .append("\"")
                        .append("\t#declaration for string variable\n");
                textSection
                        .append("la\t")
                        .append(register)
                        .append(",")
                        .append(tag)
                        .append("\t# load address of string to be printed\n");
                break;
            case VECTOR:
                //TODO implement vectors
                break;
            default:
                System.err.println("Unrecognized value type in gen(Value)");
                break;

        }
    }

    private void gen(Block block) {

        //reserve space for variables
        for (Map.Entry me : block.getVariables().entrySet()) {
            switch ((DataType) me.getValue()) {
                case NUMERO:
                    dataSection
                            .append(me.getKey())
                            .append("\t.word\t0\t#declaration for string variable\n");
                    break;
                case LOGICO:
                    dataSection
                            .append(me.getKey())
                            .append("\t.word\t0\t#declaration for string variable\n");
                    break;
                case PALABRA:
                    dataSection
                            .append(me.getKey())
                            .append("\t.asciiz\t")
                            .append("\t#declaration for string variable\n");
                    break;
                case VECTOR:
                    dataSection.append("#VECTOR\n");
                    break;
                default:
                    System.err.println("Unrecognizable type");
                    break;
            }
        }

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
        String reg = registerManager.acquireRegister();
        String elseTag = tagManager.getTag();
        String endTag= tagManager.getTag();

        gen(ifStatement.getCondition(), reg);
        registerManager.releaseRegister(reg);
        textSection.append("bez ").append(reg).append(" , ").append(elseTag).append("\n");

        gen(ifStatement.getThenBlock());
        textSection.append("j ").append(endTag).append("\n");

        textSection.append(elseTag).append(":\n");
        gen(ifStatement.getElseBlock());
        textSection.append(endTag).append(":\n");
    }

    /**
     * @param whileStatement
     */
    private void gen(WhileStatement whileStatement) {
        //gen(ifStatement.getCondition());
        String tag = tagManager.getTag();
        textSection.append(tag).append(":\n");
        gen(whileStatement.getBlock());
        String reg = registerManager.acquireRegister();
        gen(whileStatement.getCondition(), reg);

        //TODO add jump on condition
        textSection.append("bez ").append(reg).append(" , ").append(tag).append("\n");
        registerManager.releaseRegister(reg);
    }
}
