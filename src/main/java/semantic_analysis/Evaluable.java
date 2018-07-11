package main.java.semantic_analysis;

public interface Evaluable {

    DataType getType() ;
    
    boolean checkType(DataType type);

}