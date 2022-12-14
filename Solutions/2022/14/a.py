# December 13th, 2022

file = open(f"{__file__}/../input.txt", "r")

m = set()
for line in file:
	points = [ tuple(int(x) for x in y.split(",")) for y in line.strip().split(" -> ") ]
	for i in range(len(points) - 1):
		if points[i][0] == points[i + 1][0]:
			l = sorted([ points[i][1], points[i + 1][1] ])
			for j in range(l[0], l[1] + 1):
				m.add((points[i][0], j))
		else:
			l = sorted([ points[i][0], points[i + 1][0] ])
			for j in range(l[0], l[1] + 1):
				m.add((j, points[i][1]))

bottom = max(m, key = lambda x: x[1])[1]

c = 0
while True:
	x = 500
	y = 0
	while y < bottom:
		if ((x, y + 1) not in m):
			y += 1
		elif ((x - 1, y + 1) not in m):
			x -= 1
			y += 1
		elif ((x + 1, y + 1) not in m):
			x += 1
			y += 1
		else:
			m.add((x, y))
			c += 1
			break
	if y == bottom: break
print(c)
