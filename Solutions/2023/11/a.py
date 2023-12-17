# December 16th, 2023

file = open(f"{__file__}/../input.txt", "r")
grid = [ line.strip() for line in file ]

emptyR = [ i for i, x in enumerate(grid) if all(y == '.' for y in x) ]
emptyC = [ i for i, x in enumerate(zip(*grid)) if all(y == '.' for y in x) ]

galaxies = []
for r, line in enumerate(grid):
	for c, x in enumerate(line):
		if x == '#':
			galaxies.append((r, c))

s = 0
for i, (x1, y1) in enumerate(galaxies[:-1]):
	for (x2, y2) in galaxies[i+1:]:
		a = sum(1 if (x1 < x) == (x < x2) else 0 for x in emptyR)
		b = sum(1 if (y1 < y) == (y < y2) else 0 for y in emptyC)
		s += abs(x2 - x1) + abs(y2 - y1) + a + b
print(s)
