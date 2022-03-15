// February 17th, 2022
const input = "94,84,0,79,2,27,81,1,123,93,218,23,103,255,254,243".split('').map(x => x.charCodeAt(0));
input.push(17, 31, 73, 47, 23);

const n = 256;
let string = [];
for (var m = 0; m < n; m++) string.push(m);

let i = 0;
let skip = 0;
for (var c = 0; c < 64; c++) {
	for (var length of input) {
		for (var j = 0; i + j < i + length - j - 1; j++) {
			let t = string[(i + j) % n];
			string[(i + j) % n] = string[(i + length - j - 1) % n];
			string[(i + length - j - 1) % n] = t;
		}
		i += length + skip;
		skip++;
	}
}

// Dense hash
let list = [];
for (var j = 0; j < 16; j++) {
	let x = string[16 * j];
	for (var k = 1; k < 16; k++) {
		x ^= string[16 * j + k];
	}
	list.push(x);
}

const hex = ['0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'];
let output = "";
for (var num of list) {
	let a = num % 16;
	let b = (num - a) / 16;
	output += hex[b] + hex[a];
}
console.log(output);
