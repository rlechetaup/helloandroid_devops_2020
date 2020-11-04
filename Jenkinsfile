pipeline {

   agent { label 'mac' }

   environment {
       workspace = pwd()
       branch = 'master'
       url = 'https://bitbucket.org/livetouchdev/helloup2'
   }

   stages {

      /*stage('Emulator') {
         steps {
             // Abre o emulador
             sh "emulator -avd Nexus_5X_API_29 -no-window &"
         }
      }*/

      stage('Checkout git') {
          steps {
              git branch: branch, credentialsId: 'livetouch', url: url
          }
      }
      stage('Build') {
          steps {
              sh "echo 'Build apk'"
              sh "./gradlew assembleDebug assembleDebugAndroidTest"
          }
      }
       stage('UI Test') {
           steps {
               sh "./gradlew connectedAndroidTest"
           }
       }
   }
}
