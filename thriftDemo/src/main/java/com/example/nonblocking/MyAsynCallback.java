package com.example.nonblocking;

import org.apache.thrift.TException;

import java.util.concurrent.CountDownLatch;

import com.example.HelloWorldService.AsyncClient.sayHello_call;
import org.apache.thrift.async.AsyncMethodCallback;

/**
 * 异步回调方法
 * Created by tianfei on 17/3/28.
 */
public class MyAsynCallback implements AsyncMethodCallback<String> {
    private CountDownLatch latch;

    public MyAsynCallback(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void onComplete(String response) {
        System.out.println("onComplete");
        try {
            // Thread.sleep(1000L * 1);
            System.out.println("AsynCall result = "
                    + response);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            latch.countDown();
        }
    }

    @Override
    public void onError(Exception exception) {
        System.out.println("onError :" + exception.getMessage());
        latch.countDown();
    }
}
