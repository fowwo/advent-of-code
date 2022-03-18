// March 17th, 2022

const fs = require("fs");
const Computer = require("../Computer");
const instructions = fs.readFileSync(`${__dirname}/input.txt`, "utf8").trim().split(',').map(x => parseInt(x));

const computers = [];
for (var i = 0; i < 50; i++) {
	let computer = new Computer(instructions, [i]);
	computers.push(computer);
}

let nat = {x: null, y: null};
let prev;
while (nat.y !== prev) {
	prev = nat.y;
	let idle = false;
	while (!idle) {
		idle = true;
		for (var computer of computers) {
			if (!computer.input.length) {
				computer.input.push(-1);
			} else {
				idle = false;
			}
			computer.run();
			if (computer.output.length) {
				let a = computer.output.shift();
				let x = computer.output.shift();
				let y = computer.output.shift();
				if (a === 255) {
					nat = {x, y};
				} else {
					computers[a].input.push(x, y);
				}
			}
		}
	}
	computers[0].input.push(nat.x, nat.y);
}
console.log(nat.y);
