// March 15th, 2022

const fs = require("fs");
const Computer = require("../Computer");
const instructions = fs.readFileSync(`${__dirname}/input.txt`, "utf8").trim().split(',').map(x => parseInt(x));

let computer = new Computer(instructions);
computer.run();

// Build grid
let grid = [];
let row = "";
for (var c of computer.output.map(x => String.fromCharCode(x))) {
	if (c === '\n') {
		if (row) grid.push(row);
		row = "";
	} else {
		row += c;
	}
}

// Find robot
let robot = null;
for (var r = 0; r < grid.length; r++) {
	for (var c = 0; c < grid.length; c++) {
		if ("^v<>".includes(grid[r][c])) {
			robot = {r, c};
			break;
		}
	}
	if (robot) break;
}

// Find initial direction
let direction = null;
let moves = [];
if (r > 0 && grid[r-1][c] === '#') {
	direction = 0;
} else if (c < grid[0].length - 1 && grid[r][c+1] === '#') {
	direction = 1;
	moves.push("R");
} else if (r < grid.length - 1 && grid[r+1][c] === '#') {
	direction = 2;
	moves.push("R,R");
} else {
	direction = 3;
	moves.push("L");
}

// Move
let magnitude = 0;
while (grid[robot.r][robot.c] !== '.') {
	magnitude++;
	switch (direction) {
		case 0:
			robot.r--;
			break;
		case 1:
			robot.c++;
			break;
		case 2:
			robot.r++;
			break;
		case 3:
			robot.c--;
			break;
	}
	if (ahead() === '.') {
		moves.push(magnitude);
		magnitude = 0;
		turn();
	}
}
moves.pop(); // Falling into the void
// console.log(moves);

/*
Noticing pattern in moves
------------------------------
R,4,R,10,R,8,R,4,  (A)
R,10,R,6,R,4,      (B)
R,4,R,10,R,8,R,4,
R,10,R,6,R,4,
R,4,L,12,R,6,L,12, (C)
R,10,R,6,R,4,
R,4,L,12,R,6,L,12,
R,4,R,10,R,8,R,4,
R,10,R,6,R,4,
R,4,L,12,R,6,L,12
------------------------------
  --->  A,B,A,B,C,B,C,A,B,C
*/

const routine = (x) => { return `${x}\n`.split('').map(x => x.charCodeAt(0)); }
computer = new Computer(instructions);
computer.memory[0] = 2;
computer.input.push(...routine("A,B,A,B,C,B,C,A,B,C"));
computer.input.push(...routine("R,4,R,10,R,8,R,4"));
computer.input.push(...routine("R,10,R,6,R,4"));
computer.input.push(...routine("R,4,L,12,R,6,L,12"));
computer.input.push(...routine("n"));
computer.run();
console.log(computer.output.at(-1));

function ahead() {
	switch (direction) {
		case 0:
			return robot.r > 0 ? grid[robot.r-1][robot.c] : '.';
		case 1:
			return robot.c < grid[0].length - 1 ? grid[robot.r][robot.c+1] : '.';
		case 2:
			return robot.r < grid.length - 1 ? grid[robot.r+1][robot.c] : '.';
		case 3:
			return robot.c > 0 ? grid[robot.r][robot.c-1] : '.';
	}
}
function turn() {
	if (direction % 2) {
		if (robot.r > 0 && grid[robot.r-1][robot.c] === '#') {
			moves.push(direction === 1 ? "L" : "R");
			direction = 0;
		} else if (robot.r < grid.length - 1 && grid[robot.r+1][robot.c] === '#') {
			moves.push(direction === 1 ? "R" : "L");
			direction = 2;
		}
	} else {
		if (robot.c < grid[0].length - 1 && grid[robot.r][robot.c+1] === '#') {
			moves.push(direction === 0 ? "R" : "L");
			direction = 1;
		} else if (robot.c > 0 && grid[robot.r][robot.c-1] === '#') {
			moves.push(direction === 0 ? "L" : "R");
			direction = 3;
		}
	}
}
