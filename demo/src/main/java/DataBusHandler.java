/**
 * Bilibili.com Inc.
 * Copyright (c) 2009-2021 All Rights Reserved.
 */

import com.google.common.eventbus.EventBus;

/**
 *
 * @author leping
 * @version $Id: DataBusHandler.java, v 0.1 2021-07-13 下午6:20 leping Exp $$
 */
public class DataBusHandler {
    private EventBus testHandler = new EventBus("test1");

    public void processTestHandler1(Object msg) {
        testHandler.post(msg);
    }

    public void addTestListener1(NodeListener nodeListener) {
        testHandler.register(nodeListener);
    }

    //test dispatch to subscribe
    public static void main(String[] args) {
        DataBusHandler dataBusHandler = new DataBusHandler();
        TestListener1 testListener1 = new TestListener1();
        TestListener2 testListener2 = new TestListener2();
        TestListener3 testListener3 = new TestListener3();
        dataBusHandler.addTestListener1(testListener1);
        dataBusHandler.addTestListener1(testListener2);
        dataBusHandler.addTestListener1(testListener3);
        for (int i = 0; i < 10; i++) {
            dataBusHandler.processTestHandler1("t");
        }

        System.out.println("--------------");
        for (int i = 0; i < 10; i++) {
            dataBusHandler.processTestHandler1(1);
        }
    }
    //根据参数类型来派发，如果参数类型能匹配到多个方法，多个subscribe方法都会执行
}
