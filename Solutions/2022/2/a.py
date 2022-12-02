# December 1st, 2022

file = open(f"{__file__}/../input.txt", "r")

score = 0
for line in file.readlines():
	line = line.strip().split(" ")
	elf = "ABC".find(line[0])
	player = "XYZ".find(line[1])
	score += player + 1
	if elf == player:
		score += 3
	elif (elf + 1) % 3 == player:
		score += 6
print(score)
