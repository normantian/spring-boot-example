package com.example.nonblocking;

import com.example.HelloWorldService;
import com.example.service.impl.HelloWorldServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.server.TNonblockingServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TServerSocket;

import static com.sun.tools.doclint.Entity.gt;
import static com.sun.tools.doclint.Entity.lt;

/**
 * Created by tianfei on 17/3/24.
 */
@Slf4j
public class HelloNonBlockingServer {

    public static final int SERVER_PORT = 8091;

    public void startServer() {
        try {
            log.info("HelloWorld TNonblockingServer start ....");

            TProcessor tprocessor = new HelloWorldService.Processor<HelloWorldService.Iface>(
                    new HelloWorldServiceImpl());

            TNonblockingServerSocket tnbSocketTransport = new TNonblockingServerSocket(
                    SERVER_PORT);
            TNonblockingServer.Args tnbArgs = new TNonblockingServer.Args(
                    tnbSocketTransport);
            tnbArgs.processor(tprocessor);
            tnbArgs.transportFactory(new TFramedTransport.Factory());
            tnbArgs.protocolFactory(new TCompactProtocol.Factory());
//            tnbArgs.protocolFactory(new TBinaryProtocol.Factory());


            // 使用非阻塞式IO，服务端和客户端需要指定TFramedTransport数据传输的方式
            TServer server = new TNonblockingServer(tnbArgs);
            server.serve();

        } catch (Exception e) {
            System.out.println("TNonblockingServer start error!!!");
            e.printStackTrace();
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        HelloNonBlockingServer server = new HelloNonBlockingServer();
        server.startServer();
    }
}
