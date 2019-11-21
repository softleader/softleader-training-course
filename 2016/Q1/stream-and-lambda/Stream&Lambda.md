# Softleader Stream &amp; Lambda介紹
- Author: Rhys
- Date: 2016-03-25


- - -
## Lambda
### Lambda是誰?
- 是一種函式的定義


- 於JDK8以後可用


- 函式
    - 單一輸入: `f(x) = x + 1`
    - 多重輸入: `f(x,y) = x + y`


- 數學中的Lambda
    - 單一輸入: `λx.x + 1`
    - 多重輸入: `λx.λy.x + y`


- Java中的Lambda
    - 單一輸入: `x -> x + 1`
    - 多重輸入: `(x, y) -> x + y`


### Lambda怎麼用?
- 數學中的Lambda
    - 抽象化運算過程
    - 建立算式等價標準


- Java中的Lambda
    - 簡化匿名類別的實作
    - ex.
        - 使用匿名類別時:
        ~~~java
        Collections.sort(students, new Comparator<Student>() {
			@Override
			public int compare(Student o1, Student o2) {
				return Integer.compare(o1.getAge(), o2.getAge());
			}
		});
        ~~~
        - 使用Lambda時
        ~~~java
        Collections.sort(students, Comparator.comparing(student -> student.getAge()));
        ~~~


### Lambda對誰用?
- 參數是所有標記為`@FunctionalInterface`的Class時


- ex. psckage: java.util.function


- 常用:
    - `java.util.function.Function`
        - input 轉變成 output
    - `java.util.function.Predicate`
        - input 轉變成 boolean
    - `java.util.function.Consumer`
        - 有input, 沒有output
    - `java.util.function.Supplier`
        - 沒有input, 有output


- - -
## Stream
### Stream是誰?
~~~java
int sum = widgets.stream()
                 .filter(b -> b.getColor() == RED)
                 .mapToInt(b -> b.getWeight())
                 .sum();
~~~
- 將一個集合串流化, 將對集合的操作簡化為對串流的操作
    - 對集合的操作 ex.
        - 一整個貨櫃的材料給一個師傅手工打造成一台BMW
    - 對串流的操作 ex.
        1. 一個貨櫃的材料一個一個取出
        2. B產線將材料篩選成B
        3. M產線將材料變形成M
        4. W產線將材料加工成W
        5. 最後所有加工物組合成BMW
    - 串流化操作的優點
        - 商業邏輯單元化
            - 單元化的邏輯易於進行單元測試
            - 必要時可以任意組合流程
        - 流程與邏輯切割分明
            - 提高可讀性
            - 易維護
    - 串流化操作的缺點
        - 所需要的執行成本較高
            - stream() 進行串流化的過程本身須需要相當大的成本(相對於直接執行forEach)
        - 若流程設計不良, 可讀性反而嚴重降低


### Stream怎麼用?
- 參考java.util.stream.Stream下的所有方法
- 常用:
    <table>
        <tr>
            <th>方法名稱</th>
            <th>描述</th>
            <th>例子</th>
            <th></th>
        </tr>
        <tr>
            <td>filter</td>
            <td>過濾</td>
            <td>在每一個學生中挑出姓李的人</td>
            <td>intermediate</td>
        </tr>
        <tr>
            <td>map</td>
            <td>轉型</td>
            <td>把每一個學生轉成畢業生</td>
            <td>intermediate</td>
        </tr>
        <tr>
            <td>flatMap</td>
            <td>轉型成Stream合併</td>
            <td>把每一個學生的父母都取出來做成名單</td>
            <td>intermediate</td>
        </tr>
        <tr>
            <td>distinct</td>
            <td>篩選獨特值</td>
            <td>把名單中重複的學生移除</td>
            <td>intermediate</td>
        </tr>
        <tr>
            <td>sorted</td>
            <td>排序</td>
            <td>將一群學生以學號排序</td>
            <td>intermediate</td>
        </tr>
        <tr>
            <td>forEach</td>
            <td>列舉</td>
            <td>把每一個學生的名字print出來</td>
            <td>terminal</td>
        </tr>
        <tr>
            <td>reduce</td>
            <td>壓縮</td>
            <td>把每一個學生合併成為一個學生</td>
            <td>terminal</td>
        </tr>
        <tr>
            <td>collect</td>
            <td>收集</td>
            <td>將串流上的學生收集成一個集合</td>
            <td>terminal</td>
        </tr>
        <tr>
            <td>min</td>
            <td>取最小</td>
            <td>取學號最小的學生</td>
            <td>terminal</td>
        </tr>
        <tr>
            <td>max</td>
            <td>取最大</td>
            <td>取學號最大的學生</td>
            <td>terminal</td>
        </tr>
        <tr>
            <td>anyMatch</td>
            <td>任一吻合</td>
            <td>是否有任何一個學生在1/1出生</td>
            <td>terminal</td>
        </tr>
        <tr>
            <td>allMatch</td>
            <td>全部吻合</td>
            <td>是否有每一個學生都在1/1出生</td>
            <td>terminal</td>
        </tr>
        <tr>
            <td>findFirst</td>
            <td>取第一個</td>
            <td>取出在串流中的第一個學生</td>
            <td>terminal</td>
        </tr>
    </table>


- intermediate/terminal
    - 所有的Stream的method都可以被區分為這兩種類型
    - 最明顯的差異在於呼叫terminal的method之後, return的通常不是Stream
        - 也因此每個Stream都一定只有一個terminal的method作為結尾
    - Stream的串流構造是在指定了各種intermediate與terminal方法之後, 才一次於terminal"流過", 因此指定多個intermediate並不會增加迴圈成本


- 其他Stream
    - IntStream, LongStream, DoubleStream
        - 可以透過mapToInt(), mapToLong(), mapToDouble()產生
        - 因為是純數字的Stream, 因此能使用一些更獨特的方法
        - 如`sum()`, `average()`等


### Stream對誰用?
- 所有繼承自`Collection`的Class都能呼叫`.stream()`
    - 此方法可以將`Collection<E>`轉成`Stream<E>`


- 呼叫`Stream.of(T t)`或`Stream.of(T... values)`


- - -
## Stream 與 Lambda
- 由於Stream中大量使用java.util.function下的Class作為輸入參數, 因此常見到這兩者同時出現


- ex.
~~~java
int sum = widgets.stream()
                 .filter(b -> b.getColor() == RED)
                 .mapToInt(b -> b.getWeight())
                 .sum();
~~~
    1. widgets為一個Collection, 呼叫了`stream()`方法轉成Stream物件
    2. `filter`的輸入參數為`Predicate`
        - 每一個Predicate處理input, 並輸出boolean, 篩選出結果為true者
    3. `mapToInt`的輸入參數為`Function`
        - 每一個Function處理input, 並輸出一個另一種型別的output
        - 此處因為是mapToInt, 有限定output的型別必須為int
    4. `sum`不需要傳入參數, 僅是把IntStream中的每一個數字做加總
        - 經過mapToInt, Stream會被處理為IntStream, 因此才能呼叫`sum()`
