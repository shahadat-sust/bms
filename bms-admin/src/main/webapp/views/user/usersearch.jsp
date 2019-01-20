<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<div class="modal fade" id="user-search-modal" tabindex="-1" role="dialog" aria-labelledby="user-search-modal" aria-hidden="true">
    <div class="modal-dialog modal-dialog-popout modal-xl" role="document">
        <div class="modal-content">
            <div class="block block-themed block-transparent mb-0">
                <div class="block-header bg-primary-dark">
                    <h3 class="block-title">Search User</h3>
                    <div class="block-options">
                        <button type="button" class="btn-block-option" data-dismiss="modal" aria-label="Close">
                            <i class="fa fa-fw fa-times"></i>
                        </button>
                    </div>
                </div>
                <div class="block-content">
        			<div class="row items-push">
        				<div class="col-lg-3">
        					<form id="frm-user-search">
	       						<div class="form-group">
	                                <label for="val-user-search-name">Name </label>
	                               	<input id="val-user-search-name" placeholder="Enter name.." class="form-control"/>
	                            </div>
	                            <div class="form-group">
	                                <label for="val-user-search-username">Username </label>
	                               	<input id="val-user-search-username" placeholder="Enter username.." class="form-control"/>
	                            </div>
	                            <div class="form-group">
	                                <label for="val-user-search-email">Email </label>
	                               	<input id="val-user-search-email" placeholder="Enter email.." class="form-control"/>
	                            </div>
	                            <div class="form-group">
	                                <label for="val-user-search-phoneNumber">Phone Number </label>
	                               	<div class="input-group">
										<span class="input-group-btn">
											<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
												<span class="type-text hotel-search-type-text">Code</span> 
												<span class="caret"></span>
											</button>
											<div class="dropdown-menu" role="menu">
												<ul class="intl-tel-input country-list user-search-country-list" role="menu">
													<li class="country hotel-search-country" data-dial-code="880" data-country-code="bd">
														<div class="flag-box"><div class="iti-flag bd"></div></div>
														<span class="country-name">Bangladesh</span>
														<span class="dial-code">+880</span>
													</li>
												</ul>	
											</div>				
										</span>
										<input id="val-user-search-code" type="hidden" class="form-control"/>
										<input id="val-user-search-number" placeholder="Enter number.." maxlength="20" class="form-control"/>
									</div>
	                            </div>
	                           	<div class="form-group">
			                     	<div class="row items-push">
			                     		<div class="col-sm-6 text-left">
			                     			<button id="btn-user-search-reset" type="reset" class="btn btn-sm btn-success">
			                     				<i class="fa fa-fw fa-undo mr-1"></i> Reset
		                     				</button>
			                     		</div>
			                     		<div class="col-sm-6 text-right">
			                     			<button id="btn-user-search-search" type="button" class="btn btn-sm btn-success">
			                     				<i class="fa fa-fw fa-search mr-1"></i> Search
		                     				</button>
			                     		</div>
			                     	</div>
			                     </div>
                           	</form>
        				</div>
        				<div class="col-lg-9">
        					<div class="block-content block-content-full">
        					<table id="user-search-table" class="table table-bordered table-striped table-vcenter">
                                <thead>
                                    <tr>
                                    	<th class="text-center"></th>
                                        <th>Name</th>
                                        <th class="d-none d-md-table-cell" style="width: 15%;">Username</th>
                                        <th class="d-none d-lg-table-cell" style="width: 20%;">Email</th>
                                        <th class="d-none d-xl-table-cell" style="width: 25%;">Phone Number</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                    	<td class="text-center"> 
                                    		<div class="custom-control custom-checkbox custom-checkbox-square custom-control-lg custom-control-success mb-1">
                                                <input type="radio" class="custom-control-input" name="search-user-id-group" id="var-search-user-id-1">
                                                <label class="custom-control-label" for="var-search-user-id-1"></label>
                                            </div>
                                        </td>
                                        <td class="font-w600">
                                            Shahadat Hossain
                                        </td>
                                        <td class="d-none d-md-table-cell">
                                            shahadat.hossain
                                        </td>
                                        <td class="d-none d-lg-table-cell">
                                            shahadat.hossain@gmail.com
                                        </td>
                                        <td class="d-none d-xl-table-cell">
                                            +880-01834904918
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
        					</div>
        				</div>
        			</div>
                </div>
                <div class="block-content block-content-full text-right bg-light">
                    <button type="button" class="btn btn-primary btn-disabled" disabled="disabled" data-dismiss="modal">Done</button>
                </div>
            </div>
        </div>
    </div>
    <script type="text/javascript">
		var usersearchmodal = {
			fetchCountryCodeListUrl : '<c:url value="/common/countrycodes" />',
			fetchCountryCodeListAjax: undefined,

			init : function() {
				$("#user-search-modal").on('click', ".hotel-search-country", function(){
					var selectedCode = $(this).data('dial-code');
					$('.hotel-search-type-text').text("+" + selectedCode);
					$('#val-user-search-code').val(selectedCode).valid();
				});
				
				$("#btn-user-search-reset").on('click', function() {
					var datatable = $("#user-search-table").DataTable();
					if (datatable) {
						datatable.clear();
						datatable.draw();
					}
					$('.hotel-search-type-text').text("Code");
					$("#val-user-search-code").val("");
				});
				
				usersearchmodal.getCountryCodeList();
			},
			beforeOpen : function name() {

			},
			open : function() {
				$("#user-search-table").dataTable({
					order: [[ 1, "asc" ]],
					paging: false,
			        info:  false,
			        scrollY: '50vh',
			        scrollCollapse: true
                });
				$(window).resize(usersearchmodal.onWindowResize);
	   		},
			beforeClose : function name() {
				
			},
			close : function() {
				$(window).off("resize", usersearchmodal.onWindowResize);
				var datatable = $("#user-search-table").DataTable();
				if (datatable) {
					datatable.clear();
					datatable.draw();
					datatable.destroy();
				}
				$("#frm-user-search")[0].reset();
				$('.hotel-search-type-text').text("Code");
				$("#val-user-search-code").val("");
			},
			getCountryCodeList : function() {
				var url = usersearchmodal.fetchCountryCodeListUrl;
				usersearchmodal.fetchCountryCodeListUrl = $.ajax({
					type: "GET",
		            contentType: "application/json",
		            url: url,
		            dataType: 'json',
		            timeout: 600000,
		            success: function (data) {
		            	if(data.status) {
		            		usersearchmodal.fetchCountryCodeListUrl = undefined;
		            		var html = '';
		            		$.each(data.datas, function(index, data) {
		            			html += '<li class="country hotel-search-country" data-dial-code="' + data.code + '" data-country-code="' + data.shortCode + '">';
		            			html += '	<div class="flag-box"><div class="iti-flag ' + data.shortCode + '"></div></div>';
		            			html += '	<span class="country-name">' + data.name + '</span>';
		            			html += '</li>';
		            		});
		            		$('.user-search-country-list').html(html);
		            	} else {
		            		console.log(data.errors);
		            		usersearchmodal.fetchCountryCodeListUrl = undefined;
		            	}
		            },
		            error: function (e) {
		            	usersearchmodal.fetchCountryCodeListUrl = undefined;
		            }
				});
			},
			onWindowResize : function () {
				var datatable = $("#user-search-table").DataTable();
				if (datatable) {
					datatable.columns.adjust().draw();
				}
			}
	    };
	    
	    $("#user-search-modal").ready(function() {
	    	usersearchmodal.init();
	    	
	    	$("#user-search-modal").on('show.bs.modal', function(){
	    		usersearchmodal.beforeOpen();
		    });
	    	
	    	$("#user-search-modal").on('shown.bs.modal', function(){
	    		usersearchmodal.open();
		    });
	    	
	    	$("#user-search-modal").on('hide.bs.modal', function(){
	    		usersearchmodal.beforeClose();
		    });
	    	
	    	$("#user-search-modal").on('hidden.bs.modal', function(){
	    		usersearchmodal.close();
		    });
	    });
	</script>
</div>

