<html>
    <head>
        <title><g:message code="openfurry.tag.view.show" default="Submissions tagged with {0}" args="[params.id]" /></title>
        <meta name="layout" content="main" />
    </head>
    <body>
        <div class="verticalTabs">
            <ul class="nav">
                <li class="block ${params.type ? '' : 'selected'}"><strong><a href="${createLink(controller: 'tag', action: 'show', id: params.id)}"><g:message code="openfurry.allUO" default="All" /></a></strong></li>
                <li class="block ${params.type == 'image' ? 'selected' : ''}"><strong><a href="${createLink(controller: 'tag', action: 'show', id: params.id)}?type=image"><g:message code="openfurry.imageUO.plural" default="Images" /></a></strong></li>
                <li class="block ${params.type == 'text' ? 'selected' : ''}"><strong><a href="${createLink(controller: 'tag', action: 'show', id: params.id)}?type=text"><g:message code="openfurry.textUO.plural" default="Text" /></strong></a></li>
                <li class="block ${params.type == 'audio' ? 'selected' : ''}"><strong><a href="${createLink(controller: 'tag', action: 'show', id: params.id)}?type=audio"><g:message code="openfurry.audioUO.plural" default="Audio" /></strong></a></li>
                <li class="block ${params.type == 'video' ? 'selected' : ''}"><strong><a href="${createLink(controller: 'tag', action: 'show', id: params.id)}?type=video"><g:message code="openfurry.videoUO.plural" default="Videos" /></strong></a></li>
                <li class="block ${params.type == 'flash' ? 'selected' : ''}"><strong><a href="${createLink(controller: 'tag', action: 'show', id: params.id)}?type=flash"><g:message code="openfurry.flashUO.plural" default="Flash" /></strong></a></li>
                <li class="block ${params.type == 'application' ? 'selected' : ''}"><strong><a href="${createLink(controller: 'tag', action: 'show', id: params.id)}?type=application"><g:message code="openfurry.applicationUO.plural" default="Applications" /></a></strong></li>
            </ul>
            <div class="content block" style="min-height: 20em;">
                <g:each in="${uoList}">
                <li><a href="${createLink(controller: 'view', action: 'show', id: it.id)}">${it.title}</li>
                </g:each>
                <g:formatDate date="${new Date()}" format="EEEEEEEEEE MMMMMMMMMMMM d, yyyy" />
            </div>
        </div>
    </body>
</html>
