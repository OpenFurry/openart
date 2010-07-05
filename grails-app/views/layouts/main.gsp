<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
    <head>
        <title>OpenFurry - <g:layoutTitle default="OpenFurry" /></title>
        <link rel="stylesheet" href="${resource(dir:'css',file:'main.css')}" />
        <link rel="shortcut icon" href="${resource(dir:'images',file:'favicon.ico')}" type="image/x-icon" />
        <g:layoutHead />
        <g:javascript library="application" />
    </head>
    <body>
        <div id="spinner" class="spinner" style="display:none;">
            <img src="${resource(dir:'images',file:'spinner.gif')}" alt="${message(code:'spinner.alt',default:'Loading...')}" />
        </div>
        <div id="header">
            <h1>OpenFurry</h1>[OPENFURRY HEADER]
            <div id="navigation"></div>
            <div id="usercontrol">
                <g:isLoggedIn>Welcome, <g:loggedInUserInfo field="userRealName" /></g:isLoggedIn>
                <g:isNotLoggedIn>Please log in to continue</g:isNotLoggedIn>
            </div>
        </div>
        <g:if test="${flash.message}">
            <div id="message">
                ${flash.message}
            </div>
        </g:if>
        <div id="content">
            <h2><g:layoutTitle default="OpenFurry" /></h2>
            <g:layoutBody />
        </div>
        <div id="footer">
            <p>An <a href="http://mjs-svc.com">MJS Services</a> project | Powered by <a href="http://grails.org">Grails</a><br />
                <a href="/about/ip">Intellectual Property Information</a></p>
        </div>
    </body>
</html>
