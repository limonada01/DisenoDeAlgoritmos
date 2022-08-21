package ArbolBinomial;

public class main {
    public static void main(String[] args) {
        BinomialHeap arbol=new BinomialHeap<>(new Node<Integer>(10));
        arbol.insert(11);
        arbol.insert(1);
        arbol.insert(3);
        arbol.insert(41);
        arbol.insert(22);
        arbol.print();
    }
}
