package ui;

import java.util.Scanner;

public class CLI implements UI {
    private static final CLI instance = new CLI();
    private final Scanner scanner = new Scanner(System.in);

    private CLI() {}

    public static CLI getInstance() {
        return instance;
    }

    @Override
    public void print(String message) {
        System.out.println(message);
    }

    @Override
    public int readInt() {
        return scanner.nextInt();
    }
}
