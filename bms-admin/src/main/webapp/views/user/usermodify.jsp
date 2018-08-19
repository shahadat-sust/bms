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
        <link rel="stylesheet" href="<c:url value="/resources/css/intlTelInput.css"/>"></link>
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
                         <c:set var="firstNameError"><form:errors path="userProfileData.firstName"/></c:set>
                         <c:set var="lastNameError"><form:errors path="userProfileData.lastName"/></c:set>
                         <c:set var="genderError"><form:errors path="userProfileData.gender"/></c:set>
                         <c:set var="usernameError"><form:errors path="username"/></c:set>
                         <c:set var="passwordError"><form:errors path="password"/></c:set>
                         <c:set var="repasswordError"><form:errors path="repassword"/></c:set>
                         <c:set var="emailError"><form:errors path="emailAddressDatas[0].email"/></c:set>
                         <c:set var="countryCodeError"><form:errors path="phoneNumberDatas[0].code"/></c:set>
                         <c:set var="phoneNumberError"><form:errors path="phoneNumberDatas[0].number"/></c:set>
                         <c:set var="countryIdError"><form:errors path="postalAddressDatas[0].countryId"/></c:set>
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
                                             <form:input id="val-firstName" path="userProfileData.firstName" placeholder="Enter a first name.."
                                              	cssClass="form-control ${not empty firstNameError ? 'is-invalid' :''}"
                                              	aria-describedby="${not empty countryIdError ? 'val-firstName-error' :''}"/>
                                             <c:if test="${not empty firstNameError}">
                                             	<div id="val-firstName-error" class="invalid-feedback animated fadeIn">${firstNameError}</div>
                                             </c:if>
                                         </div>
                                         <div class="form-group">
                                             <label for="val-lastName">Last Name <span class="text-danger">*</span></label>
                                             <form:input id="val-lastName" path="userProfileData.lastName" placeholder="Enter a last name.."
                                             	cssClass="form-control ${not empty lastNameError ? 'is-invalid' :''}"
                                             	aria-describedby="${not empty countryIdError ? 'val-lastName-error' :''}"/>
                                             <c:if test="${not empty lastNameError}">
                                             	<div id="val-lastName-error" class="invalid-feedback animated fadeIn">${lastNameError}</div>
                                             </c:if>
                                         </div>
                                         <div class="form-group">
                                             <label for="val-gender">Gender <span class="text-danger">*</span></label>
                                             <form:select id="val-gender" path="userProfileData.gender"
                                              	cssClass="form-control ${not empty genderError ? 'is-invalid' :''}"
                                              	aria-describedby="${not empty countryIdError ? 'val-gender-error' :''}">
                                              	<form:option value="0" label="Please select"></form:option>
                                              	<form:option value="<%= Constants.MALE %>" label="Male"></form:option>
                                              	<form:option value="<%= Constants.FEMALE %>" label="Female"></form:option>
                                              </form:select> 
                                              <c:if test="${not empty genderError}">
                                             	<div id="val-gender-error" class="invalid-feedback animated fadeIn">${genderError}</div>
                                             </c:if>
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
	                                             <form:input id="val-username" path="username" placeholder="Enter a username.."
	                                             	cssClass="form-control ${not empty usernameError ? 'is-invalid' :''}"
	                                             	aria-describedby="${not empty countryIdError ? 'val-username-error' :''}"/>
	                                             <c:if test="${not empty usernameError}">
	                                             	<div id="val-username-error" class="invalid-feedback animated fadeIn">${usernameError}</div>
	                                             </c:if>
	                                         </div>
	                                         <div class="form-group">
	                                             <label for="val-password">Password <span class="text-danger">*</span></label>
	                                             <form:password id="val-password" path="password" placeholder="Choose a safe one.."
	                                             	cssClass="form-control ${not empty passwordError ? 'is-invalid' :''}"
	                                             	aria-describedby="${not empty countryIdError ? 'val-password-error' :''}"/>
	                                             <c:if test="${not empty passwordError}">
	                                             	<div id="val-password-error" class="invalid-feedback animated fadeIn">${passwordError}</div>
	                                             </c:if>
	                                         </div>
	                                         <div class="form-group">
	                                             <label for="val-repassword">Confirm Password <span class="text-danger">*</span></label>
	                                             <form:password id="val-repassword" path="repassword" placeholder="..and confirm it!"
	                                             	cssClass="form-control ${not empty repasswordError ? 'is-invalid' :''}"
	                                             	aria-describedby="${not empty countryIdError ? 'val-repassword-error' :''}"/>
	                                             <c:if test="${not empty repasswordError}">
	                                             	<div id="val-repassword-error" class="invalid-feedback animated fadeIn">${repasswordError}</div>
	                                             </c:if>
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
                                             <form:input id="val-email" path="emailAddressDatas[0].email" placeholder="Your valid email.."
                                             	cssClass="form-control ${not empty emailError ? 'is-invalid' :''}"
                                             	aria-describedby="${not empty countryIdError ? 'val-email-error' :''}"/>
                                             <c:if test="${not empty emailError}">
                                             	<div id="val-email-error" class="invalid-feedback animated fadeIn">${emailError}</div>
                                             </c:if>
                                         </div>
                                         <div class="form-group">
											<label for="val-email">Phone Number <span class="text-danger">*</span></label>
											<div class="input-group">
												<span class="input-group-btn">
													<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
														<c:if test="${not empty userForm.phoneNumberDatas[0].code}">
														    <span class="type-text">+${userForm.phoneNumberDatas[0].code}</span> 
														</c:if>
														<c:if test="${empty userForm.phoneNumberDatas[0].code}">
															<span class="type-text">Code</span> 
														</c:if>
														<span class="caret"></span>
													</button>
													<div class="dropdown-menu" role="menu">
														<ul class="intl-tel-input country-list" role="menu">
															<c:forEach var="countryCode" items="${countryCodeList}">
																<li class="country" data-dial-code="${countryCode.code}" data-country-code="${countryCode.shortCode}">
																	<div class="flag-box"><div class="iti-flag ${countryCode.shortCode}"></div></div>
																	<span class="country-name">${countryCode.name}</span>
																	<span class="dial-code">+${countryCode.code}</span>
																</li>
																<c:if test="${countryCode.priority == 1}"><li class="divider"></li></c:if>
															</c:forEach>
														</ul>	
													</div>				
												</span>
												<form:hidden id="val-code" path="phoneNumberDatas[0].code"
													cssClass="form-control ${not empty countryCodeError ? 'is-invalid' :''}"
													aria-describedby="${not empty countryIdError ? 'val-code-error' :''}"/>
												<form:input id="val-number" path="phoneNumberDatas[0].number" placeholder="Your valid phone number.." 
													cssClass="form-control ${not empty phoneNumberError ? 'is-invalid' :''}"
													aria-describedby="${not empty countryIdError ? 'val-number-error' :''}"/>
												<c:if test="${not empty countryCodeError}">
	                                             	<div id="val-code-error" class="invalid-feedback animated fadeIn">${countryCodeError}</div>
	                                            </c:if>
	                                            <c:if test="${not empty phoneNumberError}">
	                                             	<div id="val-number-error" class="invalid-feedback animated fadeIn">${phoneNumberError}</div>
	                                            </c:if>
											</div>
										</div>
                                         <div class="form-group">
                                              <label for="val-caption">Address </label>
                                              <form:textarea cssClass="form-control" id="val-line1" path="postalAddressDatas[0].line1" rows="3" placeholder="Enter address"/>
                                          </div>
                                         <div class="form-group">
                                              <label for="val-countryId">Country <span class="text-danger">*</span></label>
                                              <form:select id="val-countryId" path="postalAddressDatas[0].countryId"
                                              	 cssClass="form-control ${not empty countryIdError ? 'is-invalid' :''}"
                                              	 aria-describedby="${not empty countryIdError ? 'val-countryId-error' :''}">
                                              	 <form:option value="0" label="Please select"></form:option>
                                              	 <form:options items="${countryList}" itemValue="id" itemLabel="name"/>
                                              </form:select>
                                              <c:if test="${not empty countryIdError}">
                                             	<div id="val-countryId-error" class="invalid-feedback animated fadeIn">${countryIdError}</div>
                                             </c:if>
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
        	usermodify.isUsernameAvailableUrl = '<c:url value="/isUsernameAvailable" />';
        	usermodify.isEmailAvailableUrl = '<c:url value="/isEmailAvailable" />';
        	usermodify.isPhoneNumberAvailableUrl = '<c:url value="/isPhoneNumberAvailable" />';
        	usermodify.countryCode = "${phoneNumberDatas[0].code}";
		</script>
        
    </body>
</html>