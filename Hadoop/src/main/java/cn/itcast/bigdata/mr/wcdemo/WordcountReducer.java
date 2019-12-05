package cn.itcast.bigdata.mr.wcdemo;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

/**
 * KEYIN, VALUEIN 对应  mapper输出的KEYOUT,VALUEOUT类型对应
 * 
 * KEYOUT, VALUEOUT 是自定义reduce逻辑处理结果的输出数据类型
 * KEYOUT是单词
 * VLAUEOUT是总次数
 * @author
 *
 */
public class WordcountReducer implements Reducer<Text, IntWritable, Text, IntWritable> {

	/**
	 * <angelababy,1><angelababy,1><angelababy,1><angelababy,1><angelababy,1>
	 * <hello,1><hello,1><hello,1><hello,1><hello,1><hello,1>
	 * <banana,1><banana,1><banana,1><banana,1><banana,1><banana,1>
	 * 入参key，是一组相同单词kv对的key
	 */
	@Override
	public void reduce(Text key, Iterator<IntWritable> values, OutputCollector<Text,
			IntWritable> output, Reporter reporter) throws IOException{
		int count=0;
		while(values.hasNext()){
			count += values.next().get();
		}
		output.collect(key, new IntWritable(count));
	}

	@Override
	public void close() throws IOException {

	}

	@Override
	public void configure(JobConf job) {

	}
}
