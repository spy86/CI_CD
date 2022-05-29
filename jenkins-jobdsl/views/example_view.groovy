listView('project-A') {
    description('All jobs')
    filterBuildQueue()
    filterExecutors()
    jobs {
        name('example')
        regex(/example.+/)
    }
    jobFilters {
        status {
            status(Status.SUCESS)
        }
    }
    columns {
        status()
        weather()
        name()
        lastSuccess()
        lastFailure()
        lastDuration()
        buildButton()
    }
}