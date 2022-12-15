# December 14th, 2022

file = open(f"{__file__}/../input.txt", "r")

s = set()

row = 2000000
for line in file:
	args = line.strip().split(" ")
	sx = int(args[2][2:-1])
	sy = int(args[3][2:-1])
	bx = int(args[8][2:-1])
	by = int(args[9][2:])

	d = abs(bx - sx) + abs(by - sy)
	l = d - abs(sy - row)
	for i in range(-l, l + 1):
		s.add(sx + i)
	if by == row:
		s.remove(bx)
print(len(s))
