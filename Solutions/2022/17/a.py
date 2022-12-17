# December 16th, 2022

file = open(f"{__file__}/../input.txt", "r")
line = file.readline().strip()
file.close()

types = [
	[ (0, 2), (0, 3), (0, 4), (0, 5) ],
	[ (0, 3), (1, 2), (1, 3), (1, 4), (2, 3) ],
	[ (2, 4), (1, 4), (0, 2), (0, 3), (0, 4) ],
	[ (0, 2), (1, 2), (2, 2), (3, 2) ],
	[ (0, 2), (0, 3), (1, 2), (1, 3) ]
]

m = set()
for i in range(7):
	m.add((0, i))

i = 0
j = 0
top = 0
rock = [ (r + top + 4, c) for r, c in types[i] ]
while i < 2022:
	s = line[j]

	# Push
	copy = [ (r, c - 1) for r, c in rock ] if s == "<" else [ (r, c + 1) for r, c in rock ]
	v = True
	for x in copy:
		if x in m or x[1] < 0 or x[1] >= 7:
			v = False
			break
	if v: rock = copy

	# Fall
	copy = [ (r - 1, c) for r, c in rock ]
	v = True
	for x in copy:
		if x in m:
			v = False
			break
	if v:
		rock = copy
	else:
		for x in rock:
			top = max(top, x[0])
			m.add(x)
		i += 1
		rock = [ (r + top + 4, c) for r, c in types[i % 5] ]

	j += 1
	j %= len(line)

print(top)
