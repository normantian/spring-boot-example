package com.example.threadPool;

import com.example.HelloWorldService;
import com.example.service.impl.HelloWorldServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;

/**
 * Created by tianfei on 17/3/24.
 */
@Slf4j
public class HelloThreadPoolServer {

    public static final int SERVER_PORT = 8090;

    public void startServer() {
        try {

            log.info("HelloWorld TThreadPoolServer start ....");

            TProcessor tprocessor = new HelloWorldService.Processor<HelloWorldService.Iface>(
                    new HelloWorldServiceImpl());

            TServerSocket serverTransport = new TServerSocket(SERVER_PORT);
            TThreadPoolServer.Args ttpsArgs = new TThreadPoolServer.Args(serverTransport);
            ttpsArgs.processor(tprocessor);
            ttpsArgs.protocolFactory(new TBinaryProtocol.Factory());

            // 线程池服务模型，使用标准的阻塞式IO，预先创建一组线程处理请求。
            TServer server = new TThreadPoolServer(ttpsArgs);
            server.serve();

        } catch (Exception e) {
            System.out.println("Server start error!!!");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        HelloThreadPoolServer server = new HelloThreadPoolServer();
        server.startServer();
    }
}
