package SemanticTree;

public class Tree {
    
    private Node root;

    public Tree(){
    }
    
    public Tree(String rootData) {
        this.root = new Node(rootData);
    }
    
    public void setRoot(String rootData){
        this.root = new Node(rootData);
    }
    
    public Node getRoot(){
        return this.root;
    }
    
    public void setLeft(String l){
        this.root.setLeftNode(l);
    }
    
    public String getLeft(){
        return this.root.getLeftNode().getData();
    }
    
    public void setRight(String r){
        this.root.setRightNode(r);
    }
    
    public String getRight(){
        return this.root.getLeftNode().getData();
    }
    
    class Node {
    
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
}
