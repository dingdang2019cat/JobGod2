package com.ant.jobgod.jobgod.model.bean;

/**
 * Created by Mr.Jude on 2015/7/7.
 */
public class JobDetail {
    private int id;
    private String bizId;
    private String title;
    private String intro;
    private String img;
    private String ask;
    private String moneyIntro;
    private long applyBeginTime;
    private long applyEndTime;
    private long jobBeginTime;
    private long jobEndTime;
    private String timeIntro;
    private String bizName;
    private String bizFace;
    private int bizAvgFeel;
    private int personCount;
    private String address;
    private int applyCount;
    private String contact;
    private String personCountIntro;
    private boolean posted;
    private boolean collected;
    private int commentCount;

    public JobBrief[] getRelative() {
        return relative;
    }

    public void setRelative(JobBrief[] relative) {
        this.relative = relative;
    }

    private JobBrief[] relative;

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setPersonCountIntro(String personCountIntro) {
        this.personCountIntro = personCountIntro;
    }

    public boolean isPosted() {
        return posted;
    }

    public void setPosted(boolean posted) {
        this.posted = posted;
    }

    public boolean isCollected() {
        return collected;
    }

    public void setCollected(boolean collected) {
        this.collected = collected;
    }

    public String getPersonCountIntro() {
        return personCountIntro;
    }

    public String getContact() {
        return contact;
    }

    public int getId() {
        return id;
    }



    public void setId(int id) {
        this.id = id;
    }

    public String getBizFace() {
        return bizFace;
    }

    public String getBizId() {
        return bizId;
    }

    public void setBizId(String bizId) {
        this.bizId = bizId;
    }

    public String getTimeIntro() {
        return timeIntro;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getAsk() {
        return ask;
    }

    public void setAsk(String ask) {
        this.ask = ask;
    }

    public String getMoneyIntro() {
        return moneyIntro;
    }

    public void setMoneyIntro(String moneyIntro) {
        this.moneyIntro = moneyIntro;
    }

    public long getApplyBeginTime() {
        return applyBeginTime;
    }

    public void setApplyBeginTime(long applyBeginTime) {
        this.applyBeginTime = applyBeginTime;
    }

    public long getApplyEndTime() {
        return applyEndTime;
    }

    public void setApplyEndTime(long applyEndTime) {
        this.applyEndTime = applyEndTime;
    }

    public long getJobBeginTime() {
        return jobBeginTime;
    }

    public void setJobBeginTime(long jobBeginTime) {
        this.jobBeginTime = jobBeginTime;
    }

    public long getJobEndTime() {
        return jobEndTime;
    }

    public void setJobEndTime(long jobEndTime) {
        this.jobEndTime = jobEndTime;
    }

    public int getPersonCount() {
        return personCount;
    }

    public void setPersonCount(int personCount) {
        this.personCount = personCount;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getApplyCount() {
        return applyCount;
    }
    public void setApplyCount(int applyCount) {
        this.applyCount = applyCount;
    }

    public int getBizAvgFeel() {
        return bizAvgFeel;
    }

    public void setBizAvgFeel(int bizAvgFeel) {
        this.bizAvgFeel = bizAvgFeel;
    }

    public String getBizName() {

        return bizName;
    }

    public void setBizName(String bizName) {
        this.bizName = bizName;
    }


    public void setTimeIntro(String timeIntro) {
        this.timeIntro = timeIntro;
    }

    public void setBizFace(String bizFace) {
        this.bizFace = bizFace;
    }
}
