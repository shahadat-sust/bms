<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<div class="modal fade" id="user-search-modal" tabindex="-1" role="dialog" aria-labelledby="user-search-modal" aria-hidden="true">
    <div class="modal-dialog modal-dialog-popout modal-xl" role="document">
        <div class="modal-content">
            <div class="block block-themed block-transparent mb-0">
                <div class="block-header bg-primary-dark">
                    <h3 class="block-title">Search User Wizard</h3>
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
        				<div class="col-lg-9" style="max-height: 400px; overflow-y: auto;">
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
                                <tbody style="overflow-y: auto;">
                                </tbody>
                            </table>
        					</div>
        				</div>
        			</div>
                </div>
                <div class="block-content block-content-full text-right bg-light">
                    <button type="button" id="btn-user-search-done" class="btn btn-primary btn-disabled" disabled="disabled" data-dismiss="modal">Done</button>
                </div>
            </div>
        </div>
    </div>
    <script type="text/javascript">
		var usersearchmodal = {
			callback: null,
			fetchCountryCodeListUrl : '<c:url value="/common/countrycodes" />',
			fetchCountryCodeListAjax: undefined,
			searchUserUrl : '<c:url value="/searchuser" />',
			searchUserAjax: undefined,
			userList: {},

			init : function(e) {
				usersearchmodal.callback = e;
				
				$("#user-search-modal").on('show.bs.modal', function(e){
		    		usersearchmodal.beforeOpen(e);
			    });
		    	
		    	$("#user-search-modal").on('shown.bs.modal', function(e){
		    		usersearchmodal.open(e);
			    });
		    	
		    	$("#user-search-modal").on('hide.bs.modal', function(e){
		    		usersearchmodal.beforeClose(e);
			    });
		    	
		    	$("#user-search-modal").on('hidden.bs.modal', function(e){
		    		usersearchmodal.close(e);
			    });
				
				$("#user-search-modal").on('click', ".hotel-search-country", function(){
					var selectedCode = $(this).data('dial-code');
					$('.hotel-search-type-text').text("+" + selectedCode);
					$('#val-user-search-code').val(selectedCode).valid();
				});
				
				$("#btn-user-search-reset").on('click', function(e) {
					usersearchmodal.close();
				});

				$(document).on('change', 'input[type=radio][name=search-user-id-group]', function() {
					$("#btn-user-search-done").removeAttr("disabled");
				});
				
				$("#btn-user-search-search").on("click", function() {
					var name = $("#val-user-search-name").val();
					var username = $("#val-user-search-username").val();
					var email = $("#val-user-search-email").val();
					var code = $("#val-user-search-code").val();
					var number = $("#val-user-search-number").val();
					usersearchmodal.searchUser(name, username, email, code, number);
				});

				usersearchmodal.getCountryCodeList();
			},
			beforeOpen : function(e) {

			},
			open : function(e) {
				
	   		},
			beforeClose : function(e) {
				var oWhich = $(document.activeElement);
			  	if (oWhich[0].tagName == 'BUTTON') {
			    	if (oWhich.text() == "Done") {
			    		var userId = $("input[name='search-user-id-group']:checked"). val()
						usersearchmodal.callback(usersearchmodal.userList[userId]);
			    	}
		  		}
			},
			close : function(e) {
				usersearchmodal.userList = [];
				$("#frm-user-search")[0].reset();
				$('.hotel-search-type-text').text("Code");
				$("#val-user-search-code").val("");
				$("#btn-user-search-done").attr("disabled", "disabled");
				$('#user-search-table').find('tbody').html('');
			},
			searchUser : function(name, username, email, code, number) {
				var url = usersearchmodal.searchUserUrl + "?name=" + encodeURIComponent(name) 
						+ "&username=" + encodeURIComponent(username) + "&email=" + encodeURIComponent(email) 
						+ "&code=" + encodeURIComponent(code) + "&number=" + encodeURIComponent(number);
				usersearchmodal.searchUserAjax = $.ajax({
					type: "GET",
		            contentType: "application/json",
		            url: url,
		            dataType: 'json',
		            timeout: 600000,
		            success: function (r) {
		            	if(r.status) {
		            		usersearchmodal.searchUserAjax = undefined;
		            		usersearchmodal.userList = {};
		            		$("#btn-user-search-done").attr("disabled", "disabled");

		            		if (r.datas.length > 0) {
		            			var html = "";
		            			
		            			$.each(r.datas, function (index, data) {
			            			html += '<tr>';
			            			html += '	<td class="text-center">'; 
		            				html += '		<div class="custom-control custom-checkbox custom-checkbox-square custom-control-lg custom-control-success mb-1">';
		            				html += '            <input type="radio" class="custom-control-input" name="search-user-id-group" value="' + data.userId + '" id="var-search-user-id-' + data.userId + '">';
		            				html += '            <label class="custom-control-label" for="var-search-user-id-' + data.userId + '"></label>';
		            				html += '        </div>';
		            				html += '    </td>';
		            				html += '    <td class="font-w600">';
		            				html += '        ' + data.name;
	            					html += '    </td>';
	           						html += '     <td class="d-none d-md-table-cell">';
	        						html += '        ' + data.username;
		            				html += '     </td>';
		            				html += '    <td class="d-none d-lg-table-cell">';
		            				html += '        ' + data.email;
		            				html += '    </td>';
		            				html += '    <td class="d-none d-xl-table-cell">';
		            				html += '        ' + data.phoneNumber;
		            				html += '    </td>';
		            				html += '</tr>';
		            				
		            				usersearchmodal.userList[data.userId] = data;
		            			});
		            			
	            				$('#user-search-table').find('tbody').html(html);
	            			} else {
	            				$('#user-search-table').find('tbody').html('');
	            			}
		            	} else {
		            		console.log(r.errors);
		            		usersearchmodal.searchUserAjax = undefined;
		            	}
		            },
		            error: function (e) {
		            	usersearchmodal.searchUserAjax = undefined;
		            }
				});
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
			}

	    };
	</script>
</div>

