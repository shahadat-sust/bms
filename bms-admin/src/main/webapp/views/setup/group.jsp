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
                  	  <div class="block-content text-right">
						<button id="btnAddNew" type="button" class="btn btn-success mr-1">
                            <i class="fa fa-fw fa-plus mr-1"></i> Add Group
                        </button>
                  	  </div>
                      <div class="block-content">
                           <table id="dataTable" class="table table-bordered table-striped table-vcenter">
                               <thead>
                                   <tr>
                                       <th style="width: 50%;">Name</th>
                                       <th style="width: 50%;">Remarks</th>
                                       <th class="text-center" style="width: 100px;">Actions</th>
                                   </tr>
                               </thead>
                               <tbody>
                               	   <c:forEach items="${groupList}" var="groupData">
                               	   		<tr>
											<td class="font-w600">
												${groupData.name}
											</td>
											<td>
												${groupData.remarks}
											</td>
											<td class="text-center">
											   <input type="hidden" class="col-id" value="${groupData.id}"/>
											   <input type="hidden" class="col-name" value="${groupData.name}"/>
											   <input type="hidden" class="col-remarks" value="${groupData.remarks}"/>
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
        <div id="formTemplete" style="display: none;">
	        <form class="js-validation" action="" method="post">
	            <!-- Basic Elements -->
	            <div class="row items-push">
	                <div class="col-lg-2 col-xl-4">
	                    <p class="text-muted"></p>
	                </div>
	                <div class="col-lg-8 col-xl-5">
	                    <div class="form-group">
	                        <label for="val-name">Name <span class="text-danger">*</span></label>
	                        <input type="text" class="form-control" id="val-name" name="name" value="#[name]" placeholder="Enter Name..">
	                    </div>
	                    <div class="form-group">
	                         <label for="val-remarks">Remarks</label>
	                         <textarea class="form-control" id="val-remarks" name="remarks" rows="5" placeholder="Enter Remarks..">#[remarks]</textarea>
	                     </div>
	                     <div class="form-group">
	                     	<div class="row items-push">
	                     		<div class="col-lg-6 col-sm-6 col-xs-12 text-left">
	                     			<input id="val-id" type="hidden" name="id" value="#[id]"/>
	                     			<button id="btnSubmit" type="submit" class="btn btn-primary">Save</button>
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
        </div>
        <div id="rowTemplete" style="display: none;">
        	<table>
        		<thead>
                    <tr>
                        <th style="width: 50%;">Name</th>
                        <th style="width: 50%;">Remarks</th>
                        <th class="text-center" style="width: 100px;">Actions</th>
                    </tr>
        		</thead>
        		<tbody>
		        	<tr>
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
				     </tr>
			     <tbody>
		     </table>
        </div>

		<%@include file="../includes/footer.jsp" %>
        <%@include file="../includes/scripts.jsp" %>  
        
         <!-- Page JS Plugins -->
        <script src="resources/js/plugins/jquery-validation/jquery.validate.min.js"></script> 
        <!-- Page JS Code -->
        <script src="resources/js/pages/be_forms_validation.min.js"></script>    
        <script type="text/javascript">
        var dataBeforeEdit = {
       		id : 0,
       		name : "",
       		remarks : ""
        };

       	$(document).on("click", "#btnAddNew", function(e) {
       		var formTemplete = $("#formTemplete").clone()
			var formHtml = formTemplete.html()
			.replace("#[id]", "0")
			.replace("#[name]", "")
			.replace("#[remarks]", "");
       		$("#dataTable > tbody").prepend("<tr><td colspan='3'>" + formHtml + "</td></tr>");
       	});
       	
       	$(document).on("click", "#btnCancel", function(e) {
       		if(dataBeforeEdit.id > 0) {
       			var rowTemplete = $("#rowTemplete > table > tbody > tr").clone();
       			var rowHtml = rowTemplete.html()
   				.replace("#[id]", dataBeforeEdit.id)
   				.replace("#[name]", dataBeforeEdit.name)
   				.replace("#[name]", dataBeforeEdit.name)
   				.replace("#[remarks]", dataBeforeEdit.remarks)
   				.replace("#[remarks]", dataBeforeEdit.remarks);
               	$(this).closest("tr").html(rowHtml);
               	
				dataBeforeEdit.id = 0;
   				dataBeforeEdit.name = "";
   				dataBeforeEdit.remarks = "";
       		} else {
       			$('#dataTable > tbody tr').first().remove();
       		}
       	});
       	
       	$(document).on("click", "#btnSave", function(e) {
       		
       	});
       	
		$(document).on("click", ".edit-button", function(e) {
			$(this).tooltip('hide');
			var tr = $(this).closest("tr");
			dataBeforeEdit.id = $(tr).find(".col-id")[0].value;
			dataBeforeEdit.name = $(tr).find(".col-name")[0].value;
			dataBeforeEdit.remarks = $(tr).find(".col-remarks")[0].value;
			
			var formTemplete = $("#formTemplete").clone();
			var formHtml = formTemplete.html()
			.replace("#[id]", dataBeforeEdit.id)
			.replace("#[name]", dataBeforeEdit.name)
			.replace("#[remarks]", dataBeforeEdit.remarks);
       		$(tr).html("<td colspan='3'>" + formHtml + "</td>");
       	});
		
		$(document).on("click", ".delete-button", function(e) {
			$(this).tooltip('hide');
			$(this).closest("tr").remove();
       	});
		
       	</script>
       	
        	<!--  var page = 1;
        	var current_page = 1;
        	var total_page = 0;
        	var is_ajax_fire = 0;


        	manageData();


     
        	function manageData() {
        	    $.ajax({
        	        dataType: 'json',
        	        url: url+'api/getData.php',
        	        data: {page:page}
        	    }).done(function(data){
        	    	total_page = Math.ceil(data.total/10);
        	    	current_page = page;


        	    	$('#pagination').twbsPagination({
        		        totalPages: total_page,
        		        visiblePages: current_page,
        		        onPageClick: function (event, pageL) {
        		        	page = pageL;
        	                if(is_ajax_fire != 0){
        		        	  getPageData();
        	                }
        		        }
        		    });


        	    	manageRow(data.data);
        	        is_ajax_fire = 1;


        	    });


        	}


    
        	function getPageData() {
        		$.ajax({
        	    	dataType: 'json',
        	    	url: url+'api/getData.php',
        	    	data: {page:page}
        		}).done(function(data){
        			manageRow(data.data);
        		});
        	}


        
        	function manageRow(data) {
        		var	rows = '';
        		$.each( data, function( key, value ) {
        		  	rows = rows + '<tr>';
        		  	rows = rows + '<td>'+value.title+'</td>';
        		  	rows = rows + '<td>'+value.description+'</td>';
        		  	rows = rows + '<td data-id="'+value.id+'">';
        	        rows = rows + '<button data-toggle="modal" data-target="#edit-item" class="btn btn-primary edit-item">Edit</button> ';
        	        rows = rows + '<button class="btn btn-danger remove-item">Delete</button>';
        	        rows = rows + '</td>';
        		  	rows = rows + '</tr>';
        		});


        		$("tbody").html(rows);
        	}



        	$(".crud-submit").click(function(e){
        	    e.preventDefault();
        	    var form_action = $("#create-item").find("form").attr("action");
        	    var title = $("#create-item").find("input[name='title']").val();

        	 
        	    var description = $("#create-item").find("textarea[name='description']").val();


        	    if(title != '' && description != ''){
        	        $.ajax({
        	            dataType: 'json',
        	            type:'POST',
        	            url: url + form_action,
        	            data:{title:title, description:description}
        	        }).done(function(data){
        	            $("#create-item").find("input[name='title']").val('');
        	            $("#create-item").find("textarea[name='description']").val('');
        	            getPageData();
        	            $(".modal").modal('hide');
        	            toastr.success('Item Created Successfully.', 'Success Alert', {timeOut: 5000});
        	        });
        	    }else{
        	        alert('You are missing title or description.')
        	    }


        	});


        	$("body").on("click",".remove-item",function(){
        	    var id = $(this).parent("td").data('id');
        	    var c_obj = $(this).parents("tr");


        	    $.ajax({
        	        dataType: 'json',
        	        type:'POST',
        	        url: url + 'api/delete.php',
        	        data:{id:id}
        	    }).done(function(data){
        	        c_obj.remove();
        	        toastr.success('Item Deleted Successfully.', 'Success Alert', {timeOut: 5000});
        	        getPageData();
        	    });


        	});


        	$("body").on("click",".edit-item",function(){


        	    var id = $(this).parent("td").data('id');
        	    var title = $(this).parent("td").prev("td").prev("td").text();
        	    var description = $(this).parent("td").prev("td").text();


        	    $("#edit-item").find("input[name='title']").val(title);
        	    $("#edit-item").find("textarea[name='description']").val(description);
        	    $("#edit-item").find(".edit-id").val(id);


        	});

        	$(".crud-submit-edit").click(function(e){


        	    e.preventDefault();
        	    var form_action = $("#edit-item").find("form").attr("action");
        	    var title = $("#edit-item").find("input[name='title']").val();

        	 


        	    var description = $("#edit-item").find("textarea[name='description']").val();
        	    var id = $("#edit-item").find(".edit-id").val();


        	    if(title != '' && description != ''){
        	        $.ajax({
        	            dataType: 'json',
        	            type:'POST',
        	            url: url + form_action,
        	            data:{title:title, description:description,id:id}
        	        }).done(function(data){
        	            getPageData();
        	            $(".modal").modal('hide');
        	            toastr.success('Item Updated Successfully.', 'Success Alert', {timeOut: 5000});
        	        });
        	    }else{
        	        alert('You are missing title or description.')
        	    }


        	});-->

        
        
        
    </body>
</html>