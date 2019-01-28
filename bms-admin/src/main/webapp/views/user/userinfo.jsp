<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.bms.common.Constants" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
    <head>
        <title>BMS - User Info</title>
        <meta name="description" content="BMS - User Info">
        <%@include file="../includes/metadata.jsp" %>
        <%@include file="../includes/appicons.jsp" %>
        <%@include file="../includes/styles.jsp" %>

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
                         <h1 class="flex-sm-fill font-size-h2 font-w400 mt-2 mb-0 mb-sm-2">User Information</h1>
                         <nav class="flex-sm-00-auto ml-sm-3" aria-label="breadcrumb">
                             <ol class="breadcrumb">
                                 <li class="breadcrumb-item">User Management</li>
                                 <li class="breadcrumb-item"><a href="<c:url value="/listusers"/>">User List</a></li>
                                 <li class="breadcrumb-item active" aria-current="page">User Information</li>
                             </ol>
                         </nav>
                     </div>
                 </div>
             </div>
             <!-- END Hero -->

			<!-- Page Content -->
            <div class="content">
          	     <div class="block block-rounded block-bordered">
                      <div class="block-header block-header-default">
                          <h3 class="block-title">${userData.userProfileData.firstName}&nbsp;${userData.userProfileData.lastName}</h3>
                      </div>
                      <div class="block-content">
                          <div class="row items-push">
                              <div class="col-md-6">
                                  <!-- Basic Info -->
                                  <h2 class="content-heading pt-0">Basic Info</h2>
                                  <div class="form-group row">
                                       <label class="col-sm-4"><strong class="text-muted">Name</strong></label>
                                       <div class="col-sm-8 text-dark">
                                           ${userData.userProfileData.firstName}&nbsp;${userData.userProfileData.lastName}
                                       </div>
                                  </div>
                                  <div class="form-group row">
                                       <label class="col-sm-4"><strong class="text-muted">Gender</strong></label>
                                       <div class="col-sm-8 text-dark">
                                           <c:choose>
												<c:when test="${userData.userProfileData.gender eq Constants.MALE}">Male</c:when>
												<c:when test="${userData.userProfileData.gender eq Constants.FEMALE}">Female</c:when>
												<c:otherwise>--</c:otherwise>
										   </c:choose>
                                       </div>
                                  </div>
                                  <div class="form-group row">
                                       <label class="col-sm-4"><strong class="text-muted">Birthday</strong></label>
                                       <div class="col-sm-8 text-dark">
                                           <c:choose>
												<c:when test="${not empty userData.userProfileData.birthDay}"><fmt:formatDate pattern="dd-MM-yyyy" value="${userData.userProfileData.birthDay}"/></c:when>
												<c:otherwise>--</c:otherwise>
										   </c:choose>
                                       </div>
                                  </div>
                                  <div class="form-group row">
                                       <label class="col-sm-4"><strong class="text-muted">Caption</strong></label>
                                       <div class="col-sm-8 text-dark">
                                           <c:choose>
												<c:when test="${not empty userData.userProfileData.caption}">${userData.userProfileData.caption}</c:when>
												<c:otherwise>--</c:otherwise>
										   </c:choose>
                                       </div>
                                  </div>
                                  <!-- END Basic Info -->
                              </div>
                              <div class="col-md-6">
                                  <!-- Success -->
                                  <h2 class="content-heading pt-md-0">Permission & Security Info</h2>
                                  <div class="form-group row">
                                       <label class="col-sm-4"><strong class="text-muted">Group</strong></label>
                                       <div class="col-sm-8 text-dark">
                                           ${userData.userGroupData.groupName}
                                       </div>
                                  </div>
                                  <div class="form-group row">
                                       <label class="col-sm-4"><strong class="text-muted">Role</strong></label>
                                       <div class="col-sm-8 text-dark">
                                           ${userData.userRoleData.roleName}
                                       </div>
                                  </div>
                                  <div class="form-group row">
                                       <label class="col-sm-4"><strong class="text-muted">Username</strong></label>
                                       <div class="col-sm-8 text-dark">
                                           <c:choose>
												<c:when test="${not empty userData.username}">${userData.username}</c:when>
												<c:otherwise>--</c:otherwise>
										   </c:choose>
                                       </div>
                                  </div>
                                  <div class="form-group row">
                                       <label class="col-sm-4"><strong class="text-muted">Password</strong></label>
                                       <div class="col-sm-8 text-dark">
                                           &bull;&bull;&bull;&bull;&bull;&bull;&bull;&bull;
                                       </div>
                                  </div>
                              </div>
                              <div class="col-md-6">
                                  <!-- Success -->
                                  <h2 class="content-heading pt-md-0">Contact Info</h2>
                                  <div class="form-group row">
                                       <label class="col-sm-4"><strong class="text-muted">Email</strong></label>
                                       <div class="col-sm-8 text-dark">
                                           <c:choose>
												<c:when test="${not empty userData.emailAddressDatas && not empty userData.emailAddressDatas[0].email}">${userData.emailAddressDatas[0].email}</c:when>
												<c:otherwise>--</c:otherwise>
										   </c:choose>
                                       </div>
                                  </div>
                                  <div class="form-group row">
                                       <label class="col-sm-4"><strong class="text-muted">Phone Number</strong></label>
                                       <div class="col-sm-8 text-dark">
                                           <c:choose>
												<c:when test="${not empty userData.phoneNumberDatas && not empty userData.phoneNumberDatas[0].code && not empty userData.phoneNumberDatas[0].number}">
													+${userData.phoneNumberDatas[0].code} - ${userData.phoneNumberDatas[0].number}
												</c:when>
												<c:otherwise>--</c:otherwise>
										   </c:choose>
                                       </div>
                                  </div>
                                  <div class="form-group row">
                                       <label class="col-sm-4"><strong class="text-muted">Address</strong></label>
                                       <div class="col-sm-8 text-dark">
                                           <c:choose>
												<c:when test="${not empty userData.postalAddressDatas && not empty userData.postalAddressDatas[0].line1}">${userData.postalAddressDatas[0].line1}</c:when>
												<c:otherwise>--</c:otherwise>
											</c:choose>
                                       </div>
                                  </div>
                                  <div class="form-group row">
                                       <label class="col-sm-4"><strong class="text-muted">Country</strong></label>
                                       <div class="col-sm-8 text-dark">
                                           <c:choose>
												<c:when test="${not empty userData.postalAddressDatas && not empty userData.postalAddressDatas[0].countryName}">${userData.postalAddressDatas[0].countryName}</c:when>
												<c:otherwise>--</c:otherwise>
											</c:choose>
                                       </div>
                                   </div>
                              </div>
                              <div class="col-md-6">
                              	   <h2 class="content-heading pt-md-0">Identity Info</h2>
                              	   <div class="form-group row">
                                       <label class="col-sm-4"><strong class="text-muted">Security Number</strong></label>
                                       <div class="col-sm-8 text-dark">
                                           <c:choose>
												<c:when test="${not empty userData.userProfileData.securityNumber}">${userData.userProfileData.securityNumber}</c:when>
												<c:otherwise>--</c:otherwise>
										   </c:choose>
                                       </div>
                                  </div>
                                  <div class="form-group row">
                                       <label class="col-sm-4"><strong class="text-muted">Passport Number</strong></label>
                                       <div class="col-sm-8 text-dark">
                                           <c:choose>
												<c:when test="${not empty userData.userProfileData.passportNumber}">${userData.userProfileData.passportNumber}</c:when>
												<c:otherwise>--</c:otherwise>
										   </c:choose>
                                       </div>
                                  </div>
                                  <div class="form-group row">
                                       <label class="col-sm-4"><strong class="text-muted">Driving Licence Number</strong></label>
                                       <div class="col-sm-8 text-dark">
                                           <c:choose>
												<c:when test="${not empty userData.userProfileData.drivingLicenceNumber}">${userData.userProfileData.drivingLicenceNumber}</c:when>
												<c:otherwise>--</c:otherwise>
										   </c:choose>
                                       </div>
                                  </div>
                              </div>
                          </div>
                      </div>
                  </div>
            </div>
            <!-- END Page Content -->
        </main>
        <!-- END Main Container -->

		<%@include file="../includes/footer.jsp" %>
        <%@include file="../includes/scripts.jsp" %>  

    </body>
</html>