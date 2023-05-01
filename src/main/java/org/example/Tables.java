package org.example;

import java.util.ArrayDeque;
import java.util.ArrayList;

public class Tables {


    public ArrayList<Character> createTable(){

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

    public ArrayList<Character> shiftReferenceTable(int count){

        ArrayList<Character> referenceTable = createTable();
        ArrayDeque<Character> shiftedReferenceTableQueue = new ArrayDeque<>(referenceTable);

//      Rotate the queue to form the shifted table using offset char
        for(int i=0;i<count;i++){
            char popped = shiftedReferenceTableQueue.removeLast();
            shiftedReferenceTableQueue.addFirst(popped);
        }

        return new ArrayList<>(shiftedReferenceTableQueue);
    }
}
