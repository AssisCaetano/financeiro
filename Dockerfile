# Usa uma imagem oficial do Tomcat 10 com JDK 17
FROM tomcat:10.1.20-jdk17-temurin

# Remove o aplicativo de exemplo que vem no Tomcat
RUN rm -rf /usr/local/tomcat/webapps/ROOT
RUN rm -rf /usr/local/tomcat/webapps/docs
RUN rm -rf /usr/local/tomcat/webapps/examples
RUN rm -rf /usr/local/tomcat/webapps/host-manager
RUN rm -rf /usr/local/tomcat/webapps/manager

# Copia seu arquivo .war para a pasta webapps do Tomcat
# O nome do arquivo será 'seu-projeto.war'
# A aplicação estará acessível em http://localhost:8080/seu-projeto/
# Ajuste o caminho 'target/seu-projeto.war' para o local onde seu .war é gerado.
COPY target/finopen.war /usr/local/tomcat/webapps/app.war

# Expõe a porta padrão do Tomcat
EXPOSE 8080


