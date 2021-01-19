package com.wangzhe.hbase;

import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;

import java.io.IOException;

public class HbaseMain {

    public static void main(String[] args) {
        try {
            Connection connection = ConnectionFactory.createConnection();
            Table table = connection.getTable(TableName.valueOf("METRIC_RECORD"));
            HTableDescriptor hTableDescriptor = table.getTableDescriptor();
            HColumnDescriptor[] cfs = hTableDescriptor.getColumnFamilies();
            for( HColumnDescriptor descriptor : cfs) {
                System.out.println(descriptor);
            }
            ResultScanner resultScanner = table.getScanner("0".getBytes());
            Result[] results = resultScanner.next(1000);
            System.out.println("size: " + results.length);
            connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
