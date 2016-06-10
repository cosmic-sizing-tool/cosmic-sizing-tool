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
  if (size > 0) {
    $("#cfpE").show();
    $("#cfpE .patternValue").text(size);
  }
}

function displayPatternSizeX(size) {
  if (size > 0) {
    $("#cfpX").show();
    $("#cfpX .patternValue").text(size);
  }
}

function displayPatternSizeR(size) {
  if (size > 0) {
    $("#cfpR").show();
    $("#cfpR .patternValue").text(size);
  }
}

function displayPatternSizeW(size) {
  if (size > 0) {
    $("#cfpW").show();
    $("#cfpW .patternValue").text(size);
  }
}

function displayPatternDateCreated(date) {
  $("#patternDateCreated").text(formatDate(date));
}

function displayPatternName(name) {
  $("#Pattern_Name").val(name);
}

function displayPatternDescription(desc) {
  $("#Pattern_Desc").val(desc);
}

function displayPatternUser(user) {
  $("#User_ID").val(user);
}

function displayPatternTable(fps) {
  for (var i = 0; i < fps.length; i++) {
    var fp = fps[i];
    addPatternRow(fp);
  }
}

function addPatternRow(fp) {
  var row = $("<tr/>").append(
    firstPatternColumn(fp),
    secondPatternColumn(fp),
    thirdPatternColumn(fp),
    fourthPatternColumn(fp)
  );
  $("#patternTable").append(row);
}

function firstPatternColumn(fp) {
  var cell = $("<td/>");
  var input = $("<input type='text'>");
  input.val(fp.Pattern_FP_ID);
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

function bindSaveButton() {
  $("#patternSave").on("click", savePattern);
}

function bindInputs() {
  $("#patternModify input").on("change", patternChanged);
}

function patternChanged() {
  collectPattern();
  updateDisplay();
}

function collectPattern() {
  /* TODO: Collect the updated pattern data */
}

function updateDisplay() {
  /* TODO: Dynamically update elements on the page :
  		- #FP in #patternOverview
  		- CFP totals in #patternOverview
  		- CFP total in each #patternTable row
  */
}

function displayPattern(pattern) {
  displayPatternNFp(pattern);
  displayPatternSize(pattern);
  displayPatternDateCreated(pattern.dateCreated);
  displayPatternName(pattern.Pattern_Name);
  displayPatternDescription(pattern.Pattern_Desc);
  displayPatternUser(pattern.User_ID);
  displayPatternTable(pattern.Pattern_FP);
}

function savePattern() {
  collectPattern();
  var isAValidPattern = validatePattern();
  var updatedPattern = {};
  $("#patternModify input").each(function() {
    var id = $(this).attr("id");
    var val = $(this).val();
    updatedPattern[id] = val;
  });
  console.log("updatedPattern : ", updatedPattern);
}

/*
Validation rules
	- A FP must have at least 1 DG and at least 1 DM
	- A FP is unique in the pattern (must not be identical to any other)
*/
function validatePattern() {
  /* TODO: Validate the pattern from the data contained
   	in the pattern object */
  return true;
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
var pattern = {
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
