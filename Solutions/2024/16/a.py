# December 23rd, 2024

from heapq import heappop, heappush

file = open(f"{__file__}/../input.txt", "r")
grid = [ list(line.strip()) for line in file ]
file.close()

start = end = None
for r, row in enumerate(grid):
	for c, x in enumerate(row):
		if x == 'S':
			start = (r, c)
		elif x == 'E':
			end = (r, c)

heap = [ (0, start, (0, 1)) ]
s = { start }
while True:
	(v, (r, c), (dr, dc)) = heappop(heap)
	if (r, c) == end:
		print(v)
		exit()
	n = [ (v + 1, (r + dr, c + dc), (dr, dc)) ]
	if dr == 0: n += [ (v + 1001, (r + 1, c), (1, 0)), (v + 1001, (r - 1, c), (-1, 0)) ]
	else: n += [ (v + 1001, (r, c + 1), (0, 1)), (v + 1001, (r, c - 1), (0, -1)) ]
	for (v, (x, y), d) in n:
		if grid[x][y] != '#' and (x, y) not in s:
			s.add((x, y))
			heappush(heap, (v, (x, y), d))
