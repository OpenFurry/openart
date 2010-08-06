class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?" {
			constraints {
				// apply constraints here
			}
		}

        "/$id" {
            controller = "view"
            action = "show"
            constraints {
                id(matches: /\d+/)
            }
        }

        "/edit/$id" {
            controller = "submit"
            action = "edit"
            constraints {
                id(matches: /\d+/)
            }
        }

        "/~$username" {
            controller = "user"
            action = "show"
            constraints {
            }
        }

        // Needed for when browsers convert ~
        "/%7E$username" {
            controller = "user"
            action = "show"
            constraints {
            }
        }

        "/tag/$id" {
            controller = "tag"
            action = "show"
            constraints {
            }
        }
        "/tags/" {
            controller = "tag"
            action = "list"
            constraints {
            }
        }

        "/groups/" {
            controller = "group"
            action = "list"
            constraints {
            }
        }

        "/species/$id" {
            controller = "species"
            action = "show"
            constraints {
                id(matches: /\d+/)
            }
        }
        "/category/$id" {
            controller = "category"
            action = "show"
            constraints {
                id(matches: /\d+/)
            }
        }

        "/issues/$id" {
            controller = "issues"
            action = "show"
            constraints {
                id(matches: /\d+/)
            }
        }

        "/th/$id" {
            controller = "theme"
            action = "show"
            constraints {
            }
        }

        "/info/$id" {
            controller = "flatpage"
            action = "show"
            constraints {
            }
        }

		"/" {
            controller = "index"
            action = "index"
        }

		"500"(view:'/error')
        "404"(view:'/notFound')
	}
}
