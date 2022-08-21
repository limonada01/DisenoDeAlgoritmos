package Conjuntos;
import java.util.Set;

public class set {


    public static void buildSet(int[] universe,int[] set){
        int n=set.length;
        for(int i=0;i<n;i++){
            set[i]=universe[i];
        }
    }

    public static int search(int[] set,int numElem){
        return set[numElem];
    }

    public static void merge(int tagSet1,int tagSet2,int[] set){
        int min = Math.min(tagSet1, tagSet2);
        int max = Math.max(tagSet1, tagSet2);
        int lengthSet=set.length;
        for(int i=0;i<lengthSet;i++){
            if(set[i]==max){
                set[i]=min;
            }
        }
    }

    public static void printSet(int[] set){
        int n=set.length;
        for(int i=0;i<n;i++){
            System.out.println("Elemento "+i+": Conjunto: "+set[i]);
        }
        System.out.println("\n");
    }

    public static void main(String[] args) throws Exception {
        int[] universoDeElementos={0,1,2,3,4,5,6,7,8,9};
        
        int[] set= new int[universoDeElementos.length];

        //cada posicion del arreglo set contiene la etiqueta del set al que pertenece el elemento i
        buildSet(universoDeElementos, set);
        //System.out.println("buscar elemento 5: "+search(set, 5));
        
        printSet(set);
        merge(search(set, 1), search(set, 5), set);
        
        merge(search(set, 1), search(set, 9), set);
        printSet(set);

        merge(search(set, 4), search(set, 7), set);
       
        merge(search(set, 4), search(set, 2), set);
        printSet(set);
    }
}
