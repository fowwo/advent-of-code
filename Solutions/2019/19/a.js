// March 16th, 2022

const fs = require("fs");
const Computer = require("../Computer");
const instructions = fs.readFileSync(`${__dirname}/input.txt`, "utf8").trim().split(',').map(x => parseInt(x));

let count = 0;
for (var i = 0; i < 50; i++) {
	for (var j = 0; j < 50; j++) {
		const computer = new Computer(instructions, [i, j]);
		computer.run();
		count += computer.output[0];
	}
}
console.log(count);
