package Criptografia;

import java.util.Random;

class Key {
    int alfa;// n
    int beta;// e || d

    public Key(int alfa, int beta) {
        this.alfa = alfa;
        this.beta = beta;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "alfa: " + this.alfa + " beta: " + this.beta;
    }
}

public class Criptografia {

    public static char[] abecedario = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
            'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };

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
            r = (multiplicarDyV(r, r)) % z;
        }

        // Si n es impar
        else {
            r = a % z;
            r = (multiplicarDyV(r, exponentModDyV(a, n - 1, z)) % z) % z;
        }

        return (int) ((r + z) % z);
    }

    public static long calcularZ(long p, long q) {
        return multiplicarDyV(p, q);
    }

    public static long calcular_Phi_n(long p, long q) {
        return multiplicarDyV(p - 1, q - 1);
    }

    public static long maximoComunDivisorRecursivo(long a, long b) {
        if (b == 0)
            return a;
        return maximoComunDivisorRecursivo(b, a % b);
    }

    // SIN UTILIZAR
    public static String decimalAHexavigesimal(int decimal) { // base 10 a base 26
        String hexavigesimal = "";
        String caracteresHexavigesimales = "0123456789abcdefghijklmnop";
        while (decimal > 0) {
            int residuo = decimal % 26;
            hexavigesimal = caracteresHexavigesimales.charAt(residuo) + hexavigesimal; // Agregar a la izquierda
            decimal /= 26;
        }
        return hexavigesimal;
    }

    public static Key privateKey(int phi_n, Key publickey) {
        int i = 0;
        float d;
        do {
            i++;
            d = ((float)1 + i * phi_n) / publickey.beta;
            //System.out.println("i: "+i+ " d: "+d);
        } while (d != (int) d);// si d es entero

        return new Key(publickey.alfa, (int)d);
    }

    public static Key publicKey(int p, int q) {
        int e = 0;
        long phi_n = calcular_Phi_n(p, q);
        boolean flag = true;
        int i = 2;
        while (flag && i < phi_n) {
            if (maximoComunDivisorRecursivo(i, phi_n) == 1) {
                e = i;
                flag = false;
            }
            i++;
        }
        return new Key((int)multiplicarDyV(p, q), e);
    }

    public static String encriptarMensaje(String mensaje_in, Key publicKey) {
        String letra_encriptada = "";
        String mensaje_out = "";
        int largoMensaje = mensaje_in.length();
        int aux, n_aux;

        for (int i = 0; i < largoMensaje; i++) {
            aux = exponentModDyV(mensaje_in.charAt(i) - 'a',  publicKey.beta, publicKey.alfa);
            System.out.println("LETRA ENCRIPTADA: "+aux);
            letra_encriptada = Integer.toString(aux);
            n_aux = letra_encriptada.length();
            //System.out.println("LARGO lentra encriptada: " + n_aux);
            for (int j = 0; j < n_aux; j++) {
                //System.out.println("j: " + j);
                //System.out.println("letra encriptada: " + letra_encriptada.charAt(j));
                mensaje_out += abecedario[Character.getNumericValue(letra_encriptada.charAt(j))];
            }
            mensaje_out += '&';
        }
        return mensaje_out;
    }

    public static String desencriptarMensaje(String mensaje_in, Key privateKey) {
        String mensaje_out = "";
        String letra_desencriptada = "";
        int largo_mensajeEncriptado=mensaje_in.length()-1;
        int i=0;
        char aux;
        while(i<=largo_mensajeEncriptado){
            aux=mensaje_in.charAt(i);
            if(aux!='&'){
                letra_desencriptada+=aux-'a';
                //System.out.println("LETRA desencriptada: "+letra_desencriptada);
            }else{
                System.out.println("LETRA desencriptada: "+letra_desencriptada);
                mensaje_out+=exponentModDyV(Integer.parseInt(letra_desencriptada),privateKey.beta, privateKey.alfa);
                System.out.println("Mensaje Temp: "+mensaje_out);
                letra_desencriptada="";
            }
            
            i++;
            
        }
        
        return mensaje_out;
    }

    public static void main(String[] args) {

      
        int p=11;
        int q=3;
        String cadena = "si";
        System.out.println(cadena.charAt(0)-'a');
        //System.out.println(cadena.charAt(1)-'a');
        Key publica = publicKey(p, q);
        System.out.println("clave publica: " + publica.toString());
        String salida = encriptarMensaje(cadena, publica);
        System.out.println("W: " + salida);

        Key privada= privateKey((int)calcular_Phi_n(p, q),publica);
        System.out.println("clave privada: "+privada);
        String mensaje=desencriptarMensaje(salida, privada);
        System.out.println("MENSAJE DESENCRIPTADO: "+mensaje);
         
        
        System.out.println(exponentModDyV(329, 269, 731)); 
    }
}
