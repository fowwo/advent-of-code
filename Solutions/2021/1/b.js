// December 1st, 2021
const fs = require("fs");

const list = fs.readFileSync(`${__dirname}/input.txt`, "utf8").split('\r\n').map(x => parseInt(x));
let count = 0;
for (var i = 3; i < list.length; i++) {
	if (list[i] > list[i - 3]) count++;
}
console.log(count);
