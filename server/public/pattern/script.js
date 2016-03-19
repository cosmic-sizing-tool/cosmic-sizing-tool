"use strict"

/*
 * Data structures
 */

// Counter for data mouvement (types) letters - 5th value is sum of first 4
function DataTypeSize( string ) {
    return [ 0,0,0,0,0 ];
}

// Lines contain 2 strings
function Line( data ) {
    if ( data === undefined )
        data = {};
     var line = {
        data_group: ko.observable( data[ "x" ] || "123group" ),
        data_type: ko.observable( data[ "x" ] || "123EXR" )
    }
    return line;
}

// Processes have a name and an array of lines
function Process( data ) {
    if ( data === undefined )
        data = {};
    var process = {
        name: ko.observable( data[ "x" ] || "123name" ),
        lines: ko.observableArray( data[ "x" ] || [ new Line() ] )
    }
    return process;
}

// Patterns have an id, a short and long description and an array of processes
function Pattern( data ) {
    if ( data === undefined )
        data = {};
    var pattern = {
        id: ko.observable( data[ "x" ] || "123id" ),
        desc_short: ko.observable( data[ "x" ] || "123desc_short" ),
        desc_long: ko.observable( data[ "x" ] || "123desc_long" ),
        processes: ko.observableArray( data[ "x" ] || [ new Process() ] )
    }
    return pattern;
}   

// View Model used by Knockout JS
function PatternVM() {
    var self = this;
    
    /*
     *  Members
     */
    self.pattern = ko.observable();

    /*
     *  AJAX
     */

    // Fetch pattern from DB (if id == 0 then insert else update)
    self.getPattern = function( id ) {
        if ( id > 0 ) {
            console.log( "getPattern " + id );
            $.getJSON( "/api/pattern/" + id, function( pattern ) {
                self.pattern( new Pattern( pattern ) );
            }); 
        } else {
            self.pattern( new Pattern() );
        }
    };

    // TODO implement functions
    self.save = function() {}
    self.delete = function() {}
    self.tabNext = function() {}
    self.insertTabUnder = function() {}
    self.insertTabOver = function() {}

}


/*
 * Entry point
 */

// Get pattern ID from URL
var pattern_id = window.location.pathname.match(/\d+/)[0];
var pattern_vm = new PatternVM();

// Apply bindings from ViewModel with KnockoutJS
pattern_vm.getPattern( pattern_id );
ko.applyBindings( pattern_vm );


/*
 * Shortcuts
 */

var is_ctrl = false;

// Remember when <ctrl> is pressed
document.onkeyup = function( e ) {
    if ( e.ctrlKey ) 
        is_ctrl = false;
}

document.onkeydown = function( e ) {
    // <tab> gives focus to next input field (+ subtilities)
    if ( e.key === 'Tab' )
        pattern_vm.tabNext();
    // <ctrl> needed for shortcuts
    else if ( e.key === 'Control' )
        is_ctrl = true;
    // <ctrl> + <s> saves pattern
    else if ( is_ctrl && e.key === 's' )
        pattern_vm.save();
    // <ctrl> + <d> deletes pattern
    else if ( is_ctrl && e.key === 'd' )
        pattern_vm.delete();
    // <ctrl> + <u> inserts line under focus
    else if ( is_ctrl && e.key === 'u' )
        pattern_vm.insertLineUnder();
    // <ctrl> + <o> inserts line over focus
    else if ( is_ctrl && e.key === 'o' )
        pattern_vm.insertLineOver();
    else
        return;

    // If one of the combinations activate, prevent default behavior
    e.preventDefault();
    return;
}

