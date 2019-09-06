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
    stage('UI Test') {
      steps {
        sh '''fastlane build_for_screengrab
fastlane screengrab'''
      }
    }
  }
}