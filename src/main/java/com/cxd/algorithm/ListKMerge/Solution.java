package com.cxd.algorithm.ListKMerge;

import java.util.Comparator;
import java.util.Objects;
import java.util.PriorityQueue;

/**
 * <a href="https://leetcode.cn/problems/merge-k-sorted-lists/submissions/">LeetCode提交</a>
 * @author fanyin
 * @date 2023/4/12 17:07
 */
public class Solution {



    public ListNode mergeKLists(ListNode[] lists) {

        //  PriorityQueue 里面用到了最大堆最小堆的特性
        //  底层使用数组实现
        PriorityQueue<ListNode> pq = new PriorityQueue<>(Comparator.comparingInt((ListNode o) -> o.val));

        // 利用最小堆的特性 和 每个单向链表已排序的特性
        // 将每个链表的表头 写入 最小堆，每次拿到堆的根就是最小的node
        // 然后修改node的next，将多个链表有序合并
        // 这里可以扩展一下：排序的算法
        for (int i = 0; i < lists.length; i++) {
            pq.offer(lists[i]);
        }

        ListNode min = pq.poll();
        if (min == null) {
            return null;
        }

        ListNode curr = min;

        ListNode next;

        do {
            if (curr.next != null) {
                pq.offer(curr.next);
            }

            next = pq.poll();

            curr.next = next;

            curr = next;
        } while (curr != null);


        print("end: ", min);

        return min;
    }


    public static void print(String pre , ListNode head) {
        StringBuilder sb = new StringBuilder(pre);
        while (Objects.nonNull(head)) {
            sb.append(" ");
            sb.append(head.val);

            head = head.next;
        }

        System.out.println(sb);
    }

}
