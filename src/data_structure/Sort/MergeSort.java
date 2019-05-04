package data_structure.Sort;

/**
 * @Author: BrotherSh
 * @Date: 2019/4/11 11:28
 * @Version: 1.0
 */
public class MergeSort {
    public static <T extends Comparable<T>> void mergeSort(T[] list,T[] tmp,int left,int right){
        if (left < right){
            int mid = (left+right)/2;
            mergeSort(list,tmp,left,mid);
            mergeSort(list,tmp,mid+1,right);
            merge(list,tmp,left,mid,right);
        }
    }
    public static <T extends Comparable<T>> void merge(T[] list,T[] tmp,int left,int mid,int right){
        int i = left,j = mid+1;
        int index = 0;
        while (i <= mid && j <= right){
            if (list[i].compareTo(list[j]) < 0){
                tmp[index++] = list[i++];
            }else {
                tmp[index++] = list[j++];
            }
        }

        while (i <= mid)
            tmp[index++] = list[i++];
        while (j <= right)
            tmp[index++] = list[j++];
        for (int offset = 0;offset < index;offset++){
            list[left+offset] = tmp[offset];
        }
    }
    public static void main(String[] args) {
        Integer[] a = {15,561,562,8,46,1,87,6,1,6,4,32,154,321,321,3};
        Integer[] tmp = new Integer[a.length];
        mergeSort(a,tmp,0,a.length-1);
        for (int v: a){
            System.out.println(v);
        }
    }
}
