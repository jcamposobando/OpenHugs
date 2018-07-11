package main.java.semantic_analysis;

public interface Evaluable {
    public DataType getType() ;
    
    public boolean checkType(DataType type);
}