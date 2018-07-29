var pointOfInterestSetup = {
	pointOfInterestData : {
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
       		
       		pointOfInterestSetup.initValidation();
       	});
       	
       	$(document).on("click", "#btnCancel", function(e) {
       		var _btn = this;
       		if(pointOfInterestSetup.pointOfInterestData.id > 0) {
       			var rowTemplete = $("#rowTemplete").clone();
       			var rowHtml = rowTemplete.html()
    				.replace("#[id]", pointOfInterestSetup.pointOfInterestData.id)
					.replace("#[providerTypeId]", pointOfInterestSetup.pointOfInterestData.providerTypeId)
					.replace("#[name]", pointOfInterestSetup.pointOfInterestData.name)
					.replace("#[name]", pointOfInterestSetup.pointOfInterestData.name)
					.replace("#[remarks]", pointOfInterestSetup.pointOfInterestData.remarks)
					.replace("#[remarks]", pointOfInterestSetup.pointOfInterestData.remarks)
	       			.replace("#[providerTypeName]", pointOfInterestSetup.pointOfInterestData.providerTypeName)
					.replace("#[providerTypeName]", pointOfInterestSetup.pointOfInterestData.providerTypeName);
               	$(_btn).closest("tr").html(rowHtml);
               	
    			pointOfInterestSetup.pointOfInterestData.id = 0;
    			pointOfInterestSetup.pointOfInterestData.name = "";
    			pointOfInterestSetup.pointOfInterestData.remarks = "";
    			pointOfInterestSetup.pointOfInterestData.providerTypeId = 0;
    			pointOfInterestSetup.pointOfInterestData.providerTypeName = "";
       		} else {
       			$('#dataTable > tbody tr').first().remove();
       		}
       		$("#btnCreateNew").removeAttr('disabled');
       	});
       	
       	$(document).on("click", "#btnSubmit", function(e) {
       		var _btn = this;
       		if($("#formComponent").valid()) {
       			swal({
                    text: "Do you want to " + (pointOfInterestSetup.pointOfInterestData.id > 0 ? "update" : "create") + " this point of interest",
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
                		if(pointOfInterestSetup.pointOfInterestData.id > 0) {
                			pointOfInterestSetup.doUpdate(_btn);
                		} else {
                			pointOfInterestSetup.doCreate(_btn);
                		}
                	}
                });
            }
       	});
       	
		$(document).on("click", ".edit-button", function(e) {
			var _btn = this;
			$(_btn).tooltip('hide');
			var tr = $(_btn).closest("tr");
			pointOfInterestSetup.pointOfInterestData.id = $.trim($(tr).find(".col-id")[0].value);
			pointOfInterestSetup.pointOfInterestData.name = $.trim($(tr).find(".col-name")[0].value);
			pointOfInterestSetup.pointOfInterestData.remarks = $.trim($(tr).find(".col-remarks")[0].value);
			pointOfInterestSetup.pointOfInterestData.providerTypeId = $.trim($(tr).find(".col-providerTypeId")[0].value);
			pointOfInterestSetup.pointOfInterestData.providerTypeName = $.trim($(tr).find(".col-providerTypeName")[0].value);
			
			var formTemplete = $("#formTemplete").clone();
			var formHtml = formTemplete.html()
			.replace("#[id]", pointOfInterestSetup.pointOfInterestData.id)
			.replace("#[name]", pointOfInterestSetup.pointOfInterestData.name)
			.replace("#[remarks]", pointOfInterestSetup.pointOfInterestData.remarks);
	   		$(tr).html("<td colspan='4'>" + formHtml + "</td>");
	   		$('#val-providerTypeId').val(pointOfInterestSetup.pointOfInterestData.providerTypeId);
	   		$("#btnSubmit").html('Update');
	   		$("#btnCreateNew").attr('disabled', true);
	   		
	   		pointOfInterestSetup.initValidation();
       	});
		
		$(document).on("click", ".delete-button", function(e) {
			var _btn = this;
			$(_btn).tooltip('hide');
			var tr = $(_btn).closest("tr");
			var id = $.trim($(tr).find(".col-id")[0].value);
			if(id > 0) {
				swal({
	                text: "Do you want to delete this point of interest",
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
	            		pointOfInterestSetup.doDelete(_btn, id);
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
            url: pointOfInterestSetup.createUrl,
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
                		message: 'Point of interest created successfully!',
                		delay: 1e3
        			});
            	} else {
            		console.log(data.errors);
            		$(_btn).removeAttr("disabled");
            		Dashmix.helpers('notify', {
                		align: 'center',
                		type: 'danger', 
                		icon: 'fa fa-times mr-1', 
                		message: 'Failed to create point of interest, please try again!',
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
            url: pointOfInterestSetup.updateUrl,
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
            		
           			pointOfInterestSetup.pointOfInterestData.id = 0;
        			pointOfInterestSetup.pointOfInterestData.name = "";
        			pointOfInterestSetup.pointOfInterestData.remarks = "";
        			pointOfInterestSetup.pointOfInterestData.providerTypeId = 0;
        			pointOfInterestSetup.pointOfInterestData.providerTypeName = "";
           			
            		Dashmix.helpers('notify', {
                		align: 'center', 
                		type: 'success', 
                		icon: 'fa fa-check mr-1', 
                		message: 'Point of interest updated successfully!',
                		delay: 1e3
        			});
            	} else {
            		console.log(data.errors);
            		$(_btn).removeAttr("disabled");
            		Dashmix.helpers('notify', {
                		align: 'center',
                		type: 'danger', 
                		icon: 'fa fa-times mr-1', 
                		message: 'Failed to update point of interest, please try again!',
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
            url: pointOfInterestSetup.deleteUrl + id,
            dataType: 'json',
            timeout: 600000,
            success: function (data) {
            	if(data.status) {
            		$(_btn).closest("tr").remove();
            		Dashmix.helpers('notify', {
                		align: 'center', 
                		type: 'success', 
                		icon: 'fa fa-check mr-1', 
                		message: 'Point of interest deleted successfully!',
                		delay: 1e3
        			});
            	} else {
            		console.log(data.errors);
            		$(_btn).removeAttr("disabled");
            		Dashmix.helpers('notify', {
                		align: 'center',
                		type: 'danger', 
                		icon: 'fa fa-times mr-1', 
                		message: 'Failed to delete point of interest, please try again!',
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
	pointOfInterestSetup.init();
});


