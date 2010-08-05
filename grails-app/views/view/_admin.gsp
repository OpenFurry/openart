<div class="block admin"><!-- TODO i18n -->
    <div class="shadow">ADMINISTRATE</div>
    <ul>
        <g:if test="${instance.takenDown}">
            <li><g:link controller="admin" action="unTakeDown" id="${instance.id}">UNDO TAKE DOWN</g:link></li>
        </g:if>
        <g:else>
            <li><g:link controller="admin" action="takeDown" id="${instance.id}">TAKE DOWN</g:link><!-- TODO make this a select dropdown of reasons --></li>
        </g:else>

        <g:if test="${instance.freezeComments}">
            <li><g:link controller="submit" action="unFreezeComments" id="${instance.id}">UNDO FREEZE COMMENTS</g:link></li>
        </g:if>
        <g:else>
            <li><g:link controller="submit" action="freezeComments" id="${instance.id}">FREEZE COMMENTS</g:link></li>
        </g:else>
    </ul>
</div>
