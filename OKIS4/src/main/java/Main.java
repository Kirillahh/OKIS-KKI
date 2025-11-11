    package org.example;

    public class Main {
        public static void main(String[] args) {
            System.out.printf("Hello and welcome!");
        }

        public static int sum(int a, int b) {
            return a + b;
        }

        // 1. Метод для вычитания
        public static int subtract(int a, int b) {
            return a - b;
        }

        // 2. Метод, который может выбросить исключение (деление на ноль)
        public static int divide(int a, int b) {
            if (b == 0) {
                throw new IllegalArgumentException("Divisor cannot be zero");
            }
            return a / b;
        }

        // 3. Метод, который может выбросить другое исключение (выход за границы массива)
        public static int getElementAtIndex(int[] array, int index) {
            if (index < 0 || index >= array.length) {
                throw new ArrayIndexOutOfBoundsException("Index is out of bounds");
            }
            return array[index];
        }

        // 4. Метод для проверки, является ли число положительным
        public static boolean isPositive(int number) {
            return number > 0;
        }
    }
