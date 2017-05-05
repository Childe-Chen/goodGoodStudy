package com.cxd.algorithm.hash;

/**
 * Created by childe on 2017/5/5.
 */
public class Main {

    public static void main(String[] args) {
        Cluster c = createCluster();

        Entry[] entries = {
                new Entry("i"),
                new Entry("have"),
                new Entry("a"),
                new Entry("pen"),
                new Entry("an"),
                new Entry("apple"),
                new Entry("applepen"),
                new Entry("pineapple"),
                new Entry("pineapplepen"),
                new Entry("PPAP")
        };

        for (Entry e : entries)
            c.put(e);

        c.addServer(new Server("192.168.0.6"));

        findEntries(c, entries);

    }

    private static Cluster createCluster() {
        Cluster c = new Cluster();
        c.addServer(new Server("192.168.0.0"));
        c.addServer(new Server("192.168.0.1"));
        c.addServer(new Server("192.168.0.2"));
        c.addServer(new Server("192.168.0.3"));
        c.addServer(new Server("192.168.0.4"));
        c.addServer(new Server("192.168.0.5"));
        return c;
    }

    private static void findEntries(Cluster c, Entry[] entries) {
        for (Entry e : entries) {
            if (e == c.get(e)) {
                System.out.println("重新找到了entry:" + e);
            } else {
                System.out.println("entry已失效:" + e);
            }
        }
    }
}
