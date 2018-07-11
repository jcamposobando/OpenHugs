package main.java.semantic_analysis;

import main.java.syntax_analysis.statement.ExpressionStatement;

public class Variable implements Evaluable {
    private final Block parentBlock;
    private final ExpressionStatement parentExpression;
    private DataType type;
    private final String value;
    
    public Variable (Block parentBlock, ExpressionStatement parentExpression, String value){
        this(parentBlock,parentExpression,value,null);
    }
    
    public Variable (Block parentBlock, ExpressionStatement parentExpression, String value, DataType type){
        this.parentBlock = parentBlock;
        this.parentExpression = parentExpression;
        this.value = value;
        this.type = type;
    }
    
    public DataType getType() {
        if ( type != null ) return type;
        else {
            type = parentBlock.lookUpVariable(value);
            return type;
        }
    }
    
    public boolean checkType(DataType type){
        return this.getType() == type;
    }

    /**
     *
     * @return
     */
    public String getValue() {
        return value;
    }
}