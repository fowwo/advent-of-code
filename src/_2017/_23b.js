// March 8th, 2022

/*
Original Puzzle Input (jumps visualized):

set b 99
set c b
jnz a 2 -----+
jnz 1 5 -----|--+
mul b 100 <--+  |
sub b -100000   |
set c b         |
sub c -17000    |
set f 1 <-------+-----+
set d 2               |
set e 2 <----------+  |
set g d <-------+  |  |
mul g e         |  |  |
sub g b         |  |  |
jnz g 2 -----+  |  |  |
set f 0      |  |  |  |
sub e -1 <---+  |  |  |
set g e         |  |  |
sub g b         |  |  |
jnz g -8 -------+  |  |
sub d -1           |  |
set g d            |  |
sub g b            |  |
jnz g -13 ---------+  |
jnz f 2 -----+        |
sub h -1     |        |
set g b <----+        |
sub g c               |
jnz g 2 -----+        |
jnz 1 3 -----|--+     |
sub b -17 <--+  |     |
jnz 1 -23 ------|-----+
   <------------+



===============================

Simplified beginning:

b = 109900
c = 126900
set f 1 <-------------+
set d 2               |
set e 2 <----------+  |
set g d <-------+  |  |
mul g e         |  |  |
sub g b         |  |  |
jnz g 2 -----+  |  |  |
set f 0      |  |  |  |
sub e -1 <---+  |  |  |
set g e         |  |  |
sub g b         |  |  |
jnz g -8 -------+  |  |
sub d -1           |  |
set g d            |  |
sub g b            |  |
jnz g -13 ---------+  |
jnz f 2 -----+        |
sub h -1     |        |
set g b <----+        |
sub g c               |
jnz g 2 -----+        |
jnz 1 3 -----|--+     |
sub b -17 <--+  |     |
jnz 1 -23 ------|-----+
   <------------+



===============================

Psuedocode:

b = 109900
c = 126900
h = 0
f = 1 <----------------------+
d = 2                        |
e = 2 <-------------------+  |
g = d <----------------+  |  |
g *= e                 |  |  |
g -= b                 |  |  |
jump if g != 0 -----+  |  |  |
f = 0               |  |  |  |
e += 1   <----------+  |  |  |
g = e                  |  |  |
g -= b                 |  |  |
jump if g != 0 --------+  |  |
d += 1                    |  |
g = d                     |  |
g -= b                    |  |
jump if g != 0 -----------+  |
jump if f != 0 -----+        |
h += 1              |        |
g = b <-------------+        |
g -= c                       |
jump if g != 0 -----+        |
return              |        |
b += 17 <-----------+        |
jump ------------------------+



===============================

Psuedocode (without g):

b = 109900
c = 126900
h = 0
f = 1 <----------------------+
d = 2                        |
e = 2 <-------------------+  |
    <------------------+  |  |
jump if d * e != b -+  |  |  |
f = 0               |  |  |  |
e += 1 <------------+  |  |  |
jump if e != b --------+  |  |
d += 1                    |  |
jump if d != b -----------+  |
jump if f != 0 -----+        |
h += 1              |        |
    <---------------+        |
jump if b != c -----+        |
return              |        |
b += 17 <-----------+        |
jump ------------------------+



===============================

Psuedocode (no forward jumps):

b = 109900
c = 126900
h = 0
f = 1 <----------------------+
d = 2                        |
e = 2 <-------------------+  |
if (d * e == b): <-----+  |  |
    f = 0              |  |  |
e += 1                 |  |  |
jump if e != b --------+  |  |
d += 1                    |  |
jump if d != b -----------+  |
if (f == 0):                 |
    h += 1                   |
if (b == c):                 |
    return                   |
b += 17                      |
jump ------------------------+



===============================

JavaScript (loop 1/3):

b = 109900
c = 126900
h = 0
f = 1 <---------------------------+
d = 2                             |
    <--------------------------+  |
for (var e = 2; e != b; e++) { |  |
	if (d * e == b) {          |  |
		f = 0;                 |  |
	}                          |  |
}                              |  |
d += 1                         |  |
jump if d != b ----------------+  |
if (f == 0):                      |
    h += 1                        |
if (b == c):                      |
    return                        |
b += 17                           |
jump -----------------------------+



===============================

JavaScript (loop 2/3):

b = 109900
c = 126900
h = 0
f = 1 <----------------------------+
for (var d = 2; d != b; d++) {     |
	for (var e = 2; e != b; e++) { |
		if (d * e == b) {          |
			f = 0;                 |
		}                          |
	}                              |
}                                  |
if (f == 0):                       |
    h += 1                         |
if (b == c):                       |
    return                         |
b += 17                            |
jump ------------------------------+



===============================

JavaScript (loop 3/3):

b = 109900
c = 126900
h = 0
while (true) {
	f = 1
	for (var d = 2; d != b; d++) {
		for (var e = 2; e != b; e++) {
			if (d * e == b) {
				f = 0;
			}
		}
	}
	if (f == 0):
		h += 1
	if (b == c):
		break
	b += 17
}



===============================

JavaScript:

var b = 109900;
var c = 126900;
var h = 0;
while (true) {
	var f = 1;
	for (var d = 2; d != b; d++) { // This is checking for primes!
		for (var e = 2; e != b; e++) {
			if (d * e == b) {
				f = 0;
			}
		}
	}
	if (f == 0) h++;
	if (b == c) break;
	b += 17
}
*/

// JavaScript (optimized)
var b = 109900;
var c = 126900;
var h = 0;
while (true) {
	var f = false;
	for (var d = 2; d <= Math.sqrt(b); d++) {
		for (var e = d; e != b; e++) {
			if (d * e == b) {
				f = true;
				h++;
				break;
			}
		}
		if (f) break;
	}
	if (b == c) break;
	b += 17
}
console.log(h); // h = The number of composite numbers
