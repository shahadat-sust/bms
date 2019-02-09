var room = {
	hotelInfo: undefined,
	itemData : {
		id : "0",
		itemTypeId : "0",
   		itemTypeName : "",
   		itemCategoryId : "0",
   		itemCategoryName : "",
   		itemNo : "",
   		rent : "0.00",
   		size : "0",
   		floorNo : "0",
   		landLine : "",
   		active : "true"
	},
	getRoomListUrl: undefined,
	getRoomListAjax: undefined,
	getRoomTypeListUrl: undefined,
	getRoomTypeListAjax: undefined,
	getRoomCategoryListUrl: undefined,
	getRoomCategoryListAjax: undefined,
	getRoomCategoryUrl: undefined,
	getRoomCategoryAjax: undefined,
	isRoomNoAvailableUrl: undefined,
	createRoomUrl: undefined,
	updateRoomUrl: undefined,
	deleteRoomUrl: undefined,
		
	init : function() {
		 $(".rating").raty({
    		starType: "i",
    		hints: ["One Star", "Two Stars", "Three Stars", "Four Stars", "Five Stars"],
            starOff: $(this).data("star-off") || "fa fa-fw fa-star text-muted",
            starOn: $(this).data("star-on") || "fa fa-fw fa-star text-warning",
            readOnly: true
        });
		
		 hotelsearchmodal.init(room.onHotelSelect);
		 
		 $(document).on("click", "#btnCreateNew", function(e) {
			var _btn = this;
       		$("#btnCreateNew").attr('disabled', true);

       		var formTemplete = $("#formTemplete").clone();
    		var formHtml = formTemplete.html()
    		.replace("#[id]", "0")
    		.replace("#[itemTypeId]", "0")
    		.replace("#[itemTypeName]", "")
    		.replace("#[itemCategoryId]", "0")
    		.replace("#[itemCategoryName]", "")
    		.replace("#[itemNo]", "")
    		.replace("#[rent]", "0.00")
    		.replace("#[size]", "0")
    		.replace("#[floorNo]", "0")
    		.replace("#[landLine]", "")
    		.replace("#[active]", "true");
       		$("#dataTable > tbody").prepend("<tr><td colspan='9'>" + formHtml + "</td></tr>");
       		$("#btnSubmit").html('Save');
       		$('#val-itemTypeId').html('<option value="0">Please select</option>');
       		$('#val-itemCategoryId').html('<option value="0">Please select</option>');
       		
       		var providerId = $("#var-selected-providerId").val();
       		room.getRoomTypeList(providerId);
       		room.initValidation();
       	});
       	
       	$(document).on("click", "#btnCancel", function(e) {
       		var _btn = this;
       		if(room.itemData.id > 0) {
       			var rowTemplete = $("#rowTemplete").clone();
       			var rowHtml = rowTemplete.html()
       				.replace("#[id]", room.itemData.id)
                	.replace("#[itemTypeId]", room.itemData.itemTypeId)
                	.replace("#[itemTypeName]", room.itemData.itemTypeName)
                	.replace("#[itemTypeName]", room.itemData.itemTypeName)
                	.replace("#[itemCategoryId]", room.itemData.itemCategoryId)
                	.replace("#[itemCategoryName]", room.itemData.itemCategoryName)
                	.replace("#[itemCategoryName]", room.itemData.itemCategoryName)
                	.replace("#[itemNo]", room.itemData.itemNo)
                	.replace("#[itemNo]", room.itemData.itemNo)
                	.replace("#[rent]", room.itemData.rent)
                	.replace("#[rent]", room.itemData.rent)
                	.replace("#[size]", room.itemData.size)
                	.replace("#[size]", room.itemData.size)
                	.replace("#[floorNo]", room.itemData.floorNo)
                	.replace("#[floorNo]", room.itemData.floorNo)
                	.replace("#[landLine]", room.itemData.landLine)
                	.replace("#[landLine]", room.itemData.landLine)
                	.replace("#[active]", room.itemData.active)
                	.replace("#[active]", room.itemData.active);
               	$(_btn).closest("tr").html(rowHtml);
               	
               	room.itemData.id = "0";
               	room.itemData.itemTypeId = "0";
               	room.itemData.itemTypeName = "";
               	room.itemData.itemCategoryId = "0";
               	room.itemData.itemCategoryName = "";
               	room.itemData.itemNo = "";
               	room.itemData.rent = "0.00";
               	room.itemData.size = "0";
               	room.itemData.floorNo = "0";
               	room.itemData.landLine = "";
               	room.itemData.active = "true";
       		} else {
       			$('#dataTable > tbody tr').first().remove();
       		}
       		$("#btnCreateNew").removeAttr('disabled');
       	});
       	
       	$(document).on("click", "#btnSubmit", function(e) {
       		var _btn = this;
       		if($("#formComponent").valid()) {
       			swal({
                    text: "Do you want to " + (room.itemData.id > 0 ? "update" : "create") + " this room",
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
	                		if(room.itemData.id > 0) {
	                			room.doUpdate(_btn);
	                		} else {
	                			room.doCreate(_btn);
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
			room.itemData.id = $.trim($(tr).find(".col-id")[0].value);
			room.itemData.itemTypeId = $.trim($(tr).find(".col-itemTypeId")[0].value);
			room.itemData.itemTypeName = $.trim($(tr).find(".col-itemTypeName")[0].value);
			room.itemData.itemCategoryId = $.trim($(tr).find(".col-itemCategoryId")[0].value);
			room.itemData.itemCategoryName = $.trim($(tr).find(".col-itemCategoryName")[0].value);
			room.itemData.itemNo = $.trim($(tr).find(".col-itemNo")[0].value);
			room.itemData.rent = $.trim($(tr).find(".col-rent")[0].value);
			room.itemData.size = $.trim($(tr).find(".col-size")[0].value);
			room.itemData.floorNo = $.trim($(tr).find(".col-floorNo")[0].value);
			room.itemData.landLine = $.trim($(tr).find(".col-landLine")[0].value);
			room.itemData.active = $.trim($(tr).find(".col-active")[0].value);
			
			var formTemplete = $("#formTemplete").clone();
			var formHtml = formTemplete.html()
				.replace("#[id]", room.itemData.id)
				.replace("#[itemNo]", room.itemData.itemNo)
				.replace("#[rent]", room.itemData.rent)
				.replace("#[size]", room.itemData.size)
				.replace("#[floorNo]", room.itemData.floorNo)
				.replace("#[landLine]", room.itemData.landLine)
				.replace("#[active]", room.itemData.active);
	   		$(tr).html("<td colspan='9'>" + formHtml + "</td>");

	   		if (eval(room.itemData.active)) {
	   			$('#val-active').attr("checked", "checked");
	   		} else {
	   			$('#val-active').removeAttr("checked");
	   		}
	   		$("#btnSubmit").html('Update');
	   		$("#btnCreateNew").attr('disabled', true);
	   		$('#val-itemTypeId').html('<option value="0">Please select</option>');
	   		$('#val-itemCategoryId').html('<option value="0">Please select</option>');
	   		
	   		var providerId = $("#var-selected-providerId").val();
	   		room.getRoomTypeList(providerId, room.itemData.itemTypeId);
	   		room.getRoomCategoryList(room.itemData.itemTypeId, room.itemData.itemCategoryId);
	   		room.initValidation();
       	});
		
		$(document).on("click", ".delete-button", function(e) {
			var _btn = this;
			$(_btn).tooltip('hide');
			var tr = $(_btn).closest("tr");
			var id = $.trim($(tr).find(".col-id")[0].value);
			if(id > 0) {
				swal({
	                text: "Do you want to delete this room",
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
	            		room.doDelete(_btn, id);
	            	}
	            });
			} else {
				$(tr).remove();
			}
       	});
		
		$(document).on("change", "#val-active", function() {
			var checked = $(this).is(':checked');
			$("#val-active").val(checked);
		});
		
		$(document).on("change", "#val-itemTypeId", function(e) {
			var itemTypeId = $("#val-itemTypeId").val();
			if(itemTypeId != "" && itemTypeId != "0") {
				room.getRoomCategoryList(itemTypeId);
			} else {
				$("#val-itemCategoryId").html('<option value="0">Please select</option>');
				$('#val-rent').val("0.00");
        		$('#val-size').val("0");
			}
       	});
		
		$(document).on("change", "#val-itemCategoryId", function(e) {
			var itemCategoryId = $("#val-itemCategoryId").val();
			if(itemCategoryId != "" && itemCategoryId != "0") {
				room.getRoomCategory(itemCategoryId);
			} else {
				$('#val-rent').val("0.00");
        		$('#val-size').val("0");
			}
       	});
	},
	
	onHotelSelect : function(o) {
		room.hotelInfo = o;
		$('#var-selected-providerId').val(o.providerId);
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

		room.getRoomList(o.providerId);
	},
	
	getRoomTypeList : function(providerId, itemTypeId) {
		var url =  room.getRoomTypeListUrl.replace("{#providerId}", providerId);
		room.getRoomTypeListAjax = $.ajax({
			type: "GET",
            contentType: "application/json",
            url: url,
            dataType: 'json',
            timeout: 600000,
            success: function (data) {
            	if(data.status) {
            		room.getRoomTypeListAjax = undefined;
            		var html = '<option value="0">Please select</option>';
            		$.each(data.datas, function(index, data) {
            			html += '<option value="' + data.id + '">' + data.name + '</option>';
            		});
            		$('#val-itemTypeId').html(html);
            		if (itemTypeId) {
            			$('#val-itemTypeId').val(itemTypeId);
            		}
            	} else {
            		console.log(data.errors);
            		room.getRoomTypeListAjax = undefined;
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
            	room.getRoomTypeListAjax = undefined;
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
	
	getRoomCategoryList : function(itemTypeId, itemCategoryId) {
		var url =  room.getRoomCategoryListUrl.replace("{#itemTypeId}", itemTypeId);
		room.getRoomCategoryListAjax = $.ajax({
			type: "GET",
            contentType: "application/json",
            url: url,
            dataType: 'json',
            timeout: 600000,
            success: function (data) {
            	if(data.status) {
            		room.getRoomCategoryListAjax = undefined;
            		var html = '<option value="0">Please select</option>';
            		$.each(data.datas, function(index, data) {
            			html += '<option value="' + data.id + '">' + data.name + '</option>';
            		});
            		$('#val-itemCategoryId').html(html);
            		if (itemCategoryId) {
            			$('#val-itemCategoryId').val(itemCategoryId);
            		}
            	} else {
            		console.log(data.errors);
            		room.getRoomCategoryListAjax = undefined;
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
            	room.getRoomCategoryListAjax = undefined;
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
	
	getRoomCategory : function(itemCategoryId) {
		var url =  room.getRoomCategoryUrl.replace("{#itemCategoryId}", itemCategoryId);
		room.getRoomCategoryAjax = $.ajax({
			type: "GET",
            contentType: "application/json",
            url: url,
            dataType: 'json',
            timeout: 600000,
            success: function (data) {
            	if(data.status) {
            		room.getRoomCategoryAjax = undefined;
            		$('#val-rent').val(data.datas[0].rent);
            		$('#val-size').val(data.datas[0].roomCategoryData.size);
            	} else {
            		console.log(data.errors);
            		room.getRoomCategoryAjax = undefined;
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
            	room.getRoomCategoryAjax = undefined;
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
	
	getRoomList : function(providerId) {
		var url =  room.getRoomListUrl.replace("{#providerId}", providerId);
		room.getRoomListUrlAjax = $.ajax({
			type: "GET",
            contentType: "application/json",
            url: url,
            dataType: 'json',
            timeout: 600000,
            success: function (r) {
            	if(r.status) {
            		room.getRoomListUrlAjax = undefined;
    				if (r.datas.length > 0) {
    					html = '';
            			$.each(r.datas, function (index, data) {
            				var rowTemplete = $("#rowTemplete").clone();
                   			var rowHtml = rowTemplete.html()
	                   			.replace("#[id]", data.id)
			                	.replace("#[itemTypeId]", data.itemTypeId)
			                	.replace("#[itemTypeName]", data.itemTypeName)
			                	.replace("#[itemTypeName]", data.itemTypeName)
			                	.replace("#[itemCategoryId]", data.itemCategoryId)
			                	.replace("#[itemCategoryName]", data.itemCategoryName)
			                	.replace("#[itemCategoryName]", data.itemCategoryName)
			                	.replace("#[itemNo]", data.itemNo)
			                	.replace("#[itemNo]", data.itemNo)
			                	.replace("#[rent]", data.rent)
			                	.replace("#[rent]", data.rent)
			                	.replace("#[size]", data.roomData.size)
			                	.replace("#[size]", data.roomData.size)
			                	.replace("#[floorNo]", data.roomData.floorNo)
			                	.replace("#[floorNo]", data.roomData.floorNo)
			                	.replace("#[landLine]", data.roomData.landLine)
			                	.replace("#[landLine]", data.roomData.landLine)
			                	.replace("#[active]", data.active)
			                	.replace("#[active]", data.active);
                   			html += "<tr>" + rowHtml + "</tr>";
            			});
            			$('#dataTable').find('tbody').html(html);
    				} else {
    					$('#dataTable').find('tbody').html('');
    				}
            	} else {
            		console.log(r.errors);
            		room.getRoomListUrlAjax = undefined;
            		$('#dataTable').find('tbody').html('');
            	}
            },
            error: function (e) {
            	room.getRoomListUrlAjax = undefined;
            	$('#dataTable').find('tbody').html('');
            }
		});
	}, 
	
	doCreate : function(_btn) {
		$(_btn).attr("disabled", true);
		var form = $("#formComponent");
		var serializeForm = form.serializeObject();
		serializeForm['roomData'] = {
	    	"size": serializeForm['roomData.size'], 
	    	"floorNo": serializeForm['roomData.floorNo'], 
	    	"landLine": serializeForm['roomData.landLine'] 
	    };
		delete serializeForm['roomData.size'];
		delete serializeForm['roomData.floorNo'];
		delete serializeForm['roomData.landLine'];
		
		$.ajax({
			type: "POST",
            contentType: "application/json",
            url: room.createRoomUrl,
            data: JSON.stringify(serializeForm),
            dataType: 'json',
            timeout: 600000,
            success: function (data) {
            	if(data.status) {
            		$("#btnCreateNew").removeAttr('disabled');
            		var rowTemplete = $("#rowTemplete").clone();
           			var rowHtml = rowTemplete.html()
           				.replace("#[id]", data.datas[0].id)
	                	.replace("#[itemTypeId]", data.datas[0].itemTypeId)
	                	.replace("#[itemTypeName]", data.datas[0].itemTypeName)
	                	.replace("#[itemTypeName]", data.datas[0].itemTypeName)
	                	.replace("#[itemCategoryId]", data.datas[0].itemCategoryId)
	                	.replace("#[itemCategoryName]", data.datas[0].itemCategoryName)
	                	.replace("#[itemCategoryName]", data.datas[0].itemCategoryName)
	                	.replace("#[itemNo]", data.datas[0].itemNo)
	                	.replace("#[itemNo]", data.datas[0].itemNo)
	                	.replace("#[rent]", data.datas[0].rent)
	                	.replace("#[rent]", data.datas[0].rent)
	                	.replace("#[size]", data.datas[0].roomData.size)
	                	.replace("#[size]", data.datas[0].roomData.size)
	                	.replace("#[floorNo]", data.datas[0].roomData.floorNo)
	                	.replace("#[floorNo]", data.datas[0].roomData.floorNo)
	                	.replace("#[landLine]", data.datas[0].roomData.landLine)
	                	.replace("#[landLine]", data.datas[0].roomData.landLine)
	                	.replace("#[active]", data.datas[0].active)
	                	.replace("#[active]", data.datas[0].active);
           			$('#dataTable > tbody tr').first().html(rowHtml);
            		
            		Dashmix.helpers('notify', {
                		align: 'center', 
                		type: 'success', 
                		icon: 'fa fa-check mr-1', 
                		message: 'Room created successfully!',
                		delay: 1e3
        			});
            	} else {
            		$("#btnCreateNew").removeAttr('disabled');
            		$(_btn).removeAttr("disabled");
            		Dashmix.helpers('notify', {
                		align: 'center',
                		type: 'danger', 
                		icon: 'fa fa-times mr-1', 
                		message: data.errors && data.errors.length > 0 ? data.errors[0] : 'Failed to create room, please try again!',
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
		serializeForm['roomData'] = {
			"size": serializeForm['roomData.size'], 
	    	"floorNo": serializeForm['roomData.floorNo'], 
	    	"landLine": serializeForm['roomData.landLine'] 
	    };
		delete serializeForm['roomData.size'];
		delete serializeForm['roomData.floorNo'];
		delete serializeForm['roomData.landLine'];

		$.ajax({
			type: "PUT",
            contentType: "application/json",
            url: room.updateRoomUrl,
            data: JSON.stringify(serializeForm),
            dataType: 'json',
            timeout: 600000,
            success: function (data) {
            	if(data.status) {
            		$("#btnCreateNew").removeAttr('disabled');
            		var rowTemplete = $("#rowTemplete").clone();
           			var rowHtml = rowTemplete.html()
           				.replace("#[id]", data.datas[0].id)
	                	.replace("#[itemTypeId]", data.datas[0].itemTypeId)
	                	.replace("#[itemTypeName]", data.datas[0].itemTypeName)
	                	.replace("#[itemTypeName]", data.datas[0].itemTypeName)
	                	.replace("#[itemCategoryId]", data.datas[0].itemCategoryId)
	                	.replace("#[itemCategoryName]", data.datas[0].itemCategoryName)
	                	.replace("#[itemCategoryName]", data.datas[0].itemCategoryName)
	                	.replace("#[itemNo]", data.datas[0].itemNo)
	                	.replace("#[itemNo]", data.datas[0].itemNo)
	                	.replace("#[rent]", data.datas[0].rent)
	                	.replace("#[rent]", data.datas[0].rent)
	                	.replace("#[size]", data.datas[0].roomData.size)
	                	.replace("#[size]", data.datas[0].roomData.size)
	                	.replace("#[floorNo]", data.datas[0].roomData.floorNo)
	                	.replace("#[floorNo]", data.datas[0].roomData.floorNo)
	                	.replace("#[landLine]", data.datas[0].roomData.landLine)
	                	.replace("#[landLine]", data.datas[0].roomData.landLine)
	                	.replace("#[active]", data.datas[0].active)
	                	.replace("#[active]", data.datas[0].active);
           			$(_btn).closest("tr").html(rowHtml);
            		
           			room.itemData.id = "0";
                   	room.itemData.itemTypeId = "0";
                   	room.itemData.itemTypeName = "";
                   	room.itemData.itemCategoryId = "0";
                   	room.itemData.itemCategoryName = "";
                   	room.itemData.itemNo = "";
                   	room.itemData.rent = "0.00";
                   	room.itemData.size = "0";
                   	room.itemData.floorNo = "0";
                   	room.itemData.landLine = "";
                   	room.itemData.active = "true";
           			
            		Dashmix.helpers('notify', {
                		align: 'center', 
                		type: 'success', 
                		icon: 'fa fa-check mr-1', 
                		message: 'Room updated successfully!',
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
                		message: data.errors && data.errors.length > 0 ? data.errors[0] : 'Failed to update room, please try again!',
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
            url: room.deleteRoomUrl + id,
            dataType: 'json',
            timeout: 600000,
            success: function (data) {
            	if(data.status) {
            		$(_btn).closest("tr").remove();
            		Dashmix.helpers('notify', {
                		align: 'center', 
                		type: 'success', 
                		icon: 'fa fa-check mr-1', 
                		message: 'Room deleted successfully!',
                		delay: 1e3
        			});
            	} else {
            		console.log(data.errors);
            		$(_btn).removeAttr("disabled");
            		Dashmix.helpers('notify', {
                		align: 'center',
                		type: 'danger', 
                		icon: 'fa fa-times mr-1', 
                		message: data.errors && data.errors.length > 0 ? data.errors[0] : 'Failed to delete room, please try again!',
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
            	"itemTypeId": {
                    min: 1
                },
                "itemCategoryId": {
                    min: 1
                },
            	"itemNo": {
                    required: true,
                    remote : {
                    	url : room.isRoomNoAvailableUrl,
                    	type : "GET",
                    	data : {
                    		roomId : function() {
                    			return $('#val-id').val();
                    		},
                    		roomNo : function() {
                    			return $('#val-itemNo').val();
                    		},
                    		providerId : function() {
                    			return $("#var-selected-providerId").val();
                    		}
                    	}
                    }
                },
                "rent": {
                	required: true, decimal: true
                },
                "roomData.size": {
                	required: true, digits: true
                },
                "roomData.floorNo": {
                	required: true, digits: true
                }
            }, 
            messages : {
            	"itemTypeId": {
                	min: "Please select room type"
                },
                "itemCategoryId": {
                	min: "Please select room category"
                },
            	"itemNo": {
                    required: "Please enter room no",
                    remote: "This room no is already used"
                },
                "rent": {
                	required: "Please enter rent"
                },
                "roomData.size": {
                	required: "Please enter room size"
                },
                "roomData.floorNo": {
                	required: "Please enter floor no"
                }
            }
        });
	}
};

$(document).ready(function() {
	room.init();
});