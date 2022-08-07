# August 6th, 2022

file = open(f"{__file__}/../input.txt", "r")

x = 0
y = 0
direction = 0
for line in file:
	action = line[0]
	magnitude = int(line[1:-1])
	if (action == 'N'):
		y += magnitude
	elif (action == 'S'):
		y -= magnitude
	elif (action == 'E'):
		x += magnitude
	elif (action == 'W'):
		x -= magnitude
	elif (action == 'L'):
		direction -= (magnitude / 90)
		direction %= 4
	elif (action == 'R'):
		direction += (magnitude / 90)
		direction %= 4
	else:
		if direction == 0:
			x += magnitude
		elif direction == 1:
			y -= magnitude
		elif direction == 2:
			x -= magnitude
		else:
			y += magnitude

file.close()

print(abs(x) + abs(y))
