package cn.coblog.service.imp;

import cn.coblog.common.exception.BlogException;
import cn.coblog.domain.Config;
import cn.coblog.domain.enums.ConfigTypeEnum;
import cn.coblog.mapper.ConfigMapper;
import cn.coblog.service.ConfigService;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 配置接口实现
 * @author chens
 * @version 1.0.0
 * @date 2020/8/10
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ConfigServiceImpl implements ConfigService {

    @Resource
    private ConfigMapper configMapper;

    @Override
    public Map<String, String> getAllGlobal() {
        Map<String, String> result = new HashMap<>();
        List<Config> configs = getByType(ConfigTypeEnum.GLOBAL_OPTION.getValue());
        configs.forEach(variable -> result.put(variable.getName(),variable.getValue()));
        return result;
    }

    @Override
    public Config getById(Long id) {
        return configMapper.selectByPrimaryKey(id);
    }

    @Override
    public boolean removeById(Long id) {
        return configMapper.deleteByPrimaryKey(id) > 0;
    }

    public List<Config> getByType(Integer type) {
        Example example = new Example(Config.class);
        example.createCriteria().andEqualTo("type",type);
        return configMapper.selectByExample(example);
    }

    @Override
    public boolean updateById(Config config) {
        return configMapper.updateByPrimaryKeySelective(config) > 0;
    }

    @Override
    public boolean save(Config config) {
        return configMapper.insert(config) > 0;
    }

    @Override
    public List<Config> list() {
        return configMapper.selectAll();
    }

    @Override
    public String getConfigByName(String commentCheck) {
        Example example = new Example(Config.class);
        example.createCriteria().andEqualTo("name",commentCheck);
        Config config = configMapper.selectOneByExample(example);
        return config.getValue();
    }

    @Override
    public <T> T getConfigObject(String name, Class<T> clazz) {
        String value = getConfigByName(name);
        if(StringUtils.isNotBlank(value)){
            return JSONObject.parseObject(value, clazz);
        }
        try {
            return clazz.newInstance();
        } catch (Exception e) {
            throw new BlogException("获取参数失败");
        }
    }

}




