var express = require('express'),
    router = express.Router(),
    Message = require('../models/message');

router
	.route('/')
	.get(function(req, res) {
		Message
			.find()
			.sort('+created')
			.exec(function(err, messages) {
				if (err) {
					res.send(err);
				}

				res.json(messages);
			});
	})
	.post(function(req, res) {
		var message = new Message();
		message.author = req.body.author;
		message.content = req.body.content;		

		message.save(function(err) {
			if (err) {
 				res.send(err);
			}

			res.json(message);
		});
	});

router
	.param('message', function(req, res, next, id) {
		Message.findById(id, function(err, message) {
			if(err) {
				return res.status(404).send("Not Found");
			}

			req.message = message;
			next();
		});
	})
	.route('/:message')
	.get(function(req, res) {
		return res.send(req.message);
	})
	.delete(function(req, res) {
		if(!req.message) {
			return res.status(410).send("Gone");
		}

		Message.remove({_id: req.message._id}, function(err, message) {
			if (err) {
				return res.status(500).send("Internal Server Error");
			}

			return res.status(200).send("OK");
		});

	})
	.put(function(req, res) {
		var message = req.message;

		if(req.body.author) {
			message.author = req.body.author;
		}
		if(req.body.content) {
			message.content = req.body.content;
		}
		message.modified = new Date();

		message.save(function(err) {
			if (err) {
				console.error(err);
				return res.status(500).send("Internal Server Error");
			}

			return res.status(200).send("OK");
		});
	});

module.exports = router;
