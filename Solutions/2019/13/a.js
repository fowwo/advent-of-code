// March 14th, 2022

const fs = require("fs");
const Computer = require("../Computer");
const instructions = fs.readFileSync(`${__dirname}/input.txt`, "utf8").trim().split(',').map(x => parseInt(x));

const opcode = new Computer(instructions);
opcode.run();

let blocks = 0;
for (var i = 2; i < opcode.output.length; i += 3) {
	if (opcode.output[i] === 2) blocks++;
}
console.log(blocks);
