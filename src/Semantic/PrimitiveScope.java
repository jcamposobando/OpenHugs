package Semantic;

import java.util.HashMap;

public class PrimitiveScope implements Scope{

    /**
     *
     */
    private Primitive primitive;

    /**
     *
     */
    private HashMap<String,DataType> variables;

    /**
     *
     */
    private MethodScope parent;

    /**
     *
     * @param parent
     */
    public PrimitiveScope(Primitive primitive, MethodScope parent) {
        this.primitive = primitive;
        this.parent = parent;
        this.variables = new HashMap<>();
    }

    /**
     *
     * @return
     */
    public Primitive getPrimitive() {
        return primitive;
    }

    /**
     *
     * @param primitive
     */
    public void setPrimitive(Primitive primitive) {
        this.primitive = primitive;
    }

    /**
     *
     * @return
     */
    public HashMap<String, DataType> getVariables() {
        return variables;
    }

    /**
     *
     * @param variables
     */
    public void setVariables(HashMap<String, DataType> variables) {
        this.variables = variables;
    }

    /**
     *
     * @return
     */
    public MethodScope getParent() {
        return parent;
    }

    /**
     *
     * @param parent
     */
    public void setParent(MethodScope parent) {
        this.parent = parent;
    }

    /**
     *
     * @param name
     * @return
     */
    public DataType lookUpVariable(String name) {
        DataType var = this.variables.get(name);
        return (var!=null) ? var : this.parent.lookUpVariable(name);
    }

    /**
     *
     * @param name
     * @return
     */
    public Method lookUpMethod(String name) {
        return null;
    }
}
