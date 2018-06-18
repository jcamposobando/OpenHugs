package main.java.SemanticClasses;

import java.util.Vector;

import main.java.SemanticScope.*;

import main.java.*;

public class AsigmentStatement implements Statement{
    private final String variableName;
    
    private final ExpressionStatement expresion;
    
    private final Block parent;
    
    public  AsigmentStatement (Block parent, String variableName){
        this.variableName = variableName;
        this.expresion = new ExpressionStatement( parent , null);
        this.parent = parent;
    }
    
    public ExpressionStatement getExpression(){
        return expresion;
    }
    
    public MethodScope lookUpMethod(String name){
        return parent.lookUpMethod(name);
    }
    
    public DataType lookUpVariable(String name){
        return parent.lookUpVariable(name);
    }
    
    public boolean typeCheck () {
        return expresion.getType() == lookUpVariable(variableName);
    }
}