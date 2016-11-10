# cordova-plugin-root

Detect, request and use superuser permissions on Android devices.

## Installation

Cordova: 
```
cordova plugin add cordova-plugin-root
```

Ionic:
```
ionic plugin add cordova-plugin-root
```

## Usage

### isAvailable
Checks if SU functionality is available. Note that **THIS WILL OPEN A SU PERMISSION POPUP**.

```
root.isAvailable(function(res) {
    if(res)
        console.log("SU access is available and granted");
    else
        console.log("SU access was denied or is not available");
}, function() {
    console.error('An error occoured while checking for SU availability');
});
```

### run
Executes the given command as root.  
Please note that the fail callback will not always be executed.  
You should manually verify that the command you issued actually worked.  

```
root.run('whoami', function(res) {
    console.log(res); //['root']
}, function() {
    console.error('An error occoured while executing the command.');
});
```
