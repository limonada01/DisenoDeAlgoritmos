package ArbolTrie;

public class main {
    public static void main(String[] args) {
        String[] words={"naranja","limon","limonada","narnia"};
        String[] synonymNaranja={"mandarina","mandaruna","mandala"};
        String[] synonymLimon={"amarillo","yellow"};
        String[] synonymLimonada={"jugo","uwu","awitadeuwu"};
        TRIETree tree=new TRIETree();
       
        tree.insertWord(words[0],synonymNaranja);
        tree.insertWord(words[1],synonymLimon);
        tree.insertWord(words[2],synonymLimonada);

        System.out.println(tree.search("limon"));
        System.out.println(tree.search("narnia"));
        System.out.println(tree.search("limoneto"));
        System.out.println(tree.search("naranja"));
        System.out.println(tree.search("limonada"));

        String[] sinonimo={"juguito"};
        tree.insertWord(words[2],sinonimo);
        System.out.println(tree.search("limonada"));

        tree.printWords();
    }
}
