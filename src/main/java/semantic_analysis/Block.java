package main.java.semantic_analysis;

import java.util.Vector;
import java.util.HashMap;
import main.java.syntax_analysis.statement.*;

public class Block{
    
    HashMap<String,DataType> variables;
    
    Vector<Statement> statements;
    
    private final MethodScope parentMethod;
    
    private final Statement parentBlock;
    
    public Block( MethodScope parentMethod, Statement parentBlock){
        variables = new HashMap<>();
        statements = new Vector<>();
        this.parentMethod = parentMethod;
        this.parentBlock = parentBlock;
    }
    
    public AsigmentStatement addAssigmentStatement(String name){
        AsigmentStatement statement = new AsigmentStatement(this, name);
        statements.add(statement);
        return statement;
    }
    
    public IfStatement addIfStatement(){
        IfStatement ifStatement = new IfStatement(this,parentMethod);
        statements.add(ifStatement);
        return ifStatement;
    }

    public boolean typeCheck () {
        boolean res = true;
        for (Statement st : statements){
            res &= st.typeCheck();
        }
        return res;
    }
    
    public MethodScope lookUpMethod (String name){
        return parentMethod.lookUpMethod(name) ;
    }
    
    public MethodScope getParentMethod  () {
        return parentMethod;
    }
    
    public DataType lookUpVariable(String name){
        DataType dt = variables.get(name);
        if ( dt != null ) return  dt;
        else if ( parentBlock != null ) {
            dt = parentBlock.lookUpVariable(name);
                if (dt != null) return dt;
        }
        return parentMethod.lookUpVariable(name);
    }
    
    public Block(Vector<Statement> s){
        this.statements = s;
        this.parentMethod = null;
        this.parentBlock = null;
    }
    
    public void addStatement(Statement s){
        this.statements.add(s);
    }
    
    public WhileStatement addWhileStatement(){
        WhileStatement statement = new WhileStatement(this,parentMethod);
        statements.add(statement);
        return statement;
    }
    
    public FunctionStatement addFunctionCall(String name){
        FunctionStatement statement = new FunctionStatement(name,this);
        statements.add(statement);
        return statement;
    }
    
    public void addVariable (String typeName, String name){
        variables.put(name, DataType.valueOf(typeName));
    }
    
    public DataType getReturnType (){
        return parentMethod.getReturnType();
    }
    
    public ExpressionStatement addReturnStatement(){
        ReturnStatement returnStatement = new ReturnStatement(this);
        statements.add(returnStatement);
        return returnStatement.getExpression();
    }
}