package main.java.code_generation;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class RegisterManager {

    private Map<String,Boolean> registers;

    /**
     * Default constructor
     */
    public RegisterManager(){
        this.registers = new HashMap<>();
        for (int i=1; i<8; ++i){
            this.registers.put("$s"+i,true);
        }
    }

    /**
     * Allows to get an available register for use
     * @return the register assigned
     */
    public String acquireRegister(){
        for(String key : this.registers.keySet()){
            for (Boolean value : this.registers.values()){
                if (value){
                    this.registers.put(key,false);
                    return key;
                }
            }
        }
        return null;
    }

    /**
     * Allows to get the corresponding register for each variable
     * @param register the name assigned to the register
     * @return the register assigned
     */
    public void releaseRegister(String register){
        this.registers.put(register,true);
    }

}
