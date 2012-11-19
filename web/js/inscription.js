function on_submit()
{
    if (!valider())
        return false;

    $.ajax({
        url: "FrontController?cible=inscription",
        type: "POST",
        data: {
            txtLogin: $("#login").val(),
            txtNom: $("#nom").val(),
            txtPrenom: $("#prenom").val(),
            txtMail: $("#email").val(),
            txtPass: $("#mdp").val()
        },
        success: function (data, textStatus, jqXHR) {
            if (data.substring(0, 6) == "%%OK%%")
            {
                $("#div-status").removeClass();
                $("#div-status").addClass("txt-success");
                $("#div-status").html("Votre compte a bien &eacute;t&eacute; cr&eacute;&eacute;");
                $("#login").val("");
                $("#nom").val("");
                $("#prenom").val("");
                $("#email").val("");
                $("#mdp").val("");
                $("#mdpconfirm").val("");
            }
            else
            {
                $("#div-status").removeClass();
                $("#div-status").addClass("txt-error");
                $("#div-status").html("L'inscription n'a pas eu lieu: " + data);
            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
            $("#div-status").removeClass();
            $("#div-status").addClass("txt-error");
            $("#div-status").html("L'inscription n'a pas eu lieu: " + errorThrown);
        }
    });
}

function valider()
{
    var result = true;
    var message = "";
	
    clearErrors();
	
    // Vérifier si des champs ont été laissés vides
    if (!checkChampsVides())
    {
        message = "Un champ a été laissé vide";
        result = false;
    }
	
    // Vérifier le format de l'adresse mail
    if (!checkMail())
    {
        // Si une erreur est déjà survenue, ajouter un line break avant
        // d'ajouter notre message d'erreur
        if (!result)
            message += "<br />";

        message += "L'email n'est pas valide";
        result = false;
    }

    // Vérifier la correspondance entre les deux mots de passe
    if ($("#mdp").val() != $("#mdpconfirm").val())
    {
        $("#errorPass2").html("Les mots de passe ne correspondent pas");
		
        // Si une erreur est déjà survenue, ajouter un line break avant
        // d'ajouter notre message d'erreur
        if (!result)
            message += "<br />";
		
        message += "Les deux mots de passe ne correspondent pas";
        result = false;
    }
	
    // Afficher le(s) message(s) d'erreur, s'il y en a
    $("#div-status").removeClass();
    $("#div-status").addClass("txt-error");
    $("#div-status").html(message);

    return result;
}

function clearErrors()
{
    // Effacer les messages d'erreur déjà présents, s'il y en a
    $("#errorNom").removeClass();
    $("#errorNom").addClass("control-group");
    $("#errorPrenom").removeClass();
    $("#errorPrenom").addClass("control-group");
    $("#errorMail").removeClass();
    $("#errorMail").addClass("control-group");
    $("#errorLogin").removeClass();
    $("#errorLogin").addClass("control-group");
    $("#errorPass1").removeClass();
    $("#errorPass1").addClass("control-group");
    $("#errorPass2").removeClass();
    $("#errorPass2").addClass("control-group");
}

function checkChampsVides()
{
    var result = true;
	
    if ($("#nom").val().length <= 0)
    {
        $("#errorNom").addClass("error");
        result = false;
    }
	
    if ($("#prenom").val().length <= 0)
    {
        $("#errorPrenom").addClass("error");
        result = false;
    }
	
    if ($("#email").val().length <= 0)
    {
        $("#errorMail").addClass("error");
        result = false;
    }
	
    if ($("#login").val().length <= 0)
    {
        $("#errorLogin").addClass("error");
        result = false;
    }
	
    if ($("#mdp").val().length <= 0)
    {
        $("#errorPass1").addClass("error");
        result = false;
    }
	
    if ($("#mdpconfirm").val().length <= 0)
    {
        $("#errorPass2").addClass("error");
        result = false;
    }
	
    return result;
}

function checkMail()
{
    var mail = $("#email").val();

    // Si aucune adresse mail n'a été entrée, ne pas indiquer d'erreur de format
    if (mail == null || mail.length <= 0)
        return true;

    var exp = /^[a-zA-Z0-9._\-]+@[a-zA-Z0-9.\-]+\.[a-zA-Z]+$/;
    var result = exp.test(mail);
	
    console.log("Test:   " + mail);
    console.log("Result: " + result);
	
    if (result == false)
        $("#errorMail").addClass("error");

    return result == false ? false : true;
}