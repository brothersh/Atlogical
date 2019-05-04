package data_structure.Sort;

/**
 * @Author: BrotherSh
 * @Date: 2019/4/11 10:55
 * @Version: 1.0
 */
public class BubbleSort {
    public static <T extends Comparable<T>> void bubbleSort(T[] list){
        if (list.length <= 1)
            return;
        boolean flag;
        do {
            flag = false;
            for (int i = 0;i < list.length-1;i++){
                if (list[i].compareTo(list[i+1]) > 0){
                    flag = true;
                    T tmp = list[i];
                    list[i] = list[i+1];
                    list[i+1] = tmp;
                }
            }
        }while (flag);
    }
}
