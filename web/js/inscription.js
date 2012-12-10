function onSubmit()
{
    if (!valider())
        return false;
	
	return true;
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
    if ($("#controlPass1 input").val() != $("#controlPass2 input").val())
    {
		$("#controlPass1").removeClass();
		$("#controlPass1").addClass("control-group");
		$("#controlPass1").addClass("error");
		$("#controlPass2").removeClass();
		$("#controlPass2").addClass("control-group");
		$("#controlPass2").addClass("error");

        // Si une erreur est déjà survenue, ajouter un line break avant
        // d'ajouter notre message d'erreur
        if (!result)
            message += "<br />";
		
        message += "Les deux mots de passe ne correspondent pas";
        result = false;
    }
	
    // Afficher le(s) message(s) d'erreur, s'il y en a
    $("#div-status").removeClass();
    $("#div-status").addClass("text-error");
    $("#div-status").html(message);

    return result;
}

function clearErrors()
{
    // Effacer les messages d'erreur déjà présents, s'il y en a
    $("#controlNom").removeClass();
    $("#controlNom").addClass("control-group");
    $("#controlPrenom").removeClass();
    $("#controlPrenom").addClass("control-group");
    $("#controlMail").removeClass();
    $("#controlMail").addClass("control-group");
    $("#controlLogin").removeClass();
    $("#controlLogin").addClass("control-group");
    $("#controlPass1").removeClass();
    $("#controlPass1").addClass("control-group");
    $("#controlPass2").removeClass();
    $("#controlPass2").addClass("control-group");
}

function checkChampsVides()
{
    var result = true;
	
    if ($("#controlNom input").val().length <= 0)
    {
        $("#controlNom").addClass("error");
        result = false;
    }
	
    if ($("#controlPrenom input").val().length <= 0)
    {
        $("#controlPrenom").addClass("error");
        result = false;
    }
	
    if ($("#controlMail input").val().length <= 0)
    {
        $("#controlMail").addClass("error");
        result = false;
    }
	
    if ($("#controlLogin input").val().length <= 0)
    {
        $("#controlLogin").addClass("error");
        result = false;
    }
	
    if ($("#controlPass1 input").val().length <= 0)
    {
        $("#controlPass1").addClass("error");
        result = false;
    }
	
    if ($("#controlPass2 input").val().length <= 0)
    {
        $("#controlPass2").addClass("error");
        result = false;
    }
	
    return result;
}

function checkMail()
{
    var mail = $("#controlMail input").val();

    // Si aucune adresse mail n'a été entrée, ne pas indiquer d'erreur de format
    if (mail == null || mail.length <= 0)
        return true;

    var exp = /^[a-zA-Z0-9._\-]+@[a-zA-Z0-9.\-]+\.[a-zA-Z]+$/;
    var result = exp.test(mail);
	
    if (result == false)
        $("#controlMail").addClass("error");

    return result == false ? false : true;
}