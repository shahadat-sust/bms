$.fn.serializeObject = function()
{
    var o = {};
    var a = this.serializeArray();
    $.each(a, function() {
        if (o[this.name] !== undefined) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
};

$(document).ajaxStart(function() {
	$.loader({
		className:"blue-with-image-custom",
		content:''
	});
}).ajaxStop(function() {
	$.loader('close');
});

var assignDefaultHotel = {
	appHomeUrl: undefined,
	assignDefaultHotelUrl: undefined,
	assignDefaultHotelAjax: undefined,
	init: function () {
		$('.assign-default-hotel').on("click", function() {
			var providerId = $(this).closest("div").find("input").val();
			assignDefaultHotel.doAssignHotel(providerId);
		});
	},
	doAssignHotel : function(providerId) {
		assignDefaultHotel.assignDefaultHotelAjax = $.ajax({
			type: "POST",
            contentType: "application/json",
            url: assignDefaultHotel.assignDefaultHotelUrl,
            data: JSON.stringify({"providerId": providerId}),
            dataType: 'json',
            timeout: 600000,
            success: function (data) {
            	assignDefaultHotel.assignDefaultHotelAjax = undefined;
            	if(!data.status) {
            		console.log(data.errors);
            		Dashmix.helpers('notify', {
                		align: 'center',
                		type: 'danger', 
                		icon: 'fa fa-times mr-1', 
                		message: data.errors && data.errors.length > 0 ? data.errors[0] : 'Failed to process request, please try again!',
                		delay: 1e3
        			});
            	} else {
            		document.location.href = assignDefaultHotel.appHomeUrl;
            	}
            },
            error: function (e) {
            	assignDefaultHotel.assignDefaultHotelAjax = undefined;
            	Dashmix.helpers('notify', {
            		align: 'center',
            		type: 'danger', 
            		icon: 'fa fa-times mr-1', 
            		message: 'Failed to process request, please try again!',
            		delay: 1e3
    			});
            }
		});
	}
}

$(document).ready(function() {
	assignDefaultHotel.init();
});