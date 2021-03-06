package com.example.demo1.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "courseDesc")
public class LCourseDesc {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
	
	public void setId(long id) {
		this.id = id;
	}

	@JsonManagedReference
	@OneToMany(fetch = FetchType.EAGER,mappedBy="coursedesc",cascade = CascadeType.ALL)
    private List<LCourseContent> course;   

	@Column(name = "completion_point")
    private String completionPoint;
	
	@Column(name ="rating")
	private float rating;
	
	@JsonBackReference
	@OneToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "refid")
    private Ldemo ldemo;
	

	public Ldemo getLdemo() {
		return ldemo;
	}


	public void setLdemo(Ldemo ldemo) {
		this.ldemo = ldemo;
	}


	public LCourseDesc() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public LCourseDesc(List<LCourseContent> course, String completionPoint, float rating,Ldemo ldemo) {
		super();
		this.course = course;
		this.completionPoint = completionPoint;
		this.rating = rating;
		this.ldemo=ldemo;
	}
	
//	  //bind to this
//    public List<LCourseContent> getCourseAsList(){
//        return new ArrayList<LCourseContent>(course);
//    }


	public long getId() {
		return id;
	}

	public List<LCourseContent> getCourse() {
		return course;
	}

	public void setCourse(List<LCourseContent> course) {
		this.course = course;
	}

	public String getCompletionPoint() {
		return completionPoint;
	}

	public void setCompletionPoint(String completionPoint) {
		this.completionPoint = completionPoint;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}
	

}
