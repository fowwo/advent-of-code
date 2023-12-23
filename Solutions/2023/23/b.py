# December 23rd, 2023

file = open(f"{__file__}/../input.txt", "r")
grid = [ line.strip() for line in file ]

space = set()
slope = dict()
for r, row in enumerate(grid):
	for c, x in enumerate(row):
		if x != '#': space.add((r, c))

start = (0, grid[0].index('.'))
end = (len(grid) - 1, grid[len(grid) - 1].index('.'))
V = set([ start, end ])
for (r, c) in space:
	if sum(p in space for p in [ (r - 1, c), (r, c + 1), (r + 1, c), (r, c - 1) ]) > 2:
		V.add((r, c))

d = dict((v, dict()) for v in V)
for v, l in d.items():
	m = [ v ]
	s = set(m)
	i = 0
	while m:
		i += 1
		n = []
		for (r, c) in m:
			for p in [ (r - 1, c), (r, c + 1), (r + 1, c), (r, c - 1) ]:
				if p in s or p not in space: continue

				s.add(p)
				if p in V: l[p] = i
				else: n.append(p)
		m = n

m = { "": ([ start ], 0) }
v = 0
while m:
	n = dict()
	for path, c in m.values():
		nv = d[path[-1]]
		if end in nv:
			v = max(v, c + nv[end])
			continue
		for p, t in nv.items():
			if p in path: continue

			newPath = path + [ p ]
			k = str(sorted(newPath) + [ p ])
			if k not in n or n[k][1] < c + t: n[k] = (newPath, c + t)
	m = n
print(v)
