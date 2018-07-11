package main.java.SyntacticTree;

import java.util.*;

import main.java.*;
import main.java.SemanticClasses.*;

public class Node{
    Statement st;
    Set<Node> objects;// = new Vector<>();  
    
    public Node(){
        System.out.println("Node: sin parámetros");
        objects = new HashSet<>();  
    }
    
    public Node(StatementType type){
        System.out.println("Node: con parámetros");
        objects = new HashSet<>();  
        
        switch(type){
            case SI:
                //
                this.st = new IfStatement();
                break;
            case CICLO:
                //
                this.st = new WhileStatement();
                break;
            case SINO:
                this.st = new IfStatement(); //hay que hacer algo para diferenciar que es el else de un if
                //
                break;
            case CLASE:
                this.st = new ClassStatement();
                //
                break;
            case FUNCION:
                this.st = new FunctionStatement();
                //
                break;
            case INCORPORAR:
                //
                System.out.println("NODE: INCORPORAR");
                break;
            case NONE:
                //
                System.out.println("NODE: NONE");
                break;
            case NUMERO:
                //
                System.out.println("NODE: NUMERO");
                break;
            case PALABRA:
                //
                System.out.println("NODE: PALABRA");
                break;
            case LOGICO:
                //
                System.out.println("NODE: LOGICO");
                break;
            case RETORNAR:
                //
                System.out.println("NODE: RETORNAR");
                break;
            case VERDADERO:
                //
                System.out.println("NODE: VERDADERO");
                break;
            case FALSO:
                //
                System.out.println("NODE: FALSO");
                break;
            default:
                //
                System.out.println("NODE: no es un tipo de statement");
        }
    }
    
    public void add(StatementType newStatement){
        this.objects.add(new Node(newStatement));
    }
    
    public boolean exist(StatementType statement){
        return this.objects.contains(statement);
    }
}


/* to use later 

    switch(type){
            case SI:
                //
                break;
            case CICLO:
                //
                break;
            case SINO:
                //
                break;
            case CLASE:
                //
                break;
            case FUNCION:
                //
                break;
            case INCORPORAR:
                //
                break;
            case NONE:
                //
                break;
            case NUMERO:
                //
                break;
            case PALABRA:
                //
                break;
            case LOGICO:
                //
                break;
            case RETORNAR:
                //
                break;
            case VERDADERO:
                //
                break;
            case FALSO:
                //
                break;
            default:
                //
                System.out.println("NODE: no es un tipo de statement");
        }
*/