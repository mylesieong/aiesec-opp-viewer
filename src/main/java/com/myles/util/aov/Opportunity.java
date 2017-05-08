package com.myles.util.aov;

public class Opportunity {
    
    public final static int DETAIL_STYLE = 0;
    public final static int BRIEF_STYLE = 1;
    
    public int _id;
    public String _title;
    public String _company;
    public int _duration;
    public String _country;
    
    public String toString(int style){
        
        String output = "";
        
        if (style == BRIEF_STYLE) {
            output = this._id + " " 
            + convertToFixLength(this._title,  35) + " " 
            + convertToFixLength(this._company, 30) + " " 
            + convertToFixLength(String.valueOf(this._duration), 4) + " " 
            + convertToFixLength(this._country, 18);
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
