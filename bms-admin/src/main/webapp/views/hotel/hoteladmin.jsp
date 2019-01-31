<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>BMS - Hotel Admin</title>
		<meta name="description" content="BMS - User List">
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
                        <h1 class="flex-sm-fill font-size-h2 font-w400 mt-2 mb-0 mb-sm-2">Hotel Admin</h1>
                        <nav class="flex-sm-00-auto ml-sm-3" aria-label="breadcrumb">
                            <ol class="breadcrumb">
                                <li class="breadcrumb-item">Hotel Management</li>
                                <li class="breadcrumb-item">Hotel Info</li>
                                <li class="breadcrumb-item active" aria-current="page">Hotel Admin</li>
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
                    		<!-- <div class="col-md-6">
                    			<h2 class="content-heading pt-0">Select Hotel</h2>
                    			<div class="form-group row">
                                    <label class="col-sm-4 col-form-label" for="var-title"><strong class="text-muted">Title</strong></label>
                                    <div class="col-sm-8">
                                    	<input type="hidden" class="form-control" id="var-providerId" name="var-providerId"/>
                                        <input type="text" class="form-control" readonly="readonly" id="var-title" name="var-title" value="Hotel Title.."/>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-sm-4 col-form-label" for="var-address"><strong class="text-muted">Address</strong></label>
                                    <div class="col-sm-8">
                                        <textarea class="form-control" rows="3" readonly="readonly" id="var-address" name="var-address">Hotel Address..</textarea>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-sm-4 col-form-label"><strong class="text-muted">Rating</strong></label>
                                    <div class="col-sm-8">
                                        <div id="var-starRating" class="rating form-control" data-score=""></div>
                                    </div>
                                </div>
                             	<div class="form-group row">
                                  	<div class="col-sm-12 text-right">
                                  		<a id="btnHotelSearch" class="btn btn-sm btn-success text-white mr-1" data-toggle="modal" data-target="#hotel-search-modal">
			                           		<i class="fa fa-fw fa-search mr-1"></i> Search
			                       		</a>
                                  	</div>
                             	</div>
                    		</div> -->
                    		<div class="col-md-4">
                    			<h2 class="content-heading pt-0">Select Admin User</h2>
                    			<div class="form-group row">
                                    <label class="col-sm-4 col-form-label" for="var-name"><strong class="text-muted">Name</strong></label>
                                    <div class="col-sm-8">
                                    	<input type="hidden" class="form-control" id="var-userId" name="var-userId"/>
                                        <input type="text" class="form-control" readonly="readonly" id="var-name" name="var-name" value="Name.."/>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-sm-4 col-form-label" for="var-username"><strong class="text-muted">Username</strong></label>
                                    <div class="col-sm-8">
                                        <input type="text" class="form-control" readonly="readonly" id="var-username" name="var-username" value="Username.."/>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-sm-4 col-form-label" for="var-email"><strong class="text-muted">Email</strong></label>
                                    <div class="col-sm-8">
                                        <input type="text" class="form-control" readonly="readonly" id="var-email" name="var-email" value="Email.."/>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-sm-4 col-form-label" for="var-phoneNumber"><strong class="text-muted">Phone Number</strong></label>
                                    <div class="col-sm-8">
                                        <input type="text" class="form-control" readonly="readonly" id="var-phoneNumber" name="var-phoneNumber" value="Phone Number.."/>
                                    </div>
                                </div>
                             	<div class="form-group row">
                                  	<div class="col-sm-12 text-right">
                                  		<a id="btnUserSearch" class="btn btn-sm btn-success text-white mr-1" data-toggle="modal" data-target="#user-search-modal">
			                           		<i class="fa fa-fw fa-search mr-1"></i> Search
			                       		</a>
                                  	</div>
                             	</div>
                    		</div>
                    	 	<div class="col-md-8">
                    	 		<h2 class="content-heading pt-0">Hotel List</h2>
                    	 		<div class="block-content">
			               	  		<table id="dataTable" class="table table-bordered table-striped table-vcenter">
			               	  			<thead>
			                                <tr>
			                                    <th>Title </th>
			                                    <th class="d-none d-lg-table-cell" style="width: 20%;">City</th>
			                                    <th class="d-none d-xl-table-cell" style="width: 20%;">Country</th>
			                                    <th class="d-none d-xl-table-cell" style="width: 150px;">Star Rating</th>
			                                    <th class="text-center" style="width: 70px;">Assign</th>
			                                </tr>
			                            </thead>
			                            <tbody>
			                            	
			                            </tbody>
			               	  		</table>
			               	  	</div>
                    	 	</div>
                    	 </div>
                     </div>
            	</div>
            	<!-- END Full Table -->
            </div>
            <!-- END Page Content -->
		</main>
		<!-- END Main Container -->

		<%@include file="../includes/footer.jsp" %>
        <%@include file="../includes/scripts.jsp" %>
        
        <!-- Modal Pop Up -->
        <%-- <%@include file="hotelsearch.jsp" %> --%>
        <%@include file="../user/usersearch.jsp" %>
        <!-- END Modal Pop Up -->

		<script type="text/javascript">
			var hoteladmin = {
				userInfo: undefined,
				getAssignableHotelsUrl: '<c:url value="/assignablehotels" />',
				getAssignableHotelAjax: undefined,
				getAssignHotelsUrl: '<c:url value="/assignhotel" />',
				getAssignHotelAjax: undefined,
					
	       		init : function() {
	       			$(document).on("change", "input[type=checkbox][name=assign-providerId-group]", function() {
	       				var isAssign = $(this).is(':checked');
	       				var providerId = $(this).closest("td").find("input").val();
	       				hoteladmin.doAssignHotel(this, hoteladmin.userInfo.userId, providerId, isAssign);
	       			});
	       			
	       			/* $(".rating").raty({
	            		starType: "i",
	            		hints: ["One Star", "Two Stars", "Three Stars", "Four Stars", "Five Stars"],
	                    starOff: $(this).data("star-off") || "fa fa-fw fa-star text-muted",
	                    starOn: $(this).data("star-on") || "fa fa-fw fa-star text-warning",
	                    readOnly: true
	                });
	       			
	       			hotelsearchmodal.init(hotelinfo.onHotelSelect); */
	       			usersearchmodal.init(hoteladmin.onUserSelect);
	       		},
	       		/* onHotelSelect : function(o) {
	       			$('#var-providerId').val(o.providerId);
	       			$('#var-title').val(o.title);
	       			$('#var-address').val(o.address);
	       			$('#var-starRating').data("score", o.starRating);
	       			
	       			$(".rating").raty({
	            		starType: "i",
	            		hints: ["One Star", "Two Stars", "Three Stars", "Four Stars", "Five Stars"],
	                    starOff: $(this).data("star-off") || "fa fa-fw fa-star text-muted",
	                    starOn: $(this).data("star-on") || "fa fa-fw fa-star text-warning",
	                    readOnly: true
	                });
	       		}, */
	       		onUserSelect : function(o) {
	       			hoteladmin.userInfo = o;
	       			$('#var-userId').val(o.userId);
	       			$('#var-name').val(o.name);
	       			$('#var-username').val(o.username);
	       			$('#var-email').val(o.email);
	       			$('#var-phoneNumber').val(o.phoneNumber);
	       			hoteladmin.getAssignableHotels(o.userId);
	       		},
	       		doAssignHotel : function(_chk, userId, providerId, isAssign) {
	       			$.ajax({
	       				type: "POST",
	       	            contentType: "application/json",
	       	            url: hoteladmin.getAssignHotelsUrl,
	       	            data: JSON.stringify({"userId": userId, "providerId": providerId, "isAssign": isAssign}),
	       	            dataType: 'json',
	       	            timeout: 600000,
	       	            success: function (data) {
	       	            	if(!data.status) {
	       	            		console.log(data.errors);
	       	            		$(_chk).attr('checked', !isAssign);
	       	            		Dashmix.helpers('notify', {
	       	                		align: 'center',
	       	                		type: 'danger', 
	       	                		icon: 'fa fa-times mr-1', 
	       	                		message: data.errors && data.errors.length > 0 ? data.errors[0] : 'Failed to process request, please try again!',
	       	                		delay: 1e3
	       	        			});
	       	            	}
	       	            },
	       	            error: function (e) {
	       	            	$(_chk).attr('checked', !isAssign);
	       	            	Dashmix.helpers('notify', {
	       	            		align: 'center',
	       	            		type: 'danger', 
	       	            		icon: 'fa fa-times mr-1', 
	       	            		message: 'Failed to process request, please try again!',
	       	            		delay: 1e3
	       	    			});
	       	            }
	       			});
	       		},
	       		getAssignableHotels : function(userId) {
					var url = hoteladmin.getAssignableHotelsUrl + "?userId=" + userId;
					hoteladmin.getAssignableHotelAjax = $.ajax({
						type: "GET",
			            contentType: "application/json",
			            url: url,
			            dataType: 'json',
			            timeout: 600000,
			            success: function (r) {
			            	if(r.status) {
			            		hoteladmin.getAssignableHotelAjax = undefined;
			            		
			    				if (r.datas.length > 0) {
			            			var html = "";
		
			            			$.each(r.datas, function (index, data) {
			            				var checked = (data.id > 0 ? 'checked' : '');
				            			html += '<tr>';
				            			html += '	 <td class="font-w600">'; 
				            			html += '        <a href="#">' + data.title + '</a>';
			            				html += '    </td>';
			            				html += '    <td class="d-none d-lg-table-cell" class="font-w600">';
			            				html += '        ' + data.cityName;
		            					html += '    </td>';
		           						html += '    <td class="d-none d-xl-table-cell" class="font-w600">';
		        						html += '        ' + data.countryName;
			            				html += '    </td>';
			            				html += '    <td class="d-none d-xl-table-cell" class="font-w600">';
			            				html += '        <div class="rating" data-score="'  + data.starRating + '"></div>'
			            				html += '    </td>';
			            				html += '    <td>';
			            				html += '        <input type="hidden" value="' + data.providerId + '"/>';
			            				html += '        <div class="custom-control custom-switch custom-control-success custom-control-lg mb-2">';
			            				html += '        	<input type="checkbox" class="custom-control-input" name="assign-providerId-group" id="var-assign-providerId-' + data.providerId + '" ' + checked + '/>';
			            				html += '        	<label class="custom-control-label" for="var-assign-providerId-' + data.providerId + '"></label>';
			            				html += '        </div>';
			            				html += '    </td>';
			            				html += '</tr>';
			            			});
			            			
			            			$('#dataTable').find('tbody').html(html);
			            			
			            			$(".rating").raty({
			        					starType: "i",
			        	        		hints: ["One Star", "Two Stars", "Three Stars", "Four Stars", "Five Stars"],
			        	                starOff: $(this).data("star-off") || "fa fa-fw fa-star text-muted",
			        	                starOn: $(this).data("star-on") || "fa fa-fw fa-star text-warning",
			                            readOnly: true
			        	            });
			    				} else {
			    					$('#dataTable').find('tbody').html('');
			    				}
			            	} else {
			            		console.log(r.errors);
			            		hoteladmin.getAssignableHotelAjax = undefined;
			            		$('#dataTable').find('tbody').html('');
			            	}
			            },
			            error: function (e) {
			            	hoteladmin.getAssignableHotelAjax = undefined;
			            	$('#dataTable').find('tbody').html('');
			            }
					});
				}
	        };
	        
	        $(document).ready(function() {
	        	hoteladmin.init();
	        });
		</script>
	</body>
</html>