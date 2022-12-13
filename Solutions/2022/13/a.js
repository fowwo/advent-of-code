// December 12th, 2022

const fs = require("fs");

const lines = fs.readFileSync(`${__dirname}/input.txt`, "utf8").trim().replace(/\r\n/g, '\n').split('\n');

let c = 0;
for (let i = 0; 3 * i < lines.length; i++) {
	const a = eval(lines[3 * i]);
	const b = eval(lines[3 * i + 1]);
	if (compareLists(a, b)) c += i + 1;
}
console.log(c);

function compareLists(a, b) {
	const n = Math.max(a.length, b.length);
	for (let i = 0; i < n; i++) {
		if (a[i] === undefined && b[i] !== undefined) return true;
		if (a[i] !== undefined && b[i] === undefined) return false;
		if (typeof(a[i]) === "number" && typeof(b[i]) === "number") {
			if (a[i] < b[i]) return true;
			if (a[i] > b[i]) return false;
		} else if (typeof(a[i]) !== "number" && typeof(b[i]) !== "number") {
			const v = compareLists(a[i], b[i]);
			if (v !== undefined) return v; 
		} else if (typeof(a[i]) === "number") {
			const v = compareLists([ a[i] ], b[i]);
			if (v !== undefined) return v; 
		} else {
			const v = compareLists(a[i], [ b[i] ]);
			if (v !== undefined) return v; 
		}
	}
}
