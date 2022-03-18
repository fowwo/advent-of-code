// March 18th, 2022

const fs = require("fs");
const Computer = require("../Computer");
const Combination = require("../../../Tool/JavaScript/Combination");
const instructions = fs.readFileSync(`${__dirname}/input.txt`, "utf8").trim().split(",").map(x => parseInt(x));

/* 
	Commands:                                                                   +---+
	 - north                                                                    | e |
	 - south                                                                    +---+
	 - east                                                                       |  
	 - west                                                     +---+   +---+   +---+
	 - take <item>                                              |   |---| P |   | k |
	 - drop <item>                                              +---+   +---+   +---+
	 - inv                                                        |               |  
	Items:                      +---+   +---+   +---+   +---+   +---+   +---+   +---+
	 - hypercube                | c |---| M |---| E |---| I |   | t |---| a |---|   |
	 - coin                     +---+   +---+   +---+   +---+   +---+   +---+   +---+
	 - klein bottle               |       |                       |       |          
	 - shell                    +---+   +---+   +---+           +---+   +---+        
	 - easter egg               |   |   | G |---| d |-----------| O |   | s |        
	 - astrolabe                +---+   +---+   +---+           +---+   +---+        
	 - tambourine                 |                               |                  
	 - dark matter              +---+                   +---+   +---+                
	Traps:                      | _ |                   |   |---|   |                
	 - Photons                  +---+                   +---+   +---+                
	 - Giant electronmagnet                                       |                  
	 - Molten lava                                              +---+                
	 - Escape pod                                               | h |                
	 - Infinite loop                                            +---+                
*/

const command = (x) => { return `${x}\n`.split('').map(x => x.charCodeAt(0)); }
const computer = new Computer(instructions, [
	// Fast travel
	...command("north"),
	...command("take tambourine"),
	...command("east"),
	...command("take astrolabe"),
	...command("east"),
	...command("north"),
	...command("take klein bottle"),
	...command("north"),
	...command("take easter egg"),
	...command("south"),
	...command("south"),
	...command("west"),
	...command("south"),
	...command("take shell"),
	...command("north"),
	...command("west"),
	...command("south"),
	...command("south"),
	...command("south"),
	...command("take hypercube"),
	...command("north"),
	...command("north"),
	...command("west"),
	...command("take dark matter"),
	...command("west"),
	...command("north"),
	...command("west"),
	...command("take coin"),
	...command("south")
]);
computer.run();

// Brute-force item combinations
let combinations = Combination.create("coin", "klein bottle", "shell", "easter egg", "astrolabe", "tambourine", "dark matter"); // The drone definitely must be carrying the hypercube
for (var combination of combinations) {
	for (var item of combination)  computer.input.push(...command(`drop ${item}`));
	computer.input.push(...command("south"));
	computer.run();
	for (var item of combination)  computer.input.push(...command(`take ${item}`));
}
print(); // hypercube, coin, tambourine, dark matter

// Manual travel
// const rl = require("readline").createInterface({ input: process.stdin, output: process.stdout });
// rl.on("line", function (line) {
// 	if (line.trim() === "exit") process.exit();
// 	computer.input.push(...command(line.trim()))
// 	computer.run();
// 	print();
// 	rl.prompt();
// });
// computer.output = [];
// computer.input.push(...command("inv"));
// computer.run();
// print();
// rl.prompt();

function print() {
	console.log(String.fromCharCode(...computer.output));
	computer.output = [];
}
