import java.util.HashMap;

public class HashMapTest {
    private static HashMap<Integer, Integer> victim;
    public static void main(String[] args) {
        victim = new HashMap<>();
        MyThread threadA = new MyThread(victim, 0, 1000);
        MyThread threadB = new MyThread(victim, 1001, 1000);
        MyThread threadC = new MyThread(victim, 2001, 1000);
        Thread a = new Thread(threadA);
        Thread b = new Thread(threadB);
        Thread c = new Thread(threadC);

        a.start();
        b.start();
        c.start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("开始读取");
        int drop = 0;
        for(int i = 0;i<3000;i++){
            System.out.println(i+": "+victim.get(i));
            if(victim.get(i) == null) drop++;
        }
        System.out.println("丢失对象"+drop);
    }
}
