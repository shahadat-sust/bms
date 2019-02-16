<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>BMS - Room Category</title>
		<meta name="description" content="BMS - Room Category">
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
                        <h1 class="flex-sm-fill font-size-h2 font-w400 mt-2 mb-0 mb-sm-2">Room Category</h1>
                        <nav class="flex-sm-00-auto ml-sm-3" aria-label="breadcrumb">
                            <ol class="breadcrumb">
                                <li class="breadcrumb-item">Hotel Management</li>
                                <li class="breadcrumb-item">Room Info</li>
                                <li class="breadcrumb-item active" aria-current="page">Room Category</li>
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
                    		<div class="col-md-12">
                    			<h2 class="content-heading pt-0">Hotel Info</h2>
                    			<div class="row items-push">
                    				<div class="col-md-6">
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
                    				</div>
                    				<div class="col-md-6">
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
                    			</div>
                    		</div>
                    	 	<div class="col-md-12">
                    	 		<h2 class="content-heading pt-0">Room Category List</h2>
                   	 			<!-- Full Table -->
		                  	  	<div class="block-content row items-push">
                    				<div class="col-xl-6">
                    					<div class="form-group row">
		                                    <label class="col-sm-2 col-form-label" for="val-selected-itemTypeId">
		                                    	<strong class="text-muted">Type</strong>
		                                   	</label>
		                                    <div class="col-sm-8">
		                                    	<select class="form-control" id="val-selected-itemTypeId">
		                                    		<option value="0">Please select</option>
		                                    		<c:forEach items="${roomTypeList}" var="roomTypeData">
		                                    			<option value="${roomTypeData.id}">${roomTypeData.name}</option>
		                                    		</c:forEach>
				                             	</select>
		                                    </div>
		                                    <div class="col-sm-2">
		                                    </div>
		                                </div>
					                </div>
					                <div class="col-xl-6 text-right">
					                	<button id="btnCreateNew" type="button" class="btn btn-success mr-1" disabled="disabled">
				                            <i class="fa fa-fw fa-plus mr-1"></i> Add Room Category
				                        </button>
					                </div>
                   	 			</div>
		                      	<div class="block-content">
		                           <table id="dataTable" class="table table-bordered table-striped table-vcenter">
		                               <thead>
		                                   <tr>
		                                       <th>Name</th>
		                                       <th style="width: 100px;">Rent</th>
		                                       <th style="width: 110px;">Capacity</th>
		                                       <th class="d-none d-xl-table-cell" style="width: 100px;">Size</th>
		                                       <th class="d-none d-xl-table-cell text-center" style="width: 80px;">Active</th>
		                                       <th class="text-center" style="width: 100px;">Actions</th>
		                                   </tr>
		                               </thead>
		                               <tbody>
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
	                <div class="col-sm-1 col-lg-2 col-xl-3">
	                    <p class="text-muted"></p>
	                </div>
	                <div class="col-sm-10 col-lg-8 col-xl-6">
	                    <div class="form-group">
	                        <label for="val-name">Name <span class="text-danger">*</span></label>
	                        <input class="form-control" type="text" id="val-name" name="name" value="#[name]" placeholder="Enter Name..">
	                    </div>
	                    <div class="form-group">
	                        <label for="val-rent">Rent <span class="text-danger">*</span></label>
	                        <input class="form-control" type="text" id="val-rent" name="rent" value="#[rent]" placeholder="Enter Rent..">
	                    </div>
	                    <div class="form-group">
	                        <label for="val-capacity">Capacity <span class="text-danger">*</span></label>
	                        <input class="form-control" type="text" id="val-capacity" name="capacity" value="#[capacity]" placeholder="Enter Capacity..">
	                    </div>
	                    <div class="form-group">
	                        <label for="val-size">Size <span class="text-danger">*</span></label>
	                        <input class="form-control" type="text" id="val-size" name="roomCategoryData.size" value="#[size]" placeholder="Enter Size..">
	                    </div>
	                    <div class="form-group">
	                    	<div class="custom-control custom-checkbox custom-checkbox-square custom-control-lg custom-control-success mb-1">
		            		    <input type="checkbox" class="custom-control-input" name="active" value="#[active]" id="val-active" checked="checked">
		            			<label class="custom-control-label" for="val-active">Is Active</label>
		            		</div>
	                    </div>
	                    <div class="form-group mt-5">
	                     	<div class="row items-push">
	                     		<div class="col-lg-6 col-sm-6 col-xs-12 text-left">
	                     			<input id="val-id" type="hidden" name="id" value="#[id]"/>
	                     			<input id="val-itemTypeId" type="hidden" name="itemTypeId" value="#[itemTypeId]"/>
	                     			<button id="btnSubmit" type="button" class="btn btn-primary">Save</button>
	                     			<button id="btnCancel" type="button" class="btn btn-primary">Cancel</button>
	                     		</div>
	                     		<div class="col-lg-6 col-sm-6 col-xs-12 text-right">
	                     			<button id="btnReset" type="reset" class="btn btn-primary">Reset</button>
	                     		</div>
	                     	</div>
	                     </div>
	                </div>
	                <div class="col-sm-1 col-lg-2 col-xl-3">
	                    <p class="text-muted"></p>
	                </div>
	            </div>
	        </form>
        </template>
        <template id="rowTemplete">
        	<td class="font-w600">
				#[name]
			</td>
			<td class="text-right font-w600">
				#[rent]
			</td>
			<td class="text-right font-w600">
				#[capacity]
			</td>
			<td class="d-none d-xl-table-cell text-right font-w600">
				#[size]
			</td>
			<td class="d-none d-xl-table-cell text-center font-w600">
				#[active]
			</td>
			<td class="text-center">
				<input type="hidden" class="col-id" value="#[id]"/>
			   	<input type="hidden" class="col-name" value="#[name]"/>
			   	<input type="hidden" class="col-rent" value="#[rent]"/>
			   	<input type="hidden" class="col-capacity" value="#[capacity]"/>
			   	<input type="hidden" class="col-size" value="#[size]"/>
			   	<input type="hidden" class="col-itemTypeId" value="#[itemTypeId]"/>
			   	<input type="hidden" class="col-active" value="#[active]"/>
		        <div class="btn-group">
			        <button type="button" class="btn btn-sm btn-primary edit-button" data-toggle="tooltip" title="Edit">
			        	<i class="fa fa-pencil-alt"></i>
			        </button>
			        <button type="button" class="btn btn-sm btn-primary delete-button" data-toggle="tooltip" title="Delete">
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

		<script src="<c:url value="/resources/js/custom/room/roomcategory.js"/>"></script>

		<script type="text/javascript">
			roomcategory.getRoomTypeListUrl = '<c:url value="/roomtype/fetch/0/{#providerId}/1" />';
			roomcategory.getRoomCategoryListUrl = '<c:url value="/roomcategory/fetch/0/{#itemTypeId}/0/0" />';
			roomcategory.createRoomCategoryUrl = '<c:url value="/roomcategory/create" />';
			roomcategory.updateRoomCategoryUrl = '<c:url value="/roomcategory/update" />';
			roomcategory.deleteRoomCategoryUrl = '<c:url value="/roomcategory/delete/" />';
		</script>
	</body>
</html>