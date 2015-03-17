# Funmarket exercise

- Check out UI drafts for outline

## Tips on what to do

REST API endpoint available at: http://funmarket-api.herokuapp.com/

## Setting up project

Install Yeoman and yeoman generators

	npm install -g yo
	npm install -g generator-gulp-angular

Scaffold project base:

	yo gulp-angular 

## Implement features

1. Implement routes / controller stubs
2. Implement backend connection with ngResource
3. Create frontpage view that displays active ads from backend
4. Create "add new market ad" view
	5.1. Add input components for fields
	5.2. Add validators to validate user input
		5.2.1. Add validators as required
		5.2.2. Display validation errors with ngMessages		
	5.3. Save market ad to backend
