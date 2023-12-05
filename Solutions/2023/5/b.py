# December 5th, 2023

file = open(f"{__file__}/../input.txt", "r")
seeds = list(map(int, file.readline().split(": ")[1].split()))

maps = []
file.readline()
while file.readline().strip():
	m = []
	while (line := file.readline().strip()):
		m.append(tuple(map(int, line.split())))
	maps.append(m)

ranges = list(zip(seeds[::2], seeds[1::2]))
for m in maps:
	mapped = []
	for (d, s, l) in m:
		unmapped = []
		for (x, y) in ranges:
			if x < s + l and s < x + y:
				if a := max(s - x, 0):
					unmapped.append((x, a))
				if b := max((x + y) - (s + l), 0):
					unmapped.append((s + l, b))
				mapped.append((max(s, x) + d - s, y - a - b))
			else:
				unmapped.append((x, y))
		ranges = unmapped
	ranges += mapped
print(min(ranges)[0])
