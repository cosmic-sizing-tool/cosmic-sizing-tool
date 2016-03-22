$ ->
  $.get "/email/current/emails" (emails) ->
    $.each emails, (index, email) ->
      $('#certifications').append $('<li>').text email.addresse
