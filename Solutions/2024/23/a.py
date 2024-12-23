# December 22nd, 2024

d = dict()

file = open(f"{__file__}/../input.txt", "r")
for line in file:
	a, b = line.strip().split("-")
	if a not in d: d[a] = set()
	if b not in d: d[b] = set()
	d[a].add(b)
	d[b].add(a)
file.close()

s = set()
for x in d:
	for y in d[x]:
		for z in d[y]:
			if x in d[z] and any(c[0] == "t" for c in [ x, y, z ]):
				s.add(tuple(sorted((x, y, z))))

print(len(s))
