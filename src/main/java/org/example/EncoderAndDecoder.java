package org.example;

import java.util.*;

public class EncoderAndDecoder {


    private ArrayList<Character> shiftedReferenceTableArray;
    private ArrayList<Character> initialReferenceTableArray;
    private char offSetChar;
    private int offSetNum;
    private Tables tables;



    public EncoderAndDecoder(char offSetChar){


        this.tables = new Tables();
        this.initialReferenceTableArray = new ArrayList<>(tables.createTable());
        this.offSetChar = offSetChar;
        this.offSetNum = initialReferenceTableArray.indexOf(offSetChar);
        this.shiftedReferenceTableArray = tables.shiftReferenceTable(offSetNum);



    }

    public void setShiftedReferenceTableArray(ArrayList<Character> shiftedReferenceTableArray) {
        this.shiftedReferenceTableArray = shiftedReferenceTableArray;
    }



    public void printTable(){
        System.out.println("Initial Table : "+ initialReferenceTableArray);
        System.out.println("Shifted Table : "+ shiftedReferenceTableArray);


    }

    public String encode (String plainText){


        StringBuilder newText = new StringBuilder("" + offSetChar);

        char[] plainTextArray = plainText.toCharArray();

        for(char character:plainTextArray){
            int currIndex = initialReferenceTableArray.indexOf(character);
            if (currIndex<0 || currIndex > initialReferenceTableArray.size()){
                newText.append(character);
            }
            else{
                char newChar = shiftedReferenceTableArray.get(currIndex);
                newText.append(newChar);
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

        setShiftedReferenceTableArray(tables.shiftReferenceTable(currOffSetNum));



//      Add the chars from unshifted table to new text
        StringBuilder newText = new StringBuilder();

        char[] plainTextArray = encodedText.toCharArray();


        for(char character:plainTextArray){
            int currIndex = shiftedReferenceTableArray.indexOf(character);
            if (currIndex<0 || currIndex > initialReferenceTableArray.size()){
                newText.append(character);
            }
            else{
                char newChar = initialReferenceTableArray.get(currIndex);
                newText.append(newChar);
            }

        }



        return "Decoded Text with '" + offSetChar +"' as the offset character : " + newText;
    }



}
