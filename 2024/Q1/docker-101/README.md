# run docker container
```shell
# run 在前景(ctrl+c可以結束)
docker run nginx:1.25.4-alpine
# run 在背景
docker run -d nginx:1.25.4-alpine
# 映射port
docker run -d -p 11180:80 nginx:1.25.4-alpine
# 映射port, mount目錄
docker run -d -p 11180:80 --mount "type=bind,source=<有部屬檔的目錄>,target=/usr/share/nginx/html" nginx:1.25.4-alpine
# 用 docker compose 啟動
docker compose up -d
```

```shell
# 看目前正在運行的container
docker ps
# 看目前正在運行的container log
docker logs <container_id>
# 進入container環境進行除錯
docker exec -it <container_id> sh
docker exec -it <container_id> bash
```

refs:
- https://www.docker.com/products/docker-desktop/
- https://hub.docker.com/
- https://docs.docker.com/compose/
- 
- https://github.com/docker/awesome-compose