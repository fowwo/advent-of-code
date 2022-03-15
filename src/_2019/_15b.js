// March 14th, 2022

const fs = require("fs");
const Computer = require("./Computer");
const instructions = fs.readFileSync(`${__dirname}/../../input/2019/15.txt`, "utf8").trim().split(',').map(x => parseInt(x));

let computers = [[ new Computer(instructions), 0, 0 ]];
const known = new Set(["0,0"]);
const walls = new Set();
let oxygen = null;
while (computers.length) {
	let temp = [];
	for (var [computer, x, y] of computers) {
		temp.push(...move(x, y + 1, 1));
		temp.push(...move(x, y - 1, 2));
		temp.push(...move(x - 1, y, 3));
		temp.push(...move(x + 1, y, 4));
	}
	computers = temp;
}

known.forEach(x => { if (walls.has(x)) known.delete(x); });
let list = [oxygen];
for (var minutes = 0; list.length; minutes++) {
	let temp = [];
	for (var [x, y] of list) {
		temp.push(...move2(x, y + 1));
		temp.push(...move2(x, y - 1));
		temp.push(...move2(x - 1, y));
		temp.push(...move2(x + 1, y));
	}
	list = temp;
}
console.log(minutes - 1);

function move(x, y, direction) {
	let list = [];
	if (!known.has(`${x},${y}`)) {
		known.add(`${x},${y}`);
		let nc = new Computer(computer.memory, [direction]);
		nc.run();
		let status = nc.output.pop();
		if (status === 0) {
			walls.add(`${x},${y}`);
		} else {
			list.push([ nc, x, y ]);
			if (status === 2) oxygen = [x, y];
		}
		
	}
	return list;
}
function move2(x, y) {
	let list = [];
	if (known.has(`${x},${y}`)) {
		known.delete(`${x},${y}`);
		list.push([ x, y ]);
	}
	return list;
}
