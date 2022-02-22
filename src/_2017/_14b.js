// February 22nd, 2022
const input = "oundnydw";

let m = [];
for (var i = 0; i < 128; i++) {
	m.push([]);
	let string = hash(`${input}-${i}`);
	for (var l of string) {
		m[i].push(l);
	}
}

let count = 0;
for (var i = 0; i < 128; i++) {
	for (var j = 0; j < 128; j++) {
		if (m[i][j] === '1') {
			let fill = [`${i},${j}`];
			while (fill.length) {
				let temp = [];
				for (var l of fill) {
					let split = l.split(',');
					let i2 = parseInt(split[0]);
					let j2 = parseInt(split[1]);
					m[i2][j2] = '0';
					if (i2 > 0 && m[i2-1][j2] === '1') temp.push(`${i2-1},${j2}`);
					if (j2 > 0 && m[i2][j2-1] === '1') temp.push(`${i2},${j2-1}`);
					if (i2 < 127 && m[i2+1][j2] === '1') temp.push(`${i2+1},${j2}`);
					if (j2 < 127 && m[i2][j2+1] === '1') temp.push(`${i2},${j2+1}`);
				}
				fill = temp;
			}
			count++;
		}
	}
}
console.log(count);

function hash(input) {
	input = input.split('').map(x => x.charCodeAt(0));
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

	let binary = "";
	for (var num of list) {
		let sub = "";
		while (num) {
			let mod = num % 2;
			sub = `${mod}${sub}`;
			num = (num - mod) / 2;
		}
		while (sub.length < 8) sub = `0${sub}`;
		binary += sub;
	}

	return binary;
}
