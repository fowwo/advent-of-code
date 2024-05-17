# December 23rd, 2023

file = open(f"{__file__}/../input.txt", "r")
lines = [ line.strip().split(' @ ') for line in file ]

hailstones = []
for p, v in lines:
	px, py, pz = map(int, p.split(", "))
	vx, vy, vz = map(int, v.split(", "))
	hailstones.append(((px, py, pz), (vx, vy, vz)))

l = 200000000000000
r = 400000000000000
s = 0
for i in range(len(hailstones) - 1):
	for j in range(i + 1, len(hailstones)):
		a = (px1, py1, pz1), (vx1, vy1, vz1) = hailstones[i]
		b = (px2, py2, pz2), (vx2, vy2, vz2) = hailstones[j]
		if vy1 * vx2 - vx1 * vy2 == 0: continue # parallel
		x = ((vy1 / vx1) * px1 - (vy2 / vx2) * px2 + py2 - py1) / (vy1 / vx1 - vy2 / vx2)
		y = (vy1 / vx1) * (x - px1) + py1
		if abs((px1) - x) < abs((px1 + vx1) - x) or abs((px2) - x) < abs((px2 + vx2) - x):
			continue # past
		if l <= x and x <= r and l <= y and y <= r: s += 1
print(s)
