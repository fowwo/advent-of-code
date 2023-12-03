# December 2nd, 2023

file = open(f"{__file__}/../input.txt", "r")
lines = [ line.strip() for line in file ]

digits = dict()
gears = []
for r, line in enumerate(lines):
	for c, x in enumerate(line):
		if x.isdigit():
			digits[(r, c)] = int(x)
		elif x == "*":
			gears.append((r, c))

s = 0
for (r, c) in gears:
	m = set()
	n = 0
	ratio = 1
	for i in range(-1, 2):
		for j in range(-1, 2):
			if (r + i, c + j) in digits and (r + i, c + j) not in m:
				if (n := n + 1) > 2: break
				while (r + i, c + j - 1) in digits: j -= 1
				v = 0
				while (r + i, c + j) in digits:
					m.add((r + i, c + j))
					v = 10 * v + digits[(r + i, c + j)]
					j += 1
				ratio *= v
		if n > 2: break
	if n == 2:
		s += ratio
print(s)
