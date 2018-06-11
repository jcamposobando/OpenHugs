package Semantic;

public interface Scope {

    /**
     *
     * @param name
     * @return
     */
    DataType lookUpVariable(String name);

    /**
     *
     * @param name
     * @return
     */
    Method lookUpMethod(String name);
}
