package data_structure.Sort;

/**
 * @Author: BrotherSh
 * @Date: 2019/4/11 14:45
 * @Version: 1.0
 */
public class CountSort {
    public static int[] countSort(int[] list){
        if (list.length <= 1)
            return list;
        int max = list[0],min = list[0];
        for (int v: list){
            max = (max > v)?max:v;
            min = (min < v)?min:v;
        }
        int range = max - min+1;
        int[] counter = new int[range];
        int[] res = new int[list.length];
        for (int v: list){
            counter[v-min]++;
        }
        for (int i = 1;i < range;i++){
            counter[i] += counter[i-1];
        }
        for (int v: list){
            res[counter[v-min]-1] = v;
        }
        return res;
    }
    public static void main(String[] args) {
        int[] a = {15,561,562,8,46,1,87,6,1,6,4,32,154,321,321,3};
        for (int v: countSort(a)){
            System.out.println(v);
        }
    }
}
