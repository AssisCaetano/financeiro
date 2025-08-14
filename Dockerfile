# --- ESTÁGIO DE BUILD (FAZ A COMPILAÇÃO DO SEU PROJETO) ---
# Usa uma imagem do Maven para compilar o projeto.
# 'AS build-stage' dá um nome a este estágio para podermos referenciá-lo depois.
FROM maven:3.8.7-eclipse-temurin-17 AS build-stage

# Define o diretório de trabalho dentro do container.
WORKDIR /app

# Copia o arquivo pom.xml para que o Docker possa baixar as dependências.
COPY pom.xml .

# Baixa as dependências. Se o pom.xml não mudar, o Docker usa o cache.
RUN mvn dependency:go-offline

# Copia o código-fonte restante e compila a aplicação.
COPY src ./src
RUN mvn package

# --- ESTÁGIO FINAL (CRIA A IMAGEM DE EXECUÇÃO) ---
# Usa a imagem do Tomcat oficial, que será a base da nossa imagem final.
FROM tomcat:10.1.20-jdk17-temurin

# Remove os aplicativos de exemplo do Tomcat, como você já estava fazendo.
RUN rm -rf /usr/local/tomcat/webapps/ROOT \
    /usr/local/tomcat/webapps/docs \
    /usr/local/tomcat/webapps/examples \
    /usr/local/tomcat/webapps/host-manager \
    /usr/local/tomcat/webapps/manager

# Copia o arquivo .war do ESTÁGIO DE BUILD para a pasta webapps do Tomcat.
# A instrução '--from=build-stage' é o segredo aqui!
COPY --from=build-stage /app/target/financeiro-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/app.war

# Expõe a porta padrão do Tomcat.
EXPOSE 8080


