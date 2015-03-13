# funmarket-server

## Deploying to Heroku

Create Heroku app:

	heroku create funmarket-api --stack cedar --region eu

Install Mongolab plugin and activate Heroku config

	heroku addons:add mongolab --app funmarket-api
	heroku config:set spring.profiles.active=heroku
	heroku ps:scale web=1 --app funmarket-api

Add Heroku remote to your local git repo:

    git remote add heroku-funmarket-api https://git.heroku.com/funmarket-api.git

Push the actual server subtree to Heroku:

	git subtree push --prefix funmarket/funmarket-server heroku-funmarket-api master
