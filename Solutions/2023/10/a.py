# December 13th, 2023

file = open(f"{__file__}/../input.txt", "r")
grid = [ line.strip() for line in file ]

r = c = None
for i, row in enumerate(grid):
	for j, x in enumerate(row):
		if x == 'S':
			r, c = i, j
			break
	if r: break

def cell(r, c):
	if r < 0 or r >= len(grid) or c < 0 or c >= len(grid[0]): return '.'
	return grid[r][c]

m = []
if cell(r - 1, c) in "|7F": m.append((r - 1, c, 0))
if cell(r, c + 1) in "-J7": m.append((r, c + 1, 1))
if cell(r + 1, c) in "|LJ": m.append((r + 1, c, 2))
if cell(r, c - 1) in "-LF": m.append((r, c - 1, 3))
(ax, ay, ad), (bx, by, bd) = m

def move(x, y, d):
	return [ (x - 1, y), (x, y + 1), (x + 1, y), (x, y - 1) ][d]

s = 1
t = { 'L': { 2: 1, 3: 0 }, 'J': { 1: 0, 2: 3 }, '7': { 1: 2, 0: 3 }, 'F': { 0: 1, 3: 2 } }
while (ax, ay) != (bx, by):
	if (k := grid[ax][ay]) in t: ad = t[k][ad]
	if (k := grid[bx][by]) in t: bd = t[k][bd]
	ax, ay = move(ax, ay, ad)
	bx, by = move(bx, by, bd)
	s += 1
print(s)
