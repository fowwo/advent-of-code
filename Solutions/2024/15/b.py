# December 23rd, 2024

file = open(f"{__file__}/../input.txt", "r")
grid = []
while (line := file.readline().strip()):
	grid.append([ x for x in line for _ in range(2) ])

robot = None
for r, row in enumerate(grid):
	for c, x in enumerate(row):
		if x == '@':
			robot = (r, c)
			grid[r][c] = '.'
			grid[r][c + 1] = '.'
		elif x == 'O':
			grid[r][c] = '['
			grid[r][c + 1] = ']'

def canMoveBoxVertically(r, c, dr):
	if grid[r + dr][c] == '#' or grid[r + dr][c + 1] == '#': return False
	if grid[r + dr][c] == '[': return canMoveBoxVertically(r + dr, c, dr)
	if grid[r + dr][c] == ']' and not canMoveBoxVertically(r + dr, c - 1, dr): return False
	if grid[r + dr][c + 1] == '[' and not canMoveBoxVertically(r + dr, c + 1, dr): return False
	return True
def moveBoxVertically(r, c, dr):
	if grid[r + dr][c] == '[': moveBoxVertically(r + dr, c, dr)
	if grid[r + dr][c] == ']': moveBoxVertically(r + dr, c - 1, dr)
	if grid[r + dr][c + 1] == '[': moveBoxVertically(r + dr, c + 1, dr)
	grid[r + dr][c] = '['
	grid[r + dr][c + 1] = ']'
	grid[r][c] = '.'
	grid[r][c + 1] = '.'

(r, c) = robot
while (line := file.readline().strip()):
	for x in line:
		(dr, dc) = [ (1, 0), (-1, 0), (0, 1), (0, -1) ]["v^><".index(x)]
		(tr, tc) = (r + dr, c + dc)
		if grid[tr][tc] == '.':
			(r, c) = (tr, tc)
		elif x in "<>":
			while grid[tr][tc] in "[]":
				(tr, tc) = (tr + dr, tc + dc)
			if grid[tr][tc] != '#':
				while (tr, tc) != (r, c):
					grid[tr][tc] = grid[tr - dr][tc - dc]
					(tr, tc) = (tr - dr, tc - dc)
				(r, c) = (r + dr, c + dc)
		elif grid[tr][tc] != '#':
			i = "[]".index(grid[tr][tc])
			if canMoveBoxVertically(tr, tc - i, dr):
				moveBoxVertically(tr, tc - i, dr)
				(r, c) = (tr, tc)

n = 0
for r, row in enumerate(grid):
	for c, x in enumerate(row):
		if x == '[':
			n += 100 * r + c
print(n)
