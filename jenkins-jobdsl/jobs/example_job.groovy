freeStyleJob('example') {
    logRotator(-1, 10)
    parameters{
        stringParam('PARAM_01', '', 'Example description')
        stringParam('PARAM_02', '', 'Example description')
        }
    steps {
        shell('echo ${PARAM_01}')
        shell('echo ${PARAM_02}')
    }
    }