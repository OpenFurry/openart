package openart

import org.codehaus.groovy.grails.plugins.web.taglib.ApplicationTagLib as g

class OpenArtCodec {
    static encode = { target ->
        target.replaceAll(/~([a-zA-Z0-9_-]+)/, { a, b -> "<a href=\"/${a}\">${a}</a>" })
    }
}
