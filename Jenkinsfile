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
            when{
                branch 'master'
            }
            steps {
                echo "The build number is ${env.BUILD_NUMBER}"
                echo 'Backing up code'
                sh '''
                cd /home/jenkins/workspace
                #cp -r manzerlacaz_pipeline /home/jenkins/backup
                #cd /home/jenkins/backup
                #mv manzerlacaz_pipeline manzer_lacaz_backup_$BUILD_NUMBER
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
                cd /home/jenkins/workspace/manzerlacaz_pipeline/manzerlacaz/manzerlacaz-parent
                mvn clean install
                '''
            }
        }
        stage('Deliver') {
            when{
                branch 'master'
            }
            steps {
                echo 'Deliver....'
                sh '''
                cd /home/jenkins/workspace/manzerlacaz_pipeline/
                docker-compose up -d
                '''
            }
        }

    }
}
