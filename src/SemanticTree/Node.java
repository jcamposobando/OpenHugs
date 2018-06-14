
public class Node {
    
    private String data;
    private Node parent;
    private Node h_right;
    private Node h_left;
    
    public Node(String d){
        this.data = d;
        this.h_left = null;
        this.h_right = null;
    }
    
    public String getData(){
        return this.data;
    }
    
    public void setData(String d){
        this.data = d;
    }
    
    public void setRightNode(String hr){
        this.h_right = new Node(hr);
    }
    
    public Node getRightNode(){
        return this.h_right;
    }
    
    public void setLeftNode(String hl){
        this.h_left = new Node(hl);
    }
    
    public Node getLeftNode(){
        return this.h_left;
    }
}