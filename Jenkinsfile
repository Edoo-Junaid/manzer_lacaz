pipeline {
    agent {
        node {
            label 'slave01'
        }
    }
    options {
        skipDefaultCheckout true
    }

    stages {
        stage('Backup') {
            steps {
                echo "The build number is ${env.BUILD_NUMBER}"
                echo 'Backing up code'
                sh '''
                cd /home/jenkins/workspace
                cp -r manzer_lacaz_pipeline /home/jenkins/backup
                cd /home/jenkins/backup
                mv manzer_lacaz_pipeline manzer_lacaz_backup_$BUILD_NUMBER
                echo "doing backup stuff.."
                '''
            }
        }
        stage('Checkout') {
            steps {
                echo 'Checking out code from Git'
                checkout scm
            }
        }
        stage('Build') {
            steps {
                echo 'Building..'
                sh '''
                echo "doing build stuff.."
                cd /home/jenkins/workspace/manzer_lacaz_pipeline/manzerlacaz/manzerlacaz-parent
                mvn clean install
                cd /home/jenkins/workspace/manzer_lacaz_pipeline/manzerlacaz/manzerlacaz-web
                docker build -t edoojunaid/manzerback:$BUILD_NUMBER .
                docker build edoojunaid/manzerback:lastest .
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
