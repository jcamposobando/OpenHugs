package main.java.semantic_analysis;

import java.util.*;
import java.lang.*;

import main.java.*;

import main.java.SemanticScope.*;

public interface Statement{
    
    public DataType lookUpVariable(String name);
    
    public MethodScope lookUpMethod (String name);
    
    public boolean typeCheck();
}