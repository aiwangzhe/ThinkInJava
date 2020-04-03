package com.wangzhe.hadoop;

import com.google.protobuf.BlockingService;
import com.wangzhe.hadoop.MyNameNodePB;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hdfs.protocol.proto.ClientNamenodeProtocolProtos;
import org.apache.hadoop.hdfs.protocolPB.ClientNamenodeProtocolPB;
import org.apache.hadoop.ipc.ProtobufRpcEngine;
import org.apache.hadoop.ipc.RPC;

import java.io.IOException;

public class MyNamenodeServer {

    public static void main(String[] args) {
        Configuration conf = new Configuration();
        MyNameNodePB myNameNode = new MyNameNodePB();
        try {
            RPC.setProtocolEngine(conf, ClientNamenodeProtocolPB.class,
                    ProtobufRpcEngine.class);
            BlockingService clientNNPbService = ClientNamenodeProtocolProtos.ClientNamenodeProtocol.
                    newReflectiveBlockingService(myNameNode);
            RPC.Server server = new RPC.Builder(conf)
                    .setProtocol(ClientNamenodeProtocolPB.class)
                    .setInstance(clientNNPbService)
                    .setBindAddress("127.0.0.1")
                    .setPort(12345).build();
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
