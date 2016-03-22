$ ->
  $.get "/user/current/certifications" (certifications) ->
    $.each certifications, (index, certification) ->
      $('#certifications').append $('<li>').text certification.method + " " + certification.version
