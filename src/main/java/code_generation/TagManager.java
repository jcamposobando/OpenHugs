package main.java.code_generation;

public class TagManager {
    //
    private int tnum;
    //
    private final String tname = "tag";

    /**
     *
     */
    public TagManager(){
        tnum = 0;
    }

    /**
     *
     * @return
     */
    public String getTag(){
        return tname + tnum++;
    }
}
