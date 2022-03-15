// March 13th, 2022
// Resolving initial opcode puzzles to make future opcode puzzles easier

const fs = require("fs");
const instructions = fs.readFileSync(`${__dirname}/input.txt`, "utf8").trim().split(',').map(x => parseInt(x));

class Computer {
	constructor(instructions, input = []) {
		this.memory = instructions.slice();
		this.position = 0;
		this.input = input;
		this.output = [];
	}
	execute() {
		let code = this.memory[this.position];
		let modes = `00${Math.floor(code / 100)}`;
		modes = modes.substring(modes.length - 3, modes.length).split('').map(x => parseInt(x));
		code %= 100;
		let a = this.memory[this.position + 1];
		let b = this.memory[this.position + 2];
		let c = this.memory[this.position + 3];
		switch (code) {
			case 1:
				a = this.parameterMode(a, modes[2]);
				b = this.parameterMode(b, modes[1]);
				this.add(a, b, c);
				break;
			case 2:
				a = this.parameterMode(a, modes[2]);
				b = this.parameterMode(b, modes[1]);
				this.multiply(a, b, c);
				break;
			case 3:
				this.in(a);
				break;
			case 4:
				this.out(a);
				break;
			default:
				throw new Error(`Invalid instruction: ${this.memory[this.position]} @ ${this.position}`);
		}
	}
	parameterMode(parameter, mode) {
		switch (mode) {
			case 0:
				return this.memory[parameter];
			case 1:
				return parameter;
		}
	}
	run() {
		while (this.memory[this.position] !== 99) this.execute();
	}
	add(a, b, c) {
		this.memory[c] = a + b;
		this.position += 4;
	}
	multiply(a, b, c) {
		this.memory[c] = a * b;
		this.position += 4;
	}
	in(a) {
		this.memory[a] = this.input.shift();
		this.position += 2;
	}
	out(a) {
		this.output.push(this.memory[a]);
		this.position += 2;
	}
}

const opcode = new Computer(instructions, [1]);
opcode.run();
console.log(opcode.output.at(-1));
