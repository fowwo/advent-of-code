// March 14th, 2022

const fs = require("fs");
const Computer = require("../Computer");
const instructions = fs.readFileSync(`${__dirname}/input.txt`, "utf8").trim().split(',').map(x => parseInt(x));

let computers = [[ new Computer(instructions), 0, 0 ]];
const known = new Set(["0,0"]);
let moves = 0;
while (true) {
	let temp = [];
	moves++;
	for (var [computer, x, y] of computers) {
		temp.push(...move(x, y + 1, 1));
		temp.push(...move(x, y - 1, 2));
		temp.push(...move(x - 1, y, 3));
		temp.push(...move(x + 1, y, 4));
	}
	computers = temp;
}

function move(x, y, direction) {
	let list = [];
	if (!known.has(`${x},${y}`)) {
		known.add(`${x},${y}`);
		let nc = new Computer(computer.memory, [direction]);
		nc.run();
		let status = nc.output.pop();
		if (status === 1) {
			list.push([ nc, x, y ]);
		} else if (status === 2) {
			console.log(moves);
			process.exit(0);
		}
	}
	return list;
}
