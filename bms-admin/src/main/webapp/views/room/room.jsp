<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>BMS - Room</title>
		<meta name="description" content="BMS - Room">
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
                        <h1 class="flex-sm-fill font-size-h2 font-w400 mt-2 mb-0 mb-sm-2">Room</h1>
                        <nav class="flex-sm-00-auto ml-sm-3" aria-label="breadcrumb">
                            <ol class="breadcrumb">
                                <li class="breadcrumb-item">Hotel Management</li>
                                <li class="breadcrumb-item">Room Info</li>
                                <li class="breadcrumb-item active" aria-current="page">Room</li>
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
                    			<h2 class="content-heading pt-0">Select Hotel</h2>
                    			<div class="row items-push">
                    				<div class="col-md-6">
                    					<div class="form-group row">
		                                    <label class="col-sm-4 col-form-label" for="var-title">
		                                    	<strong class="text-muted">Title</strong>
		                                   	</label>
		                                    <div class="col-sm-8">
		                                    	<input type="hidden" class="form-control" id="var-selected-providerId" value="${sessionScope.DEFAULT_HOTEL.providerId}"/>
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
                    	 		<h2 class="content-heading pt-0">Room List</h2>
                   	 			<!-- Full Table -->
		                  	  	<div class="block-content text-right">
									<button id="btnCreateNew" type="button" class="btn btn-success mr-1">
			                            <i class="fa fa-fw fa-plus mr-1"></i> Add Room
			                        </button>
		                  	  	</div>
		                      	<div class="block-content">
		                           <table id="dataTable" class="table table-bordered table-striped table-vcenter">
		                               <thead>
		                                   <tr>
		                                   	   <th style="width: 100px;">Room No</th>
		                                       <th style="width: 15%;">Type</th>
		                                       <th style="width: 15%;">Category</th>
		                                       <th style="width: 100px;">Rent</th>
		                                       <th class="d-none d-xl-table-cell" style="width: 100px;">Size</th>
		                                       <th class="d-none d-lg-table-cell" style="width: 100px;">Floor No</th>
		                                       <th class="d-none d-xl-table-cell" style="width: 120px;">Land Line</th>
		                                       <th class="d-none d-lg-table-cell text-center" style="width: 80px;">Active</th>
		                                       <th class="text-center" style="width: 100px;">Actions</th>
		                                   </tr>
		                               </thead>
		                               <tbody>
		                               	   <c:forEach items="${roomList}" var="itemData">
	                               	   			<tr>
	                               	   				<td class="font-w600">
														${itemData.itemNo}
													</td>
													<td class="font-w600">
														${itemData.itemTypeName}
													</td>
													<td class="font-w600">
														${itemData.itemCategoryName}
													</td>
													<td class="text-right" class="font-w600">
														${itemData.rent}
													</td>
													<td class="d-none d-xl-table-cell text-right font-w600">
														${itemData.roomData.size}
													</td>
													<td class="d-none d-lg-table-cell text-right font-w600">
														${itemData.roomData.floorNo}
													</td>
													<td class="d-none d-xl-table-cell text-right font-w600">
														${itemData.roomData.landLine}
													</td>
													<td class="d-none d-lg-table-cell text-center font-w600">
														${itemData.active}
													</td>
													<td class="text-center">
													   <input type="hidden" class="col-id" value="${itemData.id}"/>
													   <input type="hidden" class="col-itemTypeId" value="${itemData.itemTypeId}"/>
													   <input type="hidden" class="col-itemTypeName" value="${itemData.itemTypeName}"/>
													   <input type="hidden" class="col-itemCategoryId" value="${itemData.itemCategoryId}"/>
													   <input type="hidden" class="col-itemCategoryName" value="${itemData.itemCategoryName}"/>
													   <input type="hidden" class="col-itemNo" value="${itemData.itemNo}"/>
													   <input type="hidden" class="col-rent" value="${itemData.rent}"/>
													   <input type="hidden" class="col-size" value="${itemData.roomData.size}"/>
													   <input type="hidden" class="col-floorNo" value="${itemData.roomData.floorNo}"/>
													   <input type="hidden" class="col-landLine" value="${itemData.roomData.landLine}"/>
													   <input type="hidden" class="col-active" value="${itemData.active}"/>
			                                           <div class="btn-group">
			                                               <button type="button" class="btn btn-sm btn-primary edit-button" data-toggle="tooltip" title="Edit">
			                                                   <i class="fa fa-pencil-alt"></i>
			                                               </button>
			                                               <button type="button" class="btn btn-sm btn-primary delete-button" data-toggle="tooltip" title="Delete">
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
	                <div class="col-sm-2 col-md-3 col-lg-3 col-xl-4">
	                    <p class="text-muted"></p>
	                </div>
	                <div class="col-sm-8 col-md-6 col-lg-6 col-xl-4">
	                	<div class="form-group">
	                		<label for="val-name">Type <span class="text-danger">*</span></label>
	                		<select class="form-control" id="val-itemTypeId" name="itemTypeId">
                             </select>
	                	</div>
	                	<div class="form-group">
	                		<label for="val-name">Category <span class="text-danger">*</span></label>
	                		<select class="form-control" id="val-itemCategoryId" name="itemCategoryId">
                             </select>
	                	</div>
	                	<div class="form-group">
	                        <label for="val-name">Room No <span class="text-danger">*</span></label>
	                        <input class="form-control" type="text" id="val-itemNo" name="itemNo" value="#[itemNo]" placeholder="Enter Room No..">
	                    </div>
	                    <div class="form-group">
	                        <label for="val-name">Rent <span class="text-danger">*</span></label>
	                        <input class="form-control" type="text" id="val-rent" name="rent" value="#[rent]" placeholder="Enter Rent..">
	                    </div>
	                    <div class="form-group">
	                        <label for="val-name">Size <span class="text-danger">*</span></label>
	                        <input class="form-control" type="text" id="val-size" name="roomData.size" value="#[size]" placeholder="Enter Size..">
	                    </div>
	                    <div class="form-group">
	                        <label for="val-name">Floor No <span class="text-danger">*</span></label>
	                        <input class="form-control" type="text" id="val-floorNo" name="roomData.floorNo" value="#[floorNo]" placeholder="Enter Floor No..">
	                    </div>
	                    <div class="form-group">
	                        <label for="val-name">Land Line </label>
	                        <input class="form-control" type="text" id="val-landLine" name="roomData.landLine" value="#[landLine]" placeholder="Enter Land Line..">
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
	                     			<button id="btnSubmit" type="button" class="btn btn-primary">Save</button>
	                     			<button id="btnCancel" type="button" class="btn btn-primary">Cancel</button>
	                     		</div>
	                     		<div class="col-lg-6 col-sm-6 col-xs-12 text-right">
	                     			<button id="btnReset" type="reset" class="btn btn-primary">Reset</button>
	                     		</div>
	                     	</div>
	                     </div>
	                </div>
	                <div class="col-sm-2 col-md-3 col-lg-3 col-xl-4">
	                    <p class="text-muted"></p>
	                </div>
	            </div>
	        </form>
        </template>
        <template id="rowTemplete">
        	<td class="font-w600">
				#[itemNo]
			</td>
        	<td class="font-w600">
				#[itemTypeName]
			</td>
			<td class="font-w600">
				#[itemCategoryName]
			</td>
			<td class="text-right" class="font-w600">
				#[rent]
			</td>
			<td class="d-none d-xl-table-cell text-right font-w600">
				#[size]
			</td>
			<td class="d-none d-lg-table-cell text-right font-w600">
				#[floorNo]
			</td>
			<td class="d-none d-xl-table-cell text-right font-w600">
				#[landLine]
			</td>
			<td class="d-none d-lg-table-cell text-center font-w600">
				#[active]
			</td>
			<td class="text-center">
			   <input type="hidden" class="col-id" value="#[id]"/>
			   <input type="hidden" class="col-itemTypeId" value="#[itemTypeId]"/>
			   <input type="hidden" class="col-itemTypeName" value="#[itemTypeName]"/>
			   <input type="hidden" class="col-itemCategoryId" value="#[itemCategoryId]"/>
			   <input type="hidden" class="col-itemCategoryName" value="#[itemCategoryName]"/>
			   <input type="hidden" class="col-itemNo" value="#[itemNo]"/>
			   <input type="hidden" class="col-rent" value="#[rent]"/>
			   <input type="hidden" class="col-size" value="#[size]"/>
			   <input type="hidden" class="col-floorNo" value="#[floorNo]"/>
			   <input type="hidden" class="col-landLine" value="#[landLine]"/>
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

		<script src="<c:url value="/resources/js/custom/room/room.js"/>"></script>

		<script type="text/javascript">
			room.getRoomListUrl = '<c:url value="/room/fetch/0/{#providerId}/0" />';
			room.getRoomTypeListUrl = '<c:url value="/roomtype/fetch/0/{#providerId}/1" />';
			room.getRoomCategoryListUrl = '<c:url value="/roomcategory/fetch/0/{#itemTypeId}/0/1" />';
			room.getRoomCategoryUrl = '<c:url value="/roomcategory/fetch/{#itemCategoryId}/0/0/0" />';
			room.isRoomNoAvailableUrl = '<c:url value="/room/isRoomNoAvailable" />';
			room.createRoomUrl = '<c:url value="/room/create" />';
			room.updateRoomUrl = '<c:url value="/room/update" />';
			room.deleteRoomUrl = '<c:url value="/room/delete/" />';
		</script>
	</body>
</html>