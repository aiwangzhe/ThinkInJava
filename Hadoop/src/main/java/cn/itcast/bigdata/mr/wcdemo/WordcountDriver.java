package cn.itcast.bigdata.mr.wcdemo;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.*;
import org.apache.hadoop.mapred.jobcontrol.Job;

/**
 * 相当于一个yarn集群的客户端
 * 需要在此封装我们的mr程序的相关运行参数，指定jar包
 * 最后提交给yarn
 * @author
 *
 */
public class WordcountDriver {
	
	public static void main(String[] args) throws Exception {
		Configuration conf = new Configuration();

		
		//是否运行为本地模式，就是看这个参数值是否为local，默认就是local
		/*conf.set("mapreduce.framework.name", "local");*/
		
		//本地模式运行mr程序时，输入输出的数据可以在本地，也可以在hdfs上
		//到底在哪里，就看以下两行配置你用哪行，默认就是file:///
		/*conf.set("fs.defaultFS", "hdfs://mini1:9000/");*/
		/*conf.set("fs.defaultFS", "file:///");*/
		
		
		
		//运行集群模式，就是把程序提交到yarn中去运行
		//要想运行为集群模式，以下3个参数要指定为集群上的值
		/*conf.set("mapreduce.framework.name", "yarn");
		conf.set("yarn.resourcemanager.hostname", "mini1");
		conf.set("fs.defaultFS", "hdfs://mini1:9000/");*/
		JobConf jobConf = new JobConf(conf);

		//jobConf.setJar("c:/wc.jar");
		//指定本程序的jar包所在的本地路径
		jobConf.setJarByClass(WordcountDriver.class);
		
		//指定本业务job要使用的mapper/Reducer业务类
		jobConf.setMapperClass(WordcountMapper.class);
		jobConf.setReducerClass(WordcountReducer.class);
		
		//指定mapper输出数据的kv类型
		jobConf.setMapOutputKeyClass(Text.class);
		jobConf.setMapOutputValueClass(IntWritable.class);
		
		//指定最终输出的数据的kv类型
		jobConf.setOutputKeyClass(Text.class);
		jobConf.setOutputValueClass(IntWritable.class);
		
		//指定需要使用combiner，以及用哪个类作为combiner的逻辑
		/*job.setCombinerClass(WordcountCombiner.class);*/
		jobConf.setCombinerClass(WordcountReducer.class);
		
		//如果不设置InputFormat，它默认用的是TextInputformat.class
		//jobConf.setInputFormat(CombineTextInputFormat.class);
		//CombineTextInputFormat.setMaxInputSplitSize(job, 4194304);
		//CombineTextInputFormat.setMinInputSplitSize(job, 2097152);
		
		//指定job的输入原始文件所在目录

		FileInputFormat.setInputPaths(jobConf, "/apps/hive/warehouse/tpcds_bin_partitioned_orc_5.db/call_center/000000_0");
		//指定job的输出结果所在目录
		FileOutputFormat.setOutputPath(jobConf, new Path("output"));
		
		//将job中配置的相关参数，以及job所用的java类所在的jar包，提交给yarn去运行
		/*job.submit();*/
		JobClient jobClient = new JobClient(jobConf);
		RunningJob runningJob = jobClient.submitJob(jobConf);
		runningJob.waitForCompletion();
		
	}
	

}
