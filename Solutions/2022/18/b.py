# December 17th, 2022

file = open(f"{__file__}/../input.txt", "r")

s = set()
for line in file:
	line = line.strip()
	t = tuple(int(x) for x in line.split(","))
	s.add(t)

minx, miny, minz = (min(x[0] for x in s), min(x[1] for x in s), min(x[2] for x in s))
maxx, maxy, maxz = (max(x[0] for x in s), max(x[1] for x in s), max(x[2] for x in s))

air = set()
m = [ (-1, -1, -1) ]
while len(m):
	new = []
	for t in m:
		x, y, z = t
		for d in [ (x + 1, y, z), (x, y + 1, z), (x, y, z + 1), (x - 1, y, z), (x, y - 1, z), (x, y, z - 1) ]:
			dx, dy, dz = d
			if dx < minx - 1 or dy < miny - 1 or dz < minz - 1: continue
			if dx > maxx + 1 or dy > maxy + 1 or dz > maxz + 1: continue
			if d not in s and d not in air:
				air.add(d)
				new.append(d)
	m = new

c = 0
for t in air:
	x, y, z = t
	for d in [ (x + 1, y, z), (x, y + 1, z), (x, y, z + 1), (x - 1, y, z), (x, y - 1, z), (x, y, z - 1) ]:
		if d in s:
			c += 1
print(c)
