/* 
Notes for back-end

  1. Du côté back-end, ne pas sauvegarder le nom d'utilisateur,
    car malgré le 'disabled' sur l'input, ça peut être manipulé 
    via les outils de développeurs

  2. Il faut trouver un moyen de se protéger contre les utilisateurs
    malicieux qui pourraient tenter de truquer le pattern ID afin de
    modifier ou supprimer des patterns qui ne lui appartiennent pas.
*/

$(window).resize(windowResize);

function windowResize() {
  $("#patternModify").css("marginTop", $("nav").height())
}

function displayPatternNFp(pattern) {
  var nFp = pattern.Pattern_FP.length;
  $("#patternNFp").text(nFp);
}

function displayPatternSize(pattern) {
  var nE = 0,
    nX = 0,
    nR = 0,
    nW = 0;
  for (var i = 0; i < pattern.Pattern_FP.length; i++) {
    var fp = pattern.Pattern_FP[i];
    for (var j = 0; j < fp.Pattern_DGDM.length; j++) {
      var dgdm = fp.Pattern_DGDM[j];
      if (dgdm.Pattern_DM_E) nE++;
      if (dgdm.Pattern_DM_X) nX++;
      if (dgdm.Pattern_DM_R) nR++;
      if (dgdm.Pattern_DM_W) nW++;
    }
  }
  $("#patternSize").text(nE + nX + nR + nW);
  displayPatternSizeE(nE);
  displayPatternSizeX(nX);
  displayPatternSizeR(nR);
  displayPatternSizeW(nW);
}

function displayPatternSizeE(size) {
  $("#cfpE .patternValue").text(size);
  if (size > 0) {
    $("#cfpE").show();
  }
}

function displayPatternSizeX(size) {
  $("#cfpX .patternValue").text(size);
  if (size > 0) {
    $("#cfpX").show();
  }
}

function displayPatternSizeR(size) {
  $("#cfpR .patternValue").text(size);
  if (size > 0) {
    $("#cfpR").show();
  }
}

function displayPatternSizeW(size) {
  $("#cfpW .patternValue").text(size);
  if (size > 0) {
    $("#cfpW").show();
  }
}

function displayPatternDateCreated(date) {
  $("#patternDateCreated").text(formatDate(date));
}


function displayPatternUser(user) {
  $("#patternUserID").text(user);
}

function displayPatternName(name) {
  $("#Pattern_Name").val(name);
}

function displayPatternDescription(desc) {
  $("#Pattern_Desc").val(desc);
}

function displayPatternTable(fps) {
  var table = $("#patternTable tbody").html("");
  for (var i = 0; i < fps.length; i++) {
    var fp = fps[i];
    var dgdms = fp.Pattern_DGDM;
    var fpSize = 0;
    for (var j = 0; j < dgdms.length; j++) {
      var dgdm = dgdms[j];
      if (dgdm.Pattern_DM_E) fpSize++;
      if (dgdm.Pattern_DM_X) fpSize++;
      if (dgdm.Pattern_DM_R) fpSize++;
      if (dgdm.Pattern_DM_W) fpSize++;
    }

    for (var j = 0; j < dgdms.length; j++) {
      var dgdm = dgdms[j];
      if (j === 0) {
        var row = $("<tr/>").append(
          firstPatternColumn(fp),
          secondPatternColumn(dgdm),
          thirdPatternColumn(dgdm),
          fourthPatternColumn(fpSize)
        );
      } else {
        var row = $("<tr/>").append(
          $("<td/>"),
          secondPatternColumn(dgdm),
          thirdPatternColumn(dgdm),
          $("<td/>")
        );
      }
      if (i % 2 === 0)
        row.addClass("even");
      table.append(row);
    }
  }
}

function bindNewRowButton() {
  $("#createNewRow").on("click", clickedNewRowButton);
}

function clickedNewRowButton() {
  var pattern = collectPattern();
  var fp = {
    Pattern_ID: "",
    Pattern_FP_ID: "",
    Pattern_DGDM:[{}],
  }
  pattern.Pattern_FP.push(fp);
  displayPattern(pattern);
}

function firstPatternColumn(fp) {
  var cell = $("<td/>");
  var input = $("<input type='text'>");
  input.val(fp.Pattern_FP_ID);
  input.attr("data-id", fp.Pattern_ID);
  cell.append(input);
  return cell;
}

function secondPatternColumn(dgdm) {
  var cell = $("<td/>");
  var input = $("<input type='text'>");
  input.val(dgdm.Pattern_DG);
  cell.append(input, "<br/>");
  return cell;
}

function thirdPatternColumn(dgdm) {
  var cell = $("<td/>");
  var input = $("<input type='text'>");
  var val = "";
  if (dgdm.Pattern_DM_E) val += "E";
  if (dgdm.Pattern_DM_X) val += "X";
  if (dgdm.Pattern_DM_R) val += "R";
  if (dgdm.Pattern_DM_W) val += "W";
  input.val(val);
  cell.append(input, "<br/>");
  return cell;
}

function fourthPatternColumn(fpSize) {
  var cell = $("<td/>");
  var span = $("<span/>");
  span.text(fpSize);
  cell.append(span);
  return cell;
}

function getPatternNFp() {
  return $("#patternNFp").text();
}

function getPatternSize() {
  return $("#patternSize").text();
}

function getPatternDateCreated() {
  return $("#patternDateCreated").text();
}

function getPatternName() {
  return $("#Pattern_Name").val();
}

function getPatternDescription() {
  return $("#Pattern_Desc").val();
}

function getPatternUser() {
  return $("#patternUserID").text();
}

function getPatternSizeE() {
  return $("#cfpE .patternValue").text();
}

function getPatternSizeX() {
  return $("#cfpX .patternValue").text();
}

