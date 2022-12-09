pipeline {
    agent { 
        node {
            label 'slave01'
            }
      }

    stages {
        stage('Checkout') {
            steps {
                echo 'Checking out code from Git'
                checkout scm
            }
        }
        stage('Backup') {
            steps {
                echo 'Backing up code'
                sh '''
                echo "doing backup stuff.."
            
                '''
            }
        }
        stage('Build') {
            steps {
                echo "Building.."
                sh '''
                echo "doing build stuff.."
            
                '''
            }
        }
        stage('Test') {
            steps {
                echo "Testing.."
                sh '''
                echo "doing test stuff.."
            
                '''
            }
        }
        stage('Deliver') {
            steps {
                echo 'Deliver....'
                sh '''
                echo "doing delivery stuff.."
                '''
            }
        }
    }
}