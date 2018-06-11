package Semantic;

import java.util.HashMap;

public class ProgramScope {

    /**
     *
     */
    private HashMap<String,ClassScope> classes;

    /**
     *
     */
    public ProgramScope(){
        this.classes = new HashMap<>();
    }

    /**
     *
     * @return
     */
    public HashMap<String, ClassScope> getClasses() {
        return classes;
    }

    /**
     *
     * @param classes
     */
    public void setClasses(HashMap<String, ClassScope> classes) {
        this.classes = classes;
    }
}
