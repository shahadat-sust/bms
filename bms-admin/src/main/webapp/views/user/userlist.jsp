<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
    <head>
        <title>BMS - User List</title>
        <meta name="description" content="BMS - User List">
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
                         <h1 class="flex-sm-fill font-size-h2 font-w400 mt-2 mb-0 mb-sm-2">User List</h1>
                         <nav class="flex-sm-00-auto ml-sm-3" aria-label="breadcrumb">
                             <ol class="breadcrumb">
                                 <li class="breadcrumb-item">User</li>
                                 <li class="breadcrumb-item active" aria-current="page">User List</li>
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
                            <i class="fa fa-fw fa-plus mr-1"></i> Create User
                        </button>
                  	  </div>
                      <div class="block-content">
                           <table id="dataTable" class="table table-bordered table-striped table-vcenter">
                               <thead>
                                   <tr>
                                  	   <th class="text-center" style="width: 100px;">
                                           <i class="far fa-user"></i>
                                       </th>
                                       <th style="width: 30%;">User Name</th>
                                       <th style="width: 30%;">Full Name</th>
                                       <th style="width: 15%;">Active</th>
                                       <th class="text-center" style="width: 100px;">Actions</th>
                                   </tr>
                               </thead>
                               <tbody>
                               	   <c:forEach items="${userList}" var="userData">
                               	   		<tr>
                               	   			<td class="text-center">
                                                <img class="img-avatar img-avatar48" src="resources/media/avatars/avatar3.jpg" alt="">
                                            </td>
                                            <td class="font-w600">
												${userData.username}
											</td>
											<td class="font-w600">
												${userData.userProfileData.firstName}&nbsp;${userData.userProfileData.lastName}
											</td>
											<td class="font-w600">
												${userData.active}
											</td>
											<td class="text-center">
											   <input type="hidden" class="col-id" value="${userData.id}"/>
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
        
		<%@include file="../includes/footer.jsp" %>
        <%@include file="../includes/scripts.jsp" %>  
    </body>
</html>