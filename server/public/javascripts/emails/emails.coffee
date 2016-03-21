$ ->
  $.get "/emails/user", (emails) ->
    $.each emails, (index, email) ->
      $('#emails').append $("<li>").text email.addresse