var chronoSeconds = 0;
var isRunning = 0;
var isStarted = 0;
var chronoIntervalHandle;

$(document).ready(initializeClock);
$(window).unload(stopChrono);

function disableSection(id){
    $("#" + id + " :input").prop("disabled", true);
}

function enableSection(id){
    $("#" + id + " :input").prop("disabled", false);
}

function convertSeconds(seconds) {
    var hours = parseInt(seconds / 3600);
    var remainder = seconds % 3600;
    var minutes = parseInt(remainder / 60);
    remainder = remainder % 60;
    var seconds = remainder;

    return {
        hours: hours,
        minutes: minutes,
        seconds: seconds
    };
}

function initializeClock(idClock, idProject, idOrg, number){
    var endpoint = "/organization/" + idOrg + "/project/" + idProject + "/timer";
    var chronoSeconds = 0;
    //     $.ajax({
    //         url: endpoint,
    //         data: "",
    //         success: function(data) {
    //             chronoSeconds = data;
    //         },
    //         dataType: "json"}
    //     );

    chronoSeconds = 3668;
    setChronoTime(chronoSeconds);
    startChrono();
}

function setChronoTime(seconds) {
    chronoSeconds = seconds;
    var chronoTime = convertSeconds(seconds);

    $("#idHour").text(("0" + chronoTime.hours).slice(-2));
    $("#idMin").text(("0" + chronoTime.minutes).slice(-2));
    $("#idSec").text(("0" + chronoTime.seconds).slice(-2));
}

function startChrono() {
    chronoIntervalHandle = setInterval(updateChronoTime, 1000);
    console.log(chronoIntervalHandle);
}

function updateChronoTime() {
    setChronoTime(++chronoSeconds);
}

function stopChrono() {
    clearInterval(chronoIntervalHandle);
}

function increment(){
    if (isRunning === 0 || isRunning === -1){
        return;
    }
    second = second + 1;

    if (second > 60){
        second = 0;
        minute = minute + 1;
    }

    if (minute > 60){
        minute = 0;
        hour = hour + 1;
    }

    var clock = document.getElementById('clockdiv');

    var secondsSpan = clock.querySelector('.seconds');
    var hoursSpan = clock.querySelector('.hours');
    var minutesSpan = clock.querySelector('.minutes');

    hoursSpan.innerHTML = hour;
    minutesSpan.innerHTML = minute;
}

function togglePlayPause() {
    if ($("#idBtnStart").is(':visible')) {
        // Starting timer
        initializeClock();
        // TODO: Replace inputTest by the form id
        enableSection("inputTest");
        $("#idBtnStart").hide();
        $("#idBtnPause").show();
        $("#clockCover").hide();
    } else {
        // Pausing timer
        stopChrono();
        // TODO: Replace inputTest by the form id
        disableSection("inputTest");
        $("#idBtnStart").show();
        $("#idBtnPause").hide();
        $("#clockCover").show();
    }
}

function toggleSecondes() {
    if ($(".secondes").is(':visible')) {
        $(".secondes").hide();
        $("#minutesDiv").attr('class','timeDiv rightDiv');
        $("#secondesDiv").attr('class','timeDiv secondes');
    } else {
        $(".secondes").css('display','inline-block');
        $("#minutesDiv").attr('class','timeDiv');
        $("#secondesDiv").attr('class','timeDiv rightDiv secondes');
    }
}
