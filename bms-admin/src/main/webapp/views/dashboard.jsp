<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">

        <title>BMS - Admin Dashboard</title>

        <meta name="description" content="Dashmix - Bootstrap 4 Admin Template &amp; UI Framework created by pixelcave and published on Themeforest">
        <meta name="author" content="pixelcave">
        <meta name="robots" content="noindex, nofollow">

        <!-- Open Graph Meta -->
        <meta property="og:title" content="BMS - Admin Dashboard">
        <meta property="og:site_name" content="SourceEngines">
        <meta property="og:description" content="BMS - Admin Dashboard">
        <meta property="og:type" content="website">
        <meta property="og:url" content="">
        <meta property="og:image" content="">

        <!-- Icons -->
        <!-- The following icons can be replaced with your own, they are used by desktop and mobile browsers -->
        <link rel="shortcut icon" href="resources/media/favicons/favicon.png">
        <link rel="icon" type="image/png" sizes="192x192" href="resources/media/favicons/favicon-192x192.png">
        <link rel="apple-touch-icon" sizes="180x180" href="resources/media/favicons/apple-touch-icon-180x180.png">
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
        <div id="page-container" class="sidebar-o enable-page-overlay side-scroll page-header-fixed page-header-dark main-content-narrow">
            <!-- Side Overlay-->
            <aside id="side-overlay">
                <!-- Side Header -->
                <div class="bg-image" style="background-image: url('resources/media/various/bg_side_overlay_header.jpg');">
                    <div class="bg-primary-op">
                        <div class="content-header">
                            <!-- User Avatar -->
                            <a class="img-link mr-1" href="be_pages_generic_profile.html">
                                <img class="img-avatar img-avatar48" src="resources/media/avatars/avatar10.jpg" alt="">
                            </a>
                            <!-- END User Avatar -->

                            <!-- User Info -->
                            <div class="ml-2">
                                <a class="text-white font-w600" href="be_pages_generic_profile.html">George Taylor</a>
                                <div class="text-white-75 font-size-sm font-italic">Full Stack Developer</div>
                            </div>
                            <!-- END User Info -->

                            <!-- Close Side Overlay -->
                            <!-- Layout API, functionality initialized in Template._uiApiLayout() -->
                            <a class="ml-auto text-white" href="javascript:void(0)" data-toggle="layout" data-action="side_overlay_close">
                                <i class="fa fa-times-circle"></i>
                            </a>
                            <!-- END Close Side Overlay -->
                        </div>
                    </div>
                </div>
                <!-- END Side Header -->

                <!-- Side Content -->
                <div class="content-side">
                    <!-- Side Overlay Tabs -->
                    <div class="block block-transparent pull-x pull-t">
                        <ul class="nav nav-tabs nav-tabs-block nav-justified" data-toggle="tabs" role="tablist">
                            <li class="nav-item">
                                <a class="nav-link active" href="#so-settings">
                                    <i class="fa fa-fw fa-cog"></i>
                                </a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="#so-people">
                                    <i class="far fa-fw fa-user-circle"></i>
                                </a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="#so-profile">
                                    <i class="far fa-fw fa-edit"></i>
                                </a>
                            </li>
                        </ul>
                        <div class="block-content tab-content overflow-hidden">
                            <!-- Settings Tab -->
                            <div class="tab-pane pull-x fade fade-up show active" id="so-settings" role="tabpanel">
                                <div class="block mb-0">
                                    <!-- Color Themes -->
                                    <!-- Toggle Themes functionality initialized in Template._uiHandleTheme() -->
                                    <div class="block-content block-content-sm block-content-full bg-body">
                                        <span class="text-uppercase font-size-sm font-w700">Color Themes</span>
                                    </div>
                                    <div class="block-content block-content-full">
                                        <div class="row gutters-tiny text-center">
                                            <div class="col-4 mb-1">
                                                <a class="d-block py-3 text-white font-size-sm font-w600 bg-default" data-toggle="theme" data-theme="default" href="#">
                                                    Default
                                                </a>
                                            </div>
                                            <div class="col-4 mb-1">
                                                <a class="d-block py-3 text-white font-size-sm font-w600 bg-xwork" data-toggle="theme" data-theme="resources/css/themes/xwork.min.css" href="#">
                                                    xWork
                                                </a>
                                            </div>
                                            <div class="col-4 mb-1">
                                                <a class="d-block py-3 text-white font-size-sm font-w600 bg-xmodern" data-toggle="theme" data-theme="resources/css/themes/xmodern.min.css" href="#">
                                                    xModern
                                                </a>
                                            </div>
                                            <div class="col-4 mb-1">
                                                <a class="d-block py-3 text-white font-size-sm font-w600 bg-xeco" data-toggle="theme" data-theme="resources/css/themes/xeco.min.css" href="#">
                                                    xEco
                                                </a>
                                            </div>
                                            <div class="col-4 mb-1">
                                                <a class="d-block py-3 text-white font-size-sm font-w600 bg-xsmooth" data-toggle="theme" data-theme="resources/css/themes/xsmooth.min.css" href="#">
                                                    xSmooth
                                                </a>
                                            </div>
                                            <div class="col-4 mb-1">
                                                <a class="d-block py-3 text-white font-size-sm font-w600 bg-xinspire" data-toggle="theme" data-theme="resources/css/themes/xinspire.min.css" href="#">
                                                    xInspire
                                                </a>
                                            </div>
                                            <div class="col-12">
                                                <a class="d-block py-3 bg-body-dark font-w600 text-dark" href="be_ui_color_themes.html">All Color Themes</a>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- END Color Themes -->

                                    <!-- Sidebar -->
                                    <div class="block-content block-content-sm block-content-full bg-body">
                                        <span class="text-uppercase font-size-sm font-w700">Sidebar</span>
                                    </div>
                                    <div class="block-content block-content-full">
                                        <div class="row gutters-tiny text-center">
                                            <div class="col-6 mb-1">
                                                <a class="d-block py-3 bg-body-dark font-w600 text-dark" data-toggle="layout" data-action="sidebar_style_dark" href="javascript:void(0)">Dark</a>
                                            </div>
                                            <div class="col-6 mb-1">
                                                <a class="d-block py-3 bg-body-dark font-w600 text-dark" data-toggle="layout" data-action="sidebar_style_light" href="javascript:void(0)">Light</a>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- END Sidebar -->

                                    <!-- Header -->
                                    <div class="block-content block-content-sm block-content-full bg-body">
                                        <span class="text-uppercase font-size-sm font-w700">Header</span>
                                    </div>
                                    <div class="block-content block-content-full">
                                        <div class="row gutters-tiny text-center mb-2">
                                            <div class="col-6 mb-1">
                                                <a class="d-block py-3 bg-body-dark font-w600 text-dark" data-toggle="layout" data-action="header_style_dark" href="javascript:void(0)">Dark</a>
                                            </div>
                                            <div class="col-6 mb-1">
                                                <a class="d-block py-3 bg-body-dark font-w600 text-dark" data-toggle="layout" data-action="header_style_light" href="javascript:void(0)">Light</a>
                                            </div>
                                            <div class="col-6 mb-1">
                                                <a class="d-block py-3 bg-body-dark font-w600 text-dark" data-toggle="layout" data-action="header_mode_fixed" href="javascript:void(0)">Fixed</a>
                                            </div>
                                            <div class="col-6 mb-1">
                                                <a class="d-block py-3 bg-body-dark font-w600 text-dark" data-toggle="layout" data-action="header_mode_static" href="javascript:void(0)">Static</a>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- END Header -->

                                    <!-- Content -->
                                    <div class="block-content block-content-sm block-content-full bg-body">
                                        <span class="text-uppercase font-size-sm font-w700">Content</span>
                                    </div>
                                    <div class="block-content block-content-full">
                                        <div class="row gutters-tiny text-center">
                                            <div class="col-6 mb-1">
                                                <a class="d-block py-3 bg-body-dark font-w600 text-dark" data-toggle="layout" data-action="content_layout_boxed" href="javascript:void(0)">Boxed</a>
                                            </div>
                                            <div class="col-6 mb-1">
                                                <a class="d-block py-3 bg-body-dark font-w600 text-dark" data-toggle="layout" data-action="content_layout_narrow" href="javascript:void(0)">Narrow</a>
                                            </div>
                                            <div class="col-12 mb-1">
                                                <a class="d-block py-3 bg-body-dark font-w600 text-dark" data-toggle="layout" data-action="content_layout_full_width" href="javascript:void(0)">Full Width</a>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- END Content -->

                                    <!-- Layout API -->
                                    <div class="block-content row justify-content-center border-top">
                                        <div class="col-9">
                                            <a class="btn btn-block btn-hero-primary" href="be_layout_api.html">
                                                <i class="fa fa-fw fa-flask mr-1"></i> Layout API
                                            </a>
                                        </div>
                                    </div>
                                    <!-- END Layout API -->
                                </div>
                            </div>
                            <!-- END Settings Tab -->

                            <!-- People -->
                            <div class="tab-pane pull-x fade fade-up" id="so-people" role="tabpanel">
                                <div class="block mb-0">
                                    <!-- Online -->
                                    <div class="block-content block-content-sm block-content-full bg-body">
                                        <span class="text-uppercase font-size-sm font-w700">Online</span>
                                    </div>
                                    <div class="block-content">
                                        <ul class="nav-items">
                                            <li>
                                                <a class="media py-2" href="be_pages_generic_profile.html">
                                                    <div class="mx-3 overlay-container">
                                                        <img class="img-avatar img-avatar48" src="resources/media/avatars/avatar6.jpg" alt="">
                                                        <span class="overlay-item item item-tiny item-circle border border-2x border-white bg-success"></span>
                                                    </div>
                                                    <div class="media-body">
                                                        <div class="font-w600">Susan Day</div>
                                                        <div class="font-size-sm text-muted">Photographer</div>
                                                    </div>
                                                </a>
                                            </li>
                                            <li>
                                                <a class="media py-2" href="be_pages_generic_profile.html">
                                                    <div class="mx-3 overlay-container">
                                                        <img class="img-avatar img-avatar48" src="resources/media/avatars/avatar13.jpg" alt="">
                                                        <span class="overlay-item item item-tiny item-circle border border-2x border-white bg-success"></span>
                                                    </div>
                                                    <div class="media-body">
                                                        <div class="font-w600">Adam McCoy</div>
                                                        <div class="font-w400 font-size-sm text-muted">Web Designer</div>
                                                    </div>
                                                </a>
                                            </li>
                                            <li>
                                                <a class="media py-2" href="be_pages_generic_profile.html">
                                                    <div class="mx-3 overlay-container">
                                                        <img class="img-avatar img-avatar48" src="resources/media/avatars/avatar6.jpg" alt="">
                                                        <span class="overlay-item item item-tiny item-circle border border-2x border-white bg-success"></span>
                                                    </div>
                                                    <div class="media-body">
                                                        <div class="font-w600">Marie Duncan</div>
                                                        <div class="font-w400 font-size-sm text-muted">Web Developer</div>
                                                    </div>
                                                </a>
                                            </li>
                                        </ul>
                                    </div>
                                    <!-- Online -->

                                    <!-- Busy -->
                                    <div class="block-content block-content-sm block-content-full bg-body">
                                        <span class="text-uppercase font-size-sm font-w700">Busy</span>
                                    </div>
                                    <div class="block-content">
                                        <ul class="nav-items">
                                            <li>
                                                <a class="media py-2" href="be_pages_generic_profile.html">
                                                    <div class="mx-3 overlay-container">
                                                        <img class="img-avatar img-avatar48" src="resources/media/avatars/avatar8.jpg" alt="">
                                                        <span class="overlay-item item item-tiny item-circle border border-2x border-white bg-danger"></span>
                                                    </div>
                                                    <div class="media-body">
                                                        <div class="font-w600">Marie Duncan</div>
                                                        <div class="font-w400 font-size-sm text-muted">UI Designer</div>
                                                    </div>
                                                </a>
                                            </li>
                                        </ul>
                                    </div>
                                    <!-- END Busy -->

                                    <!-- Away -->
                                    <div class="block-content block-content-sm block-content-full bg-body">
                                        <span class="text-uppercase font-size-sm font-w700">Away</span>
                                    </div>
                                    <div class="block-content">
                                        <ul class="nav-items">
                                            <li>
                                                <a class="media py-2" href="be_pages_generic_profile.html">
                                                    <div class="mx-3 overlay-container">
                                                        <img class="img-avatar img-avatar48" src="resources/media/avatars/avatar15.jpg" alt="">
                                                        <span class="overlay-item item item-tiny item-circle border border-2x border-white bg-warning"></span>
                                                    </div>
                                                    <div class="media-body">
                                                        <div class="font-w600">Jesse Fisher</div>
                                                        <div class="font-w400 font-size-sm text-muted">Copywriter</div>
                                                    </div>
                                                </a>
                                            </li>
                                            <li>
                                                <a class="media py-2" href="be_pages_generic_profile.html">
                                                    <div class="mx-3 overlay-container">
                                                        <img class="img-avatar img-avatar48" src="resources/media/avatars/avatar8.jpg" alt="">
                                                        <span class="overlay-item item item-tiny item-circle border border-2x border-white bg-warning"></span>
                                                    </div>
                                                    <div class="media-body">
                                                        <div class="font-w600">Barbara Scott</div>
                                                        <div class="font-w400 font-size-sm text-muted">Writer</div>
                                                    </div>
                                                </a>
                                            </li>
                                        </ul>
                                    </div>
                                    <!-- END Away -->

                                    <!-- Offline -->
                                    <div class="block-content block-content-sm block-content-full bg-body">
                                        <span class="text-uppercase font-size-sm font-w700">Offline</span>
                                    </div>
                                    <div class="block-content">
                                        <ul class="nav-items">
                                            <li>
                                                <a class="media py-2" href="be_pages_generic_profile.html">
                                                    <div class="mx-3 overlay-container">
                                                        <img class="img-avatar img-avatar48" src="resources/media/avatars/avatar11.jpg" alt="">
                                                        <span class="overlay-item item item-tiny item-circle border border-2x border-white bg-muted"></span>
                                                    </div>
                                                    <div class="media-body">
                                                        <div class="font-w600">Jack Greene</div>
                                                        <div class="font-w400 font-size-sm text-muted">Teacher</div>
                                                    </div>
                                                </a>
                                            </li>
                                            <li>
                                                <a class="media py-2" href="be_pages_generic_profile.html">
                                                    <div class="mx-3 overlay-container">
                                                        <img class="img-avatar img-avatar48" src="resources/media/avatars/avatar4.jpg" alt="">
                                                        <span class="overlay-item item item-tiny item-circle border border-2x border-white bg-muted"></span>
                                                    </div>
                                                    <div class="media-body">
                                                        <div class="font-w600">Laura Carr</div>
                                                        <div class="font-w400 font-size-sm text-muted">Photographer</div>
                                                    </div>
                                                </a>
                                            </li>
                                            <li>
                                                <a class="media py-2" href="be_pages_generic_profile.html">
                                                    <div class="mx-3 overlay-container">
                                                        <img class="img-avatar img-avatar48" src="resources/media/avatars/avatar6.jpg" alt="">
                                                        <span class="overlay-item item item-tiny item-circle border border-2x border-white bg-muted"></span>
                                                    </div>
                                                    <div class="media-body">
                                                        <div class="font-w600">Susan Day</div>
                                                        <div class="font-w400 font-size-sm text-muted">Front-end Developer</div>
                                                    </div>
                                                </a>
                                            </li>
                                            <li>
                                                <a class="media py-2" href="be_pages_generic_profile.html">
                                                    <div class="mx-3 overlay-container">
                                                        <img class="img-avatar img-avatar48" src="resources/media/avatars/avatar9.jpg" alt="">
                                                        <span class="overlay-item item item-tiny item-circle border border-2x border-white bg-muted"></span>
                                                    </div>
                                                    <div class="media-body">
                                                        <div class="font-w600">Carl Wells</div>
                                                        <div class="font-w400 font-size-sm text-muted">UX Specialist</div>
                                                    </div>
                                                </a>
                                            </li>
                                        </ul>
                                    </div>
                                    <!-- END Offline -->

                                    <!-- Add People -->
                                    <div class="block-content row justify-content-center border-top">
                                        <div class="col-9">
                                            <a class="btn btn-block btn-hero-primary" href="javascript:void(0)">
                                                <i class="fa fa-fw fa-plus mr-1"></i> Add People
                                            </a>
                                        </div>
                                    </div>
                                    <!-- END Add People -->
                                </div>
                            </div>
                            <!-- END People -->

                            <!-- Profile -->
                            <div class="tab-pane pull-x fade fade-up" id="so-profile" role="tabpanel">
                                <form action="be_pages_dashboard.html" method="post" onsubmit="return false;">
                                    <div class="block mb-0">
                                        <!-- Personal -->
                                        <div class="block-content block-content-sm block-content-full bg-body">
                                            <span class="text-uppercase font-size-sm font-w700">Personal</span>
                                        </div>
                                        <div class="block-content block-content-full">
                                            <div class="form-group">
                                                <label>Username</label>
                                                <input type="text" readonly class="form-control" id="staticEmail" value="Admin">
                                            </div>
                                            <div class="form-group">
                                                <label for="so-profile-name">Name</label>
                                                <input type="text" class="form-control" id="so-profile-name" name="so-profile-name" value="George Taylor">
                                            </div>
                                            <div class="form-group">
                                                <label for="so-profile-email">Email</label>
                                                <input type="email" class="form-control" id="so-profile-email" name="so-profile-email" value="g.taylor@example.com">
                                            </div>
                                        </div>
                                        <!-- END Personal -->

                                        <!-- Password Update -->
                                        <div class="block-content block-content-sm block-content-full bg-body">
                                            <span class="text-uppercase font-size-sm font-w700">Password Update</span>
                                        </div>
                                        <div class="block-content block-content-full">
                                            <div class="form-group">
                                                <label for="so-profile-password">Current Password</label>
                                                <input type="password" class="form-control" id="so-profile-password" name="so-profile-password">
                                            </div>
                                            <div class="form-group">
                                                <label for="so-profile-new-password">New Password</label>
                                                <input type="password" class="form-control" id="so-profile-new-password" name="so-profile-new-password">
                                            </div>
                                            <div class="form-group">
                                                <label for="so-profile-new-password-confirm">Confirm New Password</label>
                                                <input type="password" class="form-control" id="so-profile-new-password-confirm" name="so-profile-new-password-confirm">
                                            </div>
                                        </div>
                                        <!-- END Password Update -->

                                        <!-- Options -->
                                        <div class="block-content block-content-sm block-content-full bg-body">
                                            <span class="text-uppercase font-size-sm font-w700">Options</span>
                                        </div>
                                        <div class="block-content">
                                            <div class="custom-control custom-checkbox custom-control-primary mb-1">
                                                <input type="checkbox" class="custom-control-input" id="so-settings-status" name="so-settings-status" value="1">
                                                <label class="custom-control-label" for="so-settings-status">Online Status</label>
                                            </div>
                                            <p class="text-muted font-size-sm">
                                                Make your online status visible to other users of your app
                                            </p>
                                            <div class="custom-control custom-checkbox custom-control-primary mb-1">
                                                <input type="checkbox" class="custom-control-input" id="so-settings-notifications" name="so-settings-notifications" value="1" checked>
                                                <label class="custom-control-label" for="so-settings-notifications">Notifications</label>
                                            </div>
                                            <p class="text-muted font-size-sm">
                                                Receive desktop notifications regarding your projects and sales
                                            </p>
                                            <div class="custom-control custom-checkbox custom-control-primary mb-1">
                                                <input type="checkbox" class="custom-control-input" id="so-settings-updates" name="so-settings-updates" value="1" checked>
                                                <label class="custom-control-label" for="so-settings-updates">Auto Updates</label>
                                            </div>
                                            <p class="text-muted font-size-sm">
                                                If enabled, we will keep all your applications and servers up to date with the most recent features automatically
                                            </p>
                                        </div>
                                        <!-- END Options -->

                                        <!-- Submit -->
                                        <div class="block-content row justify-content-center border-top">
                                            <div class="col-9">
                                                <button type="submit" class="btn btn-block btn-hero-primary">
                                                    <i class="fa fa-fw fa-save mr-1"></i> Save
                                                </button>
                                            </div>
                                        </div>
                                        <!-- END Submit -->
                                    </div>
                                </form>
                            </div>
                            <!-- END Profile -->
                        </div>
                    </div>
                    <!-- END Side Overlay Tabs -->
                </div>
                <!-- END Side Content -->
            </aside>
            <!-- END Side Overlay -->

            <!-- Sidebar -->
            <nav id="sidebar" aria-label="Main Navigation">
                <!-- Side Header -->
                <div class="bg-header-dark">
                    <div class="content-header bg-white-10">
                        <!-- Logo -->
                        <a class="link-fx font-w600 font-size-lg text-white" href="index.html">
                            <span class="text-white-75">Dash</span><span class="text-white">mix</span>
                        </a>
                        <!-- END Logo -->

                        <!-- Options -->
                        <div>
                            <!-- Toggle Sidebar Style -->
                            <!-- Layout API, functionality initialized in Template._uiApiLayout() -->
                            <!-- Class Toggle, functionality initialized in Helpers.coreToggleClass() -->
                            <a class="js-class-toggle text-white-75" data-target="#sidebar-style-toggler" data-class="fa-toggle-off fa-toggle-on" data-toggle="layout" data-action="sidebar_style_toggle" href="javascript:void(0)">
                                <i class="fa fa-toggle-off" id="sidebar-style-toggler"></i>
                            </a>
                            <!-- END Toggle Sidebar Style -->

                            <!-- Close Sidebar, Visible only on mobile screens -->
                            <!-- Layout API, functionality initialized in Template._uiApiLayout() -->
                            <a class="d-lg-none text-white ml-2" data-toggle="layout" data-action="sidebar_close" href="javascript:void(0)">
                                <i class="fa fa-times-circle"></i>
                            </a>
                            <!-- END Close Sidebar -->
                        </div>
                        <!-- END Options -->
                    </div>
                </div>
                <!-- END Side Header -->

                <!-- Side Navigation -->
                <div class="content-side content-side-full">
                    <ul class="nav-main">
                        <li class="nav-main-item">
                            <a class="nav-main-link active" href="be_pages_dashboard.html">
                                <i class="nav-main-link-icon si si-cursor"></i>
                                <span class="nav-main-link-name">Dashboard</span>
                                <span class="nav-main-link-badge badge badge-pill badge-success">5</span>
                            </a>
                        </li>
                        <li class="nav-main-heading">Base</li>
                        <li class="nav-main-item">
                            <a class="nav-main-link nav-main-link-submenu" data-toggle="submenu" aria-haspopup="true" aria-expanded="false" href="#">
                                <i class="nav-main-link-icon si si-grid"></i>
                                <span class="nav-main-link-name">Blocks</span>
                            </a>
                            <ul class="nav-main-submenu">
                                <li class="nav-main-item">
                                    <a class="nav-main-link" href="be_blocks_styles.html">
                                        <span class="nav-main-link-name">Styles</span>
                                    </a>
                                </li>
                                <li class="nav-main-item">
                                    <a class="nav-main-link" href="be_blocks_options.html">
                                        <span class="nav-main-link-name">Options</span>
                                    </a>
                                </li>
                                <li class="nav-main-item">
                                    <a class="nav-main-link" href="be_blocks_forms.html">
                                        <span class="nav-main-link-name">Forms</span>
                                    </a>
                                </li>
                                <li class="nav-main-item">
                                    <a class="nav-main-link" href="be_blocks_themed.html">
                                        <span class="nav-main-link-name">Themed</span>
                                    </a>
                                </li>
                                <li class="nav-main-item">
                                    <a class="nav-main-link" href="be_blocks_api.html">
                                        <span class="nav-main-link-name">API</span>
                                    </a>
                                </li>
                            </ul>
                        </li>
                        <li class="nav-main-item">
                            <a class="nav-main-link nav-main-link-submenu" data-toggle="submenu" aria-haspopup="true" aria-expanded="false" href="#">
                                <i class="nav-main-link-icon si si-puzzle"></i>
                                <span class="nav-main-link-name">Widgets</span>
                            </a>
                            <ul class="nav-main-submenu">
                                <li class="nav-main-item">
                                    <a class="nav-main-link" href="be_widgets_tiles.html">
                                        <span class="nav-main-link-name">Tiles</span>
                                    </a>
                                </li>
                                <li class="nav-main-item">
                                    <a class="nav-main-link" href="be_widgets_stats.html">
                                        <span class="nav-main-link-name">Statistics</span>
                                    </a>
                                </li>
                                <li class="nav-main-item">
                                    <a class="nav-main-link" href="be_widgets_media.html">
                                        <span class="nav-main-link-name">Media</span>
                                    </a>
                                </li>
                                <li class="nav-main-item">
                                    <a class="nav-main-link" href="be_widgets_users.html">
                                        <span class="nav-main-link-name">Users</span>
                                    </a>
                                </li>
                            </ul>
                        </li>
                        <li class="nav-main-item">
                            <a class="nav-main-link nav-main-link-submenu" data-toggle="submenu" aria-haspopup="true" aria-expanded="false" href="#">
                                <i class="nav-main-link-icon si si-star"></i>
                                <span class="nav-main-link-name">Layout</span>
                            </a>
                            <ul class="nav-main-submenu">
                                <li class="nav-main-item">
                                    <a class="nav-main-link nav-main-link-submenu" data-toggle="submenu" aria-haspopup="true" aria-expanded="false" href="#">
                                        <span class="nav-main-link-name">Page</span>
                                    </a>
                                    <ul class="nav-main-submenu">
                                        <li class="nav-main-item">
                                            <a class="nav-main-link" href="be_layout_page_default.html">
                                                <span class="nav-main-link-name">Default</span>
                                            </a>
                                        </li>
                                        <li class="nav-main-item">
                                            <a class="nav-main-link" href="be_layout_page_flipped.html">
                                                <span class="nav-main-link-name">Flipped</span>
                                            </a>
                                        </li>
                                        <li class="nav-main-item">
                                            <a class="nav-main-link" href="be_layout_page_native_scrolling.html">
                                                <span class="nav-main-link-name">Native Scrolling</span>
                                            </a>
                                        </li>
                                    </ul>
                                </li>
                                <li class="nav-main-item">
                                    <a class="nav-main-link nav-main-link-submenu" data-toggle="submenu" aria-haspopup="true" aria-expanded="false" href="#">
                                        <span class="nav-main-link-name">Main Content</span>
                                    </a>
                                    <ul class="nav-main-submenu">
                                        <li class="nav-main-item">
                                            <a class="nav-main-link" href="be_layout_content_main_full_width.html">
                                                <span class="nav-main-link-name">Full Width</span>
                                            </a>
                                        </li>
                                        <li class="nav-main-item">
                                            <a class="nav-main-link" href="be_layout_content_main_narrow.html">
                                                <span class="nav-main-link-name">Narrow</span>
                                            </a>
                                        </li>
                                        <li class="nav-main-item">
                                            <a class="nav-main-link" href="be_layout_content_main_boxed.html">
                                                <span class="nav-main-link-name">Boxed</span>
                                            </a>
                                        </li>
                                    </ul>
                                </li>
                                <li class="nav-main-item">
                                    <a class="nav-main-link nav-main-link-submenu" data-toggle="submenu" aria-haspopup="true" aria-expanded="false" href="#">
                                        <span class="nav-main-link-name">Side Content</span>
                                    </a>
                                    <ul class="nav-main-submenu">
                                        <li class="nav-main-item">
                                            <a class="nav-main-link" href="be_layout_content_side_left.html">
                                                <span class="nav-main-link-name">Left</span>
                                            </a>
                                        </li>
                                        <li class="nav-main-item">
                                            <a class="nav-main-link" href="be_layout_content_side_right.html">
                                                <span class="nav-main-link-name">Right</span>
                                            </a>
                                        </li>
                                    </ul>
                                </li>
                                <li class="nav-main-item">
                                    <a class="nav-main-link nav-main-link-submenu" data-toggle="submenu" aria-haspopup="true" aria-expanded="false" href="#">
                                        <span class="nav-main-link-name">Hero</span>
                                    </a>
                                    <ul class="nav-main-submenu">
                                        <li class="nav-main-item">
                                            <a class="nav-main-link nav-main-link-submenu" data-toggle="submenu" aria-haspopup="true" aria-expanded="false" href="#">
                                                <span class="nav-main-link-name">Color</span>
                                            </a>
                                            <ul class="nav-main-submenu">
                                                <li class="nav-main-item">
                                                    <a class="nav-main-link" href="be_layout_hero_color_dark.html">
                                                        <span class="nav-main-link-name">Dark</span>
                                                    </a>
                                                </li>
                                                <li class="nav-main-item">
                                                    <a class="nav-main-link" href="be_layout_hero_color_light.html">
                                                        <span class="nav-main-link-name">Light</span>
                                                    </a>
                                                </li>
                                            </ul>
                                        </li>
                                        <li class="nav-main-item">
                                            <a class="nav-main-link nav-main-link-submenu" data-toggle="submenu" aria-haspopup="true" aria-expanded="false" href="#">
                                                <span class="nav-main-link-name">Image</span>
                                            </a>
                                            <ul class="nav-main-submenu">
                                                <li class="nav-main-item">
                                                    <a class="nav-main-link" href="be_layout_hero_image_dark.html">
                                                        <span class="nav-main-link-name">Dark</span>
                                                    </a>
                                                </li>
                                                <li class="nav-main-item">
                                                    <a class="nav-main-link" href="be_layout_hero_image_light.html">
                                                        <span class="nav-main-link-name">Light</span>
                                                    </a>
                                                </li>
                                            </ul>
                                        </li>
                                        <li class="nav-main-item">
                                            <a class="nav-main-link nav-main-link-submenu" data-toggle="submenu" aria-haspopup="true" aria-expanded="false" href="#">
                                                <span class="nav-main-link-name">Video</span>
                                            </a>
                                            <ul class="nav-main-submenu">
                                                <li class="nav-main-item">
                                                    <a class="nav-main-link" href="be_layout_hero_video_dark.html">
                                                        <span class="nav-main-link-name">Dark</span>
                                                    </a>
                                                </li>
                                                <li class="nav-main-item">
                                                    <a class="nav-main-link" href="be_layout_hero_video_light.html">
                                                        <span class="nav-main-link-name">Light</span>
                                                    </a>
                                                </li>
                                            </ul>
                                        </li>
                                    </ul>
                                </li>
                                <li class="nav-main-item">
                                    <a class="nav-main-link nav-main-link-submenu" data-toggle="submenu" aria-haspopup="true" aria-expanded="false" href="#">
                                        <span class="nav-main-link-name">Header</span>
                                    </a>
                                    <ul class="nav-main-submenu">
                                        <li class="nav-main-item">
                                            <a class="nav-main-link nav-main-link-submenu" data-toggle="submenu" aria-haspopup="true" aria-expanded="false" href="#">
                                                <span class="nav-main-link-name">Fixed</span>
                                            </a>
                                            <ul class="nav-main-submenu">
                                                <li class="nav-main-item">
                                                    <a class="nav-main-link" href="be_layout_header_fixed_light.html">
                                                        <span class="nav-main-link-name">Light</span>
                                                    </a>
                                                </li>
                                                <li class="nav-main-item">
                                                    <a class="nav-main-link" href="be_layout_header_fixed_light_glass.html">
                                                        <span class="nav-main-link-name">Light Glass</span>
                                                    </a>
                                                </li>
                                                <li class="nav-main-item">
                                                    <a class="nav-main-link" href="be_layout_header_fixed_dark.html">
                                                        <span class="nav-main-link-name">Dark</span>
                                                    </a>
                                                </li>
                                                <li class="nav-main-item">
                                                    <a class="nav-main-link" href="be_layout_header_fixed_dark_glass.html">
                                                        <span class="nav-main-link-name">Dark Glass</span>
                                                    </a>
                                                </li>
                                            </ul>
                                        </li>
                                        <li class="nav-main-item">
                                            <a class="nav-main-link nav-main-link-submenu" data-toggle="submenu" aria-haspopup="true" aria-expanded="false" href="#">
                                                <span class="nav-main-link-name">Static</span>
                                            </a>
                                            <ul class="nav-main-submenu">
                                                <li class="nav-main-item">
                                                    <a class="nav-main-link" href="be_layout_header_static_light.html">
                                                        <span class="nav-main-link-name">Light</span>
                                                    </a>
                                                </li>
                                                <li class="nav-main-item">
                                                    <a class="nav-main-link" href="be_layout_header_static_light_glass.html">
                                                        <span class="nav-main-link-name">Light Glass</span>
                                                    </a>
                                                </li>
                                                <li class="nav-main-item">
                                                    <a class="nav-main-link" href="be_layout_header_static_dark.html">
                                                        <span class="nav-main-link-name">Dark</span>
                                                    </a>
                                                </li>
                                                <li class="nav-main-item">
                                                    <a class="nav-main-link" href="be_layout_header_static_dark_glass.html">
                                                        <span class="nav-main-link-name">Dark Glass</span>
                                                    </a>
                                                </li>
                                            </ul>
                                        </li>
                                    </ul>
                                </li>
                                <li class="nav-main-item">
                                    <a class="nav-main-link nav-main-link-submenu" data-toggle="submenu" aria-haspopup="true" aria-expanded="false" href="#">
                                        <span class="nav-main-link-name">Footer</span>
                                    </a>
                                    <ul class="nav-main-submenu">
                                        <li class="nav-main-item">
                                            <a class="nav-main-link" href="be_layout_footer_static.html">
                                                <span class="nav-main-link-name">Static</span>
                                            </a>
                                        </li>
                                        <li class="nav-main-item">
                                            <a class="nav-main-link" href="be_layout_footer_fixed.html">
                                                <span class="nav-main-link-name">Fixed</span>
                                                <span class="nav-main-link-badge badge badge-pill badge-success">New</span>
                                            </a>
                                        </li>
                                    </ul>
                                </li>
                                <li class="nav-main-item">
                                    <a class="nav-main-link nav-main-link-submenu" data-toggle="submenu" aria-haspopup="true" aria-expanded="false" href="#">
                                        <span class="nav-main-link-name">Sidebar</span>
                                    </a>
                                    <ul class="nav-main-submenu">
                                        <li class="nav-main-item">
                                            <a class="nav-main-link" href="be_layout_sidebar_light.html">
                                                <span class="nav-main-link-name">Light</span>
                                            </a>
                                        </li>
                                        <li class="nav-main-item">
                                            <a class="nav-main-link" href="be_layout_sidebar_dark.html">
                                                <span class="nav-main-link-name">Dark</span>
                                            </a>
                                        </li>
                                        <li class="nav-main-item">
                                            <a class="nav-main-link" href="be_layout_sidebar_hidden.html">
                                                <span class="nav-main-link-name">Hidden</span>
                                            </a>
                                        </li>
                                    </ul>
                                </li>
                                <li class="nav-main-item">
                                    <a class="nav-main-link nav-main-link-submenu" data-toggle="submenu" aria-haspopup="true" aria-expanded="false" href="#">
                                        <span class="nav-main-link-name">Side Overlay</span>
                                    </a>
                                    <ul class="nav-main-submenu">
                                        <li class="nav-main-item">
                                            <a class="nav-main-link" href="be_layout_side_overlay_visible.html">
                                                <span class="nav-main-link-name">Visible</span>
                                            </a>
                                        </li>
                                        <li class="nav-main-item">
                                            <a class="nav-main-link" href="be_layout_side_overlay_mode_hover.html">
                                                <span class="nav-main-link-name">Hover Mode</span>
                                            </a>
                                        </li>
                                        <li class="nav-main-item">
                                            <a class="nav-main-link" href="be_layout_side_overlay_no_page_overlay.html">
                                                <span class="nav-main-link-name">No Page Overlay</span>
                                            </a>
                                        </li>
                                    </ul>
                                </li>
                                <li class="nav-main-item">
                                    <a class="nav-main-link" href="be_layout_api.html">
                                        <span class="nav-main-link-name">API</span>
                                    </a>
                                </li>
                            </ul>
                        </li>
                        <li class="nav-main-heading">Interface</li>
                        <li class="nav-main-item">
                            <a class="nav-main-link nav-main-link-submenu" data-toggle="submenu" aria-haspopup="true" aria-expanded="false" href="#">
                                <i class="nav-main-link-icon si si-chemistry"></i>
                                <span class="nav-main-link-name">Elements</span>
                            </a>
                            <ul class="nav-main-submenu">
                                <li class="nav-main-item">
                                    <a class="nav-main-link" href="be_ui_icons.html">
                                        <span class="nav-main-link-name">Icon Packs</span>
                                    </a>
                                </li>
                                <li class="nav-main-item">
                                    <a class="nav-main-link" href="be_ui_grid.html">
                                        <span class="nav-main-link-name">Flexbox Grid</span>
                                    </a>
                                </li>
                                <li class="nav-main-item">
                                    <a class="nav-main-link" href="be_ui_typography.html">
                                        <span class="nav-main-link-name">Typography</span>
                                    </a>
                                </li>
                                <li class="nav-main-item">
                                    <a class="nav-main-link" href="be_ui_navigation.html">
                                        <span class="nav-main-link-name">Navigation</span>
                                    </a>
                                </li>
                                <li class="nav-main-item">
                                    <a class="nav-main-link" href="be_ui_tabs.html">
                                        <span class="nav-main-link-name">Tabs</span>
                                    </a>
                                </li>
                                <li class="nav-main-item">
                                    <a class="nav-main-link" href="be_ui_buttons.html">
                                        <span class="nav-main-link-name">Buttons</span>
                                    </a>
                                </li>
                                <li class="nav-main-item">
                                    <a class="nav-main-link" href="be_ui_buttons_groups.html">
                                        <span class="nav-main-link-name">Button Groups</span>
                                    </a>
                                </li>
                                <li class="nav-main-item">
                                    <a class="nav-main-link" href="be_ui_dropdowns.html">
                                        <span class="nav-main-link-name">Dropdowns</span>
                                    </a>
                                </li>
                                <li class="nav-main-item">
                                    <a class="nav-main-link" href="be_ui_progress.html">
                                        <span class="nav-main-link-name">Progress</span>
                                    </a>
                                </li>
                                <li class="nav-main-item">
                                    <a class="nav-main-link" href="be_ui_alerts.html">
                                        <span class="nav-main-link-name">Alerts</span>
                                    </a>
                                </li>
                                <li class="nav-main-item">
                                    <a class="nav-main-link" href="be_ui_tooltips.html">
                                        <span class="nav-main-link-name">Tooltips</span>
                                    </a>
                                </li>
                                <li class="nav-main-item">
                                    <a class="nav-main-link" href="be_ui_popovers.html">
                                        <span class="nav-main-link-name">Popovers</span>
                                    </a>
                                </li>
                                <li class="nav-main-item">
                                    <a class="nav-main-link" href="be_ui_accordion.html">
                                        <span class="nav-main-link-name">Accordion</span>
                                    </a>
                                </li>
                                <li class="nav-main-item">
                                    <a class="nav-main-link" href="be_ui_modals.html">
                                        <span class="nav-main-link-name">Modals</span>
                                    </a>
                                </li>
                                <li class="nav-main-item">
                                    <a class="nav-main-link" href="be_ui_images.html">
                                        <span class="nav-main-link-name">Images Overlay</span>
                                    </a>
                                </li>
                                <li class="nav-main-item">
                                    <a class="nav-main-link" href="be_ui_timeline.html">
                                        <span class="nav-main-link-name">Timeline</span>
                                    </a>
                                </li>
                                <li class="nav-main-item">
                                    <a class="nav-main-link" href="be_ui_ribbons.html">
                                        <span class="nav-main-link-name">Ribbons</span>
                                    </a>
                                </li>
                                <li class="nav-main-item">
                                    <a class="nav-main-link" href="be_ui_animations.html">
                                        <span class="nav-main-link-name">Animations</span>
                                    </a>
                                </li>
                                <li class="nav-main-item">
                                    <a class="nav-main-link" href="be_ui_color_themes.html">
                                        <span class="nav-main-link-name">Color Themes</span>
                                    </a>
                                </li>
                            </ul>
                        </li>
                        <li class="nav-main-item">
                            <a class="nav-main-link nav-main-link-submenu" data-toggle="submenu" aria-haspopup="true" aria-expanded="false" href="#">
                                <i class="nav-main-link-icon si si-cup"></i>
                                <span class="nav-main-link-name">Tables</span>
                            </a>
                            <ul class="nav-main-submenu">
                                <li class="nav-main-item">
                                    <a class="nav-main-link" href="be_tables_styles.html">
                                        <span class="nav-main-link-name">Styles</span>
                                    </a>
                                </li>
                                <li class="nav-main-item">
                                    <a class="nav-main-link" href="be_tables_responsive.html">
                                        <span class="nav-main-link-name">Responsive</span>
                                    </a>
                                </li>
                                <li class="nav-main-item">
                                    <a class="nav-main-link" href="be_tables_helpers.html">
                                        <span class="nav-main-link-name">Helpers</span>
                                    </a>
                                </li>
                                <li class="nav-main-item">
                                    <a class="nav-main-link" href="be_tables_pricing.html">
                                        <span class="nav-main-link-name">Pricing</span>
                                    </a>
                                </li>
                                <li class="nav-main-item">
                                    <a class="nav-main-link" href="be_tables_datatables.html">
                                        <span class="nav-main-link-name">DataTables</span>
                                    </a>
                                </li>
                            </ul>
                        </li>
                        <li class="nav-main-item">
                            <a class="nav-main-link nav-main-link-submenu" data-toggle="submenu" aria-haspopup="true" aria-expanded="false" href="#">
                                <i class="nav-main-link-icon si si-notebook"></i>
                                <span class="nav-main-link-name">Forms</span>
                            </a>
                            <ul class="nav-main-submenu">
                                <li class="nav-main-item">
                                    <a class="nav-main-link" href="be_forms_elements.html">
                                        <span class="nav-main-link-name">Elements</span>
                                    </a>
                                </li>
                                <li class="nav-main-item">
                                    <a class="nav-main-link" href="be_forms_custom_controls.html">
                                        <span class="nav-main-link-name">Custom Controls</span>
                                        <span class="nav-main-link-badge badge badge-pill badge-success">New</span>
                                    </a>
                                </li>
                                <li class="nav-main-item">
                                    <a class="nav-main-link" href="be_forms_layouts.html">
                                        <span class="nav-main-link-name">Layouts</span>
                                    </a>
                                </li>
                                <li class="nav-main-item">
                                    <a class="nav-main-link" href="be_forms_input_groups.html">
                                        <span class="nav-main-link-name">Input Groups</span>
                                    </a>
                                </li>
                                <li class="nav-main-item">
                                    <a class="nav-main-link" href="be_forms_plugins.html">
                                        <span class="nav-main-link-name">Plugins</span>
                                    </a>
                                </li>
                                <li class="nav-main-item">
                                    <a class="nav-main-link" href="be_forms_editors.html">
                                        <span class="nav-main-link-name">Editors</span>
                                    </a>
                                </li>
                                <li class="nav-main-item">
                                    <a class="nav-main-link" href="be_forms_validation.html">
                                        <span class="nav-main-link-name">Validation</span>
                                    </a>
                                </li>
                                <li class="nav-main-item">
                                    <a class="nav-main-link" href="be_forms_wizard.html">
                                        <span class="nav-main-link-name">Wizard</span>
                                    </a>
                                </li>
                            </ul>
                        </li>
                        <li class="nav-main-heading">Extend</li>
                        <li class="nav-main-item">
                            <a class="nav-main-link nav-main-link-submenu" data-toggle="submenu" aria-haspopup="true" aria-expanded="false" href="#">
                                <i class="nav-main-link-icon si si-wrench"></i>
                                <span class="nav-main-link-name">Components</span>
                            </a>
                            <ul class="nav-main-submenu">
                                <li class="nav-main-item">
                                    <a class="nav-main-link" href="be_comp_dialogs.html">
                                        <span class="nav-main-link-name">Dialogs</span>
                                    </a>
                                </li>
                                <li class="nav-main-item">
                                    <a class="nav-main-link" href="be_comp_notifications.html">
                                        <span class="nav-main-link-name">Notifications</span>
                                    </a>
                                </li>
                                <li class="nav-main-item">
                                    <a class="nav-main-link" href="be_comp_loaders.html">
                                        <span class="nav-main-link-name">Loaders</span>
                                    </a>
                                </li>
                                <li class="nav-main-item">
                                    <a class="nav-main-link" href="be_comp_charts.html">
                                        <span class="nav-main-link-name">Charts</span>
                                    </a>
                                </li>
                                <li class="nav-main-item">
                                    <a class="nav-main-link" href="be_comp_gallery.html">
                                        <span class="nav-main-link-name">Gallery</span>
                                    </a>
                                </li>
                                <li class="nav-main-item">
                                    <a class="nav-main-link" href="be_comp_sliders.html">
                                        <span class="nav-main-link-name">Sliders</span>
                                    </a>
                                </li>
                                <li class="nav-main-item">
                                    <a class="nav-main-link" href="be_comp_rating.html">
                                        <span class="nav-main-link-name">Rating</span>
                                    </a>
                                </li>
                                <li class="nav-main-item">
                                    <a class="nav-main-link" href="be_comp_appear.html">
                                        <span class="nav-main-link-name">Appear</span>
                                    </a>
                                </li>
                                <li class="nav-main-item">
                                    <a class="nav-main-link" href="be_comp_calendar.html">
                                        <span class="nav-main-link-name">Calendar</span>
                                    </a>
                                </li>
                                <li class="nav-main-item">
                                    <a class="nav-main-link" href="be_comp_image_cropper.html">
                                        <span class="nav-main-link-name">Image Cropper</span>
                                    </a>
                                </li>
                                <li class="nav-main-item">
                                    <a class="nav-main-link" href="be_comp_maps_google.html">
                                        <span class="nav-main-link-name">Google Maps</span>
                                    </a>
                                </li>
                                <li class="nav-main-item">
                                    <a class="nav-main-link" href="be_comp_maps_vector.html">
                                        <span class="nav-main-link-name">Vector Maps</span>
                                    </a>
                                </li>
                                <li class="nav-main-item">
                                    <a class="nav-main-link" href="be_comp_syntax_highlighting.html">
                                        <span class="nav-main-link-name">Syntax Highlighting</span>
                                    </a>
                                </li>
                            </ul>
                        </li>
                        <li class="nav-main-item">
                            <a class="nav-main-link nav-main-link-submenu" data-toggle="submenu" aria-haspopup="true" aria-expanded="false" href="#">
                                <i class="nav-main-link-icon si si-settings"></i>
                                <span class="nav-main-link-name">Main Menu</span>
                            </a>
                            <ul class="nav-main-submenu">
                                <li class="nav-main-item">
                                    <a class="nav-main-link" href="#">
                                        <i class="nav-main-link-icon si si-drawer"></i>
                                        <span class="nav-main-link-name">1.1 Item</span>
                                    </a>
                                </li>
                                <li class="nav-main-item">
                                    <a class="nav-main-link" href="#">
                                        <i class="nav-main-link-icon si si-fire"></i>
                                        <span class="nav-main-link-name">1.2 Item</span>
                                    </a>
                                </li>
                                <li class="nav-main-item">
                                    <a class="nav-main-link" href="#">
                                        <i class="nav-main-link-icon si si-graph"></i>
                                        <span class="nav-main-link-name">1.3 Item</span>
                                    </a>
                                </li>
                                <li class="nav-main-item">
                                    <a class="nav-main-link nav-main-link-submenu" data-toggle="submenu" aria-haspopup="true" aria-expanded="false" href="#">
                                        <span class="nav-main-link-name">Sub Level 2</span>
                                        <span class="nav-main-link-badge badge badge-pill badge-primary">3</span>
                                    </a>
                                    <ul class="nav-main-submenu">
                                        <li class="nav-main-item">
                                            <a class="nav-main-link" href="#">
                                                <i class="nav-main-link-icon si si-tag"></i>
                                                <span class="nav-main-link-name">2.1 Item</span>
                                                <span class="nav-main-link-badge badge badge-pill badge-info">2</span>
                                            </a>
                                        </li>
                                        <li class="nav-main-item">
                                            <a class="nav-main-link" href="#">
                                                <i class="nav-main-link-icon si si-pie-chart"></i>
                                                <span class="nav-main-link-name">2.2 Item</span>
                                                <span class="nav-main-link-badge badge badge-pill badge-danger">2</span>
                                            </a>
                                        </li>
                                        <li class="nav-main-item">
                                            <a class="nav-main-link" href="#">
                                                <i class="nav-main-link-icon si si-note"></i>
                                                <span class="nav-main-link-name">2.3 Item</span>
                                                <span class="nav-main-link-badge badge badge-pill badge-warning">3</span>
                                            </a>
                                        </li>
                                        <li class="nav-main-item">
                                            <a class="nav-main-link nav-main-link-submenu" data-toggle="submenu" aria-haspopup="true" aria-expanded="false" href="#">
                                                <span class="nav-main-link-name">Sub Level 3</span>
                                            </a>
                                            <ul class="nav-main-submenu">
                                                <li class="nav-main-item">
                                                    <a class="nav-main-link" href="#">
                                                        <i class="nav-main-link-icon si si-map"></i>
                                                        <span class="nav-main-link-name">3.1 Item</span>
                                                    </a>
                                                </li>
                                                <li class="nav-main-item">
                                                    <a class="nav-main-link" href="#">
                                                        <i class="nav-main-link-icon si si-cup"></i>
                                                        <span class="nav-main-link-name">3.2 Item</span>
                                                    </a>
                                                </li>
                                                <li class="nav-main-item">
                                                    <a class="nav-main-link" href="#">
                                                        <i class="nav-main-link-icon si si-user-female"></i>
                                                        <span class="nav-main-link-name">3.3 Item</span>
                                                    </a>
                                                </li>
                                                <li class="nav-main-item">
                                                    <a class="nav-main-link nav-main-link-submenu" data-toggle="submenu" aria-haspopup="true" aria-expanded="false" href="#">
                                                        <span class="nav-main-link-name">Sub Level 4</span>
                                                    </a>
                                                    <ul class="nav-main-submenu">
                                                        <li class="nav-main-item">
                                                            <a class="nav-main-link" href="#">
                                                                <i class="nav-main-link-icon si si-heart"></i>
                                                                <span class="nav-main-link-name">4.1 Item</span>
                                                            </a>
                                                        </li>
                                                        <li class="nav-main-item">
                                                            <a class="nav-main-link" href="#">
                                                                <i class="nav-main-link-icon si si-magnifier"></i>
                                                                <span class="nav-main-link-name">4.2 Item</span>
                                                            </a>
                                                        </li>
                                                        <li class="nav-main-item">
                                                            <a class="nav-main-link" href="#">
                                                                <i class="nav-main-link-icon si si-microphone"></i>
                                                                <span class="nav-main-link-name">4.3 Item</span>
                                                            </a>
                                                        </li>
                                                    </ul>
                                                </li>
                                            </ul>
                                        </li>
                                    </ul>
                                </li>
                            </ul>
                        </li>
                        <li class="nav-main-heading">Pages</li>
                        <li class="nav-main-item">
                            <a class="nav-main-link nav-main-link-submenu" data-toggle="submenu" aria-haspopup="true" aria-expanded="false" href="#">
                                <i class="nav-main-link-icon si si-docs"></i>
                                <span class="nav-main-link-name">Page Kits</span>
                            </a>
                            <ul class="nav-main-submenu">
                                <li class="nav-main-item">
                                    <a class="nav-main-link nav-main-link-submenu" data-toggle="submenu" aria-haspopup="true" aria-expanded="false" href="#">
                                        <span class="nav-main-link-name">Generic</span>
                                    </a>
                                    <ul class="nav-main-submenu">
                                        <li class="nav-main-item">
                                            <a class="nav-main-link" href="be_pages_generic_blank.html">
                                                <span class="nav-main-link-name">Blank</span>
                                            </a>
                                        </li>
                                        <li class="nav-main-item">
                                            <a class="nav-main-link" href="be_pages_generic_blank_block.html">
                                                <span class="nav-main-link-name">Blank (Block)</span>
                                            </a>
                                        </li>
                                        <li class="nav-main-item">
                                            <a class="nav-main-link" href="be_pages_generic_search.html">
                                                <span class="nav-main-link-name">Search</span>
                                            </a>
                                        </li>
                                        <li class="nav-main-item">
                                            <a class="nav-main-link" href="be_pages_generic_profile.html">
                                                <span class="nav-main-link-name">Profile</span>
                                            </a>
                                        </li>
                                        <li class="nav-main-item">
                                            <a class="nav-main-link" href="be_pages_generic_inbox.html">
                                                <span class="nav-main-link-name">Inbox</span>
                                            </a>
                                        </li>
                                        <li class="nav-main-item">
                                            <a class="nav-main-link" href="be_pages_generic_invoice.html">
                                                <span class="nav-main-link-name">Invoice</span>
                                            </a>
                                        </li>
                                        <li class="nav-main-item">
                                            <a class="nav-main-link" href="be_pages_generic_faq.html">
                                                <span class="nav-main-link-name">FAQ</span>
                                            </a>
                                        </li>
                                        <li class="nav-main-item">
                                            <a class="nav-main-link" href="be_pages_generic_upgrade_plan.html">
                                                <span class="nav-main-link-name">Upgrade Plan</span>
                                            </a>
                                        </li>
                                    </ul>
                                </li>
                                <li class="nav-main-item">
                                    <a class="nav-main-link nav-main-link-submenu" data-toggle="submenu" aria-haspopup="true" aria-expanded="false" href="#">
                                        <span class="nav-main-link-name">Education</span>
                                        <span class="nav-main-link-badge badge badge-pill badge-success">New</span>
                                    </a>
                                    <ul class="nav-main-submenu">
                                        <li class="nav-main-item">
                                            <a class="nav-main-link" href="be_pages_education_dashboard.html">
                                                <span class="nav-main-link-name">Dashboard</span>
                                            </a>
                                        </li>
                                        <li class="nav-main-item">
                                            <a class="nav-main-link" href="be_pages_education_course.html">
                                                <span class="nav-main-link-name">Course</span>
                                            </a>
                                        </li>
                                        <li class="nav-main-item">
                                            <a class="nav-main-link" href="be_pages_education_authors.html">
                                                <span class="nav-main-link-name">Authors</span>
                                            </a>
                                        </li>
                                    </ul>
                                </li>
                                <li class="nav-main-item">
                                    <a class="nav-main-link nav-main-link-submenu" data-toggle="submenu" aria-haspopup="true" aria-expanded="false" href="#">
                                        <span class="nav-main-link-name">Forum</span>
                                        <span class="nav-main-link-badge badge badge-pill badge-success">New</span>
                                    </a>
                                    <ul class="nav-main-submenu">
                                        <li class="nav-main-item">
                                            <a class="nav-main-link" href="be_pages_forum_categories.html">
                                                <span class="nav-main-link-name">Categories</span>
                                            </a>
                                        </li>
                                        <li class="nav-main-item">
                                            <a class="nav-main-link" href="be_pages_forum_topics.html">
                                                <span class="nav-main-link-name">Topics</span>
                                            </a>
                                        </li>
                                        <li class="nav-main-item">
                                            <a class="nav-main-link" href="be_pages_forum_discussion.html">
                                                <span class="nav-main-link-name">Discussion</span>
                                            </a>
                                        </li>
                                    </ul>
                                </li>
                                <li class="nav-main-item">
                                    <a class="nav-main-link nav-main-link-submenu" data-toggle="submenu" aria-haspopup="true" aria-expanded="false" href="#">
                                        <span class="nav-main-link-name">Special</span>
                                    </a>
                                    <ul class="nav-main-submenu">
                                        <li class="nav-main-item">
                                            <a class="nav-main-link" href="op_maintenance.html">
                                                <span class="nav-main-link-name">Maintenance</span>
                                            </a>
                                        </li>
                                        <li class="nav-main-item">
                                            <a class="nav-main-link" href="op_status.html">
                                                <span class="nav-main-link-name">Status</span>
                                            </a>
                                        </li>
                                        <li class="nav-main-item">
                                            <a class="nav-main-link" href="op_coming_soon.html">
                                                <span class="nav-main-link-name">Coming Soon</span>
                                            </a>
                                        </li>
                                        <li class="nav-main-item">
                                            <a class="nav-main-link" href="op_coming_soon_2.html">
                                                <span class="nav-main-link-name">Coming Soon 2</span>
                                            </a>
                                        </li>
                                    </ul>
                                </li>
                            </ul>
                        </li>
                        <li class="nav-main-item">
                            <a class="nav-main-link nav-main-link-submenu" data-toggle="submenu" aria-haspopup="true" aria-expanded="false" href="#">
                                <i class="nav-main-link-icon si si-cup"></i>
                                <span class="nav-main-link-name">Dashboards</span>
                            </a>
                            <ul class="nav-main-submenu">
                                <li class="nav-main-item">
                                    <a class="nav-main-link" href="be_pages_dashboard_all.html">
                                        <span class="nav-main-link-name">All</span>
                                    </a>
                                </li>
                                <li class="nav-main-item">
                                    <a class="nav-main-link" href="db_social.html">
                                        <span class="nav-main-link-name">Social</span>
                                    </a>
                                </li>
                                <li class="nav-main-item">
                                    <a class="nav-main-link" href="db_messages.html">
                                        <span class="nav-main-link-name">Messages</span>
                                    </a>
                                </li>
                                <li class="nav-main-item">
                                    <a class="nav-main-link" href="db_dark.html">
                                        <span class="nav-main-link-name">Dark</span>
                                    </a>
                                </li>
                                <li class="nav-main-item">
                                    <a class="nav-main-link" href="db_minimal.html">
                                        <span class="nav-main-link-name">Minimal</span>
                                    </a>
                                </li>
                                <li class="nav-main-item">
                                    <a class="nav-main-link" href="db_modern.html">
                                        <span class="nav-main-link-name">Modern</span>
                                    </a>
                                </li>
                                <li class="nav-main-item">
                                    <a class="nav-main-link" href="db_classic_boxed.html">
                                        <span class="nav-main-link-name">Classic Boxed</span>
                                    </a>
                                </li>
                                <li class="nav-main-item">
                                    <a class="nav-main-link" href="db_banking.html">
                                        <span class="nav-main-link-name">Banking</span>
                                    </a>
                                </li>
                                <li class="nav-main-item">
                                    <a class="nav-main-link" href="db_crypto.html">
                                        <span class="nav-main-link-name">Crypto</span>
                                    </a>
                                </li>
                                <li class="nav-main-item">
                                    <a class="nav-main-link" href="db_hosting.html">
                                        <span class="nav-main-link-name">Hosting</span>
                                        <span class="nav-main-link-badge badge badge-pill badge-success">New</span>
                                    </a>
                                </li>
                                <li class="nav-main-item">
                                    <a class="nav-main-link" href="db_booking.html">
                                        <span class="nav-main-link-name">Booking</span>
                                        <span class="nav-main-link-badge badge badge-pill badge-success">New</span>
                                    </a>
                                </li>
                                <li class="nav-main-item">
                                    <a class="nav-main-link" href="db_gaming.html">
                                        <span class="nav-main-link-name">Gaming</span>
                                        <span class="nav-main-link-badge badge badge-pill badge-success">New</span>
                                    </a>
                                </li>
                            </ul>
                        </li>
                        <li class="nav-main-item">
                            <a class="nav-main-link nav-main-link-submenu" data-toggle="submenu" aria-haspopup="true" aria-expanded="false" href="#">
                                <i class="nav-main-link-icon si si-users"></i>
                                <span class="nav-main-link-name">Auth</span>
                            </a>
                            <ul class="nav-main-submenu">
                                <li class="nav-main-item">
                                    <a class="nav-main-link" href="be_pages_auth_all.html">
                                        <span class="nav-main-link-name">All</span>
                                    </a>
                                </li>
                                <li class="nav-main-item">
                                    <a class="nav-main-link" href="op_auth_signin.html">
                                        <span class="nav-main-link-name">Sign In</span>
                                    </a>
                                </li>
                                <li class="nav-main-item">
                                    <a class="nav-main-link" href="op_auth_signin_box.html">
                                        <span class="nav-main-link-name">Sign In Box</span>
                                    </a>
                                </li>
                                <li class="nav-main-item">
                                    <a class="nav-main-link" href="op_auth_signup.html">
                                        <span class="nav-main-link-name">Sign Up</span>
                                    </a>
                                </li>
                                <li class="nav-main-item">
                                    <a class="nav-main-link" href="op_auth_signup_box.html">
                                        <span class="nav-main-link-name">Sign Up Box</span>
                                    </a>
                                </li>
                                <li class="nav-main-item">
                                    <a class="nav-main-link" href="op_auth_lock.html">
                                        <span class="nav-main-link-name">Lock Screen</span>
                                    </a>
                                </li>
                                <li class="nav-main-item">
                                    <a class="nav-main-link" href="op_auth_lock_box.html">
                                        <span class="nav-main-link-name">Lock Screen Box</span>
                                    </a>
                                </li>
                                <li class="nav-main-item">
                                    <a class="nav-main-link" href="op_auth_reminder.html">
                                        <span class="nav-main-link-name">Pass Reminder</span>
                                    </a>
                                </li>
                                <li class="nav-main-item">
                                    <a class="nav-main-link" href="op_auth_reminder_box.html">
                                        <span class="nav-main-link-name">Pass Reminder Box</span>
                                    </a>
                                </li>
                            </ul>
                        </li>
                        <li class="nav-main-item">
                            <a class="nav-main-link nav-main-link-submenu" data-toggle="submenu" aria-haspopup="true" aria-expanded="false" href="#">
                                <i class="nav-main-link-icon si si-ghost"></i>
                                <span class="nav-main-link-name">Error</span>
                            </a>
                            <ul class="nav-main-submenu">
                                <li class="nav-main-item">
                                    <a class="nav-main-link" href="be_pages_error_all.html">
                                        <span class="nav-main-link-name">All</span>
                                    </a>
                                </li>
                                <li class="nav-main-item">
                                    <a class="nav-main-link" href="op_error_400.html">
                                        <span class="nav-main-link-name">400</span>
                                    </a>
                                </li>
                                <li class="nav-main-item">
                                    <a class="nav-main-link" href="op_error_401.html">
                                        <span class="nav-main-link-name">401</span>
                                    </a>
                                </li>
                                <li class="nav-main-item">
                                    <a class="nav-main-link" href="op_error_403.html">
                                        <span class="nav-main-link-name">403</span>
                                    </a>
                                </li>
                                <li class="nav-main-item">
                                    <a class="nav-main-link" href="op_error_404.html">
                                        <span class="nav-main-link-name">404</span>
                                    </a>
                                </li>
                                <li class="nav-main-item">
                                    <a class="nav-main-link" href="op_error_500.html">
                                        <span class="nav-main-link-name">500</span>
                                    </a>
                                </li>
                                <li class="nav-main-item">
                                    <a class="nav-main-link" href="op_error_503.html">
                                        <span class="nav-main-link-name">503</span>
                                    </a>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </div>
                <!-- END Side Navigation -->
            </nav>
            <!-- END Sidebar -->

            <!-- Header -->
            <header id="page-header">
                <!-- Header Content -->
                <div class="content-header">
                    <!-- Left Section -->
                    <div>
                        <!-- Toggle Sidebar -->
                        <!-- Layout API, functionality initialized in Template._uiApiLayout()-->
                        <button type="button" class="btn btn-dual mr-1" data-toggle="layout" data-action="sidebar_toggle">
                            <i class="fa fa-fw fa-bars"></i>
                        </button>
                        <!-- END Toggle Sidebar -->

                        <!-- Open Search Section -->
                        <!-- Layout API, functionality initialized in Template._uiApiLayout() -->
                        <button type="button" class="btn btn-dual" data-toggle="layout" data-action="header_search_on">
                            <i class="fa fa-fw fa-search"></i> <span class="ml-1 d-none d-sm-inline-block">Search</span>
                        </button>
                        <!-- END Open Search Section -->
                    </div>
                    <!-- END Left Section -->

                    <!-- Right Section -->
                    <div>
                        <!-- User Dropdown -->
                        <div class="dropdown d-inline-block">
                            <button type="button" class="btn btn-dual" id="page-header-user-dropdown" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <i class="fa fa-fw fa-user d-sm-none"></i>
                                <span class="d-none d-sm-inline-block">Admin</span>
                                <i class="fa fa-fw fa-angle-down ml-1 d-none d-sm-inline-block"></i>
                            </button>
                            <div class="dropdown-menu dropdown-menu-right p-0" aria-labelledby="page-header-user-dropdown">
                                <div class="bg-primary-darker rounded-top font-w600 text-white text-center p-3">
                                    User Options
                                </div>
                                <div class="p-2">
                                    <a class="dropdown-item" href="be_pages_generic_profile.html">
                                        <i class="far fa-fw fa-user mr-1"></i> Profile
                                    </a>
                                    <a class="dropdown-item d-flex align-items-center justify-content-between" href="be_pages_generic_inbox.html">
                                        <span><i class="far fa-fw fa-envelope mr-1"></i> Inbox</span>
                                        <span class="badge badge-primary">3</span>
                                    </a>
                                    <a class="dropdown-item" href="be_pages_generic_invoice.html">
                                        <i class="far fa-fw fa-file-alt mr-1"></i> Invoices
                                    </a>
                                    <div role="separator" class="dropdown-divider"></div>

                                    <!-- Toggle Side Overlay -->
                                    <!-- Layout API, functionality initialized in Template._uiApiLayout() -->
                                    <a class="dropdown-item" href="javascript:void(0)" data-toggle="layout" data-action="side_overlay_toggle">
                                        <i class="far fa-fw fa-building mr-1"></i> Settings
                                    </a>
                                    <!-- END Side Overlay -->

                                    <div role="separator" class="dropdown-divider"></div>
                                    <a class="dropdown-item" href="op_auth_signin.html">
                                        <i class="far fa-fw fa-arrow-alt-circle-left mr-1"></i> Sign Out
                                    </a>
                                </div>
                            </div>
                        </div>
                        <!-- END User Dropdown -->

                        <!-- Notifications Dropdown -->
                        <div class="dropdown d-inline-block">
                            <button type="button" class="btn btn-dual" id="page-header-notifications-dropdown" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <i class="fa fa-fw fa-bell"></i>
                                <span class="badge badge-secondary badge-pill">5</span>
                            </button>
                            <div class="dropdown-menu dropdown-menu-lg dropdown-menu-right p-0" aria-labelledby="page-header-notifications-dropdown">
                                <div class="bg-primary-darker rounded-top font-w600 text-white text-center p-3">
                                    Notifications
                                </div>
                                <ul class="nav-items my-2">
                                    <li>
                                        <a class="text-dark media py-2" href="javascript:void(0)">
                                            <div class="mx-3">
                                                <i class="fa fa-fw fa-check-circle text-success"></i>
                                            </div>
                                            <div class="media-body font-size-sm pr-2">
                                                <div class="font-w600">App was updated to v5.6!</div>
                                                <div class="text-muted font-italic">3 min ago</div>
                                            </div>
                                        </a>
                                    </li>
                                    <li>
                                        <a class="text-dark media py-2" href="javascript:void(0)">
                                            <div class="mx-3">
                                                <i class="fa fa-fw fa-user-plus text-info"></i>
                                            </div>
                                            <div class="media-body font-size-sm pr-2">
                                                <div class="font-w600">New Subscriber was added! You now have 2580!</div>
                                                <div class="text-muted font-italic">10 min ago</div>
                                            </div>
                                        </a>
                                    </li>
                                    <li>
                                        <a class="text-dark media py-2" href="javascript:void(0)">
                                            <div class="mx-3">
                                                <i class="fa fa-fw fa-times-circle text-danger"></i>
                                            </div>
                                            <div class="media-body font-size-sm pr-2">
                                                <div class="font-w600">Server backup failed to complete!</div>
                                                <div class="text-muted font-italic">30 min ago</div>
                                            </div>
                                        </a>
                                    </li>
                                    <li>
                                        <a class="text-dark media py-2" href="javascript:void(0)">
                                            <div class="mx-3">
                                                <i class="fa fa-fw fa-exclamation-circle text-warning"></i>
                                            </div>
                                            <div class="media-body font-size-sm pr-2">
                                                <div class="font-w600">You are running out of space. Please consider upgrading your plan.</div>
                                                <div class="text-muted font-italic">1 hour ago</div>
                                            </div>
                                        </a>
                                    </li>
                                    <li>
                                        <a class="text-dark media py-2" href="javascript:void(0)">
                                            <div class="mx-3">
                                                <i class="fa fa-fw fa-plus-circle text-primary"></i>
                                            </div>
                                            <div class="media-body font-size-sm pr-2">
                                                <div class="font-w600">New Sale! + $30</div>
                                                <div class="text-muted font-italic">2 hours ago</div>
                                            </div>
                                        </a>
                                    </li>
                                </ul>
                                <div class="p-2 border-top">
                                    <a class="btn btn-light btn-block text-center" href="javascript:void(0)">
                                        <i class="fa fa-fw fa-eye mr-1"></i> View All
                                    </a>
                                </div>
                            </div>
                        </div>
                        <!-- END Notifications Dropdown -->

                        <!-- Toggle Side Overlay -->
                        <!-- Layout API, functionality initialized in Template._uiApiLayout() -->
                        <button type="button" class="btn btn-dual" data-toggle="layout" data-action="side_overlay_toggle">
                            <i class="far fa-fw fa-list-alt"></i>
                        </button>
                        <!-- END Toggle Side Overlay -->
                    </div>
                    <!-- END Right Section -->
                </div>
                <!-- END Header Content -->

                <!-- Header Search -->
                <div id="page-header-search" class="overlay-header bg-primary">
                    <div class="content-header">
                        <form class="w-100" action="be_pages_generic_search.html" method="post">
                            <div class="input-group">
                                <div class="input-group-prepend">
                                    <!-- Layout API, functionality initialized in Template._uiApiLayout() -->
                                    <button type="button" class="btn btn-primary" data-toggle="layout" data-action="header_search_off">
                                        <i class="fa fa-fw fa-times-circle"></i>
                                    </button>
                                </div>
                                <input type="text" class="form-control border-0" placeholder="Search or hit ESC.." id="page-header-search-input" name="page-header-search-input">
                            </div>
                        </form>
                    </div>
                </div>
                <!-- END Header Search -->

                <!-- Header Loader -->
                <!-- Please check out the Loaders page under Components category to see examples of showing/hiding it -->
                <div id="page-header-loader" class="overlay-header bg-primary-darker">
                    <div class="content-header">
                        <div class="w-100 text-center">
                            <i class="fa fa-fw fa-2x fa-sun fa-spin text-white"></i>
                        </div>
                    </div>
                </div>
                <!-- END Header Loader -->
            </header>
            <!-- END Header -->

            <!-- Main Container -->
            <main id="main-container">

                <!-- Hero -->
                <div class="bg-image" style="background-image: url('resources/media/various/bg_dashboard.jpg');">
                    <div class="bg-white-90">
                        <div class="content content-full">
                            <div class="row">
                                <div class="col-md-6 d-md-flex align-items-md-center">
                                    <div class="py-4 py-md-0 text-center text-md-left invisible" data-toggle="appear">
                                        <h1 class="font-size-h2 mb-2">Dashboard</h1>
                                        <h2 class="font-size-lg font-w400 text-muted mb-0">Today is a great one!</h2>
                                    </div>
                                </div>
                                <div class="col-md-6 d-md-flex align-items-md-center">
                                    <div class="row w-100 text-center">
                                        <div class="col-6 col-xl-4 offset-xl-4 invisible" data-toggle="appear" data-timeout="300">
                                            <p class="font-size-h3 font-w600 text-body-color-dark mb-0">
                                                67
                                            </p>
                                            <p class="font-size-sm font-w700 text-uppercase mb-0">
                                                <i class="far fa-chart-bar text-muted mr-1"></i> Sales
                                            </p>
                                        </div>
                                        <div class="col-6 col-xl-4 invisible" data-toggle="appear" data-timeout="600">
                                            <p class="font-size-h3 font-w600 text-body-color-dark mb-0">
                                                $980
                                            </p>
                                            <p class="font-size-sm font-w700 text-uppercase mb-0">
                                                <i class="far fa-chart-bar text-muted mr-1"></i> Earnings
                                            </p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- END Hero -->

                <!-- Page Content -->
                <div class="content">
                    <!-- Quick Stats -->
                    <!-- jQuery Sparkline (.js-sparkline class is initialized in Helpers.sparkline() -->
                    <!-- For more info and examples you can check out http://omnipotent.net/jquery.sparkline/#s-about -->
                    <div class="row">
                        <div class="col-md-6 col-xl-3 invisible" data-toggle="appear">
                            <a class="block block-rounded block-link-pop" href="javascript:void(0)">
                                <div class="block-content block-content-full d-flex align-items-center justify-content-between">
                                    <div>
                                        <!-- Sparkline Dashboard Users Container -->
                                        <span class="js-sparkline" data-type="line"
                                              data-points="[340,330,360,340,360,350,370,360]"
                                              data-width="90px"
                                              data-height="40px"
                                              data-line-color="#82b54b"
                                              data-fill-color="transparent"
                                              data-spot-color="transparent"
                                              data-min-spot-color="transparent"
                                              data-max-spot-color="transparent"
                                              data-highlight-spot-color="#82b54b"
                                              data-highlight-line-color="#82b54b"
                                              data-tooltip-suffix="Users"></span>
                                    </div>
                                    <div class="ml-3 text-right">
                                        <p class="text-muted mb-0">
                                            Users
                                        </p>
                                        <p class="font-size-h3 font-w300 mb-0">
                                            +350
                                        </p>
                                    </div>
                                </div>
                            </a>
                        </div>
                        <div class="col-md-6 col-xl-3 invisible" data-toggle="appear" data-timeout="200">
                            <a class="block block-rounded block-link-pop" href="javascript:void(0)">
                                <div class="block-content block-content-full d-flex align-items-center justify-content-between">
                                    <div>
                                        <!-- Sparkline Dashboard Tickets Container -->
                                        <span class="js-sparkline" data-type="line"
                                              data-points="[21,17,19,25,24,25,18,27]"
                                              data-width="90px"
                                              data-height="40px"
                                              data-line-color="#e04f1a"
                                              data-fill-color="transparent"
                                              data-spot-color="transparent"
                                              data-min-spot-color="transparent"
                                              data-max-spot-color="transparent"
                                              data-highlight-spot-color="#e04f1a"
                                              data-highlight-line-color="#e04f1a"
                                              data-tooltip-suffix="Tickets"></span>
                                    </div>
                                    <div class="ml-3 text-right">
                                        <p class="text-muted mb-0">
                                            Tickets
                                        </p>
                                        <p class="font-size-h3 font-w300 mb-0">
                                            28
                                        </p>
                                    </div>
                                </div>
                            </a>
                        </div>
                        <div class="col-md-6 col-xl-3 invisible" data-toggle="appear" data-timeout="400">
                            <a class="block block-rounded block-link-pop" href="javascript:void(0)">
                                <div class="block-content block-content-full d-flex align-items-center justify-content-between">
                                    <div>
                                        <!-- Sparkline Dashboard Projects Container -->
                                        <span class="js-sparkline" data-type="line"
                                              data-points="[7,9,5,2,3,4,8,3]"
                                              data-width="90px"
                                              data-height="40px"
                                              data-line-color="#3c90df"
                                              data-fill-color="transparent"
                                              data-spot-color="transparent"
                                              data-min-spot-color="transparent"
                                              data-max-spot-color="transparent"
                                              data-highlight-spot-color="#3c90df"
                                              data-highlight-line-color="#3c90df"
                                              data-tooltip-suffix="Projects"></span>
                                    </div>
                                    <div class="ml-3 text-right">
                                        <p class="text-muted mb-0">
                                            Projects
                                        </p>
                                        <p class="font-size-h3 font-w300 mb-0">
                                            6
                                        </p>
                                    </div>
                                </div>
                            </a>
                        </div>
                        <div class="col-md-6 col-xl-3 invisible" data-toggle="appear" data-timeout="600">
                            <a class="block block-rounded block-link-pop" href="javascript:void(0)">
                                <div class="block-content block-content-full d-flex align-items-center justify-content-between">
                                    <div>
                                        <!-- Sparkline Dashboard Sales Container -->
                                        <span class="js-sparkline" data-type="line"
                                              data-points="[68,25,36,62,59,80,75,89]"
                                              data-width="90px"
                                              data-height="40px"
                                              data-line-color="#343a40"
                                              data-fill-color="transparent"
                                              data-spot-color="transparent"
                                              data-min-spot-color="transparent"
                                              data-max-spot-color="transparent"
                                              data-highlight-spot-color="#343a40"
                                              data-highlight-line-color="#343a40"
                                              data-tooltip-suffix="Sales"></span>
                                    </div>
                                    <div class="ml-3 text-right">
                                        <p class="text-muted mb-0">
                                            Sales
                                        </p>
                                        <p class="font-size-h3 font-w300 mb-0">
                                            +89
                                        </p>
                                    </div>
                                </div>
                            </a>
                        </div>
                    </div>
                    <!-- END Quick Stats -->

                    <!-- Main Chart -->
                    <div class="block block-rounded block-mode-loading-refresh invisible" data-toggle="appear">
                        <div class="block-header block-header-default">
                            <h3 class="block-title">Earnings</h3>
                            <div class="block-options">
                                <div class="btn-group btn-group-sm btn-group-toggle mr-2" data-toggle="buttons" role="group" aria-label="Earnings Select Date Group">
                                    <label class="btn btn-light" data-toggle="dashboard-chart-set-week">
                                        <input type="radio" name="dashboard-chart-options" id="dashboard-chart-options-week"> Week
                                    </label>
                                    <label class="btn btn-light" data-toggle="dashboard-chart-set-month">
                                        <input type="radio" name="dashboard-chart-options" id="dashboard-chart-options-month"> Month
                                    </label>
                                    <label class="btn btn-light active" data-toggle="dashboard-chart-set-year">
                                        <input type="radio" name="dashboard-chart-options" id="dashboard-chart-options-year" checked> Year
                                    </label>
                                </div>
                                <button type="button" class="btn-block-option align-middle" data-toggle="block-option" data-action="state_toggle" data-action-mode="demo">
                                    <i class="si si-refresh"></i>
                                </button>
                            </div>
                        </div>
                        <div class="block-content block-content-full overflow-hidden">
                            <div class="pull-x pull-b">
                                <!-- Chart.js Dashboard Earnings Container -->
                                <canvas class="js-chartjs-dashboard-earnings" style="height: 340px;"></canvas>
                            </div>
                        </div>
                    </div>
                    <!-- END Main Chart -->

                    <!-- Users and Purchases -->
                    <div class="row row-deck">
                        <div class="col-xl-6 invisible" data-toggle="appear">
                            <!-- Users -->
                            <div class="block block-rounded block-mode-loading-refresh">
                                <div class="block-header block-header-default">
                                    <h3 class="block-title">Users</h3>
                                    <div class="block-options">
                                        <button type="button" class="btn-block-option" data-toggle="block-option" data-action="state_toggle" data-action-mode="demo">
                                            <i class="si si-refresh"></i>
                                        </button>
                                        <button type="button" class="btn-block-option">
                                            <i class="si si-cloud-download"></i>
                                        </button>
                                        <div class="dropdown">
                                            <button type="button" class="btn-block-option" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                                <i class="si si-wrench"></i>
                                            </button>
                                            <div class="dropdown-menu dropdown-menu-right">
                                                <a class="dropdown-item" href="javascript:void(0)">
                                                    <i class="far fa-fw fa-user mr-1"></i> New Users
                                                </a>
                                                <a class="dropdown-item" href="javascript:void(0)">
                                                    <i class="far fa-fw fa-bookmark mr-1"></i> VIP Users
                                                </a>
                                                <div role="separator" class="dropdown-divider"></div>
                                                <a class="dropdown-item" href="javascript:void(0)">
                                                    <i class="fa fa-fw fa-pencil-alt"></i> Manage
                                                </a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="block-content block-content-full block-content-sm bg-body-dark">
                                    <form action="be_pages_dashboard.html" method="post" onsubmit="return false;">
                                        <input type="text" class="form-control form-control-alt" placeholder="Search Users..">
                                    </form>
                                </div>
                                <div class="block-content">
                                    <table class="table table-striped table-hover table-borderless table-vcenter font-size-sm">
                                        <thead>
                                            <tr class="text-uppercase">
                                                <th class="font-w700 text-center" style="width: 120px;">Avatar</th>
                                                <th class="font-w700">Name</th>
                                                <th class="d-none d-sm-table-cell font-w700">Access</th>
                                                <th class="font-w700 text-center" style="width: 60px;"></th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <td class="text-center">
                                                    <img class="img-avatar img-avatar32 img-avatar-thumb" src="resources/media/avatars/avatar4.jpg" alt="">
                                                </td>
                                                <td>
                                                    <div class="font-w600 font-size-base">Lori Moore</div>
                                                    <div class="text-muted">carol@example.com</div>
                                                </td>
                                                <td class="d-none d-sm-table-cell font-size-base">
                                                    <span class="badge badge-dark">VIP</span>
                                                </td>
                                                <td class="text-center">
                                                    <a href="javascript:void(0)" data-toggle="tooltip" data-placement="left" title="Edit User">
                                                        <i class="fa fa-fw fa-pencil-alt"></i>
                                                    </a>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td class="text-center">
                                                    <img class="img-avatar img-avatar32 img-avatar-thumb" src="resources/media/avatars/avatar10.jpg" alt="">
                                                </td>
                                                <td>
                                                    <div class="font-w600 font-size-base">Brian Stevens</div>
                                                    <div class="text-muted">smith@example.com</div>
                                                </td>
                                                <td class="d-none d-sm-table-cell font-size-base">
                                                    <span class="badge badge-secondary">Pro</span>
                                                </td>
                                                <td class="text-center">
                                                    <a href="javascript:void(0)" data-toggle="tooltip" data-placement="left" title="Edit User">
                                                        <i class="fa fa-fw fa-pencil-alt"></i>
                                                    </a>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td class="text-center">
                                                    <img class="img-avatar img-avatar32 img-avatar-thumb" src="resources/media/avatars/avatar16.jpg" alt="">
                                                </td>
                                                <td>
                                                    <div class="font-w600 font-size-base">Scott Young</div>
                                                    <div class="text-muted">john@example.com</div>
                                                </td>
                                                <td class="d-none d-sm-table-cell font-size-base">
                                                    <span class="badge badge-dark">VIP</span>
                                                </td>
                                                <td class="text-center">
                                                    <a href="javascript:void(0)" data-toggle="tooltip" data-placement="left" title="Edit User">
                                                        <i class="fa fa-fw fa-pencil-alt"></i>
                                                    </a>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td class="text-center">
                                                    <img class="img-avatar img-avatar32 img-avatar-thumb" src="resources/media/avatars/avatar6.jpg" alt="">
                                                </td>
                                                <td>
                                                    <div class="font-w600 font-size-base">Andrea Gardner</div>
                                                    <div class="text-muted">lori@example.com</div>
                                                </td>
                                                <td class="d-none d-sm-table-cell font-size-base">
                                                    <span class="badge badge-secondary">Pro</span>
                                                </td>
                                                <td class="text-center">
                                                    <a href="javascript:void(0)" data-toggle="tooltip" data-placement="left" title="Edit User">
                                                        <i class="fa fa-fw fa-pencil-alt"></i>
                                                    </a>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td class="text-center">
                                                    <img class="img-avatar img-avatar32 img-avatar-thumb" src="resources/media/avatars/avatar15.jpg" alt="">
                                                </td>
                                                <td>
                                                    <div class="font-w600 font-size-base">Carl Wells</div>
                                                    <div class="text-muted">jack@example.com</div>
                                                </td>
                                                <td class="d-none d-sm-table-cell font-size-base">
                                                    <span class="badge badge-success">Free</span>
                                                </td>
                                                <td class="text-center">
                                                    <a href="javascript:void(0)" data-toggle="tooltip" data-placement="left" title="Edit User">
                                                        <i class="fa fa-fw fa-pencil-alt"></i>
                                                    </a>
                                                </td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                            <!-- END Users -->
                        </div>
                        <div class="col-xl-6 invisible" data-toggle="appear" data-timeout="200">
                            <!-- Purchases -->
                            <div class="block block-rounded block-mode-loading-refresh">
                                <div class="block-header block-header-default">
                                    <h3 class="block-title">Purchases</h3>
                                    <div class="block-options">
                                        <button type="button" class="btn-block-option" data-toggle="block-option" data-action="state_toggle" data-action-mode="demo">
                                            <i class="si si-refresh"></i>
                                        </button>
                                        <button type="button" class="btn-block-option">
                                            <i class="si si-cloud-download"></i>
                                        </button>
                                        <div class="dropdown">
                                            <button type="button" class="btn-block-option" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                                <i class="si si-wrench"></i>
                                            </button>
                                            <div class="dropdown-menu dropdown-menu-right">
                                                <a class="dropdown-item" href="javascript:void(0)">
                                                    <i class="fa fa-fw fa-sync fa-spin text-warning mr-1"></i> Pending
                                                </a>
                                                <a class="dropdown-item" href="javascript:void(0)">
                                                    <i class="far fa-fw fa-times-circle text-danger mr-1"></i> Cancelled
                                                </a>
                                                <a class="dropdown-item" href="javascript:void(0)">
                                                    <i class="far fa-fw fa-check-circle text-success mr-1"></i> Cancelled
                                                </a>
                                                <div role="separator" class="dropdown-divider"></div>
                                                <a class="dropdown-item" href="javascript:void(0)">
                                                    <i class="fa fa-fw fa-eye mr-1"></i> View All
                                                </a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="block-content block-content-full block-content-sm bg-body-dark">
                                    <form action="be_pages_dashboard.html" method="post" onsubmit="return false;">
                                        <input type="text" class="form-control form-control-alt" placeholder="Search Purchases..">
                                    </form>
                                </div>
                                <div class="block-content">
                                    <table class="table table-striped table-hover table-borderless table-vcenter font-size-sm">
                                        <thead>
                                            <tr class="text-uppercase">
                                                <th class="font-w700">Product</th>
                                                <th class="d-none d-sm-table-cell font-w700">Date</th>
                                                <th class="font-w700">State</th>
                                                <th class="d-none d-sm-table-cell font-w700 text-right" style="width: 120px;">Price</th>
                                                <th class="font-w700 text-center" style="width: 60px;"></th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <td>
                                                    <span class="font-w600">iPhone X</span>
                                                </td>
                                                <td class="d-none d-sm-table-cell">
                                                    <span class="font-size-sm text-muted">today</span>
                                                </td>
                                                <td>
                                                    <span class="font-w600 text-warning">Pending..</span>
                                                </td>
                                                <td class="d-none d-sm-table-cell text-right">
                                                    $999,99
                                                </td>
                                                <td class="text-center">
                                                    <a href="javascript:void(0)" data-toggle="tooltip" data-placement="left" title="Manage">
                                                        <i class="fa fa-fw fa-pencil-alt"></i>
                                                    </a>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <span class="font-w600">MacBook Pro 15"</span>
                                                </td>
                                                <td class="d-none d-sm-table-cell">
                                                    <span class="font-size-sm text-muted">today</span>
                                                </td>
                                                <td>
                                                    <span class="font-w600 text-warning">Pending..</span>
                                                </td>
                                                <td class="d-none d-sm-table-cell text-right">
                                                    $2.299,00
                                                </td>
                                                <td class="text-center">
                                                    <a href="javascript:void(0)" data-toggle="tooltip" data-placement="left" title="Manage">
                                                        <i class="fa fa-fw fa-pencil-alt"></i>
                                                    </a>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <span class="font-w600">Nvidia GTX 1080 Ti</span>
                                                </td>
                                                <td class="d-none d-sm-table-cell">
                                                    <span class="font-size-sm text-muted">today</span>
                                                </td>
                                                <td>
                                                    <span class="font-w600 text-warning">Pending..</span>
                                                </td>
                                                <td class="d-none d-sm-table-cell text-right">
                                                    $1200,00
                                                </td>
                                                <td class="text-center">
                                                    <a href="javascript:void(0)" data-toggle="tooltip" data-placement="left" title="Manage">
                                                        <i class="fa fa-fw fa-pencil-alt"></i>
                                                    </a>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <span class="font-w600">Playstation 4 Pro</span>
                                                </td>
                                                <td class="d-none d-sm-table-cell">
                                                    <span class="font-size-sm text-muted">today</span>
                                                </td>
                                                <td>
                                                    <span class="font-w600 text-danger">Cancelled</span>
                                                </td>
                                                <td class="d-none d-sm-table-cell text-right">
                                                    $399,00
                                                </td>
                                                <td class="text-center">
                                                    <a href="javascript:void(0)" data-toggle="tooltip" data-placement="left" title="Manage">
                                                        <i class="fa fa-fw fa-pencil-alt"></i>
                                                    </a>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <span class="font-w600">Nintendo Switch</span>
                                                </td>
                                                <td class="d-none d-sm-table-cell">
                                                    <span class="font-size-sm text-muted">yesterday</span>
                                                </td>
                                                <td>
                                                    <span class="font-w600 text-success">Completed</span>
                                                </td>
                                                <td class="d-none d-sm-table-cell text-right">
                                                    $349,00
                                                </td>
                                                <td class="text-center">
                                                    <a href="javascript:void(0)" data-toggle="tooltip" data-placement="left" title="Manage">
                                                        <i class="fa fa-fw fa-pencil-alt"></i>
                                                    </a>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <span class="font-w600">iPhone X</span>
                                                </td>
                                                <td class="d-none d-sm-table-cell">
                                                    <span class="font-size-sm text-muted">yesterday</span>
                                                </td>
                                                <td>
                                                    <span class="font-w600 text-success">Completed</span>
                                                </td>
                                                <td class="d-none d-sm-table-cell text-right">
                                                    $999,00
                                                </td>
                                                <td class="text-center">
                                                    <a href="javascript:void(0)" data-toggle="tooltip" data-placement="left" title="Manage">
                                                        <i class="fa fa-fw fa-pencil-alt"></i>
                                                    </a>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <span class="font-w600">Echo Dot</span>
                                                </td>
                                                <td class="d-none d-sm-table-cell">
                                                    <span class="font-size-sm text-muted">yesterday</span>
                                                </td>
                                                <td>
                                                    <span class="font-w600 text-success">Completed</span>
                                                </td>
                                                <td class="d-none d-sm-table-cell text-right">
                                                    $39,99
                                                </td>
                                                <td class="text-center">
                                                    <a href="javascript:void(0)" data-toggle="tooltip" data-placement="left" title="Manage">
                                                        <i class="fa fa-fw fa-pencil-alt"></i>
                                                    </a>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <span class="font-w600">Xbox One X</span>
                                                </td>
                                                <td class="d-none d-sm-table-cell">
                                                    <span class="font-size-sm text-muted">yesterday</span>
                                                </td>
                                                <td>
                                                    <span class="font-w600 text-success">Completed</span>
                                                </td>
                                                <td class="d-none d-sm-table-cell text-right">
                                                    $499,00
                                                </td>
                                                <td class="text-center">
                                                    <a href="javascript:void(0)" data-toggle="tooltip" data-placement="left" title="Manage">
                                                        <i class="fa fa-fw fa-pencil-alt"></i>
                                                    </a>
                                                </td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                            <!-- END Purchases -->
                        </div>
                    </div>
                    <!-- END Users and Purchases -->
                </div>
                <!-- END Page Content -->

            </main>
            <!-- END Main Container -->

            <!-- Footer -->
            <footer id="page-footer" class="bg-body-light">
                <div class="content py-0">
                    <div class="row font-size-sm">
                        <div class="col-sm-6 order-sm-2 mb-1 mb-sm-0 text-center text-sm-right">
                            Crafted with <i class="fa fa-heart text-danger"></i> by <a class="font-w600" href="https://goo.gl/vNS3I" target="_blank">pixelcave</a>
                        </div>
                        <div class="col-sm-6 order-sm-1 text-center text-sm-left">
                            <a class="font-w600" href="https://goo.gl/mDBqx1" target="_blank">Dashmix 1.2</a> &copy; <span data-toggle="year-copy">2018</span>
                        </div>
                    </div>
                </div>
            </footer>
            <!-- END Footer -->
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
        <script src="resources/js/dashmix.core.min.js"></script>

        <!--
            Dashmix JS

            Custom functionality including Blocks/Layout API as well as other vital and optional helpers
            webpack is putting everything together at resources/_es6/main/app.js
        -->
        <script src="resources/js/dashmix.app.min.js"></script>

        <!-- Page JS Plugins -->
        <script src="resources/js/plugins/jquery-sparkline/jquery.sparkline.min.js"></script>
        <script src="resources/js/plugins/chart.js/Chart.bundle.min.js"></script>

        <!-- Page JS Code -->
        <script src="resources/js/pages/be_pages_dashboard.min.js"></script>

        <!-- Page JS Helpers (jQuery Sparkline plugin) -->
        <script>jQuery(function(){ Dashmix.helpers('sparkline'); });</script>
    </body>
</html>