<html><!-- TODO i18n -->
    <head>
        <title>CALENDAR</title>
        <meta name="layout" content="main" />
    </head>
    <body>
        <table class="calendar">
            <thead>
                <tr>
                    <th>SUNDAY</th>
                    <th>MONDAY</th>
                    <th>TUESDAY</th>
                    <th>WEDNESDAY</th>
                    <th>THURSDAY</th>
                    <th>FRIDAY</th>
                    <th>SATURDAY</th>
                </tr>
            </thead>
            <tbody>
                <of:calendarAsTDs start="${monthStart}" end="${monthEnd}" events="${events}" />
            </tbody>
            <tfoot>
                <tr>
                    <th>SUNDAY</th>
                    <th>MONDAY</th>
                    <th>TUESDAY</th>
                    <th>WEDNESDAY</th>
                    <th>THURSDAY</th>
                    <th>FRIDAY</th>
                    <th>SATURDAY</th>
                </tr>
            </tfoot>
        </table>
    </body>
</body>
