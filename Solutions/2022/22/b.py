# December 22nd, 2022 - Only works on input with the same shape as my own input.

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
		cd = d
		for _ in range(i):
			if d == 0:
				c = (x, y + 1)
				if c not in tile and c not in wall:
					if y + 1 >= width:
						c = (150 - (x % 50) - 1, 99)
						cd = 2
					elif y + 1 >= 100:
						if x >= 50 and x <= 99:
							c = (49, 100 + (x % 50))
							cd = 3
						else:
							c = (50 - (x % 50) - 1, 149)
							cd = 2
					else:
						c = (149, (x % 50) + 50)
						cd = 3
			elif d == 1:
				c = (x + 1, y)
				if c not in tile and c not in wall:
					if x + 1 >= height:
						c = (0, (y % 50))
					elif x + 1 >= 50 and x + 1 <= 99:
						c = (50 + (y % 50), 99)
						cd = 2
					else:
						c = (150 + (y % 50), 49)
						cd = 2
			elif d == 2:
				c = (x, y - 1)
				if c not in tile and c not in wall:
					if y - 1 < 0:
						if x >= 100 and x <= 149:
							c = (50 - (x % 50) - 1, 50)
							cd = 0
						else:
							c = (0, (x % 50) + 50)
							cd = 1
					else:
						if x >= 51 and x <= 100:
							c = (100, (x % 50))
							cd = 1
						else:
							c = (150 - (x % 50) - 1, 0)
							cd = 0
			else:
				c = (x - 1, y)
				if c not in tile and c not in wall:
					if x - 1 < 0:
						if y >= 50 and y <= 99:
							c = (150 + (y % 50), 0)
							cd = 0
						else:
							c = (199, (y % 50))
					else:
						c = (y + 50, 50)
						cd = 0
			if c in tile:
				x, y = c
				d = cd
			else:
				break
	elif i == "R":
		d = (d + 1) % 4
	else:
		d = (d - 1) % 4

print(1000 * (x + 1) + 4 * (y + 1) + d)
