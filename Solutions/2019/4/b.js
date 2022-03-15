// December 12th, 2019

var a = 134792;
var b = 675810;

var valid = 0;

// It is a six-digit number.
// The value is within the range given in your puzzle input.
for (var i = a; i <= b; i++){

	// Going from left to right, the digits never decrease; they only ever increase or stay the same (like 111123 or 135679).
	if (Number(i.toString()[0]) <= Number(i.toString()[1]) && Number(i.toString()[1]) <= Number(i.toString()[2]) && Number(i.toString()[2]) <= Number(i.toString()[3]) && Number(i.toString()[3]) <= Number(i.toString()[4]) && Number(i.toString()[4]) <= Number(i.toString()[5])){
		
		// Two adjacent digits are the same (like 22 in 122345). ...the two adjacent matching digits are not part of a larger group of matching digits.
		var digits = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0];
		for (var j = 0; j < 6; j++){
			digits[Number(i.toString()[j])]++;
		}
		for (var j = 0; j < 10; j++){
			if (digits[j] == 2){
				valid++;
				break;
			}
		}
	}
}

console.log("Valid numbers: " + valid);
