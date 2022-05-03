pipeline {
  agent any
  stages {
    stage('Build') {
      parallel {
        stage('Build') {
          steps {
            sh 'mvn clean install -Dlicense.skip=true'
          }
        }

        stage('Doc') {
          steps {
            sh 'mvn javadoc:javadoc'
          }
        }

      }
    }

    stage('Test') {
      steps {
        sh 'mvn test'
      }
    }

    stage('Static Analyze') {
      steps {
        sh 'mvn jdepend:generate'
      }
    }

    stage('End') {
      steps {
        echo 'Th&t\'s all folks'
      }
    }

  }
}