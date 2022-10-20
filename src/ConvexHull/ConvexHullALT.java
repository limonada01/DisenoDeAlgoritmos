package ConvexHull;

import java.lang.reflect.Array;
import java.util.ArrayList;

class Punto {
    int x, y;

    Punto(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "(" + this.x + "," + this.y + ")";
    }

    @Override
    public boolean equals(Object obj) {
        // TODO Auto-generated method stub
        Punto otro = (Punto) obj;
        return otro.y == this.y && otro.y == this.y;
    }
}

class ParPuntos {
    Punto X;
    Punto Y;

    ParPuntos(Punto x, Punto y) {
        this.X = x;
        this.Y = y;
    }

    @Override
    public String toString() {
        return "[" + this.X + "-" + this.Y + "]";
    }

    @Override
    public boolean equals(Object obj) {
        ParPuntos otro = (ParPuntos) obj;
        return otro.X == this.X && otro.Y == this.Y;
    }
}

public class ConvexHullALT {

    // para calcular distancia de un punto a la recta y el lado de un punto con
    // respecto a la recta
    private static float ecuacionGeneralRecta(Punto p1, Punto p2, int x, int y) {
        return (p1.y - p2.y) * x + (p2.x - p1.x) * y + (p1.x - p2.x) * p1.y + (p2.y - p1.y) * p1.x;
    }

    public static float distanciaPuntoRecta(Punto p1, Punto p2, Punto c) {
        return (float) (Math.abs(ecuacionGeneralRecta(p1, p2, c.x, c.y))
                / Math.sqrt(Math.pow((p1.y - p2.y), 2) + Math.pow((p2.x - p1.x), 2)));
    }

    public static ArrayList<Punto> puntosCorrespondientes(ArrayList<Punto> puntos, Punto A, Punto B, Punto ZZ) {
        ArrayList sub = new ArrayList<Punto>();
        Punto aux;
        for (int i = 0; i < puntos.size(); i++) {
            aux=puntos.get(i);
            if (aux!=A && aux!=B && aux!=ZZ && ecuacionGeneralRecta(A, B, aux.x, aux.y) >= 0) {
                sub.add(puntos.get(i));
            }
        }
        return sub;
    }

    // los puntos vienen ordenados de menor a mayor en cuanto a su coordenada x
    public static ArrayList convexHull(ArrayList<Punto> puntos) {
        //ArrayList solucion = new ArrayList<ParPuntos>();
        // los puntos mas alejados en x son el primero A y el ultimo B del arreglo
        Punto A = puntos.get(0);
        Punto B = puntos.get(puntos.size() - 1);
        //S1 y S2 contienen los puntos de cada lado de la recta AB
        ArrayList S1 = new ArrayList<Punto>();
        ArrayList S2 = new ArrayList<Punto>();
        
        Punto aux;
        for (int i = 0; i < puntos.size(); i++) {
            aux=puntos.get(i);
            if(aux!=A && aux!=B){
                if (ecuacionGeneralRecta(A, B, aux.x, aux.y) >= 0) {
                    S1.add(puntos.get(i));
                } else {
                    S2.add(puntos.get(i));
                }
            }
            
        }

        ArrayList<ParPuntos> Sol1=findHull(S1, A, B);
        ArrayList<ParPuntos> Sol2=findHull(S2, B, A);
        Sol1.addAll(Sol2);
        return Sol1;
    }

   

    public static ArrayList<ParPuntos> findHull(ArrayList<Punto> puntos, Punto A, Punto B) {
        //mientras aun existan puntos por sobre la recta
        if (puntos.isEmpty()){
            ArrayList Sol= new ArrayList<ParPuntos>();
            Sol.add(new ParPuntos(A, B));
            return Sol;
        
        }else {
            //busco el punto c mas lejano perpendicularmente a la recta AB
            Punto c = puntos.get(0);
            for (int i = 0; i < puntos.size(); i++) {
                if (distanciaPuntoRecta(A, B, puntos.get(i)) > distanciaPuntoRecta(A, B, c)) {
                    c = puntos.get(i);
                }
            }
            
            //calculo los conjuntos de puntos que hay por sobre las rectas Ac (S1) y cB (S2) 
            ArrayList S1 = puntosCorrespondientes(puntos, A, c, B);
            ArrayList S2 = puntosCorrespondientes(puntos, c, B, A);

            ArrayList<ParPuntos> Sol1=findHull(S1, A, c);
            ArrayList<ParPuntos> Sol2=findHull(S2, c, B);
            Sol1.addAll(Sol2);
            return Sol1;
        }
        
        
    }

    public static void main(String[] args) {

        Punto p1 = new Punto(1, 3);
        Punto p2 = new Punto(2, 4);
        Punto p3 = new Punto(3, 2);
        Punto p4 = new Punto(3, 9);
        Punto p5 = new Punto(4, 7);
        Punto p6 = new Punto(6, 1);
        Punto p7 = new Punto(7, 3);
        Punto p8 = new Punto(8, 4);
        Punto p9 = new Punto(8, 8);
        Punto p10 = new Punto(10, 6);
        ArrayList puntos = new ArrayList<Punto>();
        puntos.add(p1);
        puntos.add(p2);
        puntos.add(p3);
        puntos.add(p4);
        puntos.add(p5);
        puntos.add(p6);
        puntos.add(p7);
        puntos.add(p8);
        puntos.add(p9);
        puntos.add(p10);
        //System.out.println(ecuacionGeneralRecta(p1, p10, 3, 9));

        System.out.println(convexHull(puntos).toString());

        // System.out.println(puntos);
        // puntos.remove(new Punto(1, 3));
        // System.out.println(puntos);

    }
}
