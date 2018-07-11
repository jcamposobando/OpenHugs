//pakage main.java.code_generation;

public class TagManager {
    private int tnum;
    private final String tname = "tag";
    
    public TagManager(){
        tnum = 0;
    }
    
    public String getTag(){
        return tname + tnum++;
    }
    
    public static void main(String [] args){
        TagManager t = new TagManager();
        System.out.println(t.getTag());
        System.out.println(t.getTag());
        System.out.println(t.getTag());
        System.out.println(t.getTag());
        System.out.println(t.getTag());
    }
}
