# 講講我如何高效運用Intellij

[錄影檔](https://drive.google.com/file/d/1RBA0Am1LgEAFAu5VQ07t2oB-n4RhJhLA/view?usp=drive_link)


---

# `AI筆記`

### **高效駕馭 IntelliJ IDEA：從環境設定到實戰技巧**

IntelliJ IDEA 是一款功能強大的整合開發環境 (IDE)，但要完全發揮其潛力，關鍵在於掌握正確的設定、外掛程式以及隱藏在介面下的高效功能。本文件將引導您完成從環境優化到進階操作的完整流程。

#### **一、 基礎環境設定與外掛程式**

在開始撰寫程式碼之前，優化您的開發環境是提升效率的第一步。

##### **1. 加入開發者社群**
為了獲取最新的外掛程式推薦與使用技巧，建議加入 SoftLeader 的 Slack 公開頻道：`#intellij_idea`。這個頻道是同事間分享實用技巧和解決方案的平台，您可以在此找到許多實用的討論。

##### **2. 必備外掛程式推薦**
前往 `File > Settings > Plugins`，在 Marketplace 中搜尋並安裝以下推薦的外掛程式：

*   **Grep Console**
   *   **用途**：解決原生終端機 (Console) 日誌輸出單調、難以閱讀的問題。
   *   **核心功能**：它能根據日誌的關鍵字（如 `ERROR`, `WARN`, `INFO`）或正則表達式，為不同的日誌行提供自訂的背景色或文字顏色。這讓您在龐大的日誌輸出中，能一眼就定位到錯誤或關鍵資訊。
   *   **安裝後**：執行應用程式後，您會發現終端機視窗中的日誌已自動被著色。

*   **CodeGlance Pro**
   *   **用途**：提供類似 Sublime Text 或 VS Code 的程式碼迷你地圖功能。
   *   **核心功能**：在編輯器右側生成一個可拖曳的程式碼縮圖。這個縮圖不僅能讓您快速了解整個檔案的結構，還會用不同顏色標示出程式碼的修改處、錯誤或警告，是您在長檔案中快速導航的利器。

*   **Maven Helper**
   *   **用途**：簡化 Maven 專案的管理與除錯。
   *   **核心功能**：
      1.  **快速執行指令**：在專案的 `pom.xml` 檔案上按右鍵，可以直接執行 `Run Maven`，並選擇常用的 Maven 生命週期指令（如 `clean package -DskipTests`）。
      2.  **依賴分析神器**：打開 `pom.xml` 後，編輯器下方會出現一個 **Dependency Analyzer** 頁籤。它能以樹狀圖或列表形式，清晰地展示所有直接與間接的依賴關係，並自動檢測出版本衝突 (Conflicts)。當您遇到 `ClassNotFoundException` 或 `NoSuchMethodError` 時，這項功能是您排查問題的首選工具。

##### **3. 專案設定：匯入相依模組**
在進行微服務或前後端分離的專案時，經常需要同時開啟多個專案。
*   **步驟**：
   1.  開啟您的主要專案。
   2.  前往 `File > Project Structure > Modules`。
   3.  點擊 `+` 號，選擇 `Import Module`。
   4.  找到您另一個專案的根目錄（例如公司的 Framework 或另一個微服務），並將其匯入。
*   **優勢**：如此一來，您就可以在同一個 IntelliJ IDEA 視窗中，同時進行多個專案的開發與除錯，甚至可以直接追蹤到 Framework 的原始碼。

#### **二、 內建高效功能詳解**

IntelliJ IDEA 內建了大量強大的功能，以下是幾項在日常開發中最能提升效率的技巧。

##### **1. Git 整合：快速比較分支**
在合併程式碼前，了解不同分支之間的差異至關重要。
*   **步驟**：
   1.  點擊右下角的 Git 分支名稱（例如 `master`）。
   2.  在彈出的分支列表中，選擇您想要比較的分支（例如 `feature/some-feature`）。
   3.  點擊該分支，並在子選單中選擇 `Compare with 'master'`。
*   **結果**：IntelliJ IDEA 會開啟一個新視窗，清晰地列出兩個分支之間所有差異的提交紀錄 (Commits) 與檔案變更。

##### **2. 程式碼重構 (Refactoring)**
重構是改善程式碼品質的關鍵，而 IntelliJ IDEA 提供了強大的自動化工具。

*   **萬能重構選單 (`Refactor This`)**
   *   **快捷鍵**：`Ctrl + Alt + Shift + T`
   *   **用途**：這是一個情境感知的重構選單。無論您選取的是一個變數、一個方法、還是一段程式碼，按下此快捷鍵，它都會列出所有可用的重構選項，例如：
      *   **Introduce Variable** (`Ctrl+Alt+V`)：將一個表達式抽取為區域變數。
      *   **Introduce Parameter** (`Ctrl+Alt+P`)：將一個變數提升為方法參數。
      *   **Extract Method** (`Ctrl+Alt+M`)：將一段程式碼抽取成一個新方法。

*   **快速包裹程式碼 (`Surround With`)**
   *   **快捷鍵**：`Ctrl + Alt + T`
   *   **用途**：快速地用 `if/else`, `while`, `try/catch` 等區塊將您選中的程式碼包裹起來，無需手動調整縮排與括號。

##### **3. 程式碼導航與選取**

*   **上下方法間跳轉**
   *   **快捷鍵**：`Alt + ↑` / `Alt + ↓`
   *   **用途**：在類別 (Class) 的不同方法之間快速跳躍。

*   **返回上一個瀏覽位置**
   *   **快捷鍵**：`Alt + ←` (後退) / `Alt + →` (前進)
   *   **用途**：這就像瀏覽器的「上一頁」，能讓您在剛剛跳轉過的不同程式碼位置之間快速返回，非常適合在追蹤複雜的呼叫鏈時使用。

*   **語法層級選取 (Extend/Shrink Selection)**
   *   **快捷鍵**：`Ctrl + W` (擴大選取) / `Ctrl + Shift + W` (縮小選取)
   *   **用途**：這是 IntelliJ IDEA 的一大特色。將游標放在一個單字上，連續按下 `Ctrl+W`，選取範圍會從單字 > 整個表達式 > 整行 > 整個程式碼區塊 > 整個方法，逐級擴大。這讓您可以極其精準地選取想操作的程式碼範圍。

##### **4. 設定優化：啟用駝峰式大小寫 (CamelHumps)**
為了讓 `Ctrl + ←/→`（按單字移動游標）和 `Ctrl + W`（擴大選取）更加智慧，您需要啟用「駝峰式大小寫」辨識。
*   **設定路徑**：`Settings > Editor > General > Smart Keys`
*   **勾選項**：`Use "CamelHumps" words`
*   **效果**：啟用後，`myVariableName` 將被視為三個單字 (`my`, `Variable`, `Name`)，讓您的游標移動和選取更加精準。

---

## 附件: Commit prompt

```markdown
絕對遵守: 使用繁體中文/正體中文作為主要語言

# 慣例式提交 Conventional Commits 1.0.0

## 概述

慣例式提交規範，是一種對提交說明的輕量慣例。 它提供一些簡單的條件集合用於建立明確的提交歷史； 這能讓自動化工具更容易撰寫。 這份慣例能對應到 SemVer， 透過在提交說明裡描述功能、修正以及重大變更。

### 提交說明應被以下方式建構

<類型 type>[可選的作用範圍 scope]: <描述 description>

[可選的正文 body]

[可選的頁腳 footer]

### 提交應包含以下結構性元素，用以向使用這套函式庫的使用者溝通當時的意圖

- fix: 為 fix 類型 的提交，表示對程式修正了一個臭蟲（bug）（對應到語意化版本中的 修訂號 PATCH）。
- feat: 為 feat 類型 的提交，表示對程式增加了一個功能（對應到語意化版本中的 次版本 MINOR）。
- BREAKING CHANGE: 重大變更，如果提交的頁腳以 BREAKING CHANGE: 開頭，或是在類型、作用範圍後有 !，代表包含了重大 API 變更（對應到語意化版本中的 主版本 MAJOR）。 重大變更可以是任何 類型 提交的一部分。
- 其他: 除 fix: 與 feat: 以外，其他的提交 類型 也是被允許的，例如 @commitlint/config-conventional (基於 Angular 慣例) 中推薦的 chore:、docs:、style:、refactor:、perf:、test: 以及更多。
- 我們也推薦對那些沒有增加新功能或是修正臭蟲而是改善目前實作的提交使用 improvement。 請注意，這些類型在慣例式提交規範中並不是強制性的，且在語意化版本中也沒有隱含的作用 (除非它們包含 BREAKING CHANGE)。

- 除了 fix: 與 feat: 之外也允許其他的 類型 ，如（基於 Angular 慣例的）@commitlint/config-conventional 推薦使用 build: 與 chore:、 ci:、docs:、style:、refactor:、perf:、test:、等其他。
- 也可以使用 BREAKING CHANGE: <描述> 之外的 頁腳 ，並遵守類似 git trailer format 的慣例。
- 追加類型並不被慣例式提交所束縛，並且不對語義化版本有任何隱藏的影響。（但若包含 BREAKING CHANGE 則不在此限。） 提交的類型可以在括號內給予作用範圍，以提供額外的脈絡資訊。例如：feat(parser): add ability to parse arrays。

## 規範

在本文中使用的關鍵字：MUST、MUST NOT、REQUIRED、SHALL、SHALL NOT、SHOULD、SHOULD NOT、RECOMMENDED、MAY、以及 OPTIONAL 應以 RFC 2119 為參考解釋。

- 每個提交最前面「必須 MUST」要有類型，類型由名詞組成，例如：feat、fix 等，後接上「可選的 OPTIONAL」作用範圍以及「必要的 REQUIRED」一個冒號與空格。
- 當提交一個新功能到你的應用程式或是函式庫時，「必須 MUST」使用 feat 類型。
- 當提交一個臭蟲修正到你的應用程式時，「必須 MUST」使用 fix 類型。
- 類型之後「可以 MAY」加上作用範圍。個別作用範圍「必須 MUST」由一個描述程式區段的名詞所組成，並用括號包覆。例如： fix(parser):
- 描述「必須 MUST」緊鄰在類型／作用範圍後的冒號與空格。 描述是對於程式碼修改的簡短總結，如 fix: array parsing issue when multiple spaces were contained in string 。
- 在簡短的描述後「可以 MAY」加上更長的提交正文，提供關於對程式碼變更的額外脈絡資訊。正文「必須 MUST」在描述後的一個空行之後開始。
- 提交正文為自由格式，並「可以 MAY」有數個以換行字元區分的段落。
- 在正文後「可以 MAY」有一個或多個頁腳，頁腳在正文後空行之後開始。 每個頁腳「必須 MUST」包含一個符記 (token) ，並接着以 :<space> 或 <space># 分隔，再緊鄰一個字串值。 （本處靈感係源自於 git trailer convention。）
- 頁腳的符記「必須 MUST」使用 - 作為空白字元，如 Acked-by （這有助於區分出頁腳與多段落的正文）。 但 BREAKING CHANGE 則為例外，且也「可以 MAY」作為符記使用。
- 頁腳的值「可以 MAY」包含空白與換行，解析時「必須 MUST」在遇到下一組有效的符記／分隔時停止。
- 重大變更「必須 MUST作為提交中的類型／作用範圍的前綴，或是在頁腳中作為一個段落存在。
- 若放置於頁腳，重大變更「必須 MUST」維持大寫文字 BREAKING CHANGE，而後緊鄰一個分號、空白、並接着描述。如： BREAKING CHANGE: environment variables now take precedence over config files 。
- 若作為類型／作用範圍的前綴，重大變更「必須 MUST」以一個 ! 識別，並緊鄰於 : 之前。若使用 !， 頁腳段落的 BREAKING CHANGE: 則「可以 MAY」被省略，且提交說明「應當 SHALL」用來描述重大變更。
- 除了 feat 與 fix 以外的類型「可以 MAY」被用於提交訊息內，如： docs: updated ref docs 。
- 組成慣例式提交資訊的單位在實作時除了大寫的 BREAKING CHANGE 外，「禁止 MUST NOT」區分大小寫。
- 在作為頁腳符記時，BREAKING-CHANGE「必須 MUST」與 BREAKING CHANGE 視為相同的。

## 為何要使用慣例式提交

- 自動產生修改日誌 (Changelog)。
- 基於提交的類型，自動決定語意化版本的升級。
- 向同事、公眾以及其他的利益相關者傳達變化的過程。
- 觸發建置與發布流程。
- 讓大家探索更有結構的提交歷史，使你的專案更容易被貢獻。

## 其餘注意事項
- 不能使用任何 code block, 包含 <code></code>,  ```...```, `...` 
```