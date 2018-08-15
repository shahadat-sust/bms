var usermodify = {
		isEditMode : false,
		isUsernameAlreadyExistsUrl : "",
		countryCode : "",
		
		init : function () {
			Dashmix.helpers(['datepicker']);
			usermodify.initValidation();

			$("button[type='submit']").on("click", function() {
				if($("#formUserModify").valid()) {
					$("#formUserModify").submit();
				}
			});
			
			$(".country").on('click', function(){
				var selectedCode = $(this).find('.dial-code').html();
				$('.type-text').text(selectedCode);
				$('#val-code').val(selectedCode);
			});
			
			$("button[type='reset']").on('click', function() {
				if(usermodify.countryCode) {
					$('.type-text').text(usermodify.countryCode);
				} else {
					$('.type-text').text("Code");
				}
			});
		},

		initValidation : function() {
			/**
			 * Custom validator for contains date in dd-mm-yyyy format
			 */
			$.validator.addMethod("date", function(value, element) {
		        return value.trim() == "" || value.match(/^\d\d?\-\d\d?\-\d\d\d\d$/);
		    }, "Please enter a date in the format dd-mm-yyyy.");
			
			/**
			 * Custom validator for contains only characters(upper/lower) and numbers
			 */
			$.validator.addMethod("usernamecheck", function(value, element) {
			    return value.match(/^[a-zA-Z0-9]+$/);
			}, "Username must contains only letter(upper/lower) or number.");
			
			/**
			 * Custom validator for contains at least one lower-case letter
			 */
			$.validator.addMethod("passwordcheck", function (value, element) {
			    return value.match(/^[a-zA-Z0-9!@#$%^&*]+$/);
			}, "Password must contains only letter(upper/lower), number or special characters(!@#$%^&*).");
			
			/**
			 * Custom validator for contains at least one lower-case letter
			 */
			$.validator.addMethod("atLeastOneLowercaseLetter", function (value, element) {
			    return this.optional(element) || /[a-z]+/.test(value);
			}, "Must have at least one lowercase letter");
			 
			/**
			 * Custom validator for contains at least one upper-case letter.
			 */
			$.validator.addMethod("atLeastOneUppercaseLetter", function (value, element) {
			    return this.optional(element) || /[A-Z]+/.test(value);
			}, "Must have at least one uppercase letter");
			 
			/**
			 * Custom validator for contains at least one number.
			 */
			$.validator.addMethod("atLeastOneNumber", function (value, element) {
			    return this.optional(element) || /[0-9]+/.test(value);
			}, "Must have at least one number");
			 
			/**
			 * Custom validator for contains at least one symbol.
			 */
			$.validator.addMethod("atLeastOneSymbol", function (value, element) {
			    return this.optional(element) || /[!@#$%^&*]+/.test(value);
			}, "Must have at least one symbol");
			
			$('#formUserModify').validate({
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
	            	"userProfileData.firstName": {
	                    required: true
	                },
	                "userProfileData.lastName": {
	                    required: true
	                },
	                "userProfileData.gender": {
	                    min : 1
	                },
	                "userProfileData.birthDay": {
	                    date: true
	                },
	            	"username": {
	                    required: true, 
	                    minlength: 8,
	                    usernamecheck: true,
	                    remote : {
	                    	url : usermodify.isUsernameAlreadyExistsUrl,
	                    	type : "get",
	                    	data : {
	                    		userId : function() {
	                    			return $('#val-userId').val();
	                    		},
	                    		username : function() {
	                    			return $('#val-username').val();
	                    		}
	                    	}
	                    }
	                },
	            	"password": {
	                    required: true, 
	                    passwordcheck: true,
	                    atLeastOneLowercaseLetter: true,
	                    atLeastOneUppercaseLetter: true,
	                    atLeastOneNumber: true,
	                    atLeastOneSymbol: true,
	                    minlength: 8,
	                    maxlength: 40
	                },
	                "repassword": {
	                    required: true, equalTo : '[name="password"]'
	                },
	            	"emailAddressDatas[0].email": {
	                    required: true, email: true
	                },
	                "phoneNumberDatas[0].code": {
	                    required: true
	                },
	            	"phoneNumberDatas[0].number": {
	                    required: true, digits : true
	                },
	            	"postalAddressDatas[0].countryId": {
	            		min : 1
	                }
	            }, 
	            messages : {
	            	"userProfileData.gender": {
	                    min: "This field is required."
	                },
	                "postalAddressDatas[0].countryId": {
	                    min: "This field is required."
	                },
	                "username": {
	                    remote : $.validator.format("'{0}' is already taken.")
	                },
	                "repassword": {
	                    equalTo : "Password does not match the confirm password"
	                },
	                "emailAddressDatas[0].email": {
	                	required: "Email is required"
	                },
	                "phoneNumberDatas[0].code": {
	                	required: "Country code is required"
	                },
	                "phoneNumberDatas[0].number": {
	                    required: "Phone number is required"
	                },
	            }
			});
		}
}

$(document).ready(function() {
	usermodify.init();
});