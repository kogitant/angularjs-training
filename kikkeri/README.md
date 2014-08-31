# Kikkeri - Example AngularJS Application with Java backend

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
