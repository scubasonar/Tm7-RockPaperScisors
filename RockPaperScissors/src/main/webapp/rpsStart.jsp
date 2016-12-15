<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<html lang="en">
<head>
   <meta name="viewport" content="width=device-width, initial-scale=1">
   <!-- Latest compiled and minified CSS -->
   <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" 
   integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
   <link rel="stylesheet" type="text/css" href="css/style.css">
   <title>Rock, Paper, Scissors</title>
</head>
<body>
   <div class="row">
      <div class="col-md-4">
         <img class="img-responsive" src="images/rock_01.png">
         <form method="post" action="RPSGame">
         <button type="submit" value="Rock" name="value" class="btn btn-default">Rock</button>
         </form>
      </div>
      <div class="col-md-4">
         <img class="img-responsive" src="images/paper_01.png">
         <form method="post" action="RPSGame">
         <button type="submit" value="Paper" name="value" class="btn btn-default">Paper</button>
         </form>
      </div>
      <div class="col-md-4">
         <img class="img-responsive" src="images/scissors_01.png">
         <form method="post" action="RPSGame">
         <button type="submit" value="Scissors" name="value" class="btn btn-default">Scissors</button>
         </form>
      </div>
   </div>
   <div class="row">
      <h2>Session Stats</h2>
      <div class="col-md-3">
         <p>Total Wins: <% out.println(request.getAttribute("totalWon")); %> </p>
         <p>Total Losses: <% out.println(request.getAttribute("totalLost")); %> </p>
         <p>Total Draws: <% out.println(request.getAttribute("totalDraw")); %> </p>
         <p>Build Version: <% out.println(request.getAttribute("buildVersion")); %> </p>
      </div>
   </div>
</body>
</html>
