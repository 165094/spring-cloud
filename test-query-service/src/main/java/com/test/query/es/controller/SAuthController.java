package com.test.query.es.controller;

import com.alibaba.fastjson.JSONObject;
import com.test.query.es.entity.SAuth;
import com.test.query.es.entity.STest;
import com.test.query.es.service.SAuthService;
import com.test.query.es.service.STestService;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/auth")
public class SAuthController {

    private static final Logger logger = LoggerFactory.getLogger(SAuthController.class);

    @Autowired
    private SAuthService authService;

    @Autowired
    private STestService testService;

    @Autowired
    private TransportClient client;

    @PostMapping
    public String insertAuth(){
//        SAuth auth = new SAuth();
//        auth.setId(1);
//        auth.setAuthCode("001");
//        auth.setAuthName("test");
//        auth.setResourceName("baidu");
//        auth.setResourceUrl("www.baidu.com");
        STest test = new STest();
        test.setAuth("12345");
        test.setName("tom");
        test.setShinelon("true");
        testService.save(test);
//        authService.save(auth);
        return JSONObject.toJSONString(test);
    }

    @GetMapping("/search")
    public String search(){
        SearchRequestBuilder requestbuilder = client.prepareSearch("test_study")
                .setTypes("s_auth");
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery()
                .must(QueryBuilders.termQuery("authCode", "001"));

        requestbuilder.setQuery(queryBuilder);
        requestbuilder.setFrom(0).setSize(100).setExplain(false);
        requestbuilder.setScroll(new TimeValue(30000));
        SearchResponse response = requestbuilder.get();

        List<SAuth> auths = new ArrayList<>();
        SearchHits hits = response.getHits();
        if (hits != null && hits.getHits().length > 0) {
            for (SearchHit hit : hits) {
                String json = hit.getSourceAsString();
                try {
                    SAuth dto = JSONObject.parseObject(json,SAuth.class);
                    auths.add(dto);
                } catch (Exception e) {
                    logger.error("解析json数据出错：", e);
                }
            }
        }
        return JSONObject.toJSONString(auths);
    }
}
