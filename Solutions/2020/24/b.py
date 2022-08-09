# August 9th, 2022

file = open(f"{__file__}/../input.txt", "r")

flipped = set()
for line in file:
	line = line.strip()
	x = 0
	y = 0
	i = 0
	while i < len(line):
		if line[i] == 'n':
			y += 1
			i += 1
			if line[i] == 'w':
				x -= 1
		elif line[i] == 's':
			y -= 1
			i += 1
			if line[i] == 'e':
				x += 1
		elif line[i] == 'e':
			x += 1
		else:
			x -= 1
		i += 1
	if (x, y) in flipped:
		flipped.remove((x, y))
	else:
		flipped.add((x, y))
file.close()

def surrounding(x, y):
	c = 0
	if (x - 1, y) in flipped: c += 1
	if (x - 1, y + 1) in flipped: c += 1
	if (x, y - 1) in flipped: c += 1
	if (x, y + 1) in flipped: c += 1
	if (x + 1, y - 1) in flipped: c += 1
	if (x + 1, y) in flipped: c += 1
	return c

for _ in range(100):
	new = set()
	minx = min(flipped, key=lambda x: x[0])[0]
	miny = min(flipped, key=lambda x: x[1])[1]
	maxx = max(flipped, key=lambda x: x[0])[0]
	maxy = max(flipped, key=lambda x: x[1])[1]
	for x in range(minx - 1, maxx + 2):
		for y in range(miny - 1, maxy + 2):
			v = surrounding(x, y)
			if v == 2 or ((x, y) in flipped and v == 1):
				new.add((x, y))
	flipped = new
print(len(flipped))
