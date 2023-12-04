# December 3rd, 2023

file = open(f"{__file__}/../input.txt", "r")
lines = [ line.split(": ")[1].strip() for line in file ]
cards = [ [ set(map(int, y.split())) for y in x.split(" | ") ] for x in lines ]

s = 0
for winning, numbers in cards:
	if (v := sum(1 if x in winning else 0 for x in numbers)):
		s += 1 << (v - 1)
print(s)
