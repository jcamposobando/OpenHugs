package main.java.code_generation;

import main.java.semantic_analysis.*;
import main.java.syntax_analysis.statement.*;

public class CodeGenerator {

    RegisterManager registerManager;

    TagManager tagManager;

    // program root
    private ProgramScope root;
    // file manager
    private FileManager fileManager;
    // .data section in the final file
    private StringBuilder dataSection;
    // .text section in the final file
    private StringBuilder textSection;

    /**
     *
     * @param root
     */
    public CodeGenerator(ProgramScope root){
        this.root = root;
        this.registerManager = new RegisterManager();
        this.tagManager = new TagManager();
        this.fileManager = new FileManager();
    }

    /**
     *
     * @return
     */
    private boolean generate(){
       return false;
    }

    /**
     *
     * @param classScope
     */
    private void gen(ClassScope classScope){

    }

    /**
     *
     * @param methodScope
     */
    private void gen(MethodScope methodScope){

    }

    /**
     *
     * @param asigmentStatement
     */
    private void gen(AsigmentStatement asigmentStatement){

    }

    /**
     * Allows to generate code using ExpressionStatements
     * @param expressionStatement the expression for generation
     */
    private void gen(ExpressionStatement expressionStatement){
        Evaluable eval1 = expressionStatement.getVar1();
        //check the Evaluable implementation
        if(eval1 instanceof Variable){
            gen((Variable)eval1);
        } else if (eval1 instanceof ExpressionStatement){
            gen((ExpressionStatement)eval1);
        } else if (eval1 instanceof Value){
            gen((Value)eval1);
        }
    }

    /**
     * Generate code for moving declared variables
     * @param variable the variable object
     */
    private void gen(Variable variable, String register){
        this.textSection.append(
                "la\t"+ register + " , " + variable.getValue() + "\n"
        );
    }

    /**
     *
     * @param functionStatement
     */
    private void gen(FunctionStatement functionStatement){

    }

    /**
     * Generate code for literal values
     * @param value the value object
     */
    private void gen(Value value, String register){
        switch (value.getType()){
            case NUMERO:
                this.textSection.append(
                        "li\t" + register + " , " + value.getValue() + "\n"
                );
                break;
            case LOGICO:
                // 1: true 0: false
                if(value.getValue().equals("VERDADERO"))
                    this.textSection.append(
                            "li\t" + register + " , " + 1 + "\n"
                    );
                else
                    this.textSection.append(
                            "li\t" + register + " , " + 0 + "\n"
                    );
                break;
            case PALABRA:
                String tag = this.tagManager.getTag();
                this.dataSection.append(
                        tag + "\t.asciiz\t" + "\""+value.getValue()+"\"" + "\t#declaration for string variable\n"
                );
                this.textSection.append(
                        "la\t" + register + "," + tag + "\t# load address of string to be printed\n"
                );
                break;
            case NONE:
                System.out.println("Pánico");
                break;
            case OPERATOR:
                System.out.println("Pánico");
                break;
            case VECTOR:
                //check this
                break;
        }
    }
    
    private void gen(Block block){

    }

    /**
     *
     * @param ifStatement
     */
    private void gen(IfStatement ifStatement){
        this.gen(ifStatement.getCondition());
        this.gen(ifStatement.getElseBlock());
        this.gen(ifStatement.getThenBlock());
    }

    /**
     *
     * @param whileStatement
     */
    private void gen(WhileStatement whileStatement){

    }
}
