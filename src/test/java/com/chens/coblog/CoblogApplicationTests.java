package com.chens.coblog;

import cn.coblog.CoblogApplication;
import cn.coblog.service.ArticleService;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CoblogApplication.class)
class CoblogApplicationTests {


    @Autowired
    public RestHighLevelClient client;
    @Autowired
    private ArticleService articleService;

    @Test
    void contextLoads() throws IOException {
        System.out.println("开始");
//        IndexRequest indexRequest = new IndexRequest("blog");
//        indexRequest.id("2");
//        Article article = articleService.getById(7L);
//        String jsonString = JSONObject.toJSONString(article);
//        indexRequest.source(jsonString, XContentType.JSON);
//        IndexResponse index = client.index(indexRequest, ElasticSearchConfig.COMMON_OPTIONS);
//        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//        searchSourceBuilder.from(0);
//        searchSourceBuilder.size(2);
//        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
//        List<Article> list = ElasticSearchUtil.search(client, "blog", searchSourceBuilder , Article.class);
//        System.out.println(client);
    }

}
