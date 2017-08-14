package com.kowa.app.po;

import javax.persistence.*;

/**
 *
 * 文章表Po类
 * @Auther yumengshuai【kely】
 * @Date   17/7/4 下午1:37
 *
 */
@Entity
@Table(name = "article")
public class ArticlePo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "user_id")
    private int userid;

    @Column(name = "category")
    private int category;



}
