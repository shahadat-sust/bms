<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.bms.common.Constants" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
    <head>
        <title>BMS - Create User</title>
        <meta name="description" content="BMS - User List">
        <%@include file="../includes/metadata.jsp" %>
        <%@include file="../includes/appicons.jsp" %>
        <%@include file="../includes/styles.jsp" %>
        
        <link rel="stylesheet" href="<c:url value="/resources/js/plugins/bootstrap-datepicker/css/bootstrap-datepicker3.min.css"/>" />
    </head>
    <body>
        <%@include file="../includes/header.jsp" %>
        <%@include file="../includes/sidebarleft.jsp" %>
		<%@include file="../includes/sidebarright.jsp" %>

        <!-- Main Container -->
        <main id="main-container">

			<!-- Hero -->
             <div class="bg-body-light">
                 <div class="content content-full">
                     <div class="d-flex flex-column flex-sm-row justify-content-sm-between align-items-sm-center">
                         <h1 class="flex-sm-fill font-size-h2 font-w400 mt-2 mb-0 mb-sm-2">Create User</h1>
                         <nav class="flex-sm-00-auto ml-sm-3" aria-label="breadcrumb">
                             <ol class="breadcrumb">
                                 <li class="breadcrumb-item">User Management</li>
                                 <li class="breadcrumb-item active" aria-current="page">Create User</li>
                             </ol>
                         </nav>
                     </div>
                 </div>
             </div>
             <!-- END Hero -->

			<!-- Page Content -->
            <div class="content">
          	     <!-- User Form -->
          	     <c:url var="saveuser" value="/saveuser" />
                 <form:form id="formUserModify" action="${saveuser}" method="post" modelAttribute="userForm">
                     <div class="block block-rounded block-bordered">
                         <div class="block-header block-header-default">
                             <h3 class="block-title">User Create - Form</h3>
                             <div class="block-options">
                             	 <form:hidden id="val-userId" path="id"/>
                             	 <form:hidden id="val-userProfileId" path="userProfileData.id"/>
                             	 <form:hidden id="val-emailAddressId" path="emailAddressDatas[0].id"/>
                             	 <form:hidden id="val-phoneNumberId" path="phoneNumberDatas[0].id"/>
                             	 <form:hidden id="val-postalAddressId" path="postalAddressDatas[0].id"/>
                                 <button type="submit" class="btn btn-sm btn-light" onclick="return false;">
                                     <i class="fa fa-fw fa-check"></i> Submit
                                 </button>
                                 <button type="reset" class="btn btn-sm btn-light">
                                 	<i class="fa fa-fw fa-undo"></i> Reset
								</button>
                             </div>
                         </div>
                         <div class="block-content block-content-full">
                             <div class="">
                             	<!-- Security Info -->
                                 <h2 class="content-heading">Basic Info</h2>
                                 <div class="row items-push">
                                     <div class="col-lg-4">
                                         <p class="text-muted">
                                             Enter user basic information like first name, last name, gender, birthday, security number etc.
                                         </p>
                                     </div>
                                     <div class="col-lg-8 col-xl-5">
                                         <div class="form-group">
                                             <label for="val-firstName">First Name <span class="text-danger">*</span></label>
                                             <form:input cssClass="form-control" id="val-firstName" path="userProfileData.firstName" placeholder="Enter a first name.."/>
                                         </div>
                                         <div class="form-group">
                                             <label for="val-lastName">Last Name <span class="text-danger">*</span></label>
                                             <form:input cssClass="form-control" id="val-lastName" path="userProfileData.lastName" placeholder="Enter a last name.."/>
                                         </div>
                                         <div class="form-group">
                                              <label for="val-gender">Gender <span class="text-danger">*</span></label>
                                              <form:select cssClass="form-control" id="val-gender" path="userProfileData.gender">
                                              	<form:option value="0" label="Please select"></form:option>
                                              	<form:option value="<%= Constants.MALE %>" label="Male"></form:option>
                                              	<form:option value="<%= Constants.FEMALE %>" label="Female"></form:option>
                                              </form:select> 
                                         </div>
                                         <div class="form-group">
                                             <label for="val-birthDay">Birthday </label>
                                             <form:input cssClass="js-datepicker form-control" id="val-birthDay" path="userProfileData.birthDay" placeholder="dd-mm-yyyy"
                                              		data-week-start="1" data-autoclose="true" data-today-highlight="true" data-date-format="dd-mm-yyyy"/>
                                         </div>
                                         <div class="form-group">
                                             <label for="val-securityNumber">Security Number </label>
                                             <form:input cssClass="form-control" id="val-securityNumber" path="userProfileData.securityNumber" placeholder="Enter a security number.."/>
                                         </div>
                                         <div class="form-group">
                                             <label for="val-passportNumber">Passport Number </label>
                                             <form:input cssClass="form-control" id="val-passportNumber" path="userProfileData.passportNumber" placeholder="Enter a passport number.."/>
                                         </div>
                                         <div class="form-group">
                                             <label for="val-drivingLicenceNumber">Passport Number </label>
                                             <form:input cssClass="form-control" id="val-drivingLicenceNumber" path="userProfileData.drivingLicenceNumber" placeholder="Enter a driving licence number.."/>
                                         </div>
                                         <div class="form-group">
                                              <label for="val-caption">Caption </label>
                                              <form:textarea cssClass="form-control" id="val-caption" path="userProfileData.caption" rows="3" placeholder="What would you like to write somthing about user?"/>
                                          </div>
                                     </div>
                                 </div>
                                 <!-- END Basic Info -->
                                 
                             	 <c:if test="${!isEditMode}">
	                                 <!-- Security Info -->
	                                 <h2 class="content-heading">Security Info</h2>
	                                 <div class="row items-push">
	                                     <div class="col-lg-4">
	                                         <p class="text-muted">
	                                             Enter authentication related information like username and password
	                                         </p>
	                                     </div>
	                                     <div class="col-lg-8 col-xl-5">
	                                         <div class="form-group">
	                                             <label for="val-username">Username <span class="text-danger">*</span></label>
	                                             <form:input cssClass="form-control" id="val-username" path="username" placeholder="Enter a username.."/>
	                                         </div>
	                                         <div class="form-group">
	                                             <label for="val-password">Password <span class="text-danger">*</span></label>
	                                             <input type="password" class="form-control" id="val-password" name="password" placeholder="Choose a safe one.."/>
	                                         </div>
	                                         <div class="form-group">
	                                             <label for="val-repassword">Confirm Password <span class="text-danger">*</span></label>
	                                             <input type="password" class="form-control" id="val-repassword" name="repassword" placeholder="..and confirm it!"/>
	                                         </div>
	                                     </div>
	                                 </div>
	                                 <!-- END Security Info -->
                                 </c:if>

								 <!-- Contact Info -->
                                 <h2 class="content-heading">Basic Info</h2>
                                 <div class="row items-push">
                                     <div class="col-lg-4">
                                         <p class="text-muted">
                                             Enter user contact information like email, phone number, address etc.
                                         </p>
                                     </div>
                                     <div class="col-lg-8 col-xl-5">
                                         <div class="form-group">
                                             <label for="val-email">Email <span class="text-danger">*</span></label>
                                             <form:input cssClass="form-control" id="val-email" path="emailAddressDatas[0].email" placeholder="Your valid email.."/>
                                         </div>
                                         <div class="form-group">
                                             <label for="val-phoneNumber">Phone Number <span class="text-danger">*</span></label>
                                             <form:input cssClass="form-control" id="val-phoneNumber" path="phoneNumberDatas[0].number" placeholder="Your valid phone number.."/>
                                         </div>
                                         <div class="form-group">
                                              <label for="val-caption">Address </label>
                                              <form:textarea cssClass="form-control" id="val-line1" path="postalAddressDatas[0].line1" rows="3" placeholder="Enter address"/>
                                          </div>
                                         <div class="form-group">
                                              <label for="val-countryId">Country <span class="text-danger">*</span></label>
                                              <form:select cssClass="form-control" id="val-countryId" path="postalAddressDatas[0].countryId">
                                              	 <form:option value="0" label="Please select"></form:option>
                                              	 <form:options items="${countryList}" itemValue="id" itemLabel="name"/>
                                              </form:select>
                                         </div> 
                                     </div>
                                 </div>
                                 <!-- END Contact Info -->

                                 <!-- Submit -->
                                 <div class="row items-push">
                                     <div class="col-lg-7 offset-lg-4">
                                         <button type="submit" class="btn btn-primary" onclick="return false;">Submit</button>
                                         <button type="reset" class="btn btn-secondary">Reset</button>
                                     </div>
                                 </div>
                                 <!-- END Submit -->
                             </div>
                         </div>
                     </div>
                 </form:form>
                 <!-- User Form -->
            </div>
            <!-- END Page Content -->
        </main>
        <!-- END Main Container -->

		<%@include file="../includes/footer.jsp" %>
        <%@include file="../includes/scripts.jsp" %>  
        
        <script src="<c:url value="/resources/js/plugins/bootstrap-datepicker/js/bootstrap-datepicker.min.js"/>"></script>
        <script src="<c:url value="/resources/js/custom/user/usermodify.js"/>"></script>
        <script>
        	usermodify.isEditMode = ${isEditMode};
        	usermodify.isUsernameAlreadyExistsUrl = '<c:url value="/isUsernameAlreadyExists" />';
		</script>
        
    </body>
</html>