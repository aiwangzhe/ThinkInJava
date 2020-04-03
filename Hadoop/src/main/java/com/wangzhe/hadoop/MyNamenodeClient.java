package com.wangzhe.hadoop;

import com.google.protobuf.ServiceException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hdfs.protocol.proto.ClientNamenodeProtocolProtos;
import org.apache.hadoop.hdfs.protocolPB.ClientNamenodeProtocolPB;
import org.apache.hadoop.ipc.ProtobufRpcEngine;
import org.apache.hadoop.ipc.RPC;

import java.io.IOException;
import java.net.InetSocketAddress;

public class MyNamenodeClient {

    public static void main(String[] args) {
        Configuration conf = new Configuration();
        try {
            RPC.setProtocolEngine(conf, ClientNamenodeProtocolPB.class, ProtobufRpcEngine.class);
            ClientNamenodeProtocolPB protocolPB = RPC.getProxy(ClientNamenodeProtocolPB.class,
                    1L, new InetSocketAddress("test-server", 9000), conf);
            ClientNamenodeProtocolProtos.GetFileInfoRequestProto req =
                    ClientNamenodeProtocolProtos.GetFileInfoRequestProto.newBuilder()
                    .setSrc("/tmp")
                    .build();
            ClientNamenodeProtocolProtos.GetFileInfoResponseProto responseProto =
                    protocolPB.getFileInfo(null, req);
            System.out.println(responseProto.getFs().getOwner());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }
}
