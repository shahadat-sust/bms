<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<div class="modal fade" id="user-search-modal" tabindex="-1" role="dialog" aria-labelledby="user-search-modal" aria-hidden="true">
    <div class="modal-dialog modal-dialog-popout modal-lg" role="document">
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
        				<div class="col-sm-4">
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
	                                <label for="val-user-search-countryId">Country </label>
	                                <select id="val-user-search-countryId" class="form-control">
	                                	 <option value="0" label="Please select"></option>
	                                </select>
	                           	</div>
	                           	<div class="form-group">
			                     	<div class="row items-push">
			                     		<div class="col-sm-6 text-left">
			                     			<button id="btn-user-search-reset" type="reset" class="btn btn-primary">Reset</button>
			                     		</div>
			                     		<div class="col-sm-6 text-right">
			                     			<button id="btn-user-search-search" type="button" class="btn btn-primary">Search</button>
			                     		</div>
			                     	</div>
			                     </div>
                           	</form>
        				</div>
        				<div class="col-sm-8">
        				</div>
        			</div>
                </div>
                <div class="block-content block-content-full text-right bg-light">
                    <button type="button" class="btn btn-sm btn-light" data-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-sm btn-primary" data-dismiss="modal">Done</button>
                </div>
            </div>
        </div>
    </div>
    <script type="text/javascript">
		var usersearchmodal = {
			fetchCountryCodeListUrl : '<c:url value="/common/countrycodes" />',
			fetchCountryListUrl : '<c:url value="/country/fetch/0/1" />',
			fetchCountryCodeListAjax: undefined,
			fetchCountryListAjax : undefined,

			init : function() {
				$("#user-search-modal").on('click', ".hotel-search-country", function(){
					var selectedCode = $(this).data('dial-code');
					$('.hotel-search-type-text').text("+" + selectedCode);
					$('#val-user-search-code').val(selectedCode).valid();
				});
				
				$("#btn-user-search-reset").on('click', function() {
					$('.hotel-search-type-text').text("Code");
					$("#val-user-search-code").val("");
				});
				
				usersearchmodal.getCountryCodeList();
			},
			beforeOpen : function name() {
				usersearchmodal.getCountryList();
			},
			open : function() {
				
	   		},
			beforeClose : function name() {
				
			},
			close : function() {
				$("#frm-user-search")[0].reset();
				$('.hotel-search-type-text').text("Code");
				$("#val-user-search-code").val("");
				$("#val-user-search-countryId").html('<option value="">Please select</option>');
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
			getCountryList : function() {
				var url = usersearchmodal.fetchCountryListUrl;
				usersearchmodal.fetchCountryListAjax = $.ajax({
					type: "GET",
		            contentType: "application/json",
		            url: url,
		            dataType: 'json',
		            timeout: 600000,
		            success: function (data) {
		            	if(data.status) {
		            		usersearchmodal.fetchCountryListAjax = undefined;
		            		var html = '<option value="">Please select</option>';
		            		$.each(data.datas, function(index, data) {
		            			html += '<option value="' + data.id + '">' + data.name + '</option>';
		            		});
		            		$('#val-user-search-countryId').html(html);
		            	} else {
		            		console.log(data.errors);
		            		usersearchmodal.fetchCountryListAjax = undefined;
		            	}
		            },
		            error: function (e) {
		            	usersearchmodal.fetchCountryListAjax = undefined;
		            }
				});
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

