pipeline {
  agent {
    docker {
      image 'thyrlian/android-sdk'
      args '-v $PWD/sdk:/opt/android-sdk'
    }

  }
  stages {
    stage('Test') {
      parallel {
        stage('Test') {
          steps {
            sh 'fastlane test'
          }
        }
        stage('Create AVD') {
          steps {
            sh './gradlew test'
          }
        }
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