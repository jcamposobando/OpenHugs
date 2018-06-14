package  SemanticTree;

import SemanticScope.DataType;

import java.util.Collection;
import java.util.HashMap; //Diccionario :v

public class IfStatement implements Statement {

    private HashMap<String, DataType> contenido;
    private String operador;
    private DataType tipoExp = null;

    public IfStatement() {
      //  this.type = "SI";

    }
    public void setOperador(String operador){this.operador = operador;}

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
                        !contenido.containsValue(DataType.PALABRA)){

                    resultado = true;
                }
            break;

            case LOGICO:
                if(     contenido.containsValue(DataType.LOGICO) &&
                        !contenido.containsValue(DataType.NUMERO) &&
                        !contenido.containsValue(DataType.PALABRA)){

                    resultado = true;
                }
            break;

            case PALABRA:
                if(         contenido.containsValue(DataType.PALABRA)
                        && !contenido.containsValue(DataType.NUMERO)
                        && !contenido.containsValue(DataType.LOGICO)){
                    resultado = true;
                }
            break;
        }

        return  resultado;

    }




}