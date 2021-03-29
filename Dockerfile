
#ls | grep -v "app.jar" | xargs rm -rf 
#comando 3 acima é para excluir todos os arquivos que contém na pasta que ele está, exceto o que está ENTRE ASPAS

FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY . /tmp_build
WORKDIR /tmp_build
RUN ./mvnw -T 1C clean install -Dmaven.test.skip -DskipTests -Dmaven.javadoc.skip=true \
    && cp ${JAR_FILE} /app.jar \
    && rm -fr *
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]