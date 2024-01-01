package com.example.myapplication;

public class ReviewData {
    private String movie; //선택한 영화
    private String review; //리뷰
    private float score; //별점

    public ReviewData(String movie, String review, float score){
        this.movie = movie;
        this.review = review;
        this.score = score;
    }
    public String getMovie(){ return movie; }

    public void setMovie(String movie){
        this.movie = movie;
    }

    public String getReview(){
        return review;
    }

    public void setReview(String review){ this.review = review; }
    public float getScore(){
        return score;
    }

    public void setScore(float score){ this.score = score; }

}
