package main.java.semantic_analysis;

import main.java.DataType;

import java.util.HashMap; //Diccionario :v

import main.java.SemanticScope.*;

public class IfStatement implements Statement {

    private HashMap<String, DataType> contenido;
    private String operador;
    private DataType tipoExp = null;
    
    private final Block thenBlock;
    private final Block elseBlock;
    private ExpressionStatement condition;
    private Block parent;
    private MethodScope parentMethod;

    public IfStatement(Block parent,MethodScope parentMethod) {
        //this.type = "SI";
        condition = new  ExpressionStatement(parent,null);
        thenBlock = new Block(parentMethod,this);
        elseBlock = new Block(parentMethod,this);
        this.parent = parent;
        this.parentMethod = parentMethod;
    }
    
    public boolean typeCheck (){
        boolean res = true;
        res &= condition.getType() == DataType.LOGICO;
        res &= thenBlock.typeCheck();
        res &= (elseBlock!= null) ? elseBlock.typeCheck() : true;
        return res;
    }
    
    public ExpressionStatement getCondition(){
        return condition;
    }
    
    public Block getElseBlock(){
        return elseBlock;
    }
    
    public Block getParent(){
        return parent;
    }
    
    public Block getThenBlock(){
        return thenBlock;
    }
    
    public MethodScope lookUpMethod(String name){
        return parent.lookUpMethod(name);
    }
    
        
    public DataType lookUpVariable(String name){
        return parent.lookUpVariable(name);
    }

}