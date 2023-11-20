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
//                 script{
//                     withCredentials([file(credentialsId: 'my-k8-credentials', variable:'KUBE_CONFIG')]){
//                         //sh 'export KUBE_CONFIG=$(pwd)/$KUBE_CONFIG'
//                         sh 'kubectl --kubeconfig=${KUBE_CONFIG} apply -f my-deployment,yaml'
//                         sh 'kubectl --kubeconfig=${KUBE_CONFIG} get pods'
//                     }
//                }
                withKubeConfig(caCertificate: '', clusterName: '', contextName: '', credentialsId: 'my-k8-credentials', namespace: '', restrictKubeConfigAccess: false, serverUrl: 'https://192.168.49.2:8443') {
                    sh 'kubectl apply -f my-deployment.yaml'
                }
            }
        }

    }

}