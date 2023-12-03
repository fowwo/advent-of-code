# December 2nd, 2023

file = open(f"{__file__}/../input.txt", "r")
lines = [ line.strip() for line in file ]

symbols = set()
for r, line in enumerate(lines):
	for c, x in enumerate(line):
		if x != '.' and not x.isdigit():
			symbols.add((r, c))

s = 0
for r, line in enumerate(lines):
	v = 0
	part = False
	for c, x in enumerate(line):
		if x.isdigit():
			v = 10 * v + int(x)
			for i in range(-1, 2):
				for j in range(-1, 2):
					if (r + i, c + j) in symbols:
						part = True
						break
				if part: break
		else:
			if part:
				s += v
				part = False
			v = 0
	if part:
		s += v
print(s)
