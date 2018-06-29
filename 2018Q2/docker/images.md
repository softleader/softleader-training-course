## Image

### Define a container with `Dockerfile`

```
FROM softleader/tomcat85

RUN mkdir -p ${TOMCAT_HOME}/webapps/ROOT
RUN echo "Hello Container!" > ${TOMCAT_HOME}/webapps/ROOT/index.html

CMD $TOMCAT_HOME/bin/catalina.sh run
```

> to see what's in [softleader/tomcat85](https://github.com/softleader/dockerfile/blob/master/tomcat85/Dockerfile)

### Build `Dockerfile` to image

```sh
# build to docker image
docker build -t mytomcat .

# let's take a deep look about layers of image
docker image inspect mytomcat
```

#### Images and layers

![](https://docs.docker.com/storage/storagedriver/images/container-layers.jpg)

> [https://docs.docker.com/storage/storagedriver/#images-and-layers](https://docs.docker.com/storage/storagedriver/#images-and-layers)

#### Make some changes in `Dockerfile`

```
FROM softleader/tomcat85

RUN mkdir -p ${TOMCAT_HOME}/webapps/ROOT \
	&& echo "Hello Container!" > ${TOMCAT_HOME}/webapps/ROOT/index.html

CMD $TOMCAT_HOME/bin/catalina.sh run
```

then rebuild image and inspect again...

```sh
docker build -t mytomcat .

docker image inspect mytomcat
```

#### `Dockerfile` 常用指令

- `FROM` - base image
- `COPY` - 複製當前目錄中的檔案到 container 中
- `ENV` - 宣告變數
- `VOLUME` - 宣告目錄
- `WORKDIR` - 設定當前目錄
- `RUN` - build layer 中執行的指令
- `CMD` - container 啟動時執行的指令

> [https://docs.docker.com/engine/reference/builder/](https://docs.docker.com/engine/reference/builder/)

### Quiz 1

Docker build 的時候才將 `index.html` 包進去

## Next steps

- Learn about [Containers](./containers.md)
