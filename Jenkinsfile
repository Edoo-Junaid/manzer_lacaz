pipeline {
    agent any
    options {
        skipDefaultCheckout true
    }
    stages {
        stage('Prompt for branch name') {
            steps {
                script {
                    def branch = input message: 'Enter the branch name:', parameters: [string(name: 'branch_name', defaultValue: 'master')]
                    println "Branch name entered: ${branch}"
                }
            }
        }
        stage('Check branch existence') {
            steps {
                script {
                    def branchName = 'my-branch'
                    git branch: '--all', name: 'allBranches'
                    def branchExists = allBranches.contains(branchName)
                    if (branchExists) {
                        echo "${branchName} exists!"
                    } else {
                        echo "${branchName} does not exist."
                    }
                }
            }
        }
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
                mvn versions:set -DnewVersion=1.0.$BUILD_NUMBER-SNAPSHOT
                cd /home/jenkins/workspace/manzerlacaz_pipeline_develop/manzerlacaz/manzerlacaz-web
                mvn versions:set -DnewVersion=1.0.$BUILD_NUMBER-SNAPSHOT
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
                mvn versions:set -DnewVersion=1.0.$BUILD_NUMBER
                cd /home/jenkins/workspace/manzerlacaz_pipeline_main/manzerlacaz/manzerlacaz-web
                mvn versions:set -DnewVersion=1.0.$BUILD_NUMBER
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
                cd /home/jenkins/workspace/manzerlacaz_pipeline_main/manzerlacaz/manzerlacaz-web/target
                java -jar manzerlacaz-web-1.0.$BUILD_NUMBER.jar
                echo "doing deploy stuff.."
                '''
            }
        }
        }
    }
