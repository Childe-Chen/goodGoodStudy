package com.cxd.sotiy.factory;

import org.apache.velocity.app.VelocityEngine;

/**
 * @author  childe on 2017/7/16.
 */
public enum VelocityEngineFactory {

    INSTANCE(new VelocityEngine());

    VelocityEngine velocityEngine;

    VelocityEngineFactory(VelocityEngine velocityEngine) {
        this.velocityEngine = velocityEngine;
    }

    public static VelocityEngine getVelocityEngine() {
        return INSTANCE.velocityEngine;
    }
}
