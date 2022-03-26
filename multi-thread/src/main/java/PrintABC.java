public class PrintABC {
    private static final int PRINT_COUNT = 100;
    public static void main(String[] args) {
        ThreadLocal<Integer> count = new ThreadLocal<>();

        creatThread('A', count).start();
        creatThread('B', count).start();
        creatThread('C', count).start();

    }

    private static Thread creatThread(char c, ThreadLocal<Integer> count){
        return new Thread(()->{
            count.set(PRINT_COUNT);
            System.out.println("线程"+Thread.currentThread()+"于 "+System.currentTimeMillis()+"开始打印"+c);
            while(count.get()>0){
                System.out.println(c);
                count.set(count.get()-1);
            }
        });
    }
}
