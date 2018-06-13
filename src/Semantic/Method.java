package Semantic;

import java.util.SortedMap;


class Method {
    String name;
    String returnType;
    SortedMap<String, String> params;
    
    public Method(){
        
    }

    public Method (String n, String retType){
        this.name = n;
        this.returnType = retType;
    }
    
    public setName(String n){
        this.name = n;
    }
    
    public setRetType(String ret){
        this.returnType = ret;
    }
    
    public void addParam(String type, String name){
        this.params.add(type, name);
    }
}