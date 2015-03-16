
angular.module('myApp')

.directive('angularTrainingModule', function () {
        return {
            restrict: 'E',
            template: '<span>Module #{{index}}: Presenter: {{presenter}}, description: {{description}}</span>',
            scope: {
                index: '=index',
                presenter: '=presenter',
                description: '=description'
            }
        }
    });

/*

 <angular-training-module index="module.index" presenter="module.presenter" description="module.description"></angular-training-module>

 */