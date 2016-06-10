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

function displayPatternName(name) {
  $("#patternNameInput").val(name);
}

function displayPatternDescription(desc) {
  $("#patternDescriptionInput").val(desc);
}

function displayPatternUser(user) {
  $("#patternUserInput").val(user);
}

function bindButton() {
  $("#patternFooter button").on("click", function() {
    updatePattern();
  })
}

function updatePattern() {
  if (validatePattern()) {
  	var updatedPattern = {};
    $("input").each(function() {
      var id = $(this).attr("id");
      var val = $(this).val();
      updatedPattern[id] = val;
    })
    console.log("updatedPattern : ");
    console.log(updatedPattern);
  }
}

function validatePattern() {
  return true;
}


/* Test, until there's a database */
var testPatternObj = {
  patternNFp: 1,
  patternSize: 3,
  patternNameInput: "CRUDL-3",
  patternDescriptionInput: "Crud+List w/ 3 variables",
  patternUserInput: "STrudel",
}

function testPattern() {
  displayPatternFp(testPatternObj.patternNFp);
  displayPatternSize(testPatternObj.patternSize);
  displayPatternName(testPatternObj.patternNameInput);
  displayPatternDescription(testPatternObj.patternDescriptionInput);
  displayPatternUser(testPatternObj.patternUserInput);
}
