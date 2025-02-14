# December 19th, 2024

file = open(f"{__file__}/../input.txt", "r")
grid = [ list(line.strip()) for line in file ]
file.close()

p = None
for r, row in enumerate(grid):
	for c, x in enumerate(row):
		if x == 'S':
			p = (r, c)
		elif x == 'E':
			grid[r][c] = '.'

n = i = 0
while p:
	m = [ p ]
	s = set(m)
	for d in range(1, 21):
		m2 = []
		for (r, c) in m:
			for (x, y) in [ (r + 1, c), (r, c + 1), (r - 1, c), (r, c - 1) ]:
				if 0 <= x < len(grid) and 0 <= y < len(grid[0]) and (x, y) not in s:
					s.add((x, y))
					m2.append((x, y))
					if isinstance(grid[x][y], int) and i - grid[x][y] - d >= 100:
						n += 1
		m = m2
	(r, c) = p
	grid[r][c] = i
	p = None
	for (x, y) in [ (r + 1, c), (r, c + 1), (r - 1, c), (r, c - 1) ]:
		if grid[x][y] == '.':
			p = (x, y)
			break
	i += 1
print(n)
