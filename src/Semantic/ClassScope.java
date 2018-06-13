package Semantic;

import java.util.HashMap;

public class ClassScope {

    /**
     * This structure provides all global variables
     */
    private HashMap<String,DataType> attributes;

    /**
     * This structure provide all methods in a class
     */
    private HashMap<String, MethodScope> methods;

    /**
     *
     */
    private ProgramScope parent;

    /**
     *
     */
    public ClassScope(ProgramScope parent) {
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
    public HashMap<String, MethodScope> getMethods() {
        return methods;
    }

    /**
     *
     * @param methods
     */
    public void setMethods(HashMap<String, MethodScope> methods) {
        this.methods = methods;
    }

    /**
     *
     * @param name
     * @return
     */
    public DataType lockUpAttribute (String name){
        return this.attributes.get(name);
    }

    /**
     *
     * @param name
     * @return
     */
    public MethodScope lockUpMethod (String name){
        return this.methods.get(name);
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
}
