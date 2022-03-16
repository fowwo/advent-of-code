// March 16th, 2022

const fs = require("fs");
const Computer = require("../Computer");
const instructions = fs.readFileSync(`${__dirname}/input.txt`, "utf8").trim().split(',').map(x => parseInt(x));

let i = 0, j = 0;
while (true) {
	let a = new Computer(instructions, [i + 99, j]);
	let b = new Computer(instructions, [i, j + 99]);
	a.run();
	b.run();
	a = a.output[0];
	b = b.output[0];
	if (!a) {
		j++;
	} else if (!b) {
		i++;
	} else {
		break;
	}
}
console.log(10000 * i + j);
