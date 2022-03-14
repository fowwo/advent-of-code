// March 14th, 2022

const fs = require("fs");
const Computer = require("./Computer");
const instructions = fs.readFileSync(`${__dirname}/../../input/2019/13.txt`, "utf8").trim().split(',').map(x => parseInt(x));

const opcode = new Computer(instructions, [0, 0]); // Waiting for ball to hit paddle
opcode.memory[0] = 2;
opcode.run();

// Find width and height
let width = 0;
let height = 0;
for (var i = 0; i < opcode.output.length; i += 3) {
	width = Math.max(width, opcode.output[i] + 1);
	height = Math.max(height, opcode.output[i + 1] + 1);
}

// Generate empty screen
let screen = [];
for (var r = 0; r < height; r++) {
	let row = [];
	for (var c = 0; c < width; c++) {
		row.push(0);
	}
	screen.push(row);
}
updateTiles();

// Find ball
let ball = null;
for (var r = 0; r < height; r++) {
	for (var c = 0; c < width; c++) {
		if (screen[r][c] === 4) {
			ball = { r, c };
			break;
		}
	}
	if (ball) break;
}

let score = 0;
opcode.input.push(0);
opcode.run();
while (opcode.paused) {
	updateTiles();
	// displayScreen();
	let newBall = findBall();
	opcode.input.push(newBall.c - ball.c);
	ball = newBall;
	opcode.run();
	updateScore();
}
console.log(score);

function updateScore() {
	for (var i = 0; i < opcode.output.length; i += 3) {
		if (opcode.output[i] === -1 && opcode.output[i + 1] === 0) score = opcode.output[i + 2];
	}
}
function updateTiles() {
	for (var i = 0; i < opcode.output.length; i += 3) {
		if (opcode.output[i] !== -1) {
			screen[opcode.output[i + 1]][opcode.output[i]] = opcode.output[i + 2];
		}
	}
	opcode.output = [];
}
function displayScreen() {
	for (var r = 0; r < height; r++) {
		let row = "";
		for (var c = 0; c < width; c++) {
			switch (screen[r][c]) {
				case 0:
					row += " ";
					break;
				case 1:
					row += "|";
					break;
				case 2:
					row += "#";
					break;
				case 3:
					row += "-";
					break;
				case 4:
					row += "o";
					break;
				default:
					row += '!'
			}
		}
		console.log(row);
	}
}
function findBall() {
	for (var x of [[-1,-1], [0,-1], [1,-1], [-1,1], [0,1], [1,1]]) {
		const r = ball.r - x[0];
		const c = ball.c - x[1];
		if (screen[r][c] === 4) return { r, c };
	}
}
