class Combination {

	static create(...list) {
		let combinations = [new Set()];
		for (var item of list) {
			combinations = Combination.add(combinations, item);
		}
		return combinations;
	}

	static add(combinations, item) {
		let sets = combinations.slice();
		for (var set of combinations) {
			let n = new Set(set);
			n.add(item);
			sets.push(n);
		}
		return sets;
	}

}

module.exports = Combination;
