package main.java.syntax_analysis;

public class Errors {
    public int count = 0;                                    // number of errors detected
    public java.io.PrintStream errorStream = System.out;     // error messages go to this stream
    public String errMsgFormat = "-- line {0} col {1}: {2}"; // 0=line, 1=column, 2=text

    protected void printMsg(int line, int column, String msg) {
        StringBuffer b = new StringBuffer(errMsgFormat);
        int pos = b.indexOf("{0}");
        if (pos >= 0) { b.delete(pos, pos+3); b.insert(pos, line); }
        pos = b.indexOf("{1}");
        if (pos >= 0) { b.delete(pos, pos+3); b.insert(pos, column); }
        pos = b.indexOf("{2}");
        if (pos >= 0) b.replace(pos, pos+3, msg);
        errorStream.println(b.toString());
    }

    public void SynErr (int line, int col, int n) {
        String s;
        switch (n) {
            case 0: s = "EOF expected"; break;
            case 1: s = "lineEnd expected"; break;
            case 2: s = "variableName expected"; break;
            case 3: s = "className expected"; break;
            case 4: s = "number expected"; break;
            case 5: s = "stringLit expected"; break;
            case 6: s = "comma expected"; break;
            case 7: s = "dec expected"; break;
            case 8: s = "dot expected"; break;
            case 9: s = "inc expected"; break;
            case 10: s = "lbrace expected"; break;
            case 11: s = "lbrack expected"; break;
            case 12: s = "lpar expected"; break;
            case 13: s = "minus expected"; break;
            case 14: s = "not expected"; break;
            case 15: s = "plus expected"; break;
            case 16: s = "rbrace expected"; break;
            case 17: s = "rbrack expected"; break;
            case 18: s = "rpar expected"; break;
            case 19: s = "tilde expected"; break;
            case 20: s = "asign expected"; break;
            case 21: s = "mul expected"; break;
            case 22: s = "div expected"; break;
            case 23: s = "colon expected"; break;
            case 24: s = "leq expected"; break;
            case 25: s = "geq expected"; break;
            case 26: s = "less expected"; break;
            case 27: s = "great expected"; break;
            case 28: s = "equal expected"; break;
            case 29: s = "notEq expected"; break;
            case 30: s = "and expected"; break;
            case 31: s = "or expected"; break;
            case 32: s = "si expected"; break;
            case 33: s = "ciclo expected"; break;
            case 34: s = "mientras expected"; break;
            case 35: s = "sino expected"; break;
            case 36: s = "clase expected"; break;
            case 37: s = "incorporar expected"; break;
            case 38: s = "funcion expected"; break;
            case 39: s = "numero expected"; break;
            case 40: s = "palabra expected"; break;
            case 41: s = "returns expected"; break;
            case 42: s = "return expected"; break;
            case 43: s = "bool expected"; break;
            case 44: s = "true expected"; break;
            case 45: s = "false expected"; break;
            case 46: s = "??? expected"; break;
            case 47: s = "invalid typeName"; break;
            case 48: s = "invalid tipoRetorno"; break;
            case 49: s = "invalid StatementBlock"; break;
            case 50: s = "invalid expresion"; break;
            case 51: s = "invalid value"; break;
            case 52: s = "invalid boolValue"; break;
            case 53: s = "invalid operator"; break;
            default: s = "error " + n; break;
        }
        printMsg(line, col, s);
        count++;
    }

    public void SemErr (int line, int col, String s) {
        printMsg(line, col, s);
        count++;
    }

    public void SemErr (String s) {
        errorStream.println(s);
        count++;
    }

    public void Warning (int line, int col, String s) {
        printMsg(line, col, s);
    }

    public void Warning (String s) {
        errorStream.println(s);
    }
}
