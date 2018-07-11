package main.java.SemanticClasses;

import main.java.*;

public interface Evaluable {
    public DataType getType() ;
    
    public boolean checkType(DataType type);
}