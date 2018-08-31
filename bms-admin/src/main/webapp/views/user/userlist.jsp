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
                                 <li class="breadcrumb-item">User Management</li>
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
						<a id="btnCreateNew" class="btn btn-success text-white mr-1" href='<c:url value="/createuser"/>'>
                            <i class="fa fa-fw fa-plus mr-1"></i> Create User
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
                                                <img class="img-avatar img-avatar48" src="<c:url value="/resources/media/avatars/avatar3.jpg"/>" alt="">
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
											   <form action='<c:url value="/deleteuser"/>' method="post"><input type="hidden" id="userId" name="userId" value="${userData.id}"/></form>
	                                           <div class="btn-group">
	                                           	   <a class="btn btn-sm btn-primary text-white edit-button" data-toggle="tooltip" title="View" href='<c:url value="/viewuser/${userData.id}"/>'>
	                                                   <i class="fa fa-file"></i>
	                                               </a>
	                                               <a class="btn btn-sm btn-primary text-white edit-button" data-toggle="tooltip" title="Edit" href='<c:url value="/edituser/${userData.id}"/>'>
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
        
        <script type="text/javascript">
        
        var userlist = {
       		init : function() {
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
        	userlist.init();
        });
        
        </script>
    </body>
</html>