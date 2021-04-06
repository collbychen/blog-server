package cn.coblog.common.base;

import tk.mybatis.mapper.entity.Condition;

import java.util.HashMap;

public class BasePages {

    public static final Integer PAGE_SIZE = 5;
    public static final String ASC = "asc";
    public static final String DESC = "desc";

    public static HashMap<String, Object> getPagesMap(Object list, Object total){
        HashMap<String, Object> res = new HashMap<>(2);
        res.put("list", list);
        res.put("total", total);
        return res;
    }

    public static Condition addSort(String sort, Condition condition){
        if (sort != null){
            String field = sort.split(",")[0];
            if (!"".equals(field)){
                if (sort.contains(ASC)){
                    condition.orderBy(field).asc();
                }else {
                    condition.orderBy(field).desc();
                }
            }
        }
        return condition;
    }

}
