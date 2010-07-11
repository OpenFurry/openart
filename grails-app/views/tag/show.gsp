<html>
    <head>
        <title>TAG</title>
        <meta name="layout" content="main" />
    </head>
    <body>
        <div class="verticalTabs">
            <ul class="nav">
                <li class="block selected"><strong><g:message code="openfurry.allUO" default="All" /></strong></li>
                <li class="block"><strong><g:message code="openfurry.imageUO.plural" default="Images" /></strong></li>
                <li class="block"><strong><g:message code="openfurry.textUO.plural" default="Text" /></strong></li>
                <li class="block"><strong><g:message code="openfurry.audioUO.plural" default="Audio" /></strong></li>
                <li class="block"><strong><g:message code="openfurry.videoUO.plural" default="Videos" /></strong></li>
                <li class="block"><strong><g:message code="openfurry.flashUO.plural" default="Flash" /></strong></li>
                <li class="block"><strong><g:message code="openfurry.applicationUO.plural" default="Applications" /></strong></li>
            </ul>
            <div class="content block" style="min-height: 20em;">
                <g:each in="${uoList}">
                <li><a href="${createLink(controller: 'view', action: 'show', id: it.id)}">${it.title}</li>
                </g:each>
            </div>
        </div>
    </body>
</html>
