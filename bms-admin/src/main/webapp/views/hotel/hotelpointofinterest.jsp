<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>BMS - Hotel Wise Point Of Interest</title>
		<meta name="description" content="BMS - Hotel Wise Point Of Interest">
		<%@include file="../includes/metadata.jsp"%>
		<%@include file="../includes/appicons.jsp"%>
		<%@include file="../includes/styles.jsp"%>
	</head>
	<body>
		<%@include file="../includes/header.jsp"%>
		<%@include file="../includes/sidebarleft.jsp"%>
		<%@include file="../includes/sidebarright.jsp"%>
		
		<!-- Main Container -->
		<main id="main-container">
		
			<!-- Hero -->
            <div class="bg-body-light">
                <div class="content content-full">
                    <div class="d-flex flex-column flex-sm-row justify-content-sm-between align-items-sm-center">
                        <h1 class="flex-sm-fill font-size-h2 font-w400 mt-2 mb-0 mb-sm-2">Hotel Wise Point Of Interest</h1>
                        <nav class="flex-sm-00-auto ml-sm-3" aria-label="breadcrumb">
                            <ol class="breadcrumb">
                                <li class="breadcrumb-item">Hotel Management</li>
                                <li class="breadcrumb-item">Hotel Info</li>
                                <li class="breadcrumb-item active" aria-current="page">Hotel Wise Point Of Interest</li>
                            </ol>
                        </nav>
                    </div>
                </div>
            </div>
            <!-- END Hero -->
             
            <!-- Page Content -->
            <div class="content">
            	<!-- Full Table -->
            	<div class="block block-rounded block-bordered">
           			<div class="block-content">
                    	<div class="row items-push">
                    		<div class="col-md-4">
                    			<h2 class="content-heading pt-0">Hotel Info</h2>
                    			<div class="form-group row">
                                    <label class="col-sm-4 col-form-label" for="var-title">
                                    	<strong class="text-muted">Title</strong>
                                   	</label>
                                    <div class="col-sm-8">
                                    	<input type="hidden" class="form-control" id="var-selected-providerId" value="${sessionScope.DEFAULT_HOTEL.providerId}"/>
                                    	<input type="hidden" class="form-control" id="var-selected-providerTypeId" value="${sessionScope.DEFAULT_HOTEL.providerTypeId}"/>
                                        <input type="text" class="form-control" readonly="readonly" id="var-title" value="${sessionScope.DEFAULT_HOTEL.title}"/>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-sm-4 col-form-label" for="var-cityName">
                                    	<strong class="text-muted">City</strong>
                                   	</label>
                                    <div class="col-sm-8">
                                    	<input type="text" class="form-control" readonly="readonly" id="var-cityName" value="${sessionScope.DEFAULT_HOTEL.cityName}"/>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-sm-4 col-form-label" for="var-countryName">
                                    	<strong class="text-muted">Country</strong>
                                   	</label>
                                    <div class="col-sm-8">
                                    	<input type="text" class="form-control" readonly="readonly" id="var-countryName" value="${sessionScope.DEFAULT_HOTEL.countryName}"/>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-sm-4 col-form-label" for="var-address"><strong class="text-muted">Address</strong></label>
                                    <div class="col-sm-8">
                                        <textarea class="form-control" rows="3" readonly="readonly" id="var-address">${sessionScope.DEFAULT_HOTEL.address}</textarea>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-sm-4 col-form-label"><strong class="text-muted">Rating</strong></label>
                                    <div class="col-sm-8">
                                        <div id="var-starRating" class="rating form-control" data-score="${sessionScope.DEFAULT_HOTEL.starRating}"></div>
                                    </div>
                                </div>
                             	<div class="form-group row">
                                  	<div class="col-sm-12 text-right">
                                  		<a id="btnHotelSearch" class="btn btn-sm btn-success text-white mr-1" data-toggle="modal" data-target="#hotel-search-modal">
			                           		<i class="fa fa-fw fa-search mr-1"></i> Search
			                       		</a>
                                  	</div>
                             	</div>
                    		</div>
                    	 	<div class="col-md-8">
                    	 		<h2 class="content-heading pt-0">Point Of Interest List</h2>
                   	 			<!-- Full Table -->
		                  	  	<div class="block-content text-right">
									<button id="btnCreateNew" type="button" class="btn btn-success mr-1">
			                            <i class="fa fa-fw fa-plus mr-1"></i> Add Point Of Interest
			                        </button>
		                  	  	</div>
		                      	<div class="block-content">
		                           <table id="dataTable" class="table table-bordered table-striped table-vcenter">
		                               <thead>
		                                   <tr>
		                                       <th>Name</th>
		                                       <th class="text-center" style="width: 100px;">Actions</th>
		                                   </tr>
		                               </thead>
		                               <tbody>
		                               	   <c:forEach items="${hotelPointOfInterestList}" var="hotelPointOfInterestData">
	                               	   			<tr>
													<td class="font-w600">
														${hotelPointOfInterestData.pointOfInterestName}
													</td>
													<td class="text-center">
													   <input type="hidden" class="col-id" value="${hotelPointOfInterestData.id}"/>
													   <input type="hidden" class="col-pointOfInterestId" value="${hotelPointOfInterestData.pointOfInterestId}"/>
													   <input type="hidden" class="col-providerId" value="${hotelPointOfInterestData.providerId}"/>
			                                           <div class="btn-group">
			                                               <button type="button" class="btn btn-sm btn-primary delete-button" data-toggle="tooltip" title="Remove">
			                                                   <i class="fa fa-times"></i>
			                                               </button>
			                                           </div>
			                                       </td>
		                                        </tr>
		                               	   </c:forEach>
		                               </tbody>
		                           </table>
		                      	</div>
		                  		<!-- END Full Table -->
                    	 	</div>
                    	 </div>
                     </div>
            	</div>
            	<!-- END Full Table -->
            </div>
            <!-- END Page Content -->
		</main>
		<!-- END Main Container -->

		<template id="formTemplete">
	        <form id="formComponent" action="#" method="post">
	            <!-- Basic Elements -->
	            <div class="row items-push">
	                <div class="col-sm-1 col-lg-1 col-xl-3">
	                    <p class="text-muted"></p>
	                </div>
	                <div class="col-sm-10 col-lg-10 col-xl-6">
	                	<div class="form-group">
	                		<input id="val-providerId" type="hidden" name="providerId" value="#[providerId]"/>
	                	</div>
	                    <div class="form-group">
	                        <label for="val-pointOfInterestId">Name <span class="text-danger">*</span></label>
	                        <select class="form-control" id="val-pointOfInterestId" name="pointOfInterestId">
                            </select>
	                    </div>
	                    <div class="form-group mt-5">
	                     	<div class="row items-push">
	                     		<div class="col-lg-6 col-sm-6 col-xs-12 text-left">
	                     			<input id="val-id" type="hidden" name="id" value="#[id]"/>
	                     			<button id="btnSubmit" type="button" class="btn btn-primary">Save</button>
	                     			<button id="btnCancel" type="button" class="btn btn-primary">Cancel</button>
	                     		</div>
	                     		<div class="col-lg-6 col-sm-6 col-xs-12 text-right">
	                     			<button id="btnReset" type="reset" class="btn btn-primary">Reset</button>
	                     		</div>
	                     	</div>
	                     </div>
	                </div>
	                <div class="col-sm-1 col-lg-1 col-xl-3">
	                    <p class="text-muted"></p>
	                </div>
	            </div>
	        </form>
        </template>
        <template id="rowTemplete">
	        <td class="font-w600">
				#[pointOfInterestName]
			</td>
			<td class="text-center">
				<input type="hidden" class="col-id" value="#[id]"/>
			    <input type="hidden" class="col-pointOfInterestId" value="#[pointOfInterestId]"/>
			    <input type="hidden" class="col-providerId" value="#[providerId]"/>
		        <div class="btn-group">
			        <button type="button" class="btn btn-sm btn-primary delete-button" data-toggle="tooltip" title="Remove">
			           <i class="fa fa-times"></i>
			        </button>
		        </div>
		     </td>
        </template>

		<%@include file="../includes/footer.jsp" %>
        <%@include file="../includes/scripts.jsp" %>
        
        <!-- Modal Pop Up -->
        <%@include file="../hotel/hotelsearch.jsp" %>
        <!-- END Modal Pop Up -->

		<script src="<c:url value="/resources/js/custom/hotel/hotelpointofinterest.js"/>"></script>

		<script type="text/javascript">
			hotelpointofinterest.isAvailableUrl = '<c:url value="/hotelpointofinterest/isAvailable/" />';
			hotelpointofinterest.getPointOfInterestListUrl = '<c:url value="/pointofinterest/fetch/0/{#providerTypeId}/1" />';
			hotelpointofinterest.getHotelPointOfInterestListUrl = '<c:url value="/hotelpointofinterest/fetch/0/{#providerId}/0" />';
			hotelpointofinterest.createHotelPointOfInterestUrl = '<c:url value="/hotelpointofinterest/create" />';
			hotelpointofinterest.updateHotelPointOfInterestUrl = '<c:url value="/hotelpointofinterest/update" />';
			hotelpointofinterest.deleteHotelPointOfInterestUrl = '<c:url value="/hotelpointofinterest/delete/" />';
		</script>
	</body>
</html>