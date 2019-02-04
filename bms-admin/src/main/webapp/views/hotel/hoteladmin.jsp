<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>BMS - Hotel Admin</title>
		<meta name="description" content="BMS - Hotel Admin">
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
                    		<div class="col-md-4">
                    			<h2 class="content-heading pt-0">Select User</h2>
                    			<div class="form-group row">
                                    <label class="col-sm-4 col-form-label" for="var-name"><strong class="text-muted">Name</strong></label>
                                    <div class="col-sm-8">
                                    	<input type="hidden" class="form-control" id="var-selected-userId"/>
                                        <input type="text" class="form-control" readonly="readonly" id="var-name" value="Name.."/>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-sm-4 col-form-label" for="var-username"><strong class="text-muted">Username</strong></label>
                                    <div class="col-sm-8">
                                        <input type="text" class="form-control" readonly="readonly" id="var-username" value="Username.."/>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-sm-4 col-form-label" for="var-email"><strong class="text-muted">Email</strong></label>
                                    <div class="col-sm-8">
                                        <input type="text" class="form-control" readonly="readonly" id="var-email" value="Email.."/>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-sm-4 col-form-label" for="var-phoneNumber"><strong class="text-muted">Phone Number</strong></label>
                                    <div class="col-sm-8">
                                        <input type="text" class="form-control" readonly="readonly" id="var-phoneNumber" value="Phone Number.."/>
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

		<script src="<c:url value="/resources/js/custom/hotel/hoteladmin.js"/>"></script>

		<script type="text/javascript">
			hoteladmin.getAssignableHotelsUrl = '<c:url value="/assignablehotels" />';
			hoteladmin.getAssignHotelsUrl = '<c:url value="/assignhotel" />';
		</script>
	</body>
</html>