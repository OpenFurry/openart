<g:ifAnyGranted role="ROLE_STAFF,ROLE_ADMIN">
    <g:render template="admin" />
</g:ifAnyGranted>
<div class="uoDisplay block">
    <div class="uoTitle shadow"><strong>${instance.title.encodeAsHTML()}</strong> - <of:linking noImages="true">${instance.owner.encodeAsHTML()}</of:linking></div>
    <div class="uoDescription">
        <of:linking><markdown:renderHtml>${instance.description?.encodeAsHTML()}</markdown:renderHtml></of:linking>
    </div>
    <div class="uoInfo">
        <dl>
            <dt class="odd"><g:message code="openfurry.uo.rating" default="Rating" /></dt>
            <dd class="odd">${grailsApplication.config.openfurry.ratings.repr[instance.rating]}</dd>

            <dt class="even"><g:message code="openfurry.uo.species" default="Species" /></dt>
            <dd class="even">
                <ul>
                    <g:each in="${instance.species}">
                    <li><of:speciesString species="${it}" /></li>
                    </g:each>
                </ul>
            </dd>

            <dt class="odd"><g:message code="openfurry.uo.category" default="Categories" /></dt>
            <dd class="odd">
                <ul>
                    <g:each in="${instance.categories}">
                    <li><of:categoryString category="${it}" /></li>
                    </g:each>
                </ul>
            </dd>

            <dt class="even"><g:message code="openfurry.uo.dateCreated" default="Date created" /></dt>
            <dd class="even"><g:formatDate format="EEE, MMM d yyyy 'at' HH:mm:ss" date="${instance.dateCreated}" /></dd>


            <dt class="odd"><g:message code="openfurry.uo.lastUpdated" default="Date modifed" /></dt>
            <dd class="odd"><g:formatDate format="EEE, MMM d yyyy 'at' HH:mm:ss" date="${instance.lastUpdated}" /></dd>

            <dt class="even"><g:message code="openfurry.uo.published" default="Published" /></dt>
            <dd class="even">
                <g:if test="${instance.published}">
                <input type="checkbox" checked=true" disabled="true" />
                </g:if>
                <g:else>
                <input type="checkbox" disabled="true" />
                </g:else>
            </dd>

            <dt class="odd"><g:message code="openfurry.uo.friendsOnly" default="Friends only" /></dt>
            <dd class="odd">
                <g:if test="${instance.friendsOnly}">
                <input type="checkbox" checked=true" disabled="true" />
                </g:if>
                <g:else>
                <input type="checkbox" disabled="true" />
                </g:else>
            </dd>

            <dt class="even"><g:message code="openfurry.uo.viewCount" default="View count" /></dt>
            <dd class="even">${instance.viewCount}</dd>

            <dt class="odd"><g:message code="openfurry.uo.favoriteCount" default="Favorite count" /></dt>
            <dd class="odd">${instance.favoriteCount}</dd>

            <dt class="even"><g:message code="openfurry.uo.tags" default="Tags" /></dt>
            <dd class="even">
                <ul>
                    <g:each in="${instance.tags}"><li><a href="${createLink(controller: 'tag', action: 'show', id: it.tag)}">${it.tag}</a></li></g:each>
                </ul>
            </dd>

            <dt class="odd"><g:message code="openfurry.uo.externalLink" default="External link" /></dt>
            <dd class="odd"><g:if test="${instance.externalLink}"><a target="_blank" href="${instance.externalLink.encodeAsHTML()}">${instance.externalLink.encodeAsHTML()}</a></g:if></dd>
        </dl>
    </div>
    <div class="uoLicense shadow">Submission <a href="${instance.license.url}">${instance.license.display} ${instance.owner.userRealName} <g:formatDate format="yyyy" date="${instance.dateCreated}" /></a> - ${instance.license.description} | <g:message code="openfurry.technical.shareLink" default="Share link" />: <g:link controller="view" action="show" id="${instance.id}" base="${grailsApplication.config.openfurry.permalinkBase}">${createLink(controller: 'view', action: 'show', id: instance.id, base: grailsApplication.config.openfurry.permalinkBase)}</g:link></div>
</div>
