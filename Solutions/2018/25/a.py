# August 5th, 2022

file = open("input.txt", 'r')
points = set(tuple(map(int, x.strip().split(','))) for x in file.readlines())
file.close()

count = 0
while len(points):
	s = set([points.pop()])
	new = True
	while new:
		new = False
		for a in set(points):
			for b in s:
				if abs(a[0] - b[0]) + abs(a[1] - b[1]) + abs(a[2] - b[2]) + abs(a[3] - b[3]) <= 3:
					s.add(a)
					points.remove(a)
					new = True
					break
	count += 1
print(count)
