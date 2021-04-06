package cn.coblog.service;

import cn.coblog.domain.Config;

import java.util.List;
import java.util.Map;

/**
 * 配置接口
 * @author chens
 * @version 1.0.0
 * @date 2020/8/10
 */
public interface ConfigService {

    /**
     * 获得所有全局变量
     * @return 转换成Map之后的变量
     */
    Map<String, String> getAllGlobal();

    Config getById(Long id);

    boolean removeById(Long id);

    boolean updateById(Config config);

    boolean save(Config config);

    List<Config> list();

    String getConfigByName(String name);

    /**
     * 根据参数名，获取参数值的Object对象
     * @param name    参数名
     * @param clazz  Object对象
     */
    <T> T getConfigObject(String name, Class<T> clazz);
}




