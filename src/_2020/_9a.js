// September 11th, 2021

const fs = require("fs");

var arr = fs.readFileSync("input/2020/9.txt", "utf-8").split("\r\n").map(x => parseInt(x));
arr.pop();

const preamble = 25;
var v = false;
for (var i = preamble; i < arr.length; i++) {
	for (var a = 0; a < preamble - 1; a++) {
		for (var b = a + 1; b < preamble; b++) {
			if (arr[i - preamble + a] + arr[i - preamble + b] == arr[i]) {
				v = true;
				break;
			}
		}
		if (v) break;
	}
	if (!v) {
		console.log(arr[i]);
		break;
	}
	v = false;
}
