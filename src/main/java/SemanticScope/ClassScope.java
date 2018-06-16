package main.java.SemanticScope;

import java.util.HashMap;

public class ClassScope {

    /**
     * This structure stores all class variables
     */
    private final HashMap<String,DataType> attributes;

    /**
     * This structure stores all methods in a class
     */
    private final HashMap<String, MethodScope> methods;

    /**
     *
     */
    private /*final*/ ProgramScope parent; //realmente necesita ser final?
    
    /**
     *
     */
    private final String className;

    /**
     *
     */
    public ClassScope(ProgramScope parent, String className) {
        this.className = className;
        this.parent = parent;
        this.attributes = new HashMap<>();
        this.methods = new HashMap<>();
    }

    /**
     *
<<<<<<< HEAD:src/main.java.SemanticScope/ClassScope.java
     * @return
     */
    public HashMap<String, DataType> getAttributes() {
        return attributes;
    }

    /**
     *
     * @param attributes
     */
    public void setAttributes(HashMap<String, DataType> attributes) {
        this.attributes = attributes;
    }
    
    public void setAttribute1(String name, DataType type){
        this.attributes.put(name, type);
    }
    
    public void getAttributeType(String name){
        this.attributes.get(name);
    }

    /**
     *
     * @return
     */
    public HashMap<String, Method> getMethods() {
        return methods;
    }

    /**
     *
     * @param methods
     */
    public void setMethods(HashMap<String, Method> methods) {
        this.methods = methods;
    }

    /**
     *
=======
>>>>>>> master:src/Semantic/ClassScope.java
     * @param name
     * @return
     */
    public DataType lookUpAttribute (String name){
        DataType d = this.attributes.get(name);
        return (d!=null) ? d : this.parent.lookUpVariable(name); 
    }

    /**
     *
     * @param name
     * @return
     */
    public MethodScope lookUpMethod (String name){
        MethodScope a = this.methods.get(name);
        return (a!= null) ? a : this.parent.lookUpMethod(name);
    }

    /**
     *
     * @param parent
     */
    public void setParent(ProgramScope parent) {
        this.parent = parent;
    }
    
    /**
     *
     * @param parent
     */
    public void addVariable(String typeName, String name){
        attributes.put(name, DataType.valueOf(typeName));
    }
    
<<<<<<< HEAD:src/SemanticScope/ClassScope.java
    public void addMethod(String name){
        //methods.put(name, new Method(name) );
=======
    /**
     *
     * @param parent
     */
    public MethodScope addMethod(String name ){
        MethodScope newMethod = new MethodScope(this, name);
        methods.put(name, newMethod);    //Así? cual se supone que sea el otro parámetro?
        return newMethod;
>>>>>>> master:src/Semantic/ClassScope.java
    }
}


