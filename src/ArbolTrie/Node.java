package ArbolTrie;

public class Node {
    private static int ALPHABETLENGTH=26;

    private Node[] childrens;//referencia un enlace segun la letra del abecedario a otro nodo
    private boolean endWord;//indica si nos encontramos en el final de una palabra/clave

    public Node(){
        this.childrens=new Node[ALPHABETLENGTH];
        for(int i=0;i<ALPHABETLENGTH;i++){
            childrens[i]=null;
        }
        this.endWord=false;
    }

    public Node[] getChildrens() {
        return childrens;
    }

    public void setChildrens(Node[] childrens) {
        this.childrens = childrens;
    }

    public boolean isEndWord() {
        return endWord;
    }

    public void setEndWord(boolean endWord) {
        this.endWord = endWord;
    }
    
}
