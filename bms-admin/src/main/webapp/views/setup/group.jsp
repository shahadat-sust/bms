<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
    <head>
        <title>BMS - Group Setup</title>
        <meta name="description" content="BMS - Admin Dashboard">
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
                         <h1 class="flex-sm-fill font-size-h2 font-w400 mt-2 mb-0 mb-sm-2">Group Setup</h1>
                         <nav class="flex-sm-00-auto ml-sm-3" aria-label="breadcrumb">
                             <ol class="breadcrumb">
                                 <li class="breadcrumb-item">Setup</li>
                                 <li class="breadcrumb-item active" aria-current="page">Group</li>
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
                          <div class="table-responsive">
                              <table class="table table-bordered table-striped table-vcenter">
                                  <thead>
                                      <tr>
                                          <th>Name</th>
                                          <th style="width: 30%;">Email</th>
                                          <th style="width: 15%;">Access</th>
                                          <th class="text-center" style="width: 100px;">Actions</th>
                                      </tr>
                                  </thead>
                                  <tbody>
                                      <tr>
                                          <td class="font-w600">
                                              <a href="be_pages_generic_profile.html">Marie Duncan</a>
                                          </td>
                                          <td>client1<em class="text-muted">@example.com</em></td>
                                          <td>
                                              <span class="badge badge-success">VIP</span>
                                          </td>
                                          <td class="text-center">
                                              <div class="btn-group">
                                                  <button type="button" class="btn btn-sm btn-primary" data-toggle="tooltip" title="Edit">
                                                      <i class="fa fa-pencil-alt"></i>
                                                  </button>
                                                  <button type="button" class="btn btn-sm btn-primary" data-toggle="tooltip" title="Delete">
                                                      <i class="fa fa-times"></i>
                                                  </button>
                                              </div>
                                          </td>
                                      </tr>
                                      <tr>
                                          <td class="font-w600">
                                              <a href="be_pages_generic_profile.html">Laura Carr</a>
                                          </td>
                                          <td>client2<em class="text-muted">@example.com</em></td>
                                          <td>
                                              <span class="badge badge-danger">Disabled</span>
                                          </td>
                                          <td class="text-center">
                                              <div class="btn-group">
                                                  <button type="button" class="btn btn-sm btn-primary" data-toggle="tooltip" title="Edit">
                                                      <i class="fa fa-pencil-alt"></i>
                                                  </button>
                                                  <button type="button" class="btn btn-sm btn-primary" data-toggle="tooltip" title="Delete">
                                                      <i class="fa fa-times"></i>
                                                  </button>
                                              </div>
                                          </td>
                                      </tr>
                                      <tr>
                                          <td class="font-w600">
                                              <a href="be_pages_generic_profile.html">Barbara Scott</a>
                                          </td>
                                          <td>client3<em class="text-muted">@example.com</em></td>
                                          <td>
                                              <span class="badge badge-warning">Trial</span>
                                          </td>
                                          <td class="text-center">
                                              <div class="btn-group">
                                                  <button type="button" class="btn btn-sm btn-primary" data-toggle="tooltip" title="Edit">
                                                      <i class="fa fa-pencil-alt"></i>
                                                  </button>
                                                  <button type="button" class="btn btn-sm btn-primary" data-toggle="tooltip" title="Delete">
                                                      <i class="fa fa-times"></i>
                                                  </button>
                                              </div>
                                          </td>
                                      </tr>
                                      <tr>
                                          <td class="font-w600">
                                              <a href="be_pages_generic_profile.html">Alice Moore</a>
                                          </td>
                                          <td>client4<em class="text-muted">@example.com</em></td>
                                          <td>
                                              <span class="badge badge-primary">Personal</span>
                                          </td>
                                          <td class="text-center">
                                              <div class="btn-group">
                                                  <button type="button" class="btn btn-sm btn-primary" data-toggle="tooltip" title="Edit">
                                                      <i class="fa fa-pencil-alt"></i>
                                                  </button>
                                                  <button type="button" class="btn btn-sm btn-primary" data-toggle="tooltip" title="Delete">
                                                      <i class="fa fa-times"></i>
                                                  </button>
                                              </div>
                                          </td>
                                      </tr>
                                      <tr>
                                          <td class="font-w600">
                                              <a href="be_pages_generic_profile.html">Betty Kelley</a>
                                          </td>
                                          <td>client5<em class="text-muted">@example.com</em></td>
                                          <td>
                                              <span class="badge badge-info">Business</span>
                                          </td>
                                          <td class="text-center">
                                              <div class="btn-group">
                                                  <button type="button" class="btn btn-sm btn-primary" data-toggle="tooltip" title="Edit">
                                                      <i class="fa fa-pencil-alt"></i>
                                                  </button>
                                                  <button type="button" class="btn btn-sm btn-primary" data-toggle="tooltip" title="Delete">
                                                      <i class="fa fa-times"></i>
                                                  </button>
                                              </div>
                                          </td>
                                      </tr>
                                  </tbody>
                              </table>
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
    </body>
</html>