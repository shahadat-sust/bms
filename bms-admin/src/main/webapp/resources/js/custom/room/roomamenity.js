var roomamenity = {
	hotelInfo: undefined,
	itemAmenityData : {
		id : "0",
   		itemId : "0",
   		amenityId : "0",
   		amenityName : "",
   		quantity : "1"
	},
	getRoomAmenityListUrl: undefined,
	getRoomAmenityListAjax: undefined,
	getRoomTypeListUrl: undefined,
	getRoomTypeListAjax: undefined,
	getRoomCategoryListUrl: undefined,
	getRoomCategoryListAjax: undefined,
	getRoomListUrl: undefined,
	getRoomListAjax: undefined,
	getRoomCategoryAmenityUrl: undefined,
	getRoomCategoryAmenityAjax: undefined,
	getAmenityListUrl: undefined,
	getAmenityListAjax: undefined,
	isAvailableUrl: undefined,
	createRoomAmenityUrl: undefined,
	updateRoomAmenityUrl: undefined,
	deleteRoomAmenityUrl: undefined,
		
	init : function() {
		 $(".rating").raty({
    		starType: "i",
    		hints: ["One Star", "Two Stars", "Three Stars", "Four Stars", "Five Stars"],
            starOff: $(this).data("star-off") || "fa fa-fw fa-star text-muted",
            starOn: $(this).data("star-on") || "fa fa-fw fa-star text-warning",
            readOnly: true
        });
		
		 hotelsearchmodal.init(roomamenity.onHotelSelect);
		 
		 $(document).on("click", "#btnCreateNew", function(e) {
			var _btn = this;
       		$("#btnCreateNew").attr('disabled', true);
       		var itemId = $("#val-selected-itemId").val();
       		var providerTypeId = $("#var-selected-providerTypeId").val();

       		var formTemplete = $("#formTemplete").clone();
    		var formHtml = formTemplete.html()
    		.replace("#[id]", "0")
    		.replace("#[itemId]", itemId)
    		.replace("#[amenityId]", "0")
    		.replace("#[amenityName]", "")
    		.replace("#[quantity]", "1");
       		$("#dataTable > tbody").prepend("<tr><td colspan='3'>" + formHtml + "</td></tr>");
       		$("#btnSubmit").html('Save');

       		roomamenity.getAmenityList(providerTypeId);
       		roomamenity.initValidation();
       	});
       	
       	$(document).on("click", "#btnCancel", function(e) {
       		var _btn = this;
       		if(roomamenity.itemAmenityData.id > 0) {
       			var rowTemplete = $("#rowTemplete").clone();
       			var rowHtml = rowTemplete.html()
       				.replace("#[id]", roomamenity.itemAmenityData.id)
                	.replace("#[itemId]", roomamenity.itemAmenityData.itemId)
                	.replace("#[amenityId]", roomamenity.itemAmenityData.amenityId)
                	.replace("#[amenityName]", roomamenity.itemAmenityData.amenityName)
                	.replace("#[amenityName]", roomamenity.itemAmenityData.amenityName)
                	.replace("#[quantity]", roomamenity.itemAmenityData.quantity)
                	.replace("#[quantity]", roomamenity.itemAmenityData.quantity);
               	$(_btn).closest("tr").html(rowHtml);
               	
               	roomamenity.itemAmenityData.id = "0";
               	roomamenity.itemAmenityData.itemId = "0";
               	roomamenity.itemAmenityData.amenityId = "0";
               	roomamenity.itemAmenityData.amenityName = "";
               	roomamenity.itemAmenityData.quantity = "1";
       		} else {
       			$('#dataTable > tbody tr').first().remove();
       		}
       		$("#btnCreateNew").removeAttr('disabled');
       	});
       	
       	$(document).on("click", "#btnSubmit", function(e) {
       		var _btn = this;
       		if($("#formComponent").valid()) {
       			swal({
                    text: "Do you want to " + (roomamenity.itemAmenityData.id > 0 ? "update" : "add") + " this amenity",
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
	                		if(roomamenity.itemAmenityData.id > 0) {
	                			roomamenity.doUpdate(_btn);
	                		} else {
	                			roomamenity.doCreate(_btn);
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
			roomamenity.itemAmenityData.id = $.trim($(tr).find(".col-id")[0].value);
			roomamenity.itemAmenityData.itemId = $.trim($(tr).find(".col-itemId")[0].value);
			roomamenity.itemAmenityData.amenityId = $.trim($(tr).find(".col-amenityId")[0].value);
			roomamenity.itemAmenityData.amenityName = $.trim($(tr).find(".col-amenityName")[0].value);
			roomamenity.itemAmenityData.quantity = $.trim($(tr).find(".col-quantity")[0].value);
			
			var formTemplete = $("#formTemplete").clone();
			var formHtml = formTemplete.html()
				.replace("#[id]", roomamenity.itemAmenityData.id)
            	.replace("#[itemId]", roomamenity.itemAmenityData.itemId)
            	.replace("#[amenityId]", roomamenity.itemAmenityData.amenityId)
            	.replace("#[amenityName]", roomamenity.itemAmenityData.amenityName)
            	.replace("#[amenityName]", roomamenity.itemAmenityData.amenityName)
            	.replace("#[quantity]", roomamenity.itemAmenityData.quantity)
            	.replace("#[quantity]", roomamenity.itemAmenityData.quantity);
	   		$(tr).html("<td colspan='3'>" + formHtml + "</td>");

	   		$("#btnSubmit").html('Update');
	   		$("#btnCreateNew").attr('disabled', true);
	   		
	   		var providerTypeId = $("#var-selected-providerTypeId").val();
	   		roomamenity.getAmenityList(providerTypeId, roomamenity.itemAmenityData.amenityId);
       		roomamenity.initValidation();
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
	            		roomamenity.doDelete(_btn, id);
	            	}
	            });
			} else {
				$(tr).remove();
			}
       	});

		$(document).on("change", "#val-selected-itemTypeId", function(e) {
			var itemTypeId = $("#val-selected-itemTypeId").val();
			if(itemTypeId != "" && itemTypeId != "0") {
				roomamenity.getRoomCategoryList(itemTypeId);
			} else {
				$("#val-selected-itemCategoryId").html('<option value="0">Please select</option>');
				$("#val-selected-itemId").html('<option value="0">Please select</option>');
			}
			$("#btnCreateNew").attr('disabled', true);
			$('#dataTable').find('tbody').html('');
       	});
		
		$(document).on("change", "#val-selected-itemCategoryId", function(e) {
			var itemCategoryId = $("#val-selected-itemCategoryId").val();
			if(itemCategoryId != "" && itemCategoryId != "0") {
				$("#btnCreateNew").removeAttr('disabled');
				roomamenity.getRoomList(itemCategoryId);
			} else {
				$("#val-selected-itemId").html('<option value="0">Please select</option>');
			}
			$("#btnCreateNew").attr('disabled', true);
			$('#dataTable').find('tbody').html('');
       	});
		
		$(document).on("change", "#val-selected-itemId", function(e) {
			var itemId = $("#val-selected-itemId").val();
			if(itemId != "" && itemId != "0") {
				$("#btnCreateNew").removeAttr('disabled');
				roomamenity.getRoomAmenityList(itemId);
			} else {
				$("#btnCreateNew").attr('disabled', true);
				$('#dataTable').find('tbody').html('');
			}
       	});
		
		$(document).on("change", "#val-amenityId", function(e) {
			var itemCategoryId = $("#val-selected-itemCategoryId").val();
			var amenityId = $("#val-amenityId").val();
			if(amenityId != "" && amenityId != "0") {
				roomamenity.getRoomCategoryAmenity(itemCategoryId, amenityId);
			} else {
				$("#val-amenityId").val("1");
			}
       	});
	},
	
	onHotelSelect : function(o) {
		roomamenity.hotelInfo = o;
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
   		$("#val-selected-itemId").html('<option value="0">Please select</option>');
   		$("#btnCreateNew").attr('disabled', true);
   		$('#dataTable').find('tbody').html('');

   		roomamenity.getRoomTypeList(o.providerId);
	},
	
	getRoomTypeList : function(providerId) {
		var url =  roomamenity.getRoomTypeListUrl.replace("{#providerId}", providerId);
		roomamenity.getRoomTypeListAjax = $.ajax({
			type: "GET",
            contentType: "application/json",
            url: url,
            dataType: 'json',
            timeout: 600000,
            success: function (data) {
            	if(data.status) {
            		roomamenity.getRoomTypeListAjax = undefined;
            		var html = '<option value="0">Please select</option>';
            		$.each(data.datas, function(index, data) {
            			html += '<option value="' + data.id + '">' + data.name + '</option>';
            		});
            		$('#val-selected-itemTypeId').html(html);
            	} else {
            		console.log(data.errors);
            		roomamenity.getRoomTypeListAjax = undefined;
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
            	roomamenity.getRoomTypeListAjax = undefined;
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
		var url =  roomamenity.getRoomCategoryListUrl.replace("{#itemTypeId}", itemTypeId);
		roomamenity.getRoomCategoryListAjax = $.ajax({
			type: "GET",
            contentType: "application/json",
            url: url,
            dataType: 'json',
            timeout: 600000,
            success: function (data) {
            	if(data.status) {
            		roomamenity.getRoomCategoryListAjax = undefined;
            		var html = '<option value="0">Please select</option>';
            		$.each(data.datas, function(index, data) {
            			html += '<option value="' + data.id + '">' + data.name + ' [Rent: ' + data.rent + ' tk, Capacity: ' + data.capacity + ' persons, Size: ' + data.roomCategoryData.size + ' sqft]' + '</option>';
            		});
            		$('#val-selected-itemCategoryId').html(html);
            	} else {
            		console.log(data.errors);
            		roomamenity.getRoomCategoryListAjax = undefined;
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
            	roomamenity.getRoomCategoryListAjax = undefined;
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
	
	getRoomCategoryAmenity : function(itemCategoryId, amenityId) {
		var url =  roomamenity.getRoomCategoryAmenityUrl.replace("{#itemCategoryId}", itemCategoryId).replace("{#amenityId}", amenityId);
		roomamenity.getRoomCategoryAmenityAjax = $.ajax({
			type: "GET",
            contentType: "application/json",
            url: url,
            dataType: 'json',
            timeout: 600000,
            success: function (data) {
            	if(data.status) {
            		roomamenity.getRoomCategoryAmenityAjax = undefined;
            		if (data.datas && data.datas.length > 0) {
	            		$('#val-quantity').val(data.datas[0].quantity);
            		} else {
            			$('#val-quantity').val("1");
            		}
            	} else {
            		console.log(data.errors);
            		roomamenity.getRoomCategoryAmenityAjax = undefined;
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
            	roomamenity.getRoomCategoryAmenityAjax = undefined;
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
	
	getRoomList : function(itemCategoryId) {
		var url =  roomamenity.getRoomListUrl.replace("{#itemCategoryId}", itemCategoryId);
		roomamenity.getRoomListAjax = $.ajax({
			type: "GET",
            contentType: "application/json",
            url: url,
            dataType: 'json',
            timeout: 600000,
            success: function (data) {
            	if(data.status) {
            		roomamenity.getRoomListAjax = undefined;
            		var html = '<option value="0">Please select</option>';
            		$.each(data.datas, function(index, data) {
            			html += '<option value="' + data.id + '">' + data.itemNo + ' [Rent: ' + data.rent + ' tk, Capacity: ' + data.capacity + ' persons, Size: ' + data.roomData.size + ' sqft]' + '</option>';
            		});
            		$('#val-selected-itemId').html(html);
            	} else {
            		console.log(data.errors);
            		roomamenity.getRoomListAjax = undefined;
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
            	roomamenity.getRoomListAjax = undefined;
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
		var url =  roomamenity.getAmenityListUrl.replace("{#providerTypeId}", providerTypeId);
		roomamenity.getAmenityListAjax = $.ajax({
			type: "GET",
            contentType: "application/json",
            url: url,
            dataType: 'json',
            timeout: 600000,
            success: function (data) {
            	if(data.status) {
            		roomamenity.getAmenityListAjax = undefined;
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
            		roomamenity.getAmenityListAjax = undefined;
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
            	roomamenity.getAmenityListAjax = undefined;
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
	
	getRoomAmenityList : function(itemId) {
		var url =  roomamenity.getRoomAmenityListUrl.replace("{#itemId}", itemId);
		roomamenity.getRoomAmenityListAjax = $.ajax({
			type: "GET",
            contentType: "application/json",
            url: url,
            dataType: 'json',
            timeout: 600000,
            success: function (r) {
            	if(r.status) {
            		roomamenity.getRoomAmenityListAjax = undefined;
    				if (r.datas.length > 0) {
    					html = '';
            			$.each(r.datas, function (index, data) {
            				var rowTemplete = $("#rowTemplete").clone();
                   			var rowHtml = rowTemplete.html()
                   				.replace("#[id]", data.id)
				            	.replace("#[itemId]", data.itemId)
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
            		roomamenity.getRoomAmenityListAjax = undefined;
            		$('#dataTable').find('tbody').html('');
            	}
            },
            error: function (e) {
            	roomamenity.getRoomAmenityListAjax = undefined;
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
            url: roomamenity.createRoomAmenityUrl,
            data: JSON.stringify(serializeForm),
            dataType: 'json',
            timeout: 600000,
            success: function (data) {
            	if(data.status) {
            		$("#btnCreateNew").removeAttr('disabled');
            		var rowTemplete = $("#rowTemplete").clone();
           			var rowHtml = rowTemplete.html()
           				.replace("#[id]", data.datas[0].id)
		            	.replace("#[itemId]", data.datas[0].itemId)
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
            url: roomamenity.updateRoomAmenityUrl,
            data: JSON.stringify(serializeForm),
            dataType: 'json',
            timeout: 600000,
            success: function (data) {
            	if(data.status) {
            		$("#btnCreateNew").removeAttr('disabled');
            		var rowTemplete = $("#rowTemplete").clone();
           			var rowHtml = rowTemplete.html()
           				.replace("#[id]", data.datas[0].id)
		            	.replace("#[itemId]", data.datas[0].itemId)
		            	.replace("#[amenityId]", data.datas[0].amenityId)
		            	.replace("#[amenityName]", data.datas[0].amenityName)
		            	.replace("#[amenityName]", data.datas[0].amenityName)
		            	.replace("#[quantity]", data.datas[0].quantity)
		            	.replace("#[quantity]", data.datas[0].quantity);
           			$(_btn).closest("tr").html(rowHtml);
            		
           			roomamenity.itemAmenityData.id = "0";
                   	roomamenity.itemAmenityData.itemId = "0";
                   	roomamenity.itemAmenityData.amenityId = "0";
                   	roomamenity.itemAmenityData.amenityName = "";
                   	roomamenity.itemAmenityData.quantity = "1";
           			
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
            url: roomamenity.deleteRoomAmenityUrl + id,
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
                    	url : roomamenity.isAvailableUrl,
                    	type : "GET",
                    	data : {
                    		roomAmenityId : function() {
                    			return $('#val-id').val();
                    		},
                    		amenityId : function() {
                    			return $('#val-amenityId').val();
                    		},
                    		roomId : function() {
                    			return $("#val-itemId").val();
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
	roomamenity.init();
});