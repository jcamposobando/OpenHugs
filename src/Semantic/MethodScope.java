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

    /**
     *
     */
    public MethodScope(ClassScope parent){
        this.parent = parent;
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
    public DataType lockUpVariable(String name){
        DataType dec = this.declarations.get(name);
        return (dec!=null) ? dec : this.parent.lockUpAttribute(name);
    }

    /**
     *
     * @param name
     * @return
     */
    public Scope lockUpMethod(String name){
        Scope met = this.primitives.get(name);
        return (met!=null) ? met : this.parent.lockUpMethod(name);
    }
}
