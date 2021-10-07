<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>     
    
<!DOCTYPE html>
<html>
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 <title>Course List</title>
  <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
  <script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</head>
<body>
  <div class="container">
  <spring:url value="/course" var="addURL" />
  <h2>Add Student</h2>
  <form:form modelAttribute="course" method="post" action="${addURL}" cssClass="form" >
   <form:hidden path="id"/>
   <div class="form-group">
    <label>Course Id</label>
    <form:input path="id" cssClass="form-control" id="id" />
   </div>
   <div class="form-group">
    <label>Course Name</label>
    <form:input path="courseName" cssClass="form-control" id="courseName" />
   </div>
   <div class="form-group">
    <label>Competency</label>
    <form:input path="competency" cssClass="form-control" id="courseName" />
   </div>
   <div class="form-group">
    <label>Time</label>
    <form:input path="time" cssClass="form-control" id="courseName" />
   </div>
   <div class="form-group">
    <label>Status</label>
    <form:input path="status" cssClass="form-control" id="courseName" />
   </div>
   <div class="form-group">
    <label>Completion Point</label>
    <form:input path="courseDesc.completionPoint" cssClass="form-control" id="completionPoint" />
   </div>
   <div class="form-group">
    <label>Rating</label>
    <form:input path="courseDesc.rating" cssClass="form-control" id="rating" />
   </div>
   <div class="form-group">
    <label>Content Name</label>
    <form:input path="courseDesc.course[0].contentName" cssClass="form-control" id="rating" />
   </div>
   <button type="submit" class="btn btn-success">Add Course</button>
  </form:form>
  
 </div>
</body>
</html>