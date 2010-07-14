<div class="verticalTabs">
    <ul class="nav">
        <li class="block ${params.type ? '' : 'selected'}"><strong><a href="${createLink(controller: params.controller, action: params.action, id: params.id)}"><g:message code="openfurry.allUO" default="All" /></a></strong></li>
        <li class="block ${params.type == 'image' ? 'selected' : ''}"><strong><a href="${createLink(controller: params.controller, action: params.action, id: params.id)}?type=image"><g:message code="openfurry.imageUO.plural" default="Images" /></a></strong></li>
        <li class="block ${params.type == 'text' ? 'selected' : ''}"><strong><a href="${createLink(controller: params.controller, action: params.action, id: params.id)}?type=text"><g:message code="openfurry.textUO.plural" default="Text" /></strong></a></li>
        <li class="block ${params.type == 'audio' ? 'selected' : ''}"><strong><a href="${createLink(controller: params.controller, action: params.action, id: params.id)}?type=audio"><g:message code="openfurry.audioUO.plural" default="Audio" /></strong></a></li>
        <li class="block ${params.type == 'video' ? 'selected' : ''}"><strong><a href="${createLink(controller: params.controller, action: params.action, id: params.id)}?type=video"><g:message code="openfurry.videoUO.plural" default="Videos" /></strong></a></li>
        <li class="block ${params.type == 'flash' ? 'selected' : ''}"><strong><a href="${createLink(controller: params.controller, action: params.action, id: params.id)}?type=flash"><g:message code="openfurry.flashUO.plural" default="Flash" /></strong></a></li>
        <li class="block ${params.type == 'application' ? 'selected' : ''}"><strong><a href="${createLink(controller: params.controller, action: params.action, id: params.id)}?type=application"><g:message code="openfurry.applicationUO.plural" default="Applications" /></a></strong></li>
        <li class="block ${params.type == 'orderedCollection' ? 'selected' : ''}"><strong><a href="${createLink(controller: params.controller, action: params.action, id: params.id)}?type=orderedCollection"><g:message code="openfurry.collection.ordered.plural" default="Ordered collections" /></a></strong></li>
        <li class="block ${params.type == 'unorderedCollection' ? 'selected' : ''}"><strong><a href="${createLink(controller: params.controller, action: params.action, id: params.id)}?type=unorderedCollection"><g:message code="openfurry.collection.unordered.plural" default="Unordered collections" /></a></strong></li>
    </ul>
    <div class="content block" style="min-height: 20em;">
        <table>
            <tbody>
                <g:each in="${uoList}" var="uo" status="count">
                <g:if test="${count % 4 == 0}"><tr></g:if>
                    <td><a href="${createLink(controller: 'view', action: params.action, id: uo.id)}">${uo.title}</a></td>
                <g:if test="${count % 4 == 3}"></tr></g:if>
                </g:each>
                </tr>
            </tbody>
        </table>
        <g:formatDate date="${new Date()}" format="EEEEEEEEEE MMMMMMMMMMMM d, yyyy" />
    </div>
</div>
