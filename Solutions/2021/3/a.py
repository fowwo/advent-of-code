# December 3rd, 2021

file = open(f"{__file__}/../input.txt", "r")
line = file.readline().strip()
length = len(line)
count = []
total = 0

for i in range(length):
	count.append(0)

while line:
	total += 1
	for i in range(length):
		if line[i] == "1":
			count[i] += 1
	line = file.readline().strip()

gamma = 0
for i in range(length):
	gamma <<= 1
	if count[i] > total / 2:
		gamma += 1

epsilon = 2 ** length - 1 - gamma
print(gamma * epsilon)
