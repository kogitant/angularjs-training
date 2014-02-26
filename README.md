# AngularJS Training Materials

In this repository:

- presentations - Contains presentations
- server - Backend implementation (from: https://github.com/trautonen/score-tables)
- client - Frontend implementation

## Running the app

Dependencies:

- node.js
- npm
- bower
- MongoDB running locally on default port

First, start the server:

    cd server
    mvn clean install
    cd score-tables-rest-springmvc
    mvn jetty:run

Then, start the client:

    cd client
    npm install
    bower install
    grunt server
