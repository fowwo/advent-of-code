// December 12th, 2019

var a = 134792;
var b = 675810;

var valid = 0;

// It is a six-digit number.
// The value is within the range given in your puzzle input.
for (var i = a; i <= b; i++){

	// Two adjacent digits are the same (like 22 in 122345).
	if (i.toString()[0] == i.toString()[1] || i.toString()[1] == i.toString()[2] || i.toString()[2] == i.toString()[3] || i.toString()[3] == i.toString()[4] || i.toString()[4] == i.toString()[5]){

		// Going from left to right, the digits never decrease; they only ever increase or stay the same (like 111123 or 135679).
		if (Number(i.toString()[0]) <= Number(i.toString()[1]) && Number(i.toString()[1]) <= Number(i.toString()[2]) && Number(i.toString()[2]) <= Number(i.toString()[3]) && Number(i.toString()[3]) <= Number(i.toString()[4]) && Number(i.toString()[4]) <= Number(i.toString()[5])){
			valid++;
		}
	}
}

console.log("Valid numbers: " + valid);
