package main.java.SemanticClasses;

import java.util.Vector;

import java.util.HashMap;

import main.java.*;

import main.java.SemanticScope.*;


public class Block{
    
    HashMap<String,DataType> variables;
    
    Vector<Statement> statements;
    
    private final MethodScope parentMethod;
    
    private final Statement parentBlock;
    
    public MethodScope lookUpMethod (String name){
        return parentMethod.lookUpMethod(name) ;
    }
    
    public  MethodScope getParentMethod  () {
        return parentMethod;
    }
    
    public DataType lookUpVariable(String name){
        DataType dt = variables.get(name);
        if ( dt != null ) return  dt;
        else if ( parentBlock != null ) {
            dt = parentBlock.lookUpVariable(name);
                if (dt != null) return dt;
        }
        return parentMethod.lookUpVariable(name);
    }
    
    public Block( MethodScope parentMethod, Statement parentBlock){
        variables = new HashMap<>();
        statements = new Vector<>();
        this.parentMethod = parentMethod;
        this.parentBlock = parentBlock;
    }
    
    public Block(Vector<Statement> s){
        this.statements = s;
        
        this.parentMethod = null;
        this.parentBlock = null;
    }
    
    public void addStatement(Statement s){
        this.statements.add(s);
    }
    
    public IfStatement addIfStatement(){
        IfStatement ifStatement = new IfStatement(this,parentMethod);
        statements.add(ifStatement);
        return ifStatement;
    }
    
    public WhileStatement addWhileStatement(){
        WhileStatement statement = new WhileStatement(this,parentMethod);
        statements.add(statement);
        return statement;
    }
    
    public AsigmentStatement addAssigmentStatement(String name){
        AsigmentStatement statement = new AsigmentStatement(this, name);
        statements.add(statement);
        return statement;
    }
    
    public FunctionStatement addFunctionCall(String name){
        FunctionStatement statement = new FunctionStatement(name);
        statements.add(statement);
        return statement;
    }
    
    
    public void addVariable (String typeName, String name){
        variables.put(name, DataType.valueOf(typeName));
    }

    public boolean typeCheck () {
        boolean res = true;
        for (Statement st : statements){
            res &= st.typeCheck();
        }
        return res;
    }
}