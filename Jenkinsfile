pipeline {
  agent {
    docker {
      image 'budtmo/docker-android-x86-9.0'
      args '--privileged -d -p 6080:6080 -p 5554:5554 -p 5555:5555 -e DEVICE="Samsung Galaxy S6" --name android-container'
    }

  }
  stages {
    stage('Test') {
      steps {
        sh './gradlew test'
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