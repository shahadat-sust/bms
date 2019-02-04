<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<div class="modal fade" id="hotel-search-modal" tabindex="-1" role="dialog" aria-labelledby="hotel-search-modal" aria-hidden="true">
    <div class="modal-dialog modal-dialog-popout modal-xl" role="document" >
        <div class="modal-content">
            <div class="block block-themed block-transparent mb-0">
                <div class="block-header bg-primary-dark">
                    <h3 class="block-title">Search Hotel Wizard</h3>
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
	                                 <input type="hidden" class="form-control" id="var-hotel-search-starRating" value="0"/>
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
        				<div class="col-lg-9" style="max-height: 400px; overflow-y: auto;">
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
                                </tbody>
                            </table>
        				</div>
        			</div>
                </div>
                <div class="block-content block-content-full text-right bg-light">
                    <button type="button" id="btn-hotel-search-done" class="btn btn-primary" disabled="disabled" data-dismiss="modal">Done</button>
                </div>
            </div>
        </div>
    </div>
    <script type="text/javascript">
		var hotelsearchmodal = {
			callback: null,
			fetchCountryListUrl : '<c:url value="/country/fetch/0/1" />',
			fetchCityListUrl : '<c:url value="/city/fetch/0/0/{#countryId}/1" />',
			searchHotelUrl : '<c:url value="/searchhotel" />',
			fetchCountryListAjax : undefined,
			fetchCityListAjax: undefined,
			searchHotelAjax: undefined,
			hotelList: {},

			init : function(e) {
				hotelsearchmodal.callback = e;
				
				$("#hotel-search-modal").on('show.bs.modal', function(e) {
			    	hotelsearchmodal.beforeOpen(e);
			    });
		    	
		    	$("#hotel-search-modal").on('shown.bs.modal', function(e) {
			    	hotelsearchmodal.open(e);
			    });
		    	
		    	$("#hotel-search-modal").on('hide.bs.modal', function(e) {
			    	hotelsearchmodal.beforeClose(e);
			    });
		    	
		    	$("#hotel-search-modal").on('hidden.bs.modal', function(e) {
			    	hotelsearchmodal.close(e);
			    });
				
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
						$("#val-hotel-search-cityId").html('<option value="0">Please select</option>');
					}
					$("#val-hotel-search-cityId").html('<option value="0">Please select</option>');
		       	});
				
				$("#btn-hotel-search-reset").on('click', function() {
					hotelsearchmodal.close();
				});

				$(document).on('change', 'input[type=radio][name=search-hotel-id-group]', function() {
					$("#btn-hotel-search-done").removeAttr("disabled");
				});
				
				$("#btn-hotel-search-search").on("click", function() {
					var title = $("#val-hotel-search-title").val();
					var starRating = $("#var-hotel-search-starRating").val();
					var countryId = $("#val-hotel-search-countryId").val();
					var cityId = $("#val-hotel-search-cityId").val();
					hotelsearchmodal.searchHotel(title, starRating, countryId, cityId);
				});

				hotelsearchmodal.getCountryList();
			},
			beforeOpen : function(e) {
				
			},
			open : function(e) {
				
	   		},
	   		beforeClose : function(e) {
				var oWhich = $(document.activeElement);
			  	if (oWhich[0].tagName == 'BUTTON') {
			    	if (oWhich.text() == "Done") {
			    		var providerId = $("input[name='search-hotel-id-group']:checked"). val()
						hotelsearchmodal.callback(hotelsearchmodal.hotelList[providerId]);
			    	}
		  		}
			},
			close : function(e) {
				hotelsearchmodal.hotelList = [];
				$("#frm-hotel-search")[0].reset();
				$(".hotel-search-starRating").raty('set', { score: 0 });
				$("#var-hotel-search-starRating").val("0");
				$("#val-hotel-search-cityId").html('<option value="0">Please select</option>');
				$("#btn-hotel-search-done").attr("disabled", "disabled");
				$('#hotel-search-table').find('tbody').html('');
			},
			searchHotel : function(title, starRating, countryId, cityId) {
				var url = hotelsearchmodal.searchHotelUrl + "?title=" + encodeURIComponent(title) 
						+ "&starRating=" + starRating + "&countryId=" + countryId + "&cityId=" + cityId;
				hotelsearchmodal.searchHotelAjax = $.ajax({
					type: "GET",
		            contentType: "application/json",
		            url: url,
		            dataType: 'json',
		            timeout: 600000,
		            success: function (r) {
		            	if(r.status) {
		            		hotelsearchmodal.searchHotelAjax = undefined;
		            		hotelsearchmodal.hotelList = {};
		            		$("#btn-hotel-search-done").attr("disabled", "disabled");
		            		
		    				if (r.datas.length > 0) {
		            			var html = "";

		            			$.each(r.datas, function (index, data) {
			            			html += '<tr>';
			            			html += '	<td class="text-center">'; 
		            				html += '		<div class="custom-control custom-checkbox custom-checkbox-square custom-control-lg custom-control-success mb-1">';
		            				html += '            <input type="radio" class="custom-control-input" name="search-hotel-id-group" value="' + data.providerId + '" id="var-search-hotel-id-' + data.providerId + '">';
		            				html += '            <label class="custom-control-label" for="var-search-hotel-id-' + data.providerId + '"></label>';
		            				html += '        </div>';
		            				html += '    </td>';
		            				html += '    <td class="font-w600">';
		            				html += '        <a href="#">' + data.title + '</a>';
	            					html += '    </td>';
	           						html += '    <td class="d-none d-lg-table-cell">';
	        						html += '        ' + data.countryName;
		            				html += '    </td>';
		            				html += '    <td class="d-none d-md-table-cell">';
		            				html += '        ' + data.cityName;
		            				html += '    </td>';
		            				html += '    <td class="d-none d-xl-table-cell">';
		            				html += '        <div class="hotel-search-list-starRating" data-score="'  + data.starRating + '"></div>';
		            				html += '    </td>';
		            				html += '</tr>';
		            				
		            				hotelsearchmodal.hotelList[data.providerId] = data;
		            			});
		            			
		            			$('#hotel-search-table').find('tbody').html(html);
		            			
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
		    				} else {
		    					$('#hotel-search-table').find('tbody').html('');
		    				}
		            	} else {
		            		console.log(r.errors);
		            		hotelsearchmodal.searchHotelAjax = undefined;
		            	}
		            },
		            error: function (e) {
		            	hotelsearchmodal.searchHotelAjax = undefined;
		            }
				});
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
		            		var html = '<option value="0">Please select</option>';
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
		            		var html = '<option value="0">Please select</option>';
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
	</script>
</div>

