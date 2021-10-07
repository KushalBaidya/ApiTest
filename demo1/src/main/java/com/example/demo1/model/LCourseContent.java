package com.example.demo1.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "courseContent")
public class LCourseContent {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long cid;
	
	
	
	public void setCid(long cid) {
		this.cid = cid;
	}

	@Column(name = "content_name")
	private String contentName;
	
	@JsonBackReference
	@ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "id")
    private LCourseDesc coursedesc;

	public LCourseContent() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LCourseContent( String contentName, LCourseDesc coursedesc) {
		super();
		
		this.contentName = contentName;
		this.coursedesc = coursedesc;
	}

	public String getContentName() {
		return contentName;
	}

	public void setContentName(String contentName) {
		this.contentName = contentName;
	}

	public long getCid() {
		return cid;
	}


	public LCourseDesc getCoursedesc() {
		return coursedesc;
	}

	public void setCoursedesc(LCourseDesc coursedesc) {
		this.coursedesc = coursedesc;
	}

	
	
	

}
