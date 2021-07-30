FROM node:14-alpine As build-env
WORKDIR /tmp/workspace
# dependency layer
COPY ui-demo/package.json ui-demo/yarn.lock ./
RUN yarn
# build layer
COPY ui-demo/src ./src
COPY ui-demo/public ./public
RUN yarn build

FROM nginx:1.21.1
COPY --from=build-env /tmp/workspace/build /usr/share/nginx/html