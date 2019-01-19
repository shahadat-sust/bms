<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<div class="modal fade" id="hotel-search-modal" tabindex="-1" role="dialog" aria-labelledby="hotel-search-modal" aria-hidden="true">
    <div class="modal-dialog modal-dialog-popout modal-lg" role="document">
        <div class="modal-content">
            <div class="block block-themed block-transparent mb-0">
                <div class="block-header bg-primary-dark">
                    <h3 class="block-title">Search Hotel</h3>
                    <div class="block-options">
                        <button type="button" class="btn-block-option" data-dismiss="modal" aria-label="Close">
                            <i class="fa fa-fw fa-times"></i>
                        </button>
                    </div>
                </div>
                <div class="block-content">
        			<div class="row items-push">
        				<div class="col-sm-4">
        					<form id="frm-hotel-search">
	       						<div class="form-group">
	                                <label for="val-hotel-search-title">Title </label>
	                               	<input id="val-hotel-search-title" placeholder="Enter title.." class="form-control"/>
	                            </div>
	                            <div class="form-group">
	                                <label for="val-hotel-search-countryId">Country </label>
	                                <select id="val-hotel-search-countryId" class="form-control">
	                                	 <option value="0" label="Please select"></option>
	                                </select>
	                           	</div>
	                           	<div class="form-group">
	                                <label for="val-hotel-search-cityId">City </label>
	                                <select id="val-hotel-search-cityId" class="form-control">
	                                	 <option value="0" label="Please select"></option>
	                                </select>
	                           	</div>
	                           	<div class="form-group">
	                                 <label>Star Rating </label>
	                                 <input type="hidden" class="form-control" id="var-hotel-search-starRating"/>
	                                 <div class="hotel-search-starRating form-control" data-score="0"></div>
	                             </div>
	                             <div class="form-group">
			                     	<div class="row items-push">
			                     		<div class="col-sm-6 text-left">
			                     			<button id="btn-hotel-search-reset" type="reset" class="btn btn-primary">Reset</button>
			                     		</div>
			                     		<div class="col-sm-6 text-right">
			                     			<button id="btn-hotel-search-search" type="button" class="btn btn-primary">Search</button>
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
		var hotelsearchmodal = {
			fetchCountryListUrl : '<c:url value="/country/fetch/0/1" />',
			fetchCityListUrl : '<c:url value="/city/fetch/0/0/{#countryId}/1" />',
			fetchCountryListAjax : undefined,
			fetchCityListAjax: undefined,
			
			init : function() {
				$(".hotel-search-starRating").raty({
	        		starType: "i",
	        		hints: ["One Star", "Two Stars", "Three Stars", "Four Stars", "Five Stars"],
	                starOff: $(this).data("star-off") || "fa fa-fw fa-star text-muted",
	                starOn: $(this).data("star-on") || "fa fa-fw fa-star text-warning",
	                click: function(t, e) {
	                	$("#var-hotel-search-starRating").val(t);
	                }
	            });
				
				$("#val-hotel-search-countryId").on("change", function(e) {
					var countryId = $("#val-hotel-search-countryId").val();
					if(countryId != "") {
						hotelsearchmodal.getCityList(countryId);
					} else {
						$("#val-hotel-search-cityId").html('<option value="">Please select</option>');
					}
					$("#val-hotel-search-cityId").html('<option value="">Please select</option>');
		       	});
				
				$("#btn-hotel-search-reset").on('click', function() {
					$(".hotel-search-starRating").raty('set', { score: 0 });
					$("#var-hotel-search-starRating").val("");
					$("#val-hotel-search-cityId").html('<option value="">Please select</option>');
				});
			},
			beforeOpen : function name() {
				hotelsearchmodal.getCountryList();
			},
			open : function() {
				
	   		},
	   		beforeClose : function name() {
				
			},
			close : function() {
				$("#frm-hotel-search")[0].reset();
				$(".hotel-search-starRating").raty('set', { score: 0 });
				$("#var-hotel-search-starRating").val("");
				$("#val-hotel-search-countryId").html('<option value="">Please select</option>');
				$("#val-hotel-search-cityId").html('<option value="">Please select</option>');
			},
			getCountryList : function() {
				var url = hotelsearchmodal.fetchCountryListUrl;
				hotelsearchmodal.fetchCountryListAjax = $.ajax({
					type: "GET",
		            contentType: "application/json",
		            url: url,
		            dataType: 'json',
		            timeout: 600000,
		            success: function (data) {
		            	if(data.status) {
		            		hotelsearchmodal.fetchCountryListAjax = undefined;
		            		var html = '<option value="">Please select</option>';
		            		$.each(data.datas, function(index, data) {
		            			html += '<option value="' + data.id + '">' + data.name + '</option>';
		            		});
		            		$('#val-hotel-search-countryId').html(html);
		            	} else {
		            		console.log(data.errors);
		            		hotelsearchmodal.fetchCountryListAjax = undefined;
		            	}
		            },
		            error: function (e) {
		            	hotelsearchmodal.fetchCountryListAjax = undefined;
		            }
				});
			},
			getCityList : function(countryId) {
				var url = hotelsearchmodal.fetchCityListUrl.replace("{#countryId}", countryId);
				hotelsearchmodal.fetchCityListAjax = $.ajax({
					type: "GET",
		            contentType: "application/json",
		            url: url,
		            dataType: 'json',
		            timeout: 600000,
		            success: function (data) {
		            	if(data.status) {
		            		hotelsearchmodal.fetchCityListAjax = undefined;
		            		var html = '<option value="">Please select</option>';
		            		$.each(data.datas, function(index, data) {
		            			html += '<option value="' + data.id + '">' + data.name + '</option>';
		            		});
		            		$('#val-hotel-search-cityId').html(html);
		            	} else {
		            		console.log(data.errors);
		            		hotelsearchmodal.fetchCityListAjax = undefined;
		            	}
		            },
		            error: function (e) {
		            	hotelsearchmodal.fetchCityListAjax = undefined;
		            }
				});
			}
	    };
	    
	    $("#hotel-search-modal").ready(function() {
	    	hotelsearchmodal.init();
	    	
	    	$("#hotel-search-modal").on('show.bs.modal', function(){
		    	hotelsearchmodal.beforeOpen();
		    });
	    	
	    	$("#hotel-search-modal").on('shown.bs.modal', function(){
		    	hotelsearchmodal.open();
		    });
	    	
	    	$("#hotel-search-modal").on('hide.bs.modal', function(){
		    	hotelsearchmodal.beforeClose();
		    });
	    	
	    	$("#hotel-search-modal").on('hidden.bs.modal', function(){
		    	hotelsearchmodal.close();
		    });
	    });
	</script>
</div>

