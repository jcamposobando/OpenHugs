package main.java.SemanticClasses;

import main.java.*;

public class ExpressionStatement implements Statement{
    
    Tree exp;
    StatementType stType;

    public ExpressionStatement(){
        this.stType = StatementType.EXPRESSION;
        this.exp = new Tree();
    }
    
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
    
    public void setOperator(String operador){
        this.exp.setRoot(DataType.OPERATOR, operador);
    }
    
    public Boolean compare(){
        return (this.exp.getRoot().getRightNode().getDataType() == this.exp.getRoot().getLeftNode().getDataType());
    }
    
    public StatementType getType(){
        return this.stType;
    }

}