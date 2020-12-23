package com.chens.coblog.service;

import com.chens.coblog.domain.Article;
import com.chens.coblog.domain.Spider;

import java.util.List;

public interface SpiderService {


    boolean save(Spider spider);

    boolean updateById(Spider spider);

    Spider getById(Long id);

    boolean removeById(Long id);

    Article spiderArticle(Spider spider, String url);

    List<Spider> getAll();

}




