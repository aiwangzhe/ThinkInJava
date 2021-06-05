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
import java.util.Random;

public class WriteDataToHDFS {

    public static void main(String[] args) throws IOException {
        Configuration conf = new Configuration();

        DFSClient dfsClient = new DFSClient(URI.create("hdfs://localhost:8020"), conf);
        DFSOutputStream outputStream = (DFSOutputStream) dfsClient.create("/tmp/test1.txt", true);
        byte[] data = new byte[512 * 127];
        Random random = new Random();
        for(int i = 0; i < data.length; i++) {
            data[i] = (byte) random.nextInt(127);
        }
        outputStream.write("abcdefga".getBytes());
        outputStream.flush();
        outputStream.close();
    }
}
