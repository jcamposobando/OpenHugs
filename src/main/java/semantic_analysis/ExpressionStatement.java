package main.java.semantic_analysis;

import main.java.*;

import main.java.SemanticClasses.*;

import main.java.SemanticScope.*;

public class ExpressionStatement implements Evaluable{
    
    private final Block parent;
    
    private DataType type;
    
    private Evaluable var1;
    
    private Evaluable var2;
    
    private Operator operator;
    
    private final ExpressionStatement parentExpression;

    public ExpressionStatement (Block parent, ExpressionStatement parentExpression ){
        this.parent = parent;
        this.parentExpression = parentExpression;
    }
    
    public DataType getType() {
        return DataType.NUMERO;
    }
    
    public void  setOperator ( String operator){
        this.operator = new Operator(operator);
    }
    
    public ExpressionStatement getSubExpression() {
        var2 =new ExpressionStatement(parent,this);
        return (ExpressionStatement) var2;
    }
    
    public FunctionStatement addFunctionCall (String name){
        var1 = new FunctionStatement(name);
        return (FunctionStatement) var1;
    }
    
    public ExpressionStatement getParent(){
        return parentExpression;
    }
    
    public void addValue (String value){
        var1 = new Value(parent,this,value);
    }
    
    public void addVariable (String value){
        var1 = new Variable (parent, this, value);
    }
    
    public MethodScope lookUpMethod(String name){
        return parent.lookUpMethod(name);
    }
    
    public void addExpression (){
        var1 = new ExpressionStatement (parent, this);
    }
    
    public boolean checkType(DataType type){
        return true;
    }
    
}