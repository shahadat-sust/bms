<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
    <head>
        <title>BMS - City Setup</title>
        <meta name="description" content="BMS - Admin Dashboard">
        
        <!-- Stylesheets -->
        <!-- Page JS Plugins CSS -->
        <link rel="stylesheet" href="resources/js/plugins/datatables/dataTables.bootstrap4.css">
        <link rel="stylesheet" href="resources/js/plugins/datatables/buttons-bs4/buttons.bootstrap4.min.css">
        
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
                         <h1 class="flex-sm-fill font-size-h2 font-w400 mt-2 mb-0 mb-sm-2">City Setup</h1>
                         <nav class="flex-sm-00-auto ml-sm-3" aria-label="breadcrumb">
                             <ol class="breadcrumb">
                                 <li class="breadcrumb-item">Setup</li>
                                 <li class="breadcrumb-item active" aria-current="page">City</li>
                             </ol>
                         </nav>
                     </div>
                 </div>
             </div>
             <!-- END Hero -->

			<!-- Page Content -->
            <div class="content">
				 <!-- Dynamic Table Full Pagination -->
                 <div class="block block-rounded block-bordered">
                     <div class="block-content block-content-full">
                         <!-- DataTables init on table by adding .js-dataTable-full-pagination class, functionality initialized in js/pages/be_tables_datatables.js -->
                         <table class="table table-bordered table-striped table-vcenter js-dataTable-full-pagination">
                             <thead>
                                 <tr>
                                     <th class="text-center" style="width: 80px;">#</th>
                                     <th>Name</th>
                                     <th class="d-none d-sm-table-cell" style="width: 30%;">Email</th>
                                     <th class="d-none d-sm-table-cell" style="width: 15%;">Access</th>
                                     <th style="width: 15%;">Registered</th>
                                     <th class="text-center" style="width: 100px;">Actions</th>
                                 </tr>
                             </thead>
                             <tbody>
                                 <tr>
                                     <td class="text-center">1</td>
                                     <td class="font-w600">Lori Moore</td>
                                     <td class="d-none d-sm-table-cell">
                                         client1<em class="text-muted">@example.com</em>
                                     </td>
                                     <td class="d-none d-sm-table-cell">
                                         <span class="badge badge-warning">Trial</span>
                                     </td>
                                     <td>
                                         <em class="text-muted">10 days ago</em>
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
                                     <td class="text-center">2</td>
                                     <td class="font-w600">Sara Fields</td>
                                     <td class="d-none d-sm-table-cell">
                                         client2<em class="text-muted">@example.com</em>
                                     </td>
                                     <td class="d-none d-sm-table-cell">
                                         <span class="badge badge-info">Business</span>
                                     </td>
                                     <td>
                                         <em class="text-muted">8 days ago</em>
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
                                     <td class="text-center">3</td>
                                     <td class="font-w600">Jack Estrada</td>
                                     <td class="d-none d-sm-table-cell">
                                         client3<em class="text-muted">@example.com</em>
                                     </td>
                                     <td class="d-none d-sm-table-cell">
                                         <span class="badge badge-warning">Trial</span>
                                     </td>
                                     <td>
                                         <em class="text-muted">4 days ago</em>
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
                                     <td class="text-center">4</td>
                                     <td class="font-w600">Amber Harvey</td>
                                     <td class="d-none d-sm-table-cell">
                                         client4<em class="text-muted">@example.com</em>
                                     </td>
                                     <td class="d-none d-sm-table-cell">
                                         <span class="badge badge-success">VIP</span>
                                     </td>
                                     <td>
                                         <em class="text-muted">2 days ago</em>
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
                                     <td class="text-center">5</td>
                                     <td class="font-w600">Albert Ray</td>
                                     <td class="d-none d-sm-table-cell">
                                         client5<em class="text-muted">@example.com</em>
                                     </td>
                                     <td class="d-none d-sm-table-cell">
                                         <span class="badge badge-info">Business</span>
                                     </td>
                                     <td>
                                         <em class="text-muted">6 days ago</em>
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
                                     <td class="text-center">6</td>
                                     <td class="font-w600">Brian Stevens</td>
                                     <td class="d-none d-sm-table-cell">
                                         client6<em class="text-muted">@example.com</em>
                                     </td>
                                     <td class="d-none d-sm-table-cell">
                                         <span class="badge badge-primary">Personal</span>
                                     </td>
                                     <td>
                                         <em class="text-muted">9 days ago</em>
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
                                     <td class="text-center">7</td>
                                     <td class="font-w600">Betty Kelley</td>
                                     <td class="d-none d-sm-table-cell">
                                         client7<em class="text-muted">@example.com</em>
                                     </td>
                                     <td class="d-none d-sm-table-cell">
                                         <span class="badge badge-danger">Disabled</span>
                                     </td>
                                     <td>
                                         <em class="text-muted">2 days ago</em>
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
                                     <td class="text-center">8</td>
                                     <td class="font-w600">Carol Ray</td>
                                     <td class="d-none d-sm-table-cell">
                                         client8<em class="text-muted">@example.com</em>
                                     </td>
                                     <td class="d-none d-sm-table-cell">
                                         <span class="badge badge-danger">Disabled</span>
                                     </td>
                                     <td>
                                         <em class="text-muted">2 days ago</em>
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
                                     <td class="text-center">9</td>
                                     <td class="font-w600">Jeffrey Shaw</td>
                                     <td class="d-none d-sm-table-cell">
                                         client9<em class="text-muted">@example.com</em>
                                     </td>
                                     <td class="d-none d-sm-table-cell">
                                         <span class="badge badge-info">Business</span>
                                     </td>
                                     <td>
                                         <em class="text-muted">3 days ago</em>
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
                                     <td class="text-center">10</td>
                                     <td class="font-w600">Albert Ray</td>
                                     <td class="d-none d-sm-table-cell">
                                         client10<em class="text-muted">@example.com</em>
                                     </td>
                                     <td class="d-none d-sm-table-cell">
                                         <span class="badge badge-primary">Personal</span>
                                     </td>
                                     <td>
                                         <em class="text-muted">5 days ago</em>
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
                                     <td class="text-center">11</td>
                                     <td class="font-w600">Jeffrey Shaw</td>
                                     <td class="d-none d-sm-table-cell">
                                         client11<em class="text-muted">@example.com</em>
                                     </td>
                                     <td class="d-none d-sm-table-cell">
                                         <span class="badge badge-warning">Trial</span>
                                     </td>
                                     <td>
                                         <em class="text-muted">9 days ago</em>
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
                                     <td class="text-center">12</td>
                                     <td class="font-w600">Jose Parker</td>
                                     <td class="d-none d-sm-table-cell">
                                         client12<em class="text-muted">@example.com</em>
                                     </td>
                                     <td class="d-none d-sm-table-cell">
                                         <span class="badge badge-warning">Trial</span>
                                     </td>
                                     <td>
                                         <em class="text-muted">5 days ago</em>
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
                                     <td class="text-center">13</td>
                                     <td class="font-w600">Barbara Scott</td>
                                     <td class="d-none d-sm-table-cell">
                                         client13<em class="text-muted">@example.com</em>
                                     </td>
                                     <td class="d-none d-sm-table-cell">
                                         <span class="badge badge-danger">Disabled</span>
                                     </td>
                                     <td>
                                         <em class="text-muted">8 days ago</em>
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
                                     <td class="text-center">14</td>
                                     <td class="font-w600">Susan Day</td>
                                     <td class="d-none d-sm-table-cell">
                                         client14<em class="text-muted">@example.com</em>
                                     </td>
                                     <td class="d-none d-sm-table-cell">
                                         <span class="badge badge-danger">Disabled</span>
                                     </td>
                                     <td>
                                         <em class="text-muted">2 days ago</em>
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
                                     <td class="text-center">15</td>
                                     <td class="font-w600">Brian Stevens</td>
                                     <td class="d-none d-sm-table-cell">
                                         client15<em class="text-muted">@example.com</em>
                                     </td>
                                     <td class="d-none d-sm-table-cell">
                                         <span class="badge badge-warning">Trial</span>
                                     </td>
                                     <td>
                                         <em class="text-muted">9 days ago</em>
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
                                     <td class="text-center">16</td>
                                     <td class="font-w600">Judy Ford</td>
                                     <td class="d-none d-sm-table-cell">
                                         client16<em class="text-muted">@example.com</em>
                                     </td>
                                     <td class="d-none d-sm-table-cell">
                                         <span class="badge badge-success">VIP</span>
                                     </td>
                                     <td>
                                         <em class="text-muted">5 days ago</em>
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
                                     <td class="text-center">17</td>
                                     <td class="font-w600">Judy Ford</td>
                                     <td class="d-none d-sm-table-cell">
                                         client17<em class="text-muted">@example.com</em>
                                     </td>
                                     <td class="d-none d-sm-table-cell">
                                         <span class="badge badge-warning">Trial</span>
                                     </td>
                                     <td>
                                         <em class="text-muted">2 days ago</em>
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
                                     <td class="text-center">18</td>
                                     <td class="font-w600">Lori Grant</td>
                                     <td class="d-none d-sm-table-cell">
                                         client18<em class="text-muted">@example.com</em>
                                     </td>
                                     <td class="d-none d-sm-table-cell">
                                         <span class="badge badge-info">Business</span>
                                     </td>
                                     <td>
                                         <em class="text-muted">4 days ago</em>
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
                                     <td class="text-center">19</td>
                                     <td class="font-w600">Judy Ford</td>
                                     <td class="d-none d-sm-table-cell">
                                         client19<em class="text-muted">@example.com</em>
                                     </td>
                                     <td class="d-none d-sm-table-cell">
                                         <span class="badge badge-success">VIP</span>
                                     </td>
                                     <td>
                                         <em class="text-muted">3 days ago</em>
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
                                     <td class="text-center">20</td>
                                     <td class="font-w600">Marie Duncan</td>
                                     <td class="d-none d-sm-table-cell">
                                         client20<em class="text-muted">@example.com</em>
                                     </td>
                                     <td class="d-none d-sm-table-cell">
                                         <span class="badge badge-warning">Trial</span>
                                     </td>
                                     <td>
                                         <em class="text-muted">5 days ago</em>
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
                 <!-- END Dynamic Table Full Pagination -->
            </div>
            <!-- END Page Content -->
        </main>
        <!-- END Main Container -->

		<%@include file="../includes/footer.jsp" %>
        <%@include file="../includes/scripts.jsp" %> 
         
        <!-- Page JS Plugins -->
        <script src="resources/js/plugins/datatables/jquery.dataTables.min.js"></script>
        <script src="resources/js/plugins/datatables/dataTables.bootstrap4.min.js"></script>
        <script src="resources/js/plugins/datatables/buttons/dataTables.buttons.min.js"></script>
        <script src="resources/js/plugins/datatables/buttons/buttons.print.min.js"></script>
        <script src="resources/js/plugins/datatables/buttons/buttons.html5.min.js"></script>
        <script src="resources/js/plugins/datatables/buttons/buttons.flash.min.js"></script>
        <script src="resources/js/plugins/datatables/buttons/buttons.colVis.min.js"></script>
        <!-- Page JS Code -->
        <script src="resources/js/pages/be_tables_datatables.min.js"></script>    
    </body>
</html>