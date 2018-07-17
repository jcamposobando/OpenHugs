package main.java.semantic_analysis;

import java.util.HashMap;

public class ProgramScope {

    public HashMap<String, ClassScope> classes;
    private HashMap<String, ClassScope> imports;
    private HashMap<String, MethodScope> builtIns;


    public ProgramScope() {
        classes = new HashMap<>();
        imports = new HashMap<>();
        builtIns = loadBuiltins();
    }

    private HashMap<String, MethodScope> loadBuiltins() {
        HashMap<String, MethodScope> hash = new HashMap<>();
        MethodScope imprimir = new MethodScope(null, "imprimir");
        imprimir.addParameter("PALABRA", "value");
        imprimir.setReturnType("NONE");
        hash.put("imprimir", imprimir);
        return hash;
    }

    public DataType lookUpVariable(String name) {
        System.out.println("ERROR: variable " + name + " no encontrado ");
        return null;
    }

    public MethodScope lookUpMethod(String name) {
        MethodScope method = builtIns.get(name);
        if (method != null) return method;
        else {
            System.out.println("ERROR: metodo " + name + " no encontrado ");
            return null;
        }
    }

    public ClassScope addClass(String name) {
        ClassScope newClass = new ClassScope(this, name);
        classes.put(name, newClass);
        return newClass;
    }

    public void addImport(String name) {
        imports.put(name, new ClassScope(this, name));
    }

    public boolean typeCheck() {
        boolean res = true;
        for (ClassScope cls : classes.values()) res &= cls.typeCheck();
        for (ClassScope cls : imports.values()) res &= cls.typeCheck();
        return res;
    }
}
