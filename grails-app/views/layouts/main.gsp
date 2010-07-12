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
        <g:javascript library="application" />
    </head>
    <body>
        <div id="header">
            <img src="${resource(dir: 'images', file: 'of-logo.png')}" align="left" alt="Welcome to OpenFurry" />
            <h1>OpenFurry</h1>
            <div id="navigation">Navigation</div>
            <div id="usercontrol">
                <g:isLoggedIn>
                    <g:message code="openfurry.display.usercontrol.welcomeUser" args="${[loggedInUserInfo(field: 'userRealName')]}" />
                    <ul>
                        <li><g:link controller="logout"><g:message code="openfurry.display.usercontrol.logout" default="Log out" /></g:link></li>
                    </ul>
                </g:isLoggedIn>
                <g:isNotLoggedIn>
                    <g:message code="openfurry.display.usercontrol.welcomeUser" args="${['guest']}" />
                    <ul>
                        <li><g:link controller="login"><g:message code="openfurry.display.usercontrol.login" default="Log in" /></g:link></li>
                    </ul>
                </g:isNotLoggedIn>
            </div>
        </div>
        <div id="spinner" class="spinner" style="display:none;">
            <img src="${resource(dir:'images',file:'spinner.gif')}" alt="${message(code:'spinner.alt',default:'Loading...')}" />
        </div>
        <div id="content">
            <h2>${pageProperty(name: 'title').replaceAll('OpenFurry - ', '')}</h2>
            <div id="messages">
                <g:if test="${flash.transact}">
                <div class="transact">
                    <g:message code="${flash.transact}" args="${flash.transactArgs}" default="${flash.transactDefault}" />
                </div>
                </g:if>
                <g:each in="${flash.messages}"><div type="${it.type}">${it.message}</div></g:each>
            </div>
            <g:layoutBody />
        </div>
        <div id="footer">
            <p>An <a href="http://mjs-svc.com">MJS Services</a> project | Powered by <a href="http://grails.org">Grails</a><br />
                <a href="/about/ip">Intellectual Property Information</a></p>
        </div>
    </body>
</html>
