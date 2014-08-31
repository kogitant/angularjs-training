// If you want to override this config locally, copy this to default.js and
// change as necessary
module.exports = {
	port: process.env.PORT || 3000,
	mongodb_url: process.env.MONGOHQ_URL || 'http://localhost:27017'
};
