var webpack = require("webpack");
var WebpackDevServer = require("webpack-dev-server");
var config = require("../webpack.config");
var fs = require("fs");
var path = require("path");
var express = require('express')
var bodyParser = require('webpack-body-parser')

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

var app = express()
 
// create application/json parser 
var jsonParser = bodyParser.json()
 
// create application/x-www-form-urlencoded parser 
// var urlencodedParser = bodyParser.urlencoded({ extended: false })

// webpack-dev-server options: https://webpack.github.io/docs/webpack-dev-server.html#api
var server = new WebpackDevServer(compiler, {
	hot: true,
	setup: function(app) {
		// Here you can access the Express app object and add your own custom middleware to it.
		// For example, to define custom handlers for some paths:
		// app.get('/some/path', function(req, res) {
		//   res.json({ custom: 'response' });
		// });

		let nextId = 2;
		let messages = [{
				rootId: 1,
				id: 1,
				text: "initial message 1-1"
			},{
				rootId: 1,
				id: 2,
				text: "initial message 1-2"
			},{
				rootId: 2,
				id: 1,
				text: "initial message 2-1"
			},{
				rootId: 2,
				id: 2,
				text: "initial message 2-2"
			}
		]

		app.post('/messages', jsonParser, function(req, res) {
			console.log("add message...");
			let message = {
				rootId: req.body.rootId,
				id: ++nextId,
				text: req.body.text
			}
			messages.push(message);
			res.json(message);
		});

		app.get('/messages', function(req, res) {
			let rootId = req.query.rootId;
			console.log("read message...rootId: " + rootId);
			res.json(messages.filter(message => message.rootId == rootId));
		});
		
		app.delete('/messages', jsonParser, function(req, res) {
			console.log("delete message...");
			let message = messages.find(msg => msg.rootId == req.body.rootId && msg.id == req.body.id);
			let index = messages.indexOf(message);
			messages.splice(index, 1);
			res.end();
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