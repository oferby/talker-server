var visible = false
var greetingSent = false;

var intent = {};
var dialogue = {};

function getTime() {
    var today = new Date();
    return today.getHours() + ":" + today.getMinutes();
}

function sendIntent(userInput){
    intent.text = userInput;
    console.log('Sending user input to server');
    dialogue.text = userInput
    stompClient.send("/app/parseDialogue", {}, JSON.stringify(dialogue));
}

function connect() {
    var socket = new SockJS('/intent-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {

        console.log('Connected: ' + frame);

        stompClient.subscribe('/topic/dialogue', function (hint) {
            dialogue  = JSON.parse(hint.body);
//            if (res.status == 'DONE') {
//            }
            addBotResponse(dialogue);

        });

    });
}

function showChatbot() {

    visible = !visible;
    if (visible) {
        $('#smartbot').show();

        async function displayGreeting() {
            await sleep(1000);
            addBotText('Hello, how may I help you?')
            greetingSent = true;
        }

        if (!greetingSent) {
            displayGreeting()
        }
    } else {
        $('#smartbot').hide();
    }
}






$(document).ready( function(){

    connect()

    $('#user-input').keyup(function(e){
        if(e.keyCode == 13)
        {
            var userInput = $('#user-input').val();
            $('#user-input').val('');
            addUserInput(userInput);
            scroll_window();
            sendIntent(userInput)
            console.log('user entered: ' + userInput);
        }
    });

})
