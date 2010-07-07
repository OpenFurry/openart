/*
 * Dynamic theme for OpenFurry
 * 
 * Author:
 */

#header {
    background-color: ${theme.header.bgcolor ? theme.header.bgcolor : "inherit"};
    border-bottom: 1px ${theme.header.borderStyle ? theme.header.borderStyle : "inherit"} ${theme.header.borderColor ? theme.header.borderColor : "inherit"};
}

#header h1 {
    color: ${theme.header.fgcolor ? theme.header.fgcolor : "inherit"};
}

#navigation a {
    color: ${theme.navigation.fgcolor ? theme.navigation.fgcolor : "inherit"};
}

#usercontrol a {
    color: ${theme.navigation.fgcolor ? theme.navigation.fgcolor : "inherit"};
}

#content {
    background-color: ${theme.content.bgcolor ? theme.content.bgcolor : "inherit"};
    color: ${theme.content.color ? theme.content.color : "inherit"};
}

.block {
    border-style: 1px ${theme.block.borderStyle ? theme.block.borderColor : "inherit"} ${theme.block.borderColor ? theme.block.borderColor : "inherit"};
    background-color: ${theme.block.bgcolor ? theme.block.bgcolor : "inherit"};
    color: ${theme.block.fgcolor ? theme.block.fgcolor : "inherit"};
}

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
