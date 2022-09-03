package BinomialHeap;

public class Node<T extends Comparable<T>>
        implements Comparable<Node<T>> {
    public T key;
    public int degree;
    public Node<T> parent;
    public Node<T> child;
    public Node<T> sibling;

    public Node() {
        key = null;
    }

    public Node(T key) {
        this.key = key;
    }

    public int compareTo(Node<T> other) {
        return this.key.compareTo(other.key);
    }
    public Node getParent(){
        return parent;
    }
    public T getKey() {
        return key;
    }

    public void setKey(T key) {
        this.key = key;
    }

    public int getDegree() {
        return degree;
    }

    public void setDegree(int degree) {
        this.degree = degree;
    }

    public Node<T> getChild() {
        return child;
    }
  public void setParent(Node<T> parent) {
        this.parent = parent;
    }

    public void setChild(Node<T> child) {
        this.child = child;
    }

    public Node<T> getSibling() {
        return sibling;
    }

    public void setSibling(Node<T> sibling) {
        this.sibling = sibling;
    }

    
    public void print(int level) {
        Node<T> curr = this;
        while (curr != null) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < level; i++) {
                sb.append(" ");
            }
            sb.append(curr.key.toString());
            System.out.println(sb.toString());
            if (curr.child != null) {
                curr.child.print(level + 1);
            }
            curr = curr.sibling;
        }
    }

    public void print2(int level) {
        String father, sib;
        Node curr = this;
        while(curr != null){
          father="";
          sib="";
          StringBuilder sb = new StringBuilder();
          for (int i = 0; i < level; i++) {
            sb.append("");
          }
          if(curr.getParent()!=null){
            father = curr.getParent().key.toString();
          }
          if(curr.getSibling()!=null){
            sib = curr.getSibling().key.toString();
          }
          sb.append((curr.key.toString()+"(f:"+father+")(s:"+sib+")"));
          System.out.println(sb.toString());
          if(curr.child != null){
            curr.child.print(level+1);
          }
          curr = curr.getSibling();
        }
      }

      public void print3(int level) {
        Node<T> curr = this;
        Node<T> padre=curr.getParent();
        Node<T> hermano=curr.getSibling();
        while (curr != null) {
            System.out.print("Actual: "+curr.getKey()+" ");
            System.out.print("DEGREE: "+curr.getDegree());
            if(padre != null ){
                System.out.println(" Padre: "+padre.getKey());
            }
            if(hermano != null ){
                System.out.println(" Hermano: "+hermano.getKey());
            }
            System.out.println("");
            if (curr.child != null) {
                curr.child.print3(level + 1);
            }
            curr = curr.sibling;
        }
    }
}


