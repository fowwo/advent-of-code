# December 11th, 2024

from collections import deque

file = open(f"{__file__}/../input.txt", "r")
grid = [ [ x for x in line.strip() ] for line in file ]
file.close()

def price(r, c):
	v = grid[r][c]
	m = deque([ (r, c) ])
	d = { (r, c): 0 }
	while m:
		(r, c) = m.pop()
		for (x, y) in [ (r + 1, c), (r, c + 1), (r - 1, c), (r, c - 1) ]:
			if 0 <= x < len(grid) and 0 <= y < len(grid[0]) and grid[x][y] == v and (x, y) not in d:
				d[(x, y)] = 0
				m.append((x, y))

	for (r, c) in d:
		if (r + 1, c) not in d: d[(r, c)] |= 1 << 0
		if (r - 1, c) not in d: d[(r, c)] |= 1 << 1
		if (r, c + 1) not in d: d[(r, c)] |= 1 << 2
		if (r, c - 1) not in d: d[(r, c)] |= 1 << 3

	s = 0
	for (r, c) in d:
		for i, (dx, dy) in enumerate([ (0, 1), (0, 1), (1, 0), (1, 0) ]):
			if d[(r, c)] & (1 << i):
				d[(r, c)] -= (1 << i)
				x, y = r + dx, c + dy
				while (x, y) in d and d[(x, y)] & (1 << i):
					d[(x, y)] -= (1 << i)
					x += dx	
					y += dy
				x, y = r - dx, c - dy
				while (x, y) in d and d[(x, y)] & (1 << i):
					d[(x, y)] -= (1 << i)
					x -= dx	
					y -= dy
				s += 1
	for (x, y) in d: grid[x][y] = None
	return len(d) * s

s = 0
for r, row in enumerate(grid):
	for c, x in enumerate(row):
		if x:
			s += price(r, c)
print(s)
