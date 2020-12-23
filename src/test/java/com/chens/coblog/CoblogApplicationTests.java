package com.chens.coblog;

import com.chens.coblog.service.ArticleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CoblogApplicationTests {

    @Autowired
    private ArticleService articleService;

    @Test
    void contextLoads() {
//        String city = IPUtils.getCity("202.96.134.33");
//        System.out.println(city);
//        Calendar c = Calendar.getInstance();
//        List<Article> archiveList = articleService.getArchiveList();
//        List<Map<String, Object>> dataList = new ArrayList<>();
//        String year = "";
//        List<Article> lists = null;
//        for (Article a : archiveList){
//            Map<String, Object> dataMap = new HashMap<>();
//            boolean b = true;
//            c.setTime(a.getCreateTime());
//            if(!year.equals(c.get(Calendar.YEAR)+"")){
//                year = c.get(Calendar.YEAR)+"";
//                lists = new ArrayList<>();
//                b = false;
//            }
//            if ( lists != null){
//            lists.add(a);
//            }
//            if (!b){
//                dataMap.put(year, lists);
//                dataList.add(dataMap);
//            }
//        }
    }

}
