// December 14th, 2019

const fs = require('fs');

fs.readFile('input/2019/6.txt', 'utf-8', (err, data) => {
	if (err){ throw err; }
	data = data.split("\n");

	// Build map
	var map = {};
	for (var i = 0; i < data.length; i++){
		var segment = data[i].trim().split(")");
		map[segment[1]] = segment[0];
	}

	// Find path to COM
	var orbit = [];
	var key = "YOU";
	while (key != "COM"){
		key = map[key];
		orbit.push(key);
	}

	// Find matching orbit
	var match = "SAN";
	while (!orbit.includes(match)){
		match = map[match];
	}
	
	// Find minimum transfers
	var count = 0;
	key = map["YOU"];
	while (key != match){
		key = map[key];
		count++
	}
	key = map["SAN"];
	while (key != match){
		key = map[key];
		count++
	}
	console.log("Transfers: %d", count);
});
