package com.wpt.mydemos.packer;

import com.multilevel.treelist.Node;

/**
 * Author: wpt
 * Time: 2022/5/13
 *
 * @Desc：
 */
public class TreeBean extends Node {
    public TreeBean(int id, int pId, String name) {
        setId(id);
        setpId(pId);
        setName(name);
    }

    public TreeBean(int id, int pId, String name, TreeBean bean) {
        setId(id);
        setpId(pId);
        setName(name);
        this.bean = bean;
    }
}
