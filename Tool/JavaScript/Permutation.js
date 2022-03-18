class Permutation {

	static create(...list) {
		let permutations = [[list[0]]];
		for (var i = 1; i < list.length; i++) {
			permutations = Permutation.add(permutations, list[i]);
		}
		return permutations;
	}

	static add(permutations, item) {
		let temp = [];
		for (var perm of permutations) {
			for (var j = 0; j <= perm.length; j++) {
				let copy = perm.slice();
				copy.splice(j, 0, item);
				temp.push(copy);
			}
		}
		return temp;
	}

}

module.exports = Permutation;
