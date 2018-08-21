var webpack = require("webpack");
var config = require("../webpack.config")
var path = require('path');

var cfg = config();
// console.log(JSON.stringify(cfg));
var compiler = webpack(cfg);

// watch options: https://webpack.github.io/docs/configuration.html#watch
compiler.watch({ 
    aggregateTimeout: 300, // wait so long for more changes
    poll: true // use polling instead of native watchers
}, function(err, stats) {
	if(err) {
        throw new Error(err);
	}
    var jsonStats = stats.toJson();
    if(jsonStats.errors.length > 0) {
        throw new Error(jsonStats.errors);
    }
    console.log('Started watching for: ' + path.resolve(cfg.output.path, cfg.output.filename));
});