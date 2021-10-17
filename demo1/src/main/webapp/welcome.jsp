<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Create an account</title>
    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
  <div class="container">
    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <form id="logoutForm" method="POST" action="${contextPath}/logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>

        <h2>Welcome ${pageContext.request.userPrincipal.name} | <a onclick="document.forms['logoutForm'].submit()">Logout</a></h2>
    </c:if>
  </div>
  <div class="list-group">
  <a href="#" class="list-group-item list-group-item-action active">
    Greetings!
  </a>
</div>
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
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
  <script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>