import org.junit.Test;

public class PrintABCAsce {
    private volatile static int flag;
    private static final Object lock = new Object();
    private static final int PRINT_COUNT = 20;
    public static void main(String[] args) {
        flag = 1;
        creatThread('a', 1, 2).start();
        creatThread('b', 2, 3).start();
        creatThread('c', 3, 1).start();
    }

    private static Thread creatThread(char c, int thisState, int nextState){
        return new Thread(()->{
           synchronized (lock) {
               for (int i = 0; i < PRINT_COUNT; i++) {
                   while (thisState != flag) {
                       try {
                           lock.wait();
                       } catch (InterruptedException e) {
                           e.printStackTrace();
                       }
                   }
                   System.out.println(c);
                   flag = nextState;
                   lock.notifyAll();
               }
           }
        });
    }
}
