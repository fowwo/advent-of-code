# December 24th, 2024

file = open(f"{__file__}/../input.txt", "r")

locks = []
keys = []
while (line := file.readline().strip()):
	schematic = [ line ]
	while (line := file.readline().strip()):
		schematic.append(line)

	heights = tuple(column.index(column[-1]) for column in zip(*schematic))
	if schematic[0][0] == '#': locks.append(heights)
	else: keys.append(heights)
file.close()

print(sum(all(x <= y for x, y in zip(lock, key)) for lock in locks for key in keys))
