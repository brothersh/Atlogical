package data_structure.Sort;

/**
 * @Author: BrotherSh
 * @Date: 2019/4/11 10:13
 * @Version: 1.0
 */
public class InsertSort {
    public static <T extends Comparable<T>> void insertSort(T[] list){
        if (list.length <= 1)
            return;
        for (int i = 1;i < list.length;i++){
            T cur = list[i];
            int j;
            for (j = i-1;j >= 0 && cur.compareTo(list[j])==-1;j--)
                list[j+1] = list[j];
            list[j+1] = cur;
        }
    }
}
