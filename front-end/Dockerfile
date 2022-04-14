

### STAGE 1: Build ###
FROM node:14.18-slim AS build
WORKDIR /usr/src/app
COPY package*.json ./
RUN npm install
COPY . .
RUN npm audit fix --force
RUN npm run build

### STAGE 2: Run ###
FROM nginx:1.17.1-alpine
COPY nginx/default.conf /etc/nginx/conf.d/default.conf
COPY --from=build /usr/src/app/dist/front-end /usr/share/nginx/html
EXPOSE 80
