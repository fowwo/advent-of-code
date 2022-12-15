# December 14th, 2022

file = open(f"{__file__}/../input.txt", "r")

def inRange(point, beacon):
	return abs(beacon[0] - point[0]) + abs(beacon[1] - point[1]) <= beacon[2]

sensors = set()
for line in file:
	args = line.strip().split(" ")
	sx = int(args[2][2:-1])
	sy = int(args[3][2:-1])
	bx = int(args[8][2:-1])
	by = int(args[9][2:])

	r = abs(bx - sx) + abs(by - sy)
	sensors.add((sx, sy, r))

for sensor in sensors:
	bx = sensor[0]
	by = sensor[1]
	r = sensor[2]
	for i in range(r):
		for p in [ (bx+i,by+i-r-1), (bx-i,by-i+r+1), (bx+i-r-1,by+i), (bx-i+r+1,by-i) ]:
			if p[0] < 0 or p[1] < 0 or p[0] > 4000000 or p[1] > 4000000: continue
			v = True
			for s in sensors:
				if inRange(p, s):
					v = False
					break
			if v:
				print(4000000 * p[0] + p[1])
				exit()
