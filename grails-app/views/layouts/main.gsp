<html>
    <head>
        <title>OpenFurry - <g:layoutTitle default="Grails" /></title>
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
            [OPENFURRY HEADER]
            <div id="navigation"></div>
            <div id="usercontrol">
                <g:isLoggedIn>Welcome, <g:loggedInUserInfo field="userRealName" /></g:isLoggedIn>
                <g:isNotLoggedIn>Please log in to continue</g:isNotLoggedIn>
            </div>
        </div>
        <div id="content">
            <g:layoutBody />
        </div>
        <div id="footer">
            <p>An <a href="http://mjs-svc.com">MJS Services</a> project | Powered by <a href="http://grails.org">Grails</a><br />
                <a href="/about/ip">Intellectual Property Information</a></p>
        </div>
    </body>
</html>
