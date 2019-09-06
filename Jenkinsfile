pipeline {
  agent {
    docker {
      image 'runmymind/docker-android-sdk'
    }

  }
  stages {
    stage('Checkout') {
      steps {
        git(url: 'https://github.com/naveenprabhu/SMSForwarder', branch: 'master')
        sh 'apt-get update'
      }
    }
  }
}