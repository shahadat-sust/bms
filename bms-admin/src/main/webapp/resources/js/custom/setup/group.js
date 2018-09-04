var groupSetup = {
	groupData : {
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
       		
       		groupSetup.initValidation();
       	});
       	
       	$(document).on("click", "#btnCancel", function(e) {
       		var _btn = this;
       		if(groupSetup.groupData.id > 0) {
       			var rowTemplete = $("#rowTemplete").clone();
       			var rowHtml = rowTemplete.html()
       				.replace("#[id]", groupSetup.groupData.id)
    				.replace("#[id]", groupSetup.groupData.id)
    				.replace("#[name]", groupSetup.groupData.name)
    				.replace("#[name]", groupSetup.groupData.name)
    				.replace("#[remarks]", groupSetup.groupData.remarks)
    				.replace("#[remarks]", groupSetup.groupData.remarks);
               	$(_btn).closest("tr").html(rowHtml);
               	
    			groupSetup.groupData.id = 0;
    			groupSetup.groupData.name = "";
    			groupSetup.groupData.remarks = "";
       		} else {
       			$('#dataTable > tbody tr').first().remove();
       		}
       		$("#btnCreateNew").removeAttr('disabled');
       	});
       	
       	$(document).on("click", "#btnSubmit", function(e) {
       		var _btn = this;
       		var id = $("#val-id").val();
       		
       		if(groupSetup.groupData.id > 0 && id != groupSetup.groupData.id) {
    			return false;
    		}
       		
       		if($("#formComponent").valid()) {
       			swal({
                    text: "Do you want to " + (groupSetup.groupData.id > 0 ? "update" : "create") + " this group",
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
                		if(groupSetup.groupData.id > 0) {
                				groupSetup.doUpdate(_btn, groupSetup.groupData.id);
                		} else {
                			groupSetup.doCreate(_btn);
                		}
                	}
                });
            }
       	});
       	
		$(document).on("click", ".edit-button", function(e) {
			var _btn = this;
			$(_btn).tooltip('hide');
			var tr = $(_btn).closest("tr");
			groupSetup.groupData.id = $.trim($(tr).find(".col-id")[0].value);
			groupSetup.groupData.name = $.trim($(tr).find(".col-name")[0].value);
			groupSetup.groupData.remarks = $.trim($(tr).find(".col-remarks")[0].value);
			
			var formTemplete = $("#formTemplete").clone();
			var formHtml = formTemplete.html()
			.replace("#[id]", groupSetup.groupData.id)
			.replace("#[name]", groupSetup.groupData.name)
			.replace("#[remarks]", groupSetup.groupData.remarks);
	   		$(tr).html("<td colspan='4'>" + formHtml + "</td>");
	   		$("#btnSubmit").html('Update');
	   		$("#val-id").attr('disabled', true);
	   		$("#btnCreateNew").attr('disabled', true);
	   		
	   		groupSetup.initValidation();
       	});
		
		$(document).on("click", ".delete-button", function(e) {
			var _btn = this;
			$(_btn).tooltip('hide');
			var tr = $(_btn).closest("tr");
			var id = $.trim($(tr).find(".col-id")[0].value);
			if(id > 0) {
				swal({
	                text: "Do you want to delete this group",
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
	            		groupSetup.doDelete(_btn, id);
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
            url: groupSetup.createUrl,
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
                		message: 'Group created successfully!',
                		delay: 1e3
        			});
            	} else {
            		console.log(data.errors);
            		$(_btn).removeAttr("disabled");
            		Dashmix.helpers('notify', {
                		align: 'center',
                		type: 'danger', 
                		icon: 'fa fa-times mr-1', 
                		message: data.errors && data.errors.length > 0 ? data.errors[0] : 'Failed to create group, please try again!',
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
	
	doUpdate : function(_btn, id) {
		$(_btn).attr("disabled", true);
		var form = $("#formComponent");
		var serializeForm = form.serializeObject();
		serializeForm["id"] = id;
		
		$.ajax({
			type: "PUT",
            contentType: "application/json",
            url: groupSetup.updateUrl,
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
            		
           			groupSetup.groupData.id = 0;
        			groupSetup.groupData.name = "";
        			groupSetup.groupData.remarks = "";
           			
            		Dashmix.helpers('notify', {
                		align: 'center', 
                		type: 'success', 
                		icon: 'fa fa-check mr-1', 
                		message: 'Group updated successfully!',
                		delay: 1e3
        			});
            	} else {
            		console.log(data.errors);
            		$(_btn).removeAttr("disabled");
            		Dashmix.helpers('notify', {
                		align: 'center',
                		type: 'danger', 
                		icon: 'fa fa-times mr-1', 
                		message: data.errors && data.errors.length > 0 ? data.errors[0] : 'Failed to update group, please try again!',
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
            url: groupSetup.deleteUrl + id,
            dataType: 'json',
            timeout: 600000,
            success: function (data) {
            	if(data.status) {
            		$(_btn).closest("tr").remove();
            		Dashmix.helpers('notify', {
                		align: 'center', 
                		type: 'success', 
                		icon: 'fa fa-check mr-1', 
                		message: 'Group deleted successfully!',
                		delay: 1e3
        			});
            	} else {
            		console.log(data.errors);
            		$(_btn).removeAttr("disabled");
            		Dashmix.helpers('notify', {
                		align: 'center',
                		type: 'danger', 
                		icon: 'fa fa-times mr-1', 
                		message: data.errors && data.errors.length > 0 ? data.errors[0] : 'Failed to delete group, please try again!',
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
	groupSetup.init();
});


