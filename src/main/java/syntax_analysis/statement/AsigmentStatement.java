package main.java.syntax_analysis.statement;

import main.java.semantic_analysis.Block;
import main.java.semantic_analysis.DataType;
import main.java.semantic_analysis.MethodScope;

public class AsigmentStatement implements Statement {
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
    
    public DataType lookUpVariable(String name){
        return parent.lookUpVariable(name);
    }
    
    public boolean typeCheck () {
        return expresion.getType() == lookUpVariable(variableName);
    }
    
    public MethodScope lookUpMethod(String name){
        return parent.lookUpMethod(name);
    }
}