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

heap = [ (0, start, (0, 1), dict()) ]
dist = dict()
V = set()
L = dict()
minimumScore = 1000 * len(grid) * len(grid[0])
while heap:
	(v, (r, c), (dr, dc), path) = heappop(heap)
	if v > minimumScore: continue

	path[(r, c)] = (dr, dc)
	if (r, c) == end:
		minimumScore = v
		V |= path.items()
		continue

	n = [ (v + 1, (r + dr, c + dc), (dr, dc)) ]
	if dr == 0: n += [ (v + 1001, (r + 1, c), (1, 0)), (v + 1001, (r - 1, c), (-1, 0)) ]
	else: n += [ (v + 1001, (r, c + 1), (0, 1)), (v + 1001, (r, c - 1), (0, -1)) ]
	for (v, (x, y), d) in n:
		if grid[x][y] != '#' and (x, y) not in path:
			if ((x, y), d) in dist:
				if dist[((x, y), d)] == v:
					if ((x, y), d) not in L:
						L[((x, y), d)] = set()
					L[((x, y), d)] |= path.items()
			else:
				dist[((x, y), d)] = v
				heappush(heap, (v, (x, y), d, dict(path)))

previousSize = -1
while previousSize != (previousSize := len(V)):
	for p, s in L.items():
		if p in V:
			V |= s

print(len(set(x[0] for x in V)))
