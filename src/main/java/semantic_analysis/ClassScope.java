package main.java.semantic_analysis;

import java.util.HashMap;

public class ClassScope {

    private final HashMap<String,DataType> attributes;

    private final HashMap<String, MethodScope> methods;

    private ProgramScope parent; 
    
    private final String className;

    public ClassScope(ProgramScope parent, String className) {
        this.className = className;
        this.parent = parent;
        this.attributes = new HashMap<>();
        this.methods = new HashMap<>();
        System.out.println("ClassScope");
    }
    
    public DataType lookUpAttribute (String name){
        System.out.println("ClassScope lookUpAttribute");
        DataType d = this.attributes.get(name);
        return (d!=null) ? d : this.parent.lookUpVariable(name); 
    }
    
    public MethodScope lookUpMethod (String name){
        System.out.println("ClassScope lookUpMethod");
        MethodScope a = this.methods.get(name);
        return (a!= null) ? a : this.parent.lookUpMethod(name);
    }
    public void setParent(ProgramScope parent) {
        this.parent = parent;
    }
    public void addVariable(String typeName, String name){
        attributes.put(name, DataType.valueOf(typeName));
    }
    
    public MethodScope addMethod(String name ){
        MethodScope newMethod = new MethodScope(this, name);
        methods.put(name, newMethod);    //Así? cual se supone que sea el otro parámetro?
        return newMethod;
    }
    
    public boolean typeCheck(){
        boolean res = true;
        for (MethodScope meth : methods.values()){
            res &= meth.typeCheck();
        }
        if (!res) System.err.println("Error en la clase "+className);
        return res;
    }
}


