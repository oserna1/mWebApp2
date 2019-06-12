<%@ include file = "header.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>   
<body ng-app="myApp" class="ng-cloak">
      <div class="container" ng-controller="MoodController as ctrl">
          <div class="panel panel-default">
              <div class="panel-heading"><span class="lead"></span></div>
              <div class="formcontainer">
                  <form ng-submit="ctrl.submitUser()" name="myForm" class="form-horizontal">
                      <input type="hidden" ng-model="ctrl.user.id" />
                      <div class="row">
                          <div class="form-group col-md-12">
                              <label class="col-md-2 control-lable" for="file">Username</label>
                              <div class="col-md-7">
                                  <input type="text" ng-model="ctrl.user.username" name="uname" class="username form-control input-sm" placeholder="Enter your name" required ng-minlength="3"/>
                                  <div class="has-error" ng-show="myForm.$dirty">
                                      <span ng-show="myForm.uname.$error.required">This is a required field</span>
                                      <span ng-show="myForm.uname.$error.minlength">Minimum length required is 3</span>
                                      <span ng-show="myForm.uname.$invalid">This field is invalid </span>
                                  </div>
                              </div>
                          </div>
                      </div>

                      <div class="row">
                          <div class="form-group col-md-12">
                              <label class="col-md-2 control-lable" for="file">Email</label>
                              <div class="col-md-7">
                                  <input type="email" ng-model="ctrl.user.email" name="email" class="email form-control input-sm" placeholder="Enter your Email" required/>
                                  <div class="has-error" ng-show="myForm.$dirty">
                                      <span ng-show="myForm.email.$error.required">This is a required field</span>
                                      <span ng-show="myForm.email.$invalid">This field is invalid </span>
                                  </div>
                              </div>
                          </div>
                      </div>

                      <div class="row">
                          <div class="form-actions floatRight">
                              <input type="submit"  value="{{!ctrl.user.id ? 'Add' : 'Update'}}" class="btn btn-primary btn-sm" ng-disabled="myForm.$invalid">
                              <button type="button" ng-click="ctrl.resetUser()" class="btn btn-warning btn-sm" ng-disabled="myForm.$pristine">Reset Form</button>
                          </div>
                      </div>
                  </form>
              </div>
          </div>
          <div class="panel panel-default">
                <!-- Default panel contents -->
              <div class="panel-heading"><span class="lead">List of Users </span></div>
              <div class="tablecontainer">
                  <table class="table table-hover">
                      <thead>
                          <tr>
                              <th>ID.</th>
                              <th>Username</th>
                              <th>Email</th>
                              <th width="20%"></th>
                          </tr>
                      </thead>
                      <tbody>
                          <tr ng-repeat="u in ctrl.users">
                              <td><span ng-bind="u.id"></span></td>
                              <td><span ng-bind="u.username"></span></td>
                              <td><span ng-bind="u.email"></span></td>
                              <td>
                              <button type="button" ng-click="ctrl.editUser(u.id)" class="btn btn-success custom-width">Edit</button>  <button type="button" ng-click="ctrl.removeUser(u.id)" class="btn btn-danger custom-width">Remove</button>
                              </td>
                          </tr>
                      </tbody>
                  </table>
              </div>
          </div>
      </div>
      
      <script src="<c:url value='/static/js/frameworks/angular.min.js' />"></script>
      <script src="<c:url value='/static/js/frameworks/jquery-3.4.1.js' />"></script>
      <script src="<c:url value='/static/js/frameworks/popper.min.js' />"></script>
      <script src="<c:url value='/static/js/frameworks/bootstrap.bundle.min.js' />"></script>
      <script src="<c:url value='/static/js/app.js' />"></script>
      <script src="<c:url value='/static/js/service/user_service_admin.js' />"></script>
      <script src="<c:url value='/static/js/service/mood_service.js' />"></script>
      <script src="<c:url value='/static/js/controller/mood_controller.js' />"></script>
    </body>
 </head>