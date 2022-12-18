# December 17th, 2022

file = open(f"{__file__}/../input.txt", "r")

s = set()
for line in file:
	line = line.strip()
	t = tuple(int(x) for x in line.split(","))
	s.add(t)

c = 0
for t in s:
	x, y, z = t
	for d in [ (x + 1, y, z), (x, y + 1, z), (x, y, z + 1), (x - 1, y, z), (x, y - 1, z), (x, y, z - 1) ]:
		if d not in s:
			c += 1
print(c)
