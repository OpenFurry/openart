<table>
    <thead>
        <tr>
            <th colspan="2">Submission information</th>
        </tr>
    </thead>
    <tbody>
        <tr class="prop">
            <th class="name">Title</th>
            <td class="value ${hasErrors(bean: instance, field: 'title', 'errors')}">
                <g:textField name="title" value="${instance?.title}" />
            </td>
        </tr>
        <tr class="prop">
            <th class="name">Description</th>
            <td class="value ${hasErrors(bean: instance, field: 'description', 'errors')}">
                <g:textArea name="description" rows="10" cols="100" value="${instance?.description}" />
            </td>
        </tr>
        <tr class="prop">
            <th class="name">External link</th>
            <td class="value ${hasErrors(bean: instance, field: 'externalLink', 'errors')}">
                <g:textField name="externalLink" value="${instance?.externalLink}" />
            </td>
        </tr>
        <tr class="prop">
            <th class="name">Rating</th>
            <td class="value ${hasErrors(bean: instance, field: 'rating', 'errors')}">
                <g:each in="${grailsApplication.config.openfurry.ratings.values().toList()}">
                    <g:if test="${instance?.rating == it}">
                        <g:radio name="rating" value="${it}" checked="true" /> ${it}<br />
                    </g:if>
                    <g:else>
                        <g:radio name="rating" value="${it}" /> ${it}<br />
                    </g:else>
                </g:each>
            </td>
        </tr>
        <tr class="prop">
            <th class="name">Species</th>
            <td class="value ${hasErrors(bean: instance, field: 'species', 'errors')}">
                <select name="species" multiple="multiple">
                    <g:render template="/speciesOptions" collection="${openfurry.Species.createCriteria().list() { isEmpty: parent }}" />
                </select>
            </td>
        </tr>
        <tr class="prop">
            <th class="name">Categories</th>
            <td class="value ${hasErrors(bean: instance, field: 'categories', 'errors')}">TODO</td>
        </tr>
        <tr class="prop">
            <th class="name">Tags</th>
            <td class="value ${hasErrors(bean: instance, field: 'tags', 'errors')}">
                <g:textField name="tags" value="${instance?.tags}" />
            </td>
        </tr>
        <tr class="prop">
            <th class="name">Collection</th>
            <td class="value ${hasErrors(bean: instance, field: 'collection', 'errors')}">TODO</td>
        </tr>
        <tr class="prop">
            <th class="name">License</th>
            <td class="value ${hasErrors(bean: instance, field: 'license', 'errors')}">
                <g:select name="license" from="${openfurry.License.list()}" value="${instance?.license.id}", optionKey="id" />
            </td>
        </tr>
        <tr class="prop">
            <th class="name">Published</th>
            <td class="value ${hasErrors(bean: instance, field: 'published', 'errors')}">
                <g:checkBox name="published" value="${instance?.published}" />
            </td>
        </tr>
        <tr class="prop">
            <th class="name">Friends only</th>
            <td class="value ${hasErrors(bean: instance, field: 'friendsOnly', 'errors')}">
                <g:checkBox name="friendsOnly" value="${instance?.friendsOnly}" />
            </td>
        </tr>
    </tbody>
</table>
