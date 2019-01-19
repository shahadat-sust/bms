<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.bms.common.Constants" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
    <head>
        <title>BMS - Create Hotel</title>
        <meta name="description" content="BMS - User List">
        <%@include file="../includes/metadata.jsp" %>
        <%@include file="../includes/appicons.jsp" %>
        <%@include file="../includes/styles.jsp" %>
        
        <link rel="stylesheet" href="<c:url value="/resources/js/plugins/bootstrap-timepicker/bootstrap-timepicker.min.css"/>" />
        <link rel="stylesheet" href="<c:url value="/resources/js/plugins/bootstrap-clockpicker/bootstrap-clockpicker.min.css"/>" />
    </head>
    <body> 
        <%@include file="../includes/header.jsp" %>
        <%@include file="../includes/sidebarleft.jsp" %>
		<%@include file="../includes/sidebarright.jsp" %>
		
		<c:choose>
			<c:when test="${isEditMode}"><c:set var="pageTitle" value="Edit Hotel"/></c:when>
			<c:otherwise><c:set var="pageTitle" value="Create Hotel"/></c:otherwise>
		</c:choose>
		
        <!-- Main Container -->
        <main id="main-container">

			<!-- Hero -->
             <div class="bg-body-light">
                 <div class="content content-full">
                     <div class="d-flex flex-column flex-sm-row justify-content-sm-between align-items-sm-center">
                         <h1 class="flex-sm-fill font-size-h2 font-w400 mt-2 mb-0 mb-sm-2">${pageTitle}</h1>
                         <nav class="flex-sm-00-auto ml-sm-3" aria-label="breadcrumb">
                             <ol class="breadcrumb">
                                 <li class="breadcrumb-item">Hotel Management</li>
                                 <li class="breadcrumb-item">Hotel Info</li>
                                 <c:if test="${isEditMode}">
                                 	<li class="breadcrumb-item"><a href="<c:url value="/listhotels"/>">Hotel List</a></li>
                                 </c:if>
                                 <li class="breadcrumb-item active" aria-current="page">${pageTitle}</li>
                             </ol>
                         </nav>
                     </div>
                 </div>
             </div>
             <!-- END Hero -->

			<!-- Page Content -->
            <div class="content">
          	     <!-- User Form -->
          	     <c:url var="savehotel" value="/savehotel" />
                 <form:form id="formHotelModify" action="${savehotel}" method="post" modelAttribute="hotelForm">
                     <div class="block block-rounded block-bordered">
                         <div class="block-header block-header-default">
                             <h3 class="block-title">Hotel - Form</h3>
                             <div class="block-options">
                             	 <form:hidden id="val-providerId" path="id"/>
                             	 <form:hidden id="val-providerTypeId" path="providerTypeId"/>
                             	 <form:hidden id="val-isActive" path="active"/>
                             	 <form:hidden id="val-status" path="status"/>
                             	 <form:hidden id="val-hotelId" path="hotelData.id"/>
                             	 <form:hidden id="val-hotelData-providerId" path="hotelData.providerId"/>
                             	 <form:hidden id="val-emailAddressId" path="emailAddressDatas[0].id"/>
                             	 <form:hidden id="val-emailAddress-providerId" path="emailAddressDatas[0].providerId"/>
                             	 <form:hidden id="val-emailAddress-verified" path="emailAddressDatas[0].verified"/>
                             	 <form:hidden id="val-emailAddress-status" path="emailAddressDatas[0].status"/>
                             	 <form:hidden id="val-emailAddress-primary" path="emailAddressDatas[0].primary"/>
                             	 <form:hidden id="val-phoneNumberId" path="phoneNumberDatas[0].id"/>
                             	 <form:hidden id="val-phoneNumber-providerId" path="phoneNumberDatas[0].providerId"/>
                             	 <form:hidden id="val-phoneNumber-verified" path="phoneNumberDatas[0].verified"/>
                             	 <form:hidden id="val-phoneNumber-status" path="phoneNumberDatas[0].status"/>
                             	 <form:hidden id="val-phoneNumber-primary" path="phoneNumberDatas[0].primary"/>
                             	 <form:hidden id="val-postalAddressId" path="postalAddressDatas[0].id"/>
                             	 <form:hidden id="val-postalAddress-providerId" path="postalAddressDatas[0].providerId"/>
                             	 <form:hidden id="val-latitude" path="hotelData.latitude"/>
                             	 <form:hidden id="val-longitude" path="hotelData.longitude"/>
                             	 <form:hidden id="val-starRating" path="hotelData.starRating"/>
                                 <button type="submit" class="btn btn-sm btn-light" onclick="return false;">
                                     <i class="fa fa-fw fa-check"></i> Submit
                                 </button>
                                 <button type="reset" class="btn btn-sm btn-light">
                                 	<i class="fa fa-fw fa-undo"></i> Reset
								</button>
                             </div>
                         </div>
                         <c:set var="titleError"><form:errors path="title"/></c:set>
                         <c:set var="numberOfFloorError"><form:errors path="hotelData.numberOfFloor"/></c:set>
                         <c:set var="checkInTimeError"><form:errors path="hotelData.checkInTime"/></c:set>
                         <c:set var="checkOutTimeError"><form:errors path="hotelData.checkOutTime"/></c:set>
                         <c:set var="emailError"><form:errors path="emailAddressDatas[0].email"/></c:set>
                         <c:set var="countryCodeError"><form:errors path="phoneNumberDatas[0].code"/></c:set>
                         <c:set var="phoneNumberError"><form:errors path="phoneNumberDatas[0].number"/></c:set>
                         <c:set var="countryIdError"><form:errors path="postalAddressDatas[0].countryId"/></c:set>
                         <c:set var="stateIdError"><form:errors path="postalAddressDatas[0].stateId"/></c:set>
                         <c:set var="cityIdError"><form:errors path="postalAddressDatas[0].cityId"/></c:set>
                         <c:set var="postCodeError"><form:errors path="postalAddressDatas[0].postCode"/></c:set>
                         <div class="block-content block-content-full">
                             <div class="">
                             	<%@include file="../includes/formStatus.jsp" %>
                             	 <!-- Security Info -->
                                 <h2 class="content-heading">Basic Info</h2>
                                 <div class="row items-push">
                                     <div class="col-lg-4">
                                         <p class="text-muted">
                                             Enter hotel basic information like title, check in time, checkout out time, star rating etc.
                                         </p>
                                     </div>
                                     <div class="col-lg-8 col-xl-5">
                                         <div class="form-group">
                                             <label for="val-title">Title <span class="text-danger">*</span></label>
                                             <form:input id="val-title" path="title" placeholder="Enter a title.." maxlength="90"
                                              	cssClass="form-control ${not empty titleError ? 'is-invalid' :''}"
                                              	aria-describedby="${not empty titleError ? 'val-title-error' :''}"/>
                                             <c:if test="${not empty titleError}">
                                             	<div id="val-title-error" class="invalid-feedback animated fadeIn">${titleError}</div>
                                             </c:if>
                                         </div>
                                         <div class="form-group">
                                              <label for="val-specification">Specification </label>
                                              <form:textarea cssClass="form-control" id="val-specification" path="specification" rows="3" maxlength="300" placeholder="What would you like to write somthing?"/>
                                          </div>
                                          <div class="form-group">
                                             <label for="val-numberOfFloor">Number Of Floor <span class="text-danger">*</span></label>
                                             <form:input id="val-numberOfFloor" path="hotelData.numberOfFloor" placeholder="Enter Number Of Floor.." maxlength="3"
                                              	cssClass="form-control ${not empty numberOfFloorError ? 'is-invalid' :''}"
                                              	aria-describedby="${not empty numberOfFloorError ? 'val-numberOfFloor-error' :''}"/>
                                             <c:if test="${not empty numberOfFloorError}">
                                             	<div id="val-numberOfFloor-error" class="invalid-feedback animated fadeIn">${numberOfFloorError}</div>
                                             </c:if>
                                         </div>
                                          <div class="form-group clockpicker">
                                             <label for="val-checkInTime">Check In Time <span class="text-danger">*</span></label>
                                             <form:input id="val-checkInTime" path="hotelData.checkInTime" placeholder="hh:mm" data-date-format="hh:mm"
                                                data-week-start="1" data-autoclose="true" data-today-highlight="true" 
                                                cssClass="form-control ${not empty checkInTimeError ? 'is-invalid' :''}"  
                                             	aria-describedby="${not empty checkInTimeError ? 'val-checkInTime-error' :''}" />
                                           	 <c:if test="${not empty checkInTimeError}">
                                             	<div id="val-checkInTime-error" class="invalid-feedback animated fadeIn">${checkInTimeError}</div>
                                             </c:if>
                                         </div>
                                         <div class="form-group clockpicker">
                                             <label for="val-checkOutTime">Check Out Time <span class="text-danger">*</span></label>
                                             <form:input id="val-checkOutTime" path="hotelData.checkOutTime" placeholder="hh:mm" data-date-format="hh:mm"  
                                             	data-week-start="1" data-today-highlight="true" data-autoclose="true" 
                                             	cssClass="form-control ${not empty checkOutTimeError ? 'is-invalid' :''}"
                                             	aria-describedby="${not empty checkOutTimeError ? 'val-checkOutTime-error' :''}" />
                                             <c:if test="${not empty checkOutTimeError}">
                                             	<div id="val-checkOutTime-error" class="invalid-feedback animated fadeIn">${checkOutTimeError}</div>
                                             </c:if>
                                         </div>
                                         <!-- <div class="form-group clockpicker">
										    <input type="text" class="form-control" value="09:30">
										 </div> -->
                                          <div class="form-group">
                                              <label for="val-starRating">Star Rating </label>
                                              <div class="rating form-control" data-score="${hotelForm.hotelData.starRating}"></div>
                                          </div>
                                     </div>
                                 </div>
                                 <!-- END Basic Info -->
                                 
								 <!-- Contact Info -->
                                 <h2 class="content-heading">Contact Info</h2>
                                 <div class="row items-push">
                                     <div class="col-lg-4">
                                         <p class="text-muted">
                                             Enter hotel contact information like email, phone number, address etc.
                                         </p>
                                     </div>
                                     <div class="col-lg-8 col-xl-5">
                                         <div class="form-group">
                                             <label for="val-email">Email <span class="text-danger">*</span></label>
                                             <form:input id="val-email" path="emailAddressDatas[0].email" placeholder="Your valid email.." maxlength="127"
                                             	cssClass="form-control ${not empty emailError ? 'is-invalid' :''}"
                                             	aria-describedby="${not empty emailError ? 'val-email-error' :''}"/>
                                             <c:if test="${not empty emailError}">
                                             	<div id="val-email-error" class="invalid-feedback animated fadeIn">${emailError}</div>
                                             </c:if>
                                         </div>
                                         <div class="form-group">
											<label for="val-phoneNumber">Phone Number <span class="text-danger">*</span></label>
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
												<form:input id="val-number" path="phoneNumberDatas[0].number" placeholder="Your valid phone number.." maxlength="20" 
													cssClass="form-control ${not empty phoneNumberError ? 'is-invalid' :''}"
													aria-describedby="${not empty phoneNumberError ? 'val-number-error' :''}"/>
												<c:if test="${not empty countryCodeError}">
	                                             	<div id="val-code-error" class="invalid-feedback animated fadeIn">${countryCodeError}</div>
	                                            </c:if>
	                                            <c:if test="${not empty phoneNumberError}">
	                                             	<div id="val-number-error" class="invalid-feedback animated fadeIn">${phoneNumberError}</div>
	                                            </c:if>
											</div>
										</div>
                                     </div>
                                 </div>
                                 <!-- END Contact Info -->

								 <!-- Contact Info -->
                                 <h2 class="content-heading">Location Info</h2>
                                 <div class="row items-push">
                                     <div class="col-lg-4">
                                         <p class="text-muted">
                                             Enter user location information like city, country, address etc.
                                         </p>
                                     </div>
                                     <div class="col-lg-8 col-xl-5">
                                         <div class="form-group">
                                              <label for="val-countryId">Country </label>
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
                                         <div class="form-group">
                                              <label for="val-stateId">State </label>
                                              <form:select id="val-stateId" path="postalAddressDatas[0].stateId"
                                              	 cssClass="form-control ${not empty stateIdError ? 'is-invalid' :''}"
                                              	 aria-describedby="${not empty stateIdError ? 'val-stateId-error' :''}">
                                              	 <form:option value="0" label="Please select"></form:option>
                                              	 <form:options items="${stateList}" itemValue="id" itemLabel="name"/>
                                              </form:select>
                                              <c:if test="${not empty stateIdError}">
                                             	<div id="val-stateId-error" class="invalid-feedback animated fadeIn">${stateIdError}</div>
                                             </c:if>
                                         </div>
                                         <div class="form-group">
                                              <label for="val-cityId">City <span class="text-danger">*</span></label>
                                              <form:select id="val-cityId" path="postalAddressDatas[0].cityId"
                                              	 cssClass="form-control ${not empty cityIdError ? 'is-invalid' :''}"
                                              	 aria-describedby="${not empty cityIdError ? 'val-cityId-error' :''}">
                                              	 <form:option value="0" label="Please select"></form:option>
                                              	 <form:options items="${cityList}" itemValue="id" itemLabel="name"/>
                                              </form:select>
                                              <c:if test="${not empty cityIdError}">
                                             	<div id="val-cityId-error" class="invalid-feedback animated fadeIn">${cityIdError}</div>
                                             </c:if>
                                         </div>  
                                         <div class="form-group">
                                             <label for="val-postCode">Post Code </label>
                                             <form:input id="val-postCode" path="postalAddressDatas[0].postCode" placeholder="Enter Post Code.." maxlength="10"
                                              	cssClass="form-control ${not empty postCodeError ? 'is-invalid' :''}"
                                              	aria-describedby="${not empty postCodeError ? 'val-postCode-error' :''}" />
                                             <c:if test="${not empty postCodeError}">
                                             	<div id="val-postCode-error" class="invalid-feedback animated fadeIn">${postCodeError}</div>
                                             </c:if>
                                         </div>
                                         <div class="form-group">
                                              <label for="val-caption">Address </label>
                                              <form:textarea cssClass="form-control" id="val-line1" path="postalAddressDatas[0].line1" rows="3"  maxlength="150" placeholder="Enter address"/>
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
        
        <script src="<c:url value="/resources/js/plugins/bootstrap-timepicker/bootstrap-timepicker.min.js"/>"></script>
        <script src="<c:url value="/resources/js/plugins/bootstrap-clockpicker/bootstrap-clockpicker.min.js"/>"></script>
        <script src="<c:url value="/resources/js/custom/hotel/hotelmodify.js"/>"></script>
        <script>
	        hotelmodify.isEditMode = ${isEditMode};
	        hotelmodify.countryCode = "${phoneNumberDatas[0].code}";
	        hotelmodify.fetchStateListUrl = '<c:url value="/state/fetch/0/{#countryId}/1" />'
        	hotelmodify.fetchCityListUrl = '<c:url value="/city/fetch/0/{#stateId}/0/1" />'
		</script>
        
    </body>
</html>