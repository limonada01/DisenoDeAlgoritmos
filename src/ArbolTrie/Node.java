package ArbolTrie;


import java.util.LinkedList;
import java.util.List;

public class Node {
    private static int ALPHABETLENGTH = 26;
    private String word;
    private Node[] childrens;// referencia un enlace segun la letra del abecedario a otro nodo
    private LinkedList<String> synonym;

    public Node() {
        this.childrens = new Node[ALPHABETLENGTH];
        this.word = null;
        for (int i = 0; i < ALPHABETLENGTH; i++) {
            childrens[i] = null;
        }
        this.synonym = null;
    }

    public Node[] getChildrens() {
        return childrens;
    }

    public void setChildrens(Node[] childrens) {
        this.childrens = childrens;
    }

    public void setWord(String word, String[] synonymArray) {
        this.word = word;
        if (this.synonym == null) {
            this.synonym = new LinkedList<String>();
        }
        addSynonym(synonymArray);
    }

    public boolean isEndWord() {
        return this.word != null;// si no es igual a nulo, es un endWord node
    }

    public void addSynonym(String[] synonymArray) {
        int lenght = synonymArray.length;
        for (int i = 0; i < lenght; i++) {
            this.synonym.add(synonymArray[i]);
        }
    }

    public void printSynonym() {
        //int lenght = synonym.size();
        System.out.println("Sinonimos para la palabra: " + this.word);
        System.out.println(synonym.toString());
        System.out.println("\n");
    }

    public void printWords() {
        Node curr = this;
        if (isEndWord()) {
            System.out.println(this.word);
        }
        for (int i = 0; i < ALPHABETLENGTH; i++) {
            if (curr.getChildrens()[i] != null) {
                curr.getChildrens()[i].printWords();
            }
        }

    }

}
