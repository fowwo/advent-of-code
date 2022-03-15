// March 15th, 2022

const fs = require("fs");
const Computer = require("../Computer");
const instructions = fs.readFileSync(`${__dirname}/input.txt`, "utf8").trim().split(',').map(x => parseInt(x));

const computer = new Computer(instructions);
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
if (r > 0 && grid[r-1][c] === '#') {
	direction = 0;
} else if (c < grid[0].length - 1 && grid[r][c+1] === '#') {
	direction = 1;
} else if (r < grid.length - 1 && grid[r+1][c] === '#') {
	direction = 2;
} else {
	direction = 3;
}

// Move
let scaffold = new Set();
let sum = 0;
while (grid[robot.r][robot.c] !== '.') {
	if (scaffold.has(`${robot.r},${robot.c}`)) sum += robot.r * robot.c;
	scaffold.add(`${robot.r},${robot.c}`);
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
	if (ahead() === '.') turn();
}
console.log(sum);

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
			direction = 0;
		} else if (robot.r < grid.length - 1 && grid[robot.r+1][robot.c] === '#') {
			direction = 2;
		}
	} else {
		if (robot.c < grid[0].length - 1 && grid[robot.r][robot.c+1] === '#') {
			direction = 1;
		} else if (robot.c > 0 && grid[robot.r][robot.c-1] === '#') {
			direction = 3;
		}
	}
}
