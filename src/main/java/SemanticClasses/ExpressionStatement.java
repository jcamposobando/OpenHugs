package main.java.SemanticClasses;

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
    
    public DataType getType() {
        return DataType.NUMERO;
    };
    
    public boolean checkType(DataType type){
        return true;
    };
    
    public void  setOperator ( String operator){
        this.operator = new Operator(operator);
    };
    
    public ExpressionStatement getSubExpression() {
        var2 =new ExpressionStatement(parent,this);
        return (ExpressionStatement) var2;
    };
    
    public FunctionStatement addFunctionCall (String name){
        var1 = new FunctionStatement(name);
        return (FunctionStatement) var1;
    };

    public ExpressionStatement (Block parent, ExpressionStatement parentExpression ){
        this.parent = parent;
        this.parentExpression = parentExpression;
    }
    
    public MethodScope lookUpMethod(String name){
        return parent.lookUpMethod(name);
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
    
    public void addExpression (){
        var1 = new ExpressionStatement (parent, this);
    }
    
    
    
    
    
    
    
    
    
    
    
    
    Tree exp;
    StatementType stType;
    
    public void addExpression(DataType type1, String var1, DataType type2, String var2, String op){ //var es cualquier cosa, action es el operador
        this.exp.setRoot(DataType.OPERATOR, op);
        this.exp.setLeft(type1, var1);
        this.exp.setRight(type2, var2);
    }
    
    public void setVar1(DataType type, String name){
        this.exp.setLeft(type, name);
    }
    
    public void setVar1Type(DataType t){
        this.exp.root.h_left.setDataType(t);
    }
    
    public void setVar1Name(String n){
        this.exp.root.h_right.setDataName(n);
    }

    public void setVar2(DataType type, String name){
        this.exp.setRight(type, name);
    }
    
    public void setVar2Type(DataType t){
        this.exp.root.h_left.setDataType(t);
    }
    
    public void setVar2Name(String n){
        this.exp.root.h_right.setDataName(n);
    }
    
    public void setOperatorrrrrr(String operador){
        this.exp.setRoot(DataType.OPERATOR, operador);
    }
    
    public Boolean compare(){
        return (this.exp.getRoot().getRightNode().getDataType() == this.exp.getRoot().getLeftNode().getDataType());
    }

}