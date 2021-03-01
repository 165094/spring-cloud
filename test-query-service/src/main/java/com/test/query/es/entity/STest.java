package com.test.query.es.entity;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@Document(indexName = "test_sjjd", type = "s_faa")
public class STest {

    private Integer id;

    private String name;

    private String shinelon;

    private String auth;
}
