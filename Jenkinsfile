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
                branch 'main'
            }
            steps {
                echo "The build number is ${env.BUILD_NUMBER}"
                echo 'Backing up code'
                sh '''
                timestamp=$(date +%Y-%m-%d_%H-%M-%S)
                mkdir /home/jenkins/backup/$timestamp
                cd /home/jenkins/workspace/manzerlacaz_pipeline_main/manzerlacaz/manzerlacaz-web/target
                find . -name "*.jar" -exec cp {} /home/jenkins/backup/timestamp
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
        stage('Build for devlopment') {
            when{
                branch 'develop'
            }
            steps {
                echo 'Building..'
                sh '''
                echo "doing build stuff.."
                cd /home/jenkins/workspace/manzerlacaz_pipeline_develop/manzerlacaz/manzerlacaz-parent
                mvn --batch-mode release:update-versions -DdevelopmentVersion=1.$BUILD_NUMBER-SNAPSHOT
                mvn clean install
                '''
            }
        }
        stage('Build for production') {
            when {
                branch 'main'
            }
            steps {
                /* groovylint-disable-next-line DuplicateStringLiteral */
                echo 'Building..'
                sh '''
                echo "doing build stuff.."
                cd /home/jenkins/workspace/manzerlacaz_pipeline_main/manzerlacaz/manzerlacaz-parent
                mvn clean install
                '''
            }
        }
        stage('Deploy') {
            when {
                branch 'main'
            }
            steps {
                echo 'Deliver....'
                sh '''
                cd /home/jenkins/workspace/manzerlacaz_pipeline_main/
                echo "doing deploy stuff.."
                '''
            }
        }
    }
}
