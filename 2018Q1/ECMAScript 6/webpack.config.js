const path = require('path');

module.exports = function (app = "js/app.js") {
  return {
    entry: {
      app: ['@babel/polyfill', path.resolve(__dirname, app)]
    },
    output: {
      path: path.resolve(__dirname, 'build'),
      filename: 'bundle.js',
      chunkFilename: '[name].js'
    },
    module: {
      loaders: [{
        test: /\.(js|jsx)$/,
        exclude: [
          path.resolve(__dirname, 'node_modules')
        ],
        loaders: ['babel-loader']
      }, {
        test: /\.css$/,
        loader: "style-loader!css-loader"
      }, {
        test: /\.png$/,
        loader: "url-loader?limit=100000"
      }, {
        test: /\.jpg$/,
        loader: "file-loader"
      }, {
        test: /\.(woff|woff2)(\?v=\d+\.\d+\.\d+)?$/,
        loader: 'url?limit=10000&mimetype=application/font-woff'
      }, {
        test: /\.ttf(\?v=\d+\.\d+\.\d+)?$/,
        loader: 'url?limit=10000&mimetype=application/octet-stream'
      }, {
        test: /\.eot(\?v=\d+\.\d+\.\d+)?$/,
        loader: 'file'
      }, {
        test: /\.svg(\?v=\d+\.\d+\.\d+)?$/,
        loader: 'url?limit=10000&mimetype=image/svg+xml'
      }]
    },
    plugins: [],
    node: {}
  };
};
