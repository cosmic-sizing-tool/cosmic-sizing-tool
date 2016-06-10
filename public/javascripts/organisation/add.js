document.addEventListener('DOMContentLoaded', function () {
    bindSubmitController();
});

function bindSubmitController() {
    var form = document.getElementById('form-organisation');
    form.addEventListener('submit', function(e) {
           e.preventDefault();
           createOrganisation();
    });
}

function createOrganisation() {
    var nomOrganisation = document.getElementById('nom-organisation').value;
    var description = document.getElementById('description').value;
    var urlOrganisation = document.getElementById('url-organisation').value;
    var urlImage = document.getElementById('url-image').value;
    var nomContact = document.getElementById('nom-contact').value;
    var telContact = document.getElementById('tel-contact').value;
    var courrielContact = document.getElementById('organization_billing_email').value;
    var adresse1 = document.getElementById('adresse-1').value;
    var adresse2 = document.getElementById('adresse-2').value;
    var pays = document.getElementById('pays').value;
    var etat = document.getElementById('etat').value;
    var ville = document.getElementById('ville').value;
    var organisation = {};
    organisation.name = nomOrganisation;
    organisation.description = description;
    organisation.url_organisation = urlOrganisation;
    organisation.url_image = urlImage;
    organisation.nom_contact = nomContact;
    organisation.tel_contact = telContact;
    organisation.courriel_contact = courrielContact;
    organisation.adresse1 = adresse1;
    organisation.adresse2 = adresse2;
    organisation.pays = pays;
    organisation.etat = etat;
    organisation.ville = ville;
    sendOrganisation(organisation);
}

function sendOrganisation(organisation) {
      var xhr = new XMLHttpRequest();
      xhr.open('POST', ('./add'), true);
      xhr.setRequestHeader("Content-Type","application/json");
      var data = JSON.stringify(organisation);
      xhr.send(data);
      xhr.onreadystatechange = (function() {
            var message = document.getElementById('message');
        if (xhr.readyState === 4 && xhr.status === 200) {
            message.textContent = "L'organisation &agrave; bien &eacute;t&eacute; cr&eacute;er!";
            message.className = "alert alert-success";
        } else {
             message.textContent = "Erreur lors de la cr&ecute;ation de l'organisation!";
            message.className = "alert alert-danger";
            console.log(xhr.response);
        }
            message.style.display = 'block'; 
      });
}