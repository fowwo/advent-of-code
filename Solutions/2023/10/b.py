# December 14th, 2023

file = open(f"{__file__}/../input.txt", "r")
grid = [ list(line.strip()) for line in file ]

def cell(r, c):
	if r < 0 or r >= len(grid) or c < 0 or c >= len(grid[0]): return '.'
	return grid[r][c]

loop = set()
for i, row in enumerate(grid):
	for j, x in enumerate(row):
		if x != 'S': continue

		p = [ cell(i - 1, j) in "|7F", cell(i, j + 1) in "-J7", cell(i + 1, j) in "|LJ", cell(i, j - 1) in "-LF" ]
		if p[0] and p[1]: grid[i][j], d = 'L', 2
		elif p[0] and p[2]: grid[i][j], d = '|', 0
		elif p[0] and p[3]: grid[i][j], d = 'J', 2
		elif p[1] and p[2]: grid[i][j], d = 'F', 0
		elif p[1] and p[3]: grid[i][j], d = '-', 1
		else: grid[i][j], d = '7', 1

		t = { 'L': { 2: 1, 3: 0 }, 'J': { 1: 0, 2: 3 }, '7': { 1: 2, 0: 3 }, 'F': { 0: 1, 3: 2 } }
		while (i, j) not in loop:
			loop.add((i, j))
			if (k := grid[i][j]) in t: d = t[k][d]
			i, j = [ (i - 1, j), (i, j + 1), (i + 1, j), (i, j - 1) ][d]
		break
	if loop: break

s = 0
for i, row in enumerate(grid):
	b = 0
	for j, x in enumerate(row):
		if (i, j) in loop:
			if x == '|': b = (b + 2) % 4
			elif x in "L7": b = (b + 1) % 4
			elif x in "JF": b = (b - 1) % 4
		elif b == 2: s += 1
print(s)
