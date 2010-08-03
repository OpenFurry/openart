<html>
    <head>
        <title>PRICE LIST</title>
        <meta name="layout" content="main" />
    </head>
    <body>
        <g:each in="${prices}">
            <li>${it.signal} - ${it.description} - ${it.price}</li>
        </g:each>
    </body>
</html>
