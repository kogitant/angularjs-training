var config;
try {
    config = require('./local');
    console.info("Using local config");
    module.exports = config;
} catch(e) {
    config = require('./default');
    console.info("Using default config");
    module.exports = config;
}