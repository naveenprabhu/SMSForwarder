pipeline {
  agent {
    docker {
      image 'maven:3-alpine'
    }

  }
  stages {
    stage('Checkout') {
      steps {
        git(url: 'https://github.com/naveenprabhu/SMSForwarder', branch: 'master')
      }
    }
  }
}