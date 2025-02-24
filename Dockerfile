FROM eclipse-temurin:21.0.5_11-jdk

ARG USER=monitor
ARG UID=1001
ARG GID=1001
ARG APP_HOME=/app
ARG APP_FILE="entrypoints-0.0.1-SNAPSHOT.jar"

ENV TZ="America/Bogota" \
    LC_ALL=es_CO.UTF-8

RUN mkdir -p ${APP_HOME} && \
    chmod 777 -R ${APP_HOME} && \
    addgroup --system --gid ${GID} ${USER} && \
    adduser --disabled-password \
    --gecos "" \
    --home "/home/${USER}" \
    --ingroup "${USER}" \
    --no-create-home \
    --uid "${UID}" \
    "${USER}" && \
    chown -R ${USER}:${USER} ${APP_HOME}

WORKDIR ${APP_HOME}

COPY --chown=${USER}:${USER} infrastructure/entrypoints/build/libs/${APP_FILE} app.jar

RUN chmod -R 777 ${APP_HOME} && \
    rm -rf /var/cache/apk/* && \
    rm -rf /tmp/*

USER ${USER}

EXPOSE 9090
ENTRYPOINT ["java", "-jar", "/app/app.jar"]