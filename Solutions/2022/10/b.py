# December 9th, 2022

file = open(f"{__file__}/../input.txt", "r")

x = 1
s = set()
cycle = 0
for line in file:
	args = line.strip().split(" ")
	if x >= cycle % 40 - 1 and x <= cycle % 40 + 1:
		s.add((cycle // 40, cycle % 40))
	if args[0] == "addx":
		v = int(args[1])
		cycle += 1
		if x >= cycle % 40 - 1 and x <= cycle % 40 + 1:
			s.add((cycle // 40, cycle % 40))
		x += v
	cycle += 1

for r in range(6):
	for c in range(40):
		if (r, c) in s:
			print("#", end="")
		else:
			print(" ", end="")
	print()
