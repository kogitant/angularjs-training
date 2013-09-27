AngularJS training, Fall 2013

- presentation - contains intro slides
- server - backend (from: https://github.com/trautonen/score-tables)
- client - frontend implementation using angularjs


### Dependencies 

- node
- npm
- bower

### Running locally

- start mongo
- start server:
  server/mvn clean install
  server/score-tables-rest-springmvc/mvn jetty:run
- start client:
  client/npm install -d
  client/bower install
  client/grunt server
