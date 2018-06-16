package main.java.SemanticTree;

import java.util.Vector;

public class AsigmentStatement implements Statement{
    private final String variableName;
    
    private final ExpressionStatement expresion;
    
    private final Block parent;
    
    public  AsigmentStatement (Block parent, String variableName){
        this.variableName = variableName;
        this.expresion = new ExpressionStatement();
        this.parent = parent;
    }
    
    public ExpressionStatement getExpression(){
        return expresion;
    }
}