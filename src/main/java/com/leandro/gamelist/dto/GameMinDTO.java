package com.leandro.gamelist.dto;

import com.leandro.gamelist.entities.Game;

public class GameMinDTO {

    private Long id;
    private String title;
    private Integer year;
    private String genre;
    private String platforms;
    private Double score;
    private String imgUrl;

    public GameMinDTO() {
        //TODO Auto-generated constructor stub
    }

    

    public GameMinDTO(Game entity) {
        id= entity.getId();
        title = entity.getTitle();
        year = entity.getYear();
        genre = entity.getGenre();
        platforms = entity.getGenre();
        score = entity.getScore();
        imgUrl = entity.getImgUrl();
    }



    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public Integer getYear() {
        return year;
    }
    public void setYear(Integer year) {
        this.year = year;
    }
    public String getGenre() {
        return genre;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }
    public String getPlatforms() {
        return platforms;
    }
    public void setPlatforms(String platforms) {
        this.platforms = platforms;
    }
    public Double getScore() {
        return score;
    }
    public void setScore(Double score) {
        this.score = score;
    }
    public String getImgUrl() {
        return imgUrl;
    }
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    

    
}
