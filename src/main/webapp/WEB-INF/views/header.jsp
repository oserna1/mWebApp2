<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="<c:url value='/static/css/bootstrap.min.css' />" rel="stylesheet"></link>
<title>MoodTracker</title>
</head>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <img alt="Mood" src="<c:url value='/static/images/mood.png' />" width="27" height="27">
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarNav">
    <ul class="navbar-nav">
      <li class="nav-item active">
        <a id="homeLink" class="nav-link" href="<c:url value='/' />">Home</a>
      </li>
      <li class="nav-item">
        <a id="loginLink" class="nav-link" href="<c:url value='/login/' />">Login</a>
      </li>
      <li class="nav-item">
        <a id="trackLink" class="nav-link" href="<c:url value='/track/' />">Track</a>
      </li>
    </ul>
  </div>
</nav>
