package com.shangyi.business.bean;

/**
 * @author Sing
 * @date 2018/6/27 14:59
 * @desc 参数实体
 */
public class ParmsBean {
    public String KEY;
    public Object VALUE;

    public ParmsBean(String KEY, Object VALUE) {
        this.KEY = KEY;
        this.VALUE = VALUE;
    }

    @Override
    public String toString() {
        return "{" +
                "KEY:'" + KEY + '\'' +
                ", VALUE:" + VALUE +
                '}';
    }
}
