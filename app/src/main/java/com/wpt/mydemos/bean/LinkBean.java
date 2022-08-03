package com.wpt.mydemos.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: wpt
 * Time: 2022/6/7
 *
 * @Desc：
 */
public class LinkBean {

    private String title;

    private List<LinkBean> list = new ArrayList<>();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<LinkBean> getList() {
        return list;
    }

    public void setList(List<LinkBean> list) {
        this.list = list;
    }
}
