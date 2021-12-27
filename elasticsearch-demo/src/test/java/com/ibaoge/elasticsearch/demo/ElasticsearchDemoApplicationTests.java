package com.ibaoge.elasticsearch.demo;

import com.google.gson.Gson;
import jdk.nashorn.internal.parser.JSONParser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.Avg;
import org.elasticsearch.search.aggregations.metrics.AvgAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.io.IOException;
import java.util.Map;

import static com.ibaoge.elasticsearch.demo.config.SimpleMallElasticSearchConfig.COMMON_OPTIONS;

@SpringBootTest
class ElasticsearchDemoApplicationTests {

    @Resource
    private RestHighLevelClient client;

    // 测试存储 API
    // https://www.elastic.co/guide/en/elasticsearch/client/java-rest/current/java-rest-high-document-index.html
    @Test
    void indexData() throws IOException {
        IndexRequest indexRequest = new IndexRequest("users");
        indexRequest.id("1");
//        indexRequest.source("userName", "zhang san", "age", 18, "gender", "男");
        User user = new User("ZhangSan", "男", 18);
        String json = new Gson().toJson(user);
        indexRequest.source(json, XContentType.JSON);

        // 同步保存
        IndexResponse indexResponse = client.index(indexRequest, COMMON_OPTIONS);

        System.out.println(indexResponse);

        // 异步保存
//        client.indexAsync();

        // 批量保存
//        client.bulk();
//        client.bulkAsync();
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    class User{
        private String userName;
        private String gender;
        private Integer age;
    }

    // 测试 search API
    // https://www.elastic.co/guide/en/elasticsearch/client/java-rest/current/java-rest-high-search.html
    @Test
    void searchData() throws IOException {
        // 1.创建检索请求
        SearchRequest searchRequest = new SearchRequest();
        // 指定索引
        searchRequest.indices("bank");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        // 1.1 构造检索条件
//        searchSourceBuilder.query();
//        searchSourceBuilder.from();
//        searchSourceBuilder.size();
//        searchSourceBuilder.aggregation();
        searchSourceBuilder.query(QueryBuilders.matchQuery("address", "mill"));

        // 1.2 按照年龄的值分布进行聚合
        TermsAggregationBuilder ageAgg = AggregationBuilders.terms("ageAgg").field("age").size(10);
        searchSourceBuilder.aggregation(ageAgg);

        // 1.3 计算平均薪资
        AvgAggregationBuilder balanceAvg = AggregationBuilders.avg("balanceAvg").field("balance");
        searchSourceBuilder.aggregation(balanceAvg);

        System.out.println("检索条件：" + searchSourceBuilder);

        // 指定 DSL，检索条件
        searchRequest.source(searchSourceBuilder);

        // 2.执行检索
        SearchResponse searchResponse = client.search(searchRequest, COMMON_OPTIONS);

        // 3.分析结果
        System.out.println(searchResponse);
//        Map map = new Gson().fromJson(searchResponse.toString(), Map.class);
        // 3.1 获取所有查到的数据
        SearchHits hits = searchResponse.getHits();
        SearchHit[] searchHits = hits.getHits();
        //          "_index": "bank",
        //			"_type": "_doc",
        //			"_id": "970",
        //			"_score": 5.4032025,
        //			"_source": {}
        for (SearchHit hit : searchHits) {
//            hit.getIndex(); hit.getType(); hit.getId();
            String string = hit.getSourceAsString();
            Account account = new Gson().fromJson(string, Account.class);
            System.out.println("account: " + account);
        }

        // 3.2 获取这次检索到的分析信息
        Aggregations aggregations = searchResponse.getAggregations();
//        aggregations.asList().stream().forEach(aggregation -> {
//            System.out.println("当前聚合：" + aggregation.getName());
//        });

        Terms ageAgg1 = aggregations.get("ageAgg");
        ageAgg1.getBuckets().stream().forEach(bucket -> {
            String keyAsString = bucket.getKeyAsString();
            System.out.println("年龄：" + keyAsString + " ==> " + bucket.getDocCount());
        });

        Avg balanceAvg1 = aggregations.get("balanceAvg");
        System.out.println("平均薪资：" + balanceAvg1.getValue());
    }

}
