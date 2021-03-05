package com.wangzhe.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hdfs.DFSClient;
import org.apache.hadoop.hdfs.DFSOutputStream;
import org.apache.hadoop.hdfs.protocol.HdfsFileStatus;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.util.Arrays;
import java.util.Collections;

public class WriteDataToHDFS {

    public static void main(String[] args) throws IOException {
        Configuration conf = new Configuration();


        DFSClient dfsClient = new DFSClient(URI.create("hdfs://node1:8020"), conf);
        DFSOutputStream outputStream = (DFSOutputStream)
                dfsClient.create("/user/hive/warehouse/test2.db/ratings_small/000000_1", true);
        byte[] data = "2,4,4.0,1232323432".getBytes();
        outputStream.write(data);
        outputStream.close();
    }
}
