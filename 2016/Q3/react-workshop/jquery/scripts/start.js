var webpack = require("webpack");
var WebpackDevServer = require("webpack-dev-server");
var config = require("../webpack.config");
var fs = require("fs");
var path = require("path");
var bodyParser = require('webpack-body-parser');

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
		
		// create application/json parser 
		var jsonParser = bodyParser.json();
		
		app.post('/react/workshop', jsonParser, function(req, res) {
			if (!req.body) return res.sendStatus(400);
			req.body.message = "store data success";
			console.log(req.body);
			res.send(req.body);
		});

		app.post('/react/workshop/sms', jsonParser, function(req, res) {
			if (!req.body) return res.sendStatus(400);
			req.body.message = "send sms success";
			console.log(req.body);
			res.send(req.body);
		});

		app.post('/react/workshop/mail', jsonParser, function(req, res) {
			if (!req.body) return res.sendStatus(400);
			req.body.message = "send mail success";
			console.log(req.body);
			res.send(req.body);
		});
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