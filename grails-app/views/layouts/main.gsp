<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
    <head>
        <title>OpenArt - <g:layoutTitle default="OpenArt" /></title>
        <link rel="stylesheet" type="text/css" href="${resource(dir:'css',file:'main.css')}" />
        <g:isLoggedIn>
        <g:if test="${loggedInUserInfo(field: 'preferedTheme')}">
        <link rel="stylesheet" type="text/css" href="${resource(dir: 'themes/' + loggedInUserInfo(field: 'preferedTheme'), file: 'main.css')}" />
        </g:if>
        </g:isLoggedIn>
        <link rel="shortcut icon" href="${resource(dir:'images',file:'favicon.ico')}" type="image/x-icon" />
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
        <g:layoutHead />
    </head>
    <body>
        <div id="header">
            <img src="${resource(dir: 'images', file: 'of-logo.png')}" align="left" style="margin: 1px;" alt="Welcome to OpenArt" />
            <h1 style="display: none">OpenArt</h1>
            <div id="usercontrol">
                <g:isLoggedIn>
                    <g:message code="openart.display.usercontrol.welcomeUser" args="${[loggedInUserInfo(field: 'userRealName')]}" />
                    <ul>
                        <li><g:link controller="inbox"><g:message code="openart.display.usercontrol.inbox" default="Inbox" /></g:link></li>
                        <li><g:link controller="watch"><g:message code="openart.display.usercontrol.watchlist" default="Watch list" /></g:link></li>
                        <li><g:link controller="submit"><g:message code="openart.display.usercontrol.submit" default="Submit" /></g:link></li>
                        <li><g:link controller="profile"><g:message code="openart.display.usercontrol.profile" default="Profile" /></g:link></li>
                        <li><g:link controller="logout"><g:message code="openart.display.usercontrol.logout" default="Log out" /></g:link></li>
                    </ul>
                </g:isLoggedIn>
                <g:isNotLoggedIn>
                    <g:message code="openart.display.usercontrol.welcomeUser" args="${['guest']}" />
                    <ul>
                        <li><g:link controller="login"><g:message code="openart.display.usercontrol.login" default="Log in" /></g:link></li>
                        <li><g:link controller="register"><g:message code="openart.display.usercontrol.register" default="Register" /></g:link></li>
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
                    <li><a href="${createLink(uri: '/')}"><img src="${resource(dir: 'images/skin', file: 'house.png')}" alt="Home" /> <g:message code="openart.display.navigation.home" default="Home" /></a></li>
                    <li><g:link controller="tag"><g:message code="openart.display.navigation.tags" default="Tags" /></g:link></li>
                    <li><g:link controller="category"><g:message code="openart.display.navigation.categories" default="Categories" /></g:link></li>
                    <li><g:link controller="species"><g:message code="openart.display.navigation.species" default="Species" /></g:link></li>
                    <li><g:link controller="search"><g:message code="openart.display.navigation.search" default="Search" /></g:link></li>
                    <li><g:link controller="group"><g:message code="openart.display.navigation.groups" default="Groups and events" /></g:link></li>
                    <!--<li><g:link controller="market"><img src="${resource(dir: 'images/skin', file: 'database_table.png')}" alt="Market" /> <g:message code="openart.display.navigation.market" default="Market" /></g:link></li>-->
                    <li><g:link controller="issue"><img src="${resource(dir: 'images/skin', file: 'exclamation.png')}" alt="issues" /> <g:message code="openart.display.navigation.issues" default="Issues" /></g:link></li>
                </ul>
            </div>

            <!-- Message of the day -->
            <div id="motd">
                <ul>
                    <g:each in="${com.mjs_svc.openart.MOTD.findAllByActive(true)}"><li>${it.content}</li></g:each>
                </ul>
            </div>

            <!-- Page title -->
            <h2 style="clear: both">${pageProperty(name: 'title').replaceAll('OpenArt - ', '')}</h2>

            <!-- Messages -->
            <div id="messages">
                <g:if test="${flash.transact}">
                <div class="transact">
                    <g:message code="${flash.transact}" args="${flash.transactArgs}" default="${flash.transactDefault}" />
                </div>
                </g:if>
                <g:isLoggedIn>
                    <of:linking noImages="true"><of:messagesForUser /></of:linking>
                </g:isLoggedIn>
            </div>

            <!-- Content -->
            <g:layoutBody />
        </div>
        <div id="footer">
            <p>OpenArt v${grailsApplication.config.openart.version.number} - "<g:message code="openart.version.${grailsApplication.config.openart.version.number}" default="${grailsApplication.config.openart.version.name.english}" /> (<em>${grailsApplication.config.openart.version.name.latin}</em>)"</p>
            <p>An <a href="http://mjs-svc.com">MJS Services</a> project | Powered by <a href="http://grails.org">Grails</a><br />
                <g:link controller="flatpage" action="show" params="[slug: 'ip']">Intellectual Property Information</g:link></p>
        </div>
    </body>
</html>
