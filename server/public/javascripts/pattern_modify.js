$(window).resize(windowResize);

function windowResize() {
  $("#patternModify").css("marginTop", $("nav").height())
}

function displayPatternFp(fp) {
  $("#patternNFp").text(fp);
}

function displayPatternSize(size) {
  $("#patternSize").text(size);
}

function displayPatternSizeE(size) {
  $("#patternSizeE").text(size);
}

function displayPatternSizeX(size) {
  $("#patternSizeX").text(size);
}

function displayPatternSizeR(size) {
  $("#patternSizeR").text(size);
}

function displayPatternSizeW(size) {
  $("#patternSizeW").text(size);
}

function displayPatternDateCreated(date) {
  $("#patternDateCreated").text(date);
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

function bindButton() {
  $("#patternFooter button").on("click", function() {
    savePattern();
  })
}

function displayPattern(pattern) {
  displayPatternFp(pattern.patternNFp);
  displayPatternSize(pattern.patternSize);
  displayPatternSizeE(pattern.patternSizeE);
  displayPatternSizeX(pattern.patternSizeX);
  displayPatternSizeR(pattern.patternSizeR);
  displayPatternSizeW(pattern.patternSizeW);
  displayPatternDateCreated(pattern.dateCreated);
  displayPatternName(pattern.Pattern_Name);
  displayPatternDescription(pattern.Pattern_Desc);
  displayPatternUser(pattern.User_ID);
}

function savePattern() {
  var isAValidPattern = validatePattern();
  var updatedPattern = {};
  $("#patternModify input").each(function() {
    var id = $(this).attr("id");
    var val = $(this).val();
    updatedPattern[id] = val;
  })
  console.log("updatedPattern : ", updatedPattern);
}

function validatePattern() {
  return true;
}



/* HARD-CODED TEST PATTERN */
/* PatternNFp sera calculé dynamiquement à partir du nombre de FP */
/* PatternSize sera calculé dynamiquement à partir du nombre de CFP */
/* PatternSizeE sera calculé dynamiquement à partir du nombre de CFP de type E */
/* PatternSizeX sera calculé dynamiquement à partir du nombre de CFP de type X */
/* PatternSizeR sera calculé dynamiquement à partir du nombre de CFP de type R */
/* PatternSizeW sera calculé dynamiquement à partir du nombre de CFP de type W */
var testPatternObj = {
  patternNFp: 1,
  patternSize: 3,
  patternSizeE: 1,
  patternSizeX: 1,
  patternSizeR: 1,
  patternSizeW: 0,

  Pattern_Name: "CRUDL-3",
  Pattern_Desc: "Crud+List w/ 3 variables",
  User_ID: "STrudel",
  isAValidPattern: true,
  dateCreated: new Date(),

  Pattern_FP: [{
    Pattern_ID: "",
    Patterh_FP_ID: "",
    Patterh_DGDM: [{
      Pattern_FP_ID: "",
      Pattern_DG: "",
      Pattern_DM: 0,
      Pattern_Size: 0,
    }]
  }],
}
