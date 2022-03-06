import java.util.Arrays;
import java.util.Random;

public class SortUtil {

    /**
     * 检查target数组是否为升序
     * @param target 待检查的目标数组
     * @param DEBUG debug标记位
     * @param <T> 使用泛型支持所有comparable的类型
     * @return  target数组是否为升序排列
     */
    public static <T extends Comparable<T>> boolean checkSorted(T[] target, boolean DEBUG){
        if(DEBUG){
            System.out.println("传入结果");
            System.out.println(Arrays.toString(target));
        }
        int len = target.length;
        if(len == 0)
            return true;
        for(int i = 1;i<len;i++){
            if(target[i].compareTo(target[i-1]) < 0)
                return false;
        }
        return true;
    }
    public static <T extends Comparable<T>> boolean checkSorted(T[] target) {
        return checkSorted(target, false);
    }

    /**
     * 生成一个随机数组
     * @param len   数组的长度
     * @param DEBUG debug标记位
     * @return  一个随机数组
     */
    public static Integer[] getArray(int len, boolean DEBUG){
        Integer[] res = new Integer[len];
        Random random = new Random();
        for(int i = 0;i<len;i++)
            res[i] = random.nextInt((len/10+2)*10);
        if(DEBUG) {
            System.out.println("生成数组：");
            System.out.println(Arrays.toString(res));
        }
        return res;
    }
    public static Integer[] getArray(int len){
        return getArray(len, false);
    }
}

