var path = require('path');
var webpack = require("webpack");

module.exports = function() {
    return {
        entry: {
            app: [path.resolve(__dirname, 'js/app.js')]
            // vendors: ['jquery', 'bootstrap-loader']
        },
        output: {
            path: path.resolve(__dirname, 'js'),
            filename: 'bundle.js'
        },
        module: {
            // loaders: [{
            //     test: /\.jsx$/,
            //     exclude: path.resolve(__dirname, 'node_modules'),
            //     loaders: ['babel']
            // // }, {   
            // //     test: /\.json$/, 
            // //     loader: 'json-loader' 
            // // }, { npm install jquery bootstrap-sass bootstrap-loader css-loader node-sass resolve-url-loader sass-loader style-loader url-loader file-loader --save-dev
            // //     test: /\.scss$/, 
            // //     loaders: ['style', 'css', 'postcss', 'sass'] 
            // // }, { 
            // //     test: /\.(woff2?|ttf|eot|svg)$/, 
            // //     loader: 'url?limit=10000' 
            // // }, { 
            // //     test: /bootstrap\/dist\/js\/umd\//, 
            // //     loader: 'imports?jQuery=jquery' 
            // }]
        },
        plugins: [
            // new webpack.ProvidePlugin({
            //     jQuery: 'jquery',
            //     $: 'jquery',
            //     jquery: 'jquery'
            // })
            // new webpack.optimize.CommonsChunkPlugin('vendors', path.resolve(__dirname, 'js/vendor.js'))
        ],
        node: {
            // fs: 'empty',
            // net: 'empty',
            // tls: 'empty'
        }
    };
};
