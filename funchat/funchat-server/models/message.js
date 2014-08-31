var mongoose     = require('mongoose');
var Schema       = mongoose.Schema;

var MessageSchema   = new Schema({
	author: String,
	content: String,
	created: { type: Date, default: Date.now },
	modified: { type: Date, default: null }
});

module.exports = mongoose.model('Message', MessageSchema);
