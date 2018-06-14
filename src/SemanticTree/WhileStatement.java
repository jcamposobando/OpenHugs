package SemanticTree;

import java.util.HashMap;

import src.DataType;

public class WhileStatement implements Statement{

    private HashMap<String, DataType> contenido;
    private String operador;
    private DataType tipoExp = null;

    public WhileStatement(){
    }

    public void setOperador(String operador){ //Se puede meter todo tipo de operadore pero solo asigna cuando encuentre el indicado
        if (operador.equals("<=") || operador.equals(">=")
                || operador.equals("<")  || operador.equals(">")
                || operador.equals("==") || operador.equals("!=") )

        {
            this.operador = operador;
        }
    }

    public String getOperador(){return operador;}

    public void setVariables(String variable, DataType tipo){/*Mete la variable que está siendo utilizada en la condicion
                                                             Si fuera un número o algún valor lógico, se inserta este como
                                                            string y se le asocia el DataType según sea necesario*/
        contenido.put(variable,tipo);
        if(null == tipoExp){
            tipoExp = tipo; //Según la primera variable u operador se decide el tipo de la condición
        }
    }

    public HashMap<String,DataType> getHash(){
        return contenido;
    }

    public boolean allSet(){
        boolean resultado = false;
        switch (tipoExp){
            case NUMERO:
                if(     contenido.containsValue(DataType.NUMERO) &&
                        !contenido.containsValue(DataType.LOGICO) &&
                        !contenido.containsValue(DataType.PALABRA)){//Si es un número todos los operadores son válidos

                    resultado = true;
                }
                break;

            case LOGICO:
                if(     contenido.containsValue(DataType.LOGICO) &&
                        !contenido.containsValue(DataType.NUMERO) &&
                        !contenido.containsValue(DataType.PALABRA) &&
                        (operador.equals("!=") || operador.equals("=="))) {//Si es Boleano entonces permite igual o diferente

                    resultado = true;
                }
                break;

            case PALABRA:
                if(         contenido.containsValue(DataType.PALABRA)
                        && !contenido.containsValue(DataType.NUMERO)
                        && !contenido.containsValue(DataType.LOGICO)
                        && (operador.equals("==") || operador.equals("!="))){//Si es por palabra solo acepta que se comparen strings)
                    resultado = true;
                }
                break;
        }

        return  resultado;

    }
}