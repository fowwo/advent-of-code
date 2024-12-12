# December 11th, 2024

from collections import deque

file = open(f"{__file__}/../input.txt", "r")
grid = [ [ x for x in line.strip() ] for line in file ]
file.close()

def price(r, c):
	v = grid[r][c]
	m = deque([ (r, c) ])
	s = set(m)
	p = 0
	while m:
		(r, c) = m.pop()
		for (x, y) in [ (r + 1, c), (r, c + 1), (r - 1, c), (r, c - 1) ]:
			if not (0 <= x < len(grid) and 0 <= y < len(grid[0])) or grid[x][y] != v:
				p += 1
			elif (x, y) not in s:
				s.add((x, y))
				m.append((x, y))
	for (x, y) in s: grid[x][y] = None
	return len(s) * p

s = 0
for r, row in enumerate(grid):
	for c, x in enumerate(row):
		if x:
			s += price(r, c)
print(s)
