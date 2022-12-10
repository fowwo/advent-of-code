# December 9th, 2022

file = open(f"{__file__}/../input.txt", "r")

x = 1
s = 0
cycle = 0
for line in file:
	args = line.strip().split(" ")
	cycle += 1
	if (cycle - 20) % 40 == 0:
		s += cycle * x
	if args[0] == "addx":
		v = int(args[1])
		cycle += 1
		if (cycle - 20) % 40 == 0:
			s += cycle * x
		x += v
print(s)
