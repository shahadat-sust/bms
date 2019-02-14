var amenitycharge = {
	hotelInfo: undefined,
	amenityChargeData : {
		id : "0",
		providerId : "0",
		amenityId : "0",
		amenityName : "",
		charge : "0.00"
	},
	isAvailableUrl: undefined,
	getAmenityListUrl: undefined,
	getAmenityListAjax: undefined,
	createAmenityChargeUrl: undefined,
	updateAmenityChargeUrl: undefined,
	deleteAmenityChargeUrl: undefined,
		
	init : function() {
		 $(".rating").raty({
    		starType: "i",
    		hints: ["One Star", "Two Stars", "Three Stars", "Four Stars", "Five Stars"],
            starOff: $(this).data("star-off") || "fa fa-fw fa-star text-muted",
            starOn: $(this).data("star-on") || "fa fa-fw fa-star text-warning",
            readOnly: true
        });
		
		 hotelsearchmodal.init(amenitycharge.onHotelSelect);
		 
		 $(document).on("click", "#btnCreateNew", function(e) {
			var _btn = this;
       		$("#btnCreateNew").attr('disabled', true);
       		var selectedProviderId = $("#var-selected-providerId").val();

       		var formTemplete = $("#formTemplete").clone();
    		var formHtml = formTemplete.html()
	    		.replace("#[id]", "0")
	    		.replace("#[providerId]", selectedProviderId)
	    		.replace("#[charge]", "0.00");
       		$("#dataTable > tbody").prepend("<tr><td colspan='3'>" + formHtml + "</td></tr>");
       		$("#btnSubmit").html('Save');
       		$('#val-amenityId').html('<option value="0">Please select</option>');
       		
       		var providerTypeId = $("#var-providerTypeId").val();
       		amenitycharge.getAmenityList(providerTypeId);
       		amenitycharge.initValidation();
       	});
       	
       	$(document).on("click", "#btnCancel", function(e) {
       		var _btn = this;
       		if(amenitycharge.amenityChargeData.id > 0) {
       			var rowTemplete = $("#rowTemplete").clone();
       			var rowHtml = rowTemplete.html()
    				.replace("#[id]", amenitycharge.amenityChargeData.id)
    				.replace("#[providerId]", amenitycharge.amenityChargeData.providerId)
					.replace("#[amenityId]", amenitycharge.amenityChargeData.amenityId)
					.replace("#[amenityName]", amenitycharge.amenityChargeData.amenityName)
					.replace("#[amenityName]", amenitycharge.amenityChargeData.amenityName)
					.replace("#[charge]", amenitycharge.amenityChargeData.charge)
					.replace("#[charge]", amenitycharge.amenityChargeData.charge);
               	$(_btn).closest("tr").html(rowHtml);
               	
               	amenitycharge.amenityChargeData.id = "0";
               	amenitycharge.amenityChargeData.providerId = "0";
               	amenitycharge.amenityChargeData.amenityId = "0";
               	amenitycharge.amenityChargeData.amenityName = "";
               	amenitycharge.amenityChargeData.charge = "0.00";
       		} else {
       			$('#dataTable > tbody tr').first().remove();
       		}
       		$("#btnCreateNew").removeAttr('disabled');
       	});
       	
       	$(document).on("click", "#btnSubmit", function(e) {
       		var _btn = this;
       		if($("#formComponent").valid()) {
       			swal({
       				text: "Do you want to " + (amenitycharge.amenityChargeData.id > 0 ? "update" : "add ") + " this amenity",
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
                			if(amenitycharge.amenityChargeData.id > 0) {
                				amenitycharge.doUpdate(_btn);
                    		} else {
                    			amenitycharge.doCreate(_btn);
                    		}
                		}
                	}
                });
            }
       	});
       	
       	$(document).on("click", ".edit-button", function(e) {
			var _btn = this;
			$(_btn).tooltip('hide');
			var tr = $(_btn).closest("tr");
			amenitycharge.amenityChargeData.id = $.trim($(tr).find(".col-id")[0].value);
			amenitycharge.amenityChargeData.providerId = $.trim($(tr).find(".col-providerId")[0].value);
			amenitycharge.amenityChargeData.amenityId = $.trim($(tr).find(".col-amenityId")[0].value);
			amenitycharge.amenityChargeData.amenityName = $.trim($(tr).find(".col-amenityName")[0].value);
			amenitycharge.amenityChargeData.charge = $.trim($(tr).find(".col-charge")[0].value);
			
			var formTemplete = $("#formTemplete").clone();
			var formHtml = formTemplete.html()
				.replace("#[id]", amenitycharge.amenityChargeData.id)
				.replace("#[providerId]", amenitycharge.amenityChargeData.providerId)
				.replace("#[charge]", amenitycharge.amenityChargeData.charge);
	   		$(tr).html("<td colspan='4'>" + formHtml + "</td>");
	   		$("#btnSubmit").html('Update');
	   		$("#btnCreateNew").attr('disabled', true);
	   		$('#val-amenityId').html('<option value="0">Please select</option>');
	   		
	   		var providerTypeId = $("#var-providerTypeId").val();
	   		amenitycharge.getAmenityList(providerTypeId, amenitycharge.amenityChargeData.amenityId);
	   		amenitycharge.initValidation();
       	});

		$(document).on("click", ".delete-button", function(e) {
			var _btn = this;
			$(_btn).tooltip('hide');
			var tr = $(_btn).closest("tr");
			var id = $.trim($(tr).find(".col-id")[0].value);
			if(id > 0) {
				swal({
	                text: "Do you want to remove this amenity",
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
	            		amenitycharge.doDelete(_btn, id);
	            	}
	            });
			} else {
				$(tr).remove();
			}
       	});
	},
	
	onHotelSelect : function(o) {
		amenitycharge.hotelInfo = o;
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
		amenitycharge.getAmenityChargeList(o.providerId);
	},
	
	getAmenityList : function(providerTypeId, amenityId) {
		var url =  amenitycharge.getAmenityListUrl.replace("{#providerTypeId}", providerTypeId);
		amenitycharge.getAmenityListAjax = $.ajax({
			type: "GET",
            contentType: "application/json",
            url: url,
            dataType: 'json',
            timeout: 600000,
            success: function (data) {
            	if(data.status) {
            		amenitycharge.getAmenityListAjax = undefined;
            		var html = '<option value="0">Please select</option>';
            		$.each(data.datas, function(index, data) {
            			html += '<option value="' + data.id + '">' + data.name + '</option>';
            		});
            		$('#val-amenityId').html(html);
            		if (amenityId) {
            			$('#val-amenityId').val(amenityId);
            		}
            	} else {
            		console.log(data.errors);
            		amenitycharge.getAmenityListAjax = undefined;
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
            	amenitycharge.getAmenityListAjax = undefined;
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
	
	getAmenityChargeList : function(providerId) {
		var url =  amenitycharge.getAmenityChargeListUrl.replace("{#providerId}", providerId);
		amenitycharge.getAmenityChargeListAjax = $.ajax({
			type: "GET",
            contentType: "application/json",
            url: url,
            dataType: 'json',
            timeout: 600000,
            success: function (r) {
            	if(r.status) {
            		amenitycharge.getAmenityChargeListAjax = undefined;
    				if (r.datas.length > 0) {
    					html = '';
            			$.each(r.datas, function (index, data) {
            				var rowTemplete = $("#rowTemplete").clone();
                   			var rowHtml = rowTemplete.html()
	                   			.replace("#[id]", data.id)
			    				.replace("#[providerId]", data.providerId)
								.replace("#[amenityId]", data.amenityId)
								.replace("#[amenityName]", data.amenityName)
								.replace("#[amenityName]", data.amenityName)
								.replace("#[charge]", data.charge)
								.replace("#[charge]", data.charge);
                   			html += "<tr>" + rowHtml + "</tr>";
            			});
            			$('#dataTable').find('tbody').html(html);
    				} else {
    					$('#dataTable').find('tbody').html('');
    				}
            	} else {
            		console.log(r.errors);
            		amenitycharge.getAmenityChargeListAjax = undefined;
            		$('#dataTable').find('tbody').html('');
            	}
            },
            error: function (e) {
            	amenitycharge.getAmenityChargeListAjax = undefined;
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
            url: amenitycharge.createAmenityChargeUrl,
            data: JSON.stringify(serializeForm),
            dataType: 'json',
            timeout: 600000,
            success: function (data) {
            	if(data.status) {
            		$("#btnCreateNew").removeAttr('disabled');
            		var rowTemplete = $("#rowTemplete").clone();
           			var rowHtml = rowTemplete.html()
           				.replace("#[id]", data.datas[0].id)
	    				.replace("#[providerId]", data.datas[0].providerId)
						.replace("#[amenityId]", data.datas[0].amenityId)
						.replace("#[amenityName]", data.datas[0].amenityName)
						.replace("#[amenityName]", data.datas[0].amenityName)
						.replace("#[charge]", data.datas[0].charge)
						.replace("#[charge]", data.datas[0].charge);
           			$('#dataTable > tbody tr').first().html(rowHtml);
            		
            		Dashmix.helpers('notify', {
                		align: 'center', 
                		type: 'success', 
                		icon: 'fa fa-check mr-1', 
                		message: 'Amenity added successfully!',
                		delay: 1e3
        			});
            	} else {
            		$("#btnCreateNew").removeAttr('disabled');
            		$(_btn).removeAttr("disabled");
            		Dashmix.helpers('notify', {
                		align: 'center',
                		type: 'danger', 
                		icon: 'fa fa-times mr-1', 
                		message: data.errors && data.errors.length > 0 ? data.errors[0] : 'Failed to add amenity, please try again!',
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
	
	doUpdate : function(_btn) {
		$(_btn).attr("disabled", true);
		var form = $("#formComponent");
		var serializeForm = form.serializeObject();
		
		$.ajax({
			type: "PUT",
            contentType: "application/json",
            url: amenitycharge.updateAmenityChargeUrl,
            data: JSON.stringify(serializeForm),
            dataType: 'json',
            timeout: 600000,
            success: function (data) {
            	if(data.status) {
            		$("#btnCreateNew").removeAttr('disabled');
            		var rowTemplete = $("#rowTemplete").clone();
           			var rowHtml = rowTemplete.html()
        				.replace("#[id]", data.datas[0].id)
	    				.replace("#[providerId]", data.datas[0].providerId)
						.replace("#[amenityId]", data.datas[0].amenityId)
						.replace("#[amenityName]", data.datas[0].amenityName)
						.replace("#[amenityName]", data.datas[0].amenityName)
						.replace("#[charge]", data.datas[0].charge)
						.replace("#[charge]", data.datas[0].charge);
           			$(_btn).closest("tr").html(rowHtml);
            		
           			amenitycharge.amenityChargeData.id = "0";
                   	amenitycharge.amenityChargeData.providerId = "0";
                   	amenitycharge.amenityChargeData.amenityId = "0";
                   	amenitycharge.amenityChargeData.amenityName = "";
                   	amenitycharge.amenityChargeData.charge = "0.00";
           			
            		Dashmix.helpers('notify', {
                		align: 'center', 
                		type: 'success', 
                		icon: 'fa fa-check mr-1', 
                		message: 'Amenity charge updated successfully!'
        			});
            	} else {
            		console.log(data.errors);
            		$("#btnCreateNew").removeAttr('disabled');
            		$(_btn).removeAttr("disabled");
            		Dashmix.helpers('notify', {
                		align: 'center',
                		type: 'danger', 
                		icon: 'fa fa-times mr-1', 
                		message: data.errors && data.errors.length > 0 ? data.errors[0] : 'Failed to update amenity charge, please try again!'
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
            		message: 'Failed to process request!'
    			});
            }
		});
	},
	
	doDelete : function(_btn, id) {
		$(_btn).attr("disabled", true);

		$.ajax({
			type: "DELETE",
            contentType: "application/json",
            url: amenitycharge.deleteAmenityChargeUrl + id,
            dataType: 'json',
            timeout: 600000,
            success: function (data) {
            	if(data.status) {
            		$(_btn).closest("tr").remove();
            		Dashmix.helpers('notify', {
                		align: 'center', 
                		type: 'success', 
                		icon: 'fa fa-check mr-1', 
                		message: 'Amenity removed successfully!',
                		delay: 1e3
        			});
            	} else {
            		console.log(data.errors);
            		$(_btn).removeAttr("disabled");
            		Dashmix.helpers('notify', {
                		align: 'center',
                		type: 'danger', 
                		icon: 'fa fa-times mr-1', 
                		message: data.errors && data.errors.length > 0 ? data.errors[0] : 'Failed to remove amenity, please try again!',
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
		$.validator.addMethod('decimal', function(value, element) {
		  return this.optional(element) || /^((\d+(\\.\d{0,2})?)|((\d*(\.\d{1,2}))))$/.test(value);
		}, "Please enter a correct number, format 0.00");
		
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
            	"amenityId": {
                    min: 1,
                    remote : {
                    	url : amenitycharge.isAvailableUrl,
                    	type : "GET",
                    	data : {
                    		amenityChargeId : function() {
                    			return $("#val-id").val();
                    		},
                    		providerId : function() {
                    			return $("#val-providerId").val();
                    		}
                    	}
                    }
                },
                "charge": {
                	required: true, decimal: true
                }
            }, 
            messages : {
            	"amenityId": {
                	min: "Please select amenity",
                    remote: "This amenity is already assigned for this hotel"
                },
                "charge": {
                	required: "Please enter charge"
                }
            }
        });
	}
};

$(document).ready(function() {
	amenitycharge.init();
});