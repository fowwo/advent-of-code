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

print(len(flipped))
