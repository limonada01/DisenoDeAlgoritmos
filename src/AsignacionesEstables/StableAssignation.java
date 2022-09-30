package AsignacionesEstables;

import java.util.Arrays;

public class StableAssignation {

    // N boys and N girls
    private static int N = 3;

    // retorna verdadero si la mujer w prefiere quedarse con su pareja actual
    // retorna falso si prefiere cambiar a su pareja actual por la nueva
    private static boolean womanPrefersCurrentOverNew(int[][][] prefer, int woman, int newMan, int currentMan) {
        boolean result = false;
        boolean stopSearch = false;
        int i = 0;
        // System.out.println("FLAGGG");
        while (!stopSearch && i < N) {
            // si la pareja actual tiene mas preferencia q la nueva
            // retorna true para mantenerla
            if (prefer[0][woman][i] == currentMan) {
                result = true;
                stopSearch = true;
            }
            // si la nueva pareja tiene mas preferencia que la actual
            // retorna false, para cambiarla por la nueva
            if (prefer[0][woman][i] == newMan) {
                stopSearch = true;
            }
            i++;
        }
        return result;
    }

    private static void assignPartners(int[][][] prefer) {

        // almaceno el indice de la pareja hombre para cada mujer
        int[] womanPartner = new int[N];
        // si el hombre i aun no tiene asignado una pareja, el valor de la posicion i
        // será false
        boolean[] manEngaged = new boolean[N];
        // inicialmente ninguna mujer tiene pareja
        Arrays.fill(womanPartner, -1);
        // inicialmente todos los hombre estan sin pareja
        int freeMenCount = N;
        // Mientras exita un hombre sin pareja
        while (freeMenCount > 0) {
            System.out.println("FLAG");
            int m = 0;
            // obtengo la posicion de un hombre soltero
            while (m < N && manEngaged[m] == true ) {
                m++;
            }
            
            int i = 0;
            while (m < N && manEngaged[m] == false && i < N) {
                // obtengo la posicion de la mujer i
                // de la lista de preferencia de hombre m
                int w = prefer[0][m][i];
                //verifico que el hombre no tiene prohibido emparejarse con mujer w
                // o que w no tiene prohibido emparejarse con m
                if (prefer[1][m][w-N]==1 && prefer[1][w][m]==1) {
                    // si la mujer i que selecciona el hombre soltero m
                    // en su lista de preferencias no tiene pareja,
                    // se emparejan
                    if (womanPartner[w - N] == -1) {
                        womanPartner[w - N] = m;
                        manEngaged[m] = true;
                        freeMenCount--;
                    }
                    // si la mujer w YA TIENE PAREJA, busco su pareja actual
                    // y chequeo si la mujer w prefiere a su actual pareja
                    // o a m para formar una nueva pareja
                    else {
                        int currentPartner = womanPartner[w - N];
                        // si la mujer w no prefiere a su actual pareja, la cambia por la nueva
                        if (womanPrefersCurrentOverNew(prefer, w, m, currentPartner) == false ) {
                            womanPartner[w - N] = m;
                            manEngaged[m] = true;
                            manEngaged[currentPartner] = false;
                        }
                    }
                }
                i++;
            }
            //luego de haber revisado todas la opciones de pareja para m,
            //el unico caso en el que m no puede emparejarse con alguien
            //es porque tiene prohibido la opcion de mujer disponible.
            //Luego el ya no tendra pareja, y quedara soltero, pongo su valor de pareja
            //en true para evitar un loop revisando a m. Ademas resto un hombre libre
            if(m<N && manEngaged[m]==false ){
                manEngaged[m]=true;
                freeMenCount--;
            }

        }
        print(womanPartner);
    }

    private static void print(int[] womanPartner) {
        System.out.println("WOMAN   MAN");
        for (int i = 0; i < N; i++) {
            System.out.println((i + N) + "        " + womanPartner[i]);
        }
    }

    public static void main(String[] args) {
        int prefer[][][] = new int[][][] { // [2][2xN][N]

                {
                        // La primer matriz es para preferencia de parejas
                        { 4, 5, 3 },
                        { 5, 4, 3 },
                        { 4, 3, 5 },
                        { 2, 0, 1 },
                        { 1, 2, 0 },
                        { 1, 0, 2 }, },
                {
                        // La segunda es para parejas no permitidas
                        // Si esta en 1 significa que pueden ser pareja
                        // Si esta en 0 no pueden ser pareja
                        // N primeras filas:HOMBRES N segundas filas: Mujeres
                        // Cada columna representa para el caso de los hombres, las mujeres ordenadas
                        // ej: para el hombre 0 (primera fila), primera col= primera mujer (N+1),
                        // segunda col= segunda mujer (N+2)....
                        { 1, 1, 1 },
                        { 1, 1, 1 },
                        { 1, 1, 1 },
                        { 0, 1, 1 },
                        { 1, 1, 1 },
                        { 1, 1, 1 }
                }
        };
        assignPartners(prefer);
    }
}
