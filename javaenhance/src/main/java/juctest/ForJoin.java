package juctest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * Created by LIZHUANGZHUANG001 on 2019/4/13.
 *
 *
 * ForkJoin的用法是：
 *  创建ForkjoinPool 然后提交task，输出task的执行结果
 *      重点是：task的执行
 *
 *      本例的自定义task继承了RescursiveTask抽象类
 *
 *
 *
 */
public class ForJoin {
    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();

        ForkJoinTask<Long> task = new ForkJoinSumCalculate(0l, 10000000l);

        Long sum = pool.invoke(task);
        System.out.println(sum);
    }
}

class ForkJoinSumCalculate extends RecursiveTask<Long> {

    public static final long serialVersionUID = 43123414l;

    private long start;

    private long end;

    public static final long THURSHOUD = 10000l;

    public ForkJoinSumCalculate(long start, long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        long length = end - start;

        if (length < THURSHOUD) {
            long sum = 0l;

            for (long i = start; i <= end; i++) {
                sum += i;
            }
            return sum;
        } else {
            long middle = (start + end) / 2;
            ForkJoinSumCalculate left = new ForkJoinSumCalculate(start, middle);
            left.fork();

            ForkJoinSumCalculate right = new ForkJoinSumCalculate(middle + 1, end);
            right.fork();
            return left.join() + right.join();
        }
    }

}
    class Box implements Serializable {
        private static int width;
        private transient int height;
        private String name;
        private Thread thread = new Thread() {
            @Override
            public void run() {
                System.out.println("Serializable thread");
            }
        };

        public Box(String name, int width, int height) {
            this.name = name;
            this.width = width;
            this.height = height;
        }

        private void writeObject(ObjectOutputStream out) throws IOException {
            out.defaultWriteObject();//使定制的writeObject()方法可以利用自动序列化中内置的逻辑。
            out.writeInt(height);
            out.writeInt(width);
            //System.out.println("Box--writeObject width="+width+", height="+height);
        }

        private void readObject(ObjectInputStream in) throws IOException,ClassNotFoundException{
            in.defaultReadObject();//defaultReadObject()补充自动序列化
            height = in.readInt();
            width = in.readInt();
            //System.out.println("Box---readObject width="+width+", height="+height);
        }

        @Override
        public String toString() {
            return "["+name+": ("+width+", "+height+") ]";
        }
    }
