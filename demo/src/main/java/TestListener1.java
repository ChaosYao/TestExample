/**
 * Bilibili.com Inc.
 * Copyright (c) 2009-2021 All Rights Reserved.
 */

import com.google.common.eventbus.Subscribe;

/**
 *
 * @author leping
 * @version $Id: TestListener1.java, v 0.1 2021-07-13 下午6:39 leping Exp $$
 */
public class TestListener1 implements NodeListener {
    @Subscribe
    public void execute(String msg, Integer num) {
        System.out.println("test1:" + msg + num);
    }
}
