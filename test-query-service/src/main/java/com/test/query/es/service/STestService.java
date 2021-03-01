package com.test.query.es.service;

import com.test.query.es.entity.STest;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface STestService extends ElasticsearchRepository<STest, Long>, PagingAndSortingRepository<STest, Long> {
}
