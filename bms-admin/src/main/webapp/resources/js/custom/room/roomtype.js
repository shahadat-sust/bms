var roomtype = {
	hotelInfo: undefined,
	roomTypeData : {
		id : "0",
   		name : "",
   		providerId : "0",
   		active : "true"
	},
	getRoomTypeListUrl: undefined,
	getRoomTypeListAjax: undefined,
	createRoomTypeUrl: undefined,
	updateRoomTypeUrl: undefined,
	deleteRoomTypeUrl: undefined,
		
	init : function() {
		 $(".rating").raty({
    		starType: "i",
    		hints: ["One Star", "Two Stars", "Three Stars", "Four Stars", "Five Stars"],
            starOff: $(this).data("star-off") || "fa fa-fw fa-star text-muted",
            starOn: $(this).data("star-on") || "fa fa-fw fa-star text-warning",
            readOnly: true
        });
		
		 hotelsearchmodal.init(roomtype.onHotelSelect);
		 
		 $(document).on("click", "#btnCreateNew", function(e) {
			var _btn = this;
       		$("#btnCreateNew").attr('disabled', true);
       		var selectedProviderId = $("#var-selected-providerId").val();
       		
       		var formTemplete = $("#formTemplete").clone();
    		var formHtml = formTemplete.html()
    		.replace("#[id]", "0")
    		.replace("#[name]", "")
    		.replace("#[providerId]", selectedProviderId)
    		.replace("#[active]", "true");
       		$("#dataTable > tbody").prepend("<tr><td colspan='3'>" + formHtml + "</td></tr>");
       		$("#btnSubmit").html('Save');
       		
       		roomtype.initValidation();
       	});
       	
       	$(document).on("click", "#btnCancel", function(e) {
       		var _btn = this;
       		if(roomtype.roomTypeData.id > 0) {
       			var rowTemplete = $("#rowTemplete").clone();
       			var rowHtml = rowTemplete.html()
       				.replace("#[id]", roomtype.roomTypeData.id)
                	.replace("#[name]", roomtype.roomTypeData.name)
                	.replace("#[name]", roomtype.roomTypeData.name)
                	.replace("#[providerId]", roomtype.roomTypeData.providerId)
                	.replace("#[active]", roomtype.roomTypeData.active)
                	.replace("#[active]", roomtype.roomTypeData.active);
               	$(_btn).closest("tr").html(rowHtml);
               	
               	roomtype.roomTypeData.id = "0";
               	roomtype.roomTypeData.name = "";
               	roomtype.roomTypeData.providerId = "0";
               	roomtype.roomTypeData.active = "false";
       		} else {
       			$('#dataTable > tbody tr').first().remove();
       		}
       		$("#btnCreateNew").removeAttr('disabled');
       	});
       	
       	$(document).on("click", "#btnSubmit", function(e) {
       		var _btn = this;
       		if($("#formComponent").valid()) {
       			swal({
                    text: "Do you want to " + (roomtype.roomTypeData.id > 0 ? "update" : "create") + " this room type",
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
                		if(roomtype.roomTypeData.id > 0) {
                			roomtype.doUpdate(_btn);
                		} else {
                			roomtype.doCreate(_btn);
                		}
                	}
                });
            }
       	});
       	
		$(document).on("click", ".edit-button", function(e) {
			var _btn = this;
			$(_btn).tooltip('hide');
			var tr = $(_btn).closest("tr");
			roomtype.roomTypeData.id = $.trim($(tr).find(".col-id")[0].value);
			roomtype.roomTypeData.name = $.trim($(tr).find(".col-name")[0].value);
			roomtype.roomTypeData.active = $.trim($(tr).find(".col-active")[0].value);
			roomtype.roomTypeData.providerId = $.trim($(tr).find(".col-providerId")[0].value);
			
			var formTemplete = $("#formTemplete").clone();
			var formHtml = formTemplete.html()
			.replace("#[id]", roomtype.roomTypeData.id)
			.replace("#[name]", roomtype.roomTypeData.name)
			.replace("#[active]", roomtype.roomTypeData.active)
			.replace("#[providerId]", roomtype.roomTypeData.providerId);
	   		$(tr).html("<td colspan='3'>" + formHtml + "</td>");
	   		if (eval(roomtype.roomTypeData.active)) {
	   			$('#val-active').attr("checked", "checked");
	   		} else {
	   			$('#val-active').removeAttr("checked");
	   		}
	   		$("#btnSubmit").html('Update');
	   		$("#btnCreateNew").attr('disabled', true);

	   		roomtype.initValidation();
       	});
		
		$(document).on("click", ".delete-button", function(e) {
			var _btn = this;
			$(_btn).tooltip('hide');
			var tr = $(_btn).closest("tr");
			var id = $.trim($(tr).find(".col-id")[0].value);
			if(id > 0) {
				swal({
	                text: "Do you want to delete this room type",
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
	            		roomtype.doDelete(_btn, id);
	            	}
	            });
			} else {
				$(tr).remove();
			}
       	});
		
		$(document).on("change", "#val-active", function() {
			var checked = $(this).is(':checked');
			$("#val-active").val(checked);
		})
	},
	
	onHotelSelect : function(o) {
		roomtype.hotelInfo = o;
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
		
		roomtype.getRoomTypeList(o.providerId);
	},
	
	getRoomTypeList : function(providerId) {
		var url =  roomtype.getRoomTypeListUrl.replace("{#providerId}", providerId);
		roomtype.getRoomTypeListAjax = $.ajax({
			type: "GET",
            contentType: "application/json",
            url: url,
            dataType: 'json',
            timeout: 600000,
            success: function (r) {
            	if(r.status) {
            		roomtype.getRoomTypeListAjax = undefined;
    				if (r.datas.length > 0) {
    					html = '';
            			$.each(r.datas, function (index, data) {
            				var rowTemplete = $("#rowTemplete").clone();
                   			var rowHtml = rowTemplete.html()
                				.replace("#[id]", data.id)
                				.replace("#[name]", data.name)
                				.replace("#[name]", data.name)
                				.replace("#[providerId]", data.providerId)
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
            		roomtype.getRoomTypeListAjax = undefined;
            		$('#dataTable').find('tbody').html('');
            	}
            },
            error: function (e) {
            	roomtype.getRoomTypeListAjax = undefined;
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
            url: roomtype.createRoomTypeUrl,
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
        				.replace("#[providerId]", data.datas[0].providerId)
        				.replace("#[active]", data.datas[0].active)
                		.replace("#[active]", data.datas[0].active);
           			$('#dataTable > tbody tr').first().html(rowHtml);
            		
            		Dashmix.helpers('notify', {
                		align: 'center', 
                		type: 'success', 
                		icon: 'fa fa-check mr-1', 
                		message: 'Room type created successfully!',
                		delay: 1e3
        			});
            	} else {
            		$(_btn).removeAttr("disabled");
            		Dashmix.helpers('notify', {
                		align: 'center',
                		type: 'danger', 
                		icon: 'fa fa-times mr-1', 
                		message: data.errors && data.errors.length > 0 ? data.errors[0] : 'Failed to create room type, please try again!',
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
	
	doUpdate : function(_btn) {
		$(_btn).attr("disabled", true);
		var form = $("#formComponent");
		var serializeForm = form.serializeObject();
		
		$.ajax({
			type: "PUT",
            contentType: "application/json",
            url: roomtype.updateRoomTypeUrl,
            data: JSON.stringify(serializeForm),
            dataType: 'json',
            timeout: 600000,
            success: function (data) {
            	if(data.status) {
            		var rowTemplete = $("#rowTemplete").clone();
           			var rowHtml = rowTemplete.html()
        				.replace("#[id]", data.datas[0].id)
        				.replace("#[name]", data.datas[0].name)
        				.replace("#[name]", data.datas[0].name)
        				.replace("#[providerId]", data.datas[0].providerId)
        				.replace("#[active]", data.datas[0].active)
                		.replace("#[active]", data.datas[0].active);
           			$(_btn).closest("tr").html(rowHtml);
            		
           			roomtype.roomTypeData.id = "0";
                   	roomtype.roomTypeData.name = "";
                   	roomtype.roomTypeData.providerId = "0";
                   	roomtype.roomTypeData.active = "true";
           			
            		Dashmix.helpers('notify', {
                		align: 'center', 
                		type: 'success', 
                		icon: 'fa fa-check mr-1', 
                		message: 'Room type updated successfully!',
                		delay: 1e3
        			});
            	} else {
            		console.log(data.errors);
            		$(_btn).removeAttr("disabled");
            		Dashmix.helpers('notify', {
                		align: 'center',
                		type: 'danger', 
                		icon: 'fa fa-times mr-1', 
                		message: data.errors && data.errors.length > 0 ? data.errors[0] : 'Failed to update room type, please try again!',
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
	
	doDelete : function(_btn, id) {
		$(_btn).attr("disabled", true);

		$.ajax({
			type: "DELETE",
            contentType: "application/json",
            url: roomtype.deleteRoomTypeUrl + id,
            dataType: 'json',
            timeout: 600000,
            success: function (data) {
            	if(data.status) {
            		$(_btn).closest("tr").remove();
            		Dashmix.helpers('notify', {
                		align: 'center', 
                		type: 'success', 
                		icon: 'fa fa-check mr-1', 
                		message: 'Room type deleted successfully!',
                		delay: 1e3
        			});
            	} else {
            		console.log(data.errors);
            		$(_btn).removeAttr("disabled");
            		Dashmix.helpers('notify', {
                		align: 'center',
                		type: 'danger', 
                		icon: 'fa fa-times mr-1', 
                		message: data.errors && data.errors.length > 0 ? data.errors[0] : 'Failed to delete room type, please try again!',
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
            	"name": {
                    required: true, minlength: 3
                },
                "providerId": {
                    min: 1
                }
            }, 
            messages : {
            	"name": {
                    required: "Please enter name", minlength: "Name must consist of at least 3 characters"
                },
                "providerId": {
                	min: "Please select hotel from search wizard"
                }
            }
        });
	}
};

$(document).ready(function() {
	roomtype.init();
});