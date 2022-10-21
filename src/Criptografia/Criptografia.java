package Criptografia;

public class Criptografia {

    public static long expoDyV(long a, long n) {
        if (n == 1) {
            return a;
        } else if (n % 2 == 0) {
            long b = expoDyV(a, n / 2);
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
        // System.out.println("......A: "+a);
        // System.out.println("......B: "+b);
        String AL, AR, BL, BR;
        long X1, X2, X3;

        int n = Math.max(a.length(), b.length());
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

            return  (long) (X1 *(Math.pow(10, n)) + (X2 - X1 - X3) * (Math.pow(10, n / 2)) + X3);
        }

    }

    public static int exponentModDyV(int a, int n, int z) {

        // Casos bases
        if (a == 0)
            return 0;
        if (n == 0)
            return 1;

        // Si n es par
        long r;
        if (n % 2 == 0) {
            r = exponentModDyV(a, n / 2, z);
            r =  (multiplicarDyV(r, r)) % z;
        }

        // Si n es impar
        else {
            r = a % z;
            r = ( multiplicarDyV(r, exponentModDyV(a, n - 1, z)) % z) % z;
        }

        return (int) ((r + z) % z);
    }

    public static void main(String[] args) {

        // System.out.println(expoDyV(3, 4));
        //System.out.println(multiplicarDyV(1980, 2315));
        System.out.println(exponentModDyV(211, 2, 5));
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
