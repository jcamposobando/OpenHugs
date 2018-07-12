package main.java.syntax_analysis.statement;

import main.java.semantic_analysis.*;
import java.util.Arrays;

import java.util.Arrays;

public class ExpressionStatement implements Evaluable {
    
    String[] mathOp = {"+","-","*","/"};
    
    String[] compOp = {"<",">","<=","=>","==","!="};
    
    String[] logOp = {"Y","O"};
    
    private final Block parent;
    
    private DataType type;
    
    private Evaluable var1;
    
    private Evaluable var2;
    
    private String operator;
    
    private final ExpressionStatement parentExpression;

    public ExpressionStatement (Block parent, ExpressionStatement parentExpression ){
        this.parent = parent;
        this.parentExpression = parentExpression;
        operator = null;
    }
    
    public DataType getType() {
        if(operator==null){
            return var1.getType();
        } else if (Arrays.asList(mathOp).contains(operator) ) {
            if ( var1.checkType(DataType.NUMERO) && var2.checkType(DataType.NUMERO) )
                return DataType.NUMERO;
        } else if (Arrays.asList(compOp).contains(operator) ){
            if ( var1.checkType(DataType.NUMERO) && var2.checkType(DataType.NUMERO) )
                return DataType.LOGICO;
        } else if (Arrays.asList(logOp).contains(operator) ) {
            if ( var1.checkType(DataType.LOGICO) && var2.checkType(DataType.LOGICO) )
                return DataType.LOGICO;
        }
        return DataType.NONE;
    }
    
    public void  setOperator ( String operator){
        this.operator = operator;
    }
    
    public ExpressionStatement getSubExpression() {
        var2 =new ExpressionStatement(parent,this);
        return (ExpressionStatement) var2;
    }
    
    public FunctionStatement addFunctionCall (String name){
        var1 = new FunctionStatement(name,parent);
        return (FunctionStatement) var1;
    }
    
    public ExpressionStatement getParent(){
        return parentExpression;
    }
    
    public void addValue (String value){
        var1 = new Value(parent,this,value);
    }
    
    public void addVariable (String value){
        var1 = new Variable (parent, this, value);
    }
    
    public MethodScope lookUpMethod(String name){
        return parent.lookUpMethod(name);
    }
    
    public void addExpression (){
        var1 = new ExpressionStatement (parent, this);
    }
    
    public boolean checkType(DataType type){
        return type == getType();
    }

    /**
     *
     * @return
     */
    public Evaluable getVar1() {
        return var1;
    }

    /**
     *
     * @return
     */
    public Evaluable getVar2() {
        return var2;
    }

    /**
     *
     * @return
     */
    public String getOperator() {
        return operator;
    }
}