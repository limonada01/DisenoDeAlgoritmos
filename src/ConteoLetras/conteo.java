package ConteoLetras;

import javax.lang.model.type.ArrayType;

public class conteo {
    
    public static char[] countingSort(char[] array){
        int length=array.length;
        char[] sortArray=new char[length];
        int[] counter=new int[length];
        for(int i=0;i<length;i++){
            counter[i]=0;
        }
        for(int i=0;i<length-1;i++){
            for(int j=i+1;j<length;j++){
                if(Character.compare(Character.toLowerCase(array[i]), Character.toLowerCase(array[j]))<0){
                    counter[j]++;
                }else{
                    counter[i]++;
                }
            }
        }
        for(int i=0;i<length;i++){
            sortArray[counter[i]]=array[i];
        }
        return sortArray;
    }


    public static void main(String[] args) {
        char[] array={'a','B','Z','t','d','F','b','r'};
        //int res;
        //res=Character.compare(Character.toLowerCase('A'), Character.toLowerCase('a'));
        //System.out.println(res);
        System.out.println(array);
        char[] sort=countingSort(array);
        System.out.println(sort);

    }
}
