pipeline {

    agent {
        docker {
            image 'livetouch/android-build-box:latest'
            args '--privileged -v ${WORKSPACE}:/tmp -u root -e SDK_VERSION=29 -e NDK=false'
        }
    }
    environment {
        workspace = pwd()
        branch = 'master'
        url = 'https://bitbucket.org/livetouchdev/helloandroidup'
    }

    stages {

        stage('Checkout git') {
            steps {
                git branch: branch, credentialsId: 'livetouch', url: url
            }
        }

        stage('Lint') {
            steps {
                sh "echo 'Lint apk'"
                sh "./gradlew lint"
                sh "bundle update fastlane"
                sh "fastlane install_plugins"
                sh "bundle install"
                sh "bundle exec fastlane lint"
                archiveArtifacts artifacts: 'app/build/reports/**'
                androidLint()
            }
        }

        stage('Test') {
            steps {
                sh "echo 'Test apk'"
                sh "bundle update fastlane"
                sh "fastlane install_plugins"
                sh "bundle install"
                sh "bundle exec fastlane test"
                sh "touch app/build/test-results/**/*.xml"
                archiveArtifacts artifacts: 'app/build/test-results/**'
                junit 'app/build/test-results/**/*.xml'
            }
        }

        // Manage Jenkins > Credentials > Add > Secret file or Secret Text
        stage('Credentials') {
            steps {
                withCredentials([file(credentialsId: 'ANDROID_KEYSTORE_FILE', variable: 'ANDROID_KEYSTORE_FILE')]) {
                    sh "cp '${ANDROID_KEYSTORE_FILE}' app/hello.keystore"
                    sh "cat app/hello.keystore"
                }
                withCredentials([file(credentialsId: 'FIREBASE_SERVICE_ACCOUNT_FILE', variable: 'FIREBASE_SERVICE_ACCOUNT_FILE')]) {
                    sh "cp '${FIREBASE_SERVICE_ACCOUNT_FILE}' app/firebase-service-account-key.json"
                    sh "cat app/firebase-service-account-key.json"
                }
                withCredentials([file(credentialsId: 'GOOGLEPLAY_SERVICE_ACCOUNT_FILE', variable: 'GOOGLEPLAY_SERVICE_ACCOUNT_FILE')]) {
                    sh "cp '${GOOGLEPLAY_SERVICE_ACCOUNT_FILE}' app/google-service-account-key.json"
                    sh "cat app/google-service-account-key.json"
                }
            }
        }

        stage('Build') {
            steps {
                sh "echo 'Build apk'"
                sh "bundle update fastlane"
                sh "fastlane install_plugins"
                sh "bundle install"
                sh "bundle exec fastlane build"
                archiveArtifacts artifacts: 'app/build/outputs/apk/release/**'
            }
        }


        stage('Publish S3') {
            steps {
                // https://github.com/jenkinsci/pipeline-aws-plugin
                // http://download.livetouchdev.com.br/helloandroidup/app-release.apk
                withAWS(credentials:'aws', region: 'sa-east-1') {
                    s3Upload(bucket:"download.livetouchdev.com.br", path:'helloandroidup/',workingDir:'app/build/outputs/apk/release', includePathPattern:'**/*');
                }
            }
        }

        stage('Firebase Distribution') {
            steps {
                sh "echo 'Firebase dist'"
                sh "bundle update fastlane"
                sh "fastlane install_plugins"
                sh "bundle install"
                sh "bundle exec fastlane firebase"
            }
        }

        stage('Google Play - Internal') {
            steps {
                sh "echo 'Google Play - Internal'"
                sh "bundle update fastlane"
                sh "fastlane install_plugins"
                sh "bundle install"
                sh "bundle exec fastlane internal"
            }
        }

        // https://www.jenkins.io/doc/pipeline/steps/pipeline-input-step/
        stage('Promote to Alpha') {
            steps {
                script {
                    timeout(time: 10, unit: 'DAYS') {
                        input(message: "Promover para Alpha?", ok: 'Aprovar')
                    }
                }
            }
        }

        stage('Google Play - Alpha') {
            steps {
                sh "echo 'Google Play - Alpha'"
                sh "./gradlew promoteArtifact --promote-track alpha"
            }
        }
    }

    post {
        always {
            sh "rm app/hello.keystore"
            sh "rm app/firebase-service-account-key.json"
            sh "rm app/google-service-account-key.json"
        }
    }
}
