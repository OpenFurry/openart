<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
    <head>
        <title>OpenFurry - <g:layoutTitle default="OpenFurry" /></title>
        <link rel="stylesheet" type="text/css" href="${resource(dir:'css',file:'main.css')}" />
        <g:isLoggedIn>
        <g:if test="${loggedInUserInfo(field: 'preferedTheme')}">
        <link rel="stylesheet" type="text/css" href="${resource(dir: 'themes/' + loggedInUserInfo(field: 'preferedTheme'), file: 'main.css')}" />
        </g:if>
        </g:isLoggedIn>
        <link rel="shortcut icon" href="${resource(dir:'images',file:'favicon.ico')}" type="image/x-icon" />
        <g:layoutHead />
        <script src="http://www.google.com/jsapi"></script>
        <script type="text/javascript">
            google.load("jquery", "1.4.2");
            google.load("jqueryui", "1.8.2");
        </script>
        <script type="text/javascript">
            $(function() {
                $('.hide').hide();
            });
        </script>
    </head>
    <body>
        <div id="header">
            <img src="${resource(dir: 'images', file: 'of-logo.png')}" align="left" style="margin: 1px;" alt="Welcome to OpenFurry" />
            <h1 style="display: none">OpenFurry</h1>
            <div id="usercontrol">
                <g:isLoggedIn>
                    <g:message code="openfurry.display.usercontrol.welcomeUser" args="${[loggedInUserInfo(field: 'userRealName')]}" />
                    <ul>
                        <li><g:link controller="inbox"><g:message code="openfurry.display.usercontrol.inbox" default="Inbox" /></g:link></li>
                        <li><g:link controller="watch"><g:message code="openfurry.display.usercontrol.watchlist" default="Watch list" /></g:link></li>
                        <li><g:link controller="submit"><g:message code="openfurry.display.usercontrol.submit" default="Submit" /></g:link></li>
                        <li><g:link controller="profile"><g:message code="openfurry.display.usercontrol.profile" default="Profile" /></g:link></li>
                        <li><g:link controller="logout"><g:message code="openfurry.display.usercontrol.logout" default="Log out" /></g:link></li>
                    </ul>
                </g:isLoggedIn>
                <g:isNotLoggedIn>
                    <g:message code="openfurry.display.usercontrol.welcomeUser" args="${['guest']}" />
                    <ul>
                        <li><g:link controller="login"><g:message code="openfurry.display.usercontrol.login" default="Log in" /></g:link></li>
                        <li><g:link controller="register"><g:message code="openfurry.display.usercontrol.register" default="Register" /></g:link></li>
                    </ul>
                </g:isNotLoggedIn>
            </div>
        </div>
        <div id="spinner" class="spinner" style="display:none;">
            <img src="${resource(dir:'images',file:'spinner.gif')}" alt="${message(code:'spinner.alt',default:'Loading...')}" />
        </div>
        <div id="content">
            <!-- Navigation -->
            <div id="navigation">
                <ul>
                    <li><a href="${createLink(uri: '/')}"><img src="${resource(dir: 'images/skin', file: 'house.png')}" alt="Home" /> <g:message code="openfurry.display.navigation.home" default="Home" /></a></li>
                    <li><g:link controller="tag"><g:message code="openfurry.display.navigation.tags" default="Tags" /></g:link></li>
                    <li><g:link controller="category"><g:message code="openfurry.display.navigation.categories" default="Categories" /></g:link></li>
                    <li><g:link controller="species"><g:message code="openfurry.display.navigation.species" default="Species" /></g:link></li>
                    <li><g:link controller="search"><g:message code="openfurry.display.navigation.search" default="Search" /></g:link></li>
                    <li><g:link controller="group"><g:message code="openfurry.display.navigation.groups" default="Groups and events" /></g:link></li>
                    <li><g:link controller="market"><g:message code="openfurry.display.navigation.market" default="Market" /></g:link></li>
                    <li><g:link controller="issue"><g:message code="openfurry.display.navigation.issues" default="Issues" /></g:link></li>
                </ul>
            </div>

            <!-- Message of the day -->
            <div id="motd">
                <ul>
                    <g:each in="${openfurry.MOTD.findAllByActive(true)}"><li>${it.content}</li></g:each>
                </ul>
            </div>

            <!-- Page title -->
            <h2 style="clear: both">${pageProperty(name: 'title').replaceAll('OpenFurry - ', '')}</h2>

            <!-- Messages -->
            <div id="messages">
                <g:if test="${flash.transact}">
                <div class="transact">
                    <g:message code="${flash.transact}" args="${flash.transactArgs}" default="${flash.transactDefault}" />
                </div>
                </g:if>
                <of:messagesForUser />
            </div>

            <!-- Content -->
            <g:layoutBody />
        </div>
        <div id="footer">
            <p>An <a href="http://mjs-svc.com">MJS Services</a> project | Powered by <a href="http://grails.org">Grails</a><br />
                <g:link controller="flatpage" action="show" params="[slug: 'ip']">Intellectual Property Information</g:link></p>
        </div>
    </body>
</html>
