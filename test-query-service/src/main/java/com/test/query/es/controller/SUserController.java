package com.test.query.es.controller;

import com.alibaba.fastjson.JSONObject;
import com.test.query.es.entity.SUser;
import com.test.query.es.service.SUserService;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class SUserController {

    private static final Logger logger = LoggerFactory.getLogger(SUserController.class);

//    @Autowired
//    private ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    private TransportClient transportClient;

    @Autowired
    private SUserService userService;

    @GetMapping
    public String insert(){

        for (int i = 1; i <= 100;i++){
            SUser user = new SUser();
            user.setId(i);
            user.setUserName("李四");
            user.setPhone("1237412");
            user.setPassWord("789755");
            user.setSex("男");
            user.setTime(new Date());
            user.setNum((int)(Math.random()*100));
            userService.save(user);
        }
        return "adsad";
    }

    @GetMapping("/select")
    public String select(){
        Iterable<SUser> search = userService.search(QueryBuilders.matchQuery("userName", "张三"));

        return JSONObject.toJSONString(search);
    }

    @GetMapping("/search")
    public String search(){
        SearchRequestBuilder requestbuilder = transportClient.prepareSearch("test_study")
                .setTypes("s_user");
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery()
                .must(QueryBuilders.termQuery("passWord", "123145"))
                .must(QueryBuilders.termQuery("userName", "张"))
                .must(QueryBuilders.termQuery("userName", "三"));

        requestbuilder.setQuery(queryBuilder);
        requestbuilder.setFrom(0).setSize(100).setExplain(false);
        requestbuilder.setScroll(new TimeValue(30000));
        SearchResponse response = requestbuilder.get();

        List<SUser> users = new ArrayList<>();
        SearchHits hits = response.getHits();
        if (hits != null && hits.getHits().length > 0) {
            for (SearchHit hit : hits) {
                String json = hit.getSourceAsString();
                try {
                    SUser dto = JSONObject.parseObject(json,SUser.class);
                    users.add(dto);
                } catch (Exception e) {
                    logger.error("解析json数据出错：", e);
                }
            }
        }
        return JSONObject.toJSONString(users);
    }

}
