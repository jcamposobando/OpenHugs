package main.java;

import main.coco.*;

import main.java.SemanticScope.*;

public class Main {
    public static void main(String[] arg) {
        Scanner scanner = new Scanner(arg[0]);
        Parser parser = new Parser(scanner);
        parser.Parse();
        if(!parser.program.typeCheck()) System.err.println(" Error al chequear tipos");
    }   
} 

