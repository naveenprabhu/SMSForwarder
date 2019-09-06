pipeline {
  agent {
    docker {
      image 'runmymind/docker-android-sdk'
    }

  }
  stages {
    stage('Checkout') {
      steps {
        sh 'apt-get update'
      }
    }
  }
}