package com.chens.coblog.service.imp;

import com.chens.coblog.domain.Article;
import com.chens.coblog.domain.Spider;
import com.chens.coblog.mapper.SpiderMapper;
import com.chens.coblog.service.SpiderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SpiderServiceImpl implements SpiderService {

    @Resource
    private SpiderMapper spiderMapper;

    @Override
    public boolean save(Spider spider) {
        return spiderMapper.insert(spider) > 0;
    }

    @Override
    public List<Spider> getAll() {
        return spiderMapper.selectAll();
    }

    @Override
    public boolean updateById(Spider spider) {
        return spiderMapper.updateByPrimaryKeySelective(spider) > 0;
    }

    @Override
    public Spider getById(Long id) {
        return spiderMapper.selectByPrimaryKey(id);
    }

    @Override
    public boolean removeById(Long id) {
        return spiderMapper.deleteByPrimaryKey(id) > 0;
    }

    @Override
    public Article spiderArticle(Spider spider, String url) {
        return null;
    }

}




