# Webpack-Demo


## Getting started

官網 [Webpack](https://webpack.js.org/)

本篇主要介紹：

1. 利用 webpack 打包 js/css 檔案 
2. 利用 webpack-dev-server 執行一個可提供測試的 server

### Create a bundle

建立一個空的資料夾，並建立一個 node 專案，Install webpack

``` npm
$ mkdir webpack-demo && cd webpack-demo
$ npm init -y
$ npm install --save-dev webpack
```

``` npm
$ ./node_modules/.bin/webpack --help # Shows a list of valid cli commands
$ .\node_modules\.bin\webpack --help # For windows users
$ webpack --help # If you installed webpack globally
```

建立一個 app 資料夾底下包含 `index.js`

**app/index.js**

``` js
function component () {
  var element = document.createElement('div');

  /* lodash is required for the next line to work */
  element.innerHTML = _.join(['Hello','webpack'], ' ');

  return element;
}

document.body.appendChild(component());
```
在根目錄底下建立一個 `index.html`

**index.html**

``` html
<html>
  <head>
    <title>webpack 2 demo</title>
    <script src="https://unpkg.com/lodash@4.16.6"></script>
  </head>
  <body>
    <script src="app/index.js"></script>
  </body>
</html>
```
以上我們利用 `<script>` 的方式先將 `lodash` 載入後接著載入 `index.js`。
在 `index.js` 中我們並沒有定義 `_`，而是假設全域變數此變數已經存在。

這會有兩個問題：

* 如果 `lodash` 沒有載入，或是載入順序不對，會造成 `index.js` 無法執行
* 如果載入的資源沒有用到，這樣會會造成網頁載入不必要的資源

因此我們要將 `index.js` 相依 `lodash` 並且打包成一個檔案 `bundle.js`。首先先安裝 `lodash`

```
npm install --save lodash
```

接著我們修改 `index.js`

**app/index.js**

``` js
+ import _ from 'lodash';

function component () {
...
```

接著我們預期我們將 js 檔案打包成 `bundle.js`，因此我們修改 `index.html` 如下：

**index.html**

``` html
 <html>
   <head>
     <title>webpack 2 demo</title>
-    <script src="https://unpkg.com/lodash@4.16.6"></script>
   </head>
   <body>
-    <script src="app/index.js"></script>
+    <script src="dist/bundle.js"></script>
   </body>
 </html>
```

接著我們執行 (假設我們一開始 install webpack 是裝在 global)，此時我們就已經將 js 檔案打包好了，並可以開啟 `index.html`。與一開始沒打包載入兩個 js 檔案的結果相同。

```
$ webpack app/index.js dist/bundle.js
```

### Using webpack with a config

隨著我們打包的設定越來越複雜，我們建立一個 `webpack.config.js` 檔案來進行設定

**webpack.config.js**

``` js
var path = require('path');

module.exports = {
  entry: './app/index.js', // 程式的進入點
  output: {
    filename: 'bundle.js', // 打包完的檔案名稱
    path: path.resolve(__dirname, 'dist') // 打包到哪個資料夾
  }
};
```

### Using Webpack with npm
在 `package.json` 加入這段

**package.json**

```
{
  ...
  "scripts": {
    "build": "webpack"
  },
  ...
}
```
接著我們只需執行 `npm run build` 即可進行打包

```
$ npm run build
```

## 打包 css

新增 public 資料夾，並在 public 底下建立 css 及 fonts 資料夾

``` 
$ mkdir app public
$ cd public
$ mkdir css fonts
```
此時的目錄結構如下：

```
.
├── app // 程式碼
│   └── +
├── public // 靜態資源
│   └── +
├── node_modules
├── dist // 打包目錄
├── webpack.config.js
├── index.html
├── package.json


```

#### 打包 bootstrap.css

我們將 `bootstrap.css` 檔案放入 css，並將 glyphicons 檔案放入 fonts。

##### ExtractTextPlugin

在這裡我們利用 `ExtractTextPlugin`([See more](https://github.com/webpack-contrib/extract-text-webpack-plugin)) 這個 plugin 將 css 部分另外打包到 `style.css`。不是包到 `bundle.js`。

``` 
$ npm install --save-dev css-loader extract-text-webpack-plugin file-loader url-loader
```

**webpack.config.js**

``` js
...
var ExtractTextPlugin = require('extract-text-webpack-plugin');

module.exports = {
	...
	module: {
	    rules: [{
	        test: /\.css$/,
	        use: ExtractTextPlugin.extract({
	            use: 'css-loader'
	        })
	    }, {
	        test: /\.(png|woff|woff2|eot|ttf|svg)$/,
	        use: 'url-loader'
	    }]
	},
    plugins: [
        new ExtractTextPlugin('styles.css'), // 將 css 額外包成 style.css
    ]
}
```

在 `index.js` 把 `bootstrap.css` import 進來

**index.js**

``` js
+ import '../public/css/bootstrap.css'; 
...
```

執行

```
$ npm run build
```

此時我們 dist 資料夾底下會有兩個打包的檔案 `bundle.js` `style.css`
這時我們就可以在我們的 `index.html` 引用打包好的 css 檔案

**index.html**

``` html
<head>
	...
	<link rel="stylesheet" type="text/css" href="./dist/styles.css">
<head>
```

### 打包 Main.js 及 Vendor.js

在開發專案的過程中，我們引用的模組 `node_modules` 的變動頻率往往沒有我們的程式碼頻繁。如果我們每次都整個打包成 bundle.js ，會造成打包時間相對過久。因此我們可以將載入的模組另外打包成 `vendor.js`，我們的程式碼打包成 `main.js`。

#### CommonsChunkPlugin
所以我們 `webpack.config.js`([See more](https://webpack.js.org/plugins/commons-chunk-plugin/)) 會變成如下：

**webpack.config.js**

```js
var path = require('path');
var ExtractTextPlugin = require('extract-text-webpack-plugin');
var webpack = require('webpack');

module.exports = {
    entry: {
        main: './app/index.js',
    },
    output: {
        filename: '[name].[chunkhash].js',
        // filename: 'bundle.js', // 打包完的檔案名稱
        path: path.resolve(__dirname, 'dist') // 打包到哪個資料夾
    },
    module: {
        rules: [{
            test: /\.css$/,
            use: ExtractTextPlugin.extract({
                use: 'css-loader'
            })
        }, {
            test: /\.(png|woff|woff2|eot|ttf|svg)$/,
            use: 'url-loader'
        }]
    },
    plugins: [
        new ExtractTextPlugin('styles.css'),
        new webpack.optimize.CommonsChunkPlugin({
            name: 'vendor', // Specify the common bundle's name.
            minChunks: function(module) {
                // this assumes your vendor imports exist in the node_modules directory
                return module.context && module.context.indexOf('node_modules') !== -1;
            }
        })
    ]
};

```
執行

```
$ npm run build
```

### webpack-dev-server
[ webpack-dev-server 設定](https://webpack.js.org/configuration/dev-server/#devserver)

``` node
npm install --save-dev webpack-dev-server
```

在這裡我們利用 Node.js API 執行 dev-server ([See here](https://github.com/webpack/webpack-dev-server/tree/master/examples/node-api-simple))，因此我不就不需在 `webpack.config.js` 這裡設定 `dev-server`

建立一個 server.js

**server.js**

``` js
const Webpack = require("webpack");
const WebpackDevServer = require("webpack-dev-server");
const webpackConfig = require("./webpack.config");

const compiler = Webpack(webpackConfig);
const server = new WebpackDevServer(compiler, {
	stats: {
		colors: true
	}
});

server.listen(8080, "127.0.0.1", function() {
	console.log("Starting server on http://localhost:8080");
});
```

在 `package.json` 加入這段

**package.json**

```
{
  ...
  "scripts": {
    "build": "webpack",
    "dev": "node server.js"
  },
  ...
}
```

執行

```
$ npm run dev
```

連線 [http://localhost:8080/](http://localhost:8080/)

