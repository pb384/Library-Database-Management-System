google.charts.load("current", {packages:['corechart']});
	
    google.charts.setOnLoadCallback(drawChart);
    function drawChart() {
    	var _title = new Array();
    	var _borr = new Array();
    	
    	
    	var title = document.getElementById("title").value; 
    	title = title.replace("[", "");
    	title = title.replace("]", "");
    	var strings = title.split(",");
    	
    	for (var i=0; i<title.length; i++)
    		_title.push(strings[i] );
    	
    	var borr = document.getElementById("borr").value;
    	borr = borr.replace("[", "");
    	borr = borr.replace("]", "");
    	var strings1 = borr.split(",");
    	for (var i=0; i<borr.length; i++)
    		_borr.push(parseInt(strings1[i]) );
    	
      var data = google.visualization.arrayToDataTable([
        ["Title", "Count", { role: "style" } ],
        [_title[0], _borr[0], "#BFC9CA"],
        [_title[1], _borr[1], "#BFC9CA"],
        [_title[2], _borr[2], "#BFC9CA"],
        [_title[3], _borr[3], "#BFC9CA"],
        [_title[4],	_borr[4], "#BFC9CA"],
        [_title[5], _borr[5], "#BFC9CA"],
        [_title[6], _borr[6], "#BFC9CA"],
        [_title[7], _borr[7], "#BFC9CA"],
        [_title[8], _borr[8], "#BFC9CA"],
        [_title[9], _borr[9], "#BFC9CA"]
      ]);

      var view = new google.visualization.DataView(data);
      view.setColumns([0, 1,
                       { calc: "stringify",
                         sourceColumn: 1,
                         type: "string",
                         role: "annotation" },
                       2]);
	var options = {
        title: "Popular books of the year",

        bar: {groupWidth: "95%"},
        legend: { position: "none" },
      };
      var chart = new google.visualization.ColumnChart(document.getElementById("columnchart_values"));
      chart.draw(view, options);
      var st = document.getElementById("st").value; 
      if(st != null && st != ""){
    	  drawChart1();
      }
      
  }
    function drawChart1() {
    	
    	var _title = new Array();
    	var _borr = new Array();
    	
    	var branch = document.getElementById("st").value;
    	var title = document.getElementById("name").value; 
    	title = title.replace("[", "");
    	title = title.replace("]", "");
    	var strings = title.split(",");
    	
    	for (var i=0; i<title.length; i++)
    		_title.push(strings[i] );
    	
    	var borr = document.getElementById("cnt").value;
    	borr = borr.replace("[", "");
    	borr = borr.replace("]", "");
    	var strings1 = borr.split(",");
    	for (var i=0; i<borr.length; i++)
    		_borr.push(parseInt(strings1[i]) );
    	
        var data = google.visualization.arrayToDataTable([
			["Title", "Count", { role: "style" } ],
			[_title[0], _borr[0], "gold"],
			[_title[1], _borr[1], "gold"],
			[_title[2], _borr[2], "gold"],
			[_title[3], _borr[3], "gold"],
			[_title[4],	_borr[4], "gold"],
			[_title[5], _borr[5], "gold"],
			[_title[6], _borr[6], "gold"],
			[_title[7], _borr[7], "gold"],
			[_title[8], _borr[8], "gold"],
			[_title[9], _borr[9], "gold"]
        ]);

        var view = new google.visualization.DataView(data);
        view.setColumns([0, 1,
                         { calc: "stringify",
                           sourceColumn: 1,
                           type: "string",
                           role: "annotation" },
                         2]);
  	var options = {
          title: "Top Most Borrowers of "+ branch,
          bar: {groupWidth: "95%"},
          legend: { position: "none" },
        };
        var chart = new google.visualization.ColumnChart(document.getElementById("columnchart_values1"));
        chart.draw(view, options);
        drawChart2();
    }
    function drawChart2() {
    	var _title = new Array();
    	var _borr = new Array();
    	
    	var branch = document.getElementById("st").value;
    	var title = document.getElementById("til").value; 
    	title = title.replace("[", "");
    	title = title.replace("]", "");
    	var strings = title.split(",");
    	
    	for (var i=0; i<title.length; i++)
    		_title.push(strings[i] );
    	
    	var borr = document.getElementById("bor").value;
    	borr = borr.replace("[", "");
    	borr = borr.replace("]", "");
    	var strings1 = borr.split(",");
    	for (var i=0; i<borr.length; i++)
    		_borr.push(parseInt(strings1[i]) );
    	
        var data = google.visualization.arrayToDataTable([
			["Title", "Count", { role: "style" } ],
			[_title[0], _borr[0], "#D805D3"],
			[_title[1], _borr[1], "#D805D3"],
			[_title[2], _borr[2], "#D805D3"],
			[_title[3], _borr[3], "#D805D3"],
			[_title[4],	_borr[4], "#D805D3"],
			[_title[5], _borr[5], "#D805D3"],
			[_title[6], _borr[6], "#D805D3"],
			[_title[7], _borr[7], "#D805D3"],
			[_title[8], _borr[8], "#D805D3"],
			[_title[9], _borr[9], "#D805D3"]
        ]);
        var view = new google.visualization.DataView(data);
        view.setColumns([0, 1,
                         { calc: "stringify",
                           sourceColumn: 1,
                           type: "string",
                           role: "annotation" },
                         2]);
  	var options = {
          title: "Top Most Borrowed Books of "+branch,
          bar: {groupWidth: "95%"},
          legend: { position: "none" },
        };
        var chart = new google.visualization.ColumnChart(document.getElementById("columnchart_values2"));
        chart.draw(view, options);
    }
    
    