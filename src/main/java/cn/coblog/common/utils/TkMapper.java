package cn.coblog.common.utils;

import tk.mybatis.mapper.common.*;

/**
 * @author chens
 * @version 1.0.0
 * @date 2020/8/29 8:25
 * */
public interface TkMapper<T> extends BaseMapper<T>, MySqlMapper<T>, IdsMapper<T>, ConditionMapper<T>, ExampleMapper<T> {
}
