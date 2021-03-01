package com.test.query.es.config;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;

@Configuration
public class ElasticsearchConfig {

    private static final Logger logger = LoggerFactory.getLogger(ElasticsearchConfig.class);

    private static final String poolSize = "10";

    private static final String clusterNodes = "192.168.41.129:9300";

    private static final String clusterName = "elasticsearch";

    @Bean
    public TransportClient transportClient() {
        logger.info("Elasticsearch初始化开始。。。。。");
        TransportClient transportClient = null;
        try {
            // 配置信息
            Settings esSetting = Settings.builder()
                    .put("cluster.name", clusterName) //集群名字
                    .put("client.transport.sniff", false)//增加嗅探机制，找到ES集群
                    .put("thread_pool.search.size", Integer.parseInt(poolSize))//增加线程池个数，暂时设为5
                    .build();
            //配置信息Settings自定义
            transportClient = new PreBuiltTransportClient(esSetting);
            String clusterNodes = this.clusterNodes;
            String[] split = clusterNodes.split(",");
            for (String address:split){
                //ip与端口分离
                String[] addrPort = address.split(":");
//                new TransportAddress(InetAddress.getByName(addrPort[0]),Integer.parseInt(addrPort[1]));
                transportClient.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(addrPort[0]),
                        Integer.parseInt(addrPort[1])));
            }
        } catch (Exception e) {
            logger.error("elasticsearch TransportClient create error!!", e);
        }
        return transportClient;
    }

}
