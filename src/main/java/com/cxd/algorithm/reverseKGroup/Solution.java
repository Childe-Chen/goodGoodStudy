package com.cxd.algorithm.reverseKGroup;

import java.util.Objects;

/**
 * @author fanyin
 * @date 2023/4/13 10:22
 */
public class Solution {

    public static void main(String[] args) {
        ListNode data = mockDataEven();
        Solution solution = new Solution();

        print("result: ", solution.reverseKGroup(data, 3));

    }

    public static ListNode mockDataEven() {
        ListNode head1 = new ListNode(1);

        ListNode head2 = new ListNode(2);
        head1.setNext(head2);

        ListNode head3 = new ListNode(3);
        head2.setNext(head3);

        ListNode head4 = new ListNode(4);
        head3.setNext(head4);

        ListNode head5 = new ListNode(5);
        head4.setNext(head5);

        return head1;
    }
    
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode start = head;
        ListNode fast = head;
        ListNode preEnd = null;
        int counter = 0;

        // 链表的基本操作归结为：

        do {
            counter++;

            if (counter == k) {
                // reverse
                ListNode startNode = start;
                ListNode nextStart = fast.next;
                fast.next = null;

                if (preEnd == null) {
                    head = fast;
                }

                preEnd = reverse(startNode, fast, preEnd);

                counter = 1;
                start = nextStart;
                fast = nextStart;
            }


            if (fast == null || fast.next == null) {
                if (preEnd != null) {
                    preEnd.next = start;
                }
                break;
            }

            fast = fast.next;
        } while (true);

        return head;
    }

    public ListNode reverse(ListNode start, ListNode end, ListNode preEnd) {
        print("start: ", start);

        ListNode pre = null;
        ListNode curr = start;

        while (curr != null) {
            ListNode next = curr.next;

            curr.next = pre;
            pre = curr;

            curr = next;
        }

        if (preEnd != null) {
            preEnd.next = end;
        }

        print("curr: ", end);
        print("curr: ", preEnd);

        return start;
    }

    public static void print(String pre , ListNode head) {
        StringBuilder sb = new StringBuilder(pre);
        while (Objects.nonNull(head)) {
            sb.append(" ");
            sb.append(head.getVal());

            head = head.getNext();
        }

        System.out.println(sb);
    }
}
