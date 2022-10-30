pipeline{
  agent any
  tools{
    maven "maven_home"
  }
  stages{
    stage('GitClone'){
      steps{
        git "https://github.com/KelNwosu/maven-web-application"
      }
    }
    stage('Test&Build'){
      steps{
        sh "mvn clean package"
      }
    }
    stage('CodeQuality'){
      steps{
        sh "mvn sonar:sonar"
      }
    }
    stage('UploadArtifact'){
      steps{
        sh "mvn deploy"
      }
    }
    stage('message'){
      steps{
        sh "echo CI job successful"
      }
    }
    stage('predeployment'){
      steps{
        sh "docker build -t kelnwosu/ebay-app."  
        sh "docker push kelnwosu/ebay-app"  
      }
    }
  }
}