/*
 * Dynamic theme for OpenFurry
 * 
 * Author:
 */

<g:if test="${theme.header.bgcolor || theme.header.borderStyle || theme.header.borderColor">
#header {
    <g:if test="${theme.header.bgcolor}">background-color: ${theme.header.bgcolor};</g:if>
    <g:if test="${theme.header.borderStyle || theme.header.borderColor">border-bottom: 1px ${theme.header.borderStyle ? theme.header.borderStyle : "inherit"} ${theme.header.borderColor ? theme.header.borderColor : "inherit"};</g:if>
}
</g:if>

<g:if test="${theme.header.fgcolor}">
#header h1 {
    color: ${theme.header.fgcolor};
}
</g:if>

<g:if test="${theme.navigation.fgcolor}">
#navigation a {
    color: ${theme.navigation.fgcolor};
}

#usercontrol a {
    color: ${theme.navigation.fgcolor};
}
</g:if>

<g:if test="${theme.content.bgcolor || theme.content.fgcolor}">
#content {
    <g:if test="${theme.content.bgcolor}">background-color: ${theme.content.bgcolor};</g:if>
    <g:if test="${theme.content.fgcolor}">color: ${theme.content.color};</g:if>
}
</g:if>

<g:if test="${theme.block.borderStyle || theme.block.borderColor || theme.block.bgcolor || theme.block.fgcolor}">
.block {
    <g:if test="${theme.block.borderStyle || theme.block.borderColor}">border-style: 1px ${theme.block.borderStyle ? theme.block.borderColor : "inherit"} ${theme.block.borderColor ? theme.block.borderColor : "inherit"};</g:if>
    background-color: ${theme.block.bgcolor ? theme.block.bgcolor : "inherit"};
    color: ${theme.block.fgcolor ? theme.block.fgcolor : "inherit"};
}
</g:if>

.shadow {
    background: ${theme.shadow.bgcolor ? theme.shadow.bgcolor : "inherit"} url(../images/shadow-${theme.shadow.type ? theme.shadow.type : "white"}.png) top repeat-x;
    color: ${theme.shadow.fgcolor ? theme.shadow.fgcolor : "inherit"};
}

body {
    background-color: ${theme.body.bgcolor ? theme.body.bgcolor : "inherit"};
    color: ${theme.body.fgcolor ? theme.body.fgcolor : "inherit"};
    font: ${theme.body.font ? theme.body.font : "inherit"};
    font-size: ${theme.body.fontSize ? theme.body.fontSize : "inherit"};
}

h2, h3, h4, h5, h6 {
    color: ${theme.headers.color ? theme.headers.color : "inherit"};
    font: ${theme.headers.font ? theme.headers.font : "inherit"};
    font-size: ${theme.headers.fontSize ? theme.headers.fontSize : "inherit"};
}

th {
    background-color: ${theme.th.bgcolor ? theme.th.bgcolor : "inherit"};
    color: ${theme.th.fgcolor ? theme.th.fgcolor : "inherit"};
    border-bottom: ${theme.th.bottomBorder ? theme.th.bottomBorder : "inherit"};
}

td {
    background-color: ${theme.td.bgcolor ? theme.td.bgcolor : "inherit"};
    color: ${theme.td.fgcolor ? theme.td.fgcolor : "inherit"};
    border-bottom:${theme.th.bottomBorder ? theme.th.bottomBorder : "inherit"};
}
