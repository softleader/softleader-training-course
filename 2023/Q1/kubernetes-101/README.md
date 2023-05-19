# 認識Kubernetes
- 錄影檔: https://drive.google.com/file/d/1qWw74BEcW9NctMPOsKNcbpY8ppiIJO7C/view?usp=share_link
- Docker指令
  - 取得image: `docker pull tomcat:8.5.88-jdk8`
  - 啟動container: `docker run -p 18080:8080 tomcat:8.5.88-jdk8`
  - 觀看已啟動的containers: `docker ps`
  - 監看已啟動的containers: `docker stats`
  - 強制移除正在運行的container: `docker rm -f <CONTAINER_ID>`