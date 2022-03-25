import java.util.Map;

public class MyThread implements Runnable{
    Map<Integer, Integer> p;
    int start;
    int count;
    public MyThread(Map<Integer, Integer> p, int start, int count){
        this.p = p;
        this.start = start;
        this.count = count;
    }
    @Override
    public void run() {
        for(int i = 0;i<count;i++){
//            System.out.println("线程"+Thread.currentThread()+"插入"+(start+i));
            p.put(start+i, start+i);
        }
    }

}
