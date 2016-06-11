


$(document).ready(function() {

	
	$('#example3').dataTable();
	var body = $('body')[0];
	var queryContainer = $('#list_query');
	var template1;

	$('#search_btn').click(function(e){
		var zatext = $('#search_field').val();
		addQuerry(zatext);
	});

	$.get('/recherches/index.json',function(data){
		var queryInitailList = data;
		jQuery.get('queryTemplate.html',function(data){
			htTempl1 = $.parseHTML(data);
			template1 = data;
			queryInitailList.forEach(function(value,index,array){
				var temp = $.parseHTML(data);
				$(temp).find('.queryId').text('id: ' + JSON.stringify(value.id));
				$(temp).find('.querySring').text(JSON.stringify(value.criteres));
				$(temp).find('.querySring').click(function() {
					getQuerry(value.id);
				});

				//registercallback
				$(temp).find('input').click(function() {
					var valID = $(this).parent().find('.queryId').text().match(/(\d+)/g);
					delQuerry(valID);
					$(this).parent().parent().remove();
				});
				$('#list_query').prepend(temp);
			});
		});
	});

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
	
	function getQuerry(rechercheid){  
		$.ajax({			
			url: '/recherches/' + rechercheid,
			//url: '/recherche/' + rechercheid,
			type: 'GET',
			dataType: 'json',
			
			success: function(data, textStatus, jqXHR){
				renderAQuerry(data, textStatus, jqXHR);
				renderSats(data);	
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

	function renderAQuerry(data, textStatus, jqXHR){
		var pre = JSON.parse(data.resultat);
		var mydata = [];
		
		$('#result').empty();
		
		
		pre.hits.hits.forEach(function(value,index,array){
			mydata.push(value._source);
		});
		
		if(mydata.length == 0){
			
			$('#result').append('<div> no result' + '</div>');
			
		}else{
		
		
		
		var table = $.makeTable(mydata);
		
		$(table).attr('cellspacing', '0');
		$(table).attr('width', '100%');
		
		$('#result').append('<div class="container">' + '</div>');
		$(table).addClass("display");
		$(table).attr('id', 'example');
		$('#result').find("div").append(table);
		$('#example').dataTable();
		
		}
	}


	function renderAQuerry2(data, textStatus, jqXHR){
		var pre = JSON.parse(data.resultat);
		$('#result').empty();	
		pre.hits.hits.forEach(function(value,index,array){
			$('#result').append('<div class="colapse">' + '<pre>' + JSON.stringify(value, null, 2) + '</pre>>' + '</div>');
		});
	}



	function renderDelQuerry(){
		$('#result').empty();	
		$('#result').append('<div class="colapse">' + '<pre>' + 'deleted' + '</pre>>' + '</div>');
	}



	function RenderANewQuery(data, textStatus, jqXHR){
		var temp = $.parseHTML(template1);
		$(temp).find('.querySring').text(data.criteres);
		$('#list_query').prepend(temp);
		var pre = JSON.parse(data.resultat);
		$(temp).find('.queryId').text('id: ' + JSON.stringify(data.id));
		$(temp).find('.querySring').text(data.criteres);
		$(temp).find('.querySring').click(function() {
			getQuerry(data.id);
		});

		//registercallback  
		$(temp).find('input').click(function() {
			delQuerry(data.id);
			$(this).parent().parent().remove();
		});
	}




	//http://stackoverflow.com/questions/4810841/how-can-i-pretty-print-json-using-javascript
	function syntaxHighlight(json) {
		if (typeof json != 'string') {
			json = JSON.stringify(json, undefined, 2);
		}
		json = json.replace(/&/g, '&amp;').replace(/</g, '&lt;').replace(/>/g, '&gt;');
		return json.replace(/("(\\u[a-zA-Z0-9]{4}|\\[^u]|[^\\"])*"(\s*:)?|\b(true|false|null)\b|-?\d+(?:\.\d*)?(?:[eE][+\-]?\d+)?)/g, function (match) {
			var cls = 'number';
			if (/^"/.test(match)) {
				if (/:$/.test(match)) {
					cls = 'key';
				} else {
					cls = 'string';
				}
			} else if (/true|false/.test(match)) {
				cls = 'boolean';
			} else if (/null/.test(match)) {
				cls = 'null';
			}
			return '<span class="' + cls + '">' + match + '</span>';
		});

	}


}); 




























