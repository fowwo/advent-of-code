// September 1st, 2020

const fs = require("fs");
var json = JSON.parse(fs.readFileSync(`${__dirname}/input.txt`));

function search(item) {
	if (Array.isArray(item)) {
		let sum = 0;
		for (var i = 0; i < item.length; i++) {
			sum += search(item[i]);
		}
		return sum;
	} else if (typeof item === "number") {
		return item;
	} else if (typeof item !== "string") {
		// The item is an object
		let hasRed = false;
		let sum = 0;
		for (var key of Object.keys(item)) {
			let value = item[key];
			if (value === "red") {
				hasRed = true;
				break;
			} else {
				sum += search(value);
			}
		}
		if (!hasRed) {
			return sum;
		}
	}
	return 0;
}

console.log(search(json));
