
### build do Java ###
./mvnw package && java -jar target/validarCpf.jar

### Gerar imagem Docker ###
docker build -t heltonscampos/validadorcpf-concrete -f Dockerfile .

### Rodar  imagem Docker e rodar localmente ###
docker run -d -p 8081:8080 --name validadorcpf-concrete heltonscampos/validadorcpf-concrete

### Criar a tag apontando para o repositório do Docker Hub (hub.docker.com/repository/docker/heltonscampos/validadorcpf-concrete) ###
docker tag heltonscampos/validadorcpf-concrete hub.docker.com/repository/docker/heltonscampos/validadorcpf-concrete

### subir para o dockerhub ###
docker push heltonscampos/validadorcpf-concrete

### instalar o gitlab runner
docker run -d --name gitlab-runner --restart always  -v /Users/Shared/gitlab-runner/config:/etc/gitlab-runner  -v /var/run/docker.sock:/var/run/docker.sock  gitlab/gitlab-runner:latest

### Criar volume para o gitlab runner
docker volume create gitlab-runner-config

### comando para atachar(entrar) no container
docker exec -it gitlab-runner bash

# Rodar o comando "gitlab-runner register" dentro do container
gitlab-runner register

### Depois de executar o comando de registar, vai configurar o runner para se comunicar com o gitlab. Vai em SETTINGS->CI->RUNNER. O comnado pedirá qual a URL do gitlab, depois pedirá o token (AMBOS ESTÃO DISPONÍVEIS NO RUNNER QUE FOI VISTO ANTERIORMENTE). Após, coloca um nome para este runner, uma tag. Depois deve-se definir que tipo de imagem, aqui foi DOCKER  e depois a imagem padrão do runner, que aqui foi a OPENJDK.