var exec = require('cordova/exec');

exports.isAvailable = function(success, error) {
    exec(function (res) {
        success(res === 'true');
    }, error, "Root", "isAvailable", []);
};

exports.run = function(command, success, error) {
    exec(success, error, "Root", "run", [command]);
};
