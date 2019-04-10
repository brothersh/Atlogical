package data_structure.Sort;

/**
 * @Author: BrotherSh
 * @Date: 2019/4/2 18:43
 * @Version: 1.0
 */
public class QuickSort{
    public static<T extends Comparable<T>> void QuickSort(T[] list,int left,int right){
        if (left < right){
            int mid = Partition(list,left,right);
            QuickSort(list,left,mid-1);
            QuickSort(list,mid+1,right);
        }
    }

    public static<T extends Comparable<T>> int Partition(T[] list,int left,int right){
        int i = left,j = right + 1;
        T x = list[left];
        while (true){
            while (list[++i].compareTo(x) == -1  && i < right);
            while (list[--j].compareTo(x) == 1);
            if (i >= j) break;
            Swap(list,i,j);
        }
        Swap(list,left,j);
        return j;
    }

    public static <T extends Comparable<T>> void Swap(T[] list,int i,int j){
        T tmp = list[i];
        list[i] = list[j];
        list[j] = tmp;
    }
}
