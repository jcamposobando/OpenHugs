public class Tree {
    
    Node root;
    
    public Tree() {
        this.root = new Node();
    }
    
    public Tree(String type, String name){
        this.root = new Node(type, name);
    }
    
    public void setRoot(String type, String name){
        this.root = new Node(type, name);
    }
    
    public Node getRoot(){
        return this.root;
    }
    
    public void setLeft(String type, String name){
        this.root.setLeftNode(type, name);
    }
    
    public void setRight(String type, String name){
        this.root.setRightNode(type, name);
    }
    
    
    class Node {

        //private String data;
        Pair data;
        Node parent;
        Node h_right;
        Node h_left;
        
        public Node(){
            this.data = new Pair();
        }
        
        public Node(String t, String n){
            this.data = new Pair(t, n);
            this.h_left = null;
            this.h_right = null;
        }
        
        public String getDataType(){
            return this.data.type;
        }
        
        public String getDataName(){
            return this.data.name;
        }
        
        public void setDataType(String t){
            this.data.type = t;
        }
        
        public void setDataName(String n){
            this.data.name = n;
        }
        
        public void setRightNode(String rt, String rn){
            this.h_right = new Node(rt, rn);
        }
        
        public void setLeftNode(String lt, String ln){
            this.h_left = new Node(lt, ln);
        }
        
        public Node getRightNode(){
            return this.h_right;
        }
        
        public Node getLeftNode(){
            return this.h_left;
        }
        
    }
    
    class Pair{
        String type, name;
        
        public Pair(){}
        
        public Pair(String t, String n){
            this.name = n;
            this.type = t;
        }
    }
}
