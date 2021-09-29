package com.example.demo1.controller;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.demo1.model.Ldemo;
import com.example.demo1.repository.LdemoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class LdemoController {
    @Autowired
    LdemoRepository ldemoRepository;

    public LdemoRepository getLdemoRepository() {
		return ldemoRepository;
	}

	public void setLdemoRepository(LdemoRepository ldemoRepository) {
		this.ldemoRepository = ldemoRepository;
	}

	@GetMapping("/course")
    public ResponseEntity<List<Ldemo>> getAllCourse(@RequestParam(required = false) String course_name){
        try{
            List<Ldemo> course = new ArrayList<Ldemo>();
            if(course_name == null)
                ldemoRepository.findAll().forEach(course::add);
            else
                ldemoRepository.findByCourseName(course_name).forEach(course::add);
            if(course.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(course, HttpStatus.OK);
        } catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/course/{id}")
    public ResponseEntity<Ldemo> getCourseById(@PathVariable("id") long id){
        Optional<Ldemo> courseData = ldemoRepository.findById(id);

        if (courseData.isPresent()){
            return new ResponseEntity<>(courseData.get(), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/course")
    public ResponseEntity<Ldemo> createCourse(@RequestBody Ldemo course){
        try{
            Ldemo _course = ldemoRepository.save(new Ldemo(course.getName(), course.getCompetency(), course.getTime(), course.getStatus()));
            return new ResponseEntity<>(_course, HttpStatus.CREATED);
        } catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/course/{id}")
    public ResponseEntity<Ldemo> updateCourse(@PathVariable("id") long id , @RequestBody Ldemo course){
        Optional<Ldemo> courseData = ldemoRepository.findById(id);
        if (courseData.isPresent()) {
            Ldemo _course = courseData.get();
            _course.setName(course.getName());
            _course.setCompetency(course.getCompetency());
            _course.setTime(course.getTime());
            _course.setStatus(course.getStatus());
            return new ResponseEntity<>(ldemoRepository.save(_course), HttpStatus.OK);
        } 
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/course/{id}")
    public ResponseEntity<HttpStatus> deleteCourse(@PathVariable("id") long id){
        try{
            ldemoRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @DeleteMapping("course")
    public ResponseEntity<HttpStatus> deleteAllCourses(){
        try{
            ldemoRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<Ldemo>> findByCourseName(String course_name){
        try{
            List<Ldemo> course = ldemoRepository.findByCourseName(course_name);
            if(course.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            
            return new ResponseEntity<>(course, HttpStatus.OK);
        } catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
