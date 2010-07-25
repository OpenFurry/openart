<html>
    <head>
        <title>INBOX</title>
        <meta name="layout" content="main" />
    </head>
    <body>
        <g:form action="dismiss" method="post">
        <g:set var="currentReType" value="" />
        <g:set var="needUL" value="${false}" />
        <g:each in="${messages}" var="message">
            <g:if test="${currentReType != message.regardingType}">
                <g:if test="${needUL}"></ul></g:if>
                <g:else><g:set var="needUL" value="${true}" /></g:else>
                <div class="shadow">${message.regardingType}</div><!-- TODO i18n -->
                <ul>
                <g:set var="currentReType" value="${message.regardingType}" />
            </g:if>
            <li class="${grailsApplication.config.openfurry.user.messageTypes.repr[message.type]}"><g:checkBox name="id.${message.id}" /><of:linking noImages="true"><g:message code="${message.code}" default="${message.defaultMessage}" args="[message.argumentString()]" /></of:linking></li>
        </g:each>
        <div class="buttons"><input type="submit" /></div>
        </g:form>
    </body>
</html>
