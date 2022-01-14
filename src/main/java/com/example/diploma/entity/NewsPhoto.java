package com.example.diploma.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
public class NewsPhoto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String photoPath;
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private News news;

    public NewsPhoto() {
    }

    public NewsPhoto(String fotoPath, News news) {
        this.photoPath = fotoPath;
        this.news = news;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public void setNews(News news) {
        this.news = news;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public News getNews() {
        return news;
    }
}
