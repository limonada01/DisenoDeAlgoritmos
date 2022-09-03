package BinomialHeap;

import java.util.ArrayList;
import java.util.List;

public class BinomialHeap<T extends Comparable<T>> {
    private Node<T> head;

    public BinomialHeap() {
        head = null;
    }

    public BinomialHeap(Node<T> head) {
        this.head = head;
    }
    //retorna true si el heap esta vacio
    public boolean isEmpty() {
        return head == null;
    }
    //limpia completamente el heap
    public void clear() {
        head = null;
    }
    //Crea un nuevo nodo con la clave recibida por parametro y luego crea un Nuevo arbol heap.
    //Finalmente une el arbol creado con el original
    public void insert(T key) {
        Node<T> node = new Node<T>(key);
        BinomialHeap<T> tempHeap = new BinomialHeap<T>(node);
        head = union(tempHeap);
    }

    //recorre los nodos de nivel 0 buscando el mayor, retorna la clave de dicho nodo
    public T findMaximum() {
        if (head == null) {
            return null;
        } else {
            Node<T> max = head;
            Node<T> next = max.sibling;

            while (next != null) {
                if (next.compareTo(max) > 0) {
                    max = next;
                }
                next = next.sibling;
            }

            return max.key;
        }
    }
    //recorre el heap en busca del nodo al que le corresponda la clave pasada por parametro
    //utiliza una lista en donde agrega a los hijos y hermanos del nodo en el que me encuentro
    //obtengo el siguiente nodo de la lista para continuar la busqueda
    //inicialmente se ubica a la raiz en la lista vacia
    //Implemented to test delete/decrease key, runs in O(n) time
    public Node<T> search(T key) {
        List<Node<T>> nodes = new ArrayList<Node<T>>();
        nodes.add(head);
        while (!nodes.isEmpty()) {
            Node<T> curr = nodes.get(0);
            nodes.remove(0);
            if (curr.key == key) {
                return curr;
            }
            if (curr.sibling != null) {
                nodes.add(curr.sibling);
            }
            if (curr.child != null) {
                nodes.add(curr.child);
            }
        }
        return null;
    }
    //Cambia la key a un nodo en particular y llama a la funcion bubbleUp 
    //para que el nodo con la nueva key suba hasta donde le corresponda
    public void increaseKey(Node<T> node, T newKey) {
        node.key = newKey;
        bubbleUp(node, false);
    }
    //situa el nodo a eliminar en la raiz del heap a traves de la funcion buubleUp
    //Luego si el nodo retornado por bubbleUp resulta ser la cabeza de todo el heap entonces se elimina el nodo
    //su primer hermano toma el lugar de Head del Heap
    //Si el nodo retornado por bubble up no es la cabeza del heap,
    //busca el hermano previo a el y llama removerTreeRoot para enlazar el previo con el primer hermano del node
    public void delete(Node<T> node) {
        node = bubbleUp(node, true);
        if (head == node) {
            removeTreeRoot(node, null);
        } else {
            Node<T> prev = head;
            while (prev.sibling.compareTo(node) != 0) {
                prev = prev.sibling;
            }
            removeTreeRoot(node, prev);
        }
    }

    //Permite hacer que un nodo suba en el heap mientras el nodo sea mayor a su padre
    //tambien puede ser que se requiera subir un nodo hasta la raiz
    private Node<T> bubbleUp(Node<T> node, boolean toRoot) {
        Node<T> parent = node.parent;
        while (parent != null && (toRoot || node.compareTo(parent) > 0)) {
            T temp = node.key;
            node.key = parent.key;
            parent.key = temp;
            node = parent;
            parent = parent.parent;
        }
        return node;
    }
    //extrae el nodo con la clave mas alta del arbol
    public T extractMax() {
        if (head == null) {
            return null;
        }
        Node<T> max = head;
        Node<T> maxPrev = null;
        Node<T> next = max.sibling;
        Node<T> nextPrev = max;
        //busca entre los nodos del nivel 0 cual es el mas alto
        while (next != null) {
            if (next.compareTo(max) > 0) {
                max = next;
                maxPrev = nextPrev;
            }
            nextPrev = next;
            next = next.sibling;
        }
        //envia el nodo mas alto con el segundo mas alto. El segundo mas alto tomara su lugar
        removeTreeRoot(max, maxPrev);
        return max.key;
    }
    //Elimina el nodo root, quedando en su lugar el nodo prev
    private void removeTreeRoot(Node<T> root, Node<T> prev) {
        // Remove root from the heap
        if (root == head) {
            head = root.sibling;
        } else {
            prev.sibling = root.sibling;
        }
        //invierte el orden de los nodos hijos del nodo eliminado y crea un nuevo arbol con ellos
        // ej antes: 5 --> 4 --> 3    ahora: 3 --> 4 ---> 5 donde 5 es el newHead del nuevo arbol
        Node<T> newHead = null;
        Node<T> child = root.child;
        while (child != null) {
            Node<T> next = child.sibling;
            child.sibling = newHead;
            child.parent = null;
            newHead = child;
            child = next;
        }
        BinomialHeap<T> newHeap = new BinomialHeap<T>(newHead);

        // Une este nuevo heap con el resto del arbol original 
        head = union(newHeap);
    }

