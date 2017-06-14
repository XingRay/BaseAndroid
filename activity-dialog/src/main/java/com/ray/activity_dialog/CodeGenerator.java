package com.ray.activity_dialog;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Author      : leixing
 * Date        : 2017-06-12
 * Email       : leixing@hecom.cn
 * Version     : 0.0.1
 * <p>
 * Description : user for generate unique code as long type.
 */

public class CodeGenerator {
    private static volatile CodeGenerator INSTANCE;

    /**
     * unique code
     */
    private AtomicLong mCode;

    public static CodeGenerator getInstance() {
        if (INSTANCE == null) {
            synchronized (CodeGenerator.class) {
                if (INSTANCE == null) {
                    INSTANCE = new CodeGenerator();
                }
            }
        }

        return INSTANCE;
    }

    private CodeGenerator() {
        mCode = new AtomicLong(0);
    }

    /**
     * generate a long type unique code
     *
     * @return code
     */
    public long next() {
        return mCode.getAndAdd(1);
    }
}
