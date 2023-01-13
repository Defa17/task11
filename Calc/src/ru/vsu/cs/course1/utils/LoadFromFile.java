package ru.vsu.cs.course1.utils;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LoadFromFile {
    public static int loadFromFile(String inputFile) throws FileNotFoundException {
        final File file = new File(inputFile);
        int number;
        try (final Scanner scanner = new Scanner(file)){
            number = scanner.nextInt();
        }
        return number;
    }


}
