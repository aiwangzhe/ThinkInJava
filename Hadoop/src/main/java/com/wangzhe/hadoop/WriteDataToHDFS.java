package com.wangzhe.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hdfs.DFSClient;
import org.apache.hadoop.hdfs.DFSOutputStream;
import org.apache.hadoop.hdfs.protocol.HdfsFileStatus;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;

public class WriteDataToHDFS {

    public static void main(String[] args) throws IOException {
        Configuration conf = new Configuration();

        DFSClient dfsClient = new DFSClient(URI.create("hdfs://test-server:8020"), conf);
        DFSOutputStream outputStream = (DFSOutputStream) dfsClient.create("/tmp/test1.log", true);
        //outputStream.write();
    }
}
