# December 1st, 2023

file = open(f"{__file__}/../input.txt", "r")

s = 0
for i, line in enumerate(file, 1):
	c = { "red": 0, "green": 0, "blue": 0 }
	for cubes in line[line.find(":") + 2:-1].split("; "):
		for [ count, color ] in [ cube.split(" ") for cube in cubes.split(", ") ]:
			c[color] = max(c[color], int(count))
	s += c["red"] * c["green"] * c["blue"]
print(s)
