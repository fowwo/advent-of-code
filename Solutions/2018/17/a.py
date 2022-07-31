# July 30th, 2022

file = open("input.txt", 'r')

clay = set()
for line in file:
	s = line.strip().split(", ")
	if line[0] == 'x':
		x = int(s[0][2:])
		r = [ int(x) for x in s[1][2:].split("..") ]
		for y in range(r[0], r[1] + 1):
			clay.add((x, y))
	else:
		y = int(s[0][2:])
		r = [ int(x) for x in s[1][2:].split("..") ]
		for x in range(r[0], r[1] + 1):
			clay.add((x, y))
file.close()

positions = [(500, 0)]
top = min(clay, key=lambda x: x[1])[1]
bottom = max(clay, key=lambda x: x[1])[1]
water = set()
while len(positions):
	pos = positions.pop()
	x = pos[0]
	y = pos[1]
	while y <= bottom:
		water.add((x, y))
		if (x, y + 1) in clay:
			# Ground
			left = x
			right = x
			fall = False
			while (left - 1, y) not in clay:
				left -= 1
				water.add((left, y))
				if (left, y + 1) not in clay:
					fall = True
					positions.append((left, y + 1))
					break
			while (right + 1, y) not in clay:
				right += 1
				water.add((right, y))
				if (right, y + 1) not in clay:
					fall = True
					positions.append((right, y + 1))
					break
			if fall: break
			for i in range(left, right + 1): clay.add((i, y))
			y -= 1
		else:
			# Fall
			y += 1
for x in set(water):
	if x[1] < top:
		water.remove(x)
print(len(water))
