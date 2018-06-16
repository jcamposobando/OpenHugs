package main.java.SemanticScope;

import java.util.HashMap;

import java.util.LinkedHashMap;

import src.DataType;


/**
 *
 */
public class MethodScope {

    /**
     *
     */
    private HashMap<String,DataType> declarations;

    /**
     *
     */
    private final ClassScope parent;
    
    private final String methodName;
    
    private DataType returnType;
    
    private final Block block;
    
    /**
     *
     */
    private final LinkedHashMap<String, DataType> parameters;
    
    /**
     *
     */
    public MethodScope(ClassScope parent, String methodName){
        this.methodName = methodName;
        this.parent = parent;
        this.declarations = new HashMap<>();
        this.parameters = new LinkedHashMap<>();
        this.returnType = returnType;
        this.block = new Block();
    }
    
    public void setReturnType( String returnType){
        this.returnType =  DataType.valueOf(returnType); 
    }
    
    public DataType lookUpVariable(String name){
        DataType dec = declarations.get(name);
        DataType par = parameters.get(name);
        return (dec!=null) ? dec : (par!=null) ? par : parent.lookUpAttribute(name);
    }

    public MethodScope lookUpMethod(String name){
        return this.parent.lookUpMethod(name);
    }
    
    public void addVariable(String typeName, String name){
        declarations.put(name, DataType.valueOf(typeName));
    }
    
    public void addParameter(String typeName, String name){
        parameters.put(name, DataType.valueOf(typeName)); 
    }
    
    public Block getBlock(){
        return block;
    }
    
}

