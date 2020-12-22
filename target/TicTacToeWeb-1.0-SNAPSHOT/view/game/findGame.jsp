<%@ page import="java.util.ArrayList" %>
<%@ page import="app.models.Game" %>
<%@ page import="app.helpers.GameHelper" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>TicTacToe</title>
    <link rel="shortcut icon" href="data:image/x-icon;," type="image/x-icon">

    <!-- Bootstrap core CSS -->
    <link href="/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
</head>
<body>

    <header>
        <div class="collapse bg-dark" id="navbarHeader">
            <div class="container">
                <div class="row">
                    <div class="col-sm-8 col-md-7 py-4">
                        <h4 class="text-white">TicTacToe</h4>
                        <p class="text-muted">I think that you all know how to play tic-tac-toe, so I won't explain the rules, just poke the buttons below, and then you'll figure it out</p>
                    </div>
                    <div class="col-sm-4 offset-md-1 py-4">
                        <h4 class="text-white">Contact</h4>
                        <ul class="list-unstyled">
                            <li><a href="#" class="text-white">Follow on Twitter</a></li>
                            <li><a href="#" class="text-white">Like on Facebook</a></li>
                            <li><a href="#" class="text-white">Email me</a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <div class="navbar navbar-dark bg-dark shadow-sm">
            <div class="container d-flex justify-content-between">
                <a href="/Index" class="navbar-brand d-flex align-items-center">
                    <strong>Go to main page</strong>
                </a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarHeader" aria-controls="navbarHeader" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
            </div>
        </div>
    </header>

    <div class="album py-5 bg-light">
        <div class="container">
            <div class="row">

                <% ArrayList<String> gamesList = (ArrayList<String>) request.getAttribute("gamesList"); %>
                <% if (gamesList.size() > 0) { %>
                    <% for (String s : gamesList) { %>
                    <% Game game = GameHelper.GetGameByUuid(s); %>
                    <% request.setAttribute("score",game.GetScore()); %>
                    <% request.setAttribute("s",s); %>
                    <div class="col-md-4">
                        <div class="card mb-4 box-shadow">
                            <img class="card-img-top" data-src="holder.js/100px225?theme=thumb&amp;bg=55595c&amp;fg=eceeef&amp;text=Thumbnail" alt="Thumbnail [100%x225]" style="height: 225px; width: 100%; display: block;" src="data:image/svg+xml;charset=UTF-8,%3Csvg%20width%3D%22348%22%20height%3D%22225%22%20xmlns%3D%22http%3A%2F%2Fwww.w3.org%2F2000%2Fsvg%22%20viewBox%3D%220%200%20348%20225%22%20preserveAspectRatio%3D%22none%22%3E%3Cdefs%3E%3Cstyle%20type%3D%22text%2Fcss%22%3E%23holder_1767644f972%20text%20%7B%20fill%3A%23eceeef%3Bfont-weight%3Abold%3Bfont-family%3AArial%2C%20Helvetica%2C%20Open%20Sans%2C%20sans-serif%2C%20monospace%3Bfont-size%3A17pt%20%7D%20%3C%2Fstyle%3E%3C%2Fdefs%3E%3Cg%20id%3D%22holder_1767644f972%22%3E%3Crect%20width%3D%22348%22%20height%3D%22225%22%20fill%3D%22%2355595c%22%3E%3C%2Frect%3E%3Cg%3E%3Ctext%20x%3D%22116.70000076293945%22%20y%3D%22120.3%22%3EThumbnail%3C%2Ftext%3E%3C%2Fg%3E%3C%2Fg%3E%3C%2Fsvg%3E" data-holder-rendered="true">
                            <div class="card-body">
                                <p class="card-text">This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>
                                <div class="d-flex justify-content-between align-items-center">
                                    <div class="btn-group">
                                        <button type="button" class="btn btn-sm btn-outline-secondary"><a href="/Game?uuid=${s}">Join as player</a></button>
                                        <button type="button" class="btn btn-sm btn-outline-secondary"><a href="/Game?uuid=${s}&isSpectator=true">Join as spectator</a></button>
                                    </div>
                                    <small class="text-muted"> ${score} </small>
                                </div>
                            </div>
                        </div>
                    </div>
                    <% } %>
                <% } else { %>
                    <h1>No games aviable</h1>
                <% } %>

            </div>
        </div>

        <script src="/js/jquery-3.5.1.slim.min.js"></script>
        <script src="/js/bootstrap.bundle.min.js"></script>
    </div>

    <footer class="fixed-bottom">
        <div class="container">
            <p class="float-right">
                <a href="#">Back to top</a>
            </p>
            <p>Album example is © Bootstrap, but please download and customize it for yourself!</p>
            <p>New to Bootstrap? <a href="https://getbootstrap.com/">Visit the homepage</a> or read our <a href="/docs/4.5/getting-started/introduction/">getting started guide</a>.</p>
        </div>
    </footer>
</body>
</html>
