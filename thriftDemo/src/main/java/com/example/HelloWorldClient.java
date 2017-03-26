package com.example;

import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TJSONProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

import static com.example.threadPool.HelloThreadPoolServer.SERVER_PORT;

/**
 * Created by tianfei on 17/3/21.
 *
 * 客户端编码步骤:
 * 创建Transport
 * 创建TProtocol
 * 基于TTransport和TProtocol创建 Client
 * 调用Client的相应方法
 */
@Slf4j
public class HelloWorldClient {

    private static final String SERVER_IP = "localhost";
    private static final int SIMPLE_SERVER_PORT = 9090;
    private static final int THREAD_POOL_SERVER_PORT = 8090;
    private static final int TIME_OUT = 30000;

    public static void main(String[] args) {

        TTransport transport = null;
        try {
            final String username = "Lisa";

            //创建Transport


            transport = new TSocket(SERVER_IP, THREAD_POOL_SERVER_PORT,TIME_OUT);


            //创建TProtocol 协议要和服务端一致
//            TProtocol protocol = new TJSONProtocol(transport);
            TProtocol protocol = new TBinaryProtocol(transport);

            //创建 Client
            HelloWorldService.Client client = new HelloWorldService.Client(protocol);

            transport.open();
            //调用Client的相应方法
            final String result = client.sayHello(username);
            System.out.println(result);

            log.info(result);


        } catch (TTransportException e) {
            e.printStackTrace();
        } catch (TException x) {
            x.printStackTrace();
        } finally {
            if(transport != null){
                transport.close();
            }
        }
    }
}
