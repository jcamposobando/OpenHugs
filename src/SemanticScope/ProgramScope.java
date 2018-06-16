package SemanticScope;

import java.util.HashMap;

import src.DataType;

public class ProgramScope {

    private HashMap<String,ClassScope> classes;

    public ProgramScope () {
        classes = new HashMap<>();
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
}
