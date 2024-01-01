package com.example.myapplication;

public class ReviewData {
    private String movie; //선택한 영화
    private String review; //리뷰

    public ReviewData(String movie, String review){
        this.movie = movie;
        this.review = review;
    }
    public String getMovie(){ return movie; }

    public void setMovie(String movie){
        this.movie = movie;
    }

    public String getReview(){
        return review;
    }

    public void setReview(String review){ this.review = review; }

}
