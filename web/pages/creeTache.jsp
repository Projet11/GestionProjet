<%-- 
    Document   : creeTache
    Created on : 05-nov.-2012, 16:10:31
    Author     : g33252
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type ="text/javascript" src ="js/verificationCreeTache.js"></script>
        <link href="css/bootstrap.min.css" rel="stylesheet" media="screen"/>
        <title>Ajout de tache</title>
    </head>
    <body>
        <script src="http://code.jquery.com/jquery-latest.js"></script>
        <script src="js/bootstrap.min.js"></script>

        <div class="hero-unit">
            <legend>Ajout de tache</legend>
            <!-- Debut formulaire -->

            <form name="Ajouter une tache"  method="GET" class="form-horizontal">
                <fieldset>
                    <!-- Nom -->
                    <div class="control-group">
                        <label class="control-label" for="nomDeTache">Nom</label>
                        <div class="controls">
                            <input type="text" name="nomDeTache" onblur="valideNom(this)"/>  
                            <span class="help-inline" id="nomError"></span>
                            <!-- class="text-warning" -->
                        </div>
                    </div>

                    <!-- Importance -->
                    <div class="control-group">
                        <label class="control-label" for="priorite">Importance</label>
                        <div class="controls">
                            <select name="priorite">
                                <option value="prioNormale" >Normale</option>
                                <option value="prioHaute">Haute</option>
                                <option value="prioTresHaute">Très importante</option>
                            </select>
                        </div>
                    </div>

                    <!-- Description -->
                    <div class="control-group">
                        <label class="control-label" for="description">Description  </label>
                        <div class="controls">
                            <textarea rows="3" cols="30" name="description">Description de la tache.</textarea>
                        </div>
                    </div>

                    <!-- Fin -->
                    <div class="controls">
                        <input id="nomDeTache" class="btn btn-success" type="submit" value="Creer" name="cible" onclick="return valideNom(Nom)"/>
                        <input id="butAnnulerTache" class="btn btn-danger" type="submit" value="Annuler" name="cible"/>
                    </div>
                </fieldset>
            </form>
        </div>
    </body>
</html>
