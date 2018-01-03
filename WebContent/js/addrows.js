function changeTable(){
	var type = document.getElementById("type").value;
	

	if(type == "BOOK"){
		document.getElementById("br1").style.display = "table-row";
	    document.getElementById("br2").style.display = "table-row";
	    document.getElementById("br3").style.display = "table-row";
	    document.getElementById("pr1").style.display = 'none';
	    document.getElementById("pr2").style.display = 'none';
	    document.getElementById("pr3").style.display = 'none';
	    document.getElementById("jr1").style.display = 'none';
	    document.getElementById("jr2").style.display = 'none';
	    document.getElementById("jr3").style.display = 'none';
	    document.getElementById("jr4").style.display = 'none';
	    document.getElementById("jr5").style.display = 'none';
	}
	if(type == "JOURNAL_VOLUME"){
		document.getElementById("jr1").style.display = "table-row";
	    document.getElementById("jr2").style.display = "table-row";
	    document.getElementById("jr3").style.display = "table-row";
	    document.getElementById("jr4").style.display = "table-row";
	    document.getElementById("jr5").style.display = 'none';
	    document.getElementById("br1").style.display = 'none';
	    document.getElementById("br2").style.display = 'none';
	    document.getElementById("br3").style.display = 'none';
	    document.getElementById("pr1").style.display = 'none';
	    document.getElementById("pr2").style.display = 'none';
	    document.getElementById("pr3").style.display = 'none';
	}
	if(type == "PROCEEDINGS"){
		document.getElementById("pr1").style.display = "table-row";
	    document.getElementById("pr2").style.display = "table-row";
	    document.getElementById("pr3").style.display = "table-row";
	    document.getElementById("br1").style.display = 'none';
	    document.getElementById("br2").style.display = 'none';
	    document.getElementById("br3").style.display = 'none';
	    document.getElementById("jr1").style.display = 'none';
	    document.getElementById("jr2").style.display = 'none';
	    document.getElementById("jr3").style.display = 'none';
	    document.getElementById("jr4").style.display = 'none';
	    document.getElementById("jr5").style.display = 'none';
	}
}
function myfun(){
alert("TRY me" );
}