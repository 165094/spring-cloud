package com.test.query.es.service;

import com.test.query.es.entity.SUser;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SUserService extends ElasticsearchRepository<SUser, Long>, PagingAndSortingRepository<SUser, Long> {

}
