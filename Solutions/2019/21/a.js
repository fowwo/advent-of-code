// March 17th, 2022

const fs = require("fs");
const Computer = require("../Computer");
const instructions = fs.readFileSync(`${__dirname}/input.txt`, "utf8").trim().split(',').map(x => parseInt(x));

const ascii = (x) => { return `${x}\n`.split('').map(x => x.charCodeAt(0)); };
const computer = new Computer(instructions);
computer.input.push(...ascii("NOT C J")); // Jump early if possible
computer.input.push(...ascii("AND D J")); // Always land on hull
computer.input.push(...ascii("NOT A T")); // Jump over immediate hole
computer.input.push(...ascii("OR T J"));
computer.input.push(...ascii("WALK"));
computer.run();
console.log(computer.output.at(-1));

function print() {
	console.log(String.fromCharCode(...computer.output));
}
