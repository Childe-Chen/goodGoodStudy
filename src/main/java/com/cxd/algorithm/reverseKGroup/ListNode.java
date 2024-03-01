package com.cxd.algorithm.reverseKGroup;

/**
 * @author fanyin
 * @date 2023/4/12 17:06
 */
public class ListNode {
    public int val;

    public ListNode next;

    public void setNext(ListNode next) {
        this.next = next;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public ListNode getNext() {
        return next;
    }

    public ListNode() {}
    public ListNode(int val) { this.val = val; }
    public ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}