/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.promesip.dnasequence;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author vicky
 */

public class Main {
    public static void main (String[] args) throws IOException {
       
        String strand="";
        String dir = System.getProperty("user.dir"); // getting current directory path
        int i = 0;
        FileWriter DNAReport = new FileWriter("dna_results.txt", true);
        
        try {//reading file from current directory
            try (Scanner scanner = new Scanner(new File(dir+"/dna.txt"))) {
                while (scanner.hasNextLine()) {
                    strand = scanner.nextLine();
                    System.out.println(scanner.nextLine());
                }
            }
        } catch (FileNotFoundException e) {
                e.printStackTrace();
        } 

        CheckDNA dna = new CheckDNA(strand); //Creating object dna
        boolean ValidDNA = dna.ValidDNA();
        
        
        
        System.out.println("The DNA Sequence from dna.txt file: "+ strand);
        
        if(ValidDNA == true){ // check if file input is a valid DNA sequence
            System.out.println("Is a valid DNA sequence. \n");
            
            String ComplementDNA;
            String mRNA;
            
            ComplementDNA = dna.ComplementDNA(); // compute complement DNA
            System.out.println("The complimentary DNA sequence of your input is: "+ ComplementDNA +"\n");
            
            mRNA = dna.mRNA(); //compute mRNA
            System.out.println("The mRNA sequence of your input is: "+ mRNA);
            
            try { //write reported data to file
                DNAReport.write("The DNA sequence: "+ strand +" is valid. \n\n");
                DNAReport.write("The complimentary DNA sequence of "+ strand +" is: "+ ComplementDNA +"\n\n");
                DNAReport.write("The mRNA sequence is: "+ mRNA +"\n\n");
                
            } catch (IOException e) {
                System.out.println("An error occurred.");
            }
            
            boolean ValidmRNA = dna.ValidmRNA();
            if(ValidmRNA == true){ // check if mRNA contains protein
                System.out.println("Is a valid mRNA sequence. \n\n");
                try {
                    DNAReport.write("The mRNA sequence: "+ mRNA +" forms a protein. \n\n");
                } catch (IOException e) {
                    System.out.println("An error occurred.");
                }
                String Codons[] = dna.Codons();
                for (String Codon : Codons) { // print and write each codon of the mRNA's protein sequence
                    System.out.println("The protein codon["+ i +"] is: " + Codon);
                    DNAReport.write("The protein codon["+ i +"] is: " + Codon +"\n");
                    i++;
                } 
                DNAReport.write("\n\n");
                DNAReport.close();
            }else{
                System.out.println("This is not a valid mRNA sequence. It does not form a protein.\n\n");
                try {
                    DNAReport.write("The mRNA sequence does not form a protein.\n\n");
                    DNAReport.close();
                } catch (IOException e) {
                    System.out.println("An error occurred.\n");
                }
            }
             
        }else{
            System.out.println("Is not a valid DNA sequence.\n");
            System.out.println("A DNA sequence must contain 'ACTG' characters only.\n");
            System.out.println("DNA sequences with lowercase are considered invalid.\n");
            System.out.println("Please, revise your input file accordingly.\n");
            try {
                DNAReport = new FileWriter("dna_results.txt");
                DNAReport.write("Your input: "+ strand +" is not valid.\n\n");
                DNAReport.close();
                System.out.println("Successfully wrote to the file.");
            } catch (IOException e) {
                System.out.println("An error occurred.");
            }
        }   
       
    } 

}
