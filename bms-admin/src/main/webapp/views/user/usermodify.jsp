<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
                 <form class="js-validation" action="#" method="post">
                     <div class="block block-rounded block-bordered">
                         <div class="block-header block-header-default">
                             <h3 class="block-title">User Create - Form</h3>
                             <div class="block-options">
                                 <button type="submit" class="btn btn-sm btn-light">
                                     <i class="fa fa-fw fa-check"></i> Submit
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
                                             <input type="text" class="form-control" id="val-firstName" name="firstName" placeholder="Enter a first name..">
                                         </div>
                                         <div class="form-group">
                                             <label for="val-lastName">Last Name <span class="text-danger">*</span></label>
                                             <input type="text" class="form-control" id="val-lastName" name="lastName" placeholder="Enter a last name..">
                                         </div>
                                         <div class="form-group">
                                              <label for="val-gender">Gender <span class="text-danger">*</span></label>
                                              <select class="form-control" id="val-gender" name="gender">
                                                  <option value="">Please select</option>
                                                  <option value="1">Male</option>
                                                  <option value="2">Female</option>          
                                              </select>
                                         </div>
                                         <div class="form-group">
                                             <label for="val-birthDay">Birthday </label>
                                             <input type="text" class="js-datepicker form-control" id="val-birthDay" name="birthDay" data-week-start="1" data-autoclose="true" 
                                             	data-today-highlight="true" data-date-format="dd-mm-yyyy" placeholder="dd-mm-yyyy" placeholder="Enter a birthday..">
                                         </div>
                                         <div class="form-group">
                                             <label for="val-securityNumber">Security Number </label>
                                             <input type="text" class="form-control" id="val-securityNumber" name="securityNumber" placeholder="Enter a security number..">
                                         </div>
                                         <div class="form-group">
                                             <label for="val-passportNumber">Passport Number </label>
                                             <input type="text" class="form-control" id="val-passportNumber" name="passportNumber" placeholder="Enter a passport number..">
                                         </div>
                                         <div class="form-group">
                                             <label for="val-drivingLicenceNumber">Passport Number </label>
                                             <input type="text" class="form-control" id="val-drivingLicenceNumber" name="drivingLicenceNumber" placeholder="Enter a driving licence number..">
                                         </div>
                                         <div class="form-group">
                                              <label for="val-caption">Caption </label>
                                              <textarea class="form-control" id="val-caption" name="caption" rows="5" placeholder="What would you like to write somthing about you?"></textarea>
                                          </div>
                                     </div>
                                 </div>
                                 <!-- END Basic Info -->
                             
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
                                             <input type="text" class="form-control" id="val-username" name="username" placeholder="Enter a username..">
                                         </div>
                                         <div class="form-group">
                                             <label for="val-password">Password <span class="text-danger">*</span></label>
                                             <input type="password" class="form-control" id="val-password" name="password" placeholder="Choose a safe one..">
                                         </div>
                                         <div class="form-group">
                                             <label for="val-repassword">Confirm Password <span class="text-danger">*</span></label>
                                             <input type="password" class="form-control" id="val-repassword" name="repassword" placeholder="..and confirm it!">
                                         </div>
                                     </div>
                                 </div>
                                 <!-- END Security Info -->

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
                                             <input type="text" class="form-control" id="val-email" name="email" placeholder="Your valid email..">
                                         </div>
                                         <div class="form-group">
                                             <label for="val-phoneNumber">Phone Number <span class="text-danger">*</span></label>
                                             <input type="text" class="form-control" id="val-phoneNumber" name="phoneNumber" placeholder="Your valid phone number..">
                                         </div>
                                         <div class="form-group">
                                              <label for="val-caption">Address </label>
                                              <textarea class="form-control" id="val-caption" name="line1" rows="3" placeholder="Enter address"></textarea>
                                          </div>
                                         <div class="form-group">
                                              <label for="val-countryId">Country </label>
                                              <select class="form-control" id="val-countryId" name="countryId">
                                                  <option value="">Please select</option>
                                                  <option value="1">Male</option>
                                                  <option value="2">Female</option>          
                                              </select>
                                         </div> 
                                         <div class="form-group">
                                              <label for="val-stateId">State </label>
                                              <select class="form-control" id="val-stateId" name="stateId">
                                                  <option value="">Please select</option>
                                                  <option value="1">Male</option>
                                                  <option value="2">Female</option>          
                                              </select>
                                         </div> 
                                         <div class="form-group">
                                              <label for="val-cityId">City </label>
                                              <select class="form-control" id="val-cityId" name="cityId">
                                                  <option value="">Please select</option>
                                                  <option value="1">Male</option>
                                                  <option value="2">Female</option>          
                                              </select>
                                         </div> 
                                     </div>
                                 </div>
                                 <!-- END Contact Info -->

                                 <!-- Submit -->
                                 <div class="row items-push">
                                     <div class="col-lg-7 offset-lg-4">
                                         <button type="submit" class="btn btn-primary">Submit</button>
                                     </div>
                                 </div>
                                 <!-- END Submit -->
                             </div>
                         </div>
                     </div>
                 </form>
                 <!-- User Form -->
            </div>
            <!-- END Page Content -->
        </main>
        <!-- END Main Container -->

		<%@include file="../includes/footer.jsp" %>
        <%@include file="../includes/scripts.jsp" %>  
        
        <script src="<c:url value="/resources/js/plugins/bootstrap-datepicker/js/bootstrap-datepicker.min.js"/>"></script>
        <script>jQuery(function(){ Dashmix.helpers(['datepicker']); });</script>
    </body>
</html>