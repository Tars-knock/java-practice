import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FutureTaskTest {

    public static void main(String[] args) {
        FutureTask<Integer> task1 = new FutureTask<>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return task1();
            }
        });
        FutureTask<Integer> task2 = new FutureTask<>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return task1();
            }
        });

        long start = System.currentTimeMillis();
        new Thread(task1).start();
        new Thread(task2).start();
        try {
            System.out.println(task1.get() + task2.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("耗时"+(end-start));
    }

    private static int task1(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new Random().nextInt(10);
    }
}
