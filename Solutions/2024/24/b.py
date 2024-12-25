# December 24th, 2024

file = open(f"{__file__}/../input.txt", "r")

while (line := file.readline().strip()): pass

AND, OR, XOR = dict(), dict(), dict()
while (line := file.readline().strip()):
	a, z = line.split(" -> ")
	x, g, y = a.split(" ")
	if g == "AND":
		if x not in AND: AND[x] = dict()
		if y not in AND: AND[y] = dict()
		AND[x][y] = z
		AND[y][x] = z
	elif g == "OR":
		if x not in OR: OR[x] = dict()
		if y not in OR: OR[y] = dict()
		OR[x][y] = z
		OR[y][x] = z
	else:
		if x not in XOR: XOR[x] = dict()
		if y not in XOR: XOR[y] = dict()
		XOR[x][y] = z
		XOR[y][x] = z
file.close()

swappedWires = set()

# The output of each AND gate must be sent to an OR gate,
# except for the first carry out bit (x00 & y00).
for x, d in AND.items():
	for y, z in d.items():
		if x < y and (x, y) != ("x00", "y00") and z not in OR: 
			swappedWires.add(z)

# The output of each OR gate must be the input to an AND gate and an XOR gate,
# except the last carry out bit (z45).
for x, d in OR.items():
	for y, z in d.items():
		if x < y and z != "z45" and (z not in AND or z not in XOR):
			swappedWires.add(z)

# The output of each XOR gate...
for x, d in XOR.items():
	for y, z in d.items():
		if x > y: continue

		# ...must be "z##" or sent to an AND gate and another XOR gate.
		if z[0] != 'z' and (z not in AND or z not in XOR):
			swappedWires.add(z)

		# ...whose input is not "x##" and "y##" must be "z##".
		elif x[0] != 'x' and z[0] != 'z':
			swappedWires.add(z)

# At this point, my checks found 8 incorrect output wires.
# These checks may not be sufficient for all puzzle inputs.
print(",".join(sorted(swappedWires)))
