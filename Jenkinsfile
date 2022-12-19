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
                branch 'develop'
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
                echo 'Building..'
                sh '''
                echo "doing build stuff.."
                cd /home/jenkins/workspace/manzerlacaz_pipeline_main/manzerlacaz/manzerlacaz-parent
                mvn --batch-mode release:update-versions -DdevelopmentVersion=1.$BUILD_NUMBER
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
