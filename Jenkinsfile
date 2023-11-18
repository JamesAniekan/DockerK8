pipeline{
    agent any
    tools{
      maven '3.9.4'
    }
    stages{
        stage('Build and package'){
            steps{
                sh 'mvn clean package'
            }
        }
        stage('Build image'){
            steps{
                sh 'docker build -t anijames/my-dockerk8-img:latest .'
            }
        }
        stage('Push to dockerhub'){
            steps{
                    script{
                        withCredentials([string(credentialsId: 'dockerhubpass', variable: 'dockerhubpass')]) {
                            sh 'docker login -u anijames -p ${dockerhubpass}'
                        }
                        sh 'docker push anijames/my-dockerk8-img:latest'
                    }
            }
        }
        stage('K8 credentials configuration'){
            steps{
                script{
                    withCredentials([file(credentialsId: 'my-k8-credentials', variable:'KUBECONFIG')]){
                        sh 'export KUBECONFIG=$(pwd)/$KUBECONFIG'
                    }
                }
            }
        }
        stage('Run deployment file'){
            steps{
                script{
                    sh 'kubectl --kubeconfig=KUBECONFIG apply -f my-deployment,yaml'
                    sh 'kubectl --kubeconfig=KUBECONFIG get pods'
                }
            }
        }
    }

}