
![image](https://user-images.githubusercontent.com/31032281/121222590-41994200-c8b9-11eb-92a4-aebaa7688d9e.png)
# Vue.js 2 Training Course

此次教育訓練內容包含：
- Vue 2 基本功能、生命週期與屬性介紹
- Vue CLI
- Vue Router
- 使用 Vue2 製作一個簡單的通訊錄 (使用 [JSON Server](https://github.com/typicode/json-server) 作為虛擬 Server)


### Table of contents
- [1. What is Vue.js?](#1-what-is-vuejs)
- [2. Installation](#2-installation)
- [3. Vue CLI](#3-vue-cli)
- [4. 資料夾結構](#4-資料夾結構)
- [5. Vue Lifecycle](#5-vue-lifecycle)
- [6. Vue Options](#6-vue-options)
- [7. Component 間的資料溝通傳遞](#7-component-間的資料溝通傳遞)
- [8. 通訊錄 Wireframe](#8-通訊錄-wireframe)
- [9. UI Components](#9-ui-components)
- [10. Router](#10-router)
- [11. CRUD 實作](#11-crud-實作)
- [12. API](#12-api)

---

### 1. What is Vue.js?
https://vuejs.org
<br/>
- Vue.js 是一套漸進式的 JavaScript 框架 (The Progressive JavaScript Framework)。
- Vue.js 是以操作資料狀態來管理畫面的 MVVM 架構，讓大家專注在資料的操作，不需要花心思管理畫面 DOM。
- 目前比較主流的框架還有像是 React 或 Angular，Vue 特別的是把重點放在狀態還有畫面上，在寫 Vue 的時候，感覺就像在寫一般的 html，對於初學者來說很容易上手。

> MVVN : Model - View - ViewModel
> - Model：管理資料
> - View：顯示 UI
> - ViewModel：從 Model 取得 View 所需的資料
<br/>

### 2. Installation
https://vuejs.org/v2/guide/installation.html
<br/>

安裝的方式有很多種，我們這次使用的是 Vue CLI。
<br/><br/>

### 3. Vue CLI
https://cli.vuejs.org/
<br/>

Vue CLI 全名是 Vue.js Command-Line Interface，由 Vue 團隊所開發，提供開發者快速建置 Vue.js 專案並整合相關工具的一套 command-line 工具。

- 安裝前要先裝 Node.js
- 在 Node 環境下，請記得下 `yarn` / `npm install` 初始化專案
- Vue3 出來之後，Vue CLI 有更新過，安裝過程可參考[簡報](https://github.com/blairlee227/vue-training-course/blob/master/vue_traning.pdf)
<br/>

### 4. 資料夾結構
```
├─node_modules        // dependencies
├─public              // 公開文件
│ ├─ favicon.ico      // 網站 icon
│ ├─ index.html       // 頁面入口文件

├─src                 // 原始碼目錄
│ ├─assets            // 靜態文件
│ ├─components        // 元件
│ ├─views             // 畫面
│ ├─router            // 路由
│ ├─App.vue           // 入口文件
│ ├─main.js           // 所有頁面的 config 檔案

├─package.json        // 管理 dependencies & config
└─yarn.lock           // 版本鎖定
```
<br/>

### 5. Vue Lifecycle
https://vuejs.org/v2/guide/instance.html

Vue Instance 從建立(Create)、掛載(Mount)、更新(Update)到銷毀(Destroy)的過程，稱為生命週期，這些過程中，Vue 提供各階段不同的 callback funtion，被稱為生命週期的 hooks。
<br/><br/>

### 6. Vue Options
```
var vm = new Vue({
  el: "#app",       // 用來掛載 Vue 實體元素
  data: { },        // 要綁定的資料
  props: { },       // 接收外部資料的屬性
  methods: { },     // 定義 vue 實體內使用的函式
  watch: { },       // 觀察實體內資料變動
  computed: { },    // 自動計算的屬性
  template: "...",  // 提供 Vue 變更後的模板
  component: { }    // 定義元件
});
```
<br/>

### 7. Component 間的資料溝通傳遞
- **props / $emit**：Props in, Events out
- **$attr / $listeners**：多層 component 間的屬性傳遞
- **provide / inject** ：依賴注入
- **Vuex**：狀態管理

(本次只針對 `props` 及 `$emit` 實作)
<br/><br/>

### 8. 通訊錄 Wireframe
![image](https://user-images.githubusercontent.com/31032281/121213366-bc119400-c8b0-11eb-8f10-1dc8c7a9b938.png)
<br/><br/>

### 9. UI Components
- [A Single Root Element](https://vuejs.org/v2/guide/components.html#A-Single-Root-Element) <br/>
**" Every component must have a single root element. "** <br/>
元件中一定且只能有一個 root element，可以用 `<div>` 包著所有內容，而這個 `<div>` 就像是一個入口，Vue 會把裡面的東西拿出來渲染，處理好再插入到 DOM 中。<br/>

<br/>

### 10. Router
- 頁面上透過 `<router-view>` 渲染元件
- `mode: 'history'` 指的是 HTML5 的 History mode
- `base` 定義 base url，所有的 `:to` 就不用寫 base url 
- 可設定 Nested Route (可參考[簡報](https://github.com/blairlee227/vue-training-course/blob/master/vue_traning.pdf)範例)
<br/>

### 11. CRUD 實作
請參考 [sample-code](https://github.com/blairlee227/vue-training-course/tree/master/sample-code)<br/>
- [origin-files](https://github.com/blairlee227/vue-training-course/tree/master/sample-code/orign-files) : 原始還沒整理過的檔案 <br/>
- [compelete-files](https://github.com/blairlee227/vue-training-course/tree/master/sample-code/complete-files) : 完成 CRUD 的完整範例檔案
<br/>

### 12. API
- 官方推薦 [Axios](https://axios-http.com/) 這個 API 套件
- 在 component 中使用 Axios 請記得 import : `import axios from "axios";`
- 這次使用 [JSON Server](https://github.com/typicode/json-server) 作為虛擬 server
- Start JSON Server : `json-server --watch db.json`

