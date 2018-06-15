package SyntacticTree;

import java.util.*;

import SemanticClasses.*;
import src.*;

public class Node{
    Statement st;
    ArrayList<Statement> objects = new ArrayList<>();  
    
    public Node(){
        
    }
    
    public Node(StatementType type){
        //cambiar por un switch
        if(type == StatementType.SI){
            this.st = new IfStatement();
        }
        else if(type == StatementType.CICLO){
            this.st = new WhileStatement();
        }
        else if(type == StatementType.SINO){
            this.st = new IfStatement(); //hay que hacer algo para diferenciar que es el else de un if
        }
        else if(type == StatementType.CLASE){
            this.st = new ClassStatement();
        }
        else if(type == StatementType.FUNCION){
            this.st = new FunctionStatement();
        }                                       //estos no sé si hay que incluirlos
        else if(type == StatementType.NONE){
            System.out.println("NODE: NONE");
        }
        else if(type == StatementType.INCORPORAR){
            //imports 
            System.out.println("NODE: INCORPORAR");
        }
        else if(type == StatementType.NUMERO){
            //
            System.out.println("NODE: NUMERO");
        }
        else if(type == StatementType.PALABRA){
            //
            System.out.println("NODE: PALABRA");
        }
        else if(type == StatementType.LOGICO){
            //
            System.out.println("NODE: LOGICO");
        }
        else if(type == StatementType.RETORNAR){
            //
            System.out.println("NODE: RETORNAR");
        }
        else if(type == StatementType.VERDADERO){
            //
            System.out.println("NODE: VERDADERO");
        }
        else if(type == StatementType.FALSO){
            //
            System.out.println("NODE: FALSO");
        }
        else{
            //default case
            System.out.println("NODE: no es un tipo de statement");
        }
    }
}


/* to use later 

    if(type == StatementType.SI){
            
        }
        else if(type == StatementType.CICLO){
            
        }
        else if(type == StatementType.NONE){
            
        }
        else if(type == StatementType.SINO){
            
        }
        else if(type == StatementType.CLASE){
            
        }
        else if(type == StatementType.INCORPORAR){
            
        }
        else if(type == StatementType.FUNCION){
            
        }
        else if(type == StatementType.NUMERO){
            
        }
        else if(type == StatementType.PALABRA){
            
        }
        else if(type == StatementType.RETORNAR){
            
        }
        else if(type == StatementType.LOGICO){
            
        }
        else if(type == StatementType.VERDADERO){
            
        }
        else if(type == StatementType.FALSO){
            
        }
        else{
            //default case
        }
*/