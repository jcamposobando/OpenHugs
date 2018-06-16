package main.java.SemanticClasses;

import main.java.*;

public class FunctionStatement implements Statement{
    
    StatementType stType;
    
    public FunctionStatement(){
        this.stType = StatementType.FUNCION;
    }
    
    public StatementType getType(){
        return this.stType;
    }
}