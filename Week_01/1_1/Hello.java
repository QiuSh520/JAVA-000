package com.example.demo;

public class Hello {
    private int oddCount;

    private double oddSum = 0.0D;

    public double calcOddAvg(int[] arr) {
        for (int i : arr) {
            if (i % 2 == 1) {
                oddCount++;
                oddSum += i;
            }
        }

        if (oddCount == 0) {
            return oddSum;
        }

        return oddSum / oddCount;
    }
}
