package cn.coblog.common.utils;

import cn.coblog.config.ElasticSearchConfig;
import com.alibaba.fastjson.JSONObject;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * ElasticSearch工具类
 * @author chens
 * @version 1.0.0
 * @date 2020/11/20
 */
public class ElasticSearchUtil {

    /**
     * 保存或更新文档
     * @param client 客户端
     * @param indexString 索引
     * @param id id
     * @param document 文档
     * @return Boolean
     */
    public static boolean index(RestHighLevelClient client, String indexString, Long id, Object document){
        IndexRequest indexRequest = new IndexRequest(indexString);
        indexRequest.id(id+"");
        String jsonString = JSONObject.toJSONString(document);
        indexRequest.source(jsonString, XContentType.JSON);
        boolean result = false;
        IndexResponse index = null;
        try {
            index = client.index(indexRequest, ElasticSearchConfig.COMMON_OPTIONS);
            if (index != null){
                int failed = index.getShardInfo().getFailed();
                if (failed == 0){ result = true; }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 搜索文档
     * @param client 客户端
     * @param indexString 索引
     * @param searchSourceBuilder 搜索构造器
     * @param clazz 要转换的类文件
     * @return list
     */
    public static <T> List<T> search(RestHighLevelClient client, String indexString, SearchSourceBuilder searchSourceBuilder, Class<T> clazz){
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices(indexString);
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = null;
        List<T> list = new ArrayList<>();
        try {
            searchResponse = client.search(searchRequest, ElasticSearchConfig.COMMON_OPTIONS);
            SearchHits hits = searchResponse.getHits();
            SearchHit[] searchHits = hits.getHits();
            for (SearchHit hit : searchHits) {
                // do something with the SearchHit
                String sourceAsString = hit.getSourceAsString();
                T t = JSONObject.parseObject(sourceAsString, clazz);
                list.add(t);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}
