<html><!-- TODO i18n -->
    <head>
        <title>SEARCH</title>
        <meta name="layout" content="main" />
        <script type="text/javascript">
            $(function() {
                $('#accordion').accordion({
                    autoHeight: false
                });
            });
        </script>
    </head>
    <body>
        <div id="accordion">
            <h3><a href="#">SUBMISSIONS</a></h3>
            <div>
                <g:form action="submissions" method="get">
                    <table>
                        <tbody>
                            <tr class="prop">
                                <th class="name">SEARCH</th>
                                <td class="value"><g:textField name="q" /></td>
                            </tr>
                            <tr class="prop">
                                <th class="name">OPTIONS</th>
                                <td class="value">
                                    <p><input type="checkbox" name="includeTags" /> SEARCH TAGS</p>
                                    <p><input type="checkbox" name="includeCategories" /> SEARCH CATEGORIES</p>
                                    <p><input type="checkbox" name="includeSpecies" /> SEARCH SPECIES</p>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                    <div class="buttons"><input type="submit" /></div>
                </g:form>
            </div>

            <h3><a href="#">ISSUES</a></h3>
            <div>
                <g:form action="issues" method="get">
                    <table>
                        <tbody>
                            <tr class="prop">
                                <th class="name">SEARCH</th>
                                <td class="value"><g:textField name="q" /></td>
                            </tr>
                        </tbody>
                    </table>
                    <div class="buttons"><input type="submit" /></div>
                </g:form>
            </div>

            <h3><a href="#">GROUPS</a></h3>
            <div>
                <g:form action="groups" method="get">
                    <table>
                        <tbody>
                            <tr class="prop">
                                <th class="name">SEARCH</th>
                                <td class="value"><g:textField name="q" /></td>
                            </tr>
                            <tr class="prop">
                                <th class="name">OPTIONS</th>
                                <td class="value">
                                    <p><input type="checkbox" name="includePrivate" /> INCLUDE PRIVATE</p>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                    <div class="buttons"><input type="submit" /></div>
                </g:form>
            </div>

            <h3><a href="#">POSTS</a></h3>
            <div>
                <g:form action="posts" method="get">
                    <table>
                        <tbody>
                            <tr class="prop">
                                <th class="name">SEARCH</th>
                                <td class="value"><g:textField name="q" /></td>
                            </tr>
                        </tbody>
                    </table>
                    <div class="buttons"><input type="submit" /></div>
                </g:form>
            </div>
            
            <h3><a href="#">EVENTS</a></h3>
            <div>
                <g:form action="events" method="get">
                    <table>
                        <tbody>
                            <tr class="prop">
                                <th class="name">SEARCH</th>
                                <td class="value"><g:textField name="q" /></td>
                            </tr>
                        </tbody>
                    </table>
                    <div class="buttons"><input type="submit" /></div>
                </g:form>
            </div>
        </div>
    </body>
</html>
