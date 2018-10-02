/**
 * Get context path, if no context, then is empty
 */
function global_getContextPath(){
    //获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
    var curWwwPath=window.document.location.href;
    //获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
    var pathName=window.document.location.pathname;
    var pos=curWwwPath.indexOf(pathName);
    //获取主机地址，如： http://localhost:8083
    var localhostPaht=curWwwPath.substring(0,pos);
    //获取带"/"的项目名，如：/uimcardprj
    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
    return projectName;
}

/**
 *
 * check if "strMessage" is empty or null
 * isNullOrEmpty(strMessage) --> true
 *
 */
function isNullOrEmpty(strMessage) {
    return typeof strMessage == 'string' && !strMessage.trim() || typeof strMessage == 'undefined' || strMessage === null;
}

/**
 *
 * http://localhost:28081/login?aakey=value22
 * var value = getUrlParamValue("aakey");
 *
 */
function getUrlParamValue(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]); return null;
}

/**
 * xhr: {"readyState":4,"responseText":"","status":401,"statusText":"Unauthorized"}
 * Standalone function, used in AJAX function
 */
function global_handler_ajax_exception(xhr, status, error) {
    bootbox.setLocale($("#hid_txt_language").val());
    if(xhr.status == HTTP_STATUS_SHUTDOWN) {
        bootbox.confirm($("#hid_txt_err_shutdown").val(), function(result){
            window.location.href = global_getContextPath();
        });
    }

    if(xhr.status == HTTP_STATUS_UNAUTHORIZED) {
        window.location.href = global_getContextPath();
    }

    if(xhr.status != HTTP_STATUS_ACCEPTED || xhr.status != HTTP_STATUS_NO_CONTENT) {
        bootbox.alert({
            title: "Error",
            message: parseErrorMessage(xhr.responseText),
            className: 'bb-alternate-modal',
            size: 'large'
        });
    }
}

/**
 * errorMessages could be string or array, and show the error window with bootbox plugin
 */
function showErrorMessageWindow(errorMessages) {
    if(errorMessages.length == 0) {
        return;
    }

    var messageArray = new Array();
    if(!$.isArray(errorMessages)) {
        messageArray.push(errorMessages);
    } else {
        messageArray = errorMessages;
    }

    var message = "";

    for (var i=0; i < messageArray.length; i++) {
        message = message + messageArray[i] + "<br />";
    }

    handlerBusinessException(message);
}

/**
 *
 * Use javascript try-catch
 *
 */
function handlerFrontEndException(err) {
    console.error("ErrorName:" + err.name);
    console.error("ErrorMessage:" + err.message);
}


/**
 *
 * Private function
 *
 */
function handlerBusinessException(errMessage) {
    bootbox.alert({
        message: errMessage,
        className: 'bb-alternate-modal',
        size: 'large'
    });
}

/**
 *
 * Private function
 *
 */
function parseErrorMessage(errorMessage) {
    var message = JSON.parse(errorMessage);
    var errorCode = "";
    var errorDetail = "";
    try{
        errorCode   = message.httpCode;
        errorDetail = message.message;
    } catch(e){}

    return "Error code：" + errorCode + "<br />" + "Error message：" + errorDetail;
}

var HTTP_STATUS_ACCEPTED=202;
var HTTP_STATUS_NO_CONTENT=202;
var HTTP_STATUS_UNAUTHORIZED=401;
var HTTP_STATUS_SHUTDOWN=0;