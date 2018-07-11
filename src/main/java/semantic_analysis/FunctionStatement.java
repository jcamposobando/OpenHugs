package main.java.semantic_analysis;

import main.java.*;

import java.util.Vector;

import main.java.SemanticClasses.*;

import main.java.SemanticScope.*;

public class FunctionStatement implements Statement,Evaluable{
    
    private final String functionName;
    
    private final Vector<ExpressionStatement> parameters;
    
    private Block parent;
    
    private DataType returnType;
    
    StatementType stType;
    
    public FunctionStatement(String name){
        this.functionName = name;
        this.parameters = new Vector<>();
        this.returnType = null;
    }
    
    public ExpressionStatement addParameter(){
        ExpressionStatement parameter = new ExpressionStatement(parent,null);
        parameters.add(parameter);
        return parameter;
    }
    
    public DataType getType() {
        return parent.lookUpMethod(functionName).getReturnType();
    }
    
    public boolean checkType(DataType type){
        return type == getType();
    };
    
    public MethodScope lookUpMethod(String name){
        return parent.lookUpMethod(name);
    }
    
    public DataType lookUpVariable(String name){
        return parent.lookUpVariable(name);
    }
    
    public boolean typeCheck () {
        boolean res = true;
        return true;
    }
    
}