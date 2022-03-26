import java.net.Socket;
import java.util.HashMap;

public class HashMapDevil {
    private static HashMap<Long, Socket> hashMap = new HashMap<Long,Socket>();

    public static void main(String[] args) {

        for (int i = 0 ; i < 100000 ; i++){
            (new Thread(() -> hashMap.put(System.nanoTime(),new Socket()))).start();
            System.out.println("========创建第"+i+"个线程");
        }
    }
}

