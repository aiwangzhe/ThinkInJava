package com.wangzhe.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;

public class FileSystemTest {

    public static void main(String[] args) {
        Configuration conf = new Configuration();
        try {
            FileSystem fs = FileSystem.get(conf);
            FileStatus[] fileStatuses = fs.listStatus(new Path("/"));
            for(FileStatus fileStatus : fileStatuses) {
                System.out.println("status: " + fileStatus.getOwner());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
