// February 22nd, 2022
let a = 722;
let b = 354;

let count = 0;
let A = [];
let B = [];
for (var i = 0; A.length !== 5000000 || B.length !== 5000000; i++) {
	a = a * 16807 % 2147483647;
	b = b * 48271 % 2147483647;
	if (a % 4 === 0 && A.length < 5000000) A.push(a & 0xffff);
	if (b % 8 === 0 && B.length < 5000000) B.push(b & 0xffff);
}
for (var i = 0; i < 5000000; i++) {
	if (A[i] === B[i]) count++;
}
console.log(count);
