package main.java.code_generation;

import main.java.semantic_analysis.*;
import main.java.syntax_analysis.statement.*;

public class CodeGenerator {

    RegisterManager registerManager;

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
     *
     * @param variable
     */
    private void gen(Variable variable){

    }

    /**
     *
     * @param functionStatement
     */
    private void gen(FunctionStatement functionStatement){

    }

    /**
     *
     * @param value
     */
    private void gen(Value value){

    }

    /**
     *
     * @param ifStatement
     */
    private void gen(IfStatement ifStatement){

    }

    /**
     *
     * @param whileStatement
     */
    private void gen(WhileStatement whileStatement){

    }
}
