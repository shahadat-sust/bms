var amenitySetup = {
	amenityData : {
		id : 0,
   		name : "",
   		remarks : "",
   		providerTypeId : 0,
   		providerTypeName : ""
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
    		.replace("#[id]", "0")
    		.replace("#[name]", "")
    		.replace("#[remarks]", "");
       		$("#dataTable > tbody").prepend("<tr><td colspan='4'>" + formHtml + "</td></tr>");
       		$("#btnSubmit").html('Save');
       		
       		amenitySetup.initValidation();
       	});
       	
       	$(document).on("click", "#btnCancel", function(e) {
       		var _btn = this;
       		if(amenitySetup.amenityData.id > 0) {
       			var rowTemplete = $("#rowTemplete").clone();
       			var rowHtml = rowTemplete.html()
    				.replace("#[id]", amenitySetup.amenityData.id)
					.replace("#[providerTypeId]", amenitySetup.amenityData.providerTypeId)
					.replace("#[name]", amenitySetup.amenityData.name)
					.replace("#[name]", amenitySetup.amenityData.name)
					.replace("#[remarks]", amenitySetup.amenityData.remarks)
					.replace("#[remarks]", amenitySetup.amenityData.remarks)
	       			.replace("#[providerTypeName]", amenitySetup.amenityData.providerTypeName)
					.replace("#[providerTypeName]", amenitySetup.amenityData.providerTypeName);
               	$(_btn).closest("tr").html(rowHtml);
               	
    			amenitySetup.amenityData.id = 0;
    			amenitySetup.amenityData.name = "";
    			amenitySetup.amenityData.remarks = "";
    			amenitySetup.amenityData.providerTypeId = 0;
    			amenitySetup.amenityData.providerTypeName = "";
       		} else {
       			$('#dataTable > tbody tr').first().remove();
       		}
       		$("#btnCreateNew").removeAttr('disabled');
       	});
       	
       	$(document).on("click", "#btnSubmit", function(e) {
       		var _btn = this;
       		if($("#formComponent").valid()) {
       			swal({
                    text: "Do you want to " + (amenitySetup.amenityData.id > 0 ? "update" : "create") + " this amenity",
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
                		if(amenitySetup.amenityData.id > 0) {
                			amenitySetup.doUpdate(_btn);
                		} else {
                			amenitySetup.doCreate(_btn);
                		}
                	}
                });
            }
       	});
       	
		$(document).on("click", ".edit-button", function(e) {
			var _btn = this;
			$(_btn).tooltip('hide');
			var tr = $(_btn).closest("tr");
			amenitySetup.amenityData.id = $.trim($(tr).find(".col-id")[0].value);
			amenitySetup.amenityData.name = $.trim($(tr).find(".col-name")[0].value);
			amenitySetup.amenityData.remarks = $.trim($(tr).find(".col-remarks")[0].value);
			amenitySetup.amenityData.providerTypeId = $.trim($(tr).find(".col-providerTypeId")[0].value);
			amenitySetup.amenityData.providerTypeName = $.trim($(tr).find(".col-providerTypeName")[0].value);
			
			var formTemplete = $("#formTemplete").clone();
			var formHtml = formTemplete.html()
			.replace("#[id]", amenitySetup.amenityData.id)
			.replace("#[name]", amenitySetup.amenityData.name)
			.replace("#[remarks]", amenitySetup.amenityData.remarks);
	   		$(tr).html("<td colspan='4'>" + formHtml + "</td>");
	   		$('#val-providerTypeId').val(amenitySetup.amenityData.providerTypeId);
	   		$("#btnSubmit").html('Update');
	   		$("#btnCreateNew").attr('disabled', true);
	   		
	   		amenitySetup.initValidation();
       	});
		
		$(document).on("click", ".delete-button", function(e) {
			var _btn = this;
			$(_btn).tooltip('hide');
			var tr = $(_btn).closest("tr");
			var id = $.trim($(tr).find(".col-id")[0].value);
			if(id > 0) {
				swal({
	                text: "Do you want to delete this amenity",
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
	            		amenitySetup.doDelete(_btn, id);
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
            url: amenitySetup.createUrl,
            data: JSON.stringify(serializeForm),
            dataType: 'json',
            timeout: 600000,
            success: function (data) {
            	if(data.status) {
            		$("#btnCreateNew").removeAttr('disabled');
            		var rowTemplete = $("#rowTemplete").clone();
           			var rowHtml = rowTemplete.html()
        				.replace("#[id]", data.datas[0].id)
        				.replace("#[providerTypeId]", data.datas[0].providerTypeId)
        				.replace("#[name]", data.datas[0].name)
        				.replace("#[name]", data.datas[0].name)
        				.replace("#[remarks]", data.datas[0].remarks)
        				.replace("#[remarks]", data.datas[0].remarks)
	           			.replace("#[providerTypeName]", data.datas[0].providerTypeName)
	    				.replace("#[providerTypeName]", data.datas[0].providerTypeName);
           			$('#dataTable > tbody tr').first().html(rowHtml);
            		
            		Dashmix.helpers('notify', {
                		align: 'center', 
                		type: 'success', 
                		icon: 'fa fa-check mr-1', 
                		message: 'Amenity created successfully!'
        			});
            	} else {
            		console.log(data.errors);
            		$(_btn).removeAttr("disabled");
            		Dashmix.helpers('notify', {
                		align: 'center',
                		type: 'danger', 
                		icon: 'fa fa-times mr-1', 
                		message: data.errors && data.errors.length > 0 ? data.errors[0] : 'Failed to create amenity, please try again!'
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
	
	doUpdate : function(_btn) {
		$(_btn).attr("disabled", true);
		var form = $("#formComponent");
		var serializeForm = form.serializeObject();
		
		$.ajax({
			type: "PUT",
            contentType: "application/json",
            url: amenitySetup.updateUrl,
            data: JSON.stringify(serializeForm),
            dataType: 'json',
            timeout: 600000,
            success: function (data) {
            	if(data.status) {
            		var rowTemplete = $("#rowTemplete").clone();
           			var rowHtml = rowTemplete.html()
        				.replace("#[id]", data.datas[0].id)
        				.replace("#[providerTypeId]", data.datas[0].providerTypeId)
        				.replace("#[name]", data.datas[0].name)
        				.replace("#[name]", data.datas[0].name)
        				.replace("#[remarks]", data.datas[0].remarks)
        				.replace("#[remarks]", data.datas[0].remarks)
	           			.replace("#[providerTypeName]", data.datas[0].providerTypeName)
	    				.replace("#[providerTypeName]", data.datas[0].providerTypeName);
           			$(_btn).closest("tr").html(rowHtml);
            		
           			amenitySetup.amenityData.id = 0;
        			amenitySetup.amenityData.name = "";
        			amenitySetup.amenityData.remarks = "";
        			amenitySetup.amenityData.providerTypeId = 0;
        			amenitySetup.amenityData.providerTypeName = "";
           			
            		Dashmix.helpers('notify', {
                		align: 'center', 
                		type: 'success', 
                		icon: 'fa fa-check mr-1', 
                		message: 'Amenity updated successfully!'
        			});
            	} else {
            		console.log(data.errors);
            		$(_btn).removeAttr("disabled");
            		Dashmix.helpers('notify', {
                		align: 'center',
                		type: 'danger', 
                		icon: 'fa fa-times mr-1', 
                		message: data.errors && data.errors.length > 0 ? data.errors[0] : 'Failed to update amenity, please try again!'
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
            url: amenitySetup.deleteUrl + id,
            dataType: 'json',
            timeout: 600000,
            success: function (data) {
            	if(data.status) {
            		$(_btn).closest("tr").remove();
            		Dashmix.helpers('notify', {
                		align: 'center', 
                		type: 'success', 
                		icon: 'fa fa-check mr-1', 
                		message: 'Amenity deleted successfully!'
        			});
            	} else {
            		console.log(data.errors);
            		$(_btn).removeAttr("disabled");
            		Dashmix.helpers('notify', {
                		align: 'center',
                		type: 'danger', 
                		icon: 'fa fa-times mr-1', 
                		message: data.errors && data.errors.length > 0 ? data.errors[0] : 'Failed to delete amenity, please try again!'
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
            	"name": {
                    required: true, minlength: 3
                }, 
                "providerTypeId": {
	                required: true
	            }
            }, 
            messages : {
            	"name": {
                    required: "Please enter name", minlength: "Name must consist of at least 3 characters"
                },
                "providerTypeId": "Please enter provider type"
            }
        });
	}
}

$(document).ready(function() {
	amenitySetup.init();
});


