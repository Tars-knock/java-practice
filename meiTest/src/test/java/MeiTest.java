import org.junit.jupiter.api.Test;
import org.w3c.dom.Node;

import java.util.*;

public class MeiTest {
    //leetcode周赛288

    /**
     * 2231. 按奇偶性交换后的最大数字
     */
    public int largestInteger(int num) {
        String s = String.valueOf(num);
        int len = s.length();
        boolean[] true1 = new boolean[len];
        int[] numc = new int[len];
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            numc[i] = s.charAt(i) - '0';
            if (numc[i] % 2 == 1) {
                true1[i] = true;
                list1.add(numc[i]);
            } else {
                true1[i] = false;
                list2.add(numc[i]);
            }
        }

        list1.sort(Comparator.naturalOrder());
        list2.sort(Comparator.naturalOrder());

        int p1 = 0, p2 = 0;
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < len; i++) {
            if (true1[i]) {
                sb.append(list1.get(p1++));
            } else {
                sb.append(list2.get(p2++));
            }
        }
        return Integer.parseInt(sb.toString());
    }

    /**
     * 2232. 向表达式添加括号后的最小结果
     * 有边界问题
     */
    public String minimizeResult(String expression) {
        int mid = expression.indexOf('+');
        String lefts = expression.substring(0, mid), rights = expression.substring(mid + 1);
        int left = 0, right = rights.length();

        int res = Integer.parseInt(lefts) + Integer.parseInt(rights);
        int len = expression.length();
        for (int i = 1; i < mid; i++) {
            for (int j = 1; j <= rights.length(); j++) {
                int tempRes = getRes(lefts, rights, i, j);
                if (tempRes < res) {
                    left = i;
                    right = j;
                    res = tempRes;
                }
            }

        }
        return lefts.substring(0, left) + "(" + lefts.substring(left) + "+" + rights.substring(0, right) + ")" + rights.substring(right);
    }

    public Integer getRes(String lefts, String rights, int i, int j) {
        int res = Integer.parseInt(lefts.substring(i)) + Integer.parseInt(rights.substring(0, j));
        res *= Integer.parseInt(lefts.substring(0, i));
        if (j < rights.length()) res *= Integer.parseInt(rights.substring(j));
        System.out.println(lefts.substring(0, i) + "(" + lefts.substring(i) + "+" + rights.substring(0, j) + ")" + rights.substring(j) + " " + res);
        return res;
    }

    @Test
    public void test() {
        System.out.println(minimizeResult("5+22"));
    }

    /**
     * 56. 合并区间
     */
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a1, a2) -> (a1[0] - a2[0]));
        List<int[]> res = new ArrayList<>();
        for (int[] temp : intervals) {
            if (res.size() == 0 || res.get(res.size() - 1)[1] < temp[0]) {
                res.add(temp);
            } else {
                int[] p = res.get(res.size() - 1);
                res.remove(res.size() - 1);
                res.add(new int[]{p[0], Math.max(temp[1], p[1])});
            }
        }
        return res.toArray(new int[1][1]);
    }

    public int[][] mergePlus(int[][] intervals) {
        Arrays.sort(intervals, (a1, a2) -> (a1[0] - a2[0]));
        Stack<int[]> res = new Stack<>();
        for (int[] temp : intervals) {
            if (res.isEmpty() || res.peek()[1] < temp[0]) {
                res.add(temp);
            } else {
                int[] p = res.pop();
                res.add(new int[]{p[0], Math.max(temp[1], p[1])});
            }
        }
        return res.toArray(new int[1][1]);
    }

    /**
     * 62. 不同路径
     */
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        Arrays.fill(dp[0], 1);
        for (int[] p : dp) {
            p[0] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }

    @Test
    public void testUniquePaths() {
        uniquePaths(23, 12);
    }

    /**
     * 102. 二叉树的层序遍历
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();
        if (root == null) return res;
        Deque<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            List<Integer> temp = new LinkedList<>();
            for (int i = queue.size(); i > 0; i--) {
                TreeNode tempNode = queue.pop();
                if (tempNode.left != null) queue.add(tempNode.left);
                if (tempNode.right != null) queue.add(tempNode.right);
                temp.add(tempNode.val);
            }
            res.add(new LinkedList<>(temp));
        }
        return res;
    }

    //    Definition for a binary tree node.
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * 45. 跳跃游戏 II
     */
    public int jump(int[] nums) {
        int len = nums.length, max = nums[0];
        int[] dp = new int[len];
        Arrays.fill(dp, len);
        dp[len - 1] = 0;
        for (int i = len - 2; i >= 0; i--) {
            if (len - 1 - i <= nums[i]) {
                dp[i] = 1;
            } else {
                for (int j = 1; j <= nums[i]; j++) {
                    dp[i] = Math.min(dp[i], 1 + dp[i + j]);
                }
            }
        }
        return dp[0];
    }

    @Test
    public void testJump() {
        jump2(new int[]{1, 2});
    }

    public int jump2(int[] nums) {
        int len = nums.length;
        int end = 0;
        int max = 0;
        int res = 0;
        for (int i = 0; i < len - 1; i++) {
            max = Math.max(max, i + nums[i]);
            if (i == end) {
                end = max;
                res++;
            }
        }
        return res;
    }

    public void findc(int[] nums) {
        int cur, count = 0;
        int res, maxCount = 0;
        Arrays.sort(nums);//排序
        cur = nums[0];
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == cur) {
                count++;
            } else {
                if (count > maxCount) {
                    res = cur;
                    maxCount = count;
                }
                cur = nums[i];
            }
        }
    }

    @Test
    public void integerEqualTest() {
        Integer v1 = 199;
        Integer v2 = 199;
        System.out.println("Integer == Integer? " + (v1 == v2));
        Integer a1 = 127;
        Integer a2 = 127;
        System.out.println("127 == 127?  " + (a1 == a2));
    }

    /**
     * 292周赛
     */
    public String largestGoodInteger(String num) {
        int res = -1;
        char[] sc = num.toCharArray();
        for (int i = 2; i < sc.length; i++) {
            if (sc[i] == sc[i - 2] && sc[i] == sc[i - 1]) {
                res = Math.max(res, sc[i] - '0' + 10 * (sc[i - 1] - '0') + 100 * (sc[i - 2] - '0'));
            }
        }
        if (res == -1) return "";
        else if (res == 0) return "000";
        else return String.valueOf(res);

    }

    //暴力
    int count = 0;
    int sum = 0;
    int res = 0;

    public int averageOfSubtree(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode temp = stack.pop();
            if (temp.left != null) stack.push(temp.left);
            if (temp.right != null) stack.push(temp.right);
            if (check(temp)) res++;
        }
        return res;
    }

    public boolean check(TreeNode root) {
        count = 0;
        sum = 0;
        dfs(root);
        return sum / count == root.val;
    }

    public void dfs(TreeNode root) {
        if (root != null) {
            count++;
            sum += root.val;
            dfs(root.left);
            dfs(root.right);
        }
    }

    public long countTexts(String pressedKeys) {
//    public int countTexts(String pressedKeys) {
        final int NORMAL3 = 4;  //连击三次79之外的按键 可能表达的意思
        final int OTHER4 = 7; //连击四次79 可能表达的意思
        int[] poss = new int[] {0,1,2,4,7};
        char[] sc = pressedKeys.toCharArray();
        long res = 1;
        for (int i = 0; i < pressedKeys.length();) {
            int temp = findLength(sc, i);
            System.out.println(temp);
            i+=temp;
            res *= poss[temp];

        }
//        return (int) (res%(10E7+9));
        return res;

    }
    public int findLength(char[] sc, int start){
        char target = sc[start];
        int res = 1;
        for (int i = start+1; i < sc.length; i++) {
            if (sc[i] == target) {
                res ++;
            }else{
                return res;
            }
            if(res == 3 && target != '7' && target != '9') return res;
            else if((target == '7' || target == '9') && res == 4) return res;
        }
        return res;
    }

    @Test
    public void testCountTexts(){
        System.out.println(countTexts("222222222222222222222222222222222222"));
    }

    /**
     * 953. 验证外星语词典 5/17
     */
    public boolean isAlienSorted(String[] words, String order) {
        char[] sc = order.toCharArray();
        Map<Character, Integer> orderMap = new HashMap<>();
        orderMap.put('-', -1);  //空白字符
        for (int i = 0; i < sc.length; i++) {
            orderMap.put(sc[i], i);
        }
        for(int w = 1;w<words.length;w++){
            String s1 = words[w-1]+"-", s2 = words[w]+"-";
            for(int i = 0;i<Math.min(s1.length(), s2.length());i++){
                if(orderMap.get(s1.charAt(i)) > orderMap.get(s2.charAt(i))) return false;
                else if(orderMap.get(s1.charAt(i)) < orderMap.get(s2.charAt(i))) break;
            }
        }
        return true;
    }

    /**
     * hot 33. 搜索旋转排序数组
     */
    public int search(int[] nums, int target) {
        int len = nums.length;
        int l = 0, r = len;
        int m = (l+r)/2;

        while(l<=r){
            m = (l+r)/2;
            //当前在旋转点左侧
            if(nums[m] > nums[l]){
                if(target < nums[m]){
                    r = m;
                    m = (l+m)/2;
                }else{
                    l = m;
                    m = (m+r)/2;
                }
            }else if(nums[m] < nums[l]){
                if(target > nums[m]){
                    l = m;
                    m = (m+r)/2;
                }else{
                    r = m;
                    m = (m+l)/2;
                }
            }else {
                return -1;
            }
        }
        return nums[l];
    }

    @Test
    public void testSearch(){
        System.out.println(search(new int[]{4, 5, 6, 7, 0, 1, 2}, 4));
    }

    public int minDistance(String word1, String word2) {
        int m = word1.length()+1, n = word2.length()+1;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            dp[i][0] = i;
        }
        for (int i = 0; i < n; i++) {
            dp[0][i] = i;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1])+1;
                if(word1.charAt(i-1) == word2.charAt(j-1)){
                    dp[i][j] = Math.min(dp[i][j], dp[i-1][j-1]);
                }else{
                    dp[i][j] = Math.min(dp[i][j], dp[i-1][j-1]+1);
                }
            }
        }
        return dp[m-1][n-1];

    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        HashSet<ListNode> set = new HashSet<>();
        ListNode p = headA;
        while (p != null){
            set.add(p);
            p = p.next;
        }
        p = headB;
        while(p != null){
            if(set.contains(p)){
                return p;
            }else{
                p = p.next;
            }
        }
        return null;
    }

    /**
     * 6018 解密信消息
     */
    public String decodeMessage(String key, String message) {
        HashMap<Character, Character> keyMap = new HashMap<>();
        char[] sc = key.toCharArray();
        keyMap.put(' ', ' ');
        for(int i = 0;i<sc.length;i++) {
            if(!keyMap.containsKey(sc[i])){
                keyMap.put(sc[i], (char) (keyMap.size()-1+'a'));
            }
        }
        StringBuffer sb = new StringBuffer();
        for(char c : message.toCharArray()){
            sb.append(keyMap.get(c));
        }
        return sb.toString();
    }

    @Test
    public void testOfDecodeMessage(){
        String key = "the quick brown fox jumps over the lazy dog";
        String message = "vkbs bs t suepuv";
        System.out.println(decodeMessage(key, message));
    }

    /**
     * 6111. 螺旋矩阵 IV
     */
    public int[][] spiralMatrix(int m, int n, ListNode head) {
        int[][] res = new int[m][n];
        for(int[] ar: res){
            Arrays.fill(ar, -1);
        }
        int right = n,left = 0,up = 0, down = m, directionX = 1, directionY = 1;
        int i = up, j = left;
        try {
            while (head != null) {
                for (j = left; j < right; j++) {
                    res[up][j] = head.val;
                    head = head.next;
                }

                for (i = up+1; i < down; i++) {
                    res[i][right - 1] = head.val;
                    head = head.next;
                }

                if (down > up && right > left) {
                    for (j = right - 2; j >= left; j--) {
                        res[down - 1][j] = head.val;
                        head = head.next;
                    }
                    for (i = down - 2; i >= up-1; i--) {
                        res[i][left] = head.val;
                        head = head.next;
                    }
                }
                left++;
                up++;
                right--;
                down--;
            }
        }catch (NullPointerException e){
            return res;
        }

        return res;
    }

    @Test
    public void testOfSpiralMatrix() {
        int[] arr = new int[] {3,0,2,6,8,1,7,9,4,2,5,5,0};
        ListNode head = new ListNode(arr[0]);
        ListNode p = head;
        for(int i = 1;i<arr.length;i++){
            head.next = new ListNode(arr[i]);
            head = head.next;
        }
        spiralMatrix(3, 5, p);
    }
}




class ListNode {
      int val;
      int test;
      ListNode next;
      ListNode(int x) {
          val = x;
          next = null;
      }
  }




