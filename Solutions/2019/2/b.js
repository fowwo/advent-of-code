// March 11th, 2022
// Resolving initial opcode puzzles to make future opcode puzzles easier

const fs = require("fs");
const instructions = fs.readFileSync(`${__dirname}/input.txt`, "utf8").trim().split(',').map(x => parseInt(x));

class Computer {
	constructor(instructions) {
		this.memory = instructions.slice();
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
		while (this.memory[this.position] !== 99) this.execute();
	}
}

for (var noun = 0; noun < 100; noun++) {
	for (var verb = 0; verb < 100; verb++) {
		const opcode = new Computer(instructions);
		opcode.memory[1] = noun;
		opcode.memory[2] = verb;
		opcode.run();
		if (opcode.memory[0] === 19690720) {
			console.log(100 * noun + verb);
			process.exit(0);
		}
	}
}
