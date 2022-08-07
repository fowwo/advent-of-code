# August 6th, 2022

file = open(f"{__file__}/../input.txt", "r")

ranges = []
line = file.readline().strip()
while line != "":
	s = line.split(": ")[1].split(" or ")
	r = [ x.split('-') for x in s ]
	r = [ (int(x[0]), int(x[1])) for x in r ]
	ranges.append(r)
	line = file.readline().strip()
file.readline()
file.readline()
file.readline()
file.readline()

s = 0
for line in file:
	ticket = [ int(x) for x in line.split(',') ]
	for x in ticket:
		valid = False
		for r in ranges:
			if (x >= r[0][0] and x <= r[0][1]) or (x >= r[1][0] and x <= r[1][1]):
				valid = True
				break
		if not valid: s += x
file.close()

print(s)
