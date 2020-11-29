package com.wangzhe.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.hdfs.DFSClient;
import org.apache.hadoop.hdfs.DFSOutputStream;
import org.apache.hadoop.hdfs.protocol.HdfsFileStatus;
import org.apache.hadoop.hdfs.protocolPB.ClientNamenodeProtocolTranslatorPB;

import java.io.IOException;
import java.net.URI;

public class ReadHDFS {

    public static void main(String[] args) throws IOException {
        writeToHdfs();
    }

    private static void writeToHdfs() throws IOException {
        Configuration conf = new Configuration();

        DFSClient dfsClient = new DFSClient(URI.create("hdfs://m1.leap.com"), conf);
        DFSOutputStream outputStream =
                (DFSOutputStream) dfsClient.create("/tmp/test1.log", true);
        String data = "这是一份测试数据！";
        outputStream.write(data.getBytes());
        outputStream.close();
    }
}
