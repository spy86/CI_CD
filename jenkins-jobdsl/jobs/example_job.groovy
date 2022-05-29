freeStyleJob('example') {
    logRotator(-1, 10)
    parameters{
        stringParam('Param1', '', 'Example description')
        stringParam('Param2', '', 'Example description')
        }
    steps {
        shell('echo START')
    }
    }