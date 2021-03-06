package main.java.syntax_analysis.statement;

import java.util.Vector;
import main.java.semantic_analysis.Block;
import main.java.semantic_analysis.DataType;
import main.java.semantic_analysis.Evaluable;
import main.java.semantic_analysis.MethodScope;

public class FunctionStatement implements Statement,Evaluable {
    
    public final String functionName;
    
    public final Vector<ExpressionStatement> parameters;
    
    private Block parent;
    
    private DataType returnType;
    
    StatementType stType;
    
    public FunctionStatement(String name,Block parent){
        this.functionName = name;
        this.parameters = new Vector<>();
        this.returnType = null;
        this.parent = parent;
    }
    
    public ExpressionStatement addParameter(){
        ExpressionStatement parameter = new ExpressionStatement(parent,null);
        parameters.add(parameter);
        return parameter;
    }
    
    public DataType getType() {
        return parent.lookUpMethod(functionName).getReturnType();
    }
    
    public boolean checkType(DataType type){
        return type == getType();
    };
    
    public MethodScope lookUpMethod(String name){
        return parent.lookUpMethod(name);
    }
    
    public DataType lookUpVariable(String name){
        return parent.lookUpVariable(name);
    }
    
    public boolean typeCheck () {
        boolean res = true;
        DataType[] a = parent.lookUpMethod(functionName).parametersTypes();
        res &= a.length == parameters.size();
        for(int i = 0;i<a.length;i++) res &= a[i] ==  parameters.get(i).getType();
        return res;
    }
    
}