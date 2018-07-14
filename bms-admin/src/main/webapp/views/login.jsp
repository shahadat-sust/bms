<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
    <head>
    	<title>BMS - Admin Login</title>
    
        <meta charset="utf-8">
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">

        <meta name="description" content="BMS - Admin Login">
        <meta name="author" content="SourceEngines">
        <meta name="robots" content="noindex, nofollow">

        <!-- Open Graph Meta -->
        <meta property="og:title" content="BMS - Admin Login">
        <meta property="og:site_name" content="SourceEngines">
        <meta property="og:description" content="BMS - Admin Login">
        <meta property="og:type" content="website">
        <meta property="og:url" content="">
        <meta property="og:image" content="">

        <!-- Icons -->
        <!-- The following icons can be replaced with your own, they are used by desktop and mobile browsers -->
        <link rel="shortcut icon" type="image/png" href="resources/media/favicons/favicon.png">
        <link rel="icon" type="image/png" sizes="192x192" href="resources/media/favicons/favicon-192x192.png">
        <link rel="apple-touch-icon" type="image/png" sizes="180x180" href="resources/media/favicons/apple-touch-icon-180x180.png">
        <!-- END Icons -->

        <!-- Stylesheets -->
        <!-- Fonts and Dashmix framework -->
        <link rel="stylesheet" id="css-main" href="https://fonts.googleapis.com/css?family=Nunito+Sans:300,400,400i,600,700">
        <link rel="stylesheet" id="css-main" href="resources/css/dashmix.min.css">

        <!-- You can include a specific file from css/themes/ folder to alter the default color theme of the template. eg: -->
        <!-- <link rel="stylesheet" id="css-theme" href="resources/css/themes/xwork.min.css"> -->
        <!-- END Stylesheets -->
    </head>
    <body>
        <!-- Page Container -->
        <!--
            Available classes for #page-container:

        GENERIC

            'enable-cookies'                            Remembers active color theme between pages (when set through color theme helper Template._uiHandleTheme())

        SIDEBAR & SIDE OVERLAY

            'sidebar-r'                                 Right Sidebar and left Side Overlay (default is left Sidebar and right Side Overlay)
            'sidebar-o'                                 Visible Sidebar by default (screen width > 991px)
            'sidebar-o-xs'                              Visible Sidebar by default (screen width < 992px)
            'sidebar-dark'                              Dark themed sidebar

            'side-overlay-hover'                        Hoverable Side Overlay (screen width > 991px)
            'side-overlay-o'                            Visible Side Overlay by default

            'enable-page-overlay'                       Enables a visible clickable Page Overlay (closes Side Overlay on click) when Side Overlay opens

            'side-scroll'                               Enables custom scrolling on Sidebar and Side Overlay instead of native scrolling (screen width > 991px)

        HEADER

            ''                                          Static Header if no class is added
            'page-header-fixed'                         Fixed Header


        Footer

            ''                                          Static Footer if no class is added
            'page-footer-fixed'                         Fixed Footer (please have in mind that the footer has a specific height when is fixed)

        HEADER STYLE

            ''                                          Classic Header style if no class is added
            'page-header-dark'                          Dark themed Header
            'page-header-glass'                         Light themed Header with transparency by default
                                                        (absolute position, perfect for light images underneath - solid light background on scroll if the Header is also set as fixed)
            'page-header-glass page-header-dark'         Dark themed Header with transparency by default
                                                        (absolute position, perfect for dark images underneath - solid dark background on scroll if the Header is also set as fixed)

        MAIN CONTENT LAYOUT

            ''                                          Full width Main Content if no class is added
            'main-content-boxed'                        Full width Main Content with a specific maximum width (screen width > 1200px)
            'main-content-narrow'                       Full width Main Content with a percentage width (screen width > 1200px)
        -->
        <div id="page-container">

            <!-- Main Container -->
            <main id="main-container">

                <!-- Page Content -->
                <div class="row no-gutters justify-content-center bg-body-dark">
                    <div class="hero-static col-sm-10 col-md-8 col-xl-6 d-flex align-items-center p-2 px-sm-0">
                        <!-- Sign In Block -->
                        <div class="block block-rounded block-transparent block-fx-pop w-100 mb-0 overflow-hidden bg-image" style="background-image: url('resources/media/photos/photo20@2x.jpg');">
                            <div class="row no-gutters">
                                <div class="col-md-6 order-md-1 bg-white">
                                    <div class="block-content block-content-full px-lg-5 py-md-5 py-lg-6">
                                        <!-- Header -->
                                        <div class="mb-2 text-center">
                                            <a class="link-fx font-w700 font-size-h1" href="#">
                                                <span class="text-dark">BMS</span>&nbsp;<span class="text-primary">Admin</span>
                                            </a>
                                            <p class="text-uppercase font-w700 font-size-sm text-muted">Sign In</p>
                                        </div>
                                        <!-- END Header -->

                                        <!-- Sign In Form -->
                                        <!-- jQuery Validation (.js-validation-signin class is initialized in js/pages/op_auth_signin.js) -->
                                        <!-- For more info and examples you can check out https://github.com/jzaefferer/jquery-validation -->
                                        <form class="js-validation-signin" action='<c:url value="/doLogin"/>' method="post">
                                            <div class="form-group">
                                                <input type="text" class="form-control form-control-alt" id="login-username" name="login-username" placeholder="Username">
                                            </div>
                                            <div class="form-group">
                                                <input type="password" class="form-control form-control-alt" id="login-password" name="login-password" placeholder="Password">
                                            </div>
                                            <div class="form-group">
                                                <button type="submit" class="btn btn-block btn-hero-primary">
                                                    <i class="fa fa-fw fa-sign-in-alt mr-1"></i> Sign In
                                                </button>
                                            </div>
                                        </form>
                                        <!-- END Sign In Form -->
                                    </div>
                                </div>
                                <div class="col-md-6 order-md-0 bg-primary-dark-op d-flex align-items-center">
                                    <div class="block-content block-content-full px-lg-5 py-md-5 py-lg-6">
                                        <div class="media">
                                            <a class="img-link mr-3" href="javascript:void(0)">
                                                <img class="img-avatar img-avatar-thumb" src="resources//media/avatars/avatar10.jpg" alt="">
                                            </a>
                                            <div class="media-body">
                                                <p class="text-white font-w600 mb-1">
                                                    Amazing platform for online hotel bookig!
                                                </p>
                                                <a class="text-white-75 font-w600" href="javascript:void(0)">SourceEngines Ltd.</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- END Sign In Block -->
                    </div>
                </div>
                <!-- END Page Content -->

            </main>
            <!-- END Main Container -->
        </div>
        <!-- END Page Container -->

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
        <script type="text/javascript" src="resources/js/dashmix.core.min.js"></script>

        <!--
            Dashmix JS
            Custom functionality including Blocks/Layout API as well as other vital and optional helpers
            webpack is putting everything together at resources/_es6/main/app.js
        -->
        <script type="text/javascript" src="resources/js/dashmix.app.min.js"></script>

        <!-- Page JS Plugins -->
        <script type="text/javascript" src="<c:url value="resources/js/plugins/jquery-validation/jquery.validate.min.js" />"></script>

        <!-- Page JS Code -->
        <script type="text/javascript" src="resources/js/pages/op_auth_signin.min.js"></script>
    </body>
</html>