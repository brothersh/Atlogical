package thread;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * @Author: BrotherSh
 * @Date: 2019/4/12 10:21
 * @Version: 1.0
 */
public class Communication {
    public static void main(String[] args) {
        System.out.println(100.0 %2.5);
    }

    public float set(){
        float a = 1;
        return a;
    }

}
class Worker implements Runnable{
    String name;
    void work(Machine machine){
        System.out.print(name + " 正在使用使用 ");
    }

    @Override
    public void run() {

    }
}
class Factory extends Semaphore {
    String name;
    Machine[] machines;
    boolean[] isStock;
    public Factory(int permits,String name) {
        super(permits);
        machines = new Machine[permits];
        isStock = new boolean[permits];
        this.name = name;
    }




}
class Machine {
    int _no;
    void producing(){
        try {
            System.out.println(_no + " 号机器工作.");
            Thread.sleep(5000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
class Child{
    static {
        System.out.println("静态代码块");
    }

    public Child(){
        System.out.println("构造方法");
    }
    {
        System.out.println(this + "动态代码块");
    }
    public static void main(String[] args) {
        new Child();
    }
}
