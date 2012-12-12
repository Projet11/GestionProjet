function onLoginSubmit()
{
    console.log("Test");
	return validateLogin();
}

function clearLoginErrors()
{
	// Effacer les messages d'erreur déjà présents, s'il y en a
    $("#fieldNom").removeClass();
    $("#fieldNom").addClass("control-group");
    $("#fieldMdp").removeClass();
    $("#fieldMdp").addClass("control-group");
}

function validateLogin()
{
	var result = true;
	
    if ($("#controlNom input").val().length <= 0)
    {
        $("#fieldNom").addClass("error");
        result = false;
    }
	
    if ($("#controlMdp input").val().length <= 0)
    {
        $("#fieldMdp").addClass("error");
        result = false;
    }
	
	return result;
}