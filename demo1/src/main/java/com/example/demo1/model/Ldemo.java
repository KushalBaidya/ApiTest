package com.example.demo1.model;

import javax.persistence.*;

@Entity
@Table(name = "course")
public class Ldemo {	
	
    public Ldemo() {
		super();
	}

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "course_name")
    private String courseName;

    public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(name = "competency")
    private String competency;

    @Column(name = "learning_time")
    private int time;
    
    @Column(name = "status")
    private String status;

    public Ldemo(String course_name, String competency, int time, String status){
        this.courseName = course_name;
        this.competency = competency;
        this.time = time;
        this.status = status;
    }

    public long getId(){
        return id;
    }
    public String getName(){
        return courseName;
    }
    public void setName(String course_name){
        this.courseName = course_name;
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
        return "Course [id = " + id + ", course name = " + courseName + ", competency = " + competency + ", learning time = " + time + ", status = " + status + "]";
    }

}
