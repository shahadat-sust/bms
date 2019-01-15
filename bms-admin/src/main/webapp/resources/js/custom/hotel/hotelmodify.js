var hotelmodify = {
		isEditMode : false,
		isEmailAvailableUrlForHotel : "",
		isPhoneNumberAvailableUrlForHotel : "",
		fetchStateListUrl : "",
		fetchCityListUrl : "",
		fetchStateListAjax : undefined,
		fetchCityListAjax : undefined,
		countryCode : "",
		
		init : function () {
			$(this).scrollTop(0);
			hotelmodify.initValidation();
			
			$('.clockpicker').clockpicker({
				placement: 'top',
			    align: 'left',
			    donetext: 'Done',
			    init: function() { 
                    console.log("colorpicker initiated");
                },
                beforeShow: function() {
                    console.log("before show");
                },
                afterDone: function() {
                    console.log("after done");
                }
			});
			
			$(".rating").raty({
        		starType: "i",
        		hints: ["One Star", "Two Stars", "Three Stars", "Four Stars", "Five Stars"],
                starOff: $(this).data("star-off") || "fa fa-fw fa-star text-muted",
                starOn: $(this).data("star-on") || "fa fa-fw fa-star text-warning",
                click: function(t, e) {
                	$("#val-starRating").val(t);
                }
            });
			
			$(".country").on('click', function(){
				var selectedCode = $(this).data('dial-code');
				$('.type-text').text("+" + selectedCode);
				$('#val-code').val(selectedCode).valid();
			});
			
			$("#val-countryId").on("change", function(e) {
				var countryId = $("#val-countryId").val();
				if(countryId != "") {
					hotelmodify.getStateList(countryId);
				} else {
					$("#val-stateId").html('<option value="">Please select</option>');
				}
				$("#val-cityId").html('<option value="">Please select</option>');
	       	});
			
			$("#val-stateId").on("change", function(e) {
				var stateId = $("#val-stateId").val();
				if(stateId != "") {
					hotelmodify.getCityList(stateId);
				} else {
					$("#val-cityId").html('<option value="">Please select</option>');
				}
	       	});
			
			$("button[type='submit']").on("click", function() {
				if($("#formHotelModify").valid()) {
					$("#formHotelModify").submit();
				}
			});
			
			$("button[type='reset']").on('click', function() {
				if(hotelmodify.countryCode) {
					$('.type-text').text(hotelmodify.countryCode);
				} else {
					$('.type-text').text("Code");
				}
			});
		},
		
		getStateList : function(countryId) {
			var url =  hotelmodify.fetchStateListUrl.replace("{#countryId}", countryId);
			hotelmodify.fetchStateListAjax = $.ajax({
				type: "GET",
	            contentType: "application/json",
	            url: url,
	            dataType: 'json',
	            timeout: 600000,
	            success: function (data) {
	            	if(data.status) {
	            		hotelmodify.fetchStateListAjax = undefined;
	            		var html = '<option value="">Please select</option>';
	            		$.each(data.datas, function(index, data) {
	            			html += '<option value="' + data.id + '">' + data.name + '</option>';
	            		});
	            		$('#val-stateId').html(html);
	            	} else {
	            		console.log(data.errors);
	            		hotelmodify.fetchStateListAjax = undefined;
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
	            	hotelmodify.fetchStateListAjax = undefined;
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
		
		getCityList : function(stateId) {
			var url = hotelmodify.fetchCityListUrl.replace("{#stateId}", stateId);
			hotelmodify.fetchCityListAjax = $.ajax({
				type: "GET",
	            contentType: "application/json",
	            url: url,
	            dataType: 'json',
	            timeout: 600000,
	            success: function (data) {
	            	if(data.status) {
	            		hotelmodify.fetchCityListAjax = undefined;
	            		var html = '<option value="">Please select</option>';
	            		$.each(data.datas, function(index, data) {
	            			html += '<option value="' + data.id + '">' + data.name + '</option>';
	            		});
	            		$('#val-cityId').html(html);
	            	} else {
	            		console.log(data.errors);
	            		hotelmodify.fetchCityListAjax = undefined;
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
	            	hotelmodify.fetchCityListAjax = undefined;
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
			/**
			 * Custom validator for contains time in hh:mm format
			 */
			$.validator.addMethod("time", function(value, element) {
		        return value.trim() == "" || value.match(/^\d\d?\:\d\d$/);
		    }, "Please enter a time in the format hh:mm");
			
			$("#formHotelModify").validate({
				ignore : [],
				errorClass : "invalid-feedback animated fadeIn", 
				errorElement : "div",
				errorPlacement : function (e, r) {
					$(r).addClass("is-invalid"), $(r).parents(".form-group").append(e)
				},
				highlight : function (e) {
					$(e).parents(".form-group").find(".is-invalid").removeClass("is-invalid").addClass("is-invalid")
				},
				success : function (e) {
					$(e).parents(".form-group").find(".is-invalid").removeClass("is-invalid"), $(e).remove()
				},
				rules : {
					"title" : {
						required : true,
						maxlength : 100
					},
					"hotelData.numberOfFloor" : {
						required : true,
						min : 1
					},
					"hotelData.checkInTime" : {
						time : true
					},
					"hotelData.checkOutTime" : {
						time : true
					},
					"emailAddressDatas[0].email": {
	                    required: true, 
	                    email: true
	                },
	                "phoneNumberDatas[0].code": {
	                    required: true
	                },
	            	"phoneNumberDatas[0].number": {
	                    required: true, 
	                    digits : true
	                },
	            	"postalAddressDatas[0].cityId": {
	            		min : 1
	                }
				},
				messages : {
					"postalAddressDatas[0].cityId": {
	                    min: "This field is required."
	                },
	                "emailAddressDatas[0].email": {
	                	required: "Email is required"
	                },
	                "phoneNumberDatas[0].code": {
	                	required: "Country code is required"
	                },
	                "phoneNumberDatas[0].number": {
	                    required: "Phone number is required"
	                }
				}
			});
		}
};

$(document).ready(function() {
	hotelmodify.init();
});