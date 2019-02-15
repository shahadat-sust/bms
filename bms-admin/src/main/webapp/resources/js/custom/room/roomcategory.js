var roomcategory = {
	hotelInfo: undefined,
	itemCategoryData : {
		id : "0",
   		name : "",
   		rent : "0.00",
   		capacity : "1",
   		size : "0",
   		itemTypeId : "0",
   		active : "true"
	},
	getRoomTypeListUrl: undefined,
	getRoomTypeListAjax: undefined,
	getRoomCategoryListUrl: undefined,
	getRoomCategoryListAjax: undefined,
	createRoomCategoryUrl: undefined,
	updateRoomCategoryUrl: undefined,
	deleteRoomCategoryUrl: undefined,
		
	init : function() {
		 $(".rating").raty({
    		starType: "i",
    		hints: ["One Star", "Two Stars", "Three Stars", "Four Stars", "Five Stars"],
            starOff: $(this).data("star-off") || "fa fa-fw fa-star text-muted",
            starOn: $(this).data("star-on") || "fa fa-fw fa-star text-warning",
            readOnly: true
        });
		
		 hotelsearchmodal.init(roomcategory.onHotelSelect);
		 
		 $(document).on("click", "#btnCreateNew", function(e) {
			var _btn = this;
       		$("#btnCreateNew").attr('disabled', true);
       		var itemTypeId = $("#val-selected-itemTypeId").val();
       		
       		var formTemplete = $("#formTemplete").clone();
    		var formHtml = formTemplete.html()
	    		.replace("#[id]", "0")
	    		.replace("#[itemTypeId]", itemTypeId)
	    		.replace("#[name]", "")
	    		.replace("#[rent]", "0.00")
	    		.replace("#[capacity]", "1")
	    		.replace("#[size]", "0")
	    		.replace("#[active]", "true");
       		$("#dataTable > tbody").prepend("<tr><td colspan='6'>" + formHtml + "</td></tr>");
       		$("#btnSubmit").html('Save');
       		
       		roomcategory.initValidation();
       	});
       	
       	$(document).on("click", "#btnCancel", function(e) {
       		var _btn = this;
       		if(roomcategory.itemCategoryData.id > 0) {
       			var rowTemplete = $("#rowTemplete").clone();
       			var rowHtml = rowTemplete.html()
       				.replace("#[id]", roomcategory.itemCategoryData.id)
                	.replace("#[name]", roomcategory.itemCategoryData.name)
                	.replace("#[name]", roomcategory.itemCategoryData.name)
                	.replace("#[rent]", roomcategory.itemCategoryData.rent)
                	.replace("#[rent]", roomcategory.itemCategoryData.rent)
                	.replace("#[capacity]", roomcategory.itemCategoryData.capacity)
                	.replace("#[capacity]", roomcategory.itemCategoryData.capacity)
                	.replace("#[size]", roomcategory.itemCategoryData.size)
                	.replace("#[size]", roomcategory.itemCategoryData.size)
                	.replace("#[itemTypeId]", roomcategory.itemCategoryData.itemTypeId)
                	.replace("#[active]", roomcategory.itemCategoryData.active)
                	.replace("#[active]", roomcategory.itemCategoryData.active);
               	$(_btn).closest("tr").html(rowHtml);
               	
               	roomcategory.itemCategoryData.id = "0";
               	roomcategory.itemCategoryData.name = "";
               	roomcategory.itemCategoryData.rent = "0.00";
               	roomcategory.itemCategoryData.capacity = "1";
               	roomcategory.itemCategoryData.size = "0";
               	roomcategory.itemCategoryData.itemTypeId = "0";
               	roomcategory.itemCategoryData.active = "true";
       		} else {
       			$('#dataTable > tbody tr').first().remove();
       		}
       		$("#btnCreateNew").removeAttr('disabled');
       	});
       	
       	$(document).on("click", "#btnSubmit", function(e) {
       		var _btn = this;
       		if($("#formComponent").valid()) {
       			swal({
                    text: "Do you want to " + (roomcategory.itemCategoryData.id > 0 ? "update" : "create") + " this room category",
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
	                		if(roomcategory.itemCategoryData.id > 0) {
	                			roomcategory.doUpdate(_btn);
	                		} else {
	                			roomcategory.doCreate(_btn);
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
			roomcategory.itemCategoryData.id = $.trim($(tr).find(".col-id")[0].value);
			roomcategory.itemCategoryData.name = $.trim($(tr).find(".col-name")[0].value);
			roomcategory.itemCategoryData.rent = $.trim($(tr).find(".col-rent")[0].value);
			roomcategory.itemCategoryData.size = $.trim($(tr).find(".col-size")[0].value);
			roomcategory.itemCategoryData.capacity = $.trim($(tr).find(".col-capacity")[0].value);
			roomcategory.itemCategoryData.active = $.trim($(tr).find(".col-active")[0].value);
			roomcategory.itemCategoryData.itemTypeId = $.trim($(tr).find(".col-itemTypeId")[0].value);
			
			var formTemplete = $("#formTemplete").clone();
			var formHtml = formTemplete.html()
				.replace("#[id]", roomcategory.itemCategoryData.id)
				.replace("#[itemTypeId]", roomcategory.itemCategoryData.itemTypeId)
				.replace("#[name]", roomcategory.itemCategoryData.name)
				.replace("#[rent]", roomcategory.itemCategoryData.rent)
				.replace("#[capacity]", roomcategory.itemCategoryData.capacity)
				.replace("#[size]", roomcategory.itemCategoryData.size)
				.replace("#[active]", roomcategory.itemCategoryData.active);
	   		$(tr).html("<td colspan='6'>" + formHtml + "</td>");

	   		if (eval(roomcategory.itemCategoryData.active)) {
	   			$('#val-active').attr("checked", "checked");
	   		} else {
	   			$('#val-active').removeAttr("checked");
	   		}
	   		$("#btnSubmit").html('Update');
	   		$("#btnCreateNew").attr('disabled', true);

	   		roomcategory.initValidation();
       	});
		
		$(document).on("click", ".delete-button", function(e) {
			var _btn = this;
			$(_btn).tooltip('hide');
			var tr = $(_btn).closest("tr");
			var id = $.trim($(tr).find(".col-id")[0].value);
			if(id > 0) {
				swal({
	                text: "Do you want to delete this room category",
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
	            		roomcategory.doDelete(_btn, id);
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
		
		$(document).on("change", "#val-selected-itemTypeId", function(e) {
			var itemTypeId = $("#val-selected-itemTypeId").val();
			if(itemTypeId != "" && itemTypeId != "0") {
				$("#btnCreateNew").removeAttr('disabled');
				roomcategory.getRoomCategoryList(itemTypeId);
			} else {
				$("#btnCreateNew").attr('disabled', true);
				$('#dataTable').find('tbody').html('');
			}
       	});
	},
	
	onHotelSelect : function(o) {
		roomcategory.hotelInfo = o;
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
		$('#dataTable').find('tbody').html('');
		
		roomcategory.getRoomTypeList(o.providerId);
	},
	
	getRoomTypeList : function(providerId) {
		var url =  roomcategory.getRoomTypeListUrl.replace("{#providerId}", providerId);
		roomcategory.getRoomTypeListAjax = $.ajax({
			type: "GET",
            contentType: "application/json",
            url: url,
            dataType: 'json',
            timeout: 600000,
            success: function (data) {
            	if(data.status) {
            		roomcategory.getRoomTypeListAjax = undefined;
            		var html = '<option value="0">Please select</option>';
            		$.each(data.datas, function(index, data) {
            			html += '<option value="' + data.id + '">' + data.name + '</option>';
            		});
            		$('#val-selected-itemTypeId').html(html);
            	} else {
            		console.log(data.errors);
            		roomcategory.getRoomTypeListAjax = undefined;
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
            	roomcategory.getRoomTypeListAjax = undefined;
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
		var url =  roomcategory.getRoomCategoryListUrl.replace("{#itemTypeId}", itemTypeId);
		roomcategory.getRoomCategoryListAjax = $.ajax({
			type: "GET",
            contentType: "application/json",
            url: url,
            dataType: 'json',
            timeout: 600000,
            success: function (r) {
            	if(r.status) {
            		roomcategory.getRoomCategoryListAjax = undefined;
    				if (r.datas.length > 0) {
    					html = '';
            			$.each(r.datas, function (index, data) {
            				var rowTemplete = $("#rowTemplete").clone();
                   			var rowHtml = rowTemplete.html()
	                   			.replace("#[id]", data.id)
			                	.replace("#[name]", data.name)
			                	.replace("#[name]", data.name)
			                	.replace("#[rent]", data.rent)
			                	.replace("#[rent]", data.rent)
			                	.replace("#[capacity]", data.capacity)
			                	.replace("#[capacity]", data.capacity)
			                	.replace("#[size]", data.roomCategoryData.size)
			                	.replace("#[size]", data.roomCategoryData.size)
			                	.replace("#[itemTypeId]", data.itemTypeId)
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
            		roomcategory.getRoomCategoryListAjax = undefined;
            		$('#dataTable').find('tbody').html('');
            	}
            },
            error: function (e) {
            	roomcategory.getRoomCategoryListAjax = undefined;
            	$('#dataTable').find('tbody').html('');
            }
		});
	}, 
	
	doCreate : function(_btn) {
		$(_btn).attr("disabled", true);
		var form = $("#formComponent");
		var serializeForm = form.serializeObject();
		serializeForm['roomCategoryData'] = {
		    	"size": serializeForm['roomCategoryData.size'] 
	    };
		delete serializeForm['roomCategoryData.size'];
		
		$.ajax({
			type: "POST",
            contentType: "application/json",
            url: roomcategory.createRoomCategoryUrl,
            data: JSON.stringify(serializeForm),
            dataType: 'json',
            timeout: 600000,
            success: function (data) {
            	if(data.status) {
            		$("#btnCreateNew").removeAttr('disabled');
            		var rowTemplete = $("#rowTemplete").clone();
           			var rowHtml = rowTemplete.html()
           				.replace("#[id]", data.datas[0].id)
	                	.replace("#[name]", data.datas[0].name)
	                	.replace("#[name]", data.datas[0].name)
	                	.replace("#[rent]", data.datas[0].rent)
	                	.replace("#[rent]", data.datas[0].rent)
	                	.replace("#[capacity]", data.datas[0].capacity)
	                	.replace("#[capacity]", data.datas[0].capacity)
	                	.replace("#[size]", data.datas[0].roomCategoryData.size)
	                	.replace("#[size]", data.datas[0].roomCategoryData.size)
	                	.replace("#[itemTypeId]", data.datas[0].itemTypeId)
	                	.replace("#[active]", data.datas[0].active)
	                	.replace("#[active]", data.datas[0].active);
           			$('#dataTable > tbody tr').first().html(rowHtml);
            		
            		Dashmix.helpers('notify', {
                		align: 'center', 
                		type: 'success', 
                		icon: 'fa fa-check mr-1', 
                		message: 'Room category created successfully!',
                		delay: 1e3
        			});
            	} else {
            		$("#btnCreateNew").removeAttr('disabled');
            		$(_btn).removeAttr("disabled");
            		Dashmix.helpers('notify', {
                		align: 'center',
                		type: 'danger', 
                		icon: 'fa fa-times mr-1', 
                		message: data.errors && data.errors.length > 0 ? data.errors[0] : 'Failed to create room category, please try again!',
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
		serializeForm['roomCategoryData'] = {
		    	"size": serializeForm['roomCategoryData.size'] 
	    };
		delete serializeForm['roomCategoryData.size'];

		$.ajax({
			type: "PUT",
            contentType: "application/json",
            url: roomcategory.updateRoomCategoryUrl,
            data: JSON.stringify(serializeForm),
            dataType: 'json',
            timeout: 600000,
            success: function (data) {
            	if(data.status) {
            		$("#btnCreateNew").removeAttr('disabled');
            		var rowTemplete = $("#rowTemplete").clone();
           			var rowHtml = rowTemplete.html()
        				.replace("#[id]", data.datas[0].id)
	                	.replace("#[name]", data.datas[0].name)
	                	.replace("#[name]", data.datas[0].name)
	                	.replace("#[rent]", data.datas[0].rent)
	                	.replace("#[rent]", data.datas[0].rent)
	                	.replace("#[capacity]", data.datas[0].capacity)
	                	.replace("#[capacity]", data.datas[0].capacity)
	                	.replace("#[size]", data.datas[0].roomCategoryData.size)
	                	.replace("#[size]", data.datas[0].roomCategoryData.size)
	                	.replace("#[itemTypeId]", data.datas[0].itemTypeId)
	                	.replace("#[active]", data.datas[0].active)
	                	.replace("#[active]", data.datas[0].active);
           			$(_btn).closest("tr").html(rowHtml);
            		
           			roomcategory.itemCategoryData.id = "0";
                   	roomcategory.itemCategoryData.name = "";
                   	roomcategory.itemCategoryData.rent = "0.00";
                   	roomcategory.itemCategoryData.capacity = "1";
                   	roomcategory.itemCategoryData.size = "0";
                   	roomcategory.itemCategoryData.itemTypeId = "0";
                   	roomcategory.itemCategoryData.active = "true";
           			
            		Dashmix.helpers('notify', {
                		align: 'center', 
                		type: 'success', 
                		icon: 'fa fa-check mr-1', 
                		message: 'Room category updated successfully!',
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
                		message: data.errors && data.errors.length > 0 ? data.errors[0] : 'Failed to update room category, please try again!',
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
            url: roomcategory.deleteRoomCategoryUrl + id,
            dataType: 'json',
            timeout: 600000,
            success: function (data) {
            	if(data.status) {
            		$(_btn).closest("tr").remove();
            		Dashmix.helpers('notify', {
                		align: 'center', 
                		type: 'success', 
                		icon: 'fa fa-check mr-1', 
                		message: 'Room category deleted successfully!',
                		delay: 1e3
        			});
            	} else {
            		console.log(data.errors);
            		$(_btn).removeAttr("disabled");
            		Dashmix.helpers('notify', {
                		align: 'center',
                		type: 'danger', 
                		icon: 'fa fa-times mr-1', 
                		message: data.errors && data.errors.length > 0 ? data.errors[0] : 'Failed to delete room category, please try again!',
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
            	"name": {
                    required: true, minlength: 3
                },
                "rent": {
                	required: true, decimal: true
                },
                "capacity": {
                	required: true, digits: true, min: 1
                },
                "roomCategoryData.size": {
                	required: true, digits: true
                }
            }, 
            messages : {
            	"name": {
                    required: "Please enter name", minlength: "Name must consist of at least 3 characters"
                },
                "rent": {
                	required: "Please enter rent"
                },
                "capacity": {
                	required: "Please enter capacity"
                },
                "roomCategoryData.size": {
                	required: "Please enter room size"
                }
            }
        });
	}
};

$(document).ready(function() {
	roomcategory.init();
});