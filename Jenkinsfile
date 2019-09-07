pipeline {
  agent {
    docker {
      image 'opengamer/android-sdk-gradle-fastlane'
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
            sh '''$ANDROID_HOME/tools/bin/sdkmanager "system-images;android-29;google_apis;x86"
$ANDROID_HOME/tools/bin/sdkmanager --licenses
$ANDROID_HOME/tools/bin/avdmanager create avd -n test -k "system-images;android-29;google_apis;x86"

'''
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