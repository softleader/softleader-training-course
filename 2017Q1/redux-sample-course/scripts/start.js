var webpack = require("webpack");
var WebpackDevServer = require("webpack-dev-server");
var config = require("../webpack.config");
var fs = require("fs");
var path = require("path");

var port = process.env.PORT || 3000;
var host = process.env.HOST || 'localhost';

var cfg = config();

fs.unlink(path.resolve(cfg.output.path, cfg.output.filename), function() {});

cfg.entry.app.unshift(
	"webpack-dev-server/client?http://"+host+":"+port+"/", 
	"webpack/hot/dev-server"
);

cfg.plugins.unshift(
    new webpack.HotModuleReplacementPlugin()
);

// console.log(JSON.stringify(cfg));

var compiler = webpack(cfg);

// webpack-dev-server options: https://webpack.github.io/docs/webpack-dev-server.html#api
var server = new WebpackDevServer(compiler, {
	hot: true,
	setup: function(app) {
		// Here you can access the Express app object and add your own custom middleware to it.
		// For example, to define custom handlers for some paths:
		// app.get('/some/path', function(req, res) {
		//   res.json({ custom: 'response' });
		// });
		// app.get('/test/images', function(req, res) {
		// 	fs.readFile("./public/images/DisplayOrder1_N01371.zip", function(err, data) {
		// 		if (err) throw err;
		// 		res.set('content-type','text/plain; charset=x-user-defined');
		// 		res.write(data);
		// 		res.end();
		// 	});
		// });
	},
	quiet: true,
	publicPath:  '/js/',
	stats: { colors: true }
});
server.listen(port, host, function(err) {
	if (err) {
		throw new Error(err);
	}
	console.log('Listening on http://' + host + ':' + port);
});