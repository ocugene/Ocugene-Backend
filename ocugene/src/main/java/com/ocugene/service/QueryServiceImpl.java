package com.ocugene.service;

import com.ocugene.entity.Query;
import com.ocugene.entity.requests.AddQueryRequest;
import com.ocugene.repository.QueryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QueryServiceImpl implements QueryService{

    @Autowired
    QueryRepository queryRepository;

    @Override
    public Query addQuery(AddQueryRequest addQueryRequest) {
        return queryRepository.save(addQueryRequest.mapToQuery());
    }

    @Override
    public List<Query> getAllQueries() {
        return queryRepository.findAll(Sort.by(Sort.Direction.ASC, "queryID"));
    }

    @Override
    public Query respondToQuery(Integer queryID) {

        Query query = queryRepository.findById(queryID).get();
        query.setRespondedFlag(true);

        return queryRepository.save(query);
    }

    @Override
    public Query markQueryAsUnread(Integer queryID) {
        Query query = queryRepository.findById(queryID).get();
        query.setRespondedFlag(false);

        return queryRepository.save(query);
    }
}
