<div class="admin">
    <div class="shadow">ADMINISTRATE</div>
    <ul>
        <li><!-- TODO i18n -->
            <g:form controller="admin" action="warn" id="${user.username}">
                <select name="warningLevel">
                    <option value="small">SMALL</option>
                    <option value="medium">MEDIUM</option>
                    <option value="large">LARGE</option>
                </select>
                <select name="reasonCode">
                    <option value="test">TEST</option>
                </select>
                <input type="submit" value="WARN" />
            </g:form>
        </li>
        <li>
            <g:form controller="admin" action="praise" id="${user.username}">
                <select name="warningLevel">
                    <option value="small">SMALL</option>
                    <option value="medium">MEDIUM</option>
                    <option value="large">LARGE</option>
                </select>
                <select name="reasonCode">
                    <option value="test">TEST</option>
                </select>
                <input type="submit" value="PRAISE" />
            </g:form>
        </li>
        <g:if test="${user.enabled}">
            <li><g:link controller="admin" action="ban" id="${user.username}">BAN</g:link></li>
        </g:if>
        <g:else>
            <li><g:link controller="admin" action="unban" id="${user.username}">UNBAN</g:link></li>
        </g:else>
    </ul>
</div>
