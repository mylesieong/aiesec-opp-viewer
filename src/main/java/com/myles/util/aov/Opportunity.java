package com.myles.util.aov;

import java.util.*;

public class Opportunity {
    
    public final static int DETAIL_STYLE = 0;
    public final static int BRIEF_STYLE = 1;
    
    /* Brief */
    public int _id;
    public String _title;
    public String _company;
    public int _duration;
    public String _country;

    /* Details */
    public int _views;
    public String _applicationCloseDate;
    public String _homeLC;
    public List<Manager> _managers = new ArrayList<Manager>();
    public List<Skill> _skills = new ArrayList<Skill>();
    public List<Background> _backgrounds = new ArrayList<Background>();
    public String _visaLink;
    public String _visaType;
    public String _visaDuration;
    public String _city;
    public String _selectProcess;
    public int _salary;
    public String _salaryCcy;
    public int _salaryCcyCode;
    public String _createTime;
    public String _updateTime;
    
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
            output = "Id:\t" + _id
				+ "\nTitle:\t" + _title
				+ "\nCompany:\t" + _company
				+ "\nDuration:\t" + _duration
				+ "\nCountry:\t" + _country
				+ "\nViews:\t" + _views
				+ "\nApplication Close Date:\t" + _applicationCloseDate
				+ "\nHome LC:\t" + _homeLC
				+ "\nVisa Link:\t" + _visaLink
				+ "\nVisa Type:\t" + _visaType
				+ "\nVisa Duration:\t" + _visaDuration
				+ "\nCity:\t" + _city
				+ "\nSelect Process:\t" + _selectProcess
				+ "\nSalary:\t" + _salary
				+ "\nSalary Currency:\t" + _salaryCcy
				+ "\nSalary Currency Code:\t" + _salaryCcyCode
				+ "\nCreated At:\t" + _createTime
				+ "\nUpdated At:\t" + _updateTime;
		    
			for (int i = 0; i < _managers.size() ; i++ ){
				output = output + "\nManagers no." + i + ":"
					+ "\n\tName:\t" + _managers.get(i)._fullName
					+ "\n\tEmail:\t" + _managers.get(i)._email;
			}
			
			for (int i = 0; i < _skills.size() ; i++ ){
				output = output + "\nSkill no." + i + ":"
					+ "\n\tName:\t" + _skills.get(i)._name
					+ "\n\tOption:\t" + _skills.get(i)._option
					+ "\n\tLevel:\t" + _skills.get(i)._level;
			}
			
			for (int i = 0; i < _backgrounds.size() ; i++ ){
				output = output + "\nBackground no." + i + ":"
					+ "\n\tName:\t" + _backgrounds.get(i)._name
					+ "\n\tOption:\t" + _backgrounds.get(i)._option;
			}

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

    public static class Manager {
        public String _fullName;
        public String _email;
    }

    public static class Skill {
        public String _name;
        public String _option;
        public int _level;
    }

    public static class Background {
        public String _name;
        public String _option;
    }

}
