import java.util.ArrayList;
import java.util.List;
// java -Xmx20m -Xms20m oom
class oom{
     static final int len = 100;
     static class tool{

     }
    public static void main(String[] args){
        // Object[] arr = new Object[len];
        List<Object> list = new ArrayList<>();
        for(int i =0;i<10000;){     //死循环
            // arr[i%len] = new tool();
            list.add(new tool());
        }
    }


}