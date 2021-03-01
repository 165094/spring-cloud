package com.test.query.es.service;

import com.test.query.es.entity.SAuth;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SAuthService extends ElasticsearchRepository<SAuth, Long>, PagingAndSortingRepository<SAuth, Long> {
}
