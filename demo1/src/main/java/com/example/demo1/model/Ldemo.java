package com.example.demo1.model;

import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "course")
public class Ldemo {	
	
    public Ldemo() {
		super();
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    public void setId(long id) {
		this.id = id;
	}

	@Column(name = "course_name")
    private String courseName;

    @JsonManagedReference
    @OneToOne(fetch = FetchType.EAGER,mappedBy="ldemo",cascade = CascadeType.ALL)
    private LCourseDesc courseDesc;
    
    
    
    public Ldemo(String courseName, LCourseDesc courseDesc, String competency, int time, String status) {
	
		this.courseName = courseName;
		this.courseDesc = courseDesc;
		this.competency = competency;
		this.time = time;
		this.status = status;
	}

	public LCourseDesc getCourseDesc() {
		return courseDesc;
	}

	public void setCourseDesc(LCourseDesc courseDesc) {
		this.courseDesc = courseDesc;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	@Column(name = "competency")
    private String competency;

    @Column(name = "learning_time")
    private int time;
    
    @Column(name = "status")
    private String status;

    public long getId(){
        return id;
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
