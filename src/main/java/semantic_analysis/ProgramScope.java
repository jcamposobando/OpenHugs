package main.java.semantic_analysis;

import java.util.HashMap;

import main.java.*;

public class ProgramScope {

    private HashMap<String,ClassScope> classes;
    private HashMap<String,ClassScope> imports;


    public ProgramScope () {
        classes = new HashMap<>();
        imports = new HashMap<>();
    }
    
    public DataType lookUpVariable(String name){
        System.out.println("ERROR: variable " + name + " no encontrado ");
        return null;
    }

    public MethodScope lookUpMethod(String name){
        System.out.println("ERROR: metodo " + name + " no encontrado ");
        return null;
    }

    public ClassScope addClass(String name){
        ClassScope newClass = new ClassScope(this, name);
        classes.put(name, newClass);
        return newClass;
    }
    
    public void addImport (String name){
        imports.put(name, new ClassScope(this,name));
    }
    
    public boolean typeCheck(){
        boolean res = true;
        for( ClassScope cls : classes.values())  res &= cls.typeCheck();
        for( ClassScope cls : imports.values())  res &= cls.typeCheck();
        return res;
    }
}
