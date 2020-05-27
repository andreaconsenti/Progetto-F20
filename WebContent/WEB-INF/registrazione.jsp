
<!-- Frenkli Buzhiqi -->

<html>
   <head>
      <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
      <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
      <link type="text/css" rel="stylesheet" href="css/materialize.min.css"  media="screen,projection"/>
      <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
      <link rel="stylesheet" type="text/css" href="../CSS/main.css">
      <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
      <meta name="viewport" content="width=device-width, initial-scale=1" />
   </head>
   <body>

      <nav class="grey darken-2"><!--inizio della navbar-->
         <div class="nav-wrapper">
           <a class="left brand-logo">Progetto-F20</a>
           <ul class="right">
             <li>Registrazione </li>
          </ul>
         </div>
      </nav><!--fine della navbar-->

      <br>

      <div class="container"><!--inizio form regisrazione-->
         <form id="SignUpForm" action="/datiregistrazionepostati.html" align="center" method="POST"> <!-- metodo post per invio dati acquisiti -->
            <div class="row">
               <div class="input-field col s6">
                  <input id="FirstName" name="FirstName" type="text" class="validate">
                  <label for="FirstName">Nome</label>
               </div>
               <div class="input-field col s6">
                  <input id="LastName" name="LastName" type="text" class="validate">
                  <label for="LastName">Cognome</label>
               </div>
            </div>
            <br>
            <div class="center container">
               <div class="input-field col s8">
                  <input id="Email" name="Email" type="text" class="validate">
                  <label for="Email">Email</label>
               </div>
            </div>
            <br>
            <div class="row">
               <div class="input-field col s6">
                  <input id="Password" name="Password" type="password" class="validate">
                  <label for="Password">Password</label>
               </div>
               <div class="input-field col s6">
                  <input id="RipetiPassword" name="RipetiPassword" type="password" class="validate">
                  <label for="RipetiPassword">Ripeti Password</label>
               </div>
            </div>
            <div class="center container">
                <button class="btn waves-effect waves-light" type="submit" class="grey darken-2" id="submitForm">
                    Regitsrati
                    <i class="material-icons right">done</i>
                </button>
                <button class="btn waves-effect waves-light" type="reset" id="resetForm">
                    Cancella
                    <i class="material-icons right">cancel</i>
               </button>
            </div>
         </form>
      </div><!--fine form registrazione-->

      <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
      <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
      <script type="text/javascript" src="js/scripts.js"></script>
      <script type="text/javascript" src="js/materialize.min.js"></script>
      <script type="text/javascript" src="JS/main.js"></script>
    </body>
  </html>