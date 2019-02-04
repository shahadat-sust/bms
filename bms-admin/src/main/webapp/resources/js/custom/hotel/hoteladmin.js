var hoteladmin = {
	userInfo: undefined,
	getAssignableHotelsUrl: undefined,
	getAssignableHotelAjax: undefined,
	getAssignHotelsUrl: undefined,
	getAssignHotelAjax: undefined,
		
	init : function() {
		$(document).on("change", "input[type=checkbox][name=assign-providerId-group]", function() {
			var isAssign = $(this).is(':checked');
			var providerId = $(this).closest("td").find("input").val();
			hoteladmin.doAssignHotel(this, hoteladmin.userInfo.userId, providerId, isAssign);
		});

		usersearchmodal.init(hoteladmin.onUserSelect);
	},
	onUserSelect : function(o) {
		hoteladmin.userInfo = o;
		$('#var-selected-userId').val(o.userId);
		$('#var-name').val(o.name);
		$('#var-username').val(o.username);
		$('#var-email').val(o.email);
		$('#var-phoneNumber').val(o.phoneNumber);
		hoteladmin.getAssignableHotels(o.userId);
	},
	doAssignHotel : function(_chk, userId, providerId, isAssign) {
		hoteladmin.getAssignHotelAjax = $.ajax({
			type: "POST",
            contentType: "application/json",
            url: hoteladmin.getAssignHotelsUrl,
            data: JSON.stringify({"userId": userId, "providerId": providerId, "isAssign": isAssign}),
            dataType: 'json',
            timeout: 600000,
            success: function (data) {
            	hoteladmin.getAssignHotelAjax = undefined;
            	if(!data.status) {
            		console.log(data.errors);
            		$(_chk).attr('checked', !isAssign);
            		Dashmix.helpers('notify', {
                		align: 'center',
                		type: 'danger', 
                		icon: 'fa fa-times mr-1', 
                		message: data.errors && data.errors.length > 0 ? data.errors[0] : 'Failed to process request, please try again!',
                		delay: 1e3
        			});
            	}
            },
            error: function (e) {
            	hoteladmin.getAssignHotelAjax = undefined;
            	$(_chk).attr('checked', !isAssign);
            	Dashmix.helpers('notify', {
            		align: 'center',
            		type: 'danger', 
            		icon: 'fa fa-times mr-1', 
            		message: 'Failed to process request, please try again!',
            		delay: 1e3
    			});
            }
		});
	},
	getAssignableHotels : function(userId) {
		var url = hoteladmin.getAssignableHotelsUrl + "?userId=" + userId;
		hoteladmin.getAssignableHotelAjax = $.ajax({
			type: "GET",
            contentType: "application/json",
            url: url,
            dataType: 'json',
            timeout: 600000,
            success: function (r) {
            	if(r.status) {
            		hoteladmin.getAssignableHotelAjax = undefined;
            		
    				if (r.datas.length > 0) {
            			var html = "";

            			$.each(r.datas, function (index, data) {
            				var checked = (data.id > 0 ? 'checked' : '');
	            			html += '<tr>';
	            			html += '	 <td class="font-w600">'; 
	            			html += '        <a href="#">' + data.title + '</a>';
            				html += '    </td>';
            				html += '    <td class="d-none d-lg-table-cell" class="font-w600">';
            				html += '        ' + data.cityName;
        					html += '    </td>';
       						html += '    <td class="d-none d-xl-table-cell" class="font-w600">';
    						html += '        ' + data.countryName;
            				html += '    </td>';
            				html += '    <td class="d-none d-xl-table-cell" class="font-w600">';
            				html += '        <div class="rating" data-score="'  + data.starRating + '"></div>'
            				html += '    </td>';
            				html += '    <td>';
            				html += '        <input type="hidden" value="' + data.providerId + '"/>';
            				html += '        <div class="custom-control custom-switch custom-control-success custom-control-lg mb-2">';
            				html += '        	<input type="checkbox" class="custom-control-input" name="assign-providerId-group" id="var-assign-providerId-' + data.providerId + '" ' + checked + '/>';
            				html += '        	<label class="custom-control-label" for="var-assign-providerId-' + data.providerId + '"></label>';
            				html += '        </div>';
            				html += '    </td>';
            				html += '</tr>';
            			});
            			
            			$('#dataTable').find('tbody').html(html);
            			
            			$(".rating").raty({
        					starType: "i",
        	        		hints: ["One Star", "Two Stars", "Three Stars", "Four Stars", "Five Stars"],
        	                starOff: $(this).data("star-off") || "fa fa-fw fa-star text-muted",
        	                starOn: $(this).data("star-on") || "fa fa-fw fa-star text-warning",
                            readOnly: true
        	            });
    				} else {
    					$('#dataTable').find('tbody').html('');
    				}
            	} else {
            		console.log(r.errors);
            		hoteladmin.getAssignableHotelAjax = undefined;
            		$('#dataTable').find('tbody').html('');
            	}
            },
            error: function (e) {
            	hoteladmin.getAssignableHotelAjax = undefined;
            	$('#dataTable').find('tbody').html('');
            }
		});
	}
};

$(document).ready(function() {
	hoteladmin.init();
});