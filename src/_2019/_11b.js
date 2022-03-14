// March 14th, 2022

const fs = require("fs");
const Computer = require("./Computer");
const instructions = fs.readFileSync(`${__dirname}/../../input/2019/11.txt`, "utf8").trim().split(',').map(x => parseInt(x));

const opcode = new Computer(instructions, [1]);
opcode.run();

let grid = {};
let x = 0;
let y = 0;
let direction = 0;

while (opcode.paused) {
	grid[`${x},${y}`] = opcode.output.shift();
	direction += (opcode.output.shift() === 0 ? 3 : 1);
	direction %= 4;
	switch (direction) {
		case 0:
			y++;
			break;
		case 1:
			x++;
			break;
		case 2:
			y--;
			break;
		case 3:
			x--;
			break;
							
	}
	opcode.input.push(grid[`${x},${y}`] === undefined ? 0 : grid[`${x},${y}`]);
	opcode.run();
}

let min = { x: 0, y: 0 };
let max = { x: 0, y: 0 };
Object.keys(grid).forEach(x => {
	let s = x.split(',').map(x => parseInt(x));
	min.x = Math.min(min.x, s[0]);
	max.x = Math.max(max.x, s[0]);
	min.y = Math.min(min.y, s[1]);
	max.y = Math.max(max.y, s[1]);
});

for (var i = max.y; i >= min.y; i--) {
	let row = [];
	for (var j = min.x; j <= max.x; j++) {
		row.push(grid[`${j},${i}`] === 1 ? "#" : " ");
	}
	console.log(row.join(""));
}
