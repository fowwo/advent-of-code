# August 1st, 2022

file = open("input.txt", 'r')

nanobots = []
for line in file:
	s = line.strip().split(", ")
	r = int(s[1][2:])
	s = [ int(x) for x in s[0][5:-1].split(',') ]
	nanobots.append((s[0], s[1], s[2], r))
file.close()

m = max(nanobots, key=lambda x: x[3])
count = 0
for n in nanobots:
	if abs(n[0] - m[0]) + abs(n[1] - m[1]) + abs(n[2] - m[2]) <= m[3]:
		count += 1
print(count)
