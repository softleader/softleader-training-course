var path = require('path');
var ExtractTextPlugin = require('extract-text-webpack-plugin');
var webpack = require('webpack');

module.exports = {
    // entry: './app/index.js', // 程式的進入點
    entry: {
        main: './app/index.js',
    },
    output: {
        filename: '[name].[chunkhash].js',
        // filename: 'bundle.js', // 打包完的檔案名稱
        path: path.resolve(__dirname, 'dist') // 打包到哪個資料夾
    },
    // module: {
    //     rules: [{
    //         test: /\.css$/,
    //         // exclude: 'node_modules',
    //         use: 'css-loader'
    //     }]
    // }
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
