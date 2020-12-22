let playerType = "O"
let isButtonsBlock = true
let countXWins = 0;
let countOWins = 0;

const queryString = window.location.search
const urlParams = new URLSearchParams(queryString)
const gameUuid = urlParams.get('uuid')

let ws = new WebSocket("ws://185.152.139.127:55451/game/" + gameUuid)
ws.onopen = function() {
    console.log("open")
}
ws.onmessage = function(data) {
    console.log("message: " + data.data)
    switch (data.data) {
        case 'StartGame':
            console.log("case start game")
            playerType = "X"
            isButtonsBlock = false
            break
        case 'WatchPerson':
            console.log("case watch person")
            playerType = "watch"
            break
        case 'resetGame':
            for (let i = 1; i <= 9; i++){
                let cell = document.getElementById(i)
                cell.innerHTML = ""
                cell.className = ""
            }

            countXWins = 0;
            countOWins = 0;
            let countXWinLabel = document.getElementById("countXWin")
            countXWinLabel.innerHTML = "Player 1(X): " + countXWins
            let countOWinLabel = document.getElementById("countOWin")
            countOWinLabel.innerHTML = "Player 2(O): " + countOWins
            break
        case 'Twin':
        case 'Owin':
        case 'Xwin':
            isButtonsBlock = true
            console.log("case win " + data.data)
            if (data.data == 'Xwin'){
                countXWins++
                let countXWinLabel = document.getElementById("countXWin")
                countXWinLabel.innerHTML = "Player 1(X): " + countXWins
            } else if(data.data == 'Owin') {
                countOWins++
                let countOWinLabel = document.getElementById("countOWin")
                countOWinLabel.innerHTML = "Player 2(O): " + countOWins
            }

            let label = document.getElementById("header")
            if (data.data[0] != 'T'){
                label.innerHTML = data.data[0] + " win"
            } else {
                label.innerHTML = "Tie"
            }


            for (let i = 1; i <= 9; i++){
                let cell = document.getElementById(i)
                cell.innerHTML = ""
                cell.className = ""
            }
            if (playerType == 'X')
                isButtonsBlock = false
            break
        case '"1X"':
        case '"1O"':
        case '"2X"':
        case '"2O"':
        case '"3X"':
        case '"3O"':
        case '"4X"':
        case '"4O"':
        case '"5X"':
        case '"5O"':
        case '"6X"':
        case '"6O"':
        case '"7X"':
        case '"7O"':
        case '"8X"':
        case '"8O"':
        case '"9X"':
        case '"9O"':
            console.log("case other player tap button " + data.data)
            let divToChange = document.getElementById(data.data[1])
            divToChange.innerHTML = data.data[2]
            divToChange.classList.add(data.data[2]);
            isButtonsBlock = false
            break
    }
}
ws.onclose = function() {
    console.log("close")
}
ws.onerror = function(err) {
    console.log(err)
}

function playerTapButton(button){
    if (isButtonsBlock == false){
        let divToChange = button.childNodes[1]
        if (divToChange.innerHTML != "X" && divToChange.innerHTML != "O"){
            divToChange.innerHTML = playerType
            divToChange.classList.add(playerType)
            let jsonToSend = JSON.stringify(divToChange.id + playerType)
            ws.send(jsonToSend)
            isButtonsBlock = true
        }
    }
}

function resetGame(){
    let jsonToSend = JSON.stringify("resetGame")
    ws.send(jsonToSend)
}

function goToMainPage(){
    window.location.href = "http://185.152.139.127:55451";
}