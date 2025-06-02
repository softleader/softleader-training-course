# 講講我如何高效運用Intellij

[錄影檔](https://drive.google.com/file/d/1RBA0Am1LgEAFAu5VQ07t2oB-n4RhJhLA/view?usp=drive_link)

## Commit prompt

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