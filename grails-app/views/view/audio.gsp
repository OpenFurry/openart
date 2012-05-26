<html><!-- TODO i18n -->
    <head>
        <title>AUDIO - ${instance.title.encodeAsHTML()}</title>
        <meta name="layout" content="main" />
        <script type="text/javascript" src="${grailsApplication.config.openart.mediaplayer.swfobject}"></script>
    </head>
    <body>
        <div class="audioDisplay">
            <div id="mediaspace">FLASH PLAYER REQUIRED FOR IN-BROWSER PLAY</div>
            <script type="text/javascript">
                var so = new SWFObject('${grailsApplication.config.openart.mediaplayer.player}','ply','470','24','9','#000000');
                so.addParam('allowfullscreen','true');
                so.addParam('allowscriptaccess','always');
                so.addParam('wmode','opaque');
                so.addVariable('file','${resource(file: instance.file)}');
                so.write('mediaspace');
            </script>
            <a href="${resource(file: instance.file)}">DOWNLOAD</a>
        </div>
        <g:render template="uo" />
        <g:render template="/comments" model="[object: instance]" />
    </body>
</html>
