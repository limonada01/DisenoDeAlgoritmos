package Criptografia;

public class Criptografia {

    public static int expoDyV(int a, int n) {
        if (n == 1) {
            return a;
        } else if (n % 2 == 0) {
            int b = expoDyV(a, n / 2);
            return multiplicarDyV(b, b);
        } else {
            return multiplicarDyV(a, expoDyV(a, n - 1));
        }
    }

    public static String multiplicacionString(String a, String b) {
        int c = Integer.parseInt(a) * Integer.parseInt(b);
        return Integer.toString(c);
    }

    public static String sumaString(String a, String b) {
        int c = Integer.parseInt(a) + Integer.parseInt(b);
        return Integer.toString(c);
    }

    public static String splitString(String cadena, int inicio, int fin) {
        String salida = cadena.substring(inicio, fin);
        if (salida.length() == 0) {
            return "0";
        } else {
            return salida;
        }
    }

    public static int multiplicarDyV(int a, int b) {
        return karatsuba(Integer.toString(a), Integer.toString(b));
    }

    public static int karatsuba(String a, String b) {
        // System.out.println("......A: "+a);
        // System.out.println("......B: "+b);
        String AL, AR, BL, BR;
        int X1, X2, X3;

        int n = Math.max(a.length(), b.length());
        if (n == 1) {
            return Integer.parseInt(a) * Integer.parseInt(b);
        } else {
            AL = splitString(a, 0, a.length() / 2);
            // System.out.println("AL: "+AL);
            AR = splitString(a, a.length() / 2, a.length());
            // System.out.println("AR: "+AR);
            BL = splitString(b, 0, b.length() / 2);
            // System.out.println("BL: "+BL);
            BR = splitString(b, b.length() / 2, b.length());
            // System.out.println("BR: "+BR);
            X1 = karatsuba(AL, BL);
            X2 = karatsuba(sumaString(AL, AR), sumaString(BL, BR));
            X3 = karatsuba(AR, BR);

            return (int) (X1 * Math.pow(10, n) + (X2 - X1 - X3) * Math.pow(10, n / 2) + X3);
        }

    }

    public static void main(String[] args) {

        // System.out.println(expoDyV(3, 4));
        System.out.println(multiplicarDyV(1980, 2315));
        // String test=Integer.toString(1970);
        // System.out.println(test);
        /*
         * String a="122";
         * String x=a.substring(0, a.length()/2);
         * String y=a.substring(a.length()/2, a.length());
         * System.out.println(x);
         * System.out.println(y);
         * System.out.println(a.length());
         */
        // System.out.println("1".length());
    }
}
