// February 17th, 2022
const input = "94,84,0,79,2,27,81,1,123,93,218,23,103,255,254,243".split(',').map(x => parseInt(x));

const n = 256;
let string = [];
for (var m = 0; m < n; m++) string.push(m);

let i = 0;
let skip = 0;
for (var length of input) {
	for (var j = 0; i + j < i + length - j - 1; j++) {
		let t = string[(i + j) % n];
		string[(i + j) % n] = string[(i + length - j - 1) % n];
		string[(i + length - j - 1) % n] = t;
	}
	i += length + skip;
	skip++;
}

console.log(string[0] * string[1]);
