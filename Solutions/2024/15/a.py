# December 23rd, 2024

file = open(f"{__file__}/../input.txt", "r")
grid = []
while (line := file.readline().strip()):
	grid.append(list(line))

robot = None
for r, row in enumerate(grid):
	for c, x in enumerate(row):
		if x == '@':
			robot = (r, c)
			grid[r][c] = '.'
			break
	if robot: break

(r, c) = robot
while (line := file.readline().strip()):
	for x in line:
		(dr, dc) = [ (1, 0), (-1, 0), (0, 1), (0, -1) ]["v^><".index(x)]
		(tr, tc) = (r + dr, c + dc)
		while grid[tr][tc] == 'O':
			(tr, tc) = (tr + dr, tc + dc)
		if grid[tr][tc] != '#':
			while (tr, tc) != (r, c):
				grid[tr][tc] = grid[tr - dr][tc - dc]
				(tr, tc) = (tr - dr, tc - dc)
			(r, c) = (r + dr, c + dc)

n = 0
for r, row in enumerate(grid):
	for c, x in enumerate(row):
		if x == 'O':
			n += 100 * r + c
print(n)
