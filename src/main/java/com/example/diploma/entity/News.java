package com.example.diploma.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String dscrShort;
    private String urlPage;
    private String mainPhotoPath;

    public News() {
    }

    public News(String dscrShort, String urlPage, String mainPhotoPath) {
        this.dscrShort = dscrShort;
        this.urlPage = urlPage;
        this.mainPhotoPath = mainPhotoPath;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDscrShort() {
        return dscrShort;
    }

    public void setDscrShort(String dscrShort) {
        this.dscrShort = dscrShort;
    }

    public String getUrlPage() {
        return urlPage;
    }

    public void setUrlPage(String urlPage) {
        this.urlPage = urlPage;
    }

    public String getMainPhotoPath() {
        return mainPhotoPath;
    }

    public void setMainPhotoPath(String mainPhotoPath) {
        this.mainPhotoPath = mainPhotoPath;
    }
}
