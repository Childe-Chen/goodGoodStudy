package com.cxd.algorithm.consistentHash;

/**
 * Created by childe on 2017/5/5.
 */
public class Main {

    static int entryNum = 100;

    public static void main(String[] args) {
        //创建缓存集群
        Cluster cluster = createCluster();

        //写入缓存实体
        for (int i = 0; i < entryNum; i++) {
            cluster.put(new Entry(String.valueOf(i)));
        }

        //有效数据统计
        validCount(cluster);

        //新增缓存节点
        cluster.addServer(new Server("C"));

        System.out.println("afer add a server");

        //有效数据统计
        validCount(cluster);

    }

    private static Cluster createCluster() {
        Cluster c = new Cluster();
        c.addServer(new Server("A#1"));
        c.addServer(new Server("A#2"));
        c.addServer(new Server("B#1"));
        c.addServer(new Server("B#2"));
        return c;
    }

    private static void validCount(Cluster cluster) {
        int validNum = 0;
        for (int i = 0; i < entryNum; i++) {
            Entry entry = cluster.get(String.valueOf(i));
            if (entry != null) {
                validNum++;
            }
        }
        System.out.println("valid entry : " + validNum + ", total entry : " + entryNum);
    }
}