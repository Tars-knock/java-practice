class Solution {
    public int[] findRedundantConnection(int[][] edges) {
        int[] parent = new int[edges.length+1];
        for(int i = 1;i<edges.length+1;i++)    parent[i] = i;

        for(int i = 0;i<edges.length;i++)
        {
            if(find(parent, edges[i][0])!= find(parent, edges[i][1]))
            {
                union(parent, edges[i][0], edges[i][1]);
            }
            else return edges[i];
        }
        return null;
    }
    //**
    这个方法就是并查集当中的“查”，查找祖先并返回；
    其中else中的语句还顺便进行了路径缩短，使得所有同一家族的元素指向的均为最高级的祖先
     */
    public static int find(int[] parent, int index)
    {
        if(parent[index]==index)    return index;
        else return parent[index]=find(parent, parent[index]);
    }
    //**
    这个方法就是并查集当中的“并”， 可以将一条新的树枝加入原有的图中
    */
    public static int[] union(int[] parent, int index1, int index2)
    {
        parent[find(parent, parent[index2])] = find(parent, index1);
        return parent;
    }

    public static void main(String[] args)
    {
        int[][] test = {{1.2},{1,3},{2,3}};
        System.out.println(findRedundantConnection(test).toString());
    }
}
//题目详见 https://leetcode-cn.com/problems/redundant-connection/
//并查集定义详见 https://oi-wiki.org/ds/dsu/