package com.myles.util.aov;

public class Opportunity {
    private int mId;
    private String mTitle;
    
    public void setId(int id){
        this.mId = id;
    }

    public void setTitle(String title){
        this.mTitle = title;
    }
    
    public int getId(){
        return this.mId;
    }

    public String getTitle(){
        return this.mTitle;
    }
    
    public String toString(){
        return "**Opportunity (" + this.mId + ", " + this.mTitle + ")";
    }

}