package converter;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean errorFlag = false;
        String inputS;
        int inputI;
        int sourceRadix = 0;
        int targetRadix = 0;
        String[] sourceNumber = new String[2];

        if ((inputS = scanner.next()).matches("\\d+") && (inputI = Integer.parseInt(inputS)) > 0 && inputI <= 36) {
            sourceRadix = inputI;
            char maxChar = Character.forDigit(sourceRadix - 1, sourceRadix);
            inputS = scanner.next().toLowerCase();
            if (sourceRadix == 1 && inputS.matches("1+") ||
                    sourceRadix <= 10 && inputS.matches("[0-" + maxChar + ".]+") ||
                    sourceRadix > 10 && inputS.matches("[0-9a-" + maxChar + ".]+")) {
                sourceNumber = inputS.split("\\.");
                if ((inputS = scanner.next()).matches("\\d+") && (inputI = Integer.parseInt(inputS)) > 0 && inputI <= 36) {
                    targetRadix = inputI;
                } else {
                    errorFlag = true;
                }

            } else {
                errorFlag = true;
            }

        } else {
            errorFlag = true;
        }


        if (!errorFlag) {
            int integerPart = sourceRadix == 1 ? sourceNumber[0].length() : Integer.parseInt(sourceNumber[0], sourceRadix);
            if (targetRadix == 1) {
                for (int i = 0; i < integerPart; i++) {
                    System.out.print("1");
                }
                System.out.println();
            } else {
                System.out.print(Integer.toString(integerPart, targetRadix));
                if (sourceNumber.length == 2) {
                    char[] fractionalPartChars = sourceNumber[1].toCharArray();
                    double fractionalPart = 0;

                    for (int i = 0; i < fractionalPartChars.length; i++) {
                        fractionalPart += Character.getNumericValue(fractionalPartChars[i]) / Math.pow(sourceRadix, i + 1);
                    }
                    StringBuilder fractionalPartOut = new StringBuilder();
                    for (int i = 0; i < 5; i++) {
                        fractionalPart *= targetRadix;
                        fractionalPartOut.append(Character.forDigit((int) fractionalPart, targetRadix));
                        fractionalPart -= (int) fractionalPart;
                    }
                    System.out.print("." + fractionalPartOut.toString());
                }
                System.out.println();
            }
        } else {
            System.out.println("error");
        }
    }
}
