package ListadoPalabras;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;

public class words {
    
    static final String filePath="src\\ListadoPalabras\\texto.txt";

    static Hashtable<String,Integer> hashTable=new Hashtable<>(); 

    /**
     * @throws IOException
     */
    public static void readFile() throws IOException{
        File file = new File(filePath);
        // Creating an object of BufferedReader class
        BufferedReader br = new BufferedReader(new FileReader(file));
 

        String st;
        String[] words=null;
        int lenghtArrayWord;
     
        while ((st = br.readLine()) != null){// guardo en st linea por linea del archivo
            words=st.split(" ");//cada palabra esta separa por un espacio
            //System.out.println(words[10]);
            lenghtArrayWord=words.length;
            for(int i=0;i<lenghtArrayWord;i++){
                if(hashTable.get(words[i])!=null){//asocio a cada palabra su cantidad de ocurrencias en el texto
                    hashTable.put(words[i], hashTable.get(words[i])+1);
                }else{
                    hashTable.put(words[i],1);
                }
            }
            //System.out.println(st);
        }
    }
    


    public static void main(String[] args) throws IOException {
        readFile();
        //hashTable.put("hola",1);
        //hashTable.put("hola", hashTable.get("hola")+1);
        System.out.println(hashTable.get("cantidad"));
    }


}
