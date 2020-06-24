 $(document).ready(function(){
    $('.tabs').tabs();
    $('select').formSelect();
  });
  
  
$("select.typeComp").change(function(){
    var selected = $(this).children("option:selected").text();
    $("input").attr("placeholder", selected);
    
    let posting = $.post( "/administrator/getComp", {typeComp: selected});
    
    posting.done(function(data) {
	  	var convertedData =  JSON.parse(data);
	  	console.log(convertedData);  	
	  		
		$("#typeComponentDiv").empty();
		
	  	
	  	for (i = 0; i < convertedData['num']; i++) {
			$("#typeComponentDiv").append('<input type="text" placeholder="" name="routename" id="' + i + '"/>');
			$("#typeComponentDiv").append('<label id="label' + i + '" for="' + i + '">' + convertedData[i] +  '</label>');
		}
		
		$("#typeComponentDiv").append('<input type="text" placeholder="" name="routename" id="Name"/>');
		$("#typeComponentDiv").append('<label id="label' + i + '" for="Name">' + convertedData[i] +  '</label>');
		
		$("#typeComponentDiv").append('<input type="text" placeholder="" name="routename" id="Price"/>');
		$("#typeComponentDiv").append('<label id="label' + i + '" for="Price">' + convertedData[i] +  '</label>');
		
		$("#typeComponentDiv").append('<button onClick="saveComponent(' + convertedData['num'] + ')" id="submit" class="btn waves-effect waves-light" type="submit" name="action">Submit</button>');
    });
});


function saveComponent(num) {
	var value;
	var name;
	var json;
	json = '{"';
	
	for (i = 0; i < num; i++) {
		value = $("input#" + i).val();
		name = $("#label" + i).text();
		json = json + name + '": "' + value ;
		
		json = json + '", "';
	}
	
	json = json + ' "price": "' + $("#price").val() + '",';
	json = json + ' "name": "' + $("#name").val() + '"';
	
	json = json + '"}';
	
	console.log(json);
	
	let posting = $.post("administrator/addComp", json);
	alert("asdasdasd");
}