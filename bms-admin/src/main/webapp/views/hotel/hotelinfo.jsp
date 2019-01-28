<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.bms.common.Constants" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
    <head>
        <title>BMS - Hotel Info</title>
        <meta name="description" content="BMS - Hotel Info">
        <%@include file="../includes/metadata.jsp" %>
        <%@include file="../includes/appicons.jsp" %>
        <%@include file="../includes/styles.jsp" %>

		<link rel="stylesheet" href="<c:url value="/resources/js/plugins/raty-js/jquery.raty.css"/>" />
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
                         <h1 class="flex-sm-fill font-size-h2 font-w400 mt-2 mb-0 mb-sm-2">Hotel Information</h1>
                         <nav class="flex-sm-00-auto ml-sm-3" aria-label="breadcrumb">
                             <ol class="breadcrumb">
                                 <li class="breadcrumb-item">Hotel Management</li>
                                 <li class="breadcrumb-item">Hotel Info</li>
                                 <li class="breadcrumb-item"><a href="<c:url value="/listhotels"/>">Hotel List</a></li>
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
                          <h3 class="block-title">${providerData.title}</h3>
                      </div>
                      <div class="block-content">
                          <div class="row items-push">
                          		<div class="col-md-6">
                          			<h2 class="content-heading pt-0">Basic Info</h2>
                          			<div class="form-group row">
                          				<label class="col-sm-4"><strong class="text-muted">Title</strong></label>
                          				<div class="col-sm-8 text-dark">
                          					${providerData.title}
                          				</div>
                          			</div>
                          			<div class="form-group row">
                          				<label class="col-sm-4"><strong class="text-muted">Rating</strong></label>
                          				<div class="col-sm-8 text-dark">
                          					<div class="rating" data-score="${providerData.hotelData.starRating}"></div>
                          				</div>
                          			</div>
                          			<div class="form-group row">
                          				<label class="col-sm-4"><strong class="text-muted">Number Of Floor</strong></label>
                          				<div class="col-sm-8 text-dark">
                          					${providerData.hotelData.numberOfFloor}
                          				</div>
                          			</div>
                          			<div class="form-group row">
                          				<label class="col-sm-4"><strong class="text-muted">Check In Time</strong></label>
                          				<div class="col-sm-8 text-dark">
                          					${providerData.hotelData.checkInTime}
                          				</div>
                          			</div>
                          			<div class="form-group row">
                          				<label class="col-sm-4"><strong class="text-muted">Check Out Time</strong></label>
                          				<div class="col-sm-8 text-dark">
                          					${providerData.hotelData.checkOutTime}
                          				</div>
                          			</div>
                          			<div class="form-group row">
                          				<label class="col-sm-4"><strong class="text-muted">Specification</strong></label>
                          				<div class="col-sm-8 text-dark">
                          					${providerData.specification}
                          				</div>
                         			</div>
                          		</div>
                          		<div class="col-md-6">
                          			<h2 class="content-heading pt-md-0">Contact Info</h2>
                          			<div class="form-group row">
                                       	<label class="col-sm-4"><strong class="text-muted">Email</strong></label>
                                       	<div class="col-sm-8 text-dark">
                                           <c:choose>
												<c:when test="${not empty providerData.emailAddressDatas && not empty providerData.emailAddressDatas[0].email}">${providerData.emailAddressDatas[0].email}</c:when>
												<c:otherwise>--</c:otherwise>
										   </c:choose>
                                       	</div>
	                                  	</div>
	                                  	<div class="form-group row">
	                                       <label class="col-sm-4"><strong class="text-muted">Phone Number</strong></label>
	                                       <div class="col-sm-8 text-dark">
	                                           <c:choose>
													<c:when test="${not empty providerData.phoneNumberDatas && not empty providerData.phoneNumberDatas[0].code && not empty providerData.phoneNumberDatas[0].number}">
														+${providerData.phoneNumberDatas[0].code} - ${providerData.phoneNumberDatas[0].number}
													</c:when>
													<c:otherwise>--</c:otherwise>
											   </c:choose>
	                                       </div>
	                                  	</div>
	                                  	<div class="form-group row">
	                                       <label class="col-sm-4"><strong class="text-muted">Address</strong></label>
	                                       <div class="col-sm-8 text-dark">
	                                           <c:choose>
													<c:when test="${not empty providerData.postalAddressDatas && not empty providerData.postalAddressDatas[0].line1}">${providerData.postalAddressDatas[0].line1}</c:when>
													<c:otherwise>--</c:otherwise>
												</c:choose>
	                                       </div>
	                                  	</div>
	                                  	<div class="form-group row">
	                                       <label class="col-sm-4"><strong class="text-muted">City</strong></label>
	                                       <div class="col-sm-8 text-dark">
	                                           <c:choose>
													<c:when test="${not empty providerData.postalAddressDatas && not empty providerData.postalAddressDatas[0].cityName}">${providerData.postalAddressDatas[0].cityName}</c:when>
													<c:otherwise>--</c:otherwise>
												</c:choose>
	                                       </div>
                                   		</div>
	                                  	<div class="form-group row">
	                                       <label class="col-sm-4"><strong class="text-muted">State</strong></label>
	                                       <div class="col-sm-8 text-dark">
	                                           <c:choose>
													<c:when test="${not empty providerData.postalAddressDatas && not empty providerData.postalAddressDatas[0].stateName}">${providerData.postalAddressDatas[0].stateName}</c:when>
													<c:otherwise>--</c:otherwise>
												</c:choose>
	                                       </div>
                                   		</div>
	                                  	<div class="form-group row">
	                                       <label class="col-sm-4"><strong class="text-muted">Country</strong></label>
	                                       <div class="col-sm-8 text-dark">
	                                           <c:choose>
													<c:when test="${not empty providerData.postalAddressDatas && not empty providerData.postalAddressDatas[0].countryName}">${providerData.postalAddressDatas[0].countryName}</c:when>
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
        
        <script src="<c:url value="/resources/js/plugins/raty-js/jquery.raty.js"/>"></script>
		
		<script type="text/javascript">
		var hotelinfo = {
	       		init : function() {
	       			$(".rating").raty({
	            		starType: "i",
	            		hints: ["One Star", "Two Stars", "Three Stars", "Four Stars", "Five Stars"],
	                    starOff: $(this).data("star-off") || "fa fa-fw fa-star text-muted",
	                    starOn: $(this).data("star-on") || "fa fa-fw fa-star text-warning",
	                    readOnly: true
	                });
	       		}
	        };
	        
	        $(document).ready(function() {
	        	hotelinfo.init();
	        });
		</script>

    </body>
</html>

</body>
</html>