package com.fuyi.shop.portal;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @ClassName AppTest
 * @Description TODO
 * @Author fuyi
 * @Date 2018/12/17 16:51
 * @Version 1.0
 */
@SpringBootTest
public class AppTest {

    @Test
    public void test2() {
        String evalResult = "{\"submit_info\":[{\"prod_list\":[{\"org_id\":\"201801528000000000\",\"doc_info\":\"家要温暖，更要安全。仅需50元，保障您的房屋安全\",\"prod_type_name\":\"车险2.9折起\",\"tag\":\"1\",\"prod_id\":\"201805280000200000\",\"logo_img\":\"http://hd.139xy.cn/img/insurance/logo/ax.png\",\"link_url\":\"https://car.95303.com/b2c?key=12gGxt%2F2AITkfs2OOPbb8%2FPg%3D%3D\",\"ord_name\":\"安心\",\"prod_name\":\"安心车险\"},{\"doc_info\":\"最高600万保额，癌症无免赔，全家共享免赔额，住院垫付出院报销\",\"org_id\":\"201805110000000000\",\"prod_type_name\":\"财务保障\",\"prod_id\":\"201805111000000000\",\"logo_img\":\"http://hd.139xy.cn/img/insurance/logo/cw.svg\",\"link_url\":\"https://emcs.pa18.com/product/p_map/index.html?appType=01&key=11805160000000127071#/\",\"ord_name\":\"平安\",\"prod_name\":\"百万家财险\"}],\"doc\":\"整体风险可控，爱与责任，这也是让我们全身心投入的唯一理由。爱与责任的延续 不单指生命的延续，而是经济生命的延续，建议优先关注财务风险\",\"label\":\"3-2\"},{\"prod_list\":[{\"org_id\":\"201801528000000000\",\"doc_info\":\"最高600万保额，癌症无免赔，全家共享免赔额，住院垫付出院报销\",\"prod_type_name\":\"车险2.9折起\",\"tag\":\"1\",\"prod_id\":\"201805280000200000\",\"logo_img\":\"http://hd.139xy.cn/img/insurance/logo/ax.png\",\"link_url\":\"https://car.95303.com/b2c?key=12gGxt%2F2AITkfs2OOPbb8%2FPg%3D%3D\",\"ord_name\":\"安心\",\"prod_name\":\"安心车险\"},{\"doc_info\":\"最高600万保额，癌症无免赔，全家共享免赔额，住院垫付出院报销\",\"org_id\":\"201805110000000000\",\"prod_type_name\":\"财务保障\",\"prod_id\":\"201805111000000000\",\"logo_img\":\"http://hd.139xy.cn/img/insurance/logo/cw.svg\",\"link_url\":\"https://emcs.pa18.com/product/p_map/index.html?appType=01&key=11805160000000127071#/\",\"ord_name\":\"平安\",\"prod_name\":\"百万家财险\"}],\"doc\":\"整体风险可控，关爱自己身体健康是为了陪心爱的人走的更长远，建议优先关注健康风险\",\"label\":\"1-1\"},{\"prod_list\":[{\"org_id\":\"201801528000000000\",\"doc_info\":\"9座以下均可投保；低保费，高保额，一张保单保整车人安全\",\"prod_type_name\":\"车险2.9折起\",\"tag\":\"1\",\"prod_id\":\"201805280000200000\",\"logo_img\":\"http://hd.139xy.cn/img/insurance/logo/ax.png\",\"link_url\":\"https://car.95303.com/b2c?key=12gGxt%2F2AITkfs2OOPbb8%2FPg%3D%3D\",\"ord_name\":\"安心\",\"prod_name\":\"安心车险\"}],\"doc\":\"整体风险可控，老司机走过的路比别人长，遇到风险概率也更高，建议优先关注意外风险\",\"label\":\"2-3\"}],\"result_doc\":\"整体风险可控，老司机走过的路比别人长，遇到风险概率也更高，建议优先关注意外风险\",\"label_info\":[{\"level\":\"低\",\"lab_val\":\"1\",\"level_val\":\"1\",\"lab\":\"健康风险\"},{\"level\":\"高\",\"lab_val\":\"2\",\"level_val\":\"3\",\"lab\":\"意外风险\"},{\"level\":\"中\",\"lab_val\":\"3\",\"level_val\":\"2\",\"lab\":\"财产风险\"}]}";
        JSONObject jo = JSONObject.parseObject(evalResult);

        Object labelInfo = jo.get("label_info");

        JSONArray labelJsonArray = JSONArray.parseArray(labelInfo.toString());
        System.out.println(1);
    }
}
