# Multi-stage build: Maven build -> Tomcat runtime

FROM maven:3.9.9-eclipse-temurin-11 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn -q -e -B -DskipTests dependency:go-offline
COPY src ./src
RUN mvn -q -e -B -DskipTests clean package

FROM tomcat:9.0-jdk11-temurin

# Ensure JSP compilation works and clean default webapps
RUN rm -rf /usr/local/tomcat/webapps/*

# Copy built WAR as ROOT.war for root context
COPY --from=build /app/target/HostelBookingSystem.war /usr/local/tomcat/webapps/ROOT.war

# Expose default Tomcat port
EXPOSE 8080

# Environment variables for DB configuration (can be overridden)
ENV DB_HOST=db \
    DB_PORT=3306 \
    DB_NAME=hostel_booking_system \
    DB_USER=root \
    DB_PASS=change_me

# Healthcheck: wait for Tomcat to respond
HEALTHCHECK --interval=30s --timeout=5s --retries=5 CMD curl -fsS http://localhost:8080/ || exit 1

CMD ["catalina.sh", "run"]


