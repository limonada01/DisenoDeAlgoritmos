package ArbolTrie;

public class TRIETree {
    private Node root;

    public TRIETree(){
        this.root=new Node();
    }

    public void insertWord(String word,String[] synonym){
        int lenghtWord = word.length();
        int index;
        Node aux=root;
        for(int i=0;i<lenghtWord;i++){// i representa el nivel en el que me encuentro del arbol, sea 0 el nivel de la raiz
            index=word.charAt(i)-'a';//obtengo la posicion correspondiente a la letra de la palabra en la que me encuentro
            if(aux.getChildrens()[index]==null){
                aux.getChildrens()[index]=new Node();
            }
            aux=aux.getChildrens()[index];
        }
        aux.setWord(word,synonym);//indico que es el final de una nueva palabra/clave
    }

    

    public boolean search(String word){
        boolean result=false;
        int lenghtWord = word.length();
        int index;
        Node aux=root;
        int i=0;
        while(i<lenghtWord){// i representa el nivel en el que me encuentro del arbol
            index=word.charAt(i)-'a';//obtengo la posicion correspondiente a la letra de la palabra en la que me encuentro
            if(aux.getChildrens()[index]!=null){
                aux=aux.getChildrens()[index];
                if(i==lenghtWord-1){
                    result=aux.isEndWord();
                    if(result){
                        aux.printSynonym();
                    }
                }
            }else{
                i=lenghtWord;//salgo del bucle
            }
            i++;
        }
        return result;
    }

    public void printWords(){
        this.root.printWords();
    }
}
