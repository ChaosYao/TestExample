/**
 * Bilibili.com Inc.
 * Copyright (c) 2009-2021 All Rights Reserved.
 */

import com.google.common.eventbus.Subscribe;

/**
 *
 * @author leping
 * @version $Id: TestListener3.java, v 0.1 2021-07-13 下午7:02 leping Exp $$
 */
public class TestListener3 implements NodeListener {
    @Subscribe
    public void execute(String msg) {
        System.out.println("test3:" + msg);
    }
}
