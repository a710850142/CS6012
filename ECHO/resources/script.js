const messagesBox = document.querySelector(".chat-box");
const messageInput = document.getElementById("message-input");
const sendButton = document.getElementById("send");
const leaveButton = document.getElementById("leave");
const usernameInput = document.getElementById("username");
const roomnameInput = document.getElementById("roomname");
const peopleBox = document.querySelector(".people-list");

let ws = new WebSocket('ws://localhost:8080')
ws.onopen = handleOpenCB = (event) =>{
//contains the data about the message (event) that caused this
    console.log('Connected to the WebSocket server');

};
ws.onmessage = handleMsgCB= (event) =>{
    console.log(event.data)
    let data = JSON.parse(event.data)
    let text = document.createElement("blockquote")
    let people = document.createElement("blockquote")
    switch(data.type){
        case 'join':
            text.textContent = data.user + " has joined " + data.room
            people.textContent = data.user
            people.id = "user-" + data.user
            peopleBox.appendChild(people)
            
            break;
        case 'message':
            text.textContent = data.user + " :" + data.message
            
            break;
        case 'leave':
            text.textContent = data.user + " has left the room.";
            let userElement = document.getElementById('user-' + data.user);
            peopleBox.removeChild(userElement)
            break;
    }
    messagesBox.appendChild(text)
    
    
    console.log('Received message:', event.data);
    console.log('Received data type:', typeof event.data);
};




roomnameInput.addEventListener("keypress", (e) =>{
    let user = usernameInput.value.toLowerCase();
    let roomname = roomnameInput.value;
    let vaild = true;
    if(e.keyCode === 13){
        for (let char of roomname){
            if (!(char >= 'a' && char <= 'z')){ //only lowcase
                vaild = false;
            }
            if (roomname.includes(' ')) {//no spaces
                vaild = false;
            }
        }
        if(vaild){
            let message = {"type": "join", "user": user, "room": roomname}
            ws.send(JSON.stringify(message));
            // ws.send("join" + " " + user + " " + roomname)
        }
        else{
            alert("only lowercase letters (and no spaces) ")
        }
    }

})
messageInput.addEventListener("keypress", (e) =>{
    if(e.keyCode === 13){
        let user = usernameInput.value;
        let roomname = roomnameInput.value;
        let message = {"type": "message", "user": user, "room": roomname,"message":messageInput.value}
        ws.send(JSON.stringify(message));
        // ws.send("message" + " " + messageInput.value)
    }
    
})
sendButton.addEventListener("click",(e) =>{
    let user = usernameInput.value;
    let roomname = roomnameInput.value;
    let message = {"type": "message", "user": user, "room": roomname,"message":messageInput.value}
    ws.send(JSON.stringify(message));
    // ws.send("message" + " " + messageInput.value)
})

leaveButton.addEventListener("click",(e) =>{
    // ws.send("leave")
    let user = usernameInput.value;
    let roomname = roomnameInput.value;
    let message = {"type": "leave", "user": user, "room": roomname}
    ws.send(JSON.stringify(message));
})

