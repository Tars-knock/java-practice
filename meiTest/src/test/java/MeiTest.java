import org.junit.jupiter.api.Test;

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
            for(int j = 1;j<n;j++){
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }
        return dp[m-1][n-1];
    }

    @Test
    public void testUniquePaths() {
        uniquePaths(23, 12);
    }

}



