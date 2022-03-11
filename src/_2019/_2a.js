// March 11th, 2022
// Resolving initial opcode puzzles to make future opcode puzzles easier

const fs = require("fs");
let instructions = fs.readFileSync(`${__dirname}/../../input/2019/2.txt`, "utf8");
instructions = instructions.trim().split(',').map(x => parseInt(x));
instructions[1] = 12;
instructions[2] = 2;

class Computer {
	constructor(instructions) {
		this.memory = instructions;
		this.position = 0;
	}
	execute() {
		const code = this.memory[this.position];
		const a = this.memory[this.position + 1];
		const b = this.memory[this.position + 2];
		const c = this.memory[this.position + 3];
		switch (code) {
			case 1:
				this.add(a, b, c);
				break;
			case 2:
				this.multiply(a, b, c);
				break;
			default:
				throw new Error(`Invalid instruction: ${this.position}`);
		}
	}
	add(a, b, c) {
		this.memory[c] = this.memory[a] + this.memory[b];
		this.position += 4;
	}
	multiply(a, b, c) {
		this.memory[c] = this.memory[a] * this.memory[b];
		this.position += 4;
	}
	run() {
		while (this.memory[this.position] !== 99)	this.execute();
	}
}

const opcode = new Computer(instructions);
opcode.run();
console.log(opcode.memory[0]);
