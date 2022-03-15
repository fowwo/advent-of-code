// February 22nd, 2022
let a = 722;
let b = 354;

let count = 0;
for (var i = 0; i < 40000000; i++) {
	a = a * 16807 % 2147483647;
	b = b * 48271 % 2147483647;
	if ((a & 0xffff) === (b & 0xffff)) count++;
}
console.log(count);
