<g:if test="${params.id}">
<input type="hidden" name="id" value="${params.id}" />
</g:if>
<table>
    <thead>
        <tr>
            <th colspan="2"><g:message code="openart.uo.sectionTitle" default="Submission information" /></th>
        </tr>
    </thead>
    <tbody>
        <tr class="prop">
            <g:if test="${instance?.thumbnail}"><g:message code="openart.technical.currentAttachment" default="Current attachment:" /> <img src="${resource(file: instance.thumbnail)}" /></g:if>
            <th class="name"><g:message code="openart.uo.thumbnail" default="Submission thumbnail" /></th>
            <td class="value ${hasErrors(bean: instance, field: 'thumbnail', 'errors')}">
                <input type="file" name="thumbnailUpload" />
                <div class="message"><g:message code="openart.technical.allowedTypes" default="Alloewd file types" /> ${grailsApplication.config.openart.fileTypes.image}<br />
                    <g:message code="openart.technical.defaultThumb" default="If you do not upload a thumbnail image, a default thumbnail will be used for you" />
                    <g:if test="${instance?.thumbnail}"><br /><g:message code="openart.technical.uploadWillReplace" default="Uploading a new file will replace the old one" /></g:if>
                </div>
            </td>
        </tr>
        <tr class="prop">
            <th class="name"><g:message code="openart.uo.title" default="Title" /></th>
            <td class="value ${hasErrors(bean: instance, field: 'title', 'errors')}">
                <g:textField name="title" value="${instance?.title}" />
            </td>
        </tr>
        <tr class="prop">
            <th class="name">
                <g:message code="openart.uo.description" default="Description" />
                <div class="message">
                    <p><g:link controller="flatpage" action="show" id="markdown"><g:message code="openart.messages.markdownOkay" default="Markdown allowed" /></g:link></p>
                    <p><g:link controller="flatpage" action="show" id="linking"><g:message code="openart.messages.linkingOkay" default="Linking allowed" /></g:link></p>
                </div>
            </th>
            <td class="value ${hasErrors(bean: instance, field: 'description', 'errors')}">
                <g:textArea name="description" rows="10" cols="75" value="${instance?.description}" />
            </td>
        </tr>
        <tr class="prop">
            <th class="name"><g:message code="openart.uo.externalLink" default="External link" /></th>
            <td class="value ${hasErrors(bean: instance, field: 'externalLink', 'errors')}">
                <g:textField name="externalLink" value="${instance?.externalLink}" />
            </td>
        </tr>
        <tr class="prop">
            <th class="name"><g:message code="openart.uo.rating" default="Rating" /></th>
            <td class="value ${hasErrors(bean: instance, field: 'rating', 'errors')}">
                <g:each status="i" in="${grailsApplication.config.openart.ratings.repr}" var="it">
                    <g:if test="${instance?.rating == i}">
                        <g:radio name="rating" value="${i}" checked="true" /> ${it}<br />
                    </g:if>
                    <g:else>
                        <g:radio name="rating" value="${i}" /> ${it}<br />
                    </g:else>
                </g:each>
            </td>
        </tr>
        <tr class="prop">
            <th class="name"><g:message code="openart.uo.species" default="Species" /></th>
            <td class="value ${hasErrors(bean: instance, field: 'species', 'errors')}">
                <g:set var="depth" value="${0}" />
                <select name="species" multiple="multiple" size="5">
                    <of:speciesOptions />
                </select><br />
                <span class="message"><g:message code="openart.technical.selectMultiple" /></span>
            </td>
        </tr>
        <tr class="prop">
            <th class="name"><g:message code="openart.uo.categories" default="Categories" /></th>
            <td class="value ${hasErrors(bean: instance, field: 'categories', 'errors')}">
                <g:set var="depth" value="${0}" />
                <select name="categories" multiple="multiple" size="5">
                    <of:categoryOptions />
                </select><br />
                <span class="message"><g:message code="openart.technical.selectMultiple" /></span>
            </td>
        </tr>
        <tr class="prop">
            <th class="name"><g:message code="openart.uo.tags" default="Tags" /></th>
            <td class="value ${hasErrors(bean: instance, field: 'tags', 'errors')}">
                <g:textField name="tagString" value="${instance?.tags}" />
            </td>
        </tr>
        <tr class="prop">
            <th class="name"><g:message code="openart.uo.collection" default="Collection" /></th>
            <td class="value ${hasErrors(bean: instance, field: 'collection', 'errors')}">
                <select name="collectionId">
                    <option disabled="disabled" selected="selected"></option>
                    <of:collectionsOptionsForUser />
                </select>
            </td>
        </tr>
        <tr class="prop">
            <th class="name"><g:message code="openart.uo.license" default="License" /></th>
            <td class="value ${hasErrors(bean: instance, field: 'license', 'errors')}">
                <g:select name="license.id" from="${com.mjs_svc.openart.License.list()}" value="${instance?.license.id}", optionKey="id" />
            </td>
        </tr>
        <tr class="prop">
            <th class="name"><g:message code="openart.uo.published" default="Published"/></th>
            <td class="value ${hasErrors(bean: instance, field: 'published', 'errors')}">
                <g:checkBox name="published" value="${instance?.published}" />
            </td>
        </tr>
        <tr class="prop">
            <th class="name"><g:message code="openart.uo.friendsOnly" default="Friends only" /></th>
            <td class="value ${hasErrors(bean: instance, field: 'friendsOnly', 'errors')}">
                <g:checkBox name="friendsOnly" value="${instance?.friendsOnly}" />
            </td>
        </tr>
        <tr class="prop">
            <th class="name"><g:message code="openart.uo.freezeComments" default="Disallow comments" /></th>
            <td class="Value ${hasErrors(bean: instance, field: 'freezeComments', 'errors')}">
                <g:checkBox name="freezeComments" value="${instance?.freezeComments}" />
            </td>
        </tr>
    </tbody>
</table>
