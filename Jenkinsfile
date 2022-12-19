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
            when {
                branch 'main'
            }
            steps {
                echo "The build number is ${env.BUILD_NUMBER}"
                echo 'Backing up code'
                sh '''
                timestamp=$(date +%Y-%m-%d_%H-%M-%S)
                backup_dir="/home/jenkins/backup/$timestamp"
                mkdir "${backup_dir}"
                jar_file=$(find /home/jenkins/workspace/manzerlacaz_pipeline_main/manzerlacaz/manzerlacaz-web/target -name "*.jar")
                cp "${jar_file}" "${backup_dir}"
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
            when {
                branch 'develop'
            }
            steps {
                echo 'Building..'
                sh '''
                echo "doing build stuff.."
                cd /home/jenkins/workspace/manzerlacaz_pipeline_develop/manzerlacaz/manzerlacaz-parent
                mvn versions:set -DnewVersion=1.0.$BUILD_NUMBER
                cd /home/jenkins/workspace/manzerlacaz_pipeline_develop/manzerlacaz/manzerlacaz-web
                mvn versions:set -DnewVersion=1.0.$BUILD_NUMBER
                cd /home/jenkins/workspace/manzerlacaz_pipeline_develop/manzerlacaz/manzerlacaz-parent
                mvn clean install
                '''
            }
        }
        stage('Build for production') {
            when {
                branch 'main'
            }
            steps {
                echo 'Building..'
                sh '''
                echo "doing build stuff.."
                cd /home/jenkins/workspace/manzerlacaz_pipeline_main/manzerlacaz/manzerlacaz-parent
                mvn versions:set -DnewVersion=1.0.$BUILD_NUMBER-SNAPSHOT
                cd /home/jenkins/workspace/manzerlacaz_pipeline_main/manzerlacaz/manzerlacaz-web
                mvn versions:set -DnewVersion=1.0.$BUILD_NUMBER-SNAPSHOT
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
                jar_file = $(find /home/jenkins/workspace/manzerlacaz_pipeline/manzerlacaz/manzerlacaz-web/target -name "*.jar")
                java -jar "${jar_file}"
                echo "doing deploy stuff.."
                '''
            }
        }
    }
}
