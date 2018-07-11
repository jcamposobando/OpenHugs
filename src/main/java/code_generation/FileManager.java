package main.java.code_generation;

import java.io.File;
import java.io.PrintWriter;

public class FileManager {

    private PrintWriter out;

    private String PATH;

    /**
     * Default constructor
     */
    public FileManager( ){
        this.PATH = System.getProperty("user.home") + "/Documentos/OpenHugs/code/MIPScode.asm";
    }

    /**
     * Allows create new  output file
     * @return true if creation was successful
     */
    public boolean createFile(){
        try {
            out = new PrintWriter(PATH, "UTF-8");
            return true;
        } catch (Exception e){
            System.out.println("Imposible crear el archivo de salida");
            return false;
        }

    }

    /**
     * Allows write in the current file
     * @param content the content to be written
     */
    public void writeFile(String content){
        try {
            out.println(content);
        } catch (Exception e) {
            System.out.println("Imposible escribir en el archivo");
        }
    }

    /**
     *
     */
    public void abortFile(){
        this.out.close();
        new File(PATH).delete();
    }

    /**
     *
     */
    public void closeFile(){
        this.out.close();
    }
}