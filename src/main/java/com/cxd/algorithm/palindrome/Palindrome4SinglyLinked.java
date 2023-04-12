package com.cxd.algorithm.palindrome;

import java.util.Objects;

/**
 * <a href="https://leetcode.cn/problems/palindrome-linked-list/">LeetCode提交</a>
 * @author fanyin
 * @date 2023/4/11 15:45
 */
public class Palindrome4SinglyLinked {

    public static void main(String[] args) {
        System.out.println(isPalindrome(mockDataEven()));

    }

    public static ListNodeSingly mockDataEven() {
        ListNodeSingly head1 = new ListNodeSingly(1);

        ListNodeSingly head2 = new ListNodeSingly(2);
        head1.setNext(head2);

        ListNodeSingly head3 = new ListNodeSingly(3);
        head2.setNext(head3);

        ListNodeSingly head4 = new ListNodeSingly(2);
        head3.setNext(head4);

        ListNodeSingly head5 = new ListNodeSingly(1);
        head4.setNext(head5);

        return head1;
    }

    public static boolean isPalindrome(ListNodeSingly head) {
        // 1.找到链表的中间位置：使用快慢指针，慢指针每次走1步，快指针每次走2步，那么当快指针走到最后时，慢指针正好在中间
        // 注意：奇数和偶数长度，奇数长度时，慢指针所在的位置为正中心；偶数长度时，慢指针所在位置为后半段列表的开始位置

        // 2.因为是单向链表，所以需要将后半段链表反转后再与前半段做比较
        // 注意：链表的反转比较简单不再单独列出来

        // 3.遍历比较
        // 注意：奇数长度时，总有一半多出来一个元素（看程序的处理方式，是把正中心的元素放在哪里），所以可以通过 "同时判断下一个元素是否存在" 作为条件来控制遍历的终止

        ListNodeSingly startNodeP2 = getStartNodeOfP2List(head);

        ListNodeSingly reverseStartNodeP2 = reverseList(startNodeP2);

        return isEqual(head, reverseStartNodeP2);
    }

    public static boolean isEqual(ListNodeSingly headP1, ListNodeSingly headP2) {
        print("headP1: ", headP1);
        print("headP2: ",headP2);

        ListNodeSingly p1Cursor = headP1;
        ListNodeSingly p2Cursor = headP2;


        do {
            if (Objects.isNull(p1Cursor) || Objects.isNull(p2Cursor)) {
                return false;
            }

            if (p1Cursor.getVal() != p2Cursor.getVal()) {
                return false;
            }

            p1Cursor = p1Cursor.getNext();
            p2Cursor = p2Cursor.getNext();
        } while (Objects.nonNull(p1Cursor) && Objects.nonNull(p2Cursor));

        return true;
    }

    public static ListNodeSingly reverseList(ListNodeSingly head) {
        print("reverseListStart: ", head);

        ListNodeSingly curr = head;
        ListNodeSingly pre = null;
        while (Objects.nonNull(curr)) {
            ListNodeSingly next = curr.getNext();

            curr.setNext(pre);

            pre = curr;

            curr = next;
        }

        print("reverseList: ", pre);

        return pre;
    }

    public static ListNodeSingly getStartNodeOfP2List(ListNodeSingly head) {
        print("getStartNodeOfP2List: ", head);
        ListNodeSingly fast = head;
        ListNodeSingly slow = head;

        while (Objects.nonNull(fast) && fast.hasNext()) {
            slow = slow.getNext();
            fast = fast.getNext().getNext();
        }

        return slow;
    }

    public static void print(String pre ,ListNodeSingly head) {
        StringBuilder sb = new StringBuilder(pre);
        while (Objects.nonNull(head)) {
            sb.append(" ");
            sb.append(head.getVal());

            head = head.getNext();
        }

        System.out.println(sb);
    }
}
