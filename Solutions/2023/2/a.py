# December 1st, 2023

file = open(f"{__file__}/../input.txt", "r")

c = { "red": 12, "green": 13, "blue": 14 }
s = 0
for i, line in enumerate(file, 1):
	v = True
	for cubes in line[line.find(":") + 2:-1].split("; "):
		for [ count, color ] in [ cube.split(" ") for cube in cubes.split(", ") ]:
			if int(count) > c[color]:
				v = False
				break
		if not v: break
	if v: s += i
print(s)
