# December 22nd, 2023

file = open(f"{__file__}/../input.txt", "r")
grid = [ line.strip() for line in file ]

space = set()
slope = dict()
for r, row in enumerate(grid):
	for c, x in enumerate(row):
		if x == '#': continue
		space.add((r, c))
		if x != '.': slope[(r, c)] = "^>v<".index(x)

start = (0, grid[0].index('.'))
end = (len(grid) - 1, grid[len(grid) - 1].index('.'))
m = [ (start, { start }) ]
v = i = 0
while m:
	i += 1
	n = []
	for (r, c), s in m:
		if (r, c) in slope:
			p = [ (r - 1, c), (r, c + 1), (r + 1, c), (r, c - 1) ][slope[(r, c)]]
			if p in s: continue

			s.add(p)
			if p == end: v = i
			else: n.append((p, s))
		else:
			for p in [ (r - 1, c), (r, c + 1), (r + 1, c), (r, c - 1) ]:
				if p in s or p not in space: continue

				if p == end: v = i
				else: n.append((p, s | { p }))
	m = n
print(v)
