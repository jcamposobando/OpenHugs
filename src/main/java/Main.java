package main.java;

import main.java.lexical_analysis.Scanner;
import main.java.syntax_analysis.Parser;

public class Main {
    public static void main(String[] arg) {
        Scanner scanner = new Scanner(arg[0]);
        Parser parser = new Parser(scanner);
        parser.Parse();
        if(!parser.program.typeCheck()) System.err.println(" Error al chequear tipos");
    }   
} 

