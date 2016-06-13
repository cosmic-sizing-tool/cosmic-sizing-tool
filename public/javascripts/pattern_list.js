/* Test pattern, until we can work with a pattern coming from database */
function Pattern(id, name, desc, user, isValid, patternFps, date) {
  this.id = id;
  this.name = name;
  this.desc = desc;
  this.user = user;
  this.isValid = isValid;
  this.patternFps = patternFps;
  this.date = date;
}

function PatternFp(id, patternId, name, dataGroups) {
  this.id = id;
  this.patternId = patternId;
  this.name = name;
  this.dataGroups = dataGroups;
}

function PatternDg(id, fpId, name, dmE, dmX, dmR, dmW) {
  this.id = id;
  this.fpId = fpId;
  this.name = name;
  this.dmE = dmE;
  this.dmX = dmX;
  this.dmR = dmR;
  this.dmW = dmW;
}

var dataGroup1 = new PatternDg("DataGroup1", null, null, 1, 1, 1, 1);
var dataGroup2 = new PatternDg("DataGroup2", null, null, 0, 0, 1, 1);
var dataGroup3 = new PatternDg("DataGroup2", null, null, 0, 0, 1, 1);

var functionalProcess1 = new PatternFp(null, null, "Create/modify a DataGroup1", [dataGroup1, dataGroup2, dataGroup3]);
var functionalProcess2 = new PatternFp(null, null, "Create/modify a DataGroup1", [dataGroup1, dataGroup2]);
var functionalProcess3 = new PatternFp(null, null, "Create/modify a DataGroup1", [dataGroup1]);
var functionalProcess4 = new PatternFp(null, null, "Create/modify a DataGroup1", [dataGroup1]);


var patterns = [];
patterns.push(new Pattern(0, "CRUDL-3", "Crud+List w/ 3 variables", "STrudel", 1, [functionalProcess1, functionalProcess2, functionalProcess3, functionalProcess4], "2016-06-11"));
patterns.push(new Pattern(1, "CRUDL-4", "Crud+List w/ 4 variables", "User1", 1, [functionalProcess1, functionalProcess2], "2016-06-10"));
patterns.push(new Pattern(2, "CRUDL-5", "Crud+List w/ 5 variables", "User2", 0, [functionalProcess1, functionalProcess2], "2016-06-08"));
patterns.push(new Pattern(3, "CRUDL-6", "Crud+List w/ 6 variables", "STrudel", 1, [functionalProcess1, functionalProcess2], "2016-06-08"));
patterns.push(new Pattern(4, "CRUDL-5", "Crud+List w/ 5 variables", "User3", 1, [functionalProcess1], "2016-06-03"));
patterns.push(new Pattern(5, "CRUDL-4", "Crud+List w/ 4 variables", "User4", 0, [functionalProcess1], "2016-06-03"));
patterns.push(new Pattern(6, "CRUDL-5", "Crud+List w/ 5 variables", "STrudel", 0, [functionalProcess1, functionalProcess2, functionalProcess3], "2016-06-02"));
patterns.push(new Pattern(7, "CRUDL-4", "Crud+List w/ 4 variables", "User2", 1, [functionalProcess1, functionalProcess2, functionalProcess3], "2016-06-01"));

function initPage() {
  $('#btnNewPattern').click(function() {
    window.location = "modify";
  });

  $('#btnDeleteSelected').click(function() {
    deleteSelected();
  });

  initTable();
}

function initTable() {
  createTable();

  $("#patternTable>tbody").selectable({
    cancel: 'a',
    selecting: function(e, ui) { // on select
      var curr = $(ui.selecting.tagName, e.target).index(ui.selecting); // get selecting item index
      if (e.shiftKey && prev > -1) { // if shift key was pressed and there is previous - select them all
        $(ui.selecting.tagName, e.target).slice(Math.min(prev, curr), 1 + Math.max(prev, curr)).addClass('ui-selected');
        prev = -1; // and reset prev
      } else {
        prev = curr; // othervise just save prev
      }
    }
  });
}

function createTable() {
  var trHtml;
  var sizeFp;
  for (var i = 0; i < patterns.length; i++) {
    var pattern = patterns[i];
    sizeFp = 0;

    trHtml = "<tr class='" + (i % 2 === 0 ? "" : "alt ") + (pattern.isValid ? "" : "notValid") + "'>";
    trHtml += "<td style='display:none;'>" + pattern.id + "</td>";
    trHtml += "<td><a href='modify?patternId=" + pattern.id + "'>" + pattern.name + "</a></td>";
    trHtml += "<td>" + pattern.desc + "</td>";
    trHtml += "<td>" + pattern.patternFps.length + "</td>";


    for (var ii = 0; ii < pattern.patternFps.length; ii++) {
      sizeFp += getFpSize(pattern.patternFps[ii].dataGroups);
    }

    trHtml += "<td>" + sizeFp + "</td>";
    trHtml += "<td>" + pattern.date + "</td>";
    trHtml += "</tr>";
    $("#patternTable tbody").append(trHtml);
  }
}

function getFpSize(dataGroups) {
  var size = 0;
  for (var i = 0; i < dataGroups.length; i++) {
    var dataGroup = dataGroups[i];
    if (dataGroup.dmE) size++;
    if (dataGroup.dmX) size++;
    if (dataGroup.dmR) size++;
    if (dataGroup.dmW) size++;
  }

  return size;
}

function deleteSelected() {
  var patternIds = []
  $(".ui-selected").each(function(index, elem) {
    patternIds.push(elem.childNodes[0].textContent);
  });

  // Call delete avec le tableau des ID
}
