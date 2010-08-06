<head>
    <meta name="layout" content="main" />
    <title><g:message code="openfurry.journal.view.create" default="New journal submission" /></title>
</head>

<body>
    <g:hasErrors bean="${instance}">
    <div class="errors">
        <g:renderErrors bean="${instance}" as="list" />
    </div>
    </g:hasErrors>
    <g:uploadForm action="saveJournal" method="post">
        <div class="dialog">
            <table>
                <thead>
                    <tr>
                        <th colspan="2"><g:message code="openfurry.journal.sectionTitle" default="Journal submission" /></th>
                    </tr>
                </thead>
                <tbody>
                    <tr class="prop">
                        <th class="name"><g:message code="openfurry.journal.title" default="Journal title" /></th>
                        <td class="value ${hasErrors(bean: instance, field: 'title', 'errors')}"><g:textField name="title" /></td>
                    </tr>
                    <tr class="prop">
                        <th class="name"><g:message code="openfurry.uo.rating" default="Rating" /></th>
                        <td class="value ${hasErrors(bean: instance, field: 'rating', 'errors')}">
                            <g:each status="i" in="${grailsApplication.config.openfurry.ratings.repr}" var="rating">
                                <g:if test="${instance?.rating == i}">
                                    <g:radio name="rating" value="${i}" checked="true" /> ${rating}<br />
                                </g:if>
                                <g:else>
                                    <g:radio name="rating" value="${i}" /> ${rating}<br />
                                </g:else>
                            </g:each>
                        </td>
                    </tr>
                    <tr class="prop">
                        <th class="name"><g:message code="openfurry.uo.friendsOnly" default="Friends only" /></th>
                        <td class="value ${hasErrors(bean: instance, field: 'friendsOnly', 'errors')}">
                            <g:checkBox name="friendsOnly" value="${instance?.friendsOnly}" />
                        </td>
                    </tr>
                    <tr class="prop">
                        <th class="name">
                            <g:message code="openfurry.journal.journal" default="Journal entry" />
                            <div class="message">
                                <p><g:link controller="flatpage" action="show" id="markdown"><g:message code="openfurry.messages.markdownOkay" default="Markdown allowed" /></g:link></p>
                                <p><g:link controller="flatpage" action="show" id="linking"><g:message code="openfurry.messages.linkingOkay" default="Linking allowed" /></g:link></p>
                            </div>
                        </th>
                        <td class="value ${hasErrors(bean: instance, field: 'text', 'errors')}"><g:textArea name="journal" rows="10" cols="75" /></td>
                    </tr>
                </tbody>
            </table>
        </div>
        <div class="buttons">
            <span class="button"><input type="submit" value="${g.message(code: 'openfurry.uo.submit', default: 'Create submission')}" /></span>
        </div>
    </g:uploadForm>
</body>
