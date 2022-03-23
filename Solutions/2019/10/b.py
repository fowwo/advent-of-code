# March 23rd, 2022
import math

file = open("input.txt", 'r')

asteroids = set()
i = 0
for line in file:
	for c in range(0, len(line)):
		if line[c] == '#':
			asteroids.add(f"{i},{c}")
	i += 1

high = 0
for a in asteroids:
	count = 0
	split = a.split(',')
	ar = int(split[0])
	ac = int(split[1])
	for b in asteroids:
		if a == b: continue
		split = b.split(',')
		br = int(split[0])
		bc = int(split[1])
		dr = br - ar
		dc = bc - ac
		if dc == 0:
			dr = dr / abs(dr)
		else:
			dr /= abs(dc)
			dc = dc / abs(dc)
		r = ar + dr
		c = ac + dc
		sight = True
		tr = round(100 * r) / 100 # Floating-point error
		while tr != br or c != bc:
			if tr % 1 == 0 and f"{int(tr)},{int(c)}" in asteroids:
				sight = False
				break
			r += dr
			c += dc
			tr = round(100 * r) / 100
		if sight:
			count += 1
	if count > high:
		high = count
		highR = ar
		highC = ac

# Divide positions
right = []
left = []
above = []
below = []
for a in asteroids:
	split = a.split(',')
	ar = int(split[0]) - highR
	ac = int(split[1]) - highC
	if ac > 0:
		right.append([ar + highR, ac + highC, math.atan(ar / ac)])
	elif ac < 0:
		left.append([ar + highR, ac + highC, math.atan(ar / ac)])
	elif ar < 0:
		above.append([ar + highR, ac + highC])
	elif ar > 0:
		below.append([ar + highR, ac + highC])
right.sort(key=lambda x: (x[2], x[1]))
left.sort(key=lambda x: (x[2], -x[1]))
above.sort(key=lambda x: -x[0])
below.sort(key=lambda x: x[0])

# Lazer
count = 0
while True:
	# Above
	if len(above):
		x = above.pop(0)
		count += 1
		if count == 200:
			print(100 * x[1] + x[0])
			exit()
	# Right
	i = 0
	removed = None
	while i < len(right):
		if removed and right[i][2] == removed[2]:
			i += 1
		else:
			removed = right.pop(i)
			count += 1
			if count == 200:
				print(100 * removed[1] + removed[0])
				exit()
	# Below
	if len(below):
		x = below.pop(0)
		count += 1
		if count == 200:
			print(100 * x[1] + x[0])
			exit()
	# Left
	i = 0
	removed = None
	while i < len(left):
		if removed and left[i][2] == removed[2]:
			i += 1
		else:
			removed = left.pop(i)
			count += 1
			if count == 200:
				print(100 * removed[1] + removed[0])
				exit()
