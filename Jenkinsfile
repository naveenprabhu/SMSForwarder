pipeline {
  agent {
    docker {
      image 'opengamer/android-sdk-gradle-fastlane'
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