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
                    def result = sh(script: "git ls-remote --heads origin ${branch}", returnStdout: true)
                    if (result == '') {
                        error "Branch '${branch}' does not exist."
          } else {
                        println "Branch '${branch}' exists."
                    }
                }
            }
        }
        stage('Check branch existence') {
            steps {
                script {
                    def result = sh(script: "git ls-remote --heads origin ${branch}", returnStdout: true)
                    if (result == '') {
                        error "Branch '${branch}' does not exist."
          } else {
                        println "Branch '${branch}' exists."
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
