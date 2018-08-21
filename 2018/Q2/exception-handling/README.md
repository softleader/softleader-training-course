# Best practices to handle exceptions
## 使用 try/catch 的時機，與注意點
### 一些比較特殊的使用例

1. AutoCloseable
	```java
	try (OutputStream out = new FileOutputStream("file")) {
		// IO handling
	}
	```
	> 善用 AutoClosable 來減少 try/catch block 的出現以提高程式碼可讀性，以及避免自己忘記或懶得 close
	```java
	// UserSupplier:
	public AutoCloseable set(String user) {
		this.user.set(user);
		retrun () -> this.user.remove();
	}
	
	// Use:
	try (UserSupplier.set("system")) {
		// 這可以使 UserSupplier.get() 在這個 try block 區間內取得的是 system
		// 而離開 try block 後自動恢復原狀
	}
	```

2. CheckedException
	- 全宇宙最煩的 Exception 沒有之一
	```java
	public output Method(input) throws Exception
	```
	> 除非有需要否則不 catrch
	```java
	@Override
	public output Method(input) {
		try {
			// do something which throw CheckedException
		} catch (CheckedException e) {
			throw new RuntimeException(e);
		}
	}
	```
	> 如果 Method 上沒辦法下 throws 且又需要往上拋的情況 (例如 Override 別人的介面時) 
	> 也只能包成 RuntimeException 拋出
	```java
	(t -> {
		try {
			// do something which throw CheckedException
		} catch (CheckedException e) {
			throw new RuntimeException(e);
		}
	})
	```
	> Lambda 是另一種沒辦法 throws 的狀況，其實原因與前者相同
	> 但這傢伙是不能寫成一行會全身不舒服，於是可以用下面這種方法
	```java
	(Unchecked.apply(t -> // do something which throw CheckedException))
	```
	> Unchecked 是 softleader-framework 提供的一個 class 
	> 作用是將所有 Exception catch 後以 RuntimeException 封裝後拋出，迴避必須寫 try/catch 的情境
	> 詳細請參考 [softleader-wiki](https://github.com/softleader/softleader-wiki/wiki/Unchecked)
---
### 別做這些事！
1. 做了跟 e.printStrackTrace 一樣的事
	```java
	catch (Exception e) {
		log.error(e);
	}
	```
	better:
	```java
	catch (Exception e) {
		log.error("Do something falied, input:{}", ToStringBuilder(input, SHORT),e);
	}
	```
	> catch Exception 時請增加描述 Execption 當下處理的事情、輸入、甚至是預期的輸出等，不要相信預設的 Exception message 可以提供多大的幫助

2. log 的同時又將同樣的 Exception 往外 throw
	```java
	catch (Exception e) {
		log.error(e);
		throw e;
	}
	```
	> 只有在最必要的時候才進行 log 的處理，否則容易產生同樣的 Exception log 降低易讀性 

3. 沒有任何理由就忽略 Exception
	```java
	catch (Exception e) {
	}
	```
	> 除非必要否則別這樣做，而且至少也給個註解
---
### 富邦B系統開發經驗
1. 外部介接系統 Exception handling
    - API的觸錯最重要的就是 input 與 output 參數，無論呼叫成功或失敗，一定要盡可能的確保能從 log 找到這兩樣東西
	- client: `tw.com.softleader.trainingexception.api.test.client.TestApiClient`
		1. 盡可能先以代碼進行執行成功與否的溝通
		2. 只要有任何失敗，皆 throw Exception 出去，由需要呼叫此API 的程式各自處理 Exception handling
	- server: `tw.com.softleader.trainingexception.api.test.server.TestApiServer`
	    1. 比照 Controller，不要讓 Exception 直接曝露出去
	    2. 有任何特殊的回傳碼都以 Exception 處理，以便中斷 API 的運行
	    3. 自己 thorw 的 Exception 通常不太需要 stackTrace
	    4. 已經包起來的完整邏輯有可能有自己的 throw，也可以交給最大的 catch 處理，取決於是否要讓接收端接收到特殊的錯誤代碼
2. 分層授權實做
    - `tw.com.softleader.trainingexception.web.TestController`
    - 訊息類型:
        1. `SUCCESS`, `INFO`: 只需要除提示的訊息
        2. `REVIEW`: 與授權不足有關，仍需要上級人員覆核才能通過的訊息
        3. `ABORT`: 系統檢核訊息，出了之後必須中斷操作的訊息
    - 每個訊息需保留簽核者(甚至是簽核時間)
    - 最好有歷程能夠追蹤
---
### 補充資料
1. 目前 Microservice 所使用的 Restful 回應架構: [Response-Structure](https://github.com/softleader/softleader-microservice-wiki/wiki/Response-Structure)