    // enlaza 2 nodos, uno como hijo del otro. (maxNodeTree como padre de other)
    private void linkTree(Node<T> maxNodeTree, Node<T> other) {
        other.parent = maxNodeTree;
        other.sibling = maxNodeTree.child;
        maxNodeTree.child = other;
        maxNodeTree.degree++;
    }
    //Une el arbol recibido por parametro con el actual. Primero hace un merge entre ellos y luego los acomoda
    //aquellos que tinen el mismo orden los enlaza como uno hijo del otro, respetado que el padre de un nodo siempre es mayor el sus hijos
    public Node<T> union(BinomialHeap<T> heap) {
        Node<T> newHead = merge(this, heap);

        head = null;
        heap.head = null;

        if (newHead == null) {
            return null;
        }

        Node<T> prev = null;
        Node<T> curr = newHead;
        Node<T> next = newHead.sibling;

        while (next != null) {
            if (curr.degree != next.degree || (next.sibling != null &&
                    next.sibling.degree == curr.degree)) {
                prev = curr;
                curr = next;
            } else {
                if (curr.compareTo(next) > 0) {
                    curr.sibling = next.sibling;
                    linkTree(curr, next);
                } else {
                    if (prev == null) {
                        newHead = next;
                    } else {
                        prev.sibling = next;
                    }

                    linkTree(next, curr);
                    curr = next;
                }
            }

            next = curr.sibling;
        }

        return newHead;
    }
    //fusiona 2 arboles heap acomodanlos de menor a mayor (de izq a der) segun el grado (degree) de cada nodo en el nivel 0
    private static <T extends Comparable<T>> Node<T> merge(
            BinomialHeap<T> heap1, BinomialHeap<T> heap2) {
        if (heap1.head == null) {
            return heap2.head;
        } else if (heap2.head == null) {
            return heap1.head;
        } else {
            Node<T> head;
            Node<T> heap1Next = heap1.head;
            Node<T> heap2Next = heap2.head;
            //setea como nueva cabeza del monticulo a la cabeza de menor grado entre los 2 monticulos recibidos
            if (heap1.head.degree <= heap2.head.degree) {
                head = heap1.head;
                heap1Next = heap1Next.sibling;
            } else {
                head = heap2.head;
                heap2Next = heap2Next.sibling;
            }

            Node<T> tail = head;
            //se van enlazando los hermanos comenzando por la cabeza, de menor grado a mayor. quedando completamente a la derecha el nodo
            //de nivel 0 con mayor grado
            while (heap1Next != null && heap2Next != null) {
                if (heap1Next.degree <= heap2Next.degree) {
                    tail.sibling = heap1Next;
                    heap1Next = heap1Next.sibling;
                } else {
                    tail.sibling = heap2Next;
                    heap2Next = heap2Next.sibling;
                }

                tail = tail.sibling;
            }
            //seteo a la derecha del todo lo que sobra, debido a que uno de los dos monticulos ya no tienen hermanos en el nivel 0
            if (heap1Next != null) {
                tail.sibling = heap1Next;
            } else {
                tail.sibling = heap2Next;
            }

            return head;
        }
    }

    public void print() {
        System.out.println("Binomial heap:");
        if (head != null) {
            head.print3(0);
        }
    }
}
