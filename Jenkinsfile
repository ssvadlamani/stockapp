node {    
      def app     
      stage('Clone repository') {               
             
            checkout scm    
      }  
      stage('Compile and build application') {
                bat 'mvn clean install -Dmaven.test.skip=true'
    }
      stage('Build image') {         
            bat 'docker build -t stock-app .'
            bat 'docker tag stock-app sivasankarvadlamani/stockapp'   
       } 
           
       stage('Push image') {
       withDockerRegistry([ credentialsId: "docker-hub-credentials", url: "https://registry.hub.docker.com" ]) {
       bat 'docker push sivasankarvadlamani/stockapp:build'
      
      }    
   }
}       
