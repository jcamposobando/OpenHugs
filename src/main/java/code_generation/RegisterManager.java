package main.java.code_generation;

import java.util.HashMap;
import java.util.Map;

public class RegisterManager {

    private String[] registers;
    private Map<String,String> variables;
    private int currentRegister;

    /**
     * Default constructor
     * @param numberOfRegisters
     */
    public RegisterManager(int numberOfRegisters){

        this.registers = new String[numberOfRegisters+1];

        for (int i=1; i<numberOfRegisters; ++i){
            this.registers[i] = "$s"+i;
        }
        this.currentRegister = -1;
        this.variables = new HashMap<>();
    }

    /**
     * Allows to get an available register for use
     * @return the register assigned
     */
    public String newRegister(String variableName){
        String register = this.registers[(++currentRegister)%this.registers.length+1];
        this.variables.put(variableName,register);
        return register;
    }

    /**
     * Allows to get the corresponding register for each variable
     * @param variableName the name assigned to the register
     * @return the register assigned
     */
    public String getRegister(String variableName){
        return this.variables.get(variableName);
    }

}
