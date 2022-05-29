listView('project-A') {
    description('All jobs')
    filterBuildQueue()
    filterExecutors()
    jobs {
        name('example')
        regex(/example.+/)
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