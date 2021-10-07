package com.example.demo1.controller;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.example.demo1.model.LCourseContent;
import com.example.demo1.model.LCourseDesc;
import com.example.demo1.model.Ldemo;
import com.example.demo1.model.User;
import com.example.demo1.repository.LdemoRepository;
import com.example.demo1.service.SecurityService;
import com.example.demo1.service.UserService;
import com.example.demo1.validator.UserValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@Controller
public class LdemoController {
	@Autowired
	LdemoRepository ldemoRepository;

	public LdemoRepository getLdemoRepository() {
		return ldemoRepository;
	}

	public void setLdemoRepository(LdemoRepository ldemoRepository) {
		this.ldemoRepository = ldemoRepository;
	}


	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public SecurityService getSecurityService() {
		return securityService;
	}

	public void setSecurityService(SecurityService securityService) {
		this.securityService = securityService;
	}

	public UserValidator getUserValidator() {
		return userValidator;
	}

	public void setUserValidator(UserValidator userValidator) {
		this.userValidator = userValidator;
	}


	@Autowired
	private UserService userService;

	@Autowired
	private SecurityService securityService;

	@Autowired
	private UserValidator userValidator;

	@GetMapping("/registration")
	public String registration(Model model) {
		model.addAttribute("userForm", new User());

		return "registration";
	}

	@PostMapping("/registration")
	public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult) {
		userValidator.validate(userForm, bindingResult);

		if (bindingResult.hasErrors()) {
			return "registration";
		}

		userService.save(userForm);

		securityService.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm());

		return "redirect:/welcome";
	}

	@GetMapping("/login")
	public String login(Model model, String error, String logout) {
		if (error != null)
			model.addAttribute("error", "Your username and password is invalid.");

		if (logout != null)
			model.addAttribute("message", "You have been logged out successfully.");

		return "login";
	}

	@GetMapping({"/", "/welcome"})
	public String welcome(Model model) {
		return "welcome";
	}

	@GetMapping("/course")
	public String getAllCourse(Model model){
		List<Ldemo> course = new ArrayList<Ldemo>();
		ldemoRepository.findAll().forEach(course::add);
		model.addAttribute("course", course);

		return "courses";
	}

	@GetMapping(value="/addCourse")
	public String addStudent(Model model) {

		Ldemo course = new Ldemo();
		model.addAttribute("course", course);

		return "addcourse";
	}


	@GetMapping("/course/{id}")
	public String getCourseById(@PathVariable("id") long id,Model model){
		Optional<Ldemo> courseData = ldemoRepository.findById(id);
		model.addAttribute("course", courseData.get());

		return "addcourse";



	}

	@PostMapping("/course")
	public String createCourse(@ModelAttribute("course") Ldemo course){
		Optional<Ldemo> courseData = ldemoRepository.findById(course.getId());
		if (courseData.isPresent()) {
			Ldemo _course = courseData.get();
			LCourseDesc desc=_course.getCourseDesc();
			List<LCourseContent> ccnt=desc.getCourse();
			course.getCourseDesc().setId(courseData.get().getCourseDesc().getId());
			course.getCourseDesc().getCourse().get(0).setCid(courseData.get().getCourseDesc().getCourse().get(0).getCid());
			_course.setCourseName(course.getCourseName());
			_course.setCourseDesc(course.getCourseDesc());
			_course.setCompetency(course.getCompetency());
			_course.setTime(course.getTime());
			_course.setStatus(course.getStatus());
			
			LCourseDesc lcoursedesc=_course.getCourseDesc();
			lcoursedesc.setLdemo(_course);
			List<LCourseContent> lcoursecontent=lcoursedesc.getCourse();
			for(LCourseContent e:lcoursecontent) {
				e.setCoursedesc(lcoursedesc);
			}
			
			ldemoRepository.save(_course);
			return "welcome"
					;}
		else
		{
			LCourseDesc lcoursedesc=course.getCourseDesc();
			lcoursedesc.setLdemo(course);
			List<LCourseContent> lcoursecontent=lcoursedesc.getCourse();
			for(LCourseContent e:lcoursecontent) {
				e.setCoursedesc(lcoursedesc);
			}
			ldemoRepository.save(course);

			return "welcome";
		}

	}

	@PostMapping("/course/{id}")
	public String updateCourse(@PathVariable("id") long id , @RequestBody Ldemo course){
		Optional<Ldemo> courseData = ldemoRepository.findById(id);
		if (courseData.isPresent()) {
			Ldemo _course = courseData.get();
			_course.setCourseName(course.getCourseName());
			_course.setCourseDesc(course.getCourseDesc());
			_course.setCompetency(course.getCompetency());
			_course.setTime(course.getTime());
			_course.setStatus(course.getStatus());
			ldemoRepository.save(_course);
			return "welcome";
		} 
		else {
			return "welcome";
		}
	}


	@GetMapping("/deleteCourse/{id}")
	public String deleteCourse(@PathVariable("id") long id){
		try{
			ldemoRepository.deleteById(id);
			return "welcome";
		} catch(Exception e){
			return "welcome";
		}
	}

	@DeleteMapping("/course")
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
			else
				return new ResponseEntity<>(course, HttpStatus.OK);
		} catch(Exception e){
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
