<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>TicTacToe</title>
    <link rel="shortcut icon" href="data:image/x-icon;," type="image/x-icon">

    <!-- Bootstrap core CSS -->
    <link href="/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    <style>
        body {
            background-color: white;
        }

        .grid {
            background-color: rgba(0,0,0,.2);
            margin: 50px auto;
            vertical-align: center;
            width: 400px;
            height: 400px;
            display: grid;
            grid-template-columns: 1fr 1fr 1fr;
            grid-template rows: 1fr 1fr 1fr;
            grid-gap: 2px;
            border-radius: 4px;
            box-shadow: rgba(0, 0, 0, 0.3) 0px 17px 50px;
        }

        .space {
            background-color: white;
            transition: background-color .5s;
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .space:hover {
            background-color: rgba(0,0,0,0);
            transition: background-color .5s;
            cursor: pointer;
        }

        .X, .O {
            font-family: 'arial';
            font-size: 70px;
            position: absolute;
        }

        .X {
            color: tomato;
        }

        .O {
            color: #33DBFF;
        }

        .header {
            font-family: 'IBM Plex Sans Condensed', sans-serif;
            font-family: 'Montserrat', sans-serif;
            font-size: 35px;
            color: tomato;
            text-align: center;
            margin-top: 50px;
        }

        .reset {
            font-size: 20px;
            font-family: 'IBM Plex Sans Condensed', sans-serif;
            font-family: 'Montserrat', sans-serif;
            color: tomato;
            background-color: white;
            border: 1px solid tomato;
            padding: 5px;
            transition: border, color, .5s;
        }

        .reset:hover {
            cursor: pointer;
            color: #33DBFF;
            border: 1px solid #33DBFF;
            transition: border, color, .5s;
        }

        .reset:focus {
            outline: none;
        }

        .wrapper {
            display: flex;
            justify-content: center;
            margin-bottom: 50px;
        }

        .p1, .p2 {
            font-family: 'IBM Plex Sans Condensed', sans-serif;
            font-family: 'Montserrat', sans-serif;
            font-size: 20px;
        }

        .p1 {
            color: tomato;
            margin-left: 20px;
        }

        .p2 {
            color: #33DBFF;
            margin-right: 20px;
        }

        .flex {
            display: flex;
            flex-direction: row;
            justify-content: space-between;
        }
    </style>
</head>
<body>

    <div class='flex'>
        <div class='p1' id="countXWin">Player 1(X): 0</div>
        <h1 class='header' id="header">Tic Tac Toe</h1>
        <div class='p2' id="countOWin">Player 2(O): 0</div>
    </div>
    <div class='grid' id="grid">
        <div class='space' onclick="playerTapButton(this)" disabled="true">
            <div id='1'></div>
        </div>
        <div class='space' onclick="playerTapButton(this)" disabled="true">
            <div id='2'></div>
        </div>
        <div class='space' onclick="playerTapButton(this)" disabled="true">
            <div id='3'></div>
        </div>
        <div class='space' onclick="playerTapButton(this)" disabled="true">
            <div id='4'></div>
        </div>
        <div class='space' onclick="playerTapButton(this)" disabled="true">
            <div id='5'></div>
        </div>
        <div class='space' onclick="playerTapButton(this)" disabled="true">
            <div id='6'></div>
        </div>
        <div class='space' onclick="playerTapButton(this)" disabled="true">
            <div id='7'></div>
        </div>
        <div class='space' onclick="playerTapButton(this)" disabled="true">
            <div id='8'></div>
        </div>
        <div class='space' onclick="playerTapButton(this)" disabled="true">
            <div id='9'></div>
        </div>
    </div>

    <div class='wrapper'>
        <button class='reset' id="reset" onclick="resetGame()">Reset</button>
    </div>

    <div class='wrapper'>
        <button class='reset' id="exit" onclick="goToMainPage()">Exit</button>
    </div>

    <% if(request.getParameter("isSpectator") != null) { %>
        <script src="/js/spectator.js"></script>
    <% } else { %>
        <script src="/js/player.js"></script>
    <% } %>
    <script src="/js/jquery-3.5.1.slim.min.js"></script>
    <script src="/js/bootstrap.bundle.min.js"></script>
</body>
</html>