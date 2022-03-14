// March 14th, 2022

const fs = require("fs");
const instructions = fs.readFileSync(`${__dirname}/../../input/2019/9.txt`, "utf8").trim().split(',').map(x => parseInt(x));

class Computer {
	constructor(instructions, input = []) {
		this.memory = {};
		for (var i in instructions) this.memory[i] = instructions[i];
		this.position = 0;
		this.relative = 0;
		this.input = input;
		this.output = [];
		this.paused = false;
	}
	execute() {
		let code = this.memory[this.position];
		let modes = `00${Math.floor(code / 100)}`;
		modes = modes.substring(modes.length - 3, modes.length).split('').map(x => parseInt(x));
		code %= 100;
		for (var i = 1; i <= 3; i++) if (this.memory[this.position + i] === undefined) this.memory[this.position + i] = 0;
		let a = this.memory[this.position + 1];
		let b = this.memory[this.position + 2];
		let c = this.memory[this.position + 3];
		switch (code) {
			case 1:
				a = this.parameterMode(a, modes[2]);
				b = this.parameterMode(b, modes[1]);
				c = this.parameterModeIndex(c, modes[0]);
				this.add(a, b, c);
				break;
			case 2:
				a = this.parameterMode(a, modes[2]);
				b = this.parameterMode(b, modes[1]);
				c = this.parameterModeIndex(c, modes[0]);
				this.multiply(a, b, c);
				break;
			case 3:
				a = this.parameterModeIndex(a, modes[2]);
				this.in(a);
				break;
			case 4:
				a = this.parameterMode(a, modes[2]);
				this.out(a);
				break;
			case 5:
				a = this.parameterMode(a, modes[2]);
				b = this.parameterMode(b, modes[1]);
				this.jumpIfTrue(a, b);
				break;
			case 6:
				a = this.parameterMode(a, modes[2]);
				b = this.parameterMode(b, modes[1]);
				this.jumpIfFalse(a, b);
				break;
			case 7:
				a = this.parameterMode(a, modes[2]);
				b = this.parameterMode(b, modes[1]);
				c = this.parameterModeIndex(c, modes[0]);
				this.lessThan(a, b, c);
				break;
			case 8:
				a = this.parameterMode(a, modes[2]);
				b = this.parameterMode(b, modes[1]);
				c = this.parameterModeIndex(c, modes[0]);
				this.equals(a, b, c);
				break;
			case 9:
				a = this.parameterMode(a, modes[2]);
				this.adjust(a);
				break;
			default:
				throw new Error(`Invalid instruction: ${this.memory[this.position]} @ ${this.position}`);
		}
	}
	parameterMode(parameter, mode) {
		switch (mode) {
			case 0:
				if (this.memory[parameter] === undefined) this.memory[parameter] = 0;
				return this.memory[parameter];
			case 1:
				return parameter;
			case 2:
				if (this.memory[parameter + this.relative] === undefined) this.memory[parameter + this.relative] = 0;
				return this.memory[parameter + this.relative];
		}
	}
	parameterModeIndex(parameter, mode) {
		switch (mode) {
			case 0:
				if (this.memory[parameter] === undefined) this.memory[parameter] = 0;
				return parameter;
			case 2:
				if (this.memory[parameter + this.relative] === undefined) this.memory[parameter + this.relative] = 0;
				return parameter + this.relative;
		}
	}
	run() {
		this.paused = false;
		while (this.memory[this.position] !== 99 && !this.paused) this.execute();
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
		if (this.input.length === 0) {
			this.paused = true;
			return;
		}
		this.memory[a] = this.input.shift();
		this.position += 2;
	}
	out(a) {
		this.output.push(a);
		this.position += 2;
	}
	jumpIfTrue(a, b) {
		this.position = (a !== 0 ? b : this.position + 3);
	}
	jumpIfFalse(a, b) {
		this.position = (a === 0 ? b : this.position + 3);
	}
	lessThan(a, b, c) {
		this.memory[c] = (a < b ? 1 : 0);
		this.position += 4;
	}
	equals(a, b, c) {
		this.memory[c] = (a === b ? 1 : 0);
		this.position += 4;
	}
	adjust(a) {
		this.relative += a;
		this.position += 2;
	}
}

const opcode = new Computer(instructions, [1]);
opcode.run();
console.log(opcode.output[0]);
