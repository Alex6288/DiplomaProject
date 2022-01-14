package com.example.diploma.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
public class ProductRating {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long quantityMark;
    private Long sumMark;
    private String av_mark;

    public ProductRating() {
    }

    public ProductRating(Long quantityMark, Long sumMark) {
        this.quantityMark = quantityMark;
        this.sumMark = sumMark;
        this.av_mark = String.format("%1$,.2f", Double.valueOf(sumMark / quantityMark));
    }

    public void setQuantityMark(Long quantityMark) {
        this.quantityMark = quantityMark;
    }

    public void setSumMark(Long sumMark) {
        this.sumMark = sumMark;
    }

    public void setAv_mark() {
        System.out.println();
        this.av_mark = String.format("%1$,.2f", (Double.valueOf(sumMark) / Double.valueOf(quantityMark)));
    }

    public Long getId() {
        return id;
    }

    public Long getQuantityMark() {
        return quantityMark;
    }

    public Long getSumMark() {
        return sumMark;
    }

    public String getAv_mark() {
        return av_mark;
    }

}
