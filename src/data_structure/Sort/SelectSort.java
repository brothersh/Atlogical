package data_structure.Sort;

/**
 * @Author: BrotherSh
 * @Date: 2019/4/11 10:46
 * @Version: 1.0
 */
public class SelectSort {
    public static<T extends Comparable<T>> void selectSort(T[] list){
        if (list.length <= 1)
            return;
        for (int i = 0;i < list.length;i++){
            int index = i;
            T min = list[i];
            for (int j = i+1;j < list.length;j++){
                if (min.compareTo(list[j]) != -1){
                    index = j;
                    min = list[j];
                }
            }
            if (index != i){
                T tmp = list[i];
                list[i] = list[index];
                list[index] = tmp;
            }
        }
    }

    public static void main(String[] args) {
        Integer[] a = {15,561,562,8,46,1,87,6,1,6,4,32,154,321,321,3};
        selectSort(a);
        for (int v: a){
            System.out.println(v);
        }
    }
}
