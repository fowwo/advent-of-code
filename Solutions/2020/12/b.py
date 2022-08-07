# August 6th, 2022

file = open(f"{__file__}/../input.txt", "r")

sx = 0
sy = 0
wx = 10
wy = 1
for line in file:
	action = line[0]
	magnitude = int(line[1:-1])
	if (action == 'N'):
		wy += magnitude
	elif (action == 'S'):
		wy -= magnitude
	elif (action == 'E'):
		wx += magnitude
	elif (action == 'W'):
		wx -= magnitude
	elif (action == 'F'):
		sx += magnitude * wx
		sy += magnitude * wy
	else:
		if magnitude == 180:
			wx *= -1
			wy *= -1
		else:
			t = wx
			wx = wy
			wy = t
			if (action == 'L' and magnitude == 90) or (action == 'R' and magnitude == 270):
				wx *= -1
			else:
				wy *= -1

file.close()

print(abs(sx) + abs(sy))
