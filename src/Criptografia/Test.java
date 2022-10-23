package Criptografia;

public class Test {

    public static String sumaString(String a, String b) {
        Long c = Long.parseLong(a) + Long.parseLong(b);
        return Long.toString(c);
    }

    public static String splitString(String cadena, int inicio, int fin) {
        String salida = cadena.substring(inicio, fin);
        if (salida.length() == 0) {
            return "0";
        } else {
            return salida;
        }
    }

    public static long multiplicarDyV(long a, long b) {
        return karatsuba(Long.toString(a), Long.toString(b));
    }

    public static long karatsuba(String a, String b) {

        String AL, AR, BL, BR;
        long X1, X2, X3;

        int n = Math.max(a.length(), b.length());
        //System.out.println("n: "+n);
        int mitad_n= (int) Math.ceil((double)n/2);// redondea hacia arriba la division
        //System.out.println("mitad: "+mitad_n);
        if (n == 1) {
            return Long.parseLong(a) * Long.parseLong(b);
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

            return (long) (X1 * (Math.pow(10, mitad_n*2)) + (X2 - X1 - X3) * (Math.pow(10, mitad_n)) + X3);
        }

    }


    public static void main(String[] args) {
        System.out.println(multiplicarDyV(10000, 10000));
        //System.out.println(mult(100, 100));
    }
}
