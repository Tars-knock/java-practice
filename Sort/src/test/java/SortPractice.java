public class SortPractice {

    public static void main(String[] args) {
        Integer[] target = SortUtil.getArray(20, true);
        long start = System.nanoTime();

        bubble(target);

        long end = System.nanoTime();
        System.out.println(SortUtil.checkSorted(target, true));
        System.out.println("耗时"+(end-start)/1E6+"ms");
    }


    public static void bubble(Integer[] target){
        int len = target.length;
        for(int i = 0;i<len-1;i++){
            for(int j = 0;j<len-1;j++){
                if(target[j] > target[j+1]){
                    int temp = target[j];
                    target[j] = target[j+1];
                    target[j+1] = temp;
                }
            }
        }
    }

    public static void select(Integer[] target){

    }
}
