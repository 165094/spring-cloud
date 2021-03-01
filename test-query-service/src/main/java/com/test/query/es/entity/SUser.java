package com.test.query.es.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;
import java.util.Date;

@Data
@Document(indexName = "test_study", type = "over_flow")
public class SUser implements Serializable {

    private static final long serialVersionUID = 6931505667718744614L;

    @Id
    private Integer id;

    private String userName;

    private String passWord;

    private String sex;

    private String phone;

    private Date time;

    private Integer num;
}
