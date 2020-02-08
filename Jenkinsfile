pipeline {
    agent none
    stages {
        stage('Clean workspace') {
            agent {
                docker {
                    image 'maven:3.6.3-jdk-8-slim'
                }
            }
            steps {
                sh 'mvn clean'
                sh 'mv src/main/resources/application_example.yml src/main/resources/application.yml'
                sh 'sed -i "s/password:.*/password: ${mappwd}/" src/main/resources/application.yml'
            }
        }
        stage('Build application') {
            agent {
                docker {
                    image 'maven:3.6.3-jdk-8-slim'
                }
            }
            steps {
                sh 'mvn install'
            }
        }
        stage('Build docker image') {
            agent any
            steps {
                sh 'docker image rm miraclewisp/hperproteinaemia-maps || true'
                sh 'docker build -t miraclewisp/hperproteinaemia-maps:${BUILD_NUMBER} -t miraclewisp/hperproteinaemia-maps:latest .'
            }

        }
        stage('Push docker image') {
            agent any
            steps {
                withDockerRegistry([credentialsId: "dockerhub", url: ""]) {
                    sh 'docker push miraclewisp/hperproteinaemia-maps:${BUILD_NUMBER}'
                    sh 'docker push miraclewisp/hperproteinaemia-maps:latest'
                }
            }

        }
        stage('Deploy') {
            agent any
            steps {
                sh 'ssh Rinslet docker stop maps || true'
                sh 'ssh Rinslet docker image rm miraclewisp/hperproteinaemia-maps || true'
                sh 'ssh Rinslet docker pull miraclewisp/hperproteinaemia-maps'
                sh 'ssh Rinslet docker run --rm --name maps -d -p 8081:8081 miraclewisp/hperproteinaemia-maps'
            }
        }
    }
}