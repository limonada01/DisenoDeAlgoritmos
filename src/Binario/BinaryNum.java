package Binario;

public class BinaryNum {
    int[] array;

    public BinaryNum(int[] array){
        this.array=array;
    }

    public void incrementar(){
        int i=array.length-1;
        while(i>0 && array[i]==1){
            array[i]=0;
            i--;
        }
        if(i>0){
            array[i]=1; 
        }
    }

    public void decrementar(){
        int i=array.length-1;
        while(i>0 && array[i]==0){
            array[i]=1;
            i--;
        }
        if(i>0){
            array[i]=0; 
        }
    }

    public void print(){
        for(int i=0;i<array.length;i++){
            System.out.print(" "+array[i]);
        }
        System.err.println("");
    }

    public static void main(String[] args) {
        int[] inicio={0,0,1,1};
        BinaryNum num=new BinaryNum(inicio);
        num.incrementar();
        num.print();
        num.incrementar();
        num.print();
        num.incrementar();
        num.print();
        num.decrementar();
        num.print();
        num.decrementar();
        num.print();
        num.decrementar();
        num.print();
        //System.err.println(inicio[2]);
    }
    
}
