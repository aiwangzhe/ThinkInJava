package redis;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class ConnToRedis {

    public static void main(String[] args) {
        //连接本地的 Redis 服务
//        Set<HostAndPort> hostAndPortSet = new HashSet<>();
//        HostAndPort h1 = new HostAndPort("172.17.171.215", 6379);
//        HostAndPort h2 = new HostAndPort("172.17.171.215", 6380);
//        hostAndPortSet.add(h1);
//        hostAndPortSet.add(h2);
//        JedisCluster jedisCluster = new JedisCluster(hostAndPortSet, 600, 600, 10,
//                "123456", new GenericObjectPoolConfig());
//        jedisCluster.lpush("k1", "test-v1");
//        List<String> list = jedisCluster.lrange("k1", 0 , 0);
//        for(int i=0; i<list.size(); i++) {
//            System.out.println("列表项为: "+list.get(i));
//        }


        Jedis jedis = new Jedis("172.17.171.215");
        jedis.auth("123456");

        System.out.println("连接成功");
        //查看服务是否运行
       System.out.println("服务正在运行: "+jedis.ping());
        //存储数据到列表中
        jedis.lpush("sitelist", "Taobao");
//        jedis.lpush("site-list", "Google");
//        jedis.lpush("site-list", "Taobao");
        // 获取存储的数据并输出
        List<String> list = jedis.lrange("site-list", 0 ,0);
        for(int i=0; i<list.size(); i++) {
            System.out.println("列表项为: "+list.get(i));
        }
//        // 获取数据并输出
//        Set<String> keys = jedis.keys("*");
//        Iterator<String> it=keys.iterator() ;
//        while(it.hasNext()){
//            String key = it.next();
//            System.out.println(key);
//        }
    }
}
