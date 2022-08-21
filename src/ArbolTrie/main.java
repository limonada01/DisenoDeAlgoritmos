package ArbolTrie;

public class main {
    public static void main(String[] args) {
        String[] words={"naranja","limon","limonada","narnia"};

        TRIETree tree=new TRIETree();
        for(int i=0;i<words.length;i++){
            tree.insertWord(words[i]);
        }
        
        System.out.println(tree.search("limon"));
        System.out.println(tree.search("narnia"));
        System.out.println(tree.search("limoneto"));
        System.out.println(tree.search("naranja"));
        System.out.println(tree.search("limonada"));
    }
}
