var webpack = require("webpack");
var config = require("../webpack.config");
var path = require('path');
var fs = require('fs-extra');
var ncp = require('ncp').ncp;

console.log('Creating an optimized production build...');

fs.copy(path.resolve(__dirname, '../index.html'), path.resolve(__dirname, '../build/index.html'), function (err) {
    if (err) return console.error(err)
    console.log("Copied index.html to /build/index.html")
})

ncp(path.resolve(__dirname, '../public'), path.resolve(__dirname, '../build/public'), function (err) {
    if (err) return console.error(err);
    console.log("Copied /public to /build/public")
});

var cfg = config();

cfg.output = {
    path: path.resolve(__dirname, '../build'),
    filename: 'image-viewer.js'
};

webpack(cfg).run(function(err, stats) {
	if(err) {
        throw new Error(err);
	}
    var jsonStats = stats.toJson();
    if(jsonStats.errors.length > 0) {
        throw new Error(jsonStats.errors);
    }
    console.log('Successfully compiled: ' + path.resolve(cfg.output.path, cfg.output.filename));
});

var minCfg = config();

minCfg.output = {
    path: path.resolve(__dirname, '../build'),
    filename: 'image-viewer.min.js'
};

minCfg.plugins.unshift(
    new webpack.optimize.UglifyJsPlugin(), 
    new webpack.optimize.OccurrenceOrderPlugin(),
    new webpack.DefinePlugin({
        "process.env": { 
            NODE_ENV: JSON.stringify("production") 
        }
    })
);

webpack(minCfg).run(function(err, stats) {
	if(err) {
        throw new Error(err);
	}
    var jsonStats = stats.toJson();
    if(jsonStats.errors.length > 0) {
        throw new Error(jsonStats.errors);
    }
    console.log('Successfully compiled: ' + path.resolve(minCfg.output.path, minCfg.output.filename));
});