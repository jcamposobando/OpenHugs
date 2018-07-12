package main.java.code_generation;

import main.java.semantic_analysis.*;
import main.java.syntax_analysis.statement.*;
import java.util.*;

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
        
        /*
        hacer cosas con las variables
        */
        Set set = block.getVariables();
        Iterator i = set.iterator();
        
        while(i.hasNext()) {
            Map.Entry me = (Map.Entry)i.next();
            
            //System.out.print(me.getKey() + ": ");
            
            switch((DataType)me.getValue()){
                case NUMERO:
                    this.dataSection.append(
                        (String)me.getKey() + "\t.word\t0\t#declaration for string variable\n"
                    );
                    break;
                case LOGICO:
                    this.dataSection.append(
                        (String)me.getKey() + "\t.word\t0\t#declaration for string variable\n"
                    );
                    break;
                case NONE:
                    break;
                case PALABRA:
                    this.dataSection.append(
                        (String)me.getKey() + "\t.asciiz\t""\t#declaration for string variable\n"
                    );
                    break;
                case OPERATOR:
                    this.dataSection.append("#OPERATOR\n");
                    break;
                case VECTOR:
                    this.dataSection.append("#VECTOR\n");
                    break;
            }
        }
        
        Vector<Statement> statements = block.getStatements();
        Enumeration vStm = statements.elements();
      
        while(vStm.hasMoreElements()){
            Statement sval1 = vStm.nextElement();
        
            if(sval1 instanceof AsigmentStatement){
                this.gen(AsigmentStatement)sval1);
                
            } else if (sval1 instanceof ExpressionStatement){
                this.gen((ExpressionStatement)sval1);
                
            } else if (sval1 instanceof FunctionStatement){
                this.gen((FunctionStatement)sval1);
                
            }else if (sval1 instanceof IfStatement){
                this.gen((IfStatement)sval1);
                
            }else if (sval1 instanceof ReturnStatement){
                this.gen((ReturnStatement)sval1);
                
            }else if (sval1 instanceof WhileStatement){
                this.gen((WhileStatement)sval1);
            }
        }
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
