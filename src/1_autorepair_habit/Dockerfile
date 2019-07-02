FROM node:10.15.3-alpine as build

ARG COMMIT_SHA=<not-specified>
ENV NODE_ENV=production

WORKDIR /build-dir

COPY package*json ./

RUN npm install

COPY . .

RUN echo "service-name: $COMMIT_SHA" >> ./commit.sha

########################################################################################################################

FROM node:10.15.3-alpine

LABEL maintainer="Mia Platform Core Team<core@mia-platform.eu>" \
      name="1_autorepair_habit" \
      description="" \
      eu.mia-platform.url="https://www.mia-platform.eu" \
      eu.mia-platform.version="0.1.0"

ENV NODE_ENV=production
ENV LOG_LEVEL=debug
ENV SERVICE_PREFIX=/
ENV HTTP_PORT=3000
ENV USERID_HEADER_KEY=miauserid
ENV GROUPS_HEADER_KEY=miausergroups
ENV CLIENTTYPE_HEADER_KEY=miaclienttype
ENV BACKOFFICE_HEADER_KEY=isbackoffice
ENV MICROSERVICE_GATEWAY_SERVICE_NAME=microservice-gateway
ENV MONGODB_URL=mongodb://mongodb:27017/autorepair

WORKDIR /home/node/app

COPY --from=build /build-dir ./

HEALTHCHECK --start-period=5s --interval=5s --timeout=2s --retries=2 \
 CMD wget -qO- http://localhost:${HTTP_PORT}/-/healthz &> /dev/null || exit 1

USER node

CMD ["npm", "start", "--", "--options", "--port", "${HTTP_PORT}", "--log-level", "${LOG_LEVEL}", "--prefix=${SERVICE_PREFIX}", "--address", "0.0.0.0"]
