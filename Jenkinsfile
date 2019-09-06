pipeline {
  agent {
    docker {
      image 'opengamer/android-sdk-gradle-fastlane'
    }

  }
  stages {
    stage('Test') {
      steps {
        sh 'fastlane test'
      }
    }
  }
}