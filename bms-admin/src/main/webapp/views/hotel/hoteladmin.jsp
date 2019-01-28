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
			var hotelinfo = {
	       		init : function() {
	       			/* $(".rating").raty({
	            		starType: "i",
	            		hints: ["One Star", "Two Stars", "Three Stars", "Four Stars", "Five Stars"],
	                    starOff: $(this).data("star-off") || "fa fa-fw fa-star text-muted",
	                    starOn: $(this).data("star-on") || "fa fa-fw fa-star text-warning",
	                    readOnly: true
	                });
	       			
	       			hotelsearchmodal.init(hotelinfo.onHotelSelect); */
	       			usersearchmodal.init(hotelinfo.onUserSelect);
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
	       			$('#var-userId').val(o.userId);
	       			$('#var-name').val(o.name);
	       			$('#var-username').val(o.username);
	       			$('#var-email').val(o.email);
	       			$('#var-phoneNumber').val(o.phoneNumber);
	       		}
	        };
	        
	        $(document).ready(function() {
	        	hotelinfo.init();
	        });
		</script>
	</body>
</html>