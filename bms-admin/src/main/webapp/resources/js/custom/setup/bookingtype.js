var bookingTypeSetup = {
	bookingTypeData : {
		id : 0,
   		name : "",
   		remarks : ""
	},
	fetchUrl : "",
	createUrl : "",
	updateUrl : "",
	deleteUrl : "",
	
	init : function() {
		$(document).on("click", "#btnCreateNew", function(e) {
			var _btn = this;
       		$("#btnCreateNew").attr('disabled', true);
       		
       		var formTemplete = $("#formTemplete").clone()
    		var formHtml = formTemplete.html()
    		.replace("#[id]", "")
    		.replace("#[name]", "")
    		.replace("#[remarks]", "");
       		$("#dataTable > tbody").prepend("<tr><td colspan='4'>" + formHtml + "</td></tr>");
       		$("#btnSubmit").html('Save');
       		$("#val-id").removeAttr('disabled');
       		
       		bookingTypeSetup.initValidation();
       	});
       	
       	$(document).on("click", "#btnCancel", function(e) {
       		var _btn = this;
       		if(bookingTypeSetup.bookingTypeData.id > 0) {
       			var rowTemplete = $("#rowTemplete").clone();
       			var rowHtml = rowTemplete.html()
       				.replace("#[id]", bookingTypeSetup.bookingTypeData.id)
    				.replace("#[id]", bookingTypeSetup.bookingTypeData.id)
    				.replace("#[name]", bookingTypeSetup.bookingTypeData.name)
    				.replace("#[name]", bookingTypeSetup.bookingTypeData.name)
    				.replace("#[remarks]", bookingTypeSetup.bookingTypeData.remarks)
    				.replace("#[remarks]", bookingTypeSetup.bookingTypeData.remarks);
               	$(_btn).closest("tr").html(rowHtml);
               	
    			bookingTypeSetup.bookingTypeData.id = 0;
    			bookingTypeSetup.bookingTypeData.name = "";
    			bookingTypeSetup.bookingTypeData.remarks = "";
       		} else {
       			$("#btnCreateNew").removeAttr('disabled');
       			$('#dataTable > tbody tr').first().remove();
       		}
       	});
       	
       	$(document).on("click", "#btnSubmit", function(e) {
       		var _btn = this;
       		var id = $("#val-id").val();
       		
       		if(bookingTypeSetup.bookingTypeData.id > 0 && id != bookingTypeSetup.bookingTypeData.id) {
    			return false;
    		}
       		
       		if($("#formComponent").valid()) {
       			swal({
                    text: "Do you want to " + (bookingTypeSetup.bookingTypeData.id > 0 ? "update" : "create") + " this booking type",
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
                		if(bookingTypeSetup.bookingTypeData.id > 0) {
                				bookingTypeSetup.doUpdate(_btn, bookingTypeSetup.bookingTypeData.id);
                		} else {
                			bookingTypeSetup.doCreate(_btn);
                		}
                	}
                });
            }
       	});
       	
		$(document).on("click", ".edit-button", function(e) {
			var _btn = this;
			$(_btn).tooltip('hide');
			var tr = $(_btn).closest("tr");
			bookingTypeSetup.bookingTypeData.id = $.trim($(tr).find(".col-id")[0].value);
			bookingTypeSetup.bookingTypeData.name = $.trim($(tr).find(".col-name")[0].value);
			bookingTypeSetup.bookingTypeData.remarks = $.trim($(tr).find(".col-remarks")[0].value);
			
			var formTemplete = $("#formTemplete").clone();
			var formHtml = formTemplete.html()
			.replace("#[id]", bookingTypeSetup.bookingTypeData.id)
			.replace("#[name]", bookingTypeSetup.bookingTypeData.name)
			.replace("#[remarks]", bookingTypeSetup.bookingTypeData.remarks);
	   		$(tr).html("<td colspan='4'>" + formHtml + "</td>");
	   		$("#btnSubmit").html('Update');
	   		$("#val-id").attr('disabled', true);
	   		
	   		bookingTypeSetup.initValidation();
       	});
		
		$(document).on("click", ".delete-button", function(e) {
			var _btn = this;
			$(_btn).tooltip('hide');
			var tr = $(_btn).closest("tr");
			var id = $.trim($(tr).find(".col-id")[0].value);
			if(id > 0) {
				swal({
	                text: "Do you want to delete this booking type",
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
	            		bookingTypeSetup.doDelete(_btn, id);
	            	}
	            });
			} else {
				$(tr).remove();
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
            url: bookingTypeSetup.createUrl,
            data: JSON.stringify(serializeForm),
            dataType: 'json',
            timeout: 600000,
            success: function (data) {
            	if(data.status) {
            		$("#btnCreateNew").removeAttr('disabled');
            		var rowTemplete = $("#rowTemplete").clone();
           			var rowHtml = rowTemplete.html()
           				.replace("#[id]", data.datas[0].id)
        				.replace("#[id]", data.datas[0].id)
        				.replace("#[name]", data.datas[0].name)
        				.replace("#[name]", data.datas[0].name)
        				.replace("#[remarks]", data.datas[0].remarks)
        				.replace("#[remarks]", data.datas[0].remarks);
           			$('#dataTable > tbody tr').first().html(rowHtml);
            		
            		Dashmix.helpers('notify', {
                		align: 'center', 
                		type: 'success', 
                		icon: 'fa fa-check mr-1', 
                		message: 'Booking type created successfully!'
        			});
            	} else {
            		console.log(data.errors);
            		$(_btn).removeAttr("disabled");
            		Dashmix.helpers('notify', {
                		align: 'center',
                		type: 'danger', 
                		icon: 'fa fa-times mr-1', 
                		message: 'Failed to create booking type, please try again!'
        			});
            	}
            },
            error: function (e) {
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
	
	doUpdate : function(_btn, id) {
		$(_btn).attr("disabled", true);
		var form = $("#formComponent");
		var serializeForm = form.serializeObject();
		serializeForm["id"] = id;
		
		$.ajax({
			type: "PUT",
            contentType: "application/json",
            url: bookingTypeSetup.updateUrl,
            data: JSON.stringify(serializeForm),
            dataType: 'json',
            timeout: 600000,
            success: function (data) {
            	if(data.status) {
            		var rowTemplete = $("#rowTemplete").clone();
           			var rowHtml = rowTemplete.html()
        				.replace("#[id]", data.datas[0].id)
        				.replace("#[id]", data.datas[0].id)
        				.replace("#[name]", data.datas[0].name)
        				.replace("#[name]", data.datas[0].name)
        				.replace("#[remarks]", data.datas[0].remarks)
        				.replace("#[remarks]", data.datas[0].remarks);
           			$(_btn).closest("tr").html(rowHtml);
            		
            		Dashmix.helpers('notify', {
                		align: 'center', 
                		type: 'success', 
                		icon: 'fa fa-check mr-1', 
                		message: 'Booking type updated successfully!'
        			});
            	} else {
            		console.log(data.errors);
            		$(_btn).removeAttr("disabled");
            		Dashmix.helpers('notify', {
                		align: 'center',
                		type: 'danger', 
                		icon: 'fa fa-times mr-1', 
                		message: 'Failed to update booking type, please try again!'
        			});
            	}
            },
            error: function (e) {
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
            url: bookingTypeSetup.deleteUrl + id,
            dataType: 'json',
            timeout: 600000,
            success: function (data) {
            	if(data.status) {
            		$(_btn).closest("tr").remove();
            		Dashmix.helpers('notify', {
                		align: 'center', 
                		type: 'success', 
                		icon: 'fa fa-check mr-1', 
                		message: 'Booking type deleted successfully!'
        			});
            	} else {
            		console.log(data.errors);
            		$(_btn).removeAttr("disabled");
            		Dashmix.helpers('notify', {
                		align: 'center',
                		type: 'danger', 
                		icon: 'fa fa-times mr-1', 
                		message: 'Failed to delete booking type, please try again!'
        			});
            	}
            },
            error: function (e) {
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
            	"id": {
                    required: true, digits: true, min: 1
                },
                "name": {
                    required: true, minlength: 3
                }
            }, 
            messages : {
            	"id": {
                    required: "Please enter ID.", digits: "Please enter only digits."
                }, 
            	"name": {
                    required: "Please enter name.", minlength: "Name must consist of at least 3 characters."
                }
            }
        });
	}
}

$(document).ready(function() {
	bookingTypeSetup.init();
});


