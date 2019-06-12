<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<%@ include file = "header.jsp" %>
 
	<body>

	
		<div class="container col-md-4 col-md-offset-4" style="margin-top:5%">
		  <form:form commandName="user" method="POST" class="px-4 py-3" action="/mWebApp2/validateCreate">
		  	<div class="form-group">
		      <label for="exampleDropdownFormUsername">Username</label>
		      <form:input path="username" name="username" type="text" class="form-control" id="exampleDropdownFormUsername" placeholder="Username"/>
		      <form:errors path="username" />
		    </div>
		    <div class="form-group">
		      <label for="exampleDropdownFormEmail1">Email</label>
		      <form:input path="email" name="email" type="email" class="form-control" id="exampleDropdownFormEmail1" placeholder="email@example.com"/>
		      <form:errors path="email"/>
		    </div>
		    <div class="form-group">
		      <label for="exampleDropdownFormPassword1">Password</label>
		      <form:input path="password" name="password" type="password" class="form-control" id="exampleDropdownFormPassword1" placeholder="Password"/>
		      <form:errors path="password" cssClass="error" />
		    </div>
		    <button type="submit" class="btn btn-primary">Sign in</button>
		  </form:form>
		</div>
	
		<script src="<c:url value='/static/js/frameworks/jquery-3.4.1.js' />"></script>
	    <script src="<c:url value='/static/js/frameworks/popper.min.js' />"></script>
	    <script src="<c:url value='/static/js/frameworks/bootstrap.bundle.min.js' />"></script>
   </body>
</html>