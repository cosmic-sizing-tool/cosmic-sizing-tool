"use strict"

/*
 * Data structures
 */

function DataTypeSize( string ) {
    //var count_e = string.
    return [ 0,0,0,0,0 ];
}

function Line( data ) {
    var line = {
        data_group: data[ "x" ] || "",
        data_type: data[ "x" ] || ""
    }
    return line;
}

function Process( data ) {
    var process = {
        name: data[ "x" ] || "",
        lines: ko.observableArray( data[ "x" ] || [ new Line() ] )
    }
    return process;
}

function Pattern( data ) {
    var pattern = {
        id: data[ "id" ] || "",
        desc_short: data[ "x" ] || "",
        desc_long: data[ "x" ] || "",
        process: ko.observableArray( data[ "x" ] || [ new Process() ] )
    }
    return pattern;
}   

function PatterVM() {
    var self = this;
    
    /*
     *  Members
     */
    self.pattern = ko.observable( new Pattern() );

    /*
     *  AJAX
     */

    self.getPattern = function( id ) {
        if ( id > 0 ) {
            $.getJSON( "/api/pattern/" + id, function( pattern ) {
                self.pattern = new Pattern( pattern );
            });
        }
    };

    self.save = function() {

    }
}


/*
 * Entry point
 */

var pattern_id = window.location.pathname.match(/\d+/)[0];
var pattern_vm = new PatternVM();

pattern_vm.getPattern( pattern_i );
ko.applyBindings( pattern_vm );


/*
 * Shortcuts
 */

var is_ctrl = false;

document.onkeyup = function( e ) {
    if ( e.ctrlKey ) 
        is_ctrl = false;
}

document.onkeydown = function( e ) {
    if ( e.key === 'Tab' )
        pattern.tabNext();
    else if ( e.key === 'Control' )
        is_ctrl = true;
    else if ( is_ctrl && e.key === 's' )
        pattern_vm.save();
    else if ( is_ctrl && e.key === 'd' )
        pattern_vm.delete();
    else if ( is_ctrl && e.key === 'u' )
        pattern_vm.insertLineUnder();
    else if ( is_ctrl && e.key === 'o' )
        pattern_vm.insertLineOver();
    else
        return;

    // If one of the combinations activate, prevent default behavior
    e.preventDefault();
    return;
}

