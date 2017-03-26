package com.example;

import com.example.service.impl.HelloWorldServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TJSONProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TServer.Args;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;

/**
 * Created by tianfei on 17/3/21.
 * 服务端编码基本步骤:
 * 1 实现服务处理接口impl
 * 2 创建TProcessor
 * 3 创建TServerTransport
 * 4 创建TProtocol
 * 5 创建TServer
 * 6 启动Server
 */
@Slf4j
public class HelloWorldServer {

    private static final int SERVER_PORT = 9090;

    public static void startSimpleServer() {
        try {
            log.info("Starting the TSimpleServer ...");
            //创建TProcessor
            TProcessor tProcessor = new HelloWorldService.Processor<HelloWorldServiceImpl>(new HelloWorldServiceImpl());

            TServerSocket serverTransport = new TServerSocket(SERVER_PORT);
            TSimpleServer.Args tArgs = new TSimpleServer.Args(serverTransport);
            tArgs.processor(tProcessor);
            tArgs.protocolFactory(new TBinaryProtocol.Factory());
//            tArgs.protocolFactory(new TCompactProtocol.Factory());
//            tArgs.protocolFactory(new TJSONProtocol.Factory());


            //创建TServer 简单的单线程服务模型，一般用于测试
            TServer server = new TSimpleServer(tArgs);
            //启动Server
            server.serve();


        } catch (Exception e) {
            e.printStackTrace();
            log.error("server start error!!");
        }
    }

    public static void main(String[] args) {
        startSimpleServer();

        //TServerTransport serverTransport = new TServerSocket(9090);
        //简单的单线程服务模型，一般用于测试
//        TServer server = new TSimpleServer(
//                new Args(serverTransport).processor(processor));

        // Use this for a multithreaded server
        // TServer server = new TThreadPoolServer(new
        // TThreadPoolServer.Args(serverTransport).processor(processor));

        //log.info("Starting the simple server...");
        //System.out.println("Starting the simple server...");
        //server.serve();
    }
}


