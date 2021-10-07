<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>   
    
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
  <table class="table table-striped">
   <thead class="thead-dark">
    <th scope="row">ID</th>
    <th scope="row">Course Name</th>
    <th scope="row">Competency</th>
    <th scope="row">Time</th>
    <th scope="row">Status</th>
    <th scope="row">Completion Point</th>
    <th scope="row">Rating</th>
    <th scope="row">Content Name</th>
   </thead>
   <tbody>
    <c:forEach items="${course}" var="course" >
     <tr>
      <td>${course.id}</td>
      <td>${course.courseName}</td>
      <td>${course.competency}</td>
      <td>${course.time}</td>
      <td>${course.status}</td>
      <td>${course.courseDesc.completionPoint}</td>
      <td>${course.courseDesc.rating}</td>
      <td>${course.courseDesc.course[0].contentName}</td>
      <td>
       <spring:url value="/course/${course.id}" var="editURL" />
       <a class="btn btn-info" href="${editURL}" role="button" >Update</a>
      </td>
      <td>
       <spring:url value="/deleteCourse/${course.id}" var="deleteURL" />
       <a class="btn btn-danger" href="${deleteURL}" role="button" >Delete</a>
      </td>
     </tr>
    </c:forEach>
   </tbody>
  </table>
  <spring:url value="/addCourse" var="addURL" />
  <a class="btn btn-success" href="${addURL}" role="button" >Add New Course</a>
 </div>
</body>
</html>