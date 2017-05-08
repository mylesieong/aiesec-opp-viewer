package com.myles.util.aov;

public class App {    
    public static void main( String[] args )    {

        AOV aov = new AOV();
    
        if (args.length == 0){
            
            aov.help();                            //aov redirect to help
            
        }else if (args.length == 1){
            
            if (args[0].compareTo("-help") == 0){
                aov.help();                       //aov -help
            }
            
            if (args[0].compareTo("-gep") == 0){
                aov.gep();                       //aov -gep
            }

        }else if (args.length == 2){
            
            if (args[0].compareTo("-country") == 0 ){
                aov.searchByCountry(args[1]);   //aov -country us
            }
            
            if (args[0].compareTo("-keyword") == 0){
                aov.searchByKeyword(args[1]);   //aov -keyword Developer
            }

            if (args[0].compareTo("-show") == 0){
                aov.showOpportunity(args[1]);     //aov -show xxxxxx
            }
            
        }else{
            System.out.println("Args format wrong.");
        }
        
    }
}
