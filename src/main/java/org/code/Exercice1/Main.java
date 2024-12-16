package org.code.Exercice1;

public class Main {

    public static long factoriel(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Enter a positive number");
        }
        long result = 1;
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        return result;
    }
}
