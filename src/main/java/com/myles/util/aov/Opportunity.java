package com.myles.util.aov;

public class Opportunity {
    
    public final static int DETAIL_STYLE = 0;
    public final static int BRIEF_STYLE = 1;
    
    private int mId;
    private String mTitle;
    private String mCompany;
    private int mDuration;
    private String mCountry;
    
    /* Setters */
    public void setId(int id){
        this.mId = id;
    }

    public void setTitle(String title){
        this.mTitle = title;
    }
      
    public void setCompany(String company){
        this.mCompany = company;
    }

    public void setDuration(int duration){
        this.mDuration = duration;
    }
    
    public void setCountry(String country){
        this.mCountry = country;
    }
    
    /* Getters */
    public int getId(){
        return this.mId;
    }

    public String getTitle(){
        return this.mTitle;
    }
    
    public String getCompany(){
        return this.mCompany;
    }
    
    public int getDuration(){
        return this.mDuration;
    }
    
    public String getCountry(){
        return this.mCountry;
    }
    
    public String toString(int style){
        
        String output = "";
        
        if (style == BRIEF_STYLE) {
            output = this.mId + " " 
            + convertToFixLength(this.mTitle,  35) + " " 
            + convertToFixLength(this.mCompany, 30) + " " 
            + convertToFixLength(String.valueOf(this.mDuration), 4) + " " 
            + convertToFixLength(this.mCountry, 18);
        }

        if (style == DETAIL_STYLE){
            output = "NA";
        }
        
        return output;
            
    }
    
    private String convertToFixLength(String source, int len){
        String output = "";
        if (source.length() > len){
            output = source.substring(0,len - 3) + "...";
        }else{
            output = source;
            for (int i = output.length(); i<len ; i++){
                output = output + " ";
            }
        }
        return output;
    }

}
