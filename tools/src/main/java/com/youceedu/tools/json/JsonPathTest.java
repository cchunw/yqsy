package com.youceedu.tools.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;

import java.util.Set;

/**
 * @program: yqsy
 * @ClassName JsonPathTest
 * @Description TODO
 * @Author chen chunwei
 * @create: 2023-06-15 17:18
 * @Version 1.0
 */
public class JsonPathTest {
    public static void main(String[] args) {
        //定义字符串json
        String str = "{\"store\":{\"book\":[{\"category\":\"reference\"," +
                "\"author\":\"Nigel Rees\",\"title\":\"Sayings\",\"price\":9.66}," +
                "{\"category\":\"fiction\",\"author\":\"Evelyn\",\"title\":\"Sword\"," +
                "\"price\":19.88}],\"bicycle\":[{\"price\":20.66,\"color\":\"red\"}]}}";

        //step1 将字符串json转换为JSON
        JSONObject json = JSON.parseObject(str);

        //step2 JSONPath.eval(),获取json数据
        System.out.println("book数目为：" + JSONPath.eval(json, "$.store.book.size()"));
        System.out.println("book数目为：" + JSONPath.read(str, "$.store.book.size()"));
        System.out.println("--------");
        System.out.println("第一本书title为：" + JSONPath.eval(json, "$.store.book[0].title"));
        System.out.println("第二本书title为：" + JSONPath.eval(json, "$.store.book[1].title"));
        System.out.println("第一本、第二本书title为：" + JSONPath.eval(json, "$.store.book[0,1].title"));
        System.out.println("--------");
        System.out.println("price大于10元book为：" + JSONPath.eval(json, "$.store.book[price>10]"));
        System.out.println("price大于10元book的title为：" + JSONPath.eval(json, "$.store.book[price>10].title"));
        System.out.println("price在8到12之间book为：" + JSONPath.eval(json, "$.store.book[price between 8 and 12]"));
        System.out.println("--------");
        System.out.println("title以S开头的book为：" + JSONPath.eval(json, "$.store.book[title like 'S%']"));
        System.out.println("title在Sword和Sayings范围：" + JSONPath.eval(json, "$.store.book[title in ('Sword','Sayings')]"));
        System.out.println("--------");
        System.out.println("bicycle所有属性值：" + JSONPath.eval(json, "$.store.bicycle[0].*"));
        System.out.println("bicycle的price和color的属性值：" + JSONPath.eval(json, "$.store.bicycle[0]['price','color']"));
        System.out.println(JSONPath.eval(json,"$.store.bicycle"));
        System.out.println("------分割线------");

        //修改
        JSONPath.set(json, "$.store.book[0].title", "new book");
        System.out.println("set修改后第一本书的title为：" + JSONPath.eval(json, "$.store.book[0].title"));
        System.out.println(JSONPath.eval(json,"$.store.book"));
        System.out.println("--------fengexian--------");

        JSONPath.arrayAdd(json, "$.store.bicycle", "{'value':13}", "{'test':13}");
        System.out.println("arrayAdd后bicycle所有属性：" + JSONPath.eval(json, "$.store.bicycle"));

        System.out.println("判断是否包含{'value':13}:" + JSONPath.contains(json, "{'value':13}"));

        System.out.println("删除{'value':13}：" + JSONPath.remove(json, "$.store.bicycle[1]"));
        System.out.println("删除{'value':13}后bicycle所有属性：" + JSONPath.eval(json, "$.store.bicycle"));

       /* Set<String> objects = (Set<String>) JSONPath.keySet(json, "$.store");
        for (String key : objects) {
            System.out.println(key + " ");
        }*/

        System.out.println("--------分割线--------");
        String str2 = "{\"status\":1,\"info\":\"\",\"data\":{\"id\":\"1050187\",\"lastlogin\":1540882402," +
                "\"logins\":[\"exp\",\"logins+1\"],\"lastip\":\"11.203.168.2\",\"checktype\":1}}";

        //{"status":1,"info":"","data":{"id":"1050187","lastlogin":1540882402,"logins":["exp","logins+1"],"lastip":"11.203.168.2","checktype":1}}
        //将字符串json转换为json
        JSONObject json2 = JSON.parseObject(str2);

        System.out.println("str2的长度：" + JSONPath.eval(json2, "$.size()"));
        System.out.println("str2的长度：" + JSONPath.read(str2, "$.size()"));
        System.out.println("status为：" + JSONPath.eval(json2, "$.status"));
        System.out.println("info为：" + JSONPath.eval(json2, "$.info"));
        System.out.println("id为" + JSONPath.eval(json2, "$.data.id"));
        System.out.println("logins为：" + JSONPath.eval(json2, "$.data.logins"));


    }
}
