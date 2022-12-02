# December 1st, 2022

file = open(f"{__file__}/../input.txt", "r")

score = 0
for line in file.readlines():
	line = line.strip().split(" ")
	elf = "ABC".find(line[0])
	outcome = "XYZ".find(line[1])
	score += (elf + outcome - 1) % 3 + 3 * outcome + 1
print(score)
