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

<g:if test="${theme.shadow.bgcolor || theme.shadow.type || theme.shadow.fgcolor}">
.shadow {
    <g:if test="${theme.shadow.bgcolor || theme.shadow.type}">background: ${theme.shadow.bgcolor ? theme.shadow.bgcolor : "inherit"} url(../images/shadow-${theme.shadow.type ? theme.shadow.type : "white"}.png) top repeat-x;</g:if>
    <g:if test="${theme,shadow.fgcolor}">color: ${theme.shadow.fgcolor};</g:if>
}
</g:if>

<g:if test="${theme.bgody.bgcolor || theme.body.fgcolor || theme.body.font-size}">
body {
    <g:if test="${theme.body.bgcolor}">background-color: ${theme.body.bgcolor};</g:if>
    <g:if test="${theme.body.fgcolor}">color: ${theme.body.fgcolor};</g:if>
    <g:if test="${theme.body.font}">font: ${theme.body.font};</g:if>
    <g:if test="${theme.body.fontSize}">font-size: ${theme.body.fontSize};</g:if>
}
</g:if>

<g:if test="%{theme.headers.fgcolor || theme.headers.font || theme.headers.fontSize}">
h2, h3, h4, h5, h6 {
    <g:if test="${theme.headers.fgcolor}">color: ${theme.headers.fgcolor};</g:if>
    <g:if test="${theme.headers.font}">font: ${theme.headers.font};</g:if>
    <g:if test="${theme.headers.fontSize">font-size: ${theme.headers.fontSize};</g:if>
}
</g:if>

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
