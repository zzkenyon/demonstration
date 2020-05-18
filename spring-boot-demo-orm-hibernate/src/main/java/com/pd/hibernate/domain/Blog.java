package com.pd.hibernate.domain;

import javax.persistence.*;

@Entity
@Table(name = "blog")
public class Blog {
    @Id
    @Column(name = "bid")
    private Integer bid;

    @Column(name = "name")
    private String name;

    @Column(name = "author_id")
    private Integer authorId;

    public Integer getBid() {
        return bid;
    }

    public void setBid(Integer bid) {
        this.bid = bid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }
}