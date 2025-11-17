package org.example;


import org.example.application.SplitService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== PDF Splitter CLI ===");
        System.out.print("Entrez le chemin du fichier PDF : ");
        String inputFile = sc.nextLine();

        System.out.print("Entrez les plages de pages à extraire (ex: 1-3,5,8-9) : ");
        String ranges = sc.nextLine();

        new SplitService().split(inputFile, ranges);
    }
}
