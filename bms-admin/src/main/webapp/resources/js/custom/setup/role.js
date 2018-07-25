var roleSetup = {
	roleData : {
		id : 0,
   		name : "",
   		priority : 0,
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
    		.replace("#[priority]", "")
    		.replace("#[remarks]", "");
       		$("#dataTable > tbody").prepend("<tr><td colspan='5'>" + formHtml + "</td></tr>");
       		$("#btnSubmit").html('Save');
       		$("#val-id").removeAttr('disabled');
       		
       		roleSetup.initValidation();
       	});
       	
       	$(document).on("click", "#btnCancel", function(e) {
       		var _btn = this;
       		if(roleSetup.roleData.id > 0) {
       			var rowTemplete = $("#rowTemplete").clone();
       			var rowHtml = rowTemplete.html()
       				.replace("#[id]", roleSetup.roleData.id)
    				.replace("#[id]", roleSetup.roleData.id)
    				.replace("#[name]", roleSetup.roleData.name)
    				.replace("#[name]", roleSetup.roleData.name)
    				.replace("#[priority]", roleSetup.roleData.priority)
    				.replace("#[priority]", roleSetup.roleData.priority)
    				.replace("#[remarks]", roleSetup.roleData.remarks)
    				.replace("#[remarks]", roleSetup.roleData.remarks);
               	$(_btn).closest("tr").html(rowHtml);
               	
    			roleSetup.roleData.id = 0;
    			roleSetup.roleData.name = "";
    			roleSetup.roleData.priority = "";
    			roleSetup.roleData.remarks = "";
       		} else {
       			$("#btnCreateNew").removeAttr('disabled');
       			$('#dataTable > tbody tr').first().remove();
       		}
       	});
       	
       	$(document).on("click", "#btnSubmit", function(e) {
       		var _btn = this;
       		var id = $("#val-id").val();
       		
       		if(roleSetup.roleData.id > 0 && id != roleSetup.roleData.id) {
    			return false;
    		}
       		
       		if($("#formComponent").valid()) {
       			swal({
                    text: "Do you want to " + (roleSetup.roleData.id > 0 ? "update" : "create") + " this role",
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
                		if(roleSetup.roleData.id > 0) {
                				roleSetup.doUpdate(_btn, roleSetup.roleData.id);
                		} else {
                			roleSetup.doCreate(_btn);
                		}
                	}
                });
            }
       	});
       	
		$(document).on("click", ".edit-button", function(e) {
			var _btn = this;
			$(_btn).tooltip('hide');
			var tr = $(_btn).closest("tr");
			roleSetup.roleData.id = $.trim($(tr).find(".col-id")[0].value);
			roleSetup.roleData.name = $.trim($(tr).find(".col-name")[0].value);
			roleSetup.roleData.priority = $.trim($(tr).find(".col-priority")[0].value);
			roleSetup.roleData.remarks = $.trim($(tr).find(".col-remarks")[0].value);
			
			var formTemplete = $("#formTemplete").clone();
			var formHtml = formTemplete.html()
			.replace("#[id]", roleSetup.roleData.id)
			.replace("#[name]", roleSetup.roleData.name)
			.replace("#[priority]", roleSetup.roleData.priority)
			.replace("#[remarks]", roleSetup.roleData.remarks);
	   		$(tr).html("<td colspan='5'>" + formHtml + "</td>");
	   		$("#btnSubmit").html('Update');
	   		$("#val-id").attr('disabled', true);
	   		
	   		roleSetup.initValidation();
       	});
		
		$(document).on("click", ".delete-button", function(e) {
			var _btn = this;
			$(_btn).tooltip('hide');
			var tr = $(_btn).closest("tr");
			var id = $.trim($(tr).find(".col-id")[0].value);
			if(id > 0) {
				swal({
	                text: "Do you want to delete this role",
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
	            		roleSetup.doDelete(_btn, id);
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
            url: roleSetup.createUrl,
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
        				.replace("#[priority]", data.datas[0].priority)
        				.replace("#[priority]", data.datas[0].priority)
        				.replace("#[remarks]", data.datas[0].remarks)
        				.replace("#[remarks]", data.datas[0].remarks);
           			$('#dataTable > tbody tr').first().html(rowHtml);
            		
            		Dashmix.helpers('notify', {
                		align: 'center', 
                		type: 'success', 
                		icon: 'fa fa-check mr-1', 
                		message: 'Group created successfully!'
        			});
            	} else {
            		$(_btn).removeAttr("disabled");
            		Dashmix.helpers('notify', {
                		align: 'center',
                		type: 'danger', 
                		icon: 'fa fa-times mr-1', 
                		message: 'Failed to create role, please try again!'
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
            url: roleSetup.updateUrl,
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
        				.replace("#[priority]", data.datas[0].priority)
        				.replace("#[priority]", data.datas[0].priority)
        				.replace("#[remarks]", data.datas[0].remarks)
        				.replace("#[remarks]", data.datas[0].remarks);
           			$(_btn).closest("tr").html(rowHtml);
            		
            		Dashmix.helpers('notify', {
                		align: 'center', 
                		type: 'success', 
                		icon: 'fa fa-check mr-1', 
                		message: 'Group updated successfully!'
        			});
            	} else {
            		$(_btn).removeAttr("disabled");
            		Dashmix.helpers('notify', {
                		align: 'center',
                		type: 'danger', 
                		icon: 'fa fa-times mr-1', 
                		message: 'Failed to update role, please try again!'
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
            url: roleSetup.deleteUrl + id,
            dataType: 'json',
            timeout: 600000,
            success: function (data) {
            	if(data.status) {
            		$(_btn).closest("tr").remove();
            		Dashmix.helpers('notify', {
                		align: 'center', 
                		type: 'success', 
                		icon: 'fa fa-check mr-1', 
                		message: 'Group deleted successfully!'
        			});
            	} else {
            		$(_btn).removeAttr("disabled");
            		Dashmix.helpers('notify', {
                		align: 'center',
                		type: 'danger', 
                		icon: 'fa fa-times mr-1', 
                		message: 'Failed to delete role, please try again!'
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
                    required: true, digits: true
                },
                "name": {
                    required: true, minlength: 3
                },
                "priority": {
                    required: true, digits: true, min: 1
                }
            }, 
            messages : {
            	"id": {
                    required: "Please enter ID.", digits: "Please enter only digits."
                }, 
            	"name": {
                    required: "Please enter name.", minlength: "Name must consist of at least 3 characters."
                }, 
            	"priority": {
                    required: "Please enter priority.", digits: "Please enter only digits."
                }
            }
        });
	}
}

$(document).ready(function() {
	roleSetup.init();
});


