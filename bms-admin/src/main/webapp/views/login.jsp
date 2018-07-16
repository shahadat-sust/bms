<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
    <head>
    	<title>BMS - Admin Login</title>
        <meta name="description" content="BMS - Admin Login">
        
        <%@include file="includes/metadata.jsp" %>
        <%@include file="includes/appicons.jsp" %>
        <%@include file="includes/styles.jsp" %>
    </head>
    <body>
        <div id="page-container">

            <!-- Main Container -->
            <main id="main-container">

                <!-- Page Content -->
                <div class="row no-gutters justify-content-center bg-body-dark">
                    <div class="hero-static col-sm-10 col-md-8 col-xl-6 d-flex align-items-center p-2 px-sm-0">
                        <!-- Sign In Block -->
                        <div class="block block-rounded block-transparent block-fx-pop w-100 mb-0 overflow-hidden bg-image" style="background-image: url('resources/media/photos/photo20@2x.jpg');">
                            <div class="row no-gutters">
                                <div class="col-md-6 order-md-1 bg-white">
                                    <div class="block-content block-content-full px-lg-5 py-md-5 py-lg-6">
                                        <!-- Header -->
                                        <div class="mb-2 text-center">
                                            <a class="link-fx font-w700 font-size-h1" href="#">
                                                <span class="text-dark">BMS</span>&nbsp;<span class="text-primary">Admin</span>
                                            </a>
                                            <p class="text-uppercase font-w700 font-size-sm text-muted">Sign In</p>
                                        </div>
                                        <!-- END Header -->

                                        <!-- Sign In Form -->
                                        <!-- jQuery Validation (.js-validation-signin class is initialized in js/pages/op_auth_signin.js) -->
                                        <!-- For more info and examples you can check out https://github.com/jzaefferer/jquery-validation -->
                                        <c:url var="doLogin"  value="/doLogin" />
                                        <form:form class="js-validation-signin" action='${doLogin}' method="post" modelAttribute="userForm">
                                        	<spring:hasBindErrors name="userForm">
												<c:forEach var="error" items="${errors.allErrors}">
													<div class="invalid-feedback invalid-feedback-common animated fadeIn"><spring:message message="${error}"/></div>
												</c:forEach>
										    </spring:hasBindErrors>
                                            <div class="form-group">
                                                <input type="text" class="form-control form-control-alt" id="login-username" name="username" placeholder="Username">
                                            </div>
                                            <div class="form-group">
                                                <input type="password" class="form-control form-control-alt" id="login-password" name="password" placeholder="Password">
                                            </div>
                                            <div class="form-group">
                                                <button type="submit" class="btn btn-block btn-hero-primary">
                                                    <i class="fa fa-fw fa-sign-in-alt mr-1"></i> Sign In
                                                </button>
                                            </div>
                                        </form:form>
                                        <!-- END Sign In Form -->
                                    </div>
                                </div>
                                <div class="col-md-6 order-md-0 bg-primary-dark-op d-flex align-items-center">
                                    <div class="block-content block-content-full px-lg-5 py-md-5 py-lg-6">
                                        <div class="media">
                                            <a class="img-link mr-3" href="javascript:void(0)">
                                                <img class="img-avatar img-avatar-thumb" src="resources//media/avatars/avatar10.jpg" alt="">
                                            </a>
                                            <div class="media-body">
                                                <p class="text-white font-w600 mb-1">
                                                    Amazing platform for online hotel bookig!
                                                </p>
                                                <a class="text-white-75 font-w600" href="javascript:void(0)">By SourceEngines Ltd.</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- END Sign In Block -->
                    </div>
                </div>
                <!-- END Page Content -->

            </main>
            <!-- END Main Container -->
        </div>
        <!-- END Page Container -->

        <%@include file="includes/scripts.jsp" %>

        <!-- Page JS Plugins -->
        <script type="text/javascript" src="<c:url value="resources/js/plugins/jquery-validation/jquery.validate.min.js" />"></script>
        <!-- Page JS Code -->
        <script type="text/javascript" src="resources/js/pages/op_auth_signin.min.js"></script>
    </body>
</html>