#################
# Build the app #
#################
FROM node:18.12.1 as build

WORKDIR /app
COPY  package.json ./
RUN npm install
COPY . .
RUN npm install -g @angular/cli
RUN npm install rxjs
RUN ng build --configuration production --output-path=/dist/out

################
# Run in NGINX #
################
FROM nginx:alpine
COPY --from=build /dist/out /usr/share/nginx/html
COPY nginx-custom.conf /etc/nginx/conf.d/default.conf


EXPOSE 80

CMD ["/bin/sh",  "-c", "exec nginx -g 'daemon off;'"]