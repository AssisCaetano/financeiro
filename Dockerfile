# --- STAGE 1: O Ambiente de Build ---
# Usa uma imagem com Maven e Java para compilar o projeto.
FROM maven:3.9.6-eclipse-temurin-17 AS builder

# Define o diretório de trabalho dentro do container.
WORKDIR /app

# Copia os arquivos de configuração (pom.xml) e o código-fonte.
# Isso permite que o Maven baixe as dependências e compile o código.
COPY pom.xml .
COPY src ./src

# Compila o projeto e gera o WAR na pasta target.
RUN mvn clean package -DskipTests

# --- STAGE 2: O Ambiente de Execução ---
# Usa uma imagem leve do Tomcat com apenas o JRE para rodar a aplicação.
FROM tomcat:10.1.20-jdk17-temurin

# Remove o aplicativo de exemplo padrão.
RUN rm -rf /usr/local/tomcat/webapps/ROOT

# Copia o arquivo WAR gerado no "builder" para a pasta de webapps do Tomcat.
# A aplicação estará acessível em http://seuservico.render.com/app.
COPY --from=builder /app/target/finopen.war /usr/local/tomcat/webapps/app.war

# Expõe a porta padrão do Tomcat para o Render.
EXPOSE 8080

# # Usa uma imagem oficial do Tomcat 10 com JDK 17
# FROM tomcat:10.1.20-jdk17-temurin

# # Remove o aplicativo de exemplo que vem no Tomcat
# RUN rm -rf /usr/local/tomcat/webapps/ROOT
# RUN rm -rf /usr/local/tomcat/webapps/docs
# RUN rm -rf /usr/local/tomcat/webapps/examples
# RUN rm -rf /usr/local/tomcat/webapps/host-manager
# RUN rm -rf /usr/local/tomcat/webapps/manager

# # Copia seu arquivo .war para a pasta webapps do Tomcat
# # O nome do arquivo será 'seu-projeto.war'
# # A aplicação estará acessível em http://localhost:8080/seu-projeto/
# # Ajuste o caminho 'target/seu-projeto.war' para o local onde seu .war é gerado.
# COPY target/finopen.war /usr/local/tomcat/webapps/app.war

# # Expõe a porta padrão do Tomcat
# EXPOSE 8080


