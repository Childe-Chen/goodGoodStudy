package com.cxd.algorithm.palindrome;

import java.util.Objects;

/**
 * @author fanyin
 * @date 2023/4/11 15:52
 */
public class ListNodeSingly {
    private int val;

    private ListNodeSingly next;

    public ListNodeSingly(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public boolean hasNext() {
        return Objects.nonNull(getNext());
    }

    public ListNodeSingly getNext() {
        return next;
    }

    public void setNext(ListNodeSingly next) {
        this.next = next;
    }
}
