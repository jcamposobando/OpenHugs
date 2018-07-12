package main.java.syntax_analysis.statement;

import main.java.semantic_analysis.Block;
import main.java.semantic_analysis.DataType;
import main.java.semantic_analysis.MethodScope;

public class ReturnStatement implements Statement {
    
    private final ExpressionStatement expresion;
    
    private final Block parent;
    
    public  ReturnStatement (Block parent){
        this.expresion = new ExpressionStatement( parent , null);
        this.parent = parent;
    }
    
    public ExpressionStatement getExpression(){
        return expresion;
    }
    
    public boolean typeCheck () {
        return expresion.getType() == parent.getReturnType();
    }
    
    public MethodScope lookUpMethod(String name){
        return parent.lookUpMethod(name);
    }
    
    public DataType lookUpVariable(String name){
        return parent.lookUpVariable(name);
    }
}