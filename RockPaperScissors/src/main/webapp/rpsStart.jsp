<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<html lang="en">
<head>
   <meta name="viewport" content="width=device-width, initial-scale=1">
   <!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" 
   integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
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
</body>
</html>
