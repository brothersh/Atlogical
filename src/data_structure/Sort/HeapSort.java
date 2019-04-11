package data_structure.Sort;

/**
 * @Author: BrotherSh
 * @Date: 2019/4/11 11:05
 * @Version: 1.0
 */
public class HeapSort {
    public static<T extends Comparable<T>> void heapAdjust(T[] list,int i,int len){
        T cur;
        int indexChild;
        for (cur = list[i];2*i+1 < len;i = indexChild){
            indexChild =2*i+1;
            if (indexChild < len-1 && list[indexChild].compareTo(list[indexChild+1]) == -1)
                indexChild++;
            if (cur.compareTo(list[indexChild]) == -1){
                list[i] = list[indexChild];
                list[indexChild] = cur;
            }else {
                break;
            }
        }
    }
    public static <T extends Comparable<T>> void heapSort(T[] list){
        if (list.length <= 1)
            return;
        for (int i = list.length/2 - 1;i >= 0;i--)
            heapAdjust(list,i,list.length);
        for (int i = list.length-1;i > 0;i--){
            T tmp = list[0];
            list[0] = list[i];
            list[i] = tmp;
            heapAdjust(list,0,i);
        }
    }
    public static void main(String[] args) {
        Integer[] a = {15,561,562,8,46,1,87,6,1,6,4,32,154,321,321,3};
        heapSort(a);
        for (int v: a){
            System.out.println(v);
        }
    }
}
