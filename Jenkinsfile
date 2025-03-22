pipeline {
    agent any
    stages {
        stage('Checkout') {
            steps {
                git branch: 'master', url: 'https://github.com/DarshanZ0122/E-Commerce-Automation.git'
            }
        }
        stage('Build') {
            steps {
                sh 'mvn clean test'
            }
        }
        stage('Publish Report') {
            steps {
                publishTestNGResult(pattern: '**/test-output/testng-results.xml')
            }
        }
    }
}
