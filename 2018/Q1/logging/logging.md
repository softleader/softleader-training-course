
# Better Logging

---

# Logging Levels?

- TRACE
- DEBUG
- INFO
- ERROR
- ...

---

<script src="https://gist.github.com/shihyuho/7ac6de63ad7172be8e32f70c5984d206.js"></script>

---

<script src="https://gist.github.com/shihyuho/0ec50b07ed61eec8a57a70b98df00528.js"></script>

---

# Think More

![](./thinking.png)

--- 

# Getting Started to Log More

![](./470983-write.png)

---


# Tip1 - Include contextual data

---

![](./timeline.png)

---

- 進行式 - ...ing
- 完成式 - ...ed

> [圖解英文文法！過去式、現在式、現在完成式哪裡不一樣？](https://www.managertoday.com.tw/dictionary/cond/53357)

---

<script src="https://gist.github.com/shihyuho/0ec50b07ed61eec8a57a70b98df00528.js"></script>

---

	!java
	} catch (Exception e) {
	
		log.error(e.getMessage(), e);
		
	}

---

	!java
	@PostMapping(UNDER_WRITING_POLICY)
	public ResponseEntity<?> policy(
	        @RequestBody UnderWritingPolicyRequest policyRequest) {
	
		log.info("policyRequest: {}", policyRequest);
	
		ResponseEntity<ResponseDetails<?>> resp = 
	          	stub.underWritingPolicy(policyRequest);
	
		return ResponseEntity.ok(resp.getBody().getData());
	}

---

# Tip2 - Log message w/ arguments

---

	!java
	public void generateReport(ReportData data) throws Exception {
	    ...
	    if (ReportMethod.DISK.equals(data.getReportMethod())) {
	
	      log.info("export to disk.");
	
	    } else if (ReportMethod.PRINTER.equals(data.getReportMethod())) {
	
	      log.info("export to printer.");
	      this.scheduleToServer(data);
	
	    } else {
	
	      log.info("export to Stream ");
	
	      for (Report r : data.getReports()) {
				...
	      }
	    }
		...
	}

---

- `' '` - stands for STATIC value
- `[ ]` - stands for DYNAMIC value

<script src="https://gist.github.com/shihyuho/6de36fecbc75e4c4b68a5a240ea3009d.js"></script>

---

# Tip3 - Use pattern as much as we possibly can

---

	2014-03-05 10:57:51.112  INFO 45469 --- [           main] org.apache.catalina.core.StandardEngine  : Starting Servlet Engine: Apache Tomcat/7.0.52
	2014-03-05 10:57:51.253  INFO 45469 --- [ost-startStop-1] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
	2014-03-05 10:57:51.253  INFO 45469 --- [ost-startStop-1] o.s.web.context.ContextLoader            : Root WebApplicationContext: initialization completed in 1358 ms
	2014-03-05 10:57:51.698  INFO 45469 --- [ost-startStop-1] o.s.b.c.e.ServletRegistrationBean        : Mapping servlet: 'dispatcherServlet' to [/]
	2014-03-05 10:57:51.702  INFO 45469 --- [ost-startStop-1] o.s.b.c.embedded.FilterRegistrationBean  : Mapping filter: 'hiddenHttpMethodFilter' to: [/*]
	
The following items are output:

- Date and Time: Millisecond precision and easily sortable.
- Log Level: `ERROR`, `WARN`, `INFO`, `DEBUG`, or `TRACE`.
- Process ID.
- A `---` separator to distinguish the start of actual log messages.
- Thread name: Enclosed in square brackets (may be truncated for console output).
- Logger name: This is usually the source class name (often abbreviated).
The log message.

> [Spring Boot - Log Format](https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-logging.html#boot-features-logging-format)

--- 

	!java
	@Slf4j
	@Service
	public class HelloService {
	
		public void hello() {
			log.debug("hello");
		}
		
		@ThreadName(
			value =
			    "#{@currentUsernameSupplier.get()}-speaks-to-#{args[0]}",
			valueType = ValueType.SpEL)
		public void hello(String name) {
			log.debug("hello " + name);
		}
	}
	
	DEBUG 45469 --- [				 main] ...HelloService : hello
	DEBUG 45469 --- [matt-speaks-to-david] ...HelloService : hello david

> [SoftLeader Framework - @ThreadName](https://github.com/softleader/softleader-wiki/wiki/Thread-Name)

---

# Tip4 - Starts from comments

---

<script src="https://gist.github.com/shihyuho/129d6b0c9110b3eec40f281510aa5923.js"></script>

---

# Think More

![](./thinking.png)

---

# Q&A
