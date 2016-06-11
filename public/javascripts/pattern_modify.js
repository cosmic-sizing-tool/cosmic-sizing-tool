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
    table.append(generatePatternRow(fp));
  }
}

function generatePatternRow(fp) {
  return $("<tr/>").append(
    firstPatternColumn(fp),
    secondPatternColumn(fp),
    thirdPatternColumn(fp),
    fourthPatternColumn(fp)
  );
}

function firstPatternColumn(fp) {
  var cell = $("<td/>");
  var input = $("<input type='text'>");
  input.val(fp.Pattern_FP_ID);
  input.attr("data-id", fp.Pattern_ID)
  cell.append(input);
  return cell;
}

function secondPatternColumn(fp) {
  var cell = $("<td/>");
  for (var i = 0; i < fp.Pattern_DGDM.length; i++) {
    var input = $("<input type='text'>");
    input.val(fp.Pattern_DGDM[i].Pattern_DG);
    cell.append(input, "<br/>");
  }
  return cell;
}

function thirdPatternColumn(fp) {
  var cell = $("<td/>");
  for (var i = 0; i < fp.Pattern_DGDM.length; i++) {
    var dgdm = fp.Pattern_DGDM[i];
    var input = $("<input type='text'>");
    var val = "";
    if (dgdm.Pattern_DM_E) val += "E";
    if (dgdm.Pattern_DM_X) val += "X";
    if (dgdm.Pattern_DM_R) val += "R";
    if (dgdm.Pattern_DM_W) val += "W";
    input.val(val);
    cell.append(input, "<br/>");
  }
  return cell;
}

function fourthPatternColumn(fp) {
  var cell = $("<td/>");
  var span = $("<span/>");
  var n = 0;
  for (var i = 0; i < fp.Pattern_DGDM.length; i++) {
    var dgdm = fp.Pattern_DGDM[i];
    if (dgdm.Pattern_DM_E) n++;
    if (dgdm.Pattern_DM_X) n++;
    if (dgdm.Pattern_DM_R) n++;
    if (dgdm.Pattern_DM_W) n++;
  }
  span.text(n);
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

function bindInputs() {
  $("#patternModify input").unbind().on("change", patternChanged);
}

function patternChanged() {
  var updatedPattern = collectPattern();
  displayPattern(updatedPattern);
  validatePattern(updatedPattern);
}

/*
var testPattern = {
  Pattern_Name: "CRUDL-3",
  Pattern_Desc: "Crud+List w/ 3 variables",
  User_ID: "STrudel",
  isAValidPattern: true,
  dateCreated: new Date(),

  Pattern_FP: [{
    Pattern_ID: "111",
    Pattern_FP_ID: "Create/modify a <DataGroup1>",
    Pattern_DGDM: [{
      Pattern_FP_ID: "1111",
      Pattern_DG: "<DataGroup1>",
      Pattern_DM_E: 1,
      Pattern_DM_X: 1,
      Pattern_DM_R: 1,
      Pattern_DM_W: 1,
    }]
  }],
}
*/
function collectPattern() {
  var updatedPattern = {};
  updatedPattern.Pattern_Name = getPatternName();
  updatedPattern.Pattern_Desc = getPatternDescription();
  updatedPattern.User_ID = getPatternUser();
  updatedPattern.dateCreated = getPatternDateCreated();
  updatedPattern.Pattern_FP = [];

  var rows = $("#patternTable tbody tr");
  for (var i = 0; i < rows.length; i++) {
    var row = $(rows[i]);
    var fp = {};
    var cell1 = row.find("td:eq(0)");
    var cell1Input = cell1.find("input");
    var cell2 = row.find("td:eq(1)");
    var cell2Inputs = cell2.find("input");
    var cell3 = row.find("td:eq(2)");
    var cell3Inputs = cell2.find("input");
    var cell4 = row.find("td:eq(3)");
    fp.Pattern_ID = cell1Input.attr("data-id");
    fp.Pattern_FP_ID = cell1Input.val();
    fp.Pattern_DGDM = [];
    for (var j = 0; j < cell2Inputs.length; j++) {
      var cell2Input = $(cell2Inputs[j]);
      var cell3Input = $(cell3Inputs[j]);
      var dgdm = {};
      dgdm.Pattern_FP_ID = fp.Pattern_FP_ID;
      dgdm.Pattern_DG = cell2Input.val();
      dgdm.Pattern_DM_E = cell3Input.val().indexOf("E");
      dgdm.Pattern_DM_X = cell3Input.val().indexOf("X");
      dgdm.Pattern_DM_R = cell3Input.val().indexOf("R");
      dgdm.Pattern_DM_W = cell3Input.val().indexOf("W");
      fp.Pattern_DGDM.push(dgdm);
    }
    updatedPattern.Pattern_FP.push(fp);
  }
  console.log("updatedPattern : ");
  console.log(updatedPattern);

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
  console.log("updatedPattern : ", updatedPattern);
}

/*
Validation rules
  - A FP must have at least 1 DG and at least 1 DM
  - A FP is unique in the pattern (must not be identical to any other)
*/
function validatePattern(updatedPattern) {
  var isValid = true;
  /* TODO: Validate the pattern from the data contained
    in the pattern object (maybe highlight in red what's invalid) */
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
    Pattern_FP_ID: "Create/modify a <DataGroup1>",
    Pattern_DGDM: [{
      Pattern_FP_ID: "1111",
      Pattern_DG: "<DataGroup1>",
      Pattern_DM_E: 1,
      Pattern_DM_X: 1,
      Pattern_DM_R: 1,
      Pattern_DM_W: 1,
    }, {
      Pattern_FP_ID: "1222",
      Pattern_DG: "<DataGroup2>",
      Pattern_DM_E: 0,
      Pattern_DM_X: 0,
      Pattern_DM_R: 1,
      Pattern_DM_W: 1,
    }, {
      Pattern_FP_ID: "1333",
      Pattern_DG: "<DataGroup3>",
      Pattern_DM_E: 0,
      Pattern_DM_X: 0,
      Pattern_DM_R: 1,
      Pattern_DM_W: 1,
    }]
  }, {
    Pattern_ID: "222",
    Pattern_FP_ID: "Show details of <DataGroup1>",
    Pattern_DGDM: [{
      Pattern_FP_ID: "2111",
      Pattern_DG: "<DataGroup1>",
      Pattern_DM_E: 1,
      Pattern_DM_X: 1,
      Pattern_DM_R: 1,
      Pattern_DM_W: 0,
    }, {
      Pattern_FP_ID: "2222",
      Pattern_DG: "<DataGroup2>",
      Pattern_DM_E: 0,
      Pattern_DM_X: 1,
      Pattern_DM_R: 1,
      Pattern_DM_W: 0,
    }, {
      Pattern_FP_ID: "2333",
      Pattern_DG: "<DataGroup3>",
      Pattern_DM_E: 0,
      Pattern_DM_X: 1,
      Pattern_DM_R: 1,
      Pattern_DM_W: 0,
    }]
  }],
}
