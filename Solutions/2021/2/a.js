// December 1st, 2021
const fs = require("fs");

let list = fs.readFileSync(`${__dirname}/input.txt`, "utf8").split('\r\n');
list.pop();

let horizontal = 0;
let depth = 0;
for (var i = 0; i < list.length; i++) {
	let arr = list[i].split(" ");
	let direction = arr[0];
	let value = parseInt(arr[1]);
	switch (direction) {
		case "forward":
			horizontal += value;
			break;
		case "down":
			depth += value;
			break;
		case "up":
			depth -= value;
			break;
	}
}
console.log(horizontal * depth);
