// December 12th, 2022

const fs = require("fs");

const lines = fs.readFileSync(`${__dirname}/input.txt`, "utf8").trim().replace(/\r\n/g, '\n').split('\n');

const packets = [];
for (let i = 0; 3 * i < lines.length; i++) {
	const a = eval(lines[3 * i]);
	const b = eval(lines[3 * i + 1]);
	packets.push(a, b);
}
packets.push([[2]], [[6]]);
packets.sort(compareLists);

let c = 1;
for (let i = 0; i < packets.length; i++) {
	const packet = packets[i];
	if (packet.length === 1 && packet[0].length === 1 && [2, 6].includes(packet[0][0])) {
		c *= i + 1;
	}
}
console.log(c);

function compareLists(a, b) {
	const n = Math.max(a.length, b.length);
	for (let i = 0; i < n; i++) {
		if (a[i] === undefined && b[i] !== undefined) return -1;
		if (a[i] !== undefined && b[i] === undefined) return 1;
		if (typeof(a[i]) === "number" && typeof(b[i]) === "number") {
			if (a[i] < b[i]) return -1;
			if (a[i] > b[i]) return 1;
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
