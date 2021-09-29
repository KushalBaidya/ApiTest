package com.example.demo1.model;

import javax.persistence.*;

@Entity
@Table(name = "course")
public class Ldemo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "course name")
    private String course_name;

    @Column(name = "competency")
    private String competency;

    @Column(name = "learning time")
    private int time;
    
    @Column(name = "status")
    private String status;

    public Ldemo(String course_name, String competency, int time, String status){
        this.course_name = course_name;
        this.competency = competency;
        this.time = time;
        this.status = status;
    }

    public long getId(){
        return id;
    }
    public String getName(){
        return course_name;
    }
    public void setName(String course_name){
        this.course_name = course_name;
    }
    public String getCompetency(){
        return competency;
    }
    public void setCompetency(String competency){
        this.competency = competency;
    }
    public int getTime(){
        return time;
    }
    public void setTime(int time){
        this.time = time;
    }
    public String getStatus(){
        return status;
    }
    public void setStatus(String status){
        this.status = status;
    }

    @Override
    public String toString(){
        return "Course [id = " + id + ", course name = " + course_name + ", competency = " + competency + ", learning time = " + time + ", status = " + status + "]";
    }

}
