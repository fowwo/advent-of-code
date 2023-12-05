# December 4th, 2023

file = open(f"{__file__}/../input.txt", "r")
seeds = map(int, file.readline().split(": ")[1].split())

maps = []
file.readline()
while file.readline().strip():
	m = []
	while (line := file.readline().strip()):
		m.append(tuple(map(int, line.split())))
	maps.append(m)

n = None
for seed in seeds:
	for m in maps:
		for (d, s, l) in m:
			if s <= seed and seed < s + l:
				seed += d - s
				break
	if n: n = min(n, seed)
	else: n = seed
print(n)
