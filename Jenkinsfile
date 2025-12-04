pipeline {
  agent any
  stages {
    stage('Checkout') {
      steps {
        checkout scm
      }
    }
    stage('Build') {
      steps {
        sh './mvnw -DskipTests package'
      }
    }
    stage('Run MVC Test') {
      steps {
        // Run only the WebMvc integration test
        sh './mvnw -Dtest=EntegreTest test'
      }
    }
  }
  post {
    always {
      junit 'target/surefire-reports/*.xml'
    }
  }
}

