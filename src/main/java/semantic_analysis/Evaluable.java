package main.java.semantic_analysis;

import main.java.*;

public interface Evaluable {
    public DataType getType() ;
    
    public boolean checkType(DataType type);
}