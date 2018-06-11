import java.util.Vector;


class Node {
    final public String token;
    final public int type;
    final public Node parent;
    final public Vector<Node> children;

    public Node(String token,int type, Node parent){
        this.children = new Vector<Node>();
        this.parent=parent;
        this.token = token;
        this.type = type;
    }
}

class PrintTree{
    static void print(Node node, int tab ){
        System.out.println( new String(new char[tab]).replace("\0", " ") + node.token);
        for (Node n :node.children){
            print(n,tab+1);
        }
    }
    
    
    static void print(Node node){
        print(node, 0);
    }
}