pipeline {
  agent {
    docker {
      image 'thyrlian/android-sdk'
      args '-v $(PWD)/sdk:/opt/android-sdk'
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
            sh '''cd /
./$ANDROID_HOME/tools/bin/sdkmanager "system-images;android-29;google_apis;x86_64"
./$ANDROID_HOME/tools/bin/sdkmanager --licenses
echo no | ./$ANDROID_HOME/tools/bin/avdmanager create avd -n test -k "system-images;android-29;google_apis;x86_64"

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