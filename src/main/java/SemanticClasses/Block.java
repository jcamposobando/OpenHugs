package main.java.SemanticClasses;

import java.util.Vector;

import main.java.*;

public class Block{
    Vector<Statement> statements;
    
    public Block(){}
    
    public Block(Vector<Statement> s){
        this.statements = s;
    }
    
    public void addStatement(Statement s){
        this.statements.add(s);
    }
    
}