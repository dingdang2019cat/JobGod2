package com.ant.jobgod.jobgod.model.bean;

import java.io.Serializable;

/**
 * Created by Mr.Jude on 2015/7/7.
 */
public class Topic implements Serializable{
    private String id;
    private String img;
    private String subTitle;
    private String intro;
    private String title;

    public String getTitle() {
        return title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }
}
