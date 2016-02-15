function renderUser(data, textStatus, jqXHR){
	$('#user_id').val(data.id);
	$('#user_alias').val(data.alias);
	$('#user_name').val(data.name);
	$('#user_email').val(data.email);
	$('#user_password').val(data.password);
	//$('#user_alias').val(data.alias);

}
function renderPassword(data, textStatus, jqXHR){
	



}


function getUser(rechercheid){
	$.ajax({
		url: '/user/' + rechercheid,
		type: 'GET',
		dataType: 'json',

		success: function(data, textStatus, jqXHR){
			renderUser(data, textStatus, jqXHR);
		},
		error:function(){
			alert('ERROR');
		}
	});
}

function putPassword(rechercheid, passobj){
	$.ajax({
		url: '/user/' + rechercheid +  '/password',
		type: 'PUT',
		dataType: 'json',
    data : passobj,
		success: function(data, textStatus, jqXHR){
			renderPassword(data, textStatus, jqXHR);
		},
		error:function(){
			alert('ERROR');
		}
	});
}

function delQuerry(rechercheid){
	$.ajax({
		//beforeSend: function(xhr) { xhr.setRequestHeader('X-HTTP-Method-Override', 'DELETE');},
		//url: '/recherche/delete/'+rechercheid,
		url: '/recherches/'+rechercheid,
		type: 'DELETE',
		success: function(data, textStatus, jqXHR){
			renderDelQuerry()
		},
		dataType: 'json'
	});

}


function addQuerry(recherche){
	$.ajax({
		//beforeSend: function(xhr) {xhr.setRequestHeader('X-HTTP-Method-Override', 'PUT');},
		//url: '/recherche/create/',
		url: '/recherches',
		data: {c1 : recherche},
		type: 'POST',
		success: function(data, textStatus, jqXHR){

			RenderANewQuery(data, textStatus, jqXHR);
			renderAQuerry(data, textStatus, jqXHR);
			renderSats(data);
		},
		dataType: 'json'
	});
}

//VIEW

function renderSats(data){
	var pre = JSON.parse(data.resultat);
	$('#max_score').empty();
	$('#max_score').text(pre.hits.max_score);
	$('#total').empty();
	$('#total').text(pre.hits.total);
	if(pre.hits.total == 0){
		$('#result').empty();
		$('#result').append('no result');
	}
}




$.makeTable = function (mydata) {
	var table = $('<table>');


	var tblHeader = "<thead><tr>";
	for (var k in mydata[0]) tblHeader += "<th>" + k + "</th>";
	tblHeader += "</tr></thead>";

	$(tblHeader).appendTo(table);

	var tbody =$('<tbody>');

	$.each(mydata, function (index, value) {
		var TableRow = "<tr>";
		$.each(value, function (key, val) {
			TableRow += "<td>" + val + "</td>";
		});
		TableRow += "</tr>";
		$(tbody).append(TableRow);
	});

	$(table).append(tbody);
	return ($(table));
};


var myID  =  1234;
var myEmail = "Jean-paul@hotmail.com";
var body = $('body')[0];
getUser(1234);

$('#_save_btn').click(function() {

  var myObject = new Object();
  
    

	myObject.current_pass  = $('#_curret_pass').val();
	myObject.new_password1 = $('#_new_pass1').val();
	myObject.new_password2 = $('#_new_pass2').val();
  putPassword(myEmail,myObject);

});
