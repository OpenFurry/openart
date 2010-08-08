<html>
    <head>
        <title>INBOX</title>
        <meta name="layout" content="main" />
    </head>
    <body>
        <g:form action="dismiss" method="post">
        <g:set var="currentReType" value="" />
        <g:set var="needUL" value="${false}" />
        <g:each in="${messages}" var="msg">
            <g:if test="${currentReType != msg.regardingType}">
                <g:if test="${needUL}"></ul></g:if>
                <g:else><g:set var="needUL" value="${true}" /></g:else>
                <div class="shadow">${msg.regardingType}</div><!-- TODO i18n -->
                <ul>
                <g:set var="currentReType" value="${msg.regardingType}" />
            </g:if>
            <li class="${grailsApplication.config.openfurry.user.messageTypes.repr[msg.type]}"><g:checkBox name="id.${msg.id}" /><of:linking noImages="true"><g:message code="${msg.code}" default="${msg.defaultMessage}" args="[msg.argumentString(), msgregardingUser]" /></of:linking></li>
        </g:each>
        <div class="buttons"><input type="submit" value="${message(code: 'openfurry.inbox.clear', default: 'Clear checked messages')}" /></div>
        </g:form>
    </body>
</html>
