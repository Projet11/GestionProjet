function onSubmit()
{
	return validate();
}

function clearErrors()
{
	// Effacer les messages d'erreur déjà présents, s'il y en a
    $("#fieldNom").removeClass();
    $("#fieldNom").addClass("control-group");
    $("#fieldMdp").removeClass();
    $("#fieldMdp").addClass("control-group");
}

function validate()
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