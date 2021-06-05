package com.wangzhe.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.hdfs.DFSClient;
import org.apache.hadoop.hdfs.DFSInputStream;
import org.apache.hadoop.hdfs.DFSOutputStream;
import org.apache.hadoop.hdfs.protocol.HdfsFileStatus;
import org.apache.hadoop.hdfs.protocolPB.ClientNamenodeProtocolTranslatorPB;

import java.io.IOException;
import java.net.URI;
import java.nio.ByteBuffer;

public class ReadHDFS {

    public static void main(String[] args) throws IOException {
        readHdfsFile();
    }

    private static void readHdfsFile() throws IOException {
        Configuration conf = new Configuration();

        DFSClient dfsClient = new DFSClient(URI.create("hdfs://node1:8020"), conf);
        DFSInputStream inputStream = dfsClient.open("/data/ratings.csv");
        byte[] data = new byte[1024];
        //buffer.get(data);
        ByteBuffer buffer = ByteBuffer.allocate(4096);
        int result = inputStream.read(20, data, 0, 1000);
        //buffer.flip();

        System.out.println("readData: " + new String(data));
        System.out.println("result: " + result);
    }
}
