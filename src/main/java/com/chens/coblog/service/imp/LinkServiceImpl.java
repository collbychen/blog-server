package com.chens.coblog.service.imp;

import com.chens.coblog.domain.Link;
import com.chens.coblog.mapper.LinkMapper;
import com.chens.coblog.service.LinkService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 链接接口实现
 * @author chens
 * @version 1.0.0
 * @date 2020/8/10
 */
@Service
public class LinkServiceImpl implements LinkService {

    @Resource
    private LinkMapper linkMapper;

    @Override
    public boolean save(Link link) {
        return linkMapper.insert(link) > 0;
    }

    @Override
    public Link getById(Long id) {
        return linkMapper.selectByPrimaryKey(id);
    }

    @Override
    public boolean updateById(Link link) {
        return linkMapper.updateByPrimaryKeySelective(link) > 0;
    }

    @Override
    public boolean removeById(Long id) {
        return linkMapper.deleteByPrimaryKey(id) > 0;
    }

    @Override
    public List<Link> getAll() {
        return linkMapper.selectAll();
    }
}




