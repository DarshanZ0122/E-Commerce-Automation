pipeline {
    agent any

    tools {
        // Use the tools you configured in Jenkins
        maven 'maven-3.9'
        jdk 'jdk-17'
    }

    stages {

        stage('Checkout Code') {
            steps {
                echo "Pulling code from GitHub..."
                git branch: 'main', url: 'https://github.com/DarshanZ0122/E-Commerce-Automation.git'
            }
        }

        stage('Install Dependencies') {
            steps {
                echo "Running Maven clean..."
                sh 'mvn clean'
            }
        }

        stage('Run TestNG Tests') {
            steps {
                echo "Running tests..."
                sh 'mvn test'
            }
        }

        stage('Publish Reports') {
            steps {
                echo "Publishing TestNG / JUnit reports..."
            }
        }
    }

    post {
        always {
            // TestNG generates XML reports inside target/surefire-reports/
            junit '**/target/surefire-reports/*.xml'
        }
    }
}
