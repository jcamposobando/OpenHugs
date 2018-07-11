package main.java.SemanticClasses;

import java.util.HashMap;

import main.java.*;

import main.java.SemanticScope.*;

public class WhileStatement implements Statement{

    private final Block block;
    
    private final ExpressionStatement condition;

    private final Block parent;
    
    private final MethodScope parentMethod;
    
    public WhileStatement(Block parent, MethodScope parentMethod){
        this.parentMethod = parentMethod;
        this.parent = parent;
        this.block = new Block(parentMethod,this);
        this.condition = new ExpressionStatement(parent , null);
    }
    
    public Block getBlock () {
        return block;
    }
    
    public Block getParent(){
        return parent;
    }


    public MethodScope lookUpMethod(String name){
        return parent.lookUpMethod(name);
    }    
    
    public DataType lookUpVariable(String name){
        return parent.lookUpVariable(name);
    }
    
    public boolean typeCheck (){
        boolean res = true;
        res &= condition.getType() == DataType.LOGICO;
        res &= block.typeCheck();
        return res;
    }
    
    
    public ExpressionStatement getCondition(){
        return condition;
    }
    
}