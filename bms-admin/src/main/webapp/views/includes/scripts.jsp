<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!--
    Dashmix JS Core

    Vital libraries and plugins used in all pages. You can choose to not include this file if you would like
    to handle those dependencies through webpack. Please check out resources/_es6/main/bootstrap.js for more info.

    If you like, you could also include them separately directly from the resources/js/core folder in the following
    order. That can come in handy if you would like to include a few of them (eg jQuery) from a CDN.

    resources/js/core/jquery.min.js
    resources/js/core/bootstrap.bundle.min.js
    resources/js/core/simplebar.min.js
    resources/js/core/jquery-scrollLock.min.js
    resources/js/core/jquery.appear.min.js
    resources/js/core/js.cookie.min.js
-->
<script src="<c:url value="/resources/js/dashmix.core.min.js"/>"></script>

<!--
    Dashmix JS

    Custom functionality including Blocks/Layout API as well as other vital and optional helpers
    webpack is putting everything together at resources/_es6/main/app.js
-->
<script src="<c:url value="/resources/js/dashmix.app.min.js"/>"></script>

<!-- Page JS Plugins -->
<script src="<c:url value="/resources/js/plugins/jquery-validation/jquery.validate.min.js"/>"></script> 
<script src="<c:url value="/resources/js/plugins/jquery-loader/jquery.loader.js"/>"></script> 
<script src="<c:url value="/resources/js/plugins/sweetalert2/sweetalert2.min.js"/>"></script>
<script src="<c:url value="/resources/js/plugins/raty-js/jquery.raty.js"/>"></script>
<script src="<c:url value="/resources/js/plugins/bootstrap-notify/bootstrap-notify.min.js"/>"></script>
<script src="<c:url value="/resources/js/custom/utils.js"/>"></script>
