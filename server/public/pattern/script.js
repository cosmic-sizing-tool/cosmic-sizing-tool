"use strict"

/*
 * Data structures
 */

function DataTypeSize( string ) {
    //var count_e = string.
    return [ 0,0,0,0,0 ];
}

function Line( data ) {
    if ( data === undefined )
        data = {};
     var line = {
        data_group: ko.observable( data[ "x" ] || "123group" ),
        data_type: ko.observable( data[ "x" ] || "123EXR" )
    }
    return line;
}

function Process( data ) {
    if ( data === undefined )
        data = {};
    var process = {
        name: ko.observable( data[ "x" ] || "123name" ),
        lines: ko.observableArray( data[ "x" ] || [ new Line() ] )
    }
    return process;
}

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

function PatternVM() {
    var self = this;
    
    /*
     *  Members
     */
    self.pattern = ko.observable();

    /*
     *  AJAX
     */

    self.getPattern = function( id ) {
        if ( id > 0 && false ) {
            console.log( "getPattern " + id );
            $.getJSON( "/api/pattern/" + id, function( pattern ) {
                self.pattern( new Pattern( pattern ) );
            }); 
        } else {
            self.pattern( new Pattern() );
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

pattern_vm.getPattern( pattern_id );
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

