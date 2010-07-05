<table>
    <thead>
        <tr>
            <th colspan="2">Submission information</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <th>Title</th>
            <td><input type="text" name="title" /></td>
        </tr>
        <tr>
            <th>Description</th>
            <td><textarea name="description"></textarea></td>
        </tr>
        <tr>
            <th>External link</th>
            <td><input type="text" name="externalLink" value="http://" /></td>
        </tr>
        <tr>
            <th>Rating</th>
            <td>
                <g:each in="${grailsApplication.config.openfurry.ratings.values().toList()}">
                <input type="radio" name="rating" value="${it}" /> ${it}<br />
                </g:each>
            </td>
        </tr>
        <tr>
            <th>Tags</th>
            <td><input type="text" name="tags" /></td>
        </tr>
        <tr>
            <th>Collection</th>
            <td>TODO</td>
        </tr>
        <tr>
            <th>License</th>
            <td>TODO</td>
        </tr>
        <tr>
            <th>Published</th>
            <td><input type="checkbox" name="published" />
        </tr>
        <tr>
            <th>Friends only</th>
            <td><input type="checkbox" name="friendsOnly" />
        </tr>
