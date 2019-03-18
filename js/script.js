function editForm() {

    var email = document.getElementById("eemail");
    var dissVal = document.getElementById("dissValue");
    var urlFiel = document.getElementById("urlField");

    email.contentEditable = 'true';
    dissVal.contentEditable = 'true';
    urlFiel.contentEditable = 'true';
    
    email.classList.add("edit");
    dissVal.classList.add("edit");
    urlFiel.classList.add("edit");
}
