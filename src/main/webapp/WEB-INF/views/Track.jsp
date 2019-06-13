<%@ include file = "header.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>   

<body ng-app="myApp">
	<div ng-controller="MoodController as ctrl" class="container">
			<div class="dropdown">
				<button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
					Users
				</button>
				<div class="dropdown-menu">
					<div ng-repeat="u in ctrl.users" ng-click="ctrl.setUid(u.id); ctrl.fetchMoodsByUid(u.id)">
						<p ng-bind="u.username" class="dropdown-item"></p>
					</div>
				</div>	
			</div>
          <div class="panel panel-default">
              <div class="panel-heading"><span class="lead"></span></div>
              <div class="formcontainer">
                  <form ng-submit="ctrl.submit()" name="myForm" class="form-horizontal">
                      <div class="row">
                          <div class="form-group col-md-12">
                              <label class="col-md-2 control-lable" for="file">MoodRange</label>
                              <div class="col-md-7">
                                  <input type="text" ng-model="ctrl.mood.moodRange" name="range" class="range form-control input-sm" placeholder="Enter mood rating" required/>
                              </div>
                          </div>
                      </div>

                      <div class="row">
                          <div class="form-group col-md-12">
                              <label class="col-md-2 control-lable" for="file">Description</label>
                              <div class="col-md-7">
                                  <input type="text" ng-model="ctrl.mood.description" name="description" class="description form-control input-sm" placeholder="Enter description" required/>
                                  <div class="has-error" ng-show="myForm.$dirty">
                                  </div>
                              </div>
                          </div>
                      </div>

                      <div class="row">
                          <div class="form-actions floatRight">
                              <input type="submit"  value="{{!ctrl.mood.id ? 'Add' : 'Update'}}" class="btn btn-primary btn-sm" ng-disabled="myForm.$invalid">
                              <button type="button" ng-click="ctrl.reset()" class="btn btn-warning btn-sm" ng-disabled="myForm.$pristine">Reset Form</button>
                          </div>
                      </div>
                  </form>
              </div>
          </div>
          <div class="panel panel-default">
                <!-- Default panel contents -->
              <div class="panel-heading"><span class="lead">List of Moods </span></div>
              <div class="tablecontainer">
                  <table class="table table-hover">
                      <thead>
                          <tr>
                              <th>ID.</th>
                              <th>MoodRange</th>
                              <th>Description</th>
                              <th>TimeStamp</th>
                              <th>User Id</th>
                              <th width="20%"></th>
                          </tr>
                      </thead>
                      <tbody>
                          <tr ng-repeat="m in ctrl.moods">
                              <td><span ng-bind="m.id"></span></td>
                              <td><span ng-bind="m.moodRange"></span></td>
                              <td><span ng-bind="m.description"></span></td>
                              <td><span ng-bind="m.ts"></span></td>
                              <td><span ng-bind="m.uid"></span></td>
                              <td>
                              <button type="button" ng-click="ctrl.edit(m.id)" class="btn btn-success custom-width">Edit</button>  <button type="button" ng-click="ctrl.remove(m.id)" class="btn btn-danger custom-width">Remove</button>
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
</html>