package com.example.myapplication;

public class DataDTO {

    String name;
    String mbti;
    String birth;
    String call;

    int imageResource;




    public DataDTO(String name, String mbti, String birth, String call, int imageResource){

        this.name = name;
        this.mbti= mbti;
        this.birth = birth;
        this.call = call;
        this.imageResource=imageResource;


    }


    public int getImageResource(){

        return imageResource;

    }

    public void setImageResource(int imageResource){

        this.imageResource=imageResource;

    }


    public String getName(){

        return name;

    }
    public void setName(String name){

        this.name = name;
    }
    public String getMbti(){

        return mbti;

    }

    public void setMbti(String mbti){

        this.mbti = mbti;
    }

    public String getBirth(){

        return birth;
    }
    public void setBirth(String birth){

        this.birth = birth;
    }
    public String getCall(){

        return call;
    }

    public void setCall(String call){

        this.call = call;
    }


}