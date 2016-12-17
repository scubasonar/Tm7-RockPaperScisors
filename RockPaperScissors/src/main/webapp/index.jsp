<!DOCTYPE html>

<html lang="en">
<head>
   <meta name="viewport" content="width=device-width, initial-scale=1">
   <link rel="stylesheet" type="text/css" href="css/style.css">
   <!-- Latest compiled and minified CSS -->
   <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" 
   integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
   <link rel="stylesheet" type="text/css" href="css/style.css">
   <title>Rock, Paper, Scissors</title>
</head>
<body>
   <div class="container">
      <div id="startBox">
         <h1 class="text-center">Welcome to Rock, Paper, Scissors!</h1>
         <h2 class="text-center">Start a new game below.</h2>
         
         <form method="post" action="RPSServlet">
            <div class="form-group">
               <label for="emailInput">Email Address</label>
               <input type="email" class="form-control" id="emailInput" name="email" placeholder="enter your email" required>
            </div>
            <button id="newGameBtn" type="submit" value="New Game" class="btn btn-default">New Game</button>
         </form>
      </div>
   </div>
</body>
</html>