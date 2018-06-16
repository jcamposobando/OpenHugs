package main.java.SemanticTree;

import java.util.Vector;


public class Block{
    Vector<Statement> statements;
    
    public Block(){}
    
    public Block(Vector<Statement> s){
        this.statements = s;
    }
    
    public void addStatement(Statement s){
        this.statements.add(s);
    }
    
    public IfStatement addIfStatement(){
        IfStatement ifStatement = new IfStatement(this);
        statements.add(ifStatement);
        return ifStatement;
    }
    
    public WhileStatement addWhileStatement(){
        WhileStatement statement = new WhileStatement(this);
        statements.add(statement);
        return statement;
    }
    
    public AsigmentStatement addAssigmentStatement(string name){
        AsigmentStatement statement = new AsigmentStatement(this, name);
        statements.add(statement);
        return statement;
    }
}