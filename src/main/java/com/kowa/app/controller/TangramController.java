package com.kowa.app.controller;

import com.enation.app.mobile.tangram.core.Tangram;
import com.enation.app.mobile.tangram.core.TangramFactory;
import com.enation.app.mobile.tangram.model.HorizontalViewStyleBean;
import com.enation.app.mobile.tangram.model.TangramItemBean;
import com.enation.app.mobile.tangram.utils.TangramUtils;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/test")
public class TangramController {

    /**
     * 检查是否登录
     * @return json
     * @throws IOException json解析异常
     */
    @RequestMapping(value = "/tangram", method = RequestMethod.GET)
    @ResponseBody
    public String isLogin() {
       return TangramFactory.create(new Tangram.JsonTransformation() {
            @Override
            public String event(Object tangram) {
                return new Gson().toJson(tangram);
            }
        }).with(new Tangram.TangramWith() {
            @Override
            public void event(Tangram self) {
                ArrayList<TangramItemBean> list = new ArrayList<>();
                for (int i = 0; i < 10; i++) {
                    TangramItemBean itemBean = new TangramItemBean();
                    itemBean.setActionType(1);
                    Map<String,String> map = new HashMap<>();
                    map.put("parent","BannerView");
                    itemBean.setBody(new Gson().toJson(map));
                    itemBean.setImageurl("https://pic2.zhimg.com/fd4d0ccce5a3469a4a8ad647112bb6a6_m.jpg");
                    itemBean.setTitle("Title");
                    itemBean.setType("1");
                    list.add(itemBean);
                }
                HorizontalViewStyleBean bean = TangramUtils.getCarouselStyle("#ff00000","#000000","5");
                bean.setPageRatio(null);
                bean.setScrollMarginLeft(null);
                bean.setScrollMarginRight(null);
                self.createHorizontalViewNode(bean,list);
            }
        }).toJson();
    }
}
