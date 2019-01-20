<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<div class="modal fade" id="hotel-search-modal" tabindex="-1" role="dialog" aria-labelledby="hotel-search-modal" aria-hidden="true">
    <div class="modal-dialog modal-dialog-popout modal-xl" role="document" >
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
        				<div class="col-lg-3">
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
			                     			<button id="btn-hotel-search-reset" type="reset" class="btn btn-sm btn-success">
			                     				<i class="fa fa-fw fa-undo mr-1"></i> Reset
		                     				</button>
			                     		</div>
			                     		<div class="col-sm-6 text-right">
			                     			<button id="btn-hotel-search-search" type="button" class="btn btn-sm btn-success">
			                     				<i class="fa fa-fw fa-search mr-1"></i> Search
		                     				</button>
			                     		</div>
			                     	</div>
			                     </div>
                             </form>
        				</div>
        				<div class="col-lg-9" >
    						<table id="hotel-search-table" class="table table-bordered table-striped table-vcenter">
                                <thead>
                                    <tr>
                                    	<th class="text-center" style="width: 45px;"></th>
                                        <th>Title</th>
                                        <th class="d-none d-lg-table-cell" style="width: 20%;">Country</th>
                                        <th class="d-none d-md-table-cell" style="width: 20%;">City</th>
                                        <th class="d-none d-xl-table-cell" style="width: 100px;">Star</th>
                                    </tr>
                                </thead>
                                <tbody>
                                     <tr>
                                    	<td class="text-center"> 
                                    		<div class="custom-control custom-checkbox custom-checkbox-square custom-control-lg custom-control-success mb-1">
                                                <input type="radio" class="custom-control-input" name="search-hotel-id-group" id="var-search-hotel-id-1">
                                                <label class="custom-control-label" for="var-search-hotel-id-1"></label>
                                            </div>
                                        </td>
                                        <td class="font-w600">
                                            <a href="be_pages_generic_blank.html">The Westin</a>
                                        </td>
                                        <td class="d-none d-lg-table-cell">
                                            Bangladesh
                                        </td>
                                        <td class="d-none d-md-table-cell">
                                            Dhaka
                                        </td>
                                        <td class="d-none d-xl-table-cell">
                                            <div class="hotel-search-list-starRating" data-score="3"></div>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
        				</div>
        			</div>
                </div>
                <div class="block-content block-content-full text-right bg-light">
                    <button type="button" class="btn btn-primary" disabled="disabled" data-dismiss="modal">Done</button>
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
				
				$(".hotel-search-list-starRating").raty({
					starType: "i",
	        		hints: ["One Star", "Two Stars", "Three Stars", "Four Stars", "Five Stars"],
	                starOff: $(this).data("star-off-small") || "fa fa-fw fa-star text-muted",
	                starOn: $(this).data("star-on-small") || "fa fa-fw fa-star text-warning",
	                starHalf: 'star-half-small.png',
	                starOff: 'star-off-small.png',
	                starOn: 'star-on-small.png',
	                cancelOff: 'cancel-off-small.png',
	                cancelOn:  'cancel-on-small.png',
                    readOnly: true
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
					var datatable = $("#hotel-search-table").DataTable();
					if (datatable) {
						datatable.clear();
						datatable.draw();
					}
					$(".hotel-search-starRating").raty('set', { score: 0 });
					$("#var-hotel-search-starRating").val("");
					$("#val-hotel-search-cityId").html('<option value="">Please select</option>');
				});

				hotelsearchmodal.getCountryList();
			},
			beforeOpen : function name() {
				
			},
			open : function() {
				$("#hotel-search-table").dataTable({
					order: [[ 1, "asc" ]],
					paging: false,
			        info:  false,
			        scrollY: '50vh',
			        scrollCollapse: true
                });
				$(window).resize(hotelsearchmodal.onWindowResize);
	   		},
	   		beforeClose : function name() {
	   			
			},
			close : function() {
				$(window).off("resize", hotelsearchmodal.onWindowResize);
				var datatable = $("#hotel-search-table").DataTable();
				if (datatable) {
					datatable.clear();
					datatable.draw();
					datatable.destroy();
				}
				$("#frm-hotel-search")[0].reset();
				$(".hotel-search-starRating").raty('set', { score: 0 });
				$("#var-hotel-search-starRating").val("");
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
			},
			onWindowResize : function () {
				var datatable = $("#hotel-search-table").DataTable();
				if (datatable) {
					datatable.columns.adjust().draw();
				}
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

