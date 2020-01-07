# Fluent Bit

OpenShift 預設的 [Container Logs Aggregator](https://docs.openshift.com/container-platform/3.11/install_config/aggregate_logging.html#aggregated-fluentd) 是 EFK:

- **Elasticsearch** - Store, Search, and Analyze
- **Fluentd** - Log Collector, Processor, and Aggregator
- **Kibana** - Explore, Visualize, and Share

[Fluent Bit](https://fluentbit.io/) 跟 [Fluentd](https://www.fluentd.org/) 都是 [Treasure Data](https://www.treasuredata.com/opensource/) 的產品, 而 Fluent Bit 像是一個輕量級的 Fluentd, 專門處例 Log Collector 及 Processor, 而有較少的 Log Aggregator 功能

跟 Fluentd 相比效能更好, 不論 CPU 跟記憶體的使用率 Fluent Bit 都有更好的表現, 因此在公司轉換 OpenShfit 之前, 我們將使用 Fluent Bit 來取代 Logstach 來收集公司微服務中的 Log

> 延伸閱讀: [Fluent-bit rocks](https://gist.github.com/StevenACoffman/4e267f0f60c8e7fcb3f77b9e504f3bd7)

## Getting Started

Fluent Bit 在公司微服務環境中所負責的只在收集我們自己撰寫的 Ap Log, 並非整個 Docker 環境或 Kubernetes 環境的 Log, 因此我們定義了一些規則, 只有符合下述規則的 Log 才會被收集出來送到 Elasticsearch 上:

1. Log 是輸出在 Ap 的 Console (STDOUT) 中
2. Log 的內容必須是 JSON 格式, 且不可斷行
3. 每一行不可超過 32k
4. 每一行的 JSON 中必須要有 `app_name` key, 且其 value 不可為空

此篇接下來會介紹及建議在一個 Spring Boot App 中要怎麼配置, 可以簡單的達到上述條件!

> 延伸閱讀: [公司 fluent-bit 的配置](https://github.com/softleader/log-forwarder/tree/master/fluent-bit/)

### pom.xml

我們建議使用 [logstash/logstash-logback-encoder](https://github.com/logstash/logstash-logback-encoder) 來做輸出 JSON 的 encoder, 因此在 `pom.xml` 中請加上:

```xml
<dependency>
	<groupId>net.logstash.logback</groupId>
	<artifactId>logstash-logback-encoder</artifactId>
	<version>${logstash-logback-encoder.version}</version>
</dependency>
```
> 請從 [Logstash Logback Encoder - Maven Repository](https://mvnrepository.com/artifact/net.logstash.logback/logstash-logback-encoder) 選擇版本

### logback.xml

在 `logback.xml` 中, 我們先加上以下 appender

```xml
<springProperty scope="context" name="appName" source="spring.application.name"/>

<appender class="ch.qos.logback.core.ConsoleAppender" name="JSON">
	<encoder class="net.logstash.logback.encoder.LoggingEventCompositeJsonEncoder">
		<providers>
			<mdc/>
			<timestamp>
				<timeZone>Asia/Taipei</timeZone>
			</timestamp>
			<logLevel/>
			<threadName/>
			<loggerName>
				<fieldName>class_name</fieldName>
			</loggerName>
			<message/>
			<pattern>
				<pattern>
				{
				"app_name": "${appName:-}",
				"pid": "${PID:-}"
				}
				</pattern>
			</pattern>
			<stackTrace>
				<throwableConverter class="net.logstash.logback.stacktrace.ShortenedThrowableConverter">
					<maxDepthPerThrowable>30</maxDepthPerThrowable>
					<maxLength>10240</maxLength>
					<!-- 最多帶 10k 的 stack trace, 超過應該也沒用了 -->
					<shortenedClassNameLength>20</shortenedClassNameLength>
					<rootCauseFirst>true</rootCauseFirst>
					<!-- 將 root cause 擺最前面, 避免超過 10k 被 trim 掉 -->
				</throwableConverter>
			</stackTrace>
		</providers>
	</encoder>
</appender>
```

> 延伸閱讀: [Providers 的配置說明](https://github.com/logstash/logstash-logback-encoder#providers-for-loggingevents)

最後在 root 的地方, 建議透過指定 Spring Profile 才啟動開 JSON appender, 以下範例是當啟動 spring profile `efk`  時, 就停用所有其他的 appender 只開啟 json appender

```xml
<root level="info">
	<springProfile name="!efk">
		<appender-ref ref="STDOUT" />
		<appender-ref ref="FILE" />
		...
	</springProfile>
	<springProfile name="efk">
		<appender-ref ref="JSON" />
	</springProfile>
	...
</root>
```
