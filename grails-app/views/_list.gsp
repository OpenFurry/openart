<div class="verticalTabs">
    <ul class="nav">
        <li class="block ${params.type ? '' : 'selected'}"><strong><a href="${params.q ? '?q=' + params.q + '&includeTags=' + (params.includeTags ?: '') + '&includeCategories=' + (params.includeCategories ?: '') + '&includeSpecies=' + (params.includeSpecies ?: '') : ''}"><g:message code="openfurry.allUO" default="All" /></a></strong></li>
        <li class="block ${params.type == 'image' ? 'selected' : ''}"><strong><a href="?type=image${params.q ? '&q=' + params.q + '&includeTags=' + (params.includeTags ?: '') + '&includeCategories=' + (params.includeCategories ?: '') + '&includeSpecies=' + (params.includeSpecies ?: '') : ''}"><g:message code="openfurry.imageUO.plural" default="Images" /></a></strong></li>
        <li class="block ${params.type == 'text' ? 'selected' : ''}"><strong><a href="?type=text${params.q ? '&q=' + params.q + '&includeTags=' + (params.includeTags ?: '') + '&includeCategories=' + (params.includeCategories ?: '') + '&includeSpecies=' + (params.includeSpecies ?: '') : ''}"><g:message code="openfurry.textUO.plural" default="Text" /></strong></a></li>
        <li class="block ${params.type == 'audio' ? 'selected' : ''}"><strong><a href="?type=audio${params.q ? '&q=' + params.q + '&includeTags=' + (params.includeTags ?: '') + '&includeCategories=' + (params.includeCategories ?: '') + '&includeSpecies=' + (params.includeSpecies ?: '') : ''}"><g:message code="openfurry.audioUO.plural" default="Audio" /></strong></a></li>
        <li class="block ${params.type == 'video' ? 'selected' : ''}"><strong><a href="?type=video${params.q ? '&q=' + params.q + '&includeTags=' + (params.includeTags ?: '') + '&includeCategories=' + (params.includeCategories ?: '') + '&includeSpecies=' + (params.includeSpecies ?: '') : ''}"><g:message code="openfurry.videoUO.plural" default="Videos" /></strong></a></li>
        <li class="block ${params.type == 'flash' ? 'selected' : ''}"><strong><a href="?type=flash${params.q ? '&q=' + params.q + '&includeTags=' + (params.includeTags ?: '') + '&includeCategories=' + (params.includeCategories ?: '') + '&includeSpecies=' + (params.includeSpecies ?: '') : ''}"><g:message code="openfurry.flashUO.plural" default="Flash" /></strong></a></li>
        <li class="block ${params.type == 'application' ? 'selected' : ''}"><strong><a href="?type=application${params.q ? '&q=' + params.q + '&includeTags=' + (params.includeTags ?: '') + '&includeCategories=' + (params.includeCategories ?: '') + '&includeSpecies=' + (params.includeSpecies ?: '') : ''}"><g:message code="openfurry.applicationUO.plural" default="Applications" /></a></strong></li>
        <li class="block ${params.type == 'orderedCollection' ? 'selected' : ''}"><strong><a href="?type=orderedCollection${params.q ? '&q=' + params.q + '&includeTags=' + (params.includeTags ?: '') + '&includeCategories=' + (params.includeCategories ?: '') + '&includeSpecies=' + (params.includeSpecies ?: '') : ''}"><g:message code="openfurry.collection.ordered.plural" default="Ordered collections" /></a></strong></li>
        <li class="block ${params.type == 'unorderedCollection' ? 'selected' : ''}"><strong><a href="?type=unorderedCollection${params.q ? '&q=' + params.q + '&includeTags=' + (params.includeTags ?: '') + '&includeCategories=' + (params.includeCategories ?: '') + '&includeSpecies=' + (params.includeSpecies ?: '') : ''}"><g:message code="openfurry.collection.unordered.plural" default="Unordered collections" /></a></strong></li>
    </ul>
    <div class="content block uoList">
        <g:set var="dateString" value="" />
        <g:each in="${uoList}" var="uo" status="count">
            <g:if test="${dateString != formatDate(date: uo.lastUpdated, format: 'yyyy-MM-dd')}">
                <h3 style="margin-bottom: 0.5em"><g:formatDate date="${uo.lastUpdated}" format="EEEEEEEEEEE MMMMMMMMMMMM d, yyyy" /></h3>
                <g:set var="dateString" value="${formatDate(date: uo.lastUpdated, format: 'yyyy-MM-dd')}" />
            </g:if>
            <div class="submission${params.controller == 'watch' ? ' ' + of.classForSubmissionListing(submission: uo) : ''}">
                <a href="${createLink(controller: 'view', action: 'show', id: uo.id)}">
                    <img src="${resource(file: uo.thumbnail)}" /><br />
                    ${uo.title}
                </a><br />
                <g:link controller="person" action="show" params="[username: uo.owner.username]">~${uo.owner.username}</g:link> 
            </div>
            <g:set var="resetUser" value="${false}" />
        </g:each>
        <g:if test="${uoList.size() < 1}"><g:message code="openfurry.technical.noSubmissions" default="No submissions to list" /></g:if>
        <hr style="width: 50%; clear: both; margin: auto">
        <g:if test="${params.controller == 'user'}">
            <g:link controller="list" action="user" id="${loggedInUserInfo(field: 'username')}">USER SUBMISSIONS</g:link>
        </g:if>
        <g:else>
            <g:paginate total="${uoList.totalCount}" />
        </g:else>
        <g:if test="${params.controller == 'watch'}">
            <g:link controller="watch" action="updateCursors"><g:message code="openfurry.watch.views.updateCursors" default="Mark all unread submissions as read" /></g:link>
        </g:if>
    </div>
</div>
