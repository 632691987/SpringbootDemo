var myContextPath = global_getContextPath();


//var webSocket = $.simpleWebSocket({ url: 'ws://localhost:28081/textWebSocket' });


function onButtonClick() {
    $("#button1").click(function (e) {
        alert("fwfefewf");
        // var textMessage = $("#clientMsg").val();
        // alert(textMessage);
        //
        //
        // webSocket.send({ 'text': 'hello' }).done(function() {
        //     // message send
        // }).fail(function(e) {
        //     // error sending
        // });
    });
}

function pageInit() {
    onButtonClick();
}

$(document).ready(pageInit());