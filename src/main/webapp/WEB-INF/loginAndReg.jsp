<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="custom" tagdir="/WEB-INF/tags" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
	<link rel="stylesheet" type="text/css" href="/css/style.css">
	<link rel="stylesheet" type="text/css" href="/css/loginAndRegStyle.css">
	<link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Ubuntu&display=swap" rel="stylesheet">
	<title>4Baggers</title>
</head>
<body>
 <div class="container">
 	<custom:header/>
    <div class="row justify-content-center">
    	<div class="col-md-6">
        	<div class="card my-3">    
            	<div class="card-header">
                	<h4 class="header-title">${renderRegisterForm ? "RegisterForm":"Login Form"}</h4>
            	</div>
                        
                <div class="card-body">
                	<form:form action="/users/login" method="post" modelAttribute="newLogin" style="${renderRegisterForm ? 'display: none;' : 'display: block;'}">
                    	<div class="mb-3">
                        	<form:label path="email" class="form-label">Email</form:label>
                            <form:errors path="email" class="text-danger"/>
                            <form:input path="email" type="email" class="form-control" placeholder="example@email.com"/>
                        </div>
                        <div class="mb-3">
                        	<form:label path="password" class="form-label">Password</form:label>
                            <form:errors path="password" class="text-danger"/>
                            <form:input path="password" type="password" class="form-control" placeholder="Enter your password"/>
                        </div>
                        <div class="text-end">
                        <button type="submit" class="btn btn-submit mb-3">Login</button>
                        </div>
                    </form:form>

                    <form:form class="register-form" action="/users/register" method="post" modelAttribute="newUser" enctype="multipart/form-data" style="${renderRegisterForm ? 'display: block;' : 'display: none;'}">
                    	<div class="mb-3">
                        	<form:label path="firstName" class="form-label">First Name</form:label>
                            <form:errors path="firstName" class="text-danger"/>
                            <form:input path="firstName" type="text" class="form-control" placeholder="Enter your first name"/>
                        </div>
                        <div class="mb-3">
                        	<form:label path="lastName" class="form-label">Last Name</form:label>
                            <form:errors path="lastName" class="text-danger"/>
                            <form:input path="lastName" type="text" class="form-control" placeholder="Enter your last name"/>
                        </div>
                        <div class="mb-3">
                        	<form:label path="dateOfBirth" class="form-label">Date of Birth</form:label>
                            <form:errors path="dateOfBirth" class="text-danger"/>
                            <form:input path="dateOfBirth" type="date" class="form-control" placeholder="Enter your date of birth" required="true"/>
                        </div>
                        <div class="mb-3">
                                <form:label path="email" for="regEmail" class="form-label">Email</form:label>
                                <form:errors path="email" class="text-danger"/>
                                <form:input path="email" type="email" id="regEmail" class="form-control" placeholder="Enter your email"/>
                        </div>
                        <div class="mb-3">
                        	<form:label path="profilePictureFileUrl" for="formFile" class="form-label">Profile Image (optional)</form:label>
                        	<form:errors path="profilePictureFileUrl" class="text-danger"/>
                        	<input name="profilePictureFileUrlForm" class="form-control" type="file" id="formFile"/>
                        </div>
                        <div class="mb-3">
                                <form:label path="password"  for="regPassword" class="form-label">Password</form:label>
                                <form:errors path="password" class="text-danger"/>
                                <form:input path="password" type="password" id="regPassword" class="form-control" placeholder="Enter your password"/>
                        </div>
                        <div class="mb-3">
                                <form:label path="confirmPassword" class="form-label">Confirm Password</form:label>
                                <form:errors path="confirmPassword" class="text-danger"/>
                                <form:input path="confirmPassword" type="password" class="form-control" placeholder="Confirm your password"/>
                        </div>
                        <button type="submit" class="btn btn-submit mb-3">Register</button>
                    </form:form>
                        
                    <div class="card-footer foot-register rounded" style="${renderRegisterForm ? 'display: none;' : 'display: block;'}">
                    	Register for an account? <a href="#" class="link link-register">Register</a>
                    </div>
                        
                    <div class="card-footer foot-login rounded" style="${renderRegisterForm ? 'display: block;' : 'display: none;'}">
                    	Login to your account <a href="#" class="link link-login">Login</a>
                    </div>
                    
               	</div>
                        
                <div class="card-footer">
                	&copy; 2023 | All rights reserved | eLomDotCom
                </div>
            </div>
        </div>
    </div>
</div>
<script>

	// Get the register form, the login form
	const registerForm = document.querySelector(".register-form");
	const loginForm = document.querySelector(".card-body form");
	
	// Get the login link and the register link
	const loginLink = document.querySelector(".link-login");
	const registerLink = document.querySelector(".link-register");
	
	// Get the footer login and the register link
	const footerLoginLink = document.querySelector(".foot-login");
	const footerRegisterLink = document.querySelector(".foot-register");
	        
	const headerTitle = document.querySelector(".header-title");
	
	// When the register link is clicked, show the register form
	registerLink.addEventListener("click", () => {
    	loginForm.style.display = "none";
        footerRegisterLink.style.display = "none";
        footerLoginLink.style.display = "block";
        registerForm.style.display = 'block';
        headerTitle.textContent = "Register Form";});

    // Hide registration form when the Close button is clicked
    loginLink.addEventListener('click', () => {
        loginForm.style.display = 'block';
        registerForm.style.display = 'none';
        footerRegisterLink.style.display = "block";
        footerLoginLink.style.display = "none"
        headerTitle.textContent = "Login Form";});
</script>
</body>
</html>