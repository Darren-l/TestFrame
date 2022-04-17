var testjs = {};
testjs.os = {};
testjs.os.isIOS = /iOS|iPhone|iPad|iPod/i.test(navigator.userAgent);
testjs.os.isAndroid = !testjs.os.isIOS;

testjs.takeNativeAction = function(commandname, parameters){
    console.log("testjs takenativeaction")
    var request = {};
    request.name = commandname;
    request.param = parameters;
    if(window.testjs.os.isAndroid){
        console.log("android take native action" + JSON.stringify(request));
        window.baseWebView.takeNativeAction(JSON.stringify(request));
    } else {
        window.webkit.messageHandlers.baseWebView.postMessage(JSON.stringify(request))
    }
}

window.testjs = testjs;
