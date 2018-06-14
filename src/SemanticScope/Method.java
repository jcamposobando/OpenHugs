package SemanticScope;

import java.util.SortedMap;


class Method {
    String name;
    String returnType;
    SortedMap<String, String> params;
    
    public Method(){
        SortedMap<String, String> params;

    }

    public void Method (String n, String retType){
        SortedMap<String, String> params;
        this.name = n;
        this.returnType = retType;
    }
    
    public void setName(String n){
        this.name = n;
    }
    
    public void setRetType(String ret){
        this.returnType = ret;
    }
    
    public void addParam(String type, String name){
        this.params.put(type, name);
    }
}