package com.chenchen.factory.gen;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Administrator on 2017/9/6.
 */
@Entity
public class Search {
    @Id private long id;
    private String content;
    private long timastamp;
    public long getTimastamp() {
        return this.timastamp;
    }
    public void setTimastamp(long timastamp) {
        this.timastamp = timastamp;
    }
    public String getContent() {
        return this.content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
    @Generated(hash = 2061162423)
    public Search(long id, String content, long timastamp) {
        this.id = id;
        this.content = content;
        this.timastamp = timastamp;
    }
    @Generated(hash = 1644193961)
    public Search() {
    }
}
