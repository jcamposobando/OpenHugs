package Semantic;

import java.util.HashMap;

public class ClassScope {

    /**
     * This structure stores all class variables
     */
    private HashMap<String,DataType> attributes;

    /**
     * This structure stores all methods in a class
     */
    private HashMap<String, Method> methods;

    /**
     *
     */
    private ProgramScope parent;
    
    private String className;

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
    public Method lookUpMethod (String name){
        Method a = this.methods.get(name);
        return (a!= null) ? a : this.parent.lookUpMethod(name);
    }

    /**
     *
     * @return
     */
    public ProgramScope getParent() {
        return parent;
    }

    /**
     *
     * @param parent
     */
    public void setParent(ProgramScope parent) {
        this.parent = parent;
    }
    
    
    public void addVariable(String name){
        attributes.put(name, DataType.NUMERO);
    }
    
    public void addMethod(String name){
        methods.put(name, new Method(name) );
    }
    
}
