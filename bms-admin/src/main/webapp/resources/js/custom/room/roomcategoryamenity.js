var roomcategoryamenity = {
	hotelInfo: undefined,
	itemCategoryAmenityData : {
		id : "0",
   		itemCategoryId : "0",
   		amenityId : "0",
   		amenityName : "",
   		quantity : "1"
	},
	getRoomCategoryAmenityListUrl: undefined,
	getRoomCategoryAmenityListAjax: undefined,
	getRoomTypeListUrl: undefined,
	getRoomTypeListAjax: undefined,
	getRoomCategoryListUrl: undefined,
	getRoomCategoryListAjax: undefined,
	getAmenityListUrl: undefined,
	getAmenityListAjax: undefined,
	isAvailableUrl: undefined,
	createRoomCategoryAmenityUrl: undefined,
	updateRoomCategoryAmenityUrl: undefined,
	deleteRoomCategoryAmenityUrl: undefined,
		
	init : function() {
		 $(".rating").raty({
    		starType: "i",
    		hints: ["One Star", "Two Stars", "Three Stars", "Four Stars", "Five Stars"],
            starOff: $(this).data("star-off") || "fa fa-fw fa-star text-muted",
            starOn: $(this).data("star-on") || "fa fa-fw fa-star text-warning",
            readOnly: true
        });
		
		 hotelsearchmodal.init(roomcategoryamenity.onHotelSelect);
		 
		 $(document).on("click", "#btnCreateNew", function(e) {
			var _btn = this;
       		$("#btnCreateNew").attr('disabled', true);
       		var itemCategoryId = $("#val-selected-itemCategoryId").val();
       		var providerTypeId = $("#var-selected-providerTypeId").val();

       		var formTemplete = $("#formTemplete").clone();
    		var formHtml = formTemplete.html()
    		.replace("#[id]", "0")
    		.replace("#[itemCategoryId]", itemCategoryId)
    		.replace("#[amenityId]", "0")
    		.replace("#[amenityName]", "")
    		.replace("#[quantity]", "1");
       		$("#dataTable > tbody").prepend("<tr><td colspan='3'>" + formHtml + "</td></tr>");
       		$("#btnSubmit").html('Save');

       		roomcategoryamenity.getAmenityList(providerTypeId);
       		roomcategoryamenity.initValidation();
       	});
       	
       	$(document).on("click", "#btnCancel", function(e) {
       		var _btn = this;
       		if(roomcategoryamenity.itemCategoryAmenityData.id > 0) {
       			var rowTemplete = $("#rowTemplete").clone();
       			var rowHtml = rowTemplete.html()
       				.replace("#[id]", roomcategoryamenity.itemCategoryAmenityData.id)
                	.replace("#[itemCategoryId]", roomcategoryamenity.itemCategoryAmenityData.itemCategoryId)
                	.replace("#[amenityId]", roomcategoryamenity.itemCategoryAmenityData.amenityId)
                	.replace("#[amenityName]", roomcategoryamenity.itemCategoryAmenityData.amenityName)
                	.replace("#[amenityName]", roomcategoryamenity.itemCategoryAmenityData.amenityName)
                	.replace("#[quantity]", roomcategoryamenity.itemCategoryAmenityData.quantity)
                	.replace("#[quantity]", roomcategoryamenity.itemCategoryAmenityData.quantity);
               	$(_btn).closest("tr").html(rowHtml);
               	
               	roomcategoryamenity.itemCategoryAmenityData.id = "0";
               	roomcategoryamenity.itemCategoryAmenityData.itemCategoryId = "0";
               	roomcategoryamenity.itemCategoryAmenityData.amenityId = "0";
               	roomcategoryamenity.itemCategoryAmenityData.amenityName = "";
               	roomcategoryamenity.itemCategoryAmenityData.quantity = "1";
       		} else {
       			$('#dataTable > tbody tr').first().remove();
       		}
       		$("#btnCreateNew").removeAttr('disabled');
       	});
       	
       	$(document).on("click", "#btnSubmit", function(e) {
       		var _btn = this;
       		if($("#formComponent").valid()) {
       			swal({
                    text: "Do you want to " + (roomcategoryamenity.itemCategoryAmenityData.id > 0 ? "update" : "add") + " this amenity",
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
	                		if(roomcategoryamenity.itemCategoryAmenityData.id > 0) {
	                			roomcategoryamenity.doUpdate(_btn);
	                		} else {
	                			roomcategoryamenity.doCreate(_btn);
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
			roomcategoryamenity.itemCategoryAmenityData.id = $.trim($(tr).find(".col-id")[0].value);
			roomcategoryamenity.itemCategoryAmenityData.itemCategoryId = $.trim($(tr).find(".col-itemCategoryId")[0].value);
			roomcategoryamenity.itemCategoryAmenityData.amenityId = $.trim($(tr).find(".col-amenityId")[0].value);
			roomcategoryamenity.itemCategoryAmenityData.amenityName = $.trim($(tr).find(".col-amenityName")[0].value);
			roomcategoryamenity.itemCategoryAmenityData.quantity = $.trim($(tr).find(".col-quantity")[0].value);
			
			var formTemplete = $("#formTemplete").clone();
			var formHtml = formTemplete.html()
				.replace("#[id]", roomcategoryamenity.itemCategoryAmenityData.id)
            	.replace("#[itemCategoryId]", roomcategoryamenity.itemCategoryAmenityData.itemCategoryId)
            	.replace("#[amenityId]", roomcategoryamenity.itemCategoryAmenityData.amenityId)
            	.replace("#[amenityName]", roomcategoryamenity.itemCategoryAmenityData.amenityName)
            	.replace("#[amenityName]", roomcategoryamenity.itemCategoryAmenityData.amenityName)
            	.replace("#[quantity]", roomcategoryamenity.itemCategoryAmenityData.quantity)
            	.replace("#[quantity]", roomcategoryamenity.itemCategoryAmenityData.quantity);
	   		$(tr).html("<td colspan='3'>" + formHtml + "</td>");

	   		$("#btnSubmit").html('Update');
	   		$("#btnCreateNew").attr('disabled', true);
	   		
	   		var providerTypeId = $("#var-selected-providerTypeId").val();
	   		roomcategoryamenity.getAmenityList(providerTypeId, roomcategoryamenity.itemCategoryAmenityData.amenityId);
       		roomcategoryamenity.initValidation();
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
	            		roomcategoryamenity.doDelete(_btn, id);
	            	}
	            });
			} else {
				$(tr).remove();
			}
       	});

		$(document).on("change", "#val-selected-itemTypeId", function(e) {
			var itemTypeId = $("#val-selected-itemTypeId").val();
			if(itemTypeId != "" && itemTypeId != "0") {
				roomcategoryamenity.getRoomCategoryList(itemTypeId);
			} else {
				$("#val-selected-itemCategoryId").html('<option value="0">Please select</option>');
			}
			$("#btnCreateNew").attr('disabled', true);
			$('#dataTable').find('tbody').html('');
       	});
		
		$(document).on("change", "#val-selected-itemCategoryId", function(e) {
			var itemCategoryId = $("#val-selected-itemCategoryId").val();
			if(itemCategoryId != "" && itemCategoryId != "0") {
				$("#btnCreateNew").removeAttr('disabled');
				roomcategoryamenity.getRoomCategoryAmenityList(itemCategoryId);
			} else {
				$("#btnCreateNew").attr('disabled', true);
				$('#dataTable').find('tbody').html('');
			}
       	});
	},
	
	onHotelSelect : function(o) {
		roomcategoryamenity.hotelInfo = o;
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

   		$('#val-selected-itemCategoryId').html('<option value="0">Please select</option>');
   		$("#btnCreateNew").attr('disabled', true);
   		$('#dataTable').find('tbody').html('');

   		roomcategoryamenity.getRoomTypeList(o.providerId);
	},
	
	getRoomTypeList : function(providerId) {
		var url =  roomcategoryamenity.getRoomTypeListUrl.replace("{#providerId}", providerId);
		roomcategoryamenity.getRoomTypeListAjax = $.ajax({
			type: "GET",
            contentType: "application/json",
            url: url,
            dataType: 'json',
            timeout: 600000,
            success: function (data) {
            	if(data.status) {
            		roomcategoryamenity.getRoomTypeListAjax = undefined;
            		var html = '<option value="0">Please select</option>';
            		$.each(data.datas, function(index, data) {
            			html += '<option value="' + data.id + '">' + data.name + '</option>';
            		});
            		$('#val-selected-itemTypeId').html(html);
            	} else {
            		console.log(data.errors);
            		roomcategoryamenity.getRoomTypeListAjax = undefined;
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
            	roomcategoryamenity.getRoomTypeListAjax = undefined;
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
	
	getRoomCategoryList : function(itemTypeId) {
		var url =  roomcategoryamenity.getRoomCategoryListUrl.replace("{#itemTypeId}", itemTypeId);
		roomcategoryamenity.getRoomCategoryListAjax = $.ajax({
			type: "GET",
            contentType: "application/json",
            url: url,
            dataType: 'json',
            timeout: 600000,
            success: function (data) {
            	if(data.status) {
            		roomcategoryamenity.getRoomCategoryListAjax = undefined;
            		var html = '<option value="0">Please select</option>';
            		$.each(data.datas, function(index, data) {
            			html += '<option value="' + data.id + '">' + data.name + ' [Rent: ' + data.rent + 'tk, Size: ' + data.roomCategoryData.size + 'sqft]' + '</option>';
            		});
            		$('#val-selected-itemCategoryId').html(html);
            	} else {
            		console.log(data.errors);
            		roomcategoryamenity.getRoomCategoryListAjax = undefined;
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
            	roomcategoryamenity.getRoomCategoryListAjax = undefined;
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
	
	getAmenityList : function(providerTypeId, amenityId) {
		var url =  roomcategoryamenity.getAmenityListUrl.replace("{#providerTypeId}", providerTypeId);
		roomcategoryamenity.getAmenityListAjax = $.ajax({
			type: "GET",
            contentType: "application/json",
            url: url,
            dataType: 'json',
            timeout: 600000,
            success: function (data) {
            	if(data.status) {
            		roomcategoryamenity.getAmenityListAjax = undefined;
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
            		roomcategoryamenity.getAmenityListAjax = undefined;
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
            	roomcategoryamenity.getAmenityListAjax = undefined;
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
	
	getRoomCategoryAmenityList : function(itemCategoryId) {
		var url =  roomcategoryamenity.getRoomCategoryAmenityListUrl.replace("{#itemCategoryId}", itemCategoryId);
		roomcategoryamenity.getRoomCategoryAmenityListAjax = $.ajax({
			type: "GET",
            contentType: "application/json",
            url: url,
            dataType: 'json',
            timeout: 600000,
            success: function (r) {
            	if(r.status) {
            		roomcategoryamenity.getRoomCategoryAmenityListAjax = undefined;
    				if (r.datas.length > 0) {
    					html = '';
            			$.each(r.datas, function (index, data) {
            				var rowTemplete = $("#rowTemplete").clone();
                   			var rowHtml = rowTemplete.html()
                   				.replace("#[id]", data.id)
				            	.replace("#[itemCategoryId]", data.itemCategoryId)
				            	.replace("#[amenityId]", data.amenityId)
				            	.replace("#[amenityName]", data.amenityName)
				            	.replace("#[amenityName]", data.amenityName)
				            	.replace("#[quantity]", data.quantity)
				            	.replace("#[quantity]", data.quantity);
                   			html += "<tr>" + rowHtml + "</tr>";
            			});
            			$('#dataTable').find('tbody').html(html);
    				} else {
    					$('#dataTable').find('tbody').html('');
    				}
            	} else {
            		console.log(r.errors);
            		roomcategoryamenity.getRoomCategoryAmenityListAjax = undefined;
            		$('#dataTable').find('tbody').html('');
            	}
            },
            error: function (e) {
            	roomcategoryamenity.getRoomCategoryAmenityListAjax = undefined;
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
            url: roomcategoryamenity.createRoomCategoryAmenityUrl,
            data: JSON.stringify(serializeForm),
            dataType: 'json',
            timeout: 600000,
            success: function (data) {
            	if(data.status) {
            		$("#btnCreateNew").removeAttr('disabled');
            		var rowTemplete = $("#rowTemplete").clone();
           			var rowHtml = rowTemplete.html()
           				.replace("#[id]", data.datas[0].id)
		            	.replace("#[itemCategoryId]", data.datas[0].itemCategoryId)
		            	.replace("#[amenityId]", data.datas[0].amenityId)
		            	.replace("#[amenityName]", data.datas[0].amenityName)
		            	.replace("#[amenityName]", data.datas[0].amenityName)
		            	.replace("#[quantity]", data.datas[0].quantity)
		            	.replace("#[quantity]", data.datas[0].quantity);
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
            url: roomcategoryamenity.updateRoomCategoryAmenityUrl,
            data: JSON.stringify(serializeForm),
            dataType: 'json',
            timeout: 600000,
            success: function (data) {
            	if(data.status) {
            		$("#btnCreateNew").removeAttr('disabled');
            		var rowTemplete = $("#rowTemplete").clone();
           			var rowHtml = rowTemplete.html()
           				.replace("#[id]", data.datas[0].id)
		            	.replace("#[itemCategoryId]", data.datas[0].itemCategoryId)
		            	.replace("#[amenityId]", data.datas[0].amenityId)
		            	.replace("#[amenityName]", data.datas[0].amenityName)
		            	.replace("#[amenityName]", data.datas[0].amenityName)
		            	.replace("#[quantity]", data.datas[0].quantity)
		            	.replace("#[quantity]", data.datas[0].quantity);
           			$(_btn).closest("tr").html(rowHtml);
            		
           			roomcategoryamenity.itemCategoryAmenityData.id = "0";
                   	roomcategoryamenity.itemCategoryAmenityData.itemCategoryId = "0";
                   	roomcategoryamenity.itemCategoryAmenityData.amenityId = "0";
                   	roomcategoryamenity.itemCategoryAmenityData.amenityName = "";
                   	roomcategoryamenity.itemCategoryAmenityData.quantity = "1";
           			
            		Dashmix.helpers('notify', {
                		align: 'center', 
                		type: 'success', 
                		icon: 'fa fa-check mr-1', 
                		message: 'Amenity updated successfully!',
                		delay: 1e3
        			});
            	} else {
            		console.log(data.errors);
            		$("#btnCreateNew").removeAttr('disabled');
            		$(_btn).removeAttr("disabled");
            		Dashmix.helpers('notify', {
                		align: 'center',
                		type: 'danger', 
                		icon: 'fa fa-times mr-1', 
                		message: data.errors && data.errors.length > 0 ? data.errors[0] : 'Failed to update amenity, please try again!',
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
            url: roomcategoryamenity.deleteRoomCategoryAmenityUrl + id,
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
                    	url : roomcategoryamenity.isAvailableUrl,
                    	type : "GET",
                    	data : {
                    		roomCategoryAmenityId : function() {
                    			return $('#val-id').val();
                    		},
                    		amenityId : function() {
                    			return $('#val-amenityId').val();
                    		},
                    		roomCategoryId : function() {
                    			return $("#val-itemCategoryId").val();
                    		}
                    	}
                    }
                },
                "quantity": {
                	required: true, digits: true, min: 1
                }
            }, 
            messages : {
            	"amenityId": {
            		min: "Please enter amenity",
                    remote: "This amenity is already used"
                },
                "quantity": {
                	required: "Please enter quanity"
                }
            }
        });
	}
};

$(document).ready(function() {
	roomcategoryamenity.init();
});