package org.example;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter off set character: ");
        char offSetChar = scanner.next().charAt(0);

        EncoderAndDecoder encoder = new EncoderAndDecoder(offSetChar);



        encoder.printTable();



        System.out.println(encoder.encode("HELLO WORLD"));
        System.out.println(encoder.decode("FC/GGJ RJMG."));

    }

}

