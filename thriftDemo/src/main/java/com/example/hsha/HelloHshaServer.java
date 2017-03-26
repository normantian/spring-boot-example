package com.example.hsha;

import com.example.HelloWorldService;
import com.example.service.impl.HelloWorldServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.server.THsHaServer;
import org.apache.thrift.server.TNonblockingServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;

import static com.sun.tools.doclint.Entity.gt;
import static com.sun.tools.doclint.Entity.lt;

/**
 * Created by tianfei on 17/3/24.
 */
@Slf4j
public class HelloHshaServer {

    public static final int SERVER_PORT = 8092;

    public void startServer() {
        try {
            log.info("HelloWorld THsHaServer start ....");

            TProcessor tprocessor = new HelloWorldService.Processor<HelloWorldService.Iface>(
                    new HelloWorldServiceImpl());

            TNonblockingServerSocket tnbSocketTransport = new TNonblockingServerSocket(
                    SERVER_PORT);
            THsHaServer.Args thhsArgs = new THsHaServer.Args(tnbSocketTransport);
            thhsArgs.processor(tprocessor);
            thhsArgs.transportFactory(new TFramedTransport.Factory());
            thhsArgs.protocolFactory(new TBinaryProtocol.Factory());

            //半同步半异步的服务模型
            TServer server = new THsHaServer(thhsArgs);
            server.serve();

        } catch (Exception e) {
            log.error("Server start error!!!");
            e.printStackTrace();
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        HelloHshaServer server = new HelloHshaServer();
        server.startServer();
    }
}
