package com.component;

import org.apache.http.HttpHost;
import org.apache.http.message.BasicHeader;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;

public class ElasticsearchClient {
    public static void main(String[] args) {
        try {
            SearchRequest searchRequest = new SearchRequest("xx");
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.from(0);
            searchSourceBuilder.size(5);
            searchSourceBuilder.sort(new FieldSortBuilder("@timestamp").order(SortOrder.ASC));
            MatchQueryBuilder matchQueryBuilder = new MatchQueryBuilder("historyJobId", "2428927");
            searchSourceBuilder.query(matchQueryBuilder);
            searchRequest.source(searchSourceBuilder);
            RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost("xxx", 81, "http")).setPathPrefix("v7")
                    .setDefaultHeaders(new BasicHeader[]{new BasicHeader("Appid", "billions"), new BasicHeader("AppKey", "proxy")
                            , new BasicHeader("Content-Type", "application/json")
                            , new BasicHeader("Authorization", "Basic xxx==")}));


            client.close();
            System.out.println(client.search(searchRequest, RequestOptions.DEFAULT).getHits().getAt(0));
        } catch (Exception e) {
            ;
        }
    }
}
