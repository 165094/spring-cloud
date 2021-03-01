package com.test.query.es.entity;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@Document(indexName = "test_study", type = "s_auth")
public class SAuth {

    private Integer id;

    private String authCode;

    private String authName;

    private String resourceName;

    private String resourceUrl;
}
