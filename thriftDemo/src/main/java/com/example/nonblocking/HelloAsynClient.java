package com.example.nonblocking;

import com.example.HelloWorldService;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.async.TAsyncClientManager;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.transport.TNonblockingSocket;
import org.apache.thrift.transport.TNonblockingTransport;
import com.example.HelloWorldService.AsyncClient.sayHello_call;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 异步客户端
 * Created by tianfei on 17/3/28.
 */
@Slf4j
public class HelloAsynClient {

    private static final String SERVER_IP = "localhost";
    private static final int NON_BLOCKING_SERVER_PORT = 8091;
    private static final int TIME_OUT = 30000;

    /**
     *
     * @param userName
     */
    public void startClient(String userName) {
        try {
            TAsyncClientManager clientManager = new TAsyncClientManager();
            TNonblockingTransport transport = new TNonblockingSocket(SERVER_IP,
                    NON_BLOCKING_SERVER_PORT, TIME_OUT);

            TProtocolFactory tprotocol = new TCompactProtocol.Factory();
            HelloWorldService.AsyncClient asyncClient = new HelloWorldService.AsyncClient(
                    tprotocol, clientManager, transport);
            System.out.println("Client start .....");

            CountDownLatch latch = new CountDownLatch(1);
            //自定义callback
            MyAsynCallback callBack = new MyAsynCallback(latch);
            System.out.println("call method sayHello start ...");
            asyncClient.sayHello(userName, callBack);
            System.out.println("call method sayHello .... end");
            boolean wait = latch.await(30, TimeUnit.SECONDS);
            System.out.println("latch.await =:" + wait);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("startClient end.");
    }

//    public class AsynCallback implements AsyncMethodCallback<sayHello_call> {
//        private CountDownLatch latch;
//
//		public AsynCallback(CountDownLatch latch) {
//            this.latch = latch;
//        }
//
//        @Override
//        public void onComplete(sayHello_call response) {
//            System.out.println("onComplete");
//            try {
//                // Thread.sleep(1000L * 1);
//                System.out.println("AsynCall result =:"
//                        + response.getResult().toString());
//            } catch (TException e) {
//                e.printStackTrace();
//            } catch (Exception e) {
//                e.printStackTrace();
//            } finally {
//                latch.countDown();
//            }
//        }
//
//        @Override
//        public void onError(Exception exception) {
//            System.out.println("onError :" + exception.getMessage());
//            latch.countDown();
//        }
//    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        HelloAsynClient client = new HelloAsynClient();
        client.startClient("Michael");

    }
}
