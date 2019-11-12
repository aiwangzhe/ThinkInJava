package com.wangzhe.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.LocatedFileStatus;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.RemoteIterator;

import java.io.IOException;

public class ReadHDFS {

    public static void main(String[] args) {
        Configuration conf = new Configuration();
        conf.set("fs.defalutFS", "hdfs://node2.leap.com:8020");
        try {
            FileSystem fs = FileSystem.get(conf);
            RemoteIterator<LocatedFileStatus>  files = fs.listFiles(new Path("/"), false);
            while (files.hasNext()) {
                System.out.println(files.next());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
