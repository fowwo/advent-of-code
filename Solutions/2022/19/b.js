// December 21st, 2022

const fs = require("fs");

const lines = fs.readFileSync(`${__dirname}/input.txt`, "utf8").trim().replace(/\r\n/g, '\n').split('\n').map(x => x.trim()).slice(0, 3);

const blueprints = [];
for (let line of lines) {
	const b = line.match(/[0-9]+/g).map(x => parseInt(x));
	blueprints.push(b);
}

let c = 1;
for (const blueprint of blueprints) {
	let m = [ [ 1, 0, 0, 0, 0, 0, 0, 0 ] ];

	const maxOre = Math.max(blueprint[2], blueprint[3], blueprint[5]);
	const maxClay = blueprint[4];
	const maxObsidian = blueprint[6];

	let memo = {};
	const keep = (state) => {
		const key = state.slice(0, 4);
		if (memo[key] == undefined) {
			memo[key] = state.slice(4);
			return true;
		}

		const b = memo[key];
		let ax = 0;
		for (let i = 0; i < 4; i++) {
			if (state[i + 4] > b[i]) ax++;
		}
		if (ax > 0) {
			if (ax == 4) memo[key] = state.slice(4);
			return true;
		}
		return false;
	};

	for (let i = 0; i < 32; i++) {
		const next = [];
		memo = {};
		for (const x of m) {

			// Geode
			if (x[4] >= blueprint[5] && x[6] >= blueprint[6]) {
				const n = x.slice();
				for (let j = 0; j < 4; j++) n[j + 4] += n[j];
				n[4] -= blueprint[5];
				n[6] -= blueprint[6];
				n[3]++;
				if (keep(n)) next.push(n);
				continue;
			}

			let v = 0;

			// Ore
			if (x[4] >= blueprint[1] && x[0] < maxOre) {
				const n = x.slice();
				for (let j = 0; j < 4; j++) n[j + 4] += n[j];
				n[4] -= blueprint[1];
				n[0]++;
				if (keep(n)) next.push(n);
				v++;
			}

			// Clay
			if (x[4] >= blueprint[2] && x[1] < maxClay) {
				const n = x.slice();
				for (let j = 0; j < 4; j++) n[j + 4] += n[j];
				n[4] -= blueprint[2];
				n[1]++;
				if (keep(n)) next.push(n);
				v++;
			}

			// Obsidian
			if (x[4] >= blueprint[3] && x[5] >= blueprint[4] && x[2] < maxObsidian) {
				const n = x.slice();
				for (let j = 0; j < 4; j++) n[j + 4] += n[j];
				n[4] -= blueprint[3];
				n[5] -= blueprint[4];
				n[2]++;
				if (keep(n)) next.push(n);
				v++;
			}

			for (let j = 0; j < 4; j++) x[j + 4] += x[j];
			if (v < 3 && keep(x)) next.push(x);
		}
		m = next;
	}

	let n = 0;
	for (const x of m) n = Math.max(n, x[7]);
	c *= n;
}

console.log(c);
