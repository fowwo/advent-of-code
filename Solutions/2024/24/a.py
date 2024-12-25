# December 23rd, 2024

file = open(f"{__file__}/../input.txt", "r")

d = dict()
while (line := file.readline().strip()):
	a, b = line.split(": ")
	d[a] = int(b)

m = []
while (line := file.readline().strip()):
	a, b = line.split(" -> ")
	x, y, z = a.split(" ")
	m.append((x, y, z, b))
file.close()

while m:
	(x, g, y, z) = m.pop(0)
	if x not in d or y not in d:
		m.append((x, g, y, z))
		continue
	if g == "AND": d[z] = d[x] & d[y]
	elif g == "OR": d[z] = d[x] | d[y]
	else: d[z] = d[x] ^ d[y]

s = "".join(str(d[x]) for x in sorted((x for x in d if x[0] == 'z'), reverse = True))
print(int(s, 2))
