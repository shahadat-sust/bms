var hotelpointofinterest = {
	hotelInfo: undefined,
	hotelPointOfInterestData : {
		id : "0",
		providerId : "0",
		pointOfInterestId : "0"
	},
	isAvailableUrl: undefined,
	getPointOfInterestListUrl: undefined,
	getPointOfInterestListAjax: undefined,
	createHotelPointOfInterestUrl: undefined,
	updateHotelPointOfInterestUrl: undefined,
	deleteHotelPointOfInterestUrl: undefined,
		
	init : function() {
		 $(".rating").raty({
    		starType: "i",
    		hints: ["One Star", "Two Stars", "Three Stars", "Four Stars", "Five Stars"],
            starOff: $(this).data("star-off") || "fa fa-fw fa-star text-muted",
            starOn: $(this).data("star-on") || "fa fa-fw fa-star text-warning",
            readOnly: true
        });
		
		 hotelsearchmodal.init(hotelpointofinterest.onHotelSelect);
		 
		 $(document).on("click", "#btnCreateNew", function(e) {
			var _btn = this;
       		$("#btnCreateNew").attr('disabled', true);
       		var selectedProviderId = $("#var-selected-providerId").val();

       		var formTemplete = $("#formTemplete").clone();
    		var formHtml = formTemplete.html()
	    		.replace("#[id]", "0")
	    		.replace("#[providerId]", selectedProviderId);
       		$("#dataTable > tbody").prepend("<tr><td colspan='2'>" + formHtml + "</td></tr>");
       		$("#btnSubmit").html('Save');
       		$('#val-pointOfInterestId').html('<option value="0">Please select</option>');
       		
       		var providerTypeId = $("#var-selected-providerTypeId").val();
       		hotelpointofinterest.getPointOfInterestList(providerTypeId);
       		hotelpointofinterest.initValidation();
       	});
       	
       	$(document).on("click", "#btnCancel", function(e) {
       		var _btn = this;

       		$('#dataTable > tbody tr').first().remove();
       		$("#btnCreateNew").removeAttr('disabled');
       	});
       	
       	$(document).on("click", "#btnSubmit", function(e) {
       		var _btn = this;
       		if($("#formComponent").valid()) {
       			swal({
                    text: "Do you want to add this point of interest",
                    type: "question",
                    showCancelButton: true,
                    confirmButtonClass: "btn btn-danger m-1",
                    cancelButtonClass: "btn btn-secondary m-1",
                    html: false,
                    preConfirm: function(e) {
                        return new Promise(function(e) {
                            setTimeout(function() {
                                e()
                            }, 50)
                        })
                    }
           		}).then(function(e) {
                	if(e.value) {
                		if($("#formComponent").valid()) {
                			hotelpointofinterest.doCreate(_btn);
                		}
                	}
                });
            }
       	});

		$(document).on("click", ".delete-button", function(e) {
			var _btn = this;
			$(_btn).tooltip('hide');
			var tr = $(_btn).closest("tr");
			var id = $.trim($(tr).find(".col-id")[0].value);
			if(id > 0) {
				swal({
	                text: "Do you want to remove this point of interest",
	                type: "warning",
	                showCancelButton: true,
	                confirmButtonClass: "btn btn-danger m-1",
	                cancelButtonClass: "btn btn-secondary m-1",
	                html: false,
	                preConfirm: function(e) {
	                    return new Promise(function(e) {
	                        setTimeout(function() {
	                            e()
	                        }, 50)
	                    })
	                }
	            }).then(function(e) {
	            	if(e.value) {
	            		hotelpointofinterest.doDelete(_btn, id);
	            	}
	            });
			} else {
				$(tr).remove();
			}
       	});
	},
	
	onHotelSelect : function(o) {
		hotelpointofinterest.hotelInfo = o;
		$('#var-selected-providerId').val(o.providerId);
		$('#var-selected-providerTypeId').val(o.providerTypeId);
		$('#var-title').val(o.title);
		$('#var-cityName').val(o.cityName);
		$('#var-countryName').val(o.countryName);
		$('#var-address').val(o.address);
		$('#var-starRating').data("score", o.starRating);
		
		$(".rating").raty({
    		starType: "i",
    		hints: ["One Star", "Two Stars", "Three Stars", "Four Stars", "Five Stars"],
            starOff: $(this).data("star-off") || "fa fa-fw fa-star text-muted",
            starOn: $(this).data("star-on") || "fa fa-fw fa-star text-warning",
            readOnly: true
        });

		$("#btnCreateNew").removeAttr('disabled');
		hotelpointofinterest.getHotelPointOfInterestList(o.providerId);
	},
	
	getPointOfInterestList : function(providerTypeId) {
		var url =  hotelpointofinterest.getPointOfInterestListUrl.replace("{#providerTypeId}", providerTypeId);
		hotelpointofinterest.getPointOfInterestListAjax = $.ajax({
			type: "GET",
            contentType: "application/json",
            url: url,
            dataType: 'json',
            timeout: 600000,
            success: function (data) {
            	if(data.status) {
            		hotelpointofinterest.getPointOfInterestListAjax = undefined;
            		var html = '<option value="0">Please select</option>';
            		$.each(data.datas, function(index, data) {
            			html += '<option value="' + data.id + '">' + data.name + '</option>';
            		});
            		$('#val-pointOfInterestId').html(html);
            	} else {
            		console.log(data.errors);
            		hotelpointofinterest.getPointOfInterestListAjax = undefined;
            		Dashmix.helpers('notify', {
                		align: 'center',
                		type: 'danger', 
                		icon: 'fa fa-times mr-1', 
                		message: 'Failed to get states!',
                		delay: 1e3
        			});
            	}
            },
            error: function (e) {
            	hotelpointofinterest.getPointOfInterestListAjax = undefined;
            	Dashmix.helpers('notify', {
            		align: 'center',
            		type: 'danger', 
            		icon: 'fa fa-times mr-1', 
            		message: 'Failed to process request!',
            		delay: 1e3
    			});
            }
		});
	},
	
	getHotelPointOfInterestList : function(providerId) {
		var url =  hotelpointofinterest.getHotelPointOfInterestListUrl.replace("{#providerId}", providerId);
		hotelpointofinterest.getHotelPointOfInterestListAjax = $.ajax({
			type: "GET",
            contentType: "application/json",
            url: url,
            dataType: 'json',
            timeout: 600000,
            success: function (r) {
            	if(r.status) {
            		hotelpointofinterest.getHotelPointOfInterestListAjax = undefined;
    				if (r.datas.length > 0) {
    					html = '';
            			$.each(r.datas, function (index, data) {
            				var rowTemplete = $("#rowTemplete").clone();
                   			var rowHtml = rowTemplete.html()
	                   			.replace("#[id]", data.id)
	                   			.replace("#[pointOfInterestId]", data.pointOfInterestId)
			                	.replace("#[pointOfInterestName]", data.pointOfInterestName)
			                	.replace("#[providerId]", data.providerId);
                   			html += "<tr>" + rowHtml + "</tr>";
            			});
            			$('#dataTable').find('tbody').html(html);
    				} else {
    					$('#dataTable').find('tbody').html('');
    				}
            	} else {
            		console.log(r.errors);
            		hotelpointofinterest.getHotelPointOfInterestListAjax = undefined;
            		$('#dataTable').find('tbody').html('');
            	}
            },
            error: function (e) {
            	hotelpointofinterest.getHotelPointOfInterestListAjax = undefined;
            	$('#dataTable').find('tbody').html('');
            }
		});
	}, 
	
	doCreate : function(_btn) {
		$(_btn).attr("disabled", true);
		var form = $("#formComponent");
		var serializeForm = form.serializeObject();
		
		$.ajax({
			type: "POST",
            contentType: "application/json",
            url: hotelpointofinterest.createHotelPointOfInterestUrl,
            data: JSON.stringify(serializeForm),
            dataType: 'json',
            timeout: 600000,
            success: function (data) {
            	if(data.status) {
            		$("#btnCreateNew").removeAttr('disabled');
            		var rowTemplete = $("#rowTemplete").clone();
           			var rowHtml = rowTemplete.html()
           				.replace("#[id]", data.datas[0].id)
               			.replace("#[pointOfInterestId]", data.datas[0].pointOfInterestId)
	                	.replace("#[pointOfInterestName]", data.datas[0].pointOfInterestName)
	                	.replace("#[providerId]", data.datas[0].providerId);
           			$('#dataTable > tbody tr').first().html(rowHtml);
            		
            		Dashmix.helpers('notify', {
                		align: 'center', 
                		type: 'success', 
                		icon: 'fa fa-check mr-1', 
                		message: 'Point of interest added successfully!',
                		delay: 1e3
        			});
            	} else {
            		$("#btnCreateNew").removeAttr('disabled');
            		$(_btn).removeAttr("disabled");
            		Dashmix.helpers('notify', {
                		align: 'center',
                		type: 'danger', 
                		icon: 'fa fa-times mr-1', 
                		message: data.errors && data.errors.length > 0 ? data.errors[0] : 'Failed to add point of interest, please try again!',
                		delay: 1e3
        			});
            	}
            },
            error: function (e) {
            	$("#btnCreateNew").removeAttr('disabled');
            	$(_btn).removeAttr("disabled");
            	Dashmix.helpers('notify', {
            		align: 'center',
            		type: 'danger', 
            		icon: 'fa fa-times mr-1', 
            		message: 'Failed to process request!',
            		delay: 1e3
    			});
            }
		});
	},
	
	doDelete : function(_btn, id) {
		$(_btn).attr("disabled", true);

		$.ajax({
			type: "DELETE",
            contentType: "application/json",
            url: hotelpointofinterest.deleteHotelPointOfInterestUrl + id,
            dataType: 'json',
            timeout: 600000,
            success: function (data) {
            	if(data.status) {
            		$(_btn).closest("tr").remove();
            		Dashmix.helpers('notify', {
                		align: 'center', 
                		type: 'success', 
                		icon: 'fa fa-check mr-1', 
                		message: 'Point of interest removed successfully!',
                		delay: 1e3
        			});
            	} else {
            		console.log(data.errors);
            		$(_btn).removeAttr("disabled");
            		Dashmix.helpers('notify', {
                		align: 'center',
                		type: 'danger', 
                		icon: 'fa fa-times mr-1', 
                		message: data.errors && data.errors.length > 0 ? data.errors[0] : 'Failed to remove point of interest, please try again!',
                		delay: 1e3
        			});
            	}
            },
            error: function (e) {
            	$(_btn).removeAttr("disabled");
            	Dashmix.helpers('notify', {
            		align: 'center',
            		type: 'danger', 
            		icon: 'fa fa-times mr-1', 
            		message: 'Failed to process request!',
            		delay: 1e3
    			});
            }
		});
	},
	
	initValidation : function() {
		$('#formComponent').validate({
            ignore : [], 
            errorClass : "invalid-feedback animated fadeIn", 
        	errorElement : "div", 
            errorPlacement : function(e, r) {
                $(r).addClass("is-invalid"), $(r).parents(".form-group").append(e)
            }, 
            highlight : function(e) {
                $(e).parents(".form-group").find(".is-invalid").removeClass("is-invalid").addClass("is-invalid")
            }, 
            success : function(e) {
                $(e).parents(".form-group").find(".is-invalid").removeClass("is-invalid"), $(e).remove()
            }, 
            rules : {
            	"pointOfInterestId": {
                    min: 1,
                    remote : {
                    	url : hotelpointofinterest.isAvailableUrl,
                    	type : "GET",
                    	data : {
                    		providerId : function() {
                    			return $("#val-providerId").val();
                    		}
                    	}
                    }
                }
            }, 
            messages : {
            	"pointOfInterestId": {
                	min: "Please select POI",
                    remote: "This point of interest is already assigned for this hotel"
                }
            }
        });
	}
};

$(document).ready(function() {
	hotelpointofinterest.init();
});