package com.cxd.algorithm.consistentHash;


import java.util.SortedMap;
import java.util.TreeMap;

/**
 * 缓存集群
 * Created by childe on 2017/5/14.
 */
public class Cluster {
    private static final int SERVER_SIZE_MAX = 1024;

    private SortedMap<Integer, Server> servers = new TreeMap<>();
    private int size = 0;

    public void put(Entry e) {
        routeServer(e.getKey().hashCode()).put(e);
    }

    public Entry get(String key) {
        return routeServer(key.hashCode()).get(key);
    }

    private Server routeServer(int hash) {
        if (servers.isEmpty()){
            return null;
        }

        /**
         * 顺时针找到离该hash最近的slot（server）
         */
        if (!servers.containsKey(hash)) {
            SortedMap<Integer, Server> tailMap = servers.tailMap(hash);
            hash = tailMap.isEmpty() ? servers.firstKey() : tailMap.firstKey();
        }
        return servers.get(hash);
    }

    public boolean addServer(Server s) {
        if (size >= SERVER_SIZE_MAX) {
            return false;
        }

        servers.put(s.hashCode(), s);

        size++;
        return true;
    }
}
