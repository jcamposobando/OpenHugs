package main.java.code_generation;

import main.java.semantic_analysis.ClassScope;
import main.java.semantic_analysis.MethodScope;
import main.java.semantic_analysis.ProgramScope;
import main.java.syntax_analysis.statement.*;

public class CodeGenerator {

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
     *
     * @param expressionStatement
     */
    private void gen(ExpressionStatement expressionStatement){

    }

    /**
     *
     * @param functionStatement
     */
    private void gen(FunctionStatement functionStatement){

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
