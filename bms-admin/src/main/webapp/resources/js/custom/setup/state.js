var stateSetup = {
	stateData : {
		id : 0,
   		name : "",
   		remarks : "",
   		countryId : 0,
   		countryName : ""
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
       		
       		stateSetup.initValidation();
       	});
       	
       	$(document).on("click", "#btnCancel", function(e) {
       		var _btn = this;
       		if(stateSetup.stateData.id > 0) {
       			var rowTemplete = $("#rowTemplete").clone();
       			var rowHtml = rowTemplete.html()
    				.replace("#[id]", stateSetup.stateData.id)
					.replace("#[countryId]", stateSetup.stateData.countryId)
					.replace("#[name]", stateSetup.stateData.name)
					.replace("#[name]", stateSetup.stateData.name)
					.replace("#[remarks]", stateSetup.stateData.remarks)
					.replace("#[remarks]", stateSetup.stateData.remarks)
	       			.replace("#[countryName]", stateSetup.stateData.countryName)
					.replace("#[countryName]", stateSetup.stateData.countryName);
               	$(_btn).closest("tr").html(rowHtml);
               	
    			stateSetup.stateData.id = 0;
    			stateSetup.stateData.name = "";
    			stateSetup.stateData.remarks = "";
    			stateSetup.stateData.countryId = 0;
    			stateSetup.stateData.countryName = "";
       		} else {
       			$('#dataTable > tbody tr').first().remove();
       		}
       		$("#btnCreateNew").removeAttr('disabled');
       	});
       	
       	$(document).on("click", "#btnSubmit", function(e) {
       		var _btn = this;
       		if($("#formComponent").valid()) {
       			swal({
                    text: "Do you want to " + (stateSetup.stateData.id > 0 ? "update" : "create") + " this state",
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
                		if(stateSetup.stateData.id > 0) {
                			stateSetup.doUpdate(_btn);
                		} else {
                			stateSetup.doCreate(_btn);
                		}
                	}
                });
            }
       	});
       	
		$(document).on("click", ".edit-button", function(e) {
			var _btn = this;
			$(_btn).tooltip('hide');
			var tr = $(_btn).closest("tr");
			stateSetup.stateData.id = $.trim($(tr).find(".col-id")[0].value);
			stateSetup.stateData.name = $.trim($(tr).find(".col-name")[0].value);
			stateSetup.stateData.remarks = $.trim($(tr).find(".col-remarks")[0].value);
			stateSetup.stateData.countryId = $.trim($(tr).find(".col-countryId")[0].value);
			stateSetup.stateData.countryName = $.trim($(tr).find(".col-countryName")[0].value);
			
			var formTemplete = $("#formTemplete").clone();
			var formHtml = formTemplete.html()
			.replace("#[id]", stateSetup.stateData.id)
			.replace("#[name]", stateSetup.stateData.name)
			.replace("#[remarks]", stateSetup.stateData.remarks);
	   		$(tr).html("<td colspan='4'>" + formHtml + "</td>");
	   		$('#val-countryId').val(stateSetup.stateData.countryId);
	   		$("#btnSubmit").html('Update');
	   		$("#btnCreateNew").attr('disabled', true);
	   		
	   		stateSetup.initValidation();
       	});
		
		$(document).on("click", ".delete-button", function(e) {
			var _btn = this;
			$(_btn).tooltip('hide');
			var tr = $(_btn).closest("tr");
			var id = $.trim($(tr).find(".col-id")[0].value);
			if(id > 0) {
				swal({
	                text: "Do you want to delete this state",
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
	            		stateSetup.doDelete(_btn, id);
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
            url: stateSetup.createUrl,
            data: JSON.stringify(serializeForm),
            dataType: 'json',
            timeout: 600000,
            success: function (data) {
            	if(data.status) {
            		$("#btnCreateNew").removeAttr('disabled');
            		var rowTemplete = $("#rowTemplete").clone();
           			var rowHtml = rowTemplete.html()
        				.replace("#[id]", data.datas[0].id)
        				.replace("#[countryId]", data.datas[0].countryId)
        				.replace("#[name]", data.datas[0].name)
        				.replace("#[name]", data.datas[0].name)
        				.replace("#[remarks]", data.datas[0].remarks)
        				.replace("#[remarks]", data.datas[0].remarks)
	           			.replace("#[countryName]", data.datas[0].countryName)
	    				.replace("#[countryName]", data.datas[0].countryName);
           			$('#dataTable > tbody tr').first().html(rowHtml);
            		
            		Dashmix.helpers('notify', {
                		align: 'center', 
                		type: 'success', 
                		icon: 'fa fa-check mr-1', 
                		message: 'State created successfully!',
                		delay: 1e3
        			});
            	} else {
            		console.log(data.errors);
            		$(_btn).removeAttr("disabled");
            		Dashmix.helpers('notify', {
                		align: 'center',
                		type: 'danger', 
                		icon: 'fa fa-times mr-1', 
                		message: data.errors && data.errors.length > 0 ? data.errors[0] : 'Failed to create state, please try again!',
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
            url: stateSetup.updateUrl,
            data: JSON.stringify(serializeForm),
            dataType: 'json',
            timeout: 600000,
            success: function (data) {
            	if(data.status) {
            		$("#btnCreateNew").removeAttr('disabled');
            		var rowTemplete = $("#rowTemplete").clone();
           			var rowHtml = rowTemplete.html()
        				.replace("#[id]", data.datas[0].id)
        				.replace("#[countryId]", data.datas[0].countryId)
        				.replace("#[name]", data.datas[0].name)
        				.replace("#[name]", data.datas[0].name)
        				.replace("#[remarks]", data.datas[0].remarks)
        				.replace("#[remarks]", data.datas[0].remarks)
	           			.replace("#[countryName]", data.datas[0].countryName)
	    				.replace("#[countryName]", data.datas[0].countryName);
           			$(_btn).closest("tr").html(rowHtml);
            		
           			stateSetup.stateData.id = 0;
        			stateSetup.stateData.name = "";
        			stateSetup.stateData.remarks = "";
        			stateSetup.stateData.countryId = 0;
        			stateSetup.stateData.countryName = "";
           			
            		Dashmix.helpers('notify', {
                		align: 'center', 
                		type: 'success', 
                		icon: 'fa fa-check mr-1', 
                		message: 'State updated successfully!',
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
                		message: data.errors && data.errors.length > 0 ? data.errors[0] : 'Failed to update state, please try again!',
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
            url: stateSetup.deleteUrl + id,
            dataType: 'json',
            timeout: 600000,
            success: function (data) {
            	if(data.status) {
            		$(_btn).closest("tr").remove();
            		Dashmix.helpers('notify', {
                		align: 'center', 
                		type: 'success', 
                		icon: 'fa fa-check mr-1', 
                		message: 'State deleted successfully!',
                		delay: 1e3
        			});
            	} else {
            		console.log(data.errors);
            		$(_btn).removeAttr("disabled");
            		Dashmix.helpers('notify', {
                		align: 'center',
                		type: 'danger', 
                		icon: 'fa fa-times mr-1', 
                		message: data.errors && data.errors.length > 0 ? data.errors[0] : 'Failed to delete state, please try again!',
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
                "countryId": {
	                required: true
	            }
            }, 
            messages : {
            	"name": {
                    required: "Please enter name", minlength: "Name must consist of at least 3 characters"
                },
                "countryId": "Please enter country"
            }
        });
	}
}

$(document).ready(function() {
	stateSetup.init();
});


