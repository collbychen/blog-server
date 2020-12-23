package com.chens.coblog.service;

import com.chens.coblog.domain.Link;

import java.util.List;

/**
 * 链接接口
 * @author chens
 * @version 1.0.0
 * @date 2020/8/10
 */
public interface LinkService {

    boolean save(Link link);

    Link getById(Long id);

    boolean updateById(Link link);

    boolean removeById(Long id);

    List<Link> getAll();
}




