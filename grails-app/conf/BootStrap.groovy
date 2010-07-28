import openfurry.*

class BootStrap {

    def authenticateService

    def init = { servletContext ->

        /**
         * Role definitions
         */
        def roleAdmin = new Role(authority: "ROLE_ADMIN", description: "Site administrator")
        roleAdmin.save()

        def roleStaff = new Role(authority: "ROLE_STAFF", description: "Staff member")
        roleStaff.save()

        def roleGovernor = new Role(authority: "ROLE_GOVERNOR", description: "Governing board member")
        roleGovernor.save()

        def roleUser  = new Role(authority: "ROLE_USER", description: "Authenticated user")
        roleUser.save()

        /**
         * Species definitions
         */
        def speciesTest = new Species(speciesName: "Test species")
        speciesTest.save()

        def speciesDeity = new Species(speciesName: "Anthropomorphic Deity", parent: speciesTest)
        speciesDeity.save()
        
        def speciesTestSubject = new Species(speciesName: "Crash test dummy", parent: speciesTest)
        speciesTestSubject.save()

        /**
         * Category definitions
         */
        def categoryVisual = new Category(categoryName: "Visual art")
        categoryVisual.save()

        def categoryPhotography = new Category(categoryName: "Photography", parent: categoryVisual)
        categoryPhotography.save(flush: true)

        def categoryPainting = new Category(categoryName: "Painting", parent: categoryVisual)
        categoryPainting.save(flush: true)

        categoryVisual.save(flush: true)

        /**
         * License definitions
         */
        def licenseCopyright = new License(
            title: "Copyright",
            description: "All rights reserved",
            display: "&copy;",
            url: "http://loc.gov"
        )
        licenseCopyright.save()


        /**
         * User definitions
         */
        def userGod = new Person(
            username: "god",
            userRealName: "God Almighty",
            profile: "a ~god ~!god b #1 #!1",
            enabled: true,
            emailShow: true,
            email: "god@openfurry.com",
            passwd: authenticateService.encodePassword('god'),
            memberType: "Lurker",
            maxViewableRating: 2,
            preferedLicense: licenseCopyright,
            species: speciesDeity
        )
        userGod.save()
        userGod.addToWatches(userGod).save()

        roleAdmin.addToPeople(userGod)
        roleStaff.addToPeople(userGod)
        roleGovernor.addToPeople(userGod)
        roleUser.addToPeople(userGod)

        def userSlave = new Person(
            username: "slave",
            userRealName: "Slave",
            enabled: true,
            emailShow: true,
            email: "slave@hotmail.com",
            passwd: authenticateService.encodePassword('slave'),
            memberType: "Lurker",
            preferedLicense: licenseCopyright,
            species: speciesTestSubject
        )
        userSlave.save()
        userSlave.addToWatches(userSlave).save()
        
        roleUser.addToPeople(userSlave)
        
        roleUser.save(flush: true)
        roleGovernor.save(flush: true)
        roleStaff.save(flush: true)
        roleAdmin.save(flush: true)
        licenseCopyright.save(flush: true)

        /**
         * Request mapping definitions
         */
        // Acegi requests
        secure("/person/**", "ROLE_STAFF")
        secure("/authority/**", "ROLE_ADMIN")
        secure("/requestmap/**", "ROLE_ADMIN")

        // Issue requests
        secure("/issue/edit/**", "ROLE_GOVERNOR,ROLE_STAFF")
        secure("/issue/create/**", "ROLE_USER")
        secure("/issue/vote/**", "ROLE_USER")
        secure("/issue/delete/**", "ROLE_STAFF")
        
        // Submission request
        secure("/submit/**", "ROLE_USER")

        // Flatpage request
        secure("/flatpage/create/**", "ROLE_STAFF")
        secure("/flatpage/save/**", "ROLE_STAFF")
        secure("/flatpage/edit/**", "ROLE_STAFF")

        // Status requests
        secure("/status/mentions/**", "ROLE_USER")
        secure("/status/save/**", "ROLE_USER")
        secure("/status/delete/**", "ROLE_USER")

        // Group requests
        secure("/group/create/**", "ROLE_USER")
        secure("/group/save/**", "ROLE_USER")
        secure("/group/edit/**", "ROLE_USER")
        secure("/group/join/**", "ROLE_USER")
        secure("/group/leave/**", "ROLE_USER")
        secure("/group/requestToJoin/**", "ROLE_USER")
        secure("/group/listRequests/**", "ROLE_USER")
        secure("/group/showRequest/**", "ROLE_USER")
        secure("/group/approveRequest/**", "ROLE_USER")
        secure("/group/denyRequest/**", "ROLE_USER")
        secure("/group/transferAdmin/**", "ROLE_USER")
        secure("/group/post/**", "ROLE_USER")
        secure("/group/savePost/**", "ROLE_USER")
        secure("/group/deletePost/**", "ROLE_USER")
    }
    def destroy = {
    }

    private secure(url, roles) {
        new Requestmap(url: url, configAttribute: roles).save()
    }
}
