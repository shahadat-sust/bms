<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>BMS - Hotel List</title>
		<meta name="description" content="BMS - User List">
		<%@include file="../includes/metadata.jsp"%>
		<%@include file="../includes/appicons.jsp"%>
		<%@include file="../includes/styles.jsp"%>
		
		<link rel="stylesheet" href="<c:url value="/resources/js/plugins/raty-js/jquery.raty.css"/>" />
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
                        <h1 class="flex-sm-fill font-size-h2 font-w400 mt-2 mb-0 mb-sm-2">Hotel List</h1>
                        <nav class="flex-sm-00-auto ml-sm-3" aria-label="breadcrumb">
                            <ol class="breadcrumb">
                                <li class="breadcrumb-item">Hotel Management</li>
                                <li class="breadcrumb-item">Hotel Info</li>
                                <li class="breadcrumb-item active" aria-current="page">Hotel List</li>
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
            		<div class="block-content text-right">
						<a id="btnCreateNew" class="btn btn-success text-white mr-1" href='<c:url value="/createhotel"/>'>
                           <i class="fa fa-fw fa-plus mr-1"></i> Create Hotel
                       	</a>
               	  	</div>
               	  	<%@include file="../includes/formStatus.jsp" %>
               	  	<div class="block-content">
               	  		<table id="dataTable" class="table table-bordered table-striped table-vcenter">
               	  			<thead>
                                <tr>
                               	   <th class="text-center" style="width: 100px;">
                                        <i class="far fa-user"></i>
                                    </th>
                                    <th style="width: 30%;">Title </th>
                                    <th style="width: 30%;">Star Rating</th>
                                    <th style="width: 15%;">Active</th>
                                    <th class="text-center" style="width: 100px;">Actions</th>
                                </tr>
                            </thead>
                            <tbody>
                            	<c:forEach items="${hotelList}" var="providerData">
                            		<tr>
                          	   			<td class="text-center">
                                            <img class="img-avatar img-avatar48" src="<c:url value="/resources/media/avatars/avatar3.jpg"/>" alt="">
                                        </td>
                                        <td class="font-w600">
											${providerData.title}
										</td>
										<td class="font-w600">
											<div class="rating" data-score="${providerData.hotelData.starRating}"></div>
										</td>
										<td class="font-w600">
											${providerData.active}
										</td>
										<td class="text-center">
										   <form action='<c:url value="/deletehotel"/>' method="post"><input type="hidden" id="providerId" name="providerId" value="${providerData.id}"/></form>
	                                          <div class="btn-group">
	                                          	   <a class="btn btn-sm btn-primary text-white edit-button" data-toggle="tooltip" title="View" href='<c:url value="/viewhotel/${providerData.id}"/>'>
	                                                  <i class="fa fa-file"></i>
	                                              </a>
	                                              <a class="btn btn-sm btn-primary text-white edit-button" data-toggle="tooltip" title="Edit" href='<c:url value="/edithotel/${providerData.id}"/>'>
	                                                  <i class="fa fa-pencil-alt"></i>
	                                              </a>
	                                              <a class="btn btn-sm btn-primary text-white delete-button" data-toggle="tooltip" title="Delete" href='#'>
	                                                  <i class="fa fa-times"></i>
	                                              </a>
	                                          </div>
	                                      </td>
                                      </tr>
                            	</c:forEach>
                            </tbody>
               	  		</table>
               	  	</div>
            	</div>
            	<!-- END Full Table -->
            </div>
            <!-- END Page Content -->
		</main>
		<!-- END Main Container -->
		
		<%@include file="../includes/footer.jsp" %>
        <%@include file="../includes/scripts.jsp" %>
        
        <script src="<c:url value="/resources/js/plugins/raty-js/jquery.raty.js"/>"></script>
		
		<script type="text/javascript">
			var hotellist = {
	       		init : function() {
	       			$(".rating").raty({
	            		starType: "i",
	            		hints: ["One Star", "Two Stars", "Three Stars", "Four Stars", "Five Stars"],
	                    starOff: $(this).data("star-off") || "fa fa-fw fa-star text-muted",
	                    starOn: $(this).data("star-on") || "fa fa-fw fa-star text-warning",
	                    readOnly: true
	                });
	       			
	       			$(document).on("click", ".delete-button", function(e) {
	       				var tr = $(this).closest("tr");
	  					swal({
	  		                text: "Do you want to delete this user",
	  		                type: "warning",
	  		                showCancelButton: true,
	  		                confirmButtonClass: "btn btn-danger m-1",
	  		                cancelButtonClass: "btn btn-secondary m-1",
	  		                html: false,
	  		                preConfirm: function(e) {
	  		                    return new Promise(function(e) {
	  		                        setTimeout(function() {
	  		                            e()
	  		                        }, 50)
	  		                    })
	  		                }
	  		            }).then(function(e) {
	  		            	if(e.value) {
	  		            		$($(tr).find("form")[0]).submit();
	  		            	}
	  		            });
	       	       	});
	       		}
	        };
	        
	        $(document).ready(function() {
	        	hotellist.init();
	        });
		</script>
	</body>
</html>