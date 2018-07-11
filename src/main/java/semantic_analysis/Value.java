package main.java.semantic_analysis;

import main.java.syntax_analysis.statement.ExpressionStatement;

import java.lang.*;


public class Value implements Evaluable {
    private final Block parentBlock;
    private final ExpressionStatement parentExpression;
    private DataType type;
    private final String value;
    
    public Value (Block parentBlock, ExpressionStatement parentExpression, String value){
        this(parentBlock,parentExpression,value,null);
    }
    
    public Value (Block parentBlock, ExpressionStatement parentExpression, String value, DataType type){
        this.parentBlock = parentBlock;
        this.parentExpression = parentExpression;
        this.value = value;
        this.type = type;
    }
    
    public DataType getType() {
        if ( type == null){
            if (value.equals("VERDADERO") || value.equals("FALSO")  ) {
                type = DataType.LOGICO;
                return type;
            }
            
            try{
                Float.parseFloat(value);
                type = DataType.NUMERO;
                return type;
            }
            catch (NumberFormatException e){}
            type = DataType.PALABRA;
            return type;
        }
        else return type;
    };
    
    public boolean checkType(DataType type){
        return this.getType() == type;
    }

    public String getValue() {
        return value;
    }
}