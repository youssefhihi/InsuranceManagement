pipeline {
    agent any
    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        stage('Build Docker Image') {
            steps {
                sh 'docker build -t InsuranceManagement .'
            }
        }
        stage('Run Application') {
            steps {
                sh 'docker-compose up -d'
            }
        }
        stage('Clean Up') {
            steps {
                sh 'docker-compose down'
            }
        }
    }
}
