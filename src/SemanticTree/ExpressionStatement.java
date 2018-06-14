package SemanticTree;

import java.util.Stack;

public class ExpressionStatement implements Statement{
    //expresiones matemáticas
    Stack<String> st;// = new Stack();
    public ExpressionStatement(){
        this.type = "expression";
        st = new Stack<>();
    }
    
    public void addExpression(String var1, String var2, String op){ //var es cualquier cosa, action es el operador
        this.st.pop(var1);
        this.st.pop(var2);
        this.st.pop(op);
    }
}