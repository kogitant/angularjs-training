# funmarket-server

## Deploying to Heroku

Add Heroku remote to your local git repo:

    git remote add heroku-funmarket-api https://git.heroku.com/funmarket-api.git

Push the actual server subtree to Heroku:

	git subtree push --prefix funmarket/funmarket-server heroku-funmarket-api master
