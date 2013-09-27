# AngularJS training, Fall 2013

In this repository:

- presentation - contains intro slides
- server - backend (from: https://github.com/trautonen/score-tables)
- client - frontend implementation using angularjs

## Running the app

Dependencies:

- node.js
- npm
- bower
- MongoDB running locally on default port

First, start the server:

- 
```bash
server/mvn clean install
server/score-tables-rest-springmvc/mvn jetty:run
```

Then, start the client:

```bash
client/npm install -d
client/bower install
client/grunt server
```
