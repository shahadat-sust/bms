<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
    <head>
        <title>BMS - Booking Type Setup</title>
        <meta name="description" content="BMS - Booking Type Setup">
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
                         <h1 class="flex-sm-fill font-size-h2 font-w400 mt-2 mb-0 mb-sm-2">Booking Type Setup</h1>
                         <nav class="flex-sm-00-auto ml-sm-3" aria-label="breadcrumb">
                             <ol class="breadcrumb">
                                 <li class="breadcrumb-item">Setup</li>
                                 <li class="breadcrumb-item active" aria-current="page">Booking Type</li>
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
						<button id="btnCreateNew" type="button" class="btn btn-success mr-1">
                            <i class="fa fa-fw fa-plus mr-1"></i> Add Booking Type
                        </button>
                  	  </div>
                      <div class="block-content">
                           <table id="dataTable" class="table table-bordered table-striped table-vcenter">
                               <thead>
                                   <tr>
                                   	   <th style="width: 20%;">ID</th>
                                       <th style="width: 40%;">Name</th>
                                       <th style="width: 40%;">Remarks</th>
                                       <th class="text-center" style="width: 100px;">Actions</th>
                                   </tr>
                               </thead>
                               <tbody>
                               	   <c:forEach items="${bookingTypeList}" var="bookingTypeData">
                               	   		<tr>
                               	   			<td class="font-w600">
												${bookingTypeData.id}
											</td>
											<td class="font-w600">
												${bookingTypeData.name}
											</td>
											<td>
												${bookingTypeData.remarks}
											</td>
											<td class="text-center">
											   <input type="hidden" class="col-id" value="${bookingTypeData.id}"/>
											   <input type="hidden" class="col-name" value="${bookingTypeData.name}"/>
											   <input type="hidden" class="col-remarks" value="${bookingTypeData.remarks}"/>
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
	                <div class="col-lg-2 col-xl-4">
	                    <p class="text-muted"></p>
	                </div>
	                <div class="col-lg-8 col-xl-5">
	                	<div class="form-group">
	                        <label for="val-id">ID <span class="text-danger">*</span></label>
	                        <input class="form-control" type="text" id="val-id" name="id" value="#[id]" placeholder="Enter ID..">
	                    </div>
	                    <div class="form-group">
	                        <label for="val-name">Name <span class="text-danger">*</span></label>
	                        <input class="form-control" type="text" id="val-name" name="name" value="#[name]" placeholder="Enter Name..">
	                    </div>
	                    <div class="form-group">
	                         <label for="val-remarks">Remarks</label>
	                         <textarea class="form-control" id="val-remarks" name="remarks" rows="5" placeholder="Enter Remarks..">#[remarks]</textarea>
	                     </div>
	                     <div class="form-group">
	                     	<div class="row items-push">
	                     		<div class="col-lg-6 col-sm-6 col-xs-12 text-left">
	                     			<button id="btnSubmit" type="button" class="btn btn-primary">Save</button>
	                     			<button id="btnCancel" type="button" class="btn btn-primary">Cancel</button>
	                     		</div>
	                     		<div class="col-lg-6 col-sm-6 col-xs-12 text-right">
	                     			<button id="btnReset" type="reset" class="btn btn-primary">Reset</button>
	                     		</div>
	                     	</div>
	                     </div>
	                </div>
	                <div class="col-lg-2  col-xl-3">
	                    <p class="text-muted"></p>
	                </div>
	            </div>
	        </form>
        </template>
        <template id="rowTemplete">
        	<td class="font-w600">
				#[id]
			</td>
	        <td class="font-w600">
				#[name]
			</td>
			<td>
				#[remarks]
			</td>
			<td class="text-center">
				<input type="hidden" class="col-id" value="#[id]"/>
				<input type="hidden" class="col-name" value="#[name]"/>
				<input type="hidden" class="col-remarks" value="#[remarks]"/>
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

        <!-- Page JS Code -->
        <script src="<c:url value="/resources/js/custom/setup/bookingtype.js"/>"></script>   
		<script type="text/javascript">
			bookingTypeSetup.fetchUrl = '<c:url value="/bookingtype/fetch/" />';
			bookingTypeSetup.createUrl = '<c:url value="/bookingtype/create" />';
			bookingTypeSetup.updateUrl = '<c:url value="/bookingtype/update" />';
			bookingTypeSetup.deleteUrl = '<c:url value="/bookingtype/delete/" />';
		</script>
    </body>
</html>