package ConferenceProblem;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Conference {

    public static boolean verificarSolucion(ArrayList oradores, ArrayList<ArrayList> setRelevantes) {
        boolean respuesta = true;
        boolean flag;
        int i = 0;
        int j = 0;
        int n = setRelevantes.size();
        int m;
        int cantOradores = oradores.size();
        // recorre cada set de setRelevantes
        while (respuesta && i < n) {
            System.out.println("HOLAAAAA");
            ArrayList aux = setRelevantes.get(i);
            m = setRelevantes.get(i).size();
            j = 0;
            // recorre oradores
            flag=true;
            while (flag && j < cantOradores) {
                flag = aux.contains(oradores.get(j)) ? false : true;
                System.out.println(flag);
                j++;
            }
            respuesta = !flag;
            i++;
        }
        System.out.println();
        return respuesta;
    }

    public static void main(String[] args) {
        ArrayList oradores = new ArrayList<Integer>();
        oradores.add(2);
        oradores.add(4);
        ArrayList setRelevantes = new ArrayList<ArrayList>();
        ArrayList set1 = new ArrayList<Integer>();
        set1.add(1);
        set1.add(4);
        set1.add(5);
        ArrayList set2 = new ArrayList<Integer>();
        set2.add(2);
        set2.add(3);
        ArrayList set3 = new ArrayList<Integer>();
        set3.add(4);
        set3.add(7);

        setRelevantes.add(set1);
        setRelevantes.add(set2);
        setRelevantes.add(set3);

       // System.out.println(set1);
       // System.out.println(set1.retainAll(oradores));
        //System.out.println(set1);
        System.out.println(verificarSolucion(oradores, setRelevantes));
    }
}
