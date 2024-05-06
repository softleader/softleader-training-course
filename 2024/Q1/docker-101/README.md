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

# Dockerfile
https://docs.docker.com/reference/dockerfile/

> 以下為AI給的範例

好的，以下是一個簡單的Dockerfile例子，可以滿足你的需求：

```Dockerfile
# 使用Ubuntu 22.04作為基礎鏡像
FROM ubuntu:22.04

# 安裝nginx
RUN apt-get update && \
apt-get install -y nginx && \
rm -rf /var/lib/apt/lists/*

# 將nginx配置檔案複製到容器中
COPY nginx.conf /etc/nginx/nginx.conf

# 暴露80端口
EXPOSE 80

# 啟動nginx
CMD ["nginx", "-g", "daemon off;"]
```
解釋一下：

FROM ubuntu:22.04指定使用Ubuntu 22.04作為基礎鏡像。
RUN apt-get update && apt-get install -y nginx更新apt源並安裝nginx。rm -rf /var/lib/apt/lists/*刪除apt快取以減小鏡像大小。
COPY nginx.conf /etc/nginx/nginx.conf將你自定義的nginx配置檔案複製到容器的/etc/nginx/nginx.conf路徑下。你需要在構建上下文中提供nginx.conf檔案。
EXPOSE 80暴露80端口，允許通過80端口訪問nginx服務。
CMD ["nginx", "-g", "daemon off;"]指定容器啟動時運行的命令，這裡是以前台方式運行nginx。
使用方法：

在同一目錄下創建一個nginx.conf檔案，內容為你的nginx配置。
將Dockerfile和nginx.conf檔案放在同一個目錄中。
在該目錄下運行docker build -t my-nginx .構建鏡像。
運行docker run -d -p 80:80 my-nginx啟動容器。
這樣就能獲得一個運行nginx的Docker容器了。你可以根據需求修改nginx.conf來定制nginx配置。

nginx.conf是Nginx的主配置檔案，用於配置Nginx的運行參數和處理HTTP請求等。以下是一個簡單的nginx.conf檔案內容示例：

```nginx
events {
worker_connections 1024;
}

http {
server {
listen 80;
server_name localhost;

        location / {
            root /usr/share/nginx/html;
            index index.html index.htm;
        }
    }
}
```
這個配置檔案包含兩個核心部分：

events 塊用於配置影響 Nginx 服務自身的連接處理方式。worker_connections 1024 設置每個 worker 進程可以同時處理的最大連接數為 1024。
http 塊用於配置 HTTP 伺服器的各種參數。
server 塊用於配置一個虛擬主機，處理 HTTP 請求。
listen 80 監聽 80 端口的 HTTP 請求。
server_name localhost 指定虛擬主機名為 localhost。
location 塊用於匹配 URI，並指定如何處理對應的請求。這裡 location / 匹配所有請求，root /usr/share/nginx/html 指定伺服器資源檔案的根目錄，index 指定目錄的索引檔案名稱。
這是一個最小化的 Nginx 配置，它將 Nginx 配置為一個簡單的 Web 伺服器，用於服務靜態檔案。你可以根據需求修改和擴展該配置檔案，比如配置反向代理、SSL、gzip 壓縮等高級功能。Nginx 的官方文件詳細介紹了各種指令的用法：http://nginx.org/en/docs/

你需要將這個 nginx.conf 檔案放在與 Dockerfile 同一目錄下，以便在構建鏡像時被複製到容器中。