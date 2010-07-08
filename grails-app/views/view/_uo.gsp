<div class="uoDisplay block">
    <div class="uoTitle shadow"><h3>${instance.title.encodeAsHTML()}</h3></div>
    <div class="uoDescription">
        ${instance.description?.encodeAsHTML()} <!-- TODO: markdown -->
    </div>
    <div class="uoInfo">
        <dl>
            <dt class="odd"><g:message code="openfurry.uo.rating" default="Rating" /></dt>
            <dd class="odd">${grailsApplication.config.ratings.repr[instance.rating]}</dd>

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


        </dl>
    </div>
</div>
