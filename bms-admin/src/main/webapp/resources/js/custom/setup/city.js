var citySetup = {
	cityData : {
		id : 0,
   		name : "",
   		remarks : "",
   		stateId : 0,
   		stateName : "",
   		countryId : 0,
   		countryName : ""
	},
	fetchUrl : "",
	createUrl : "",
	updateUrl : "",
	deleteUrl : "",
	stateFetchAjax : undefined,
	
	init : function() {
		$(document).on("click", "#btnCreateNew", function(e) {
			var _btn = this;
       		$("#btnCreateNew").attr('disabled', true);
       		
       		var formTemplete = $("#formTemplete").clone()
    		var formHtml = formTemplete.html()
    		.replace("#[id]", "0")
    		.replace("#[name]", "")
    		.replace("#[remarks]", "");
       		$("#dataTable > tbody").prepend("<tr><td colspan='5'>" + formHtml + "</td></tr>");
       		$("#btnSubmit").html('Save');
       		
       		citySetup.initValidation();
       	});
       	
       	$(document).on("click", "#btnCancel", function(e) {
       		var _btn = this;
       		if(citySetup.cityData.id > 0) {
       			var rowTemplete = $("#rowTemplete").clone();
       			var rowHtml = rowTemplete.html()
    				.replace("#[id]", citySetup.cityData.id)
					.replace("#[stateId]", citySetup.cityData.stateId)
					.replace("#[countryId]", citySetup.cityData.countryId)
					.replace("#[name]", citySetup.cityData.name)
					.replace("#[name]", citySetup.cityData.name)
					.replace("#[remarks]", citySetup.cityData.remarks)
					.replace("#[remarks]", citySetup.cityData.remarks)
	       			.replace("#[stateName]", citySetup.cityData.stateName)
					.replace("#[stateName]", citySetup.cityData.stateName)
	       			.replace("#[countryName]", citySetup.cityData.countryName)
					.replace("#[countryName]", citySetup.cityData.countryName);
               	$(_btn).closest("tr").html(rowHtml);
               	
    			citySetup.cityData.id = 0;
    			citySetup.cityData.name = "";
    			citySetup.cityData.remarks = "";
    			citySetup.cityData.stateId = 0;
    			citySetup.cityData.stateName = "";
    			citySetup.cityData.countryId = 0;
    			citySetup.cityData.countryName = "";
       		} else {
       			$('#dataTable > tbody tr').first().remove();
       		}
       		$("#btnCreateNew").removeAttr('disabled');
       		
       		if(citySetup.stateFetchAjax) {
       			citySetup.stateFetchAjax.abort();
       			citySetup.stateFetchAjax = undefined;
       		}
       	});
       	
       	$(document).on("click", "#btnSubmit", function(e) {
       		var _btn = this;
       		if($("#formComponent").valid()) {
       			swal({
                    text: "Do you want to " + (citySetup.cityData.id > 0 ? "update" : "create") + " this city",
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
                		if(citySetup.cityData.id > 0) {
                			citySetup.doUpdate(_btn);
                		} else {
                			citySetup.doCreate(_btn);
                		}
                	}
                });
            }
       	});
       	
		$(document).on("click", ".edit-button", function(e) {
			var _btn = this;
			$(_btn).tooltip('hide');
			var tr = $(_btn).closest("tr");
			citySetup.cityData.id = $.trim($(tr).find(".col-id")[0].value);
			citySetup.cityData.name = $.trim($(tr).find(".col-name")[0].value);
			citySetup.cityData.remarks = $.trim($(tr).find(".col-remarks")[0].value);
			citySetup.cityData.stateId = $.trim($(tr).find(".col-stateId")[0].value);
			citySetup.cityData.stateName = $.trim($(tr).find(".col-stateName")[0].value);
			citySetup.cityData.countryId = $.trim($(tr).find(".col-countryId")[0].value);
			citySetup.cityData.countryName = $.trim($(tr).find(".col-countryName")[0].value);
			
			var formTemplete = $("#formTemplete").clone();
			var formHtml = formTemplete.html()
			.replace("#[id]", citySetup.cityData.id)
			.replace("#[name]", citySetup.cityData.name)
			.replace("#[remarks]", citySetup.cityData.remarks);
	   		$(tr).html("<td colspan='5'>" + formHtml + "</td>");
	   		$('#val-countryId').val(citySetup.cityData.countryId);
	   		$("#btnSubmit").html('Update');
	   		$("#btnCreateNew").attr('disabled', true);
	   		
	   		citySetup.getStateList(citySetup.cityData.countryId, citySetup.cityData.stateId);
	   		
	   		citySetup.initValidation();
       	});
		
		$(document).on("click", ".delete-button", function(e) {
			var _btn = this;
			$(_btn).tooltip('hide');
			var tr = $(_btn).closest("tr");
			var id = $.trim($(tr).find(".col-id")[0].value);
			if(id > 0) {
				swal({
	                text: "Do you want to delete this city",
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
	            		citySetup.doDelete(_btn, id);
	            	}
	            });
			} else {
				$(tr).remove();
			}
       	});
		
		$(document).on("change", "#val-countryId", function(e) {
			var countryId = $("#val-countryId").val();
			if(countryId != "") {
				citySetup.getStateList(countryId);
			} else {
				$("#val-stateId").html('<option value="">Please select</option>');
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
            url: citySetup.createUrl,
            data: JSON.stringify(serializeForm),
            dataType: 'json',
            timeout: 600000,
            success: function (data) {
            	if(data.status) {
            		$("#btnCreateNew").removeAttr('disabled');
            		var rowTemplete = $("#rowTemplete").clone();
           			var rowHtml = rowTemplete.html()
        				.replace("#[id]", data.datas[0].id)
        				.replace("#[stateId]", data.datas[0].stateId)
        				.replace("#[countryId]", data.datas[0].countryId)
        				.replace("#[name]", data.datas[0].name)
        				.replace("#[name]", data.datas[0].name)
        				.replace("#[remarks]", data.datas[0].remarks)
        				.replace("#[remarks]", data.datas[0].remarks)
	           			.replace("#[stateName]", data.datas[0].stateName)
	    				.replace("#[stateName]", data.datas[0].stateName)
	           			.replace("#[countryName]", data.datas[0].countryName)
	    				.replace("#[countryName]", data.datas[0].countryName);
           			$('#dataTable > tbody tr').first().html(rowHtml);
            		
            		Dashmix.helpers('notify', {
                		align: 'center', 
                		type: 'success', 
                		icon: 'fa fa-check mr-1', 
                		message: 'City created successfully!',
                		delay: 1e3
        			});
            	} else {
            		$(_btn).removeAttr("disabled");
            		Dashmix.helpers('notify', {
                		align: 'center',
                		type: 'danger', 
                		icon: 'fa fa-times mr-1', 
                		message: 'Failed to create city, please try again!',
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
            url: citySetup.updateUrl,
            data: JSON.stringify(serializeForm),
            dataType: 'json',
            timeout: 600000,
            success: function (data) {
            	if(data.status) {
            		var rowTemplete = $("#rowTemplete").clone();
           			var rowHtml = rowTemplete.html()
        				.replace("#[id]", data.datas[0].id)
        				.replace("#[stateId]", data.datas[0].stateId)
        				.replace("#[countryId]", data.datas[0].countryId)
        				.replace("#[name]", data.datas[0].name)
        				.replace("#[name]", data.datas[0].name)
        				.replace("#[remarks]", data.datas[0].remarks)
        				.replace("#[remarks]", data.datas[0].remarks)
	           			.replace("#[stateName]", data.datas[0].stateName)
	    				.replace("#[stateName]", data.datas[0].stateName)
	           			.replace("#[countryName]", data.datas[0].countryName)
	    				.replace("#[countryName]", data.datas[0].countryName);
           			$(_btn).closest("tr").html(rowHtml);
            		
           			citySetup.cityData.id = 0;
        			citySetup.cityData.name = "";
        			citySetup.cityData.remarks = "";
        			citySetup.cityData.stateId = 0;
        			citySetup.cityData.stateName = "";
        			citySetup.cityData.countryId = 0;
        			citySetup.cityData.countryName = "";
           			
            		Dashmix.helpers('notify', {
                		align: 'center', 
                		type: 'success', 
                		icon: 'fa fa-check mr-1', 
                		message: 'City updated successfully!',
                		delay: 1e3
        			});
            	} else {
            		console.log(data.errors);
            		$(_btn).removeAttr("disabled");
            		Dashmix.helpers('notify', {
                		align: 'center',
                		type: 'danger', 
                		icon: 'fa fa-times mr-1', 
                		message: 'Failed to update city, please try again!',
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
            url: citySetup.deleteUrl + id,
            dataType: 'json',
            timeout: 600000,
            success: function (data) {
            	if(data.status) {
            		$(_btn).closest("tr").remove();
            		Dashmix.helpers('notify', {
                		align: 'center', 
                		type: 'success', 
                		icon: 'fa fa-check mr-1', 
                		message: 'City deleted successfully!',
                		delay: 1e3
        			});
            	} else {
            		console.log(data.errors);
            		$(_btn).removeAttr("disabled");
            		Dashmix.helpers('notify', {
                		align: 'center',
                		type: 'danger', 
                		icon: 'fa fa-times mr-1', 
                		message: 'Failed to delete city, please try again!',
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
	
	getStateList : function(countryId, stateId) {
		citySetup.stateFetchAjax = $.ajax({
			type: "GET",
            contentType: "application/json",
            url: citySetup.stateFetchUrl + countryId,
            dataType: 'json',
            timeout: 600000,
            success: function (data) {
            	if(data.status) {
            		citySetup.stateFetchAjax = undefined;
            		var html = '<option value="">Please select</option>';
            		$.each(data.datas, function(index, data) {
            			html += '<option value="' + data.id + '">' + data.name + '</option>';
            		});
            		$('#val-stateId').html(html);
            		if(stateId) {
            			$('#val-stateId').val(stateId);
            		} 
            	} else {
            		console.log(data.errors);
            		citySetup.stateFetchAjax = undefined;
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
            	citySetup.stateFetchAjax = undefined;
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
                "stateId": {
	                required: true
	            }
            }, 
            messages : {
            	"name": {
                    required: "Please enter name", minlength: "Name must consist of at least 3 characters"
                },
                "stateId": "Please enter state"
            }
        });
	}
}

$(document).ready(function() {
	citySetup.init();
});


