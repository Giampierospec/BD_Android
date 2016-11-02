package com.giamp.bd;

public class Info {
    private int id;
    private String txt;
    public Info(){

    }
    public Info(int id, String txt){
        this.id = id;
        this.txt = txt;

    }
    public Info( String txt){
        this.txt = txt;

    }
    public int getId(){
        return this.id;
    }
    public void setId(int id){
        this.id = id;

    }
    public String getTxt(){
        return this.txt;
    }
    public void setTxt(String txt){
        this.txt= txt;
    }


}
