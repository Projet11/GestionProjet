function valideSVN() {
    if ($("#divPourcentage input").val()!= "100" && !isEmpty($('#divSVN input').val())) {
        document.getElementById("Error").innerHTML = "Attention, on ne peut assigner un numéro de version que si la tache est terminée.";
        return false;
    } else {
        document.getElementById("Error").innerHTML = "";
    }
    return true;
}
function validePourcentage() {
    if (isEmpty($("#divPourcentage input").val())) {
        document.getElementById("Error").innerHTML = "Attention les champs ne sont pas remplis!";
        return false;
    } else {
        document.getElementById("Error").innerHTML = "";
    }
    return true;
}

function validate(){
    return (valideSVN() && validePourcentage());
}
function isEmpty(champ) {
    return (champ == null || champ == "");
}