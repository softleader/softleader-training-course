# 認識Kubernetes

## 第一堂: 介紹 Container 技術
- 錄影檔: https://drive.google.com/file/d/1qWw74BEcW9NctMPOsKNcbpY8ppiIJO7C/view?usp=share_link
- Docker指令
   - 取得image: `docker pull tomcat:8.5.88-jdk8`
   - 啟動container: `docker run -p 18080:8080 tomcat:8.5.88-jdk8`
   - 觀看已啟動的containers: `docker ps`
   - 監看已啟動的containers: `docker stats`
   - 強制移除正在運行的container: `docker rm -f <CONTAINER_ID>`


## 第二堂: 實操啟動並運行 Container
- 錄影檔: https://drive.google.com/file/d/148e-Zf6kpH_Xv8AceCe5X32GSxK5gmpN/view?usp=share_link
- Docker指令
  - tomcat container 內的工作目錄: `/usr/local/tomcat`
  - 自 image 啟動 container: `docker run -p 18080:8080 -v "C:\develop\projects\training-k8s-series\000-container\webapp:/usr/local/tomcat/webapps" tomcat:9.0.75-jdk17`
    > -v "C:\develop\projects\training-k8s-series\000-container\webapp:/usr/local/tomcat/webapps"
    > 此目錄需根據自己的本機目錄改變 
  - 於 container 內部執行指令並直接印在 console: `docker exec <CONTAINER_ID> cat /etc/os-release`
  - 至 container 內部執行指令操作: `docker exec -it <CONTAINER_ID> bash`
  - 至 container 內部執行指令操作(備選): `docker exec -it <CONTAINER_ID> sh`
  - 包 image: `docker build -t "localhost/tomcat-demo:9.0.75-jdk17" .`
    > 須注意這必須在 [001-image](001-image) 目錄下執行
  - 自 image 啟動 container: `docker run -p 18080:8080 localhost/tomcat-demo:9.0.75-jdk17`
  - 將 image 匯出為 tar檔: `docker save -o tomcat-demo_9.0.75-jdk17.tar localhost/tomcat-demo:9.0.75-jdk17`
  - 將 tar檔 匯入為 image: `docker load -i tomcat-demo_9.0.75-jdk17.tar`
