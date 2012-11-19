function valideNom(nom) {
    if (isEmpty(nom)) {
        document.getElementById("nomError").innerHTML = "Attention le champs n'est pas rempli!";
        return false;
    } else {
        document.getElementById("nomError").innerHTML = "";
    }
    return true;
}

function isEmpty(champ) {
	return (champ == null || champ.value == null || champ.value == "");
}