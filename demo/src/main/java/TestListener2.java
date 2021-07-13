/**
 * Bilibili.com Inc.
 * Copyright (c) 2009-2021 All Rights Reserved.
 */

import com.google.common.eventbus.Subscribe;

/**
 *
 * @author leping
 * @version $Id: TestListener2.java, v 0.1 2021-07-13 下午6:41 leping Exp $$
 */
public class TestListener2 implements NodeListener{
    @Subscribe
    public void execute(String msg, String msg2) {
        System.out.println("test2" + msg + msg2);
    }
}
