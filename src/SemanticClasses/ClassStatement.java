package SemanticClasses;

import src.*;

public class ClassStatement implements Statement{
    
    StatementType stType;
    
    public ClassStatement(){
        this.stType = StatementType.CLASE;
    }
    
    public StatementType getType(){
        return this.stType;
    }
}