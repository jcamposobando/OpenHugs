package Semantic;

public interface Scope {

    /**
     *
     * @param name
     * @return
     */
    DataType lockUpVariable(String name);

    /**
     *
     * @param name
     * @return
     */
    Scope lockUpMethod(String name);
}
