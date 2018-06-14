public class ExpressionStatement implements Statement{
    
    Tree exp;
    String type; //IF, CICLO, FUNCTION EXPRESION
    
    public ExpressionStatement(){
        this.type = "expression";
        exp = new Tree();
    }
    
    public void addExpression(String type1, String var1, String type2, String var2, String op){ //var es cualquier cosa, action es el operador
        this.exp.setRoot("operador", op);
        this.exp.setLeft(type1, var1);
        this.exp.setRight(type2, var2);
    }
    
    public void setVar1(String type, String name){
        this.exp.setLeft(type, name);
    }
    
    public void setVar1Type(String t){
        this.exp.root.h_left.setDataType(t);
    }
    
    public void setVar1Name(String n){
        this.exp.root.h_right.setDataName(n);
    }

    public void setVar2(String type, String name){
        this.exp.setRight(type, name);
    }
    
    public void setVar2Type(String t){
        this.exp.root.h_left.setDataType(t);
    }
    
    public void setVar2Name(String n){
        this.exp.root.h_right.setDataName(n);
    }
    
    public void setOperator(String operador){
        this.exp.setRoot("operador", operador);
    }
    
    public Boolean compare(){
        return (this.exp.getRightNode().getDataType() == this.exp.getLeftNode().getDataType())
    }
    
    /*
    public String getTop(){
        this.st.pop();
    }
    
    public String viewTop(){
        this.st.peek();
    }
    */
}