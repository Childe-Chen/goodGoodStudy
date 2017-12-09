package com.cxd.sotiy.node;

/**
 * 文本Node
 *
 * @author childe
 * @date 2017/12/6 20:57
 **/
public final class TextNode extends AbstractNode {

    @Override
    public StringBuffer getPretreatmentBuffer() {
        return new StringBuffer(super.getPreCondition());
    }
}