function getPatternSizeR() {
  return $("#cfpR .patternValue").text();
}

function getPatternSizeW() {
  return $("#cfpW .patternValue").text();
}

function bindSaveButton() {
  $("#patternSave").on("click", savePattern);
}

function patternChanged() {
  var updatedPattern = collectPattern();
  displayPattern(updatedPattern);
  validatePattern(updatedPattern);
}

function collectPattern() {
  var updatedPattern = {};
  updatedPattern.Pattern_Name = getPatternName();
  updatedPattern.Pattern_Desc = getPatternDescription();
  updatedPattern.User_ID = getPatternUser();
  updatedPattern.dateCreated = getPatternDateCreated();
  updatedPattern.Pattern_FP = [];

  var fp;
  var rows = $("#patternTable tbody tr");
  for (var i = 0; i < rows.length; i++) {
    var row = $(rows[i]);
    var input1 = row.find("td:eq(0) input");
    if (input1.length > 0) {
      if (i > 0)
        updatedPattern.Pattern_FP.push(fp);
      fp = {};
      fp.Pattern_DGDM = [];
      fp.Pattern_ID = input1.attr("data-id");
      fp.Pattern_FP_ID = input1.val();
    }
    var dgdm = {};
    var input2 = row.find("td:eq(1) input");
    var input3 = row.find("td:eq(2) input");
    dgdm.Pattern_FP_ID = fp.Pattern_FP_ID;
    dgdm.Pattern_DG = input2.val();
    dgdm.Pattern_DM_E = input3.val().indexOf("E") > -1 ? 1 : 0;
    dgdm.Pattern_DM_X = input3.val().indexOf("X") > -1 ? 1 : 0;
    dgdm.Pattern_DM_R = input3.val().indexOf("R") > -1 ? 1 : 0;
    dgdm.Pattern_DM_W = input3.val().indexOf("W") > -1 ? 1 : 0;
    fp.Pattern_DGDM.push(dgdm);
  }
  if( typeof fp === "object") updatedPattern.Pattern_FP.push(fp);
  return updatedPattern;
}

function displayPattern(pattern) {
  displayPatternNFp(pattern);
  displayPatternSize(pattern);
  displayPatternDateCreated(pattern.dateCreated);
  displayPatternUser(pattern.User_ID);
  displayPatternName(pattern.Pattern_Name);
  displayPatternDescription(pattern.Pattern_Desc);
  displayPatternTable(pattern.Pattern_FP);
}

function savePattern() {
  var updatedPattern = collectPattern();
  validatePattern(updatedPattern);
  displayPattern(updatedPattern);
  sendPattern(updatedPattern);
}

function sendPattern(pattern){
  /* Send the pattern to the server */
}

/*
Validation rules
  - A FP must have at least 1 DG and at least 1 DM
  - A FP is unique in the pattern
*/
function validatePattern(updatedPattern) {
  var isValid = true;
  var fps = updatedPattern.Pattern_FP;
  for( var i = 0 ; i < fps.length ; i++){
    var fp = fps[i];
    var dgdms = fp.Pattern_DGDM;
    if( dgdms.length < 1 ){
      isValid = false;
      continue;
    }
    var nMovements = 0;
    for( var j = 0 ; j < dgdms.length ; j++){
      var dgdm = dgdms[j];
      nMovements += dgdms
    }
  }
  updatedPattern.isAValidPattern = isValid;
}

function formatDate(d) {
  var date = new Date(d);
  var year = date.getFullYear();
  var month = date.getMonth() + 1;
  if (month < 10) month = "0" + month;
  var day = date.getDate();
  if (day < 10) day = "0" + day;
  var sep = "-";
  return year + sep + month + sep + day;
}

/* Test pattern, until we can work with a pattern coming from database */
var testPattern = {
  Pattern_Name: "CRUDL-3",
  Pattern_Desc: "Crud+List w/ 3 variables",
  User_ID: "STrudel",
  isAValidPattern: true,
  dateCreated: new Date(),

  Pattern_FP: [{
    Pattern_ID: "111",
    Pattern_FP_ID: "Create/modify a <DG1>",
    Pattern_DGDM: [{
      Pattern_FP_ID: "1111",
      Pattern_DG: "<DG1>",
      Pattern_DM_E: 1,
      Pattern_DM_X: 1,
      Pattern_DM_R: 1,
      Pattern_DM_W: 1,
    }, {
      Pattern_FP_ID: "1222",
      Pattern_DG: "<DG2>",
      Pattern_DM_E: 0,
      Pattern_DM_X: 0,
      Pattern_DM_R: 1,
      Pattern_DM_W: 1,
    }, {
      Pattern_FP_ID: "1333",
      Pattern_DG: "<DG3>",
      Pattern_DM_E: 0,
      Pattern_DM_X: 0,
      Pattern_DM_R: 1,
      Pattern_DM_W: 1,
    }]
  }, {
    Pattern_ID: "222",
    Pattern_FP_ID: "Show details of <DG1>",
    Pattern_DGDM: [{
      Pattern_FP_ID: "2111",
      Pattern_DG: "<DG1>",
      Pattern_DM_E: 1,
      Pattern_DM_X: 1,
      Pattern_DM_R: 1,
      Pattern_DM_W: 0,
    }, {
      Pattern_FP_ID: "2222",
      Pattern_DG: "<DG2>",
      Pattern_DM_E: 0,
      Pattern_DM_X: 1,
      Pattern_DM_R: 1,
      Pattern_DM_W: 0,
    }, {
      Pattern_FP_ID: "2333",
      Pattern_DG: "<DG3>",
      Pattern_DM_E: 0,
      Pattern_DM_X: 1,
      Pattern_DM_R: 1,
      Pattern_DM_W: 0,
    }]
  }],
}
