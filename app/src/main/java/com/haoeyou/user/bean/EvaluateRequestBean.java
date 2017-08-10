package com.haoeyou.user.bean;

/**
 * Created by Mou on 2017-08-09.
 */

public class EvaluateRequestBean {
    private String token;
    private String comment;
    private String order_id;
    private String score;

    public EvaluateRequestBean() {
    }

    public EvaluateRequestBean(String token, String order_id, String comment, String score) {
        this.comment = comment;
        this.score = score;
        this.order_id = order_id;
        this.token = token;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
