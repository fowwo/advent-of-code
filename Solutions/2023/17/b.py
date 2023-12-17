# December 17th, 2023

from heapq import heappush, heappop

file = open(f"{__file__}/../input.txt", "r")
grid = [ [ int(x) for x in line.strip() ] for line in file ]

S = { (0, 0, 1, 0), (0, 0, 2, 0) }
V = S | set((i, j, k, n) for i in range(len(grid)) for j in range(len(grid[0])) for k in range(4) for n in range(1, 11))
d = dict((v, float("inf")) for v in V)

queue = [ (0, v) for v in S ]
while queue:
	t, (i, j, k, n) = heappop(queue)
	V.remove((i, j, k, n))
	for z, (x, y) in enumerate([ (i - 1, j), (i, j + 1), (i + 1, j), (i, j - 1) ]):
		v = (x, y, z, n + 1 if z == k else 1)
		if v not in V or abs(z - k) == 2 or (k != z and n < 4): continue
		if (c := t + grid[x][y]) < d[v]:
			d[v] = c
			heappush(queue, (c, v))
print(min(d[(len(grid) - 1, len(grid[0]) - 1, k, n)] for k in [ 1, 2 ] for n in range(4, 11)))
