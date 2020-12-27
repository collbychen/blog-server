pipeline {
    agent any
    stages {
        stage('Hello') {
            steps {
                checkout([$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: 'b6cb4790-92df-4a6d-80f9-d90a93dc920a', url: 'http://192.168.46.130/collby-group/blog-server.git']]])
            }
        }
    }
}