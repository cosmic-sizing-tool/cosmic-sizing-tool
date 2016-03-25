/* SHEET DROPDOWN */
function getSheetDropdown(){
	return $('#dd_sheet');
}

function getSheetOptions(){
	return $('#opt_sheet');
}

function sheetDropdownEnabled(enable){
	getSheetDropdown().prop('disabled', !enable);
}

/* LINE DROP DOWN */
function getLineDropdown(){
	return $('#dd_line');
}

function getLineOptions(){
	return $('#opt_line');
}

function lineDropdownEnabled(enable){
	getLineDropdown().prop('disabled', !enable);
}

/* COLUMN GROUPS */
function getMesureDetailsControl(){
	return $("#frm_details");
}

/* COLUMN DROP DOWNS */

// CHANGEMENT
function getChangeDropDown(){
	return $("#dd_changement");
}

function getChangeOptions(){
	return $("#opt_changement");
}

// SYSTEME
function getSystemDropDown(){
	return $("#dd_systeme");
}

function getSystemOptions(){
	return $("#opt_systeme");
}

// REFERENCE
function getReferenceDropDown(){
	return $("#dd_reference");
}

function getReferenceOptions(){
	return $("#opt_reference");
}

// PROCESSUS
function getProcessDropDown(){
	return $("#dd_processus_fonctionnel");
}

function getProcessOptions(){
	return $("#opt_processus_fonctionnel");
}

// DATA GROUP
function getDataGroupDropDown(){
	return $("#dd_groupe_donnee");
}

function getDataGroupOptions(){
	return $("#opt_groupe_donnee");
}

// ENTREE
function getEnterDropDown(){
	return $("#dd_entree");
}

function getEnterOptions(){
	return $("#opt_entree");
}

// SORTIE
function getExitDropDown(){
	return $("#dd_sortie");
}

function getExitOptions(){
	return $("#opt_sortie");
}

// ECRITURE
function getWriteDropDown(){
	return $("#dd_ecriture");
}

function getWriteOptions(){
	return $("#opt_ecriture");
}

// LECTURE
function getReadDropDown(){
	return $("#dd_lecture");
}

function getReadOptions(){
	return $("#opt_lecture");
}

// COMMENTAIRE
function getCommentDropDown(){
	return $("#dd_commentaire");
}

function getCommentOptions(){
	return $("#opt_commentaire");
}

function hideOutputs(){
	$('#sheet_output').hide();
	$('#line_output').hide();
	$('#file_output').hide();
}

function displayOutput(checkbox){
	switch(checkbox.name){
		case "chk_sheet_output":
		if(checkbox.checked)
			$('#sheet_output').show();
		else
			$('#sheet_output').hide();
		break;
		case "chk_line_output":
		if(checkbox.checked)
			$('#line_output').show();
		else
			$('#line_output').hide();
		break;
		case "chk_file_output":
		if(checkbox.checked)
			$('#file_output').show();
		else
			$('#file_output').hide();
		break;
	}
}

function setOutput(element, data){
    // fill output with a json of the data
    var output = "";
	if(data != null) output = JSON.stringify(data, 2, 2);
	if(element.innerText === undefined) element.textContent = output;
	else element.innerText = output;
}