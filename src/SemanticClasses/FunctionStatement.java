package SemanticClasses;

import src.*;

public class FunctionStatement implements Statement{
    
    StatementType stType;
    
    public FunctionStatement(){
        this.stType = StatementType.FUNCION;
    }
    
    public StatementType getType(){
        return this.stType;
    }
}