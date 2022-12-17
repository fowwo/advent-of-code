# December 17th, 2022

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

j = 0
top = 0

def drop(i):
	global j, top
	rock = [ (r + top + 4, c) for r, c in types[i] ]
	land = False
	while not land:
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
		for x in copy:
			if x in m:
				land = True
				break
		if not land:
			rock = copy
		else:
			for x in rock:
				top = max(top, x[0])
				m.add(x)
		j += 1
		j %= len(line)

memo = set()
i = 0
while (i % 5, j) not in memo:
	memo.add((i % 5, j))
	drop(i % 5)
	i += 1

p = (i % 5, j)
d = i
td = top
drop(i % 5)
i += 1

while (i % 5, j) != p:
	drop(i % 5)
	i += 1

d = i - d
td = top - td
n = (1000000000000 - i) // d
i += d * n

while i < 1000000000000:
	drop(i % 5)
	i += 1

print(top + td * n)
