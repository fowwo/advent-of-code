// December 14th, 2019

const fs = require('fs');

fs.readFile(`${__dirname}/input.txt`, 'utf-8', (err, data) => {
	if (err){ throw err; }
	data = data.trim().split("\r\n");

	// Build map
	var map = {};
	for (var i = 0; i < data.length; i++){
		var segment = data[i].split(")");
		map[segment[1]] = segment[0];
	}

	// Count orbits
	var count = 0;
	Object.keys(map).forEach((key) => {
		while (key != "COM"){
			key = map[key];
			count++;
		}
	});
	console.log("Orbits: %d", count);
});
