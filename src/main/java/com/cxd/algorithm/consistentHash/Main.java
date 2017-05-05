package com.cxd.algorithm.consistentHash;

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

        c.addServer(new Server("achuguniadsfaang"));
        findEntries(c, entries);

    }

    private static Cluster createCluster() {
        Cluster c = new Cluster();
        c.addServer(new Server("international"));
        c.addServer(new Server("china"));
        c.addServer(new Server("japanjapan"));
        c.addServer(new Server("Amarica"));
        c.addServer(new Server("samsungtsisger"));
        c.addServer(new Server("achuguniang"));
        return c;
    }

    private static void findEntries(Cluster c, Entry[] entries) {
        for (Entry e : entries) {
            if (e == c.get(e)) {
                System.out.println("重新找到了entry: " + e);
            } else {
                System.out.println("entry已失效: " + e);
            }
        }
    }
}