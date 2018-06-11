package Semantic;

import java.util.HashMap;

/**
 *
 */
public class MethodScope implements Scope{

    /**
     *
     */
    private HashMap<String,DataType> declarations;

    /**
     *
     */
    private HashMap<String,PrimitiveScope> primitives;

    /**
     *
     */
    private ClassScope parent;
    
    private String methodName;

    /**
     *
     */
    public MethodScope(ClassScope parent, String methodName){
        this.methodName = methodName;
        this.parent = parent;
        parent.addMethod(methodName);
        this.declarations = new HashMap<>();
        this.primitives = new HashMap<>();
    }

    /**
     *
     * @return
     */
    public HashMap<String, DataType> getDeclarations() {
        return declarations;
    }

    /**
     *
     * @param declarations
     */
    public void setDeclarations(HashMap<String, DataType> declarations) {
        this.declarations = declarations;
    }

    /**
     *
     * @return
     */
    public HashMap<String, PrimitiveScope> getPrimitives() {
        return primitives;
    }

    /**
     *
     * @param primitives
     */
    public void setPrimitives(HashMap<String, PrimitiveScope> primitives) {
        this.primitives = primitives;
    }

    /**
     *
     * @param name
     * @return
     */
    public DataType lookUpVariable(String name){
        DataType dec = this.declarations.get(name);
        return (dec!=null) ? dec : this.parent.lookUpAttribute(name);
    }

    /**
     *
     * @param name
     * @return
     */
    public Method lookUpMethod(String name){
        return this.parent.lookUpMethod(name);
    }
    
    public void addVariable(String name){
        declarations.put(name, DataType.NUMERO);
    }
}
