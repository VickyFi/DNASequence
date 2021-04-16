/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.promesip.dnasequence;

/**
 *
 * @author vicky
 */
public class CheckDNA {
    public String dnaSeq = "";
    public String valid = "ACTG";
    public String newSeq = "";
    public String mRNA = "";
    boolean flag = false;
    public String StartCodon = "AUG";
    public String StopCodon1 = "UAG";
    public String StopCodon2 = "UGA";
    public String StopCodon3 = "UAA";
    public String StopCodon;
     
    public CheckDNA(String dnaSeq){ // initializing constructor
        this.dnaSeq = dnaSeq;
        
        for (char c : dnaSeq.toCharArray()) {    // checking validity of DNA characters
            if (valid.indexOf(c)!=-1 ){
                flag = true;
            }else{
                flag = false;
                break;
            }
        }

    }
     
    public boolean ValidDNA(){ // the boolean method to return whether the user's input is a valid DNA sequence or not          
        return flag == true;     
    }
     
    public String ComplementDNA(){
        // Create and return the complement of the given DNA sequence
        char temp;

        for (char c : dnaSeq.toCharArray()){
            switch (c) {
                case 'A':
                    temp = 'T';
                    break;
                case 'T':
                    temp = 'A';
                    break;
                case 'C':
                    temp = 'G';
                    break;
                default:
                    temp = 'C';
                    break;
            }

            newSeq = newSeq + temp;
        }

        return newSeq;
    }
    
    public String mRNA() {
        // Create and return the mRNA of the given DNA sequence
        char temp;

        for (char c : dnaSeq.toCharArray()){
           switch (c) {
               case 'A':
                   temp = 'U';
                   break;
               case 'T':
                   temp = 'A';
                   break;
               case 'C':
                   temp = 'G';
                   break;
               default:
                   temp = 'C';
                   break;
           }

           mRNA = mRNA + temp;
        }

        return mRNA;
    }
    
    
    public boolean ValidmRNA() {
        // Create and return whether the mRNA of the given DNA sequence is valid or not
        String CheckmRNA = mRNA;
        boolean check = false;
        
        if(CheckmRNA.contains(StartCodon)){
            CheckmRNA = CheckmRNA.substring(CheckmRNA.indexOf(StartCodon) + 3, CheckmRNA.length());
            if(CheckmRNA.contains(StopCodon1) || CheckmRNA.contains(StopCodon2) || CheckmRNA.contains(StopCodon3)){
              check = CheckmRNA.length() % 3 == 0; //codons should be triplets
            }
        }
        
        return check;
    }
    
    public String[] Codons() {
        // Create and return an Array with the Codons of the Protein of the valid mRNA of the given DNA sequence
        
        if (mRNA.contains(StopCodon1)){
            StopCodon = StopCodon1;
        }else if (mRNA.contains(StopCodon2)){
            StopCodon = StopCodon2;
        }else{
            StopCodon = StopCodon3;
        }
        
        String ProteinChain = mRNA.substring(mRNA.indexOf(StartCodon) + 3, mRNA.indexOf(StopCodon));
        int ArrayLength = ProteinChain.length()/3;
        String[] C = new String[ArrayLength];
        int counter = 0;
        
        for (int i = 0; i < ProteinChain.length(); i+=3) {
            C[counter] = ProteinChain.substring(i, i+3);          
            counter++;
        }
      
        return C;
    } 

}
