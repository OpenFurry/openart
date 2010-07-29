<html><!-- TODO i18n -->
    <head>
        <title>ADMINS, STAFF, AND GOVERNORS</title>
        <meta name="layout" content="main" />
    </head>
    <body>
        <div class="groupMembers">
            <div class="shadow"><strong>ADMINS</strong></div>
            <g:each in="${admins.people}" var="member">
                <div class="member">
                    <of:linking>~${member.username}</of:linking><br />
                    ${member.title}
                </div>
            </g:each>
        </div>
        <div class="groupMembers">
            <div class="shadow"><strong>STAFF</strong></div>
            <g:each in="${staff.people}" var="member">
                <div class="member">
                    <of:linking>~${member.username}</of:linking><br />
                    ${member.title}
                </div>
            </g:each>
        </div>
        <div class="groupMembers">
            <div class="shadow"><strong>GOVERNORS</strong></div>
            <g:each in="${governors.people}" var="member">
                <div class="member">
                    <of:linking>~${member.username}</of:linking><br />
                    ${member.title}
                </div>
            </g:each>
        </div>

    </body>
</html>
