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
}
