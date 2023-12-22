# December 21st, 2023

file = open(f"{__file__}/../input.txt", "r")
lines = [ line.strip().split('~') for line in file ]

bricks = []
for a, b in lines:
	a = tuple(map(int, a.split(',')))
	b = tuple(map(int, b.split(',')))
	bricks.append(sorted((a, b), key = lambda x: x[2]))
bricks.sort(key = lambda x: x[0][2])

m = dict()
f = dict()
for i, ((x1, y1, z1), (x2, y2, z2)) in enumerate(bricks):
	z = z1
	a1, a2 = sorted((x1, x2))
	b1, b2 = sorted((y1, y2))
	segments = [ (a, b) for a in range(a1, a2 + 1) for b in range(b1, b2 + 1) ]
	while z > 1 and all(s + (z - 1,) not in m for s in segments): z -= 1
	for s in segments: m[s + (z + abs(z1 - z2),)] = i
	below = set(m[p] for p in [ s + (z - 1,) for s in segments ] if p in m)
	f[i] = below if len(below) else { -1 }

s = 0
for i in range(len(bricks)):
	b = { i }
	p = None
	while b != p:
		p = b
		for k, v in f.items():
			if k in b: continue
			if v.issubset(b):
				b.add(k)
	s += len(b) - 1
print(s)
