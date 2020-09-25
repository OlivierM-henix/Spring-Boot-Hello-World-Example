pipeline {
    agent any
    
    tools {
        maven 'Mon maven'
    }
    
    stages {
        
        stage('Checkout') {
            steps {
                echo "-=- Checkout project -=-"
                git url: 'https://github.com/zaba221/Spring-Boot-Hello-World-Example.git'
            }
        }
        
        stage('Package') {
            steps {
                echo "-=- packaging project -=-"
                sh 'mvn clean package -Dmaven.test.skip=true'
            }
            
        }


        stage('Test') {
            steps {
                echo "-=- Test project -=-"
                sh 'mvn clean test'
            }
            
            post {
                success {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }     
        stage('Code coverage') {
            steps {
                jacoco( 
                      execPattern: 'target/*.exec',
                      classPattern: 'target/classes',
                      sourcePattern: 'src/main/java',
                      exclusionPattern: 'src/test*'
                )
            }
        }
        stage('Sanity check') {
          steps {
            echo "-=- Sanity Check Test project -=-"
            sh 'mvn clean install checkstyle:checkstyle pmd:pmd'
          }
          post {
            always {
              recordIssues enabledForFailure: true, tools: [checkStyle()]
              recordIssues enabledForFailure: true, tool: pmdParser(pattern: '**/target/pmd.xml')
            }
          }
        }	
		stage('Quality Analysis Sonarqube') {
            environment {
                SCANNER_HOME = tool 'mon_sonarqube_scanner'
                ORGANIZATION = "EQL"
                PROJECT_NAME = "SpringBootProject_2"
            }
            steps {
                withSonarQubeEnv('mon_sonar') {
                    sh '''$SCANNER_HOME/bin/sonar-scanner \
                    -Dsonar.java.sources=src \
                    -Dsonar.java.binaries=target \
                    -Dsonar.projectKey=$PROJECT_NAME \
                    -Dsonar.language=java \
                    -Dsonar.sourceEncoding=UTF-8'''
                }
            }
        }
   		stage('Continuous delivery') {
			steps {
             script {
              sshPublisher(
               continueOnError: false, failOnError: true,
               publishers: [
                sshPublisherDesc(
                 configName: "docker-host",
                 verbose: true,
                 transfers: [
                  sshTransfer(
                   sourceFiles: "/var/lib/jenkins/.m2/repository/com/reytech/demo/0.0.1-SNAPSHOT/demo-0.0.1-SNAPSHOT.jar",
                   removePrefix: "/var/lib/jenkins/.m2/repository/com/reytech/demo/0.0.1-SNAPSHOT/",
                   remoteDirectory: "/tmp",
                   execCommand: """
                    sudo mv /tmp/demo-0.0.1-SNAPSHOT.jar /home/vagrant/project;
                    cd projet;
                    sudo docker build -t springbootapp1 . ;
                    docker tag springbootapp1 terranersatz/springbootapp1:1.0
                    docker push terranersatz/springbootapp1:1.0 """
                  )
                 ])
               ])
             }
          }
        } 
        stage('Continuous deployment') {
          steps {
             script {
              sshPublisher(
               continueOnError: false, failOnError: true,
               publishers: [
                sshPublisherDesc(
                 configName: "docker-host",
                 verbose: true,
                 transfers: [
                  sshTransfer(
                   sourceFiles: "",
                   removePrefix: "",
                   remoteDirectory: "",
                   execCommand: """
                    sudo docker stop \$(docker ps -a -q);
                    sudo docker rm \$(docker ps -a -q);
                    sudo docker rmi -f \$(docker images -a -q);
                    sudo docker run -d -p 8080:8080 terranersatz/springbootapp1:1.0; """
                  )
                 ])
               ])
            }
        }
    }
		stage('Checkout Selenium') {
            steps {
                echo "-=- Checkout project -=-"
                git url: 'https://github.com/OlivierM-henix/example-springboot-automation-test-selenium.git'
            }
        }
        stage('Selenium Test Job') {
            steps {
                 build job: 'projet_selenium'
            }
		}
	}
}
