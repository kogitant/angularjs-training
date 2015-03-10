var express = require('express');
var app = express();
var port = 3000;

app.use("/", express.static(__dirname + '/public'));

app.get('/api/userdetails', function(req, res) {
    res.status(200).json({
        username : 'akuankka',
        firstname : 'Aku',
        lastname : 'Ankka'
    });
});

app.get('/api/users', function(req, res) {

    var users = [
        {name : 'Tapio', city  : 'Tampere'},
        {name : 'Marko', city : 'Tampere'},
        {name : 'Kari', city : 'Helsinki'},
        {name : 'Kalle', city : 'Turku'}
    ];

    res.status(200).json(users);

});

app.listen(port, function () {
    console.log('Backend listening at http://localhost:' + port);
});
