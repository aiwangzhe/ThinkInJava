package com.wangzhe.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.LocatedFileStatus;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.RemoteIterator;
import org.apache.hadoop.hdfs.DFSClient;
import org.apache.hadoop.hdfs.protocol.HdfsFileStatus;
import org.apache.hadoop.hdfs.protocolPB.ClientNamenodeProtocolTranslatorPB;

import java.io.IOException;
import java.net.URI;

public class ReadHDFS {

    public static void main(String[] args) throws IOException {
        Configuration conf = new Configuration();

        DFSClient dfsClient = new DFSClient(URI.create("hdfs://test-server:8020"), conf);
        HdfsFileStatus fileStatus = dfsClient.getFileInfo("/mydata/test.sh");
        System.out.println("file owner: " + fileStatus.getOwner());

        //new ClientNamenodeProtocolTranslatorPB()
    }
}
