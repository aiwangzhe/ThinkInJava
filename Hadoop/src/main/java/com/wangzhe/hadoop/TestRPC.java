package com.wangzhe.hadoop;

import com.google.protobuf.ServiceException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hdfs.protocol.ClientProtocol;
import org.apache.hadoop.hdfs.protocol.HdfsFileStatus;
import org.apache.hadoop.hdfs.protocol.proto.ClientNamenodeProtocolProtos.GetFileInfoRequestProto;
import org.apache.hadoop.hdfs.protocol.proto.ClientNamenodeProtocolProtos.GetFileInfoResponseProto;
import org.apache.hadoop.hdfs.protocolPB.ClientNamenodeProtocolPB;
import org.apache.hadoop.ipc.ProtobufRpcEngine;
import org.apache.hadoop.ipc.RPC;

import java.io.IOException;
import java.net.InetSocketAddress;

public class TestRPC {

    public static void main(String[] args) {
        Configuration conf = new Configuration();
        InetSocketAddress socketAddress = new InetSocketAddress("test-server", 8020);
        try {
            RPC.setProtocolEngine(conf, ClientNamenodeProtocolPB.class, ProtobufRpcEngine.class);
            ClientNamenodeProtocolPB clientProtocol = RPC.getProxy(ClientNamenodeProtocolPB.class, 1L, socketAddress, conf);
            GetFileInfoRequestProto requestProto
                    = GetFileInfoRequestProto.newBuilder().setSrc("/mydata/test.sh").build();
            GetFileInfoResponseProto responseProto = clientProtocol.getFileInfo(null, requestProto);
            System.out.println("owner: " + responseProto.getFs().getOwner());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServiceException e) {
            e.printStackTrace();
        }

    }
}
