// September 11th, 2021

const fs = require("fs");

var arr = fs.readFileSync("input/2020/9.txt", "utf-8").split("\r\n").map(x => parseInt(x));
arr.pop();

const preamble = 25;
var v = false;
var target;
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
		target = arr[i];
		break;
	}
	v = false;
}

// Finding contiguous range
var i = 0;
var j = 1;
let sum = arr[i] + arr[j];
while (sum !== target) {
	if (sum < target) {
		j++;
		sum += arr[j];
	} else {
		sum -= arr[i];
		i++;
	}
}

// Finding min/max
var min = arr[i];
var max = arr[i];
for (var k = i; k <= j; k++) {
	if (arr[k] < min) min = arr[k];
	else if (arr[k] > max) max = arr[k];
}
console.log(min + max);
