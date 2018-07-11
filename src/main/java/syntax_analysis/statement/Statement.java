package main.java.syntax_analysis.statement;

import java.lang.*;
import main.java.semantic_analysis.DataType;
import main.java.semantic_analysis.MethodScope;

public interface Statement{
    
    public DataType lookUpVariable(String name);
    
    public MethodScope lookUpMethod (String name);
    
    public boolean typeCheck();
}