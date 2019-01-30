#! /bin/bash

if [[ ! -e /config/.git ]]; then
  cd /config && git init && git add . && git -c user.name='SoftLeader' -c user.email='support@softleader.com.tw' commit -m "initial" && cd -
fi

java -Xmx128m -Xms128m -Dspring.profiles.active=docker -Djava.security.egd=file:/dev/./urandom -jar /app.jar
