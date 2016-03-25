function getSheetData(workbook) {
    var result = new Array();
    var sheet_name_list = workbook.SheetNames;
    sheet_name_list.forEach(function(y) { /* iterate through sheets */
        var worksheet = workbook.Sheets[y];

        var sheet = new Object();
        sheet.name = y;
        sheet.lines = {};

        var currentLineNumber = -1;
        /* iterate through cells */
        for (z in worksheet) {
            /* all keys that do not begin with "!" correspond to cell addresses */
            if (z[0] === '!') continue;
            var col = z.match(/^[A-Z]+/)[0];
            var ln = parseInt(z.match(/[1-9][0-9]*$/));
            var value = worksheet[z].v;

            if (currentLineNumber != ln) {
                currentLineNumber = ln;
                sheet.lines[ln] = {};
            }
            sheet.lines[ln][col] = value;
            //console.log("line: %i col: %s val: %s", ln, col, value);
        }
        result.push(sheet);
    });

    return result;
}

function getColumnNumber(column) {
    var columnNumber = 0;
    for (i = 0; i < column.length; i++) {
        columnNumber *= 26;
        columnNumber += column.charCodeAt(i) - 65 + 1;
    }
    return columnNumber;
}