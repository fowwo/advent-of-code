# December 21st, 2022

file = open(f"{__file__}/../input.txt", "r")

wall = set()
tile = set()

width = 0
height = 0

line = file.readline()[:-1]
i = 0
while line:
	for j, c in enumerate(line):
		if c == ".": tile.add((i, j))
		elif c == "#": wall.add((i, j))
		width = max(width, j)
	line = file.readline()[:-1]
	i += 1
height = i

instructions = file.readline().strip()
file.close()

path = []
while len(instructions) > 0:
	if instructions[0].isdigit():
		i = 1
		while instructions[:i + 1].isdigit() and i < len(instructions):
			i += 1
		path.append(int(instructions[:i]))
		instructions = instructions[i:]
	else:
		path.append(instructions[0])
		instructions = instructions[1:]

x, y = (0, min(t[1] for t in tile if t[0] == 0))
d = 0
for i in path:
	if isinstance(i, int):
		for _ in range(i):
			cx, cy = x, y
			if d == 0:
				cy += 1
				while (cx, cy) not in tile and (cx, cy) not in wall:
					cy = (cy + 1) % width
			elif d == 1:
				cx += 1
				while (cx, cy) not in tile and (cx, cy) not in wall:
					cx = (cx + 1) % height
			elif d == 2:
				cy -= 1
				while (cx, cy) not in tile and (cx, cy) not in wall:
					cy = (cy - 1) % width
			else:
				cx -= 1
				while (cx, cy) not in tile and (cx, cy) not in wall:
					cx = (cx - 1) % height
			if (cx, cy) in tile:
				x, y = cx, cy
			else:
				break
	elif i == "R":
		d = (d + 1) % 4
	else:
		d = (d - 1) % 4

print(1000 * (x + 1) + 4 * (y + 1) + d)
