package org.example;

import java.util.*;

public class EncoderAndDecoder {


    private ArrayList<Character> shiftedReferenceTableArray;
    private ArrayList<Character> initialReferenceTableArray;
    private char offSetChar;
    private int offSetNum;



    public EncoderAndDecoder(char offSetChar){

        this.initialReferenceTableArray = new ArrayList<>(createTable());
        this.offSetChar = offSetChar;
        this.offSetNum = initialReferenceTableArray.indexOf(offSetChar);
        this.shiftedReferenceTableArray = shiftReferenceTable(offSetNum);





    }

    public void setShiftedReferenceTableArray(ArrayList<Character> shiftedReferenceTableArray) {
        this.shiftedReferenceTableArray = shiftedReferenceTableArray;
    }

    private ArrayList<Character> createTable(){

        ArrayList<Character> newTable = new ArrayList<>();

        //        Create our own reference table using ASCII values

        //  Add index 0 (A) to index 25 (Z) based on their ASCII values to the reference table
        for(int i=65;i<= 90;i++){
            newTable.add((char)i);
        }

        //  Add index 26 (0) to index 35 (9) based on their ASCII values to the reference table
        for(int i=48;i<= 57;i++){
            newTable.add((char)i);
        }

        //  Add index 36 (*) to index 43 (/) based on their ASCII values to the reference table
        for(int i=42;i<= 47;i++){
            newTable.add((char)i);
        }

        return newTable;
    }

    private ArrayList<Character> shiftReferenceTable(int count){

        ArrayList<Character> referenceTable = createTable();
        ArrayDeque<Character> shiftedReferenceTableQueue = new ArrayDeque<>(referenceTable);

//      Rotate the queue to form the shifted table using offset char
        for(int i=0;i<count;i++){
            char popped = shiftedReferenceTableQueue.removeLast();
            shiftedReferenceTableQueue.addFirst(popped);
        }

        return new ArrayList(shiftedReferenceTableQueue);
    }

    public void printTable(){
        System.out.println("Initial Table : "+ initialReferenceTableArray);
        System.out.println("Shifted Table : "+ shiftedReferenceTableArray);


    }

    public String encode (String plainText){


        String newText = "" + offSetChar;

        char[] plainTextArray = plainText.toCharArray();

        for(char character:plainTextArray){
            int currIndex = initialReferenceTableArray.indexOf(character);
            if (currIndex<0 || currIndex > initialReferenceTableArray.size()){
                newText += character;
            }
            else{
                char newChar = shiftedReferenceTableArray.get(currIndex);
                newText += newChar;
            }

        }

        return "Encoded Text with '" + offSetChar +"' as the offset character : " + newText;
    }


    public String decode (String encodedText) {

//     Remove the offset char

        char currOffSetChar = encodedText.charAt(0);

        encodedText = encodedText.substring(1);

        int currOffSetNum = initialReferenceTableArray.indexOf(currOffSetChar);

//      Create the shifted reference table

        setShiftedReferenceTableArray(shiftReferenceTable(currOffSetNum));



//      Add the chars from unshifted table to new text
        String newText = "";

        char[] plainTextArray = encodedText.toCharArray();


        for(char character:plainTextArray){
            int currIndex = shiftedReferenceTableArray.indexOf(character);
            if (currIndex<0 || currIndex > initialReferenceTableArray.size()){
                newText += character;
            }
            else{
                char newChar = initialReferenceTableArray.get(currIndex);
                newText += newChar;
            }

        }



        return "Decoded Text with '" + offSetChar +"' as the offset character : " + newText;
    }



}
