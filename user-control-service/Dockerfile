# Usar imagem base com JDK 17
FROM openjdk:17-jdk-slim

# Diretório de trabalho no container
WORKDIR /app

# Copiar todos os arquivos do projeto para dentro do container
COPY src .

# Rodar build do projeto, pulando testes
RUN ./mvnw clean package -DskipTests

# Expor a porta que a aplicação vai rodar
EXPOSE 8080

# Comando para iniciar a aplicação (ajuste o nome do JAR se necessário)
CMD ["java", "-jar", "target/user-control-service-0.0.1-SNAPSHOT.jar"]
