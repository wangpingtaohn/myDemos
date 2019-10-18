package com.wpt.mydemos.recycler;

/**
 * author : wpt
 * date   : 2019-10-14 09:36
 * desc   :
 */
public class ItemBean {

    public int resId;

    public boolean isDelete;

    public Test test = new Test();

    public static class Test{
        public Test02 test02 = new Test02();
    }

    public static class Test02{
        public boolean isMid;
    }

}
