package SemanticClasses;

import src.DataType;

public class Tree {
    
    Node root;
    
    public Tree() {
        this.root = new Node();
    }
    
    public Tree(DataType type, String name){
        this.root = new Node(type, name);
    }
    
    public void setRoot(DataType type, String name){
        this.root = new Node(type, name);
    }
    
    public Node getRoot(){
        return this.root;
    }
    
    public void setLeft(DataType type, String name){
        this.root.setLeftNode(type, name);
    }
    
    public void setRight(DataType type, String name){
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
        
        public Node(DataType t, String n){
            this.data = new Pair(t, n);
            this.h_left = null;
            this.h_right = null;
        }
        
        public DataType getDataType(){
            return this.data.type;
        }
        
        public String getDataName(){
            return this.data.name;
        }
        
        public void setDataType(DataType t){
            this.data.type = t;
        }
        
        public void setDataName(String n){
            this.data.name = n;
        }
        
        public void setRightNode(DataType rt, String rn){
            this.h_right = new Node(rt, rn);
        }
        
        public void setLeftNode(DataType lt, String ln){
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
        String name;
        DataType type;
        
        public Pair(){}
        
        public Pair(DataType t, String n){
            this.name = n;
            this.type = t;
        }
    }
}
